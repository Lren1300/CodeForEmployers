import java.util.Scanner;

public class View {
	public static final int LAST_TABLEAU = 7;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_WHITE = "\u001B[37m";


	Scanner scnr = new Scanner(System.in);
	
	public View() {
		
	}

	void printFoundations(Foundation[] foundation) {
		System.out.print("          ");
		System.out.println(" F1  F2  F3  F4");
		System.out.print("           ");
		for(int numFoundation=0; numFoundation<foundation.length; numFoundation++) {
			Card temp;
			if(foundation[numFoundation].isEmpty()) {
				System.out.print("[]  ");
			}
			else {
				temp = foundation[numFoundation].peek();
				if(temp.isRed) {
					System.out.print(ANSI_WHITE + ANSI_RED_BACKGROUND + temp.toString());
					System.out.print(ANSI_RESET + "  ");
				}
				else {
					System.out.print(ANSI_WHITE + ANSI_BLACK_BACKGROUND + temp.toString());
					System.out.print(ANSI_RESET + "  ");
				}
			}
		}
		System.out.println("");
		System.out.println("");
	}
	
	void printTableaus(Tableau[] tablean, Tableau[] savior) {
		Card[] tempA;
		int max, size;
		
		System.out.print(" ");
		for(int numTableau=1; numTableau<=7; numTableau++) {
			System.out.printf("  %d  ", numTableau);
		}
		System.out.println("");
		max = maxTab(tablean);
		
		for(int row=0;row<max;row++) {
			System.out.print(" ");
			for(int col=0; col<7; col++) {
				tempA = pileToArray(tablean[col]);
				tempA = reverseOrderArray(tempA);
				size = tablean[col].size()-1;
				if(size < row) {
					System.out.print("     ");
				}
				else {
					if(tempA[row].getFaceUp()) {
						if(tempA[row].isRed) {
							if(tempA[row].count == 10) {
								System.out.print(" ");
								System.out.print(ANSI_WHITE + ANSI_RED_BACKGROUND + tempA[row]);
								System.out.print(ANSI_RESET + " ");
							}
							else {
								System.out.print(" ");
								System.out.print(" ");
								System.out.print(ANSI_WHITE + ANSI_RED_BACKGROUND + tempA[row]);
								System.out.print(ANSI_RESET + " ");
							}
						}
						else {
							if(tempA[row].count == 10) {
								System.out.print(" ");
								System.out.print(ANSI_WHITE + ANSI_BLACK_BACKGROUND + tempA[row]);
								System.out.print(ANSI_RESET + " ");
							}
							else {
								System.out.print(" ");
								System.out.print(" ");
								System.out.print(ANSI_WHITE + ANSI_BLACK_BACKGROUND + tempA[row]);
								System.out.print(ANSI_RESET + " ");
							}
						}
					}
					else {
						System.out.print(" --- ");
					}
				}
		}
		System.out.println("");
		}
	System.out.println("");
	}
	
	Card[] pileToArray(Pile<Card> p) {
		Card tempC, tempC2;
		Card[] temp = new Card[p.size()];
		Pile<Card> savior = new Pile<Card> ();
		int size = p.size();
		
		for(int i=0; i<size; i++) {
			tempC = p.pop();
			savior.push(tempC);
			temp[i] = tempC;
		}
		while(!savior.isEmpty()) {
			tempC2 = savior.pop();
			p.push(tempC2);
		}
		return temp;
	}
	
	Card[] reverseOrderArray(Card[] c) {
		Card[] temp = new Card[c.length];
		int count = 0;
		
		for(int i=c.length-1; i>=0; i--) {
			temp[count] = c[i];
			count++;
		}
		return temp;
	}
	
	int maxTab(Tableau[] t) {
		int count1 = 0;
		int count2 = 0;
		Card temp;
		Card temp2;
		Tableau savior = new Tableau();
		
		for(int i=0; i<LAST_TABLEAU; i++) {
		while(!t[i].isEmpty()) {
			temp = t[i].pop();
			savior.push(temp);
			count1++;
			if(count1>count2) {
				count2=count1;
			}
		}
		while(!savior.isEmpty()) {
			temp2 = savior.pop();
			t[i].push(temp2);
		}
		count1 = 0;
		}
		return count2;
	}
	
	void printWaste(Pile<Card> waste, Pile<Card> deck) {
		if(waste.isEmpty()) {
			System.out.println("Waste: --- (" + deck.size() + " cards are left in deck)");
		}
		else {
			if(waste.peek().isRed) {
				System.out.println("Waste: "+ ANSI_WHITE + ANSI_RED_BACKGROUND + waste.peek().toString() +
						ANSI_RESET + " (" + deck.size() + " cards are left in deck)");
			}
			else {
				System.out.println("Waste: "+ ANSI_WHITE + ANSI_BLACK_BACKGROUND + waste.peek().toString() + 
						ANSI_RESET + " (" + deck.size() + " cards are left in deck)");
			}
		}
	}
	
	void printBoard(Tableau[] t, Foundation[] f, Tableau[] s, Pile<Card> waste, Pile<Card> deck) {
		System.out.println("");
		System.out.println("Command List: MOVE, DRAW, HELP, QUIT");
		System.out.println("");
		printFoundations(f);
		printTableaus(t, s);
		printWaste(waste, deck);
		System.out.print("Next action: ");
	}
	
	void printHelp() {
		System.out.println("");
		System.out.println("The game is solitare. If you have never played, here are the rules: ");
		System.out.println("");
		System.out.println("The goal of the game is to fill up the 4 foundations with cards.");
		System.out.println("The cards must be of same suit and in ascending order (A, 2, 3, 4, ... 10, J, Q, K).");
		System.out.println("");
		System.out.println("In order to do so, you must move around cards in the 7 tableaus.");
		System.out.println("These tableaus also have rules, they must be in decending order and the colors ");
		System.out.println("of the cards must be alternating. If you cannot make any legal moves, there is a deck and ");
		System.out.println("waste at your disposal. You may draw one card at a time and only take from the top of");
		System.out.println("the waste pile. Once the deck is emptied, the waste pile is flipped over and turned");
		System.out.println("into a new deck. ");
		System.out.println("");
		System.out.println("It is in the nature of the game that some games cannot be finished.");
		System.out.println("This means that some games will simply have no end and you must quit, this is something to think about.");
		System.out.println("");
		System.out.println("Commands go as followed: ");
		System.out.println("");
		System.out.println("MOVE- If you move it will ask you for the pile you want to take off of,");
		System.out.println(" your desitnation, which will be either a tableau or foundation,");
		System.out.println(" you will then put in the current # tableau or foundation, the destination #, and the number of cards.");
		System.out.println("");
		System.out.println("DRAW- This will draw one card from the deck and put it on the top of your waste pile.");
		System.out.println("");
		System.out.println("QUIT- This will end the game.");
		System.out.println("");
		System.out.println("Good luck and have fun!");
		System.out.println("");
	}
	
	void displayMessage(int num) {
		if(num == 1) {
			System.out.println("Error: Cannot place card there.");
		}
		if(num == 2) {
			System.out.println("Are you moving to a Tableau[T] or a Foundation[F]?");
		}
		if(num == 3) {
			System.out.println("Input the starting current pile number, the destination number, ");
			System.out.println("and the number of cards you want to move(ex 1 2 3): ");
		}
		if(num == 5) {
			System.out.println("Error: Broken rule, cannot move card.");
		}
		if(num == 6) {
			System.out.println("Card/s are not all faced up.");
		}
		if(num == 7) {
			System.out.println("Game Over!");
		}
		if(num == 8 ) {
			System.out.println("Input the destination number: ");
		}
		if(num == 9) {
			System.out.println("You cannot move cards into the waste unless drawing from deck.");
		}
		if(num == 10) {
			System.out.println("Where will you be taking from? [T]ableau, [F]oundation, or [W]aste?");
		}
		System.out.println("");
	}
	
	public String getCommand() {
        String command;
        command = scnr.next();
        command = command.toUpperCase();
        return command;
    }
}
