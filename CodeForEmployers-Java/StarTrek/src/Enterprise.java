import java.util.Random;

public class Enterprise extends Ship{
    int torpedos, energy;
    int[] system = {100,100,100,100,100,100}; 
    Random rand;

    public static final int ENGINE = 0;
    public static final int LONG_RANGE_SCANNER = 1;
    public static final int SHORT_RANGE_SCANNER = 2;
    public static final int SHIELD_CONTROL = 3;
    public static final int PHOTON_TUBES = 4;
    public static final int PHASER_CONTROL = 5;
    public static final int NUM_SYSTEMS = 6;

    public Enterprise() {
        super();
        shields = 200;
        torpedos = 10;
        energy = 3000;
        rand = new Random();
    }

    int getEnergy() {
        return energy;
    }

    void setEnergy(int value) {
        energy = value;
    }

    void decEnergy(int value) {
        energy -= value;
    }

    int getShields() {
        return shields;
    }

    void setShields(int value) {
        shields = value;
    }

    void energyToShield(int energy) {
        this.energy -= energy;
        shields += energy;
        if(shields > 2000) {
        	shields = 2000;
        }
    }

    int getTorpedos() {
        return torpedos;
    }

    void setTorpedos(int value) {
        torpedos = value;
    }

    void decTorpedo() {
        torpedos--;
    }

    void dock() { 
        setEnergy(3000);
        setTorpedos(10);
        setShields(200);
        for(int replenish=0; replenish<NUM_SYSTEMS;replenish++) {
            system[replenish] = 100;
        }
    }

    boolean isDestroyed(boolean isDestroyed) {
        if(isDestroyed) {
            return true;
        }
        else {
            return false;
        }
    }

    void repair() {
        int count = 0;
        int count2 = 0;
        int decider = 0;
        int[] tempList = {0, 0, 0, 0, 0, 0};

        for(int scan=0;scan<NUM_SYSTEMS;scan++) {
            if(system[scan] < 100) {
                tempList[count] = scan;
                count++;
            }
        }
        if(count > 0) {
            int[] tempList2 = new int[count]; 
            for(int scan2=0;scan2<=tempList.length-1;scan2++) {
                if(tempList[scan2] != 0) {
                    tempList2[count2] = tempList[scan2];
                    count2++;
                }
            }
            decider = rand.nextInt(count);
            system[tempList2[decider]] += (rand.nextInt(5)+1);
            if(system[tempList2[decider]] > 100) {
                system[tempList2[decider]] = 100;
            }
        }
    }

    @Override
    void takeDamage(int value) {
        int systemType;
        if(shields > 0) {
            if(value >= shields) {
                value -= shields;
                shields = 0;
            }
            else if(shields > value) {
                shields -= value;
            }
        }
        if(value > 0) {
            value /= 10;
            systemType = rand.nextInt(NUM_SYSTEMS);
            for(int num=0;num<NUM_SYSTEMS;num++) {
                if(systemType == num) {
                    if(value > system[num]) {
                        system[num] = 0;
                    }
                    else {
                        system[num] -= value;
                    }
                }
            }	
        }
    }
}
