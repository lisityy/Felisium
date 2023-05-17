#include "pixel_utils.h"

void draw_snake_head_right(int x, int y, unsigned int color){
  // x and y are coords of left upper corner of the head
  draw_pixel_big(x, y, color, 16); // upper part of the snake
  draw_pixel_big(x, y + 4, color, 16); // bottom part of the snake
  draw_pixel_big(x + 16, y + 8, hsv2rgb_lcd(0, 225, 225), 4); // tongue
  draw_pixel_big(x + 7, y, hsv2rgb_lcd(60, 250, 194), 6); // yellow part of the left eye 
  draw_pixel_big(x + 10, y, hsv2rgb_lcd(60, 250, 194), 6);
  draw_pixel_big(x + 9, y + 2, hsv2rgb_lcd(60, 250, 0), 2); // black part of the left eye
  draw_pixel_big(x + 11, y + 2, hsv2rgb_lcd(60, 250, 0), 2); 
  draw_pixel_big(x + 13, y + 2, hsv2rgb_lcd(60, 250, 0), 2);
  draw_pixel_big(x + 7, y + 14, hsv2rgb_lcd(60, 250, 194), 6); // yellow part of the right eye 
  draw_pixel_big(x + 10, y + 14, hsv2rgb_lcd(60, 250, 194), 6);
  draw_pixel_big(x + 9, y + 16, hsv2rgb_lcd(60, 250, 0), 2); // black part of the right eye
  draw_pixel_big(x + 11, y + 16, hsv2rgb_lcd(60, 250, 0), 2); 
  draw_pixel_big(x + 13, y + 16, hsv2rgb_lcd(60, 250, 0), 2);
}

void draw_snake_head_left(int x, int y, unsigned int color){
  draw_pixel_big(x + 4, y, color, 16); // upper part of the snake
  draw_pixel_big(x + 4, y + 4, color, 16); // bottom part of the snake
  draw_pixel_big(x, y + 8, hsv2rgb_lcd(0, 225, 225), 4); // tongue
  draw_pixel_big(x + 5, y, hsv2rgb_lcd(60, 250, 194), 6); // yellow part of the left eye 
  draw_pixel_big(x + 8, y, hsv2rgb_lcd(60, 250, 194), 6);
  draw_pixel_big(x + 6, y + 2, hsv2rgb_lcd(60, 250, 0), 2); // black part of the left eye
  draw_pixel_big(x + 8, y + 2, hsv2rgb_lcd(60, 250, 0), 2); 
  draw_pixel_big(x + 10, y + 2, hsv2rgb_lcd(60, 250, 0), 2);
  draw_pixel_big(x + 5, y + 14, hsv2rgb_lcd(60, 250, 194), 6); // yellow part of the right eye 
  draw_pixel_big(x + 8, y + 14, hsv2rgb_lcd(60, 250, 194), 6);
  draw_pixel_big(x + 6, y + 16, hsv2rgb_lcd(60, 250, 0), 2); // black part of the right eye
  draw_pixel_big(x + 8, y + 16, hsv2rgb_lcd(60, 250, 0), 2); 
  draw_pixel_big(x + 10, y + 16, hsv2rgb_lcd(60, 250, 0), 2);
}

void draw_snake_head_up(int x, int y, unsigned int color){
  draw_pixel_big(x + 8, y, hsv2rgb_lcd(0, 225, 225), 4); // tongue
  draw_pixel_big(x, y + 4, color, 16); // left part of the snake
  draw_pixel_big(x + 4, y + 4, color, 16); // right part of the snake
  draw_pixel_big(x, y + 4, hsv2rgb_lcd(60, 250, 194), 6); // yellow part of the left eye 
  draw_pixel_big(x, y + 7, hsv2rgb_lcd(60, 250, 194), 6);
  draw_pixel_big(x + 2, y + 5, hsv2rgb_lcd(60, 250, 0), 2); // black part of the left eye
  draw_pixel_big(x + 2, y + 7, hsv2rgb_lcd(60, 250, 0), 2); 
  draw_pixel_big(x + 2, y + 9, hsv2rgb_lcd(60, 250, 0), 2);
  draw_pixel_big(x + 14, y + 4, hsv2rgb_lcd(60, 250, 194), 6); // yellow part of the right eye 
  draw_pixel_big(x + 14, y + 7, hsv2rgb_lcd(60, 250, 194), 6);
  draw_pixel_big(x + 16, y + 5, hsv2rgb_lcd(60, 250, 0), 2); // black part of the right eye
  draw_pixel_big(x + 16, y + 7, hsv2rgb_lcd(60, 250, 0), 2); 
  draw_pixel_big(x + 16, y + 9, hsv2rgb_lcd(60, 250, 0), 2);
}

void draw_snake_head_down(int x, int y, unsigned int color){
  draw_pixel_big(x + 8, y + 16, hsv2rgb_lcd(0, 225, 225), 4); // tongue
  draw_pixel_big(x, y, color, 16); // left part of the snake
  draw_pixel_big(x + 4, y, color, 16); // right part of the snake
  draw_pixel_big(x, y + 7, hsv2rgb_lcd(60, 250, 194), 6); // yellow part of the left eye 
  draw_pixel_big(x, y + 10, hsv2rgb_lcd(60, 250, 194), 6);
  draw_pixel_big(x + 2, y + 9, hsv2rgb_lcd(60, 250, 0), 2); // black part of the left eye
  draw_pixel_big(x + 2, y + 11, hsv2rgb_lcd(60, 250, 0), 2); 
  draw_pixel_big(x + 2, y + 13, hsv2rgb_lcd(60, 250, 0), 2);
  draw_pixel_big(x + 14, y + 7, hsv2rgb_lcd(60, 250, 194), 6); // yellow part of the right eye 
  draw_pixel_big(x + 14, y + 10, hsv2rgb_lcd(60, 250, 194), 6);
  draw_pixel_big(x + 16, y + 9, hsv2rgb_lcd(60, 250, 0), 2); // black part of the right eye
  draw_pixel_big(x + 16, y + 11, hsv2rgb_lcd(60, 250, 0), 2); 
  draw_pixel_big(x + 16, y + 13, hsv2rgb_lcd(60, 250, 0), 2);
}

void draw_apple(int x, int y){
  // x, y are coords of the left upper corner of the apple
  draw_pixel_big(x + 1, y + 9, hsv2rgb_lcd(0, 225, 225), 9); // middle part of apple
  draw_pixel_big(x + 10, y + 9, hsv2rgb_lcd(0, 225, 225), 9); // middle part of apple
  draw_pixel_big(x + 4, y + 9, hsv2rgb_lcd(0, 225, 225), 11); // bottom part of apple
  draw_pixel_big(x + 4, y + 6, hsv2rgb_lcd(0, 225, 225), 12); // upper part of an apple
  draw_pixel_big(x + 7, y + 3, hsv2rgb_lcd(22, 64, 26), 5); // apple stick
  draw_pixel_big(x + 8, y + 3, hsv2rgb_lcd(22, 64, 26), 5); // apple stick
  draw_pixel_big(x + 11, y, hsv2rgb_lcd(123, 88, 150), 4); // apple leaf
}
