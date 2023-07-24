#ifndef CONNECT4_H
#define CONNECT4_H

// Declare your helper functions here.

extern int row;
extern int col;
extern int numInARow;
extern int placedRow;
extern int placedCol;

int x_in_a_row(int x, int length, int array[]);
int within_bounds(int chosen_column);
int prompt_user(int player);
void error_message();
void initialize_board(char Board[row][col]);
void print_board(char Board[row][col]);
int place_turn(int player, int chosenColumn, char Board[row][col]);
int check_win(char Board[row][col], int player);

#endif