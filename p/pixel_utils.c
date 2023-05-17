#include "font_types.h"
#include <string.h>
#include <stdio.h>
#include "pixel_utils.h"

#define WIDTH 480
#define HEIGHT 320

extern unsigned short *fb;
extern font_descriptor_t *fdes;

void draw_text_centered(const char* text, int y, unsigned short color, int scale, int letter_spacing) {
    int text_length = strlen(text);
    int total_width = 0;

    // Calculate the total width of the text with letter spacing
    for (int i = 0; i < text_length; i++) {
        total_width += (char_width(text[i]) + letter_spacing) * scale;
    }
    
    // Calculate the starting x-coordinate for centering the text
    int x = (480 - total_width) / 2;

    // Draw each letter with spacing
    for (int i = 0; i < text_length; i++) {
        draw_char(x, y, text[i], color, scale);
        x += (char_width(text[i]) + letter_spacing) * scale;
    }
}


void draw_char(int x, int y, char ch, unsigned int color, int scale){
    int w = char_width(ch);
    const font_bits_t *idx;
    if((ch >= fdes->firstchar) && (ch - fdes->firstchar < fdes->size)){
        if(fdes->offset){
            idx = &fdes->bits[fdes->offset[ch - fdes->firstchar]];
        } else {
            int bw = (fdes->maxwidth + 15)/16;
            idx = &fdes->bits[(ch - fdes->firstchar) * bw * fdes->height];
        }
        int i, j;
        for (i = 0; i < fdes->height;i++){
            font_bits_t val = *idx;
            for(j = 0;j < w;j++){
                if ((val&0x8000)!=0){
                    draw_pixel_big(x + scale * j, y + scale * i, color, scale);
                }
                val<<=1;
            }
            idx++;
        }
    }
}

void draw_pixel_big(int x, int y, unsigned int color, int scale){
    int i, j;
    for(i = 0; i<scale;i++) {
        for(j = 0; j<scale;j++) {
            draw_pixel(x + i, y + j, color);
        }
    }
}

void draw_pixel(int x, int y, unsigned int color) {
    if(x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
        fb[x + WIDTH * y] = color;
    }
}

int char_width(int ch){
    int width;
    if(!fdes->width){
        width = fdes->maxwidth;
    } else {
        width = fdes->width[ch - fdes->firstchar];
    }
    return width;
}

unsigned int hsv2rgb_lcd(int hue, int saturation, int value){
    hue = hue % 360;    
    float f = (hue % 60)/60.0;
    int p = (value * (255 - saturation))/255;
    int q = (value * (255-(saturation*f)))/255;
    int t = (value * (255-(saturation*(1.0 - f))))/255;
    unsigned int r,g,b;

    if(hue < 60){
        r = value; 
        g = t; 
        b = p;
    } else if(hue < 120) {
        r = q; 
        g = value; 
        b = p;
    } else if(hue < 180) {
        r = p; 
        g = value;
        b = t;
    } else if(hue < 240) {
        r = p; 
        g = q; 
        b = value;
    } else if(hue < 300) {
        r = t; 
        g = p; 
        b = value;
    }else{
        r = value; 
        g = p; 
        b = q;
    }

    r>>=3;
    g>>=2;
    b>>=3;
    return (((r & 0x1f) << 11) | ((g & 0x3f) << 5) | (b & 0x1f));
}