#include "font_types.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include "pixel_utils.h"
#include "gameplay_utils.h"
#include "mzapo_regs.h"
#include "mzapo_parlcd.h"

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

extern unsigned short *fb;
extern font_descriptor_t *fdes;


void snake_init(snake_t *snake, int x, int y, int score){
    snake->head = 3;
    snake->tail = 0;
    int idx = 0;
    for(;idx < score;idx++){
        snake->coords[3 - idx].x = x;
        snake->coords[3 - idx].y = y + idx * BODY_SEGMENT;
    }
    for(;idx < MAX_LEN;idx++){
        snake->coords[3 - idx].x = 0;
        snake->coords[3 - idx].y = 0;
    }
}

void draw_game_field(){
    // top part of screen is fully black and the rest of screen is green
    for(int idx = 0; idx < HEIGHT*WIDTH; idx++){
        if(idx < BODY_SEGMENT * WIDTH)
            fb[idx] = 0;
        else
            fb[idx] = FIELD;
    }
}

void compute_movement(int prev_pos, int cur_pos, int *direction){
    int idx = *direction;
    int diff = cur_pos - prev_pos;

    if((diff > 0 && diff < 100) || (diff < -100)){
        // turn to right
        idx = (idx == 3) ? 0 : idx + 1;
    }
    if((diff < 0 && diff > -100) || (diff > 100)){
        // turn to left
        idx = (idx == 0) ? 3 : idx - 1;
    }
    *direction = idx;
}

void draw_snake(snake_t snake, int score, unsigned int color){
    draw_snake_head_up(snake.coords[snake.head].x, snake.coords[snake.head].y, color);
    for(int i = score - 2;i >= 0;i--){
        draw_pixel_big(snake.coords[i].x, snake.coords[i].y, color, 20);
    }
}

int redraw_snake(snake_t *snake, int direction, int *score, unsigned int snake_clr){
    unsigned int apple = hsv2rgb_lcd(0, 225, 225);
    int prev = 0;
    
    // update head coordinates
    prev = snake->head;
    snake->head = (snake->head == MAX_LEN - 1) ? 0 : snake->head + 1; 
    if(direction == 0){ // up
        snake->coords[snake->head].x = snake->coords[prev].x;
        snake->coords[snake->head].y = snake->coords[prev].y - BODY_SEGMENT;
    }
    else if(direction == 1){ // right
        snake->coords[snake->head].x = snake->coords[prev].x + BODY_SEGMENT;
        snake->coords[snake->head].y = snake->coords[prev].y;
    }
    else if(direction == 2){ // down
        snake->coords[snake->head].x = snake->coords[prev].x;
        snake->coords[snake->head].y = snake->coords[prev].y + BODY_SEGMENT;
    }
    else if(direction == 3){ // left
        snake->coords[snake->head].x = snake->coords[prev].x - BODY_SEGMENT;
        snake->coords[snake->head].y = snake->coords[prev].y;
    }
    
    if(snake->coords[snake->head].x < 0 || snake->coords[snake->head].y < 20 || snake->coords[snake->head].x >= WIDTH || snake->coords[snake->head].y >= HEIGHT)
        return WALL_HIT;
    
    draw_pixel_big(snake->coords[prev].x, snake->coords[prev].y, snake_clr, 20); // redraw head to body
    prev = (snake->coords[snake->head].x + 8) + WIDTH * (snake->coords[snake->head].y + 8);
    
    if(fb[prev] != FIELD && fb[prev] != apple){
        return CLASH;
    }
    
    if(fb[prev] == apple){
        (*score)++;
    }
    else{
        // update tail coordinates
        draw_pixel_big(snake->coords[snake->tail].x, snake->coords[snake->tail].y, FIELD, 20); // delete tail, draw_pixel_big starts with left UPPER corner
        snake->tail = (snake->tail == MAX_LEN - 1) ? 0 : snake->tail + 1;
    }

    if(direction == 0){ // up
        draw_snake_head_up(snake->coords[snake->head].x, snake->coords[snake->head].y, snake_clr);
    }
    else if(direction == 1){ // right
        draw_snake_head_right(snake->coords[snake->head].x, snake->coords[snake->head].y, snake_clr);
    }
    else if(direction == 2){ // down
        draw_snake_head_down(snake->coords[snake->head].x, snake->coords[snake->head].y, snake_clr);
    }
    else if(direction == 3){ // left
        draw_snake_head_left(snake->coords[snake->head].x, snake->coords[snake->head].y, snake_clr);
    }
    
    return OK;
}

void LCD_update(uint32_t *lcd_value, unsigned char *mem_base, int cnt){
    if(cnt == 0){
        *lcd_value = 0;
    }
    
    *lcd_value += pow(2, TIME_SPAN - 1 - cnt);
    if(cnt == 0){
        *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0xff0707;
        *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0xff0707; 
    }
    else{
        *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB1_o) = 0x0; 
        *(volatile uint32_t*)(mem_base + SPILED_REG_LED_RGB2_o) = 0x0; 
    }

    *(volatile uint32_t*)(mem_base + SPILED_REG_LED_LINE_o) = *lcd_value; // load number on a microcontroller's lamps
}


void place_apple(){
    int x, y;
    do{
        x = (rand() % 24) * BODY_SEGMENT; // 24 * BODY_SEGMENT = WIDTH
        y = (rand() % 15 + 1) * (BODY_SEGMENT); // + 1 because of the score field at the top
        if(fb[x + y * WIDTH] == FIELD && fb[x + (BODY_SEGMENT - 1) + y * WIDTH] == FIELD){
            draw_apple(x, y);
            break;
        }
    } while(1);
}

void update_score(int start, int score, unsigned int color){
    draw_pixel_big(start + 56, 2, 0, 16);
    draw_pixel_big(start + 66, 2, 0, 16);
    draw_pixel_big(start + 76, 2, 0, 16);
    
    draw_char(start + 76, 2, (score % 10) + '0', color, 1);
    draw_char(start + 66, 2, ((score % 100)/10) + '0', color, 1);
    draw_char(start + 56, 2, (score / 100) + '0', color, 1);
}
