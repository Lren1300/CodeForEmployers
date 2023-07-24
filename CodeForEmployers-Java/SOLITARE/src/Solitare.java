import java.util.ArrayList;
import java.util.Random;


public class Solitare {
	boolean gameOver;
	View view;
	Tableau[] tablean, savior;
	Foundation[] foundations;
	Pile<Card> mainDeck, waste;
	public static final int WASTE = -1;
	public static final int NUM_FOUNDATIONS = 4;
	public static final int NUM_SUITS = 4;
	public static final int NUM_CARDS_PER_SUIT = 13;
	public static final int NUM_TABLEAN = 7;
	public static final int MAX_FOUNDATION_SIZE = 13;
	
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
		for(int tab=0;tab<NUM_TABLEAN;tab++) {
			tablean[tab] = new Tableau();
			savior[tab] = new Tableau();
		}
		return tablean;
	}
	
	Foundation[] initializeFoundations() {
		for(int found=0;found<NUM_FOUNDATIONS;found++) {
			foundations[found] = new Foundation();
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
		if(foundations[0].size() == MAX_FOUNDATION_SIZE &&
		   foundations[1].size() == MAX_FOUNDATION_SIZE &&
		   foundations[2].size() == MAX_FOUNDATION_SIZE &&
		   foundations[3].size() == MAX_FOUNDATION_SIZE) {
			return true;
		}
		else {
			return false;
		}
	}
	
	Pile<Card> deal(){
		Card temp;
		for(int tab=0;tab<NUM_TABLEAN;tab++) {
			for(int tabSize=0;tabSize<=tab;tabSize++) {
				temp = mainDeck.pop();
				tablean[tab].push(temp);
			}
			tablean[tab].peek().setFaceUp();
		}
		waste.push(mainDeck.pop());
		return mainDeck;
	}
	
	Pile<Card> createCards(Pile<Card> p) {
		char suitC = 'D';
		for (int suit=1; suit<=NUM_SUITS; suit++) {
			if(suit==2) {
				suitC = 'H';
			}
			else if (suit==3) {
				suitC = 'C';
			}
			else if (suit==4) {
				suitC = 'S';
			}
			for (int count = 1; count<=NUM_CARDS_PER_SUIT; count++) {
				Card temp = new Card(suitC, count);
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
		if(before == WASTE) {
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
			if(tablean[before-1].size() > 0) {
				tablean[before-1].peek().setFaceUp();
			}
		}
	}
	
	void moveToTableau(int c, int d, int nc) throws Exception{
		if(nc == 1 || c == WASTE) {
			moveCardTab(c, d);
		}
		else if(nc > 1) {
			Tableau temp = new Tableau();
			Card tempCard, tc2;
			
			for(int i=1; i<=nc; i++) {
				tempCard = tablean[c-1].pop();
				tempCard.faceUp = tempCard.getFaceUp();
				if(tempCard.faceUp = true) {
					temp.push(tempCard);	
				}
				else {
					tablean[c-1].push(tempCard);
					break;
				}
			}
			while(!temp.isEmpty()) {
				tc2 = temp.pop();
				try{
					tablean[d-1].conditionedPush(tc2);
				}
				catch(Exception e){
					view.displayMessage(5);
				}
			}
			if(tablean[c-1].size() > 0) {
				tablean[c-1].peek().setFaceUp();
			}
		}
	}

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
			
			for(int i=1; i<=nc; i++) {
				tempCard = foundations[c-1].pop();
				tempCard.faceUp = tempCard.getFaceUp();
				if(tempCard.faceUp = true) {
					temp.push(tempCard);
				}
				else {
					foundations[c-1].push(tempCard);
					break;
				}
			}
			while(!temp.isEmpty()) {
				tc2 = temp.pop();
				try{
					tablean[d-1].conditionedPush(tc2);
				}
				catch(Exception e){
					view.displayMessage(5);
				}
			}
		}
	}
	
	void moveToFoundation(int c, int d, int nc) throws Exception {
		if(nc == 1 || c == WASTE) {
			moveCardFoun(c, d);
		}
		else if(nc > 1 && c != WASTE) {
			Foundation temp = new Foundation();
			Card tempCard, tc2;
			
			for(int i=1; i<=nc; i++) {
				tempCard = tablean[c-1].pop();
				tempCard.faceUp = tempCard.getFaceUp();
				if(tempCard.faceUp = true) {
					temp.push(tempCard);
				}
				else {
					tablean[c-1].push(tempCard);
					break;
				}
			}
			while(!temp.isEmpty()) {
				tc2 = temp.pop();
				try{
					foundations[d-1].conditionedPush(tc2);
				}
				catch(Exception e){
					view.displayMessage(5);
				}
			}
			
		}
	}
	
	void executeCommand(String command){
		if(command.equals("MOVE")) {
			String place = "";
			String c, d, nc;
			int current, destination, numCards;
			
			view.displayMessage(10);
			place = view.getCommand();
			if(place.equals("T")) {
				view.displayMessage(2); 
				place = view.getCommand();
				if(place.equals("T")) {
					view.displayMessage(3);
				
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
				else if (place.equals("W")) {
					view.displayMessage(9);
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
					if(d.equals("W")) {
						view.displayMessage(9);
					}
					else {
						destination = Integer.parseInt(d);
						nc = view.getCommand();
						numCards = Integer.parseInt(nc);
				
						try{
							moveToFoundation(current, destination, numCards);
						}
						catch(Exception e) {
							view.displayMessage(6); 
						}
					}
				}
			}
			else if(place.equals("F")) {
					view.displayMessage(3);
				
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
					try {
						moveToTableau(-1, destination, 1);
					} 
					catch (Exception e) {
						view.displayMessage(6); // not face up
					}
				}
				else if(place.equals("F")) {
					view.displayMessage(8);
					d = view.getCommand();
					destination = Integer.parseInt(d);
					try {
						moveToFoundation(-1, destination, 1);
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
