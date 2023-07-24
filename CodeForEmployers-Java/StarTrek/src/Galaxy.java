
import java.util.Random;

public class Galaxy {
    Random rand; 
    Quadrant[][] galaxy;
    int quadX, quadY;
    Enterprise enterprise;
    int starbases, klingons, stars;
    public static int NUM_ENTERPRISES = 1;

    public Galaxy() {
        rand = new Random();
        enterprise = new Enterprise();
        galaxy = new Quadrant[8][8];
        for (int r=View.INDEX_MIN; r<View.INDEX_MAX; r++) {
            for (int c=View.INDEX_MIN; c<View.INDEX_MAX; c++) {
                galaxy[r][c] = new Quadrant();
            }
        }
        quadX = -1;
        quadY = -1;
        starbases = 3 + rand.nextInt(3);
        klingons = 10 + rand.nextInt(10);
        stars = 70 + rand.nextInt(130);
        placeItem(1, klingons);
        placeItem(2, stars);
        placeItem(3, starbases);
        placeItem(4, NUM_ENTERPRISES);

    }

    int getQuadX() {
        return quadX;
    }

    int getQuadY() {
        return quadY;
    }

    int getKlingons() {
        return klingons;
    }

    void decKlingons() {
        klingons--;
    }

    int getStarbases() {
        return starbases;
    }

    void decStarbase() {
        starbases--;
    }

    public Quadrant currentQuadrant() {
        return galaxy[quadX][quadY];
    }

    public void moveEnterprise(int qx, int qy, int sx, int sy) { 
        currentQuadrant().removeEnterprise();
        galaxy[qx][qy].sector[sx][sy] = enterprise;
        galaxy[qx][qy].updateSectorCoor(sx, sy);
        quadX = qx;
        quadY = qy;
        if(galaxy[qx][qy].isDocked()) {
            enterprise.dock();
        }
    }

    public void placeItem(int type, int n) {
        int qx, qy, sx, sy; 
        try {
            if (type == Placeable.KLINGON) {
                for(int i=0;i<n;i++) {
                    qx = rand.nextInt(8);
                    qy = rand.nextInt(8);
                    sx = rand.nextInt(8);
                    sy = rand.nextInt(8);
                    if(galaxy[qx][qy].sector[sx][sy] == null) {
                        galaxy[qx][qy].putKlingon(sx, sy);
                    }
                    else {
                        i--;
                    }
                }
            }
            if (type == Placeable.STAR) {
                for(int i=0;i<n;i++) {
                    qx = rand.nextInt(8);
                    qy = rand.nextInt(8);
                    sx = rand.nextInt(8);
                    sy = rand.nextInt(8);
                    if(galaxy[qx][qy].sector[sx][sy] == null) {
                        galaxy[qx][qy].putStar(sx, sy);
                    }
                    else {
                        i--;
                    }
                }
            }
            if (type == Placeable.STARBASE) {
                for(int i=0;i<n;i++) {
                    qx = rand.nextInt(8);
                    qy = rand.nextInt(8);
                    sx = rand.nextInt(8);
                    sy = rand.nextInt(8);
                    if(galaxy[qx][qy].sector[sx][sy] == null) {
                        galaxy[qx][qy].putStarbase(sx, sy);
                    }
                    else {
                        i--;
                    }
                }
            }
            if (type == Placeable.ENTERPRISE) {
                for(int i=0;i<n;i++) {
                    qx = rand.nextInt(8);
                    qy = rand.nextInt(8);
                    sx = rand.nextInt(8);
                    sy = rand.nextInt(8);
                    if(galaxy[qx][qy].sector[sx][sy] == null) {
                        galaxy[qx][qy].putFirstEnterprise(enterprise, sx, sy);
                        quadX = qx;
                        quadY = qy;
                        galaxy[qx][qy].updateSectorCoor(sx, sy);
                    }
                    else {
                        i--;
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.print("Error: Could not identify 'Placeables'");
        }
    }

}
