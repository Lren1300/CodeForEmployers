
import java.util.Random;
import java.lang.Math;

public class StarTrek {
    int starDate, endStarDate, currentStarDate;
    boolean gameOver, docked, destroy;
    View view;
    Galaxy galaxy;
    Random rand = new Random();

    public StarTrek() {
        view = new View();
        galaxy = new Galaxy();
        starDate = rand.nextInt(100) + 3000;
        endStarDate = starDate + 30;
        currentStarDate = endStarDate - starDate;
        gameOver = false;
        docked = false;
        destroy = false;
    }

    public static void main(String[] args) {
        StarTrek st = new StarTrek();
        st.view.displayOpening(st.getStarDate(), st.getEndStarDate(), st.galaxy.getKlingons(), st.galaxy.getStarbases());
        st.gameLoop();

    }

    Galaxy getGalaxy() {
        return galaxy;
    }

    int getStarDate() {
        return starDate;
    }

    int getEndStarDate() {
        return endStarDate;
    }

    public void gameLoop() {
        while(! isGameOver(gameOver) ) {
            if(isGameOver(gameOver)) {
                break;
            }
            else {
                srs();
                executeCommand(view.getCommand());
                if(! gameOver) {
                    enemyTurn();
                    galaxy.enterprise.repair();
                }
            }
        }
        view.displayMessage(-1); 
    }

    public boolean isGameOver(boolean gameOver) {
        if (gameOver || currentStarDate == 0 || galaxy.enterprise.system[0] == 0 || galaxy.enterprise.energy == 0) {
            return gameOver;
        }
        else {
            gameOver = false;
            return gameOver;
        }
    }

    boolean destruct() {
        boolean gameOver = true;
        return gameOver;
    }

    void enemyTurn() {
        if(galaxy.currentQuadrant().getKlingons() > 0) {
            galaxy.enterprise.takeDamage((200 + rand.nextInt(100)) * galaxy.currentQuadrant().getKlingons());
        }
    }

    void executeCommand(String command){
        if(command.equals("LRS")) {
            lrs();
        }
        if(command.equals("SRS")) {
            srs();
        }
        if(command.equals("MAP")) {
            map();
        }
        if(command.equals("PNG")) {
            ping();
        }
        if(command.equals("SHI")) {
            String eString = "";
            int e = 0;

            view.displayMessage(10);
            eString = view.getCommand();
            e = Integer.parseInt(eString);

            shields(e);
        }
        if(command.equals("IMP")) {
            String xString = "";
            String yString = "";
            int x = 0;
            int y = 0;

            view.displayMessage(11);
            xString = view.getCommand();
            x = Integer.parseInt(xString);

            view.displayMessage(12);
            yString = view.getCommand();
            y = Integer.parseInt(yString);
            System.out.println("");

            impulse(y, x);

        }
        if(command.equals("WRP")) {
            String xString = "";
            String yString = "";
            int x = 0;
            int y = 0;

            view.displayMessage(11);
            xString = view.getCommand();
            x = Integer.parseInt(xString);
            view.displayMessage(12);
            yString = view.getCommand();
            y = Integer.parseInt(yString);

            warp(y, x);
        }
        if(command.equals("PHA")) {
            String eString = "";
            String xString = "";
            String yString = "";
            int e = 0;
            int x = 0;
            int y = 0;

            view.displayMessage(10);
            eString = view.getCommand();
            e = Integer.parseInt(eString);

            view.displayMessage(11);
            xString = view.getCommand();
            x = Integer.parseInt(xString);

            view.displayMessage(12);
            yString = view.getCommand();
            y = Integer.parseInt(yString);

            phase(e, y, x);
        }
        if(command.equals("TOR")) {
            String xString = "";
            String yString = "";
            int x = 0;
            int y = 0;

            view.displayMessage(11);
            xString = view.getCommand();
            x = Integer.parseInt(xString);

            view.displayMessage(12);
            yString = view.getCommand();
            y = Integer.parseInt(yString);

            torpedo(y, x);
        }
        if(command.equals("DAM")) {
            damage();
        }
        if(command.equals("DES")) {
            isGameOver(destruct());
            currentStarDate = 0;
        }
    }

    void srs() {
        if(galaxy.enterprise.isDestroyed(destroy) || currentStarDate == 0) {
            gameOver = true;
        }
        else {
            view.srs(
                currentStarDate,
                galaxy.getQuadX(),
                galaxy.getQuadY(),
                galaxy.currentQuadrant().getSectorX(),
                galaxy.currentQuadrant().getSectorY(),
                galaxy.enterprise.getTorpedos(),
                galaxy.enterprise.getShields(),
                galaxy.enterprise.getEnergy(),
                galaxy.getKlingons(),
                galaxy.getStarbases(),
                galaxy.currentQuadrant(),
                galaxy);
        }

    }

    void lrs() {
        view.lrs(galaxy, galaxy.getQuadX(), galaxy.getQuadY());
    }

    void map() {
        view.map(galaxy);
    }

    void ping() {
        if(galaxy.currentQuadrant().getKlingons() > 0) {
            int r2, c2, hull, shields;

            for(int r=View.INDEX_MIN;r<View.INDEX_MAX;r++) {
                for(int c=View.INDEX_MIN;c<View.INDEX_MAX;c++) {
                    if(galaxy.currentQuadrant().sector[r][c] != null && galaxy.currentQuadrant().sector[r][c] instanceof Klingon) {
                        Klingon k = (Klingon) galaxy.currentQuadrant().sector[r][c];
                        r2 = r;
                        c2 = c;
                        shields = k.getShields();
                        hull = k.getHull();
                        view.ping(
                            galaxy,
                            r2,
                            c2,
                            shields,
                            (shields + hull)
                        );
                    }
                }
            }
        }
        else {
            view.displayMessage(4);
        }

    }

    void shields(int energy) {
        if(galaxy.enterprise.system[5] == View.DESTROYED) {
            energy -= energy;
        }
        else if(galaxy.enterprise.system[5] >= View.MIN_HEALTH && galaxy.enterprise.system[3] <= View.MAX_DAMAGE_TWO) {
            energy /= 3;
        }
        else if(galaxy.enterprise.system[5] >= View.MIN_DAMAGE_ONE && galaxy.enterprise.system[3] <= View.MAX_DAMAGE_ONE) {
            energy /= 2;
        }
        galaxy.enterprise.energyToShield(energy);
    }

    void phase(int energy, int sx, int sy) {
        if(galaxy.enterprise.system[3] == View.DESTROYED) {
            view.displayMessage(15);
        }
        else if(galaxy.enterprise.system[3] >= View.MIN_HEALTH && galaxy.enterprise.system[3] <= View.MAX_DAMAGE_TWO) {
            int chance = rand.nextInt(2);
            if(chance == 0) {
                view.displayMessage(16);
                galaxy.enterprise.decEnergy((energy / 2));
            }
        }
        else if(galaxy.enterprise.system[3] >= View.MIN_DAMAGE_ONE && galaxy.enterprise.system[3] <= View.MAX_DAMAGE_ONE) {
            int chance = rand.nextInt(4);
            if(chance == 0) {
                view.displayMessage(16);
                galaxy.enterprise.decEnergy((energy / 3));
            }
        }
        else {
            galaxy.enterprise.decEnergy(energy);
            if (galaxy.currentQuadrant().sector[sx-1][sy-1] != null && galaxy.currentQuadrant().sector[sx-1][sy-1] instanceof Klingon ) {
                Klingon k = (Klingon) galaxy.currentQuadrant().sector[sx-1][sy-1];
                k.takeDamage(energy);
                int hull = k.getHull();
                if(hull == 0) {
                    galaxy.currentQuadrant().removeKlingon((sx-1), (sy-1));
                    galaxy.decKlingons();
                    view.displayMessage(2);
                }
                else { 
                    view.remainingHealth(k.getShields(), k.getHull(), (k.getShields()+k.getHull()));
                }
            }
            else if(galaxy.currentQuadrant().sector[sx-1][sy-1] != null && galaxy.currentQuadrant().sector[sx-1][sy-1] instanceof Starbase) {
                galaxy.currentQuadrant().removeKlingon((sx-1), (sy-1));
                galaxy.decStarbase();
                view.displayMessage(8);
            }
            else if(galaxy.currentQuadrant().sector[sx-1][sy-1] != null && galaxy.currentQuadrant().sector[sx-1][sy-1] instanceof Star) {
                view.displayMessage(9);
            }
            else {
                view.displayMessage(3);
            }
        }
    }

    void torpedo(int sx, int sy) {
        if(galaxy.enterprise.system[4] == View.DESTROYED) {
            view.displayMessage(13);
        }
        else if(galaxy.enterprise.system[4] >= View.MIN_DAMAGE_ONE && galaxy.enterprise.system[4] <= View.MAX_DAMAGE_ONE) {
            int chance = rand.nextInt(2);
            if(chance == 0) {
                view.displayMessage(14);
            }
        }
        else if(galaxy.enterprise.system[4] >= View.MIN_HEALTH && galaxy.enterprise.system[4] <= View.MAX_DAMAGE_TWO) {
            int chance = rand.nextInt(4);
            if (chance == 0) {
                view.displayMessage(14);
            }
        }
        else {
            if (galaxy.currentQuadrant().sector[sx-1][sy-1] != null && galaxy.currentQuadrant().sector[sx-1][sy-1] instanceof Klingon){
                galaxy.currentQuadrant().removeKlingon((sx-1), (sy-1));
                galaxy.decKlingons();
                view.displayMessage(2);
            }
            else if (galaxy.currentQuadrant().sector[sx-1][sy-1] != null && galaxy.currentQuadrant().sector[sx-1][sy-1] instanceof Starbase){
                galaxy.currentQuadrant().removeKlingon((sx-1), (sy-1));
                galaxy.decStarbase();
                view.displayMessage(8);
            }
            else if (galaxy.currentQuadrant().sector[sx-1][sy-1] != null && galaxy.currentQuadrant().sector[sx-1][sy-1] instanceof Star){
                view.displayMessage(9);
            }
            else {
                view.displayMessage(1);
            }
            galaxy.enterprise.decTorpedo();
        }
    }

    void impulse(int sx, int sy) {
        double prevXcoor, prevYcoor, xCoorDif, yCoorDif, finalEnergyDec;

        sx--;
        sy--;

        prevXcoor = galaxy.currentQuadrant().getSectorX();
        prevYcoor = galaxy.currentQuadrant().getSectorY();

        xCoorDif = Math.abs(sx-prevXcoor);
        yCoorDif = Math.abs(sy-prevYcoor);

        finalEnergyDec = Math.pow(xCoorDif, 2.0)+Math.pow(yCoorDif, 2.0);
        finalEnergyDec = Math.sqrt(finalEnergyDec);

        galaxy.enterprise.decEnergy((int)finalEnergyDec * 10);

        galaxy.currentQuadrant().removeEnterprise();
        if(galaxy.currentQuadrant().sector[sx][sy] != null) {
            if(galaxy.currentQuadrant().sector[sx][sy] instanceof Star) {
                destroy = true;
                isGameOver(galaxy.enterprise.isDestroyed(destroy));
                view.displayMessage(5); 
            }
            if(galaxy.currentQuadrant().sector[sx][sy] instanceof Starbase) {
                destroy = true;
                isGameOver(galaxy.enterprise.isDestroyed(destroy));
                view.displayMessage(6); 
            }
            if(galaxy.currentQuadrant().sector[sx][sy] instanceof Klingon) {
                destroy = true;
                isGameOver(galaxy.enterprise.isDestroyed(destroy));
                view.displayMessage(7); 
            }
        }
        else {
            galaxy.currentQuadrant().putEnterprise(galaxy.enterprise, sx, sy);
            currentStarDate--;
        }
    }

    void warp(int qx, int qy) {
        double prevXcoor, prevYcoor, xCoorDif, yCoorDif, finalEnergyDec;
        int sx, sy;

        qx--;
        qy--;

        prevXcoor = galaxy.getQuadX();
        prevYcoor = galaxy.getQuadY();

        xCoorDif = Math.abs(qx-prevXcoor);
        yCoorDif = Math.abs(qy-prevYcoor);

        finalEnergyDec = Math.pow(xCoorDif, 2.0)+Math.pow(yCoorDif, 2.0);
        finalEnergyDec = Math.sqrt(finalEnergyDec);
        galaxy.enterprise.decEnergy((int)finalEnergyDec * 100);

        sx = galaxy.currentQuadrant().getSectorX();
        sy = galaxy.currentQuadrant().getSectorY();

        if(galaxy.galaxy[qx][qy].sector[sx][sy] != null) {
            if(galaxy.galaxy[qx][qy].sector[sx][sy] instanceof Star) {
                destroy = true;
                isGameOver(galaxy.enterprise.isDestroyed(destroy));
                view.displayMessage(5); 
            }
            if(galaxy.galaxy[qx][qy].sector[sx][sy] instanceof Starbase) {
                destroy = true;
                isGameOver(galaxy.enterprise.isDestroyed(destroy));
                view.displayMessage(6); 
            }
            if(galaxy.galaxy[qx][qy].sector[sx][sy] instanceof Klingon) {
                destroy = true; 
                isGameOver(galaxy.enterprise.isDestroyed(destroy));
                view.displayMessage(7); 
            }
        }
        galaxy.moveEnterprise(qx, qy, sx, sy);
        if(galaxy.enterprise.system[0] <= View.MAX_HEALTH && galaxy.enterprise.system[0] >= View.MIN_HEALTH_NO_DAMAGE)
            currentStarDate--;
        else if (galaxy.enterprise.system[0] <= View.MAX_DAMAGE_ONE && galaxy.enterprise.system[0] >= View.MIN_DAMAGE_ONE) {
            currentStarDate -= 2;
        }
        else if(galaxy.enterprise.system[0] <= View.MAX_DAMAGE_TWO && galaxy.enterprise.system[0] >= View.MIN_HEALTH) {
            currentStarDate -= 3;
        }
    }

    void damage() {
        view.damage(
            galaxy.enterprise.system[0],
            galaxy.enterprise.system[1],
            galaxy.enterprise.system[2],
            galaxy.enterprise.system[3],
            galaxy.enterprise.system[4],
            galaxy.enterprise.system[5],
            2000);
    }
}
