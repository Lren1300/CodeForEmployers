
import java.util.Random;

public class Quadrant {
    Random rand = new Random();
    Placeable[][] sector;
    int sectorX, sectorY;
    int starbases, klingons, stars;
    boolean docked, isScanned;
    Enterprise e;

    public Quadrant() {
        sector = new Placeable[8][8];
        for (int r = View.INDEX_MIN; r < View.INDEX_MAX; r++) {
            for (int c = View.INDEX_MIN; c < View.INDEX_MAX; c++) {
                sector[r][c] = null;
            }
        }
        sectorX = -1;
        sectorY = -1;
        starbases = 0;
        klingons = 0;
        stars = 0;
        docked = false;
        isScanned = false;
    }

    int getSectorX() {
        return sectorX;
    }

    int getSectorY() {
        return sectorY;
    }

    void updateSectorCoor(int sx, int sy) {
        sectorX = sx;
        sectorY = sy;
    }

    int getKlingons() {
        return klingons;
    }

    int getStarbases() {
        return starbases;
    }

    int getStars() {
        return stars;
    }

    void setIsScanned() {
        isScanned = true;
    }

    boolean isScanned() {
        return isScanned;
    }

    String getKBS() {
        String s = String.valueOf(klingons)+String.valueOf(starbases)+String.valueOf(stars);
        return s;
    }

    boolean hasEnterprise(Quadrant q) {
        for (int r=View.INDEX_MIN;r<View.INDEX_MAX;r++) {
            for(int c=View.INDEX_MIN;c<View.INDEX_MAX;c++) {
                if(sector[r][c] instanceof Enterprise) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean hasKlingon() {
        for (int r=View.INDEX_MIN;r<View.INDEX_MAX;r++) {
            for(int c=View.INDEX_MIN;c<View.INDEX_MAX;c++) {
                if(sector[r][c] instanceof Klingon) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isDocked() {
        for(int r=View.INDEX_MIN;r<View.INDEX_MAX;r++) {
            for(int c=View.INDEX_MIN;c<View.INDEX_MAX;c++) {
                if(sector[r][c] instanceof Starbase) {
                    if((r-1) >= View.INDEX_MIN && (r+1) < View.INDEX_MAX && (c-1) >= View.INDEX_MIN && (c+1) < View.INDEX_MAX ) {
                        if(sector[r-1][c] instanceof Enterprise ||
                        sector[r][c-1] instanceof Enterprise ||
                        sector[r][c+1] instanceof Enterprise ||
                        sector[r+1][c] instanceof Enterprise) {
                            return true;
                        }
                    }
                    else{
                        if((r-1) < View.INDEX_MIN && (c-1) >= View.INDEX_MIN && (r+1) < View.INDEX_MAX && (c+1) < View.INDEX_MAX) {
                            if(sector[r][c-1] instanceof Enterprise ||
                            sector[r][c+1] instanceof Enterprise ||
                            sector[r+1][c] instanceof Enterprise) {
                                return true;	
                            }
                        }
                        else if((c-1) < View.INDEX_MIN && (r-1) >= View.INDEX_MIN && (r+1) < View.INDEX_MAX && (c+1) < View.INDEX_MAX) {
                            if(sector[r-1][c] instanceof Enterprise ||
                            sector[r][c+1] instanceof Enterprise ||
                            sector[r+1][c] instanceof Enterprise) {
                                return true;	
                            }
                        }
                        else if((r-1) >= View.INDEX_MIN && (c-1) >= View.INDEX_MIN && (r+1) >= View.INDEX_MAX && (c+1) < View.INDEX_MAX) {
                            if(sector[r-1][c] instanceof Enterprise ||
                            sector[r][c-1] instanceof Enterprise ||
                            sector[r][c+1] instanceof Enterprise) {
                                return true;
                            }
                        }
                        else if((r-1) >= View.INDEX_MIN && (c-1) >= View.INDEX_MIN && (c+1) >= View.INDEX_MAX && (r+1) < View.INDEX_MAX) {
                            if(sector[r-1][c] instanceof Enterprise ||
                            sector[r][c-1] instanceof Enterprise ||
                            sector[r+1][c] instanceof Enterprise) {
                                return true;
                            }
                        }
                        else if((c+1) >= View.INDEX_MAX && (r+1) >= View.INDEX_MAX && (r-1) >= View.INDEX_MIN && (c-1) >= View.INDEX_MIN) {
                            if(sector[r-1][c] instanceof Enterprise ||
                            sector[r][c-1] instanceof Enterprise) {
                                return true;
                            }
                        }
                        else if((c-1) < View.INDEX_MIN && (r-1) < View.INDEX_MIN && (r+1) < View.INDEX_MAX && (c+1) < View.INDEX_MAX) {
                            if(sector[r+1][c] instanceof Enterprise ||
                            sector[r][c+1] instanceof Enterprise) {
                                return true;
                            }
                        }
                        else if((c-1) < View.INDEX_MIN && (r+1) >= View.INDEX_MAX && (r-1) >= View.INDEX_MIN && (c+1) < View.INDEX_MAX) {
                            if(sector[r-1][c] instanceof Enterprise ||
                            sector[r][c+1] instanceof Enterprise) {
                                return true;
                            }
                        }
                        else if((c+1) >= View.INDEX_MAX && (r-1) < View.INDEX_MIN && (c-1) >= View.INDEX_MIN && (r+1) < View.INDEX_MAX) {
                            if(sector[r+1][c] instanceof Enterprise ||
                            sector[r][c-1] instanceof Enterprise) {
                                return true;
                            }
                        }
                    }
                }
            }

        }
        return false;
    }

    void putFirstEnterprise(Enterprise e, int sx, int sy) {
        sector[sx][sy] = e;
        this.e = e;
        sectorX = sx;
        sectorY = sy;
    }

    void putEnterprise(Enterprise e, int sx, int sy) {
        sector[sx][sy] = e;
        this.e = e;
        sectorX = sx;
        sectorY = sy;
        if(isDocked()) {
            e.dock();
        }
    }

    void putKlingon(int sx, int sy) {
        klingons++;
        Klingon k = new Klingon();
        sector[sx][sy] = k;
    }

    void putStar(int sx, int sy) {
        stars++;
        Star s = new Star();
        sector[sx][sy] = s;
    }

    void putStarbase(int sx, int sy) {
        starbases++;
        Starbase st = new Starbase();
        sector[sx][sy] = st;
    }

    void moveEnterprise(int sx, int sy) {
        removeEnterprise();
        sector[sx][sy] = e; 
    }

    void removeEnterprise() {
        sector[sectorX][sectorY] = null;
    }

    void removeKlingon(int sx, int sy) {
        sector[sx][sy] = null;
        klingons--;
    }
}

