import java.util.*;

public class  Pile<Card> extends Stack<Card>{
	
	private static final long serialVersionUID = 1L; 
	public static final int EMPTY = 0;

	public Pile() {
		super();
	}
	public boolean isEmpty() {
		if(size() == EMPTY) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

