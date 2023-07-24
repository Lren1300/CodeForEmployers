
public abstract class Ship extends Placeable{
	int shields;
	
	public Ship() {
		shields = 0;
	}
	
	void decShields(int value){
		shields -= value;
	}
	void incShields(int value) {
		shields += value;
	}
	abstract void takeDamage(int value);
}
