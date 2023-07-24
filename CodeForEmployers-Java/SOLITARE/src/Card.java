
public class Card {
	char suit;
	int count;
	boolean faceUp, isRed;
	public static final int ACE = 1;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final char SPADES = 'S';
	public static final char HEARTS = 'H';
	public static final char CLUBS = 'C';
	public static final char DIAMONDS = 'D';
	
	public Card(char suit, int count) {
		this.suit = suit;
		this.count = count;
		faceUp = false;
		isRed();
	}
	void isRed() {
		if(suit == HEARTS || suit == DIAMONDS) {
			isRed = true;
		}
		else {
			isRed = false;
		}
	}
	boolean getFaceUp() {
		return faceUp;
	}
	void setFaceUp() {
		faceUp = true;
	}
	public String toString() {
		String s = "";
		if(count == KING) {
			s += "K";
		}
		else if(count == QUEEN) {
			s += "Q";
		}
		else if(count == JACK) {
			s += "J";
		}
		else if(count == ACE) {
			s += "A";
		}
		else {
			s += count;
		}
		s += suit;
		return s;
	}
}
