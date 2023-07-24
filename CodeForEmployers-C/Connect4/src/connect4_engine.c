/* 
   Logan Reneau and Allison Fitzgerald

   Place all your Connect4 functions here --- except for main.
   main() needs to go in its own separate .c file.
*/
#include <stdio.h>
#include <ctype.h>
#include "connect4.h"

int row=0;
int col=0;
int placedRow=0;
int placedCol=0;
int numInARow=0;

/*int index_helper(int x){
    if(x == 5){
        return 0;
    }
    if(x == 4){
        return 1;
    }
    if(x == 3){
        return 2;
    }

    if(x == 2){
        return 3;
    }

    if(x == 1){
        return 4;
    }

    if(x == 0){
        return 5;
    }
}*/
    
//simple funtions that makes sure the column number is within bounds
int within_bounds(int chosen_column){
    // zk short-cut:  return chosen_column >=0 && chosen_column < col
    if(chosen_column >=0 && chosen_column < col){
        return 1;
    }
    else{
        return 0;
    }
}

//asks the current player for a column and returns it
int prompt_user(int player){
    char chosenColumn;
    int temp, inBounds;
    printf("It is player %d's turn, your next turn is: ", player);
    scanf(" %c", &chosenColumn);
    chosenColumn = tolower(chosenColumn);
    if(chosenColumn == 'q'){
        return -1;
    }
    // zk use 'a' instead of 97.  It makes your intention clearer
    temp = (int)chosenColumn-97;
    inBounds = within_bounds(temp);
    if(inBounds == 1){
        return temp;
    }
    else{
        return -99;
    }
}

//simple error message
void error_message(){
    printf("Error: Illegal Move. Please try again.\n");
    printf("\n");
}

//x in a row method impleneted
int x_in_a_row(int x, int length, int array[]){
    int count=0;
    for(int i=0; i<length; i++){
        if(array[i] == x){
            count++;
        }
        else{
            if(count<numInARow){
                count=0;
            }
        }
    }

    if(count >= numInARow){
        return 1;
    }
    return 0;
}

int check_win(char Board[row][col], int player){
    int check=0;
    int final=0;
    //horizontal case
    int horizontalArray[col]; 
    for(int i=0; i<col; i++){
        horizontalArray[i] = (int)Board[placedRow][i]-48;
    }

    check = x_in_a_row(player, col, horizontalArray);
    if(check == 1){
        final = 1;
    }


    //vertical case
    int verticalArray[row];
    for(int i=0; i<row; i++){
       verticalArray[i] = (int)Board[i][placedCol]-48;
    }

    check = x_in_a_row(player, row, verticalArray);
    if(check == 1){
        final = 1;
    }

    //diagonal variables
    int distanceToLeftBorder = placedCol;
    int tempRow; 
    //diagonal(bottom-left --> top right)
    tempRow = placedRow + distanceToLeftBorder;
    int positiveDiagArray[col];

    for(int i=0; i<col; i++){
        if(tempRow > row-1 || tempRow < 0){
            positiveDiagArray[i] = -99;
        }
        else{
            positiveDiagArray[i] = (int)Board[tempRow][i]-48;
        }
        tempRow--;
    }

    check = x_in_a_row(player, col, positiveDiagArray);
    if(check == 1){
        final = 1;
    }


    //diagonal (top-left --> bottom right)
    tempRow = placedRow - distanceToLeftBorder;
    int negativeDiagArray[col];

    for(int i=0; i<col; i++){
        if(tempRow > row-1 || tempRow < 0){
            negativeDiagArray[i] = -99;
        }
        else{
            negativeDiagArray[i] = (int)Board[tempRow][i]-48;
        }
        tempRow++;
    }

    check = x_in_a_row(player, col, negativeDiagArray);
    if(check == 1){
        final = 1;
    }

    //check if any of the arrays indicated a win
    if(final == 1){
        return 1;
    }
    return 0;
    
}
//place turn does what it says, it places the turn based on given column
int place_turn(int player, int chosenColumn, char Board[row][col]){
    int temp = player++;
    int maxRow = row-1;
    for(int i=maxRow; i>=0; i--){
        if(Board[i][chosenColumn] == '.'){
            Board[i][chosenColumn] = (char)temp+49;
            placedRow = i;
            placedCol = chosenColumn;
            return 1;
        }
        else{
            continue;
        }
    }
    return 0;
}
//function to initialize the board to be clear and labeled
void initialize_board(char Board[row][col]){
    for(int i=0; i<row; i++){
        for(int j=0; j<col; j++){
            Board[i][j] = '.';
        }
    }
}

//function that simply prints our 2D array
void print_board(char Board[row][col]){
    for(int numCols=0; numCols<col; numCols++){
        printf("%c ", 65+numCols);
    }
    printf("\n");
    for(int i=0; i<row; i++){
        for(int j=0; j<col; j++){
            printf("%c ", Board[i][j]);
        }
        printf("\n");
    }
}
    
