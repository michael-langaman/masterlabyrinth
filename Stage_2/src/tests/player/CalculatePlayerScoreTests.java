package tests.player;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.model.FormulaCard;
import code.model.Player;
import code.model.Token;

public class CalculatePlayerScoreTests {
	
	@Test public void test01() {
		Player player1 = new Player("p1");
		player1.addToken(new Token(1, ""));
		player1.addToken(new Token(10, ""));
		player1.addToken(new Token(13, ""));
		player1.setFormulaCard(new FormulaCard(1));
		assertTrue("" + player1.getScore(), player1.getScore() == 93);
	}
	
	@Test public void test02() {
		Player player1 = new Player("p1");
		player1.addToken(new Token(9, ""));
		player1.addToken(new Token(20, ""));
		player1.addToken(new Token(11, ""));
		player1.setFormulaCard(new FormulaCard(9));
		player1.setWands(0);
		assertTrue("" + player1.getScore(), player1.getScore() == 100);
	}
	
	@Test public void test03() {
		Player player1 = new Player("p1"); 
		player1.addToken(new Token(25, ""));
		player1.addToken(new Token(21, ""));
		player1.setFormulaCard(new FormulaCard(25));
		player1.setWands(2);
		assertTrue("" + player1.getScore(), player1.getScore() == 72);
	}
	
}
