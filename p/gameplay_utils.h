#include "end_page_utils.h"

#define MAX_LEN 360

void draw_game_field();

void compute_movement(int prev_pos, int cur_pos, int *direction);

void snake_init(snake_t *snake, int x, int y, int score);

void draw_snake(snake_t snake, int score, unsigned int color);

int redraw_snake(snake_t *snake, int direction, int *score, unsigned int snake_clr);

void LCD_update(uint32_t *lcd_value, unsigned char *mem_base, int cnt);

void place_apple();

void update_score(int start, int score, unsigned int color);

void draw_the_winner(int player_number, int y, unsigned short color, int scale, int letter_spacing);

void draw_score(int score, int y, unsigned short color, int scale, int letter_spacing);

