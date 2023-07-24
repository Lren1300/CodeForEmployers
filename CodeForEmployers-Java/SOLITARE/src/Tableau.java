
public class Tableau extends Pile<Card>{
	public Tableau() {
		super();
	}
	void conditionedPush(Card c) throws Exception { 
		if(isEmpty() && c.count == Card.KING) {
			push(c);
		}
		else if(!isEmpty() && peek().isRed && !c.isRed) {
			if(peek().count-1 == c.count) {
				push(c);
			}
			else {
				throw new Exception("Did not follow rules");
			}
		}
		else if(!isEmpty() && !peek().isRed && c.isRed) {
			if(peek().count-1 == c.count) {
				push(c);
			}
			else {
				throw new Exception("Did not follow rules");
			}
		}
		else {
			throw new Exception("Did not follow rules");
		}
	}
}
