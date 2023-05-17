#include <stdio.h>
#include "end_page_utils.h"
#include "mzapo_regs.h"
#include "font_types.h"
#include "pixel_utils.h"
#include "mzapo_parlcd.h"

extern unsigned short *fb;
extern font_descriptor_t *fdes;

int choose_the_end_page(int ret, int action_owner, int score_p1, int score_p2, snake_t snake_p1, snake_t snake_p2){    
    if(ret == WALL_HIT){
        return 1;
    }
    else if(ret == WIN){
        return 2;
    }
    else if((action_owner == 2 && snake_p2.coords[snake_p2.head].x == snake_p1.coords[snake_p1.head].x && 
                                 snake_p2.coords[snake_p2.head].y == snake_p1.coords[snake_p1.head].y)){
        return 3;
    }
    else if(score_p2 != 0){
        for(int i = snake_p1.tail;i <= snake_p1.head;i++){
            if(snake_p1.coords[i].x == snake_p2.coords[snake_p2.head].x && 
                snake_p1.coords[i].y == snake_p2.coords[snake_p2.head].y){
                return 6;
            }
            if(i == MAX_LEN - 1)
                i = 0;
        }
        for(int i = snake_p2.tail;i <= snake_p2.head;i++){
            if(snake_p2.coords[i].x == snake_p1.coords[snake_p1.head].x && 
                snake_p2.coords[i].y == snake_p1.coords[snake_p1.head].y){
                return 5;
            }
            if(i == MAX_LEN - 1)
                i = 0;
        }     
    }
    
    return 7;
}

void print_the_end_page(unsigned char *parlcd_mem_base, unsigned char *mem_base, int result, int action_owner, int score_p1, int score_p2){
    // result: 1 = wall hit, 2 = win, 3 = draw (mutual hit), 4 = draw (score/simultanious death), 5 = enemy hit (1 -> 2), 6 = enemy hit (2 -> 1), 7 = self hit 
    for(int idx = 0; idx < HEIGHT*WIDTH; idx++)
        fb[idx] = 0;

    switch(result){
        case(1):
            // wall hit
            if(score_p2 == 0){
                *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0xff0000; 
                *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0xff0000;
            }
            else{
                if(action_owner == 1){
                    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0xfc4400;
                    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0xfc4400; 
                }
                else{
                    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x085004;
                    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0x085004; 
                }
            }
            
            print_wall_hit_result(action_owner, score_p1, score_p2);
            break;
        case(2):
            // win
            if(score_p2 == 0){
                *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x0fff00; 
                *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0x0fff00; 
            }
            else{
                if(score_p1 > score_p2){
                    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x085004; 
                    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0x085004; 
                }
                else{
                    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0xfc4400; 
                    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0xfc4400;
                }
            }
            print_win_result(action_owner, score_p1, score_p2);
            break;
        case(3):
            // draw
            *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x085004;  
            *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0xfc4400;

            print_draw_result(action_owner, score_p1, score_p2, 1);
            break;
        case(4):
            // draw
            *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x085004;  
            *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0xfc4400;

            print_draw_result(action_owner, score_p1, score_p2, 0);
            break;
        case(5):
            *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0xfc4400;
            *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0xfc4400; 

            print_enemy_hit_result(1, score_p1, score_p2);
            break;
        case(6):
            *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x085004;
            *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0x085004; 

            print_enemy_hit_result(2, score_p1, score_p2);
            break;
        case(7):
            if(action_owner == 1){
                *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0xfc4400;
                *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0xfc4400; 
            }
            else{
                *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x085004;
                *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0x085004; 
            }
            print_self_hit_result(action_owner, score_p1, score_p2);
            break;
    }

    // load done changes in fb array on the microcontroller's display
    parlcd_write_cmd(parlcd_mem_base, 0x2c);
    for(int idx = 0; idx < WIDTH*HEIGHT; idx++){
        parlcd_write_data(parlcd_mem_base, fb[idx]);
    }

    while(1){
        int knob_listener = *(volatile uint32_t*)(mem_base + SPILED_REG_KNOBS_8BIT_o);
        if ((knob_listener & 0x7000000)!=0) {
            break;
        }
    }
}

void print_wall_hit_result(int action_owner, int score_p1, int score_p2){
    if(score_p2 == 0){
        // Draw "You Lost" 
        draw_text_centered("You Lost", 70, 0x6F19, 5, 1);

        // Draw "You hit the wall" 
        draw_text_centered("You hit the wall", 160, 0xFFFF, 3, 1);

        // Draw score
        draw_score(score_p1, 250, 0xFFFF, 2, 1);

        draw_text_centered("Press green button to try again and red/blue button to leave", 300, 0xFFFF, 1, 1);
    }
    else{
        int number_of_the_winning_player = (action_owner == 1) ? 2 : 1;
        
        draw_the_winner(number_of_the_winning_player, 70, 0x6F19, 5, 1);
        draw_text_centered("Opponent hit the wall", 160, 0xFFFF, 3, 1);

        draw_score(score_p1, 220, 0x04a1, 2, 1); // 1st player's score
        draw_score(score_p2, 250, 0xb467, 2, 1); // 2nd player's score

        draw_text_centered("Press green button to try again and red/blue button to leave", 300, 0xFFFF, 1, 1);
    }
}

void print_self_hit_result(int action_owner, int score_p1, int score_p2){
    if(score_p2 == 0){
        draw_text_centered("You Lost", 70, 0x6F19, 5, 1);

        draw_text_centered("My gosh...", 150, 0xFFFF, 2, 1);
        draw_text_centered("You ate yourself ._.", 180, 0xFFFF, 3, 1);

        draw_score(score_p1, 250, 0xFFFF, 2, 1);

        draw_text_centered("Press green button to try again and red/blue button to leave", 300, 0xFFFF, 1, 1);
    }
    else{
        int number_of_the_winning_player = (action_owner == 1) ? 2 : 1;
        
        draw_the_winner(number_of_the_winning_player, 70, 0x6F19, 5, 1);
        draw_text_centered("Opponent commited suicide", 150, 0xFFFF, 2, 1);
        draw_text_centered(":_(", 180, 0xFFFF, 2, 1);

        draw_score(score_p1, 220, 0x04a1, 2, 1); // 1st player's score
        draw_score(score_p2, 250, 0xb467, 2, 1); // 2nd player's score

        draw_text_centered("Press green button to try again and red/blue button to leave", 300, 0xFFFF, 1, 1);
    }
}

void print_enemy_hit_result(int action_owner, int score_p1, int score_p2){
    int number_of_the_winning_player = (action_owner == 1) ? 2 : 1;
    
    draw_the_winner(number_of_the_winning_player, 70, 0x6F19, 5, 1);
    draw_text_centered("Opponent commited suicide", 150, 0xFFFF, 2, 1);
    draw_text_centered(":_(", 180, 0xFFFF, 2, 1);

    draw_score(score_p1, 220, 0x04a1, 2, 1); // 1st player's score
    draw_score(score_p2, 250, 0xb467, 2, 1); // 2nd player's score

    draw_text_centered("Press green button to try again and red/blue button to leave", 300, 0xFFFF, 1, 1);
}

void print_draw_result(int action_owner, int score_p1, int score_p2, int is_mutual_hit){
    draw_text_centered("Draw", 70, 0x6F19, 5, 1);
    if(is_mutual_hit)
        draw_text_centered("A kiss was detected", 160, 0xFFFF, 2, 1);
    else
        draw_text_centered("Really worthy opponents", 160, 0xFFFF, 2, 1);

    draw_score(score_p1, 220, 0x04a1, 2, 1); // 1st player's score
    draw_score(score_p2, 250, 0xb467, 2, 1); // 2nd player's score

    draw_text_centered("Press green button to try again and red/blue button to leave", 300, 0xFFFF, 1, 1);    
}

void print_win_result(int action_owner, int score_p1, int score_p2){
    int number_of_the_winning_player = (score_p1 > score_p2) ? score_p1 : score_p2;
    char player_number_text[18];
    sprintf(player_number_text, "#%d Won", number_of_the_winning_player);
    draw_text_centered(player_number_text, 70, 0xFFFF, 3, 1);
    draw_score(score_p1, 220, 0x04a1, 2, 1); // 1st player's score
    draw_score(score_p2, 250, 0xb467, 2, 1); // 2nd player's score
}

void draw_the_winner(int player_number, int y, unsigned short color, int scale, int letter_spacing) {
    char player_number_text[18];
    sprintf(player_number_text, "#%d Won", player_number);
    draw_text_centered(player_number_text, y, color, scale, letter_spacing);
}

void draw_score(int score, int y, unsigned short color, int scale, int letter_spacing) {
    char score_text[16];
    sprintf(score_text, "Score: %d", score);
    draw_text_centered(score_text, y, color, scale, letter_spacing);
}


