
public class Foundation extends Pile<Card>{
	public Foundation() {
		super();
	}
	void conditionedPush(Card c) throws Exception { 
		if(isEmpty() && c.count == Card.ACE) {
			push(c);
		}
		else if(!isEmpty() && peek().count+1 == c.count && peek().suit == c.suit) {
			push(c);
		}
		else {
			throw new Exception("Did not follow rules");
		}
	}
}
