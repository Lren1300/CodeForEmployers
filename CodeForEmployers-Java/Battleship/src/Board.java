
public class Board {
    char[][] upper;
    char[][] lower;
    int battleship;
    int carrier;
    int cruiser;
    int submarine;
    int destroyer;

    public static final int ASCII_CONV_MAX = 75;
    public static final int ASCII_CONV_MIN = 64;
    public static final int ASCII_CONV_RATE = 65;
    public static final int LAST_COL = 9;

    public Board() {
        upper = new char[10][10];
        for(int r=0;r<10;r++) {
            for(int c=0;c<10;c++) {
                upper[r][c] = '.';
            }
        }
        lower = new char[10][10];
        for(int r=0;r<10;r++) {
            for(int c=0;c<10;c++) {
                lower[r][c] = '.';
            }
        }
        carrier = 5;
        battleship = 4;
        cruiser = 3;
        submarine = 3;
        destroyer = 2;
    }
    // improvised helper methods
    private char [] letterConv() {
        int intConvert = 0;
        int numConv;
        char charConvert = 'A';
        char[] rowConv = new char[11];

        for(int row=ASCII_CONV_MAX;row>ASCII_CONV_MIN;row--) {
            numConv = row-ASCII_CONV_RATE;
            intConvert = row;
            charConvert = (char)intConvert;
            rowConv[numConv] = charConvert;
        }
        return rowConv;
    }

    private String toStringLogic(char[][] board) {
        char[] rowConv;
        String s = "";
        s += " 0123456789\n";

        rowConv = letterConv();
        for(int printRow=0; printRow<10;printRow++) {
            s += rowConv[printRow];
            for(int print=0;print<LAST_COL;print++) {
                s += board[printRow][print];
            }
            s += board[printRow][LAST_COL];
            s += "\n";
        }
        return s;
    }

    private boolean canShipFitLogic(int length, char shipLetter, char orientation, int row, int col) {
        if ((length + col) > 10) {
            return false;
        }
        else if(lower[row][col] == '.') {
            for(int buildTemp=0;buildTemp<length;buildTemp++) {
                if(row >= 10 || col >= 10) {
                	return false;
                }
            else if(lower[row][col] != '.') {
                    return false;
                }
                else {
                    if(orientation == 'H') {
                        col++;
                    }
                    else {
                        row++;
                    }
                }
            }
            return true;
        }
        else if(lower[row][col] != shipLetter) {
            return false;
        }
        else {
            return true;
        }
    }

    private void buildShip(int length, char shipLetter, char orientation, int row, int col) {
        if(orientation == 'H') {
            for(int build=0;build<length;build++) {
                lower[row][col] = shipLetter;
                col++;
            }
        }
        else if (orientation == 'V') {
            for(int build=0;build<length;build++) {
                lower[row][col] = shipLetter;
                row++;
            }
        }
    }
    //end of added helper methods
    public String toString(){
        String s = "";
        s += toStringLogic(upper);
        return s;
    }

    public String lowerToString(){
        String s = "";
        s += toStringLogic(lower);
        return s;
    }

    public boolean shootAt(int row, int col) {
        if(lower[row][col] == '.') {
            lower[row][col] = 'o';
            return false;
        }
        else if(lower[row][col] == 'B'){
            battleship--;
            lower[row][col] = '#';
            if(battleship == 0) {
                System.out.println("You have sunk the battleship!");
            }
            return true;
        }
        else if(lower[row][col] == 'A'){
            carrier--;
            lower[row][col] = '#';
            if(carrier == 0) {
                System.out.println("You have sunk the Aircraft Carrier!");
            }
            return true;
        }
        else if(lower[row][col] == 'S'){
            submarine--;
            lower[row][col] = '#';
            if(submarine == 0) {
                System.out.println("You have sunk the Submarine!");
            }
            return true;
        }
        else if(lower[row][col] == 'C'){
            cruiser--;
            lower[row][col] = '#';
            if(cruiser == 0) {
                System.out.println("You have sunk the Cruiser!");
            }
            return true;
        }
        else if(lower[row][col] == 'D'){
            destroyer--;
            lower[row][col] = '#';
            if(destroyer == 0) {
                System.out.println("You have sunk the Destroyer!");
            }
            return true;
        }
        else {
            lower[row][col] = 'o';
            return false;
        }
    }

    public void recordHit(int row, int col) {
        upper[row][col] = '#';
    }

    public void recordMiss(int row, int col) {
        upper[row][col] = 'o';
    }

    public void recordHitComp(int row, int col) {
        lower[row][col] = '#';
    }

    public void recordMissComp(int row, int col) {
        lower[row][col] = 'o';
    }

    boolean hasNoShips() {
        if (battleship == 0 && carrier == 0 && submarine == 0 && cruiser == 0 && destroyer == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean canShipFit(int length, char shipLetter, char orientation, int row, int col) {
        if(orientation == 'H' && row < 10 && col < 10 && row >= 0 && col >= 0) {
            return canShipFitLogic(length, shipLetter, orientation, row, col);
        }
        else if(orientation == 'V' && row < 10 && col < 10 && row >= 0 && col >= 0){
            return canShipFitLogic(length, shipLetter, orientation, row, col);
        }
        else {
            return false;
        }
    }

    public void placeShip(int length, char shipLetter, char orientation, int row, int col) {
        if(orientation == 'H'){
            buildShip(length, shipLetter, orientation, row, col);
        }
        else if(orientation == 'V') {
            buildShip(length, shipLetter, orientation, row, col);
        }

        else {
            System.out.println("Error: Select H or V for orientation.");
        }
    }
}
