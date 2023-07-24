import java.util.ArrayList;
import java.util.Random;


public class Solitare {
	boolean gameOver;
	View view;
	Tableau[] tablean, savior;
	Foundation[] foundations;
	Pile<Card> mainDeck, waste;
	public static final int WASTE = -1;
	
	public Solitare() {
		mainDeck = new Pile<Card>();
		view = new View();
		tablean = new Tableau[7];
		savior = new Tableau[7];
		foundations = new Foundation[4];
		mainDeck = new Pile<Card> ();
		waste = new Pile<Card> ();
		gameOver = false;
	}
	
	Tableau[] initializeTablean() {
		for(int i=0;i<7;i++) {
			tablean[i] = new Tableau();
			savior[i] = new Tableau();
		}
		return tablean;
	}
	
	Foundation[] initializeFoundations() {
		for(int i=0;i<4;i++) {
			foundations[i] = new Foundation();
		}
		return foundations;
	}
	
	public static void main(String[] args) {
		Solitare s = new Solitare();
		s.setup();
		s.gameLoop();
	}
	
	void setup() {
		tablean = initializeTablean();
		foundations = initializeFoundations();
		mainDeck = createCards(mainDeck);
		mainDeck = shuffle(mainDeck);
		mainDeck = deal();
	}
	
	void gameLoop() {
		while(!gameOver) {
			view.printBoard(tablean, foundations, savior,  waste, mainDeck);
			executeCommand(view.getCommand());
		}
		view.displayMessage(7); //game over
		
	}
	
	boolean isGameOver(){
		if(foundations[0].size() == 13 &&
		   foundations[1].size() == 13 &&
		   foundations[2].size() == 13 &&
		   foundations[3].size() == 13) {
			return true;
		}
		else {
			return false;
		}
	}
	
	Pile<Card> deal(){
		Card temp;
		for(int i=0;i<7;i++) {
			for(int j=0;j<=i;j++) {
				temp = mainDeck.pop();
				tablean[i].push(temp);
			}
			tablean[i].peek().setFaceUp();
		}
		waste.push(mainDeck.pop());
		return mainDeck;
	}
	
	Pile<Card> createCards(Pile<Card> p) {
		char suit = 'D';
		for (int i=1; i<=4; i++) {
			if(i==2) {
				suit = 'H';
			}
			else if (i==3) {
				suit = 'C';
			}
			else if (i==4) {
				suit = 'S';
			}
			for (int v = 1; v<=13; v++) {
				Card temp = new Card(suit, v);
				temp.isRed();
				p.push(temp);
			}	
		}
		return p;
	}
	
	Pile<Card> shuffle(Pile<Card> p) {
		Random shuffle = new Random();
		int randomCard;
		ArrayList<Card> deck = new ArrayList<Card>();
		ArrayList<Card> shuffleList = new ArrayList<Card> ();
		
		for(int i = p.size(); i >= 1; i--) {
			deck.add(p.pop());
		}
		for (int i = deck.size(); i >= 1; i--) {
			randomCard = shuffle.nextInt(i);
			shuffleList.add(deck.get(randomCard));
			deck.remove(randomCard);
		}
		deck = shuffleList;
		
		for(int i = deck.size()-1; i>=0; i--) {
			p.push(deck.get(i));
		}
		return p;
	}

	void moveCardTab(int before, int after) {
		Card temp;
		if(before == -1) {
			temp = waste.pop();
			try {
				tablean[after-1].conditionedPush(temp);
			} 
			catch (Exception e) {
				waste.push(temp);
				view.displayMessage(5);
			}
			temp.setFaceUp();
		}
		else {
			temp = (Card) tablean[before-1].pop();
			try {
				tablean[after-1].conditionedPush(temp);
			} 
			catch (Exception e) {
				tablean[before-1].push(temp);
				view.displayMessage(5);
			}
			tablean[before-1].peek().setFaceUp();
		}
	}
	
	void moveCardFoun(int before, int after) {
		Card temp;
		if(before == WASTE) {
			temp = waste.pop();
			try {
				foundations[after-1].conditionedPush(temp);
			} 
			catch (Exception e) {
				waste.push(temp);
				view.displayMessage(5);
			}
			temp.setFaceUp();
		}
		else {
			temp = (Card) tablean[before-1].pop();
			try {
				foundations[after-1].conditionedPush(temp);
			} 
			catch (Exception e) {
				tablean[before-1].push(temp);
				view.displayMessage(5);
			}
			tablean[before-1].peek().setFaceUp();
		}
	}
	
	void moveToTableau(int c, int d, int nc) throws Exception{
		if(nc == 1 || c == WASTE) {
			moveCardTab(c, d);
		}
		else if(nc > 1) {
			Tableau temp = new Tableau();
			Card tempCard, tc2;
			boolean faceUp;
			
			for(int i=1; i<=nc; i++) {
				tempCard = tablean[c-1].pop();
				faceUp = tempCard.getFaceUp();
				if(faceUp = true) {
					try {
						temp.push(tempCard);
					}
					catch(Exception e){
						tablean[c-1].push(tempCard);
						view.displayMessage(5);// Did not meet conditions
					}
					
				}
				else {
					throw new Exception("Card/s not faced up.");
				}
			}
			while(!temp.isEmpty()) {
				tc2 = temp.pop();
				tablean[d-1].push(tc2);
			}
		}
	}
	
//	Tableau reverseTab(Tableau t) {
//		Card temp;
//		Tableau tempT = new Tableau();
//		Tableau savior = new Tableau();
//		
//		while(!t.isEmpty()) {
//			temp = t.pop();
//			tempT.push(temp);
//			savior.push(temp);
//		}
//		while(!savior.isEmpty()) {
//			temp = savior.pop();
//			t.push(temp);
//		}
//		return tempT;
//	}
	void moveFromFounToTab(int before, int after) {
		Card temp;
		temp = (Card) foundations[before-1].pop();
		try {
			tablean[after-1].conditionedPush(temp);
		} 
		catch (Exception e) {
			foundations[before-1].push(temp);
			view.displayMessage(5);
		}
	}
	void moveFromFound(int c, int d, int nc) throws Exception {
		if(nc == 1 || c == WASTE) {
			moveFromFounToTab(c, d);
		}
		else if(nc > 1) {
			Tableau temp = new Tableau();
			Card tempCard, tc2;
			boolean faceUp;
			
			for(int i=1; i<=nc; i++) {
				tempCard = foundations[c-1].pop();
				faceUp = tempCard.getFaceUp();
				if(faceUp = true) {
					try {
						temp.push(tempCard);
					}
					catch(Exception e){
						foundations[c-1].push(tempCard);
						view.displayMessage(5);// Did not meet conditions
					}
					
				}
				else {
					throw new Exception("Card/s not faced up.");
				}
			}
			while(!temp.isEmpty()) {
				tc2 = temp.pop();
				tablean[d-1].push(tc2);
			}
		}
	}
	void moveToFoundation(int c, int d, int nc) throws Exception {
		if(nc == 1 || c == WASTE) {
			moveCardFoun(c, d);
		}
		else if(nc > 1 && c != WASTE) {
			Foundation temp = new Foundation();
			Card tempCard;
			boolean faceUp;
			
			for(int i=1; i<=nc; i++) {
				tempCard = tablean[c].pop();
				faceUp = tempCard.getFaceUp();
				if(faceUp = true) {
					try {
					temp.conditionedPush(tempCard);
					}
					catch(Exception e){
						view.displayMessage(5);// Did not meet conditions
					}
				}
				else {
					throw new Exception("Card/s not faced up.");
				}
			}
			
		}
	}
	
	void executeCommand(String command){
		if(command.equals("MOVE")) {
			String place = "";
			String ct, c, d, nc;
			int current, destination, numCards;
			Pile<Card> currentType;
			
			view.displayMessage(10);//ask for starting area
			place = view.getCommand();
			if(place.equals("T")) {
				view.displayMessage(2); // ask for destination
				place = view.getCommand();
				if(place.equals("T")) {
					view.displayMessage(3);// ask for input
				
					c = view.getCommand();
					if(c.equals("W")) {
						current = WASTE;
					}
					else {
						current = Integer.parseInt(c);
					}
					d = view.getCommand();
					destination = Integer.parseInt(d);
					nc = view.getCommand();
					numCards = Integer.parseInt(nc);
				
					try{
						moveToTableau(current, destination, numCards);
					}
					catch(Exception e) {
						view.displayMessage(6); // not face up
					}
				}
				else if(place.equals("F")) {
					view.displayMessage(3);// ask for input 
				
					c = view.getCommand();
					if(c.equals("W")) {
						current = WASTE;
					}
					else {
						current = Integer.parseInt(c);
					}
					d = view.getCommand();
					destination = Integer.parseInt(d);
					nc = view.getCommand();
					numCards = Integer.parseInt(nc);
				
					try{
						moveToFoundation(current, destination, numCards);
					}
					catch(Exception e) {
						view.displayMessage(6); //not face up
					}
				}
			}
			else if(place.equals("F")) {
					view.displayMessage(3);// ask for input
				
					c = view.getCommand();
					current = Integer.parseInt(c);
					d = view.getCommand();
					destination = Integer.parseInt(d);
					nc = view.getCommand();
					numCards = Integer.parseInt(nc);
				
					try{
						moveToTableau(current, destination, numCards);
					}
					catch(Exception e) {
						view.displayMessage(6); // not face up
					}
				}
			else if(place.equals("W")){
				view.displayMessage(2); // ask for destination
				place = view.getCommand();
				
				if(place.equals("T")) {
					view.displayMessage(8);
					d = view.getCommand();
					destination = Integer.parseInt(d);
					nc = view.getCommand();
					numCards = Integer.parseInt(nc);
					try {
						moveToTableau(-1, destination, numCards);
					} 
					catch (Exception e) {
						view.displayMessage(6); // not face up
					}
				}
				else if(place.equals("F")) {
					view.displayMessage(8);
					d = view.getCommand();
					destination = Integer.parseInt(d);
					nc = view.getCommand();
					numCards = Integer.parseInt(nc);
					try {
						moveToFoundation(-1, destination, numCards);
					} 
					catch (Exception e) {
						view.displayMessage(6); // not face up
					}
				}
			}
		}
		if(command.equals("DRAW")) {
			Card temp, temp2;
			int flip = mainDeck.size();
			if(flip == 1) {
				while(!waste.isEmpty()) {
					temp2 = waste.pop();
					mainDeck.push(temp2);
				}
			}
			temp = mainDeck.pop();
			temp.setFaceUp();
			waste.push(temp);
			
		}
		if(command.equals("HELP")) {
			view.printHelp();
		}
		if(command.equals("QUIT")) {
			gameOver = true;
		}
	}
}
