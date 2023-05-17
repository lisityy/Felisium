#define OK 0
#define CLASH 1
#define WALL_HIT 2
#define SELF_HIT 3
#define ENEMY_HIT 4
#define MUTUAL_HIT 5
#define WIN 6
#define MAX_LEN 360
#define WIDTH 480
#define HEIGHT 320

typedef struct{
    int x;
    int y;
} coordinates_t;

typedef struct{
    coordinates_t coords[MAX_LEN];
    int head;
    int tail;
} snake_t;

int choose_the_end_page(int ret, int action_owner, int score_p1, int score_p2, snake_t snake_p1, snake_t snake_p2);

void print_the_end_page(unsigned char *parlcd_mem_base, unsigned char *mem_base, int result, int action_owner, int score_p1, int score_p2);

void print_wall_hit_result(int action_owner, int score_p1, int score_p2);

void print_self_hit_result(int action_owner, int score_p1, int score_p2);

void print_enemy_hit_result(int action_owner, int score_p1, int score_p2);

void print_draw_result(int action_owner, int score_p1, int score_p2, int is_mutual_hit);

void print_win_result(int action_owner, int score_p1, int score_p2);

void draw_the_winner(int player_number, int y, unsigned short color, int scale, int letter_spacing);

void draw_score(int score, int y, unsigned short color, int scale, int letter_spacing);
