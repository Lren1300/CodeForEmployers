import java.util.Scanner;
import java.util.Random;

public class Battleship {
    Scanner scnr = new Scanner(System.in);
    Board player;
    Board computer;
    String orientation, row;
    boolean boolGameOver;
    int postConvRow, col;
    char orientationConv;

    public static final String[] SHIP_NAMES = { "aircraft carrier", "battleship", "submarine", "cruiser", "destroyer" };
    public static final char[] SHIP_CHARS = { 'A', 'B', 'S', 'C', 'D'};
    public static final int[] SHIP_LENGTHS = { 5, 4, 3, 3, 2};
    public static final int ASCII_CONV_RATE = 65;

    public static void main(String[] args) {
        Battleship newGame = new Battleship();
        newGame.setup();
        newGame.play();
    }
    //improvised helper methods
    private int firstCoordinateConv(String s) {
        char conv = s.charAt(0);
        int numConv;
        numConv = (int)conv;
        numConv -= ASCII_CONV_RATE;
        return numConv;
    }

    private int shipLength (char length) {
        if(length == 'A') {
            return 5;
        }
        else if(length == 'B') {
            return 4;
        }
        else if(length == 'S') {
            return 3;
        }
        else if(length == 'C') {
            return 3;
        }
        else if(length == 'D') {
            return 2;
        }
        else {
            return 0;
        }
    }

    private void placeSingleShip(String name, char letter, int length) {
        System.out.println(player.lowerToString());
        System.out.println("Place your " + name + "...");
        getOrientation();
        getCoordinates();
    }

    private void errorMessage() {
        System.out.println("Error: Make sure ship is placed in bounds ");
        System.out.println(" and the ship is not on top of another.");
        System.out.println("");
    }

    private char getOrientation() {
        System.out.print("[H]orizontal or [V]ertical? ");
        orientation = scnr.next();
        orientation = orientation.toUpperCase();
        orientationConv = orientation.charAt(0);

        if(orientationConv != 'H' && orientationConv != 'V') {
            System.out.println("Error: Needs to be H or V");
            getOrientation();
        }
        return orientationConv;
    }

    private void getCoordinates() {
        System.out.print("Coordinates : ");
        row = scnr.next();
        row = row.toUpperCase();
        postConvRow = firstCoordinateConv(row);
        col = scnr.nextInt();
        scnr.nextLine();
        System.out.println("");
    }

    // end of added helper methods
    void setup() {
        player = new Board();
        computer = new Board();
        placePlayerShips();
        placeComputerShips();

    }

    boolean gameOver() {
        if(player.hasNoShips() || computer.hasNoShips()) {
            boolGameOver = true;
            System.out.println(player.lowerToString());
            System.out.println(computer.lowerToString());

            if(player.hasNoShips()) {
                System.out.println("The computer has won!");
            }
            else {
                System.out.println("The player has won!");
            }
            return boolGameOver;
        }
        else if(boolGameOver == true) {
        	return boolGameOver;
        }
        else{
            boolGameOver = false;
            return boolGameOver;
        }
    }

    void play() {
        while( ! boolGameOver) {
            playerTurn();
            computerTurn();
            gameOver();
        }
        System.out.print("Game Over!");
    }

    Board playerTurn() {
        String firstInput;
        int secondInput, postConvFirst;

        System.out.println(player.toString());
        System.out.println(player.lowerToString());
        System.out.print("Enter coordintes: ");
        firstInput = scnr.next();
        firstInput = firstInput.toUpperCase();
        if(firstInput.equals("QUIT")){
            boolGameOver = true;
        }
        else{
            postConvFirst = firstCoordinateConv(firstInput);
            secondInput = scnr.nextInt();

            if(computer.shootAt(postConvFirst, secondInput)) {
                System.out.println(firstInput + secondInput + " was a hit!");
                player.recordHit(postConvFirst, secondInput);
                computer.recordHitComp(postConvFirst, secondInput);
            }
            else {
                System.out.println(firstInput + secondInput + " was a miss!");
                player.recordMiss(postConvFirst, secondInput);
                computer.recordMissComp(postConvFirst, secondInput);
            }

        }
        return player;
    }

    void computerTurn() {
        int firstInput, secondInput;
        Random computerShot = new Random();

        firstInput = computerShot.nextInt(10);
        secondInput = computerShot.nextInt(10);

        if(player.shootAt(firstInput, secondInput)) {
            System.out.println("The computer hit!");
            System.out.println("");
            computer.recordHit(firstInput, secondInput);
            player.recordHitComp(firstInput, secondInput);
        }
        else {
            System.out.println("The computer missed!");
            System.out.println("");
            computer.recordMiss(firstInput, secondInput);
            player.recordMissComp(firstInput, secondInput);
        }
    }

    void placePlayerShips() {
        for(int shipNumber = 0; shipNumber < 5; shipNumber++) {
            orientationConv = 'A';
            postConvRow = 0;
            col = 0;

            while(! player.canShipFit(shipLength(SHIP_CHARS[shipNumber]), SHIP_CHARS[shipNumber], orientationConv, postConvRow, col)) {
                placeSingleShip(SHIP_NAMES[shipNumber], SHIP_CHARS[shipNumber], SHIP_LENGTHS[shipNumber]);

                if( player.canShipFit(shipLength(SHIP_CHARS[shipNumber]), SHIP_CHARS[shipNumber], orientationConv, postConvRow, col)) {
                    player.placeShip(shipLength(SHIP_CHARS[shipNumber]), SHIP_CHARS[shipNumber], orientationConv, postConvRow, col);
                }
                else {
                    errorMessage();
                }
            }

        }
    }

    void placeComputerShips() {
        Random computerShipGen = new Random();
        int randomRow = 0;
        int randomCol = 0;
        int randomOrientation = 0;
        char randomOrientationChar = 'A';

        for(int shipNumber = 0; shipNumber < 5; shipNumber++) {
            while(! computer.canShipFit(shipLength(SHIP_CHARS[shipNumber]), SHIP_CHARS[shipNumber], randomOrientationChar, randomRow, randomCol)) {
                randomRow = computerShipGen.nextInt(10);
                randomCol = computerShipGen.nextInt(10);
                randomOrientation = computerShipGen.nextInt(2);

                if(randomOrientation == 1) {
                    randomOrientationChar = 'H';
                }
                else {
                    randomOrientationChar = 'V';
                }

                if( computer.canShipFit(shipLength(SHIP_CHARS[shipNumber]), SHIP_CHARS[shipNumber], randomOrientationChar, randomRow, randomCol)) {
                    computer.placeShip(shipLength(SHIP_CHARS[shipNumber]), SHIP_CHARS[shipNumber], randomOrientationChar, randomRow, randomCol);
                }
            }

        }

    }
}