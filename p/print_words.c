#include <stdio.h>
#include "pixel_utils.h"


void display_game_info(int start, unsigned int color){
    unsigned int leave = hsv2rgb_lcd(0, 0, 255);
    draw_char(start + 1, 2, 'S', color, 1);
    draw_char(start + 9, 2, 'c', color, 1);
    draw_char(start + 16, 2, 'o', color, 1);
    draw_char(start + 23, 2, 'r', color, 1);
    draw_char(start + 28, 2, 'e', color, 1);
    draw_char(start + 36, 2, ':', color, 1);

    draw_char(354, 2, 'P', leave, 1);
    draw_char(363, 2, 'r', leave, 1);
    draw_char(368, 2, 'e', leave, 1);
    draw_char(376, 2, 's', leave, 1);
    draw_char(384, 2, 's', leave, 1);

    draw_pixel_big(400, 2, hsv2rgb_lcd(0, 225, 225), 14);

    draw_char(421, 2, 't', leave, 1);
    draw_char(426, 2, 'o', leave, 1);

    draw_char(443, 2, 'l', leave, 1);
    draw_char(446, 2, 'e', leave, 1);
    draw_char(453, 2, 'a', leave, 1);
    draw_char(461, 2, 'v', leave, 1);
    draw_char(469, 2, 'e', leave, 1);
}

void draw_game_menu(){
    unsigned int color = hsv2rgb_lcd(225, 0, 225); 
    // draw game name
    draw_char(130, 1, 'S', hsv2rgb_lcd(123, 250, 150), 6);
    draw_char(179, 1, 'n', hsv2rgb_lcd(123, 250, 150), 6);
    draw_char(220, 1, 'a', hsv2rgb_lcd(123, 250, 150), 6);
    draw_char(262, 1, 'k', hsv2rgb_lcd(123, 250, 150), 6);
    draw_char(302, 1, 'e', hsv2rgb_lcd(123, 250, 150), 6);
    // draw play button
    draw_char(175, 80, 'P', hsv2rgb_lcd(123, 88, 182), 4);
    draw_char(215, 80, 'l', hsv2rgb_lcd(123, 88, 182), 4);
    draw_char(235, 80, 'a', hsv2rgb_lcd(123, 88, 182), 4);
    draw_char(273, 80, 'y', hsv2rgb_lcd(123, 88, 182), 4);
    // draw number of players
    draw_char(100, 140, '1', color, 2);
    draw_char(120, 140, 'p', color, 2);
    draw_char(135, 140, 'l', color, 2);
    draw_char(142, 140, 'a', color, 2);
    draw_char(157, 140, 'y', color, 2);
    draw_char(172, 140, 'e', color, 2);
    draw_char(187, 140, 'r', color, 2);

    draw_char(260, 140, '2', color, 2);
    draw_char(280, 140, 'p', color, 2);
    draw_char(295, 140, 'l', color, 2);
    draw_char(302, 140, 'a', color, 2);
    draw_char(317, 140, 'y', color, 2);
    draw_char(332, 140, 'e', color, 2);
    draw_char(347, 140, 'r', color, 2);
    draw_char(357, 140, 's', color, 2);

    draw_pixel_big(146, 181, color, 11);
    draw_pixel_big(316, 181, color, 11);
    // draw difficulty levels
    draw_char(40, 200, 'e', hsv2rgb_lcd(123, 88, 250), 2);
    draw_char(55, 200, 'a', hsv2rgb_lcd(123, 88, 250), 2);
    draw_char(70, 200, 's', hsv2rgb_lcd(123, 88, 250), 2);
    draw_char(85, 200, 'y', hsv2rgb_lcd(123, 88, 250), 2);

    draw_char(133, 200, 'm', hsv2rgb_lcd(123, 88, 182), 2);
    draw_char(155, 200, 'e', hsv2rgb_lcd(123, 88, 182), 2);
    draw_char(170, 200, 'd', hsv2rgb_lcd(123, 88, 182), 2);
    draw_char(185, 200, 'i', hsv2rgb_lcd(123, 88, 182), 2);
    draw_char(193, 200, 'u', hsv2rgb_lcd(123, 88, 182), 2);
    draw_char(208, 200, 'm', hsv2rgb_lcd(123, 88, 182), 2);

    draw_char(265, 200, 'h', hsv2rgb_lcd(34, 250, 200), 2);
    draw_char(280, 200, 'a', hsv2rgb_lcd(34, 250, 200), 2);
    draw_char(295, 200, 'r', hsv2rgb_lcd(34, 250, 200), 2);
    draw_char(305, 200, 'd', hsv2rgb_lcd(34, 250, 200), 2);

    draw_char(370, 200, 'd', hsv2rgb_lcd(0, 225, 225), 2);
    draw_char(385, 200, 'e', hsv2rgb_lcd(0, 225, 225), 2);
    draw_char(400, 200, 'a', hsv2rgb_lcd(0, 225, 225), 2);
    draw_char(415, 200, 't', hsv2rgb_lcd(0, 225, 225), 2);
    draw_char(422, 200, 'h', hsv2rgb_lcd(0, 225, 225), 2);

    draw_pixel_big(64, 239, color, 11);
    draw_pixel_big(174, 239, color, 11);
    draw_pixel_big(284, 239, color, 11);
    draw_pixel_big(394, 239, color, 11);
    // draw quit button
    draw_char(180, 260, 'Q', hsv2rgb_lcd(123, 88, 182), 4);
    draw_char(220, 260, 'u', hsv2rgb_lcd(123, 88, 182), 4);
    draw_char(253, 260, 'i', hsv2rgb_lcd(123, 88, 182), 4);
    draw_char(273, 260, 't', hsv2rgb_lcd(123, 88, 182), 4);

}

