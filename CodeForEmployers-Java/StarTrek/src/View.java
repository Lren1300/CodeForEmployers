import java.util.*;

public class View {
    Scanner scnr = new Scanner(System.in);
    public static final int INDEX_LOWER_BOUND = -1;
    public static final int INDEX_UPPER_BOUND = 9;
    public static final int INDEX_MIN = 0;
    public static final int INDEX_MAX = 8;
    public static final int BOARD_MIN = 1;
    public static final int BOARD_MAX = 9;
    public static final int PRINT_ROWS = 17;
    public static final int MAX_HEALTH = 100;
    public static final int MIN_HEALTH_NO_DAMAGE = 75;
    public static final int MAX_DAMAGE_ONE = 74;
    public static final int MIN_DAMAGE_ONE = 50;
    public static final int MAX_DAMAGE_TWO = 49;
    public static final int MIN_HEALTH = 1;
    public static final int DESTROYED = 0;

    public View() {

    }

    public void displayOpening(int stardate, int endStardate, int klingons, int starbases){ // TODO: replace random # with constants
        System.out.printf("The stardate is %d.\n", stardate);
        System.out.println("");
        System.out.println("You are the captain of the USS Enterprise. You have received word from");
        System.out.println("Starfleet Command of Klingon activity in the region. The Federation is in");
        System.out.println("danger and you are the only ship in range.");
        System.out.println("");
        System.out.printf("Your mission is to hunt down and destroy the %d Klingon warships. You must\n ", klingons);
        System.out.printf("complete your mission before stardate %d, giving you %d stardates to\n", endStardate, (endStardate - stardate));
        System.out.println("succeed.");
        System.out.println("");
        System.out.printf("There are %d Federation Starbases in the region for refueling, restocking", starbases);
        System.out.println("torpedoes, and repairs.");
        System.out.println("");
        System.out.println("Good luck captain!");
        System.out.println("");
    }

    public void lrs(Galaxy gal, int currentRow, int currentCol) { 
        int yAxis = currentRow-1;
        int xAxisLength = 0;
        int yAxisLength = 0;
        int temp = 0;
        int count = 0;
        if(gal.enterprise.system[0] == 0) {
            System.out.println("You're LRS is completely destroyed."); 
        }
        else {
            System.out.println("KBS FORMAT: Klingons Starbases Stars = 000");
            System.out.print("   ");
            for (int xAxis=currentCol-1; xAxis<currentCol+2;xAxis++) {
                if(xAxis == INDEX_LOWER_BOUND || (xAxis+1) == INDEX_UPPER_BOUND) {
                    System.out.print("");
                }
                else{
                    System.out.print("  " + (xAxis+1) + "   ");
                    xAxisLength++;
                }
            }
            System.out.println("");
            for (int r=1;r<=7;r++) {
                if (r%2 == 1) {
                    if(yAxis == INDEX_LOWER_BOUND || yAxis == INDEX_UPPER_BOUND) {
                        System.out.print("");
                    }
                    else{
                        System.out.print("  ");
                        for (int row=INDEX_MIN; row<xAxisLength;row++) {
                            System.out.print("+-----");
                        }
                        System.out.println("+");
                    }
                }
                if (r%2 == 0) {
                    temp++;
                    if(yAxis == INDEX_LOWER_BOUND || (yAxis+1) == INDEX_UPPER_BOUND) {
                        System.out.print("");
                    }
                    else{
                        System.out.print((yAxis+1) + " ");
                        yAxisLength++;
                    }
                    for(int row2=currentCol-1;row2<currentCol+2;row2++) {
                        if(yAxisLength != temp ) {
                            System.out.print("");
                            temp--;
                        }
                        else {
                            if(row2 == -1 || (row2+1) == 9 || yAxis == -1 || (yAxis+1) == 9) {
                                System.out.print("");
                            }
                            else{
                                if(gal.galaxy[yAxis][row2] == gal.galaxy[gal.getQuadX()][gal.getQuadY()]) {
                                    if(gal.enterprise.system[1] <= MAX_HEALTH && gal.enterprise.system[1] >= MIN_HEALTH_NO_DAMAGE) {
                                        System.out.print("|<" + gal.galaxy[yAxis][row2].getKBS() + ">");
                                    }
                                    if(gal.enterprise.system[1] <= MAX_DAMAGE_ONE && gal.enterprise.system[1] >= MIN_DAMAGE_ONE) {
                                        System.out.print("|<" + gal.galaxy[row2][yAxis].getKBS() + ">");
                                    }
                                    if(gal.enterprise.system[1] <= MAX_DAMAGE_TWO && gal.enterprise.system[1] >= MIN_HEALTH) {
                                        System.out.print("|<" + (gal.galaxy[row2][yAxis].getKlingons()+1) + 
                                            (gal.galaxy[row2][yAxis].getStarbases()-1) +(gal.galaxy[row2][yAxis].getStars()+3) +  ">");
                                    }
                                    gal.galaxy[yAxis][row2].setIsScanned();
                                }
                                else {
                                    if(gal.enterprise.system[1] <= MAX_HEALTH && gal.enterprise.system[1] >= MIN_HEALTH_NO_DAMAGE) {
                                        System.out.print("| " + gal.galaxy[yAxis][row2].getKBS() + " ");
                                    }
                                    else if(gal.enterprise.system[1] <= MAX_DAMAGE_ONE && gal.enterprise.system[1] >= MIN_DAMAGE_ONE) {
                                        System.out.print("| " + gal.galaxy[row2][yAxis].getKBS() + " ");
                                    }
                                    else if(gal.enterprise.system[1] <= MAX_DAMAGE_TWO && gal.enterprise.system[1] >= MIN_HEALTH) {
                                        System.out.print("| " + (gal.galaxy[row2][yAxis].getKlingons()+1) + 
                                            (gal.galaxy[row2][yAxis].getStarbases()-1) +(gal.galaxy[row2][yAxis].getStars()+3) +  " ");
                                    }
                                    gal.galaxy[yAxis][row2].setIsScanned();
                                }
                                count = row2;
                            }
                        }
                    }
                    if(count == INDEX_LOWER_BOUND || (count+1) == INDEX_UPPER_BOUND 
                    || yAxis == INDEX_LOWER_BOUND || (yAxis+1) == INDEX_UPPER_BOUND) {
                        System.out.print("");
                    }
                    else {
                        System.out.println("|");
                    }
                    yAxis++;
                }
            }
        }
    }

    public void ping(Galaxy galaxy, int sx, int sy, int shield, int health) {
        System.out.printf("Enemy in sector (%d, %d)\n", (sx+1), (sy+1));
        System.out.printf("Shield level : %d\n", shield);
        System.out.printf("Phaser energy to destroy : %d\n", health);
        System.out.println("");
    }

    public void srs(int stardates, int qx, int qy, int sx, int sy, int torpedos,
    int shields, int energy, int klingons, int starbases, Quadrant q, Galaxy g) {
        if(g.enterprise.system[2] == DESTROYED) {
            System.out.println("Your SRS is completely destroyed.");
        }
        else {
            System.out.print("  ");
            for(int x=BOARD_MIN;x<BOARD_MAX;x++) {
                System.out.printf("-%d-",x);
            }
            System.out.println("");
            for(int row=BOARD_MIN;row<BOARD_MAX;row++) {
                System.out.printf("%d|", row);
                System.out.print(printMap(row-1, q, g));
                System.out.printf("|%d  ", row);
                rightSide(row, stardates, qx, qy, sx, sy, torpedos, shields,
                    energy, klingons, starbases);
            }
            System.out.print("  ");
            for(int x=BOARD_MIN;x<BOARD_MAX;x++) {
                System.out.printf("-%d-",x);
            }
            System.out.println("");
            if(q.hasKlingon()) {
                displayMessage(0);
            }
            System.out.print("YOUR ORDERS, CAPTAIN: ");
        }
    }

    int rowCalc(int r) {
        int r2;
        r2 = ((r/2)-1);
        return r2;
    }

    public void map(Galaxy g) {
        boolean isScanned;
        System.out.print("   ");
        for (int xAxis=BOARD_MIN; xAxis<BOARD_MAX;xAxis++) {
            System.out.printf("  %d   ", xAxis);
        }
        System.out.println("");
        for (int r=BOARD_MIN;r<=PRINT_ROWS;r++) {
            if (r%2 == 1) {
                System.out.print("  ");
                for (int row=BOARD_MIN; row<BOARD_MAX;row++) {
                    System.out.print("+-----");
                }
                System.out.println("+");
            }

            if (r%2 == 0) {
                int r2 = rowCalc(r);
                System.out.printf("%d ", r/2);
                for (int row2=INDEX_MIN; row2<INDEX_MAX; row2++) {
                    isScanned = g.galaxy[r2][row2].isScanned();
                    if(g.galaxy[r2][row2] == g.currentQuadrant()) {
                        System.out.print("|<" + g.galaxy[r2][row2].getKBS() + ">");
                    }
                    else{
                        if(isScanned) {
                            System.out.print("| " + g.galaxy[r2][row2].getKBS() + " ");
                        }
                        else {
                            System.out.print("|     ");
                        }
                    }
                }
                System.out.println("|");
            }
        }
        System.out.println("");
    }

    public void displayMessage(int messageNum){
        if (messageNum == 0) {
            System.out.println("!!! CODE RED !!!");
        }
        if (messageNum == 1) {
            System.out.println("Torpedo has been aimlessly launched into space!");
        }
        if (messageNum == 2) {
            System.out.println("Klingon has been obliterated!");
        }
        if ( messageNum == 3) {
            System.out.println("You shot the phaser into oblivion!");
        }
        if (messageNum == 4) {
            System.out.println("There are no Klingons in this sector.");
        }
        if (messageNum == 5) {
            System.out.println("Your enterprise has been swallowed by a star.");
        }
        if (messageNum == 6) {
            System.out.println("The enterprise came a little too close while docking, the starbase and enterprise have exploded.");
        }
        if (messageNum == 7) {
            System.out.println("That is one way to take out a Klingon...");
        }
        if (messageNum == 8) {
            System.out.println("Are you serious?!?!");
        }
        if (messageNum == 9) {
            System.out.println("That is a star... what a waste.");
        }
        if (messageNum == 10) {
            System.out.print("Give energy ammount: ");
        }
        if (messageNum == 11) {
            System.out.print("Give X coordinate: ");
        }
        if (messageNum == 12) {
            System.out.print("Give Y coordinate: ");
        }
        if (messageNum == 13) {
            System.out.println("Photon tubes are destroyed. Cannot shoot torpedo.");
        }
        if (messageNum == 14) {
            System.out.println("Photon tubes are damaged. Shot mounfunctioned... Try again.");
        }
        if(messageNum == 15) {
            System.out.println("Phaser cannons are destroyed.");
        }
        if(messageNum == 16) {
            System.out.println("Phaser cannons are damaged. Shot mounfunctioned. Energy Lost. Try again.");
        }
        if(messageNum == -1) {
            System.out.print("Game over!");
        }
    }

    public void rightSide(int row, int stardates, int qx, int qy, int sx, int sy, int torpedos,
    int shields, int energy, int klingons, int starbases) {
        if(row == 1) {
            System.out.printf("STARDATES REMAINING  %d       Command List: SHI, DAM\n", stardates);
        }
        if(row == 2) {
            System.out.printf("QUADRANT (X,Y)       (%d, %d)                 IMP, WRP\n", (qy+1), (qx+1));
        }
        if(row == 3) {
            System.out.printf("SECTOR (X,Y)         (%d, %d)                 TOR, PHA\n", (sy+1), (sx+1));
        }
        if(row == 4) {
            System.out.printf("PHOTON TORPEDOS      %d                     LRS, PNG\n", torpedos);
        }
        if(row == 5) {
            System.out.printf("SHIELDS              %d                    MAP, DES\n", shields);
        }
        if(row == 6) {
            System.out.printf("ENERGY               %d\n", energy);
        }
        if(row == 7) {
            System.out.printf("KLINGONS REMAINING   %d\n", klingons);
        }
        if(row == 8) {
            System.out.printf("STARBASES REMAINING  %d\n", starbases);
        }
    }

    public String getCommand() {
        String command;
        command = scnr.next();
        command = command.toUpperCase();
        return command;
    }

    void remainingHealth(int shield, int hull, int total) {
        System.out.printf("The klingon still has %d health. Shields: %d, Hull: %d", total, shield, hull);
    }

    public String printMap(int row, Quadrant q, Galaxy g) {
        String s = "";
        int hurt = 0;
        if(g.enterprise.system[2] <= MAX_DAMAGE_ONE && g.enterprise.system[2] >= MIN_DAMAGE_ONE) {
            hurt++;
        }
        if(g.enterprise.system[2] <= MAX_DAMAGE_TWO && g.enterprise.system[2] >= MIN_HEALTH) {
            hurt += 2;
        }
        for(int c=0;c<8;c++) {
            if(hurt == 2) {
                s += "   ";
            }
            else if(hurt == 1) {
                if(q.sector[row][c] == null) {
                    s += "   ";
                }
                else if(q.sector[row][c] instanceof Enterprise) {
                    s += "<E>";
                }
                else {
                    s += "   ";
                }
            }
            else {
                if(q.sector[row][c] == null) {
                    s += "   ";
                }
                else if(q.sector[row][c] instanceof Enterprise) {
                    s += "<E>";
                }
                else if( q.sector[row][c] instanceof Klingon) {
                    s += "=K=";
                }
                else if(q.sector[row][c] instanceof Star) {
                    s += " * ";
                }
                else if(q.sector[row][c] instanceof Starbase) {
                    s += "<!>";
                }
            }
        }
        hurt = 0;
        return s;
    }

    void damage(int warpEngines, int lrs, int srs, int pc, int pt, int sc, int maxSC) {
        System.out.println("");
        System.out.println("DAMAGE REPORT: ");
        System.out.printf("Warp Engines: %d%\n", warpEngines);
        System.out.println("   Max speed 1 quadrant per stardate.");
        System.out.printf("Long Range Sensors: %d%\n", lrs);
        System.out.printf("Short Range Sensors: %d%\n", srs);
        System.out.printf("Phaser Control: &d%\n", pc);
        System.out.printf("Photon Tubes: %d%\n", pt);
        System.out.printf("Sheild Control: %d%\n", sc);
        System.out.printf("   Max shield level: %d\n", maxSC);
        System.out.println("NOTES:");
        System.out.println("   Repair crew can repair 1-5% damage per stardate");
    }
}

