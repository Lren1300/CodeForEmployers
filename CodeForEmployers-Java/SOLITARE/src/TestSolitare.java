import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestSolitare {
	
	@Test
	public void testMultipleCardMove() throws Exception {
		Solitare sol = new Solitare();
		sol.initializeTablean();
		sol.initializeFoundations();
		Card c = new Card(Card.HEARTS, 3);
		c.setFaceUp();
		sol.tablean[0].push(c);
		
		Card c2 = new Card(Card.CLUBS, 2);
		c2.setFaceUp();
		sol.tablean[0].conditionedPush(c2);
		
		Card c3 = new Card(Card.HEARTS, Card.ACE);
		c3.setFaceUp();
		sol.tablean[0].conditionedPush(c3);
		
		Card c4 = new Card(Card.CLUBS, 4);
		c4.setFaceUp();
		sol.tablean[1].push(c4);
		
		sol.moveToTableau(1, 2, 3);
		
		assertEquals(sol.tablean[1].pop(), c3); //1
		assertEquals(sol.tablean[1].pop(), c2); //2
		assertEquals(sol.tablean[1].pop(), c); //3
		assertEquals(sol.tablean[1].pop(), c4);//4
		
		Card c5 = new Card(Card.HEARTS, 6); //Correct color, wrong count
		c5.setFaceUp();
		sol.tablean[2].push(c5);
		
		assertThrows(Exception.class, () -> {
			sol.moveToTableau(2, 3, 4);
		});
		
		Card c6 = new Card(Card.CLUBS, 5); //Correct count, wrong color
		c6.setFaceUp();
		sol.tablean[3].push(c6);
		
		assertThrows(Exception.class, () -> {
			sol.moveToTableau(2, 4, 4);
		});
	}
	
	@Test
	public void testGameOver() {
		
		Solitare s = new Solitare();
		s.setup();
		assertFalse( s.isGameOver());
		
		Foundation f = new Foundation();
		for (int value = Card.ACE; value <= Card.KING; value++) {
			Card c = new Card(Card.SPADES, value);
			f.push(c);
		}
				
		s.foundations[0] = f;
		s.foundations[1] = f;
		s.foundations[2] = f;
		s.foundations[3] = f;
				
		assertTrue( s.isGameOver() );

	}
	
	@Test
	public void testFirstTableauSpot() throws Exception {
		Solitare s = new Solitare();
		s.initializeTablean();
		Card king = new Card(Card.SPADES, Card.KING);
		Card phony = new Card(Card.SPADES, Card.ACE);
		
		assertThrows(Exception.class, () -> {
			s.tablean[0].conditionedPush(phony);
		});
		
		s.tablean[0].conditionedPush(king);
		assertEquals(s.tablean[0].pop(), king);
		
	}
	
	@Test
	public void testFirstFoundationSpot() throws Exception {
		Solitare s = new Solitare();
		s.initializeFoundations();
		Card phony = new Card(Card.SPADES, Card.KING);
		Card ace = new Card(Card.SPADES, Card.ACE);
		
		assertThrows(Exception.class, () -> {
			s.foundations[0].conditionedPush(phony);
		});
		
		s.foundations[0].conditionedPush(ace);
		assertEquals(s.foundations[0].pop(), ace);
		
	}
	
	@Test
	public void testMoveFromFoundation() throws Exception {
		Solitare s = new Solitare();
		s.initializeFoundations();
		s.initializeTablean();
		Card temp = new Card(Card.SPADES, Card.ACE);
		s.foundations[0].push(temp);
		s.tablean[0].push(new Card(Card.HEARTS, 2));
		
		s.moveFromFound(1, 1, 1);
		
		assertEquals(s.tablean[0].pop(), temp);
	}
	
	@Test
	public void testDeckRefil() {
		Solitare s = new Solitare();
		s.mainDeck.push(new Card(Card.SPADES, Card.ACE));
		s.mainDeck.push(new Card(Card.SPADES, Card.ACE));
		s.mainDeck.push(new Card(Card.SPADES, Card.ACE));
		s.mainDeck.push(new Card(Card.SPADES, Card.ACE));
		
		s.executeCommand("DRAW");
		s.executeCommand("DRAW");
		s.executeCommand("DRAW");
		
		assertEquals(s.mainDeck.size(), 1);
		
		s.executeCommand("DRAW");
		
		assertEquals(s.mainDeck.size(), 3);
	}
	
	@Test
	public void testWrongSuitAndValueFoundation() {
		Solitare s = new Solitare();
		s.initializeFoundations();
		s.foundations[0].push(new Card(Card.CLUBS, Card.ACE));
		
		assertThrows(Exception.class, () -> {
			s.foundations[0].conditionedPush(new Card(Card.HEARTS, 2));
		});
		
		assertThrows(Exception.class, () -> {
			s.foundations[0].conditionedPush(new Card(Card.CLUBS, 3));
		});
	}
	
	@Test
	public void testWrongSuitAndValueTableau() {
		Solitare s = new Solitare();
		s.initializeTablean();
		s.tablean[0].push(new Card(Card.CLUBS, Card.KING));
		
		assertThrows(Exception.class, () -> {
			s.tablean[0].conditionedPush(new Card(Card.HEARTS, Card.JACK));
		});
		
		assertThrows(Exception.class, () -> {
			s.tablean[0].conditionedPush(new Card(Card.CLUBS, Card.QUEEN));
		});
	}
}
