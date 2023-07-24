/* 
   Logan Reneau and Allison Fitzgerald

   Place your Connect4's main() function here.
   Anything that you want unit tested must go in a separate file.
*/
# include <stdio.h>
# include "connect4.h"

int main(int argc, char* argv[]) { //argv[1] is row argv[2] is column Sscanf(ARGV[1])
    //variables for main 
    row = 6;
    col = 7;
    numInARow = 4;
    placedRow = 0;
    placedCol = 0;
    

    if(argc > 1){ 
        // zk Check that this returns 2 so you know the value was formatted correctly.
        sscanf(argv[1], "%dx%d", &row, &col);
    }
    if(argc > 2){
        sscanf(argv[2], "%d", &numInARow);
    }
    
    int turns = 1; 
    int playerHasWon = 0; 
    int playerHasQuit = 0;
    int isPlayer2 = 0; 
    int chosenColumn = 0; 
    char Board[row][col]; 
    
    initialize_board(Board); //Initialize board 
    
    while(turns < row*col){ //game loop

        // zk The if and else blocks are nearly identical.  
        // Please refactor and just use isPlayer2 as a parameter.
        if(isPlayer2 == 1){ 
            //print board
            printf("Turn %d, %dx%d, %d to win\n", turns, row, col, numInARow);
            print_board(Board);
            //Ask user what column they want to select
            chosenColumn = prompt_user(2);
            //if the input is q, quit
            if(chosenColumn == -1){
                playerHasQuit = 1;
                break;
            }

            printf("\n");
            //take the chosen column and place the piece, if it doesnt work, print error 
            if(place_turn(isPlayer2, chosenColumn, Board) == 0){
                error_message();
                continue;
            }
            else{
                //check to see if win condition is met
                if(check_win(Board, 2) == 1){
                    playerHasWon = 1;
                    break;
                }
                isPlayer2 = 0;
                turns++;
            }
        }
        else{
            printf("Turn %d, %dx%d, %d to win\n", turns, row, col, numInARow);
            print_board(Board);
            chosenColumn = prompt_user(1);
            if(chosenColumn == -1){
                playerHasQuit = 1;
                break;
            }
            printf("\n");
            if(place_turn(isPlayer2, chosenColumn, Board) == 0){
                error_message();
                continue;
            }
            else{
                if(check_win(Board, 1) == 1){
                    playerHasWon = 1;
                    break;
                }
            isPlayer2 = 1;
            turns++;
            }
        }
    }
    if(playerHasQuit == 1){
        printf("Goodbye.");
    }
    else{
        if(playerHasWon == 1){
            print_board(Board);
            printf("Congratulations, Player %d. You win.", isPlayer2+1);
        }
        else{
            print_board(Board);
            printf("It's a Draw!");
        }
    }
}