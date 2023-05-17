#define _POSIX_C_SOURCE 200112L

#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <time.h>
#include <unistd.h>
#include <termios.h>            //termios, TCSANOW, ECHO, ICANON
#include <math.h>
#include <string.h>
 
#include "mzapo_parlcd.h"
#include "mzapo_phys.h"
#include "mzapo_regs.h"
#include "font_types.h"

#include "pixel_utils.h"
#include "print_words.h"
#include "draw_pictures.h"
#include "gameplay_utils.h"

#define WIDTH 480
#define HEIGHT 320
#define GAP 60 // gap between lines in menu 
#define BODY_SEGMENT 20 // size of a one snake's body segment
#define MAX_LEN 360
#define TIME_SPAN 32
#define OK 0
#define CLASH 1
#define WALL_HIT 2
#define SELF_HIT 3
#define ENEMY_HIT 4
#define MUTUAL_HIT 5
#define WIN 6
#define COLOR_P1 0x04a1
#define COLOR_P2 0xb467
#define FIELD 13254

unsigned short *fb;
font_descriptor_t *fdes;
 
int draw_home_page(unsigned char *parlcd_mem_base, unsigned char *mem_base, int *players, int *difficulty);
void draw_game_page(unsigned char *parlcd_mem_base, unsigned char *mem_base, int players, int difficulty, int *score_p1, int *score_p2);
void draw_menu_choice(int prev_position, int cur_position, int *cur_choice);
void draw_players_choice(int players);
void draw_difficulty_choice(int difficulty);



int main(int argc, char *argv[]) {
    unsigned char *parlcd_mem_base, *mem_base;
    int difficulty = 1; // medium difficulty by delault
    int players = 1; // 1 player by default
    int score_p1 = 0;
    int score_p2 = 0;
    int idx = 0;
    int ret = 0;
    fb  = (unsigned short *)malloc(HEIGHT * WIDTH * sizeof(unsigned short)); // representation of the screen
    int knob_descriptor;

    printf("Start a game!\n");
        
    // map address for the display 
    parlcd_mem_base = map_phys_address(PARLCD_REG_BASE_PHYS, PARLCD_REG_SIZE, 0); // adderess, size, ?? 
    if (parlcd_mem_base == NULL)
        exit(1);
    
    // mem base stores info about all peripherals in this desc 
    mem_base = map_phys_address(SPILED_REG_BASE_PHYS, SPILED_REG_SIZE, 0);
    if (mem_base == NULL)
        exit(1);
 
    parlcd_hx8357_init(parlcd_mem_base);
 
    // set default values of the display pixels
    parlcd_write_cmd(parlcd_mem_base, 0x2c);
    for(int i = 0; i < HEIGHT ; i++) {
        for(int j = 0; j < WIDTH ; j++) {
            fb[idx] = 0;
            parlcd_write_data(parlcd_mem_base, fb[idx]);
            idx++;
        }
    }

    knob_descriptor = *(volatile uint32_t*)(mem_base + SPILED_REG_KNOBS_8BIT_o);
    while(1){
        if((knob_descriptor & 0x20000000) != 0){
            difficulty = 1; // medium difficulty by delault
            players = 1; // 1 player by default
            score_p1 = 0;
            score_p2 = 0;
        }
        *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x0; 
        *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0x0; 

        ret = draw_home_page(parlcd_mem_base, mem_base, &players, &difficulty);
        if(ret == 1){
            // quit button was pressed
            break; 
        }

        score_p1 = 4;
        if(players == 2)
            score_p2 = 4;
        else
            score_p2 = 0;
        
        draw_game_page(parlcd_mem_base, mem_base, players, difficulty, &score_p1, &score_p2);
    }

    return 0;
}

int draw_home_page(unsigned char *parlcd_mem_base, unsigned char *mem_base, int *players, int *difficulty){
    // TODO: apple and snake sprites + colourful menu 
    int knob_detector; // detects knob actions
    int cur_choice = 0; // 0 = play, 1 = number of players, 2 = difficulty, 3 = quit
    int prev_position, cur_position;

    // set delay for loops
    struct timespec loop_delay;
    loop_delay.tv_sec = 0;
    loop_delay.tv_nsec = 150 * 1000 * 1000;
    fdes = &font_winFreeSystem14x16;

    knob_detector = *(volatile uint32_t*)(mem_base + SPILED_REG_KNOBS_8BIT_o);
    prev_position = (knob_detector >> 8) & 0xff;

    while(1){
        knob_detector = *(volatile uint32_t*)(mem_base + SPILED_REG_KNOBS_8BIT_o); // get info about what knobs were pressed
        cur_position = (knob_detector >> 8) & 0xff; // get green knob position

        if(cur_choice == 0 && (knob_detector & 0x2000000) != 0){
            return 0; // start the game, button play was pressed
        }
        if(cur_choice == 1 && (knob_detector & 0x2000000) != 0){
            *players = (*players == 1) ? 2 : 1; // change number of players
        }
        if(cur_choice == 2 && (knob_detector & 0x2000000) != 0){
            *difficulty = (*difficulty == 3) ? 0 : (*difficulty) + 1;
        }
        if(cur_choice == 3 && (knob_detector & 0x2000000) != 0){
            return 1; // quit the game, button quit was pressed
        }
        
        // screen is fully black
        for(int idx = 0; idx < HEIGHT*WIDTH; idx++){
            fb[idx]=0;
        }

        draw_game_menu(); // scale for game name is 6

        draw_menu_choice(prev_position, cur_position, &cur_choice); 

        draw_players_choice(*players);

        draw_difficulty_choice(*difficulty);

        // load done changes in fb array on the microcontroller's display
        parlcd_write_cmd(parlcd_mem_base, 0x2c);
        for(int idx = 0; idx < WIDTH*HEIGHT; idx++){
            parlcd_write_data(parlcd_mem_base, fb[idx]);
        }

        prev_position = cur_position;

        clock_nanosleep(CLOCK_MONOTONIC, 0, &loop_delay, NULL);
    }
}

void draw_game_page(unsigned char *parlcd_mem_base, unsigned char *mem_base, int players, int difficulty, int *score_p1, int *score_p2){
    int knob_detector; // detects knob actions
    uint32_t lcd_value = 4294963200; // 2^20
    int direction_p1 = 0; // movement relative to game field
    int direction_p2 = 0; // 0 = up 1 = right 2 = down 3 = left
    snake_t snake_p1;
    snake_t snake_p2;
    int green_prev_position, green_cur_position; // 1 player
    int blue_prev_position, blue_cur_position; // 2 player
    int cnt = 20; // number of made steps, each 30 step spawn an apple
    int ret = 0;
    
    snake_init(&snake_p1, 160, 120, *score_p1);
    snake_init(&snake_p2, 320, 120, *score_p2);
    
    printf("number of players: %d\n", players);

    // set delay for loops
    difficulty = 4 - difficulty;
    struct timespec loop_delay;
    loop_delay.tv_sec = 0;
    loop_delay.tv_nsec = difficulty * 50 * 1000 * 1000;
    fdes = &font_winFreeSystem14x16;
    
    draw_game_field();
    
    draw_snake(snake_p1, *score_p1, COLOR_P1);
    if(players == 2){
        draw_snake(snake_p2, *score_p2, COLOR_P2);
    }
    clock_nanosleep(CLOCK_MONOTONIC, 0, &loop_delay, NULL);

    knob_detector = *(volatile uint32_t*)(mem_base + SPILED_REG_KNOBS_8BIT_o);
    blue_prev_position = (knob_detector) & 0xff;
    green_prev_position = (knob_detector >> 8) & 0xff;
    
    display_game_info(0, COLOR_P1);
    if(players == 2)
        display_game_info(100, COLOR_P2);

    while(1){
        knob_detector = *(volatile uint32_t*)(mem_base + SPILED_REG_KNOBS_8BIT_o); // get info about what knobs were pressed
        blue_cur_position = (knob_detector) & 0xff;
        green_cur_position = (knob_detector >> 8) & 0xff;

        if((knob_detector & 0x4000000) != 0){
            return; // end the game
        }
        
        compute_movement(green_prev_position, green_cur_position, &direction_p1);
        ret = redraw_snake(&snake_p1, direction_p1, score_p1, COLOR_P1);
        if(ret != OK){
            int result;
            result = choose_the_end_page(ret, 1, *score_p1, *score_p2, snake_p1, snake_p2);
            if(ret == WALL_HIT && players == 2){
                compute_movement(blue_prev_position, blue_cur_position, &direction_p2);
                ret = redraw_snake(&snake_p2, direction_p2, score_p2, COLOR_P2);
                if(ret == WALL_HIT)
                    result = 4;
            }
            print_the_end_page(parlcd_mem_base, mem_base, result, 1, *score_p1, *score_p2);

            return;
        }
        update_score(0, *score_p1, COLOR_P1);
         
        if(players == 2){
            compute_movement(blue_prev_position, blue_cur_position, &direction_p2);
            ret = redraw_snake(&snake_p2, direction_p2, score_p2, COLOR_P2);
            if(ret != OK){
                int result;
                result = choose_the_end_page(ret, 2, *score_p1, *score_p2, snake_p1, snake_p2);
                print_the_end_page(parlcd_mem_base, mem_base, result, 2, *score_p1, *score_p2);
                return;
            }
            update_score(100, *score_p2, COLOR_P2);
        }

        if(cnt == TIME_SPAN){
            place_apple();
            cnt = 0;
        }
        else{
            cnt++;
        }

        LCD_update(&lcd_value, mem_base, cnt);

        // load done changes in fb array on the microcontroller's display
        parlcd_write_cmd(parlcd_mem_base, 0x2c);
        for(int idx = 0; idx < WIDTH*HEIGHT; idx++){
            parlcd_write_data(parlcd_mem_base, fb[idx]);
        }

        blue_prev_position = blue_cur_position;
        green_prev_position = green_cur_position;
        clock_nanosleep(CLOCK_MONOTONIC, 0, &loop_delay, NULL);
    }
}

void draw_menu_choice(int prev_position, int cur_position, int *cur_choice){
    unsigned int color = hsv2rgb_lcd(123, 250, 150); 
    int diff = cur_position - prev_position;
    
    if((diff > 0 && diff < 100) || (diff < -100)){
        *cur_choice = (*cur_choice == 3) ? 0 : (*cur_choice) + 1;
    }
    if((diff < 0 && diff > -100) || (diff > 100)){
        *cur_choice = (*cur_choice == 0) ? 3 : (*cur_choice) - 1;
    }

    draw_snake_head_right(10, 100 + (*cur_choice) * GAP, color);
}

void draw_players_choice(int players){
    if(players == 1)
        draw_apple(142, 175);
    else
        draw_apple(312, 175);
}

void draw_difficulty_choice(int difficulty){
    draw_apple(60 + difficulty * 110, 230);
}










