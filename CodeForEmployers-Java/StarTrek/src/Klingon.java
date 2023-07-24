
public class Klingon extends Ship{
	int hull;
	
	public Klingon() {
		super();
		shields = 250;
		hull = 150;
	}
	int getHull() {
		return hull;
	}
	void setHull(int hull) {
		this.hull = hull;
	}
	int getShields() {
		return shields;
	}
	@Override
	void takeDamage(int value) {
		int updatedValue = 0;
		
		if(value > shields) {
			updatedValue = (value - shields);
			shields = 0;
			
			if(updatedValue > hull) {
				hull = 0;
			}
			else {
				hull -= updatedValue;
			}
		}
		else {
			shields -= value;
		}
	}
}
