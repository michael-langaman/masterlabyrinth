package tests.formulacard;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import code.model.FormulaCard;
import code.model.Player;
import code.model.Token;

public class SetAndGetCardTests {
	
	private Player _player1;
	private FormulaCard _fc;
	
	private void commonTestCode() {
		_player1 = new Player("");
		_fc = new FormulaCard(1);
		_player1.setFormulaCard(_fc);
		assertTrue("", _player1.getFormulaCard().getCardNumber() == 1);
	}
	
	@Test public void getCardTest() {
		commonTestCode();
		assertTrue("", _player1.getFormulaCard() == _fc);
	}
	
	@Test public void getCardNameTest() {
		commonTestCode();
		assertTrue("", _player1.getFormulaCard().getCardName().equals("Crab Apples"));
	}
	
	@Test public void setCardNameTest() {
		commonTestCode();
		_player1.getFormulaCard().setCardName("Black Rooster");
		assertTrue("", _player1.getFormulaCard().getCardName().equals("Black Rooster"));
	}
	
	@Test public void getCardNumberTest() {
		commonTestCode();
		assertTrue("", _player1.getFormulaCard().getCardNumber() == 1);
	}
	
	@Test public void getCardIntegersTest() {
		commonTestCode();
		ArrayList<Integer> tokenList = new ArrayList<Integer>();
		tokenList.add(1);
		tokenList.add(10);
		tokenList.add(13);
		assertTrue("", _player1.getFormulaCard().getCardIntegers().equals(tokenList));
	}

	
	@Test public void containsTest() {
		commonTestCode();
		assertTrue("", _player1.getFormulaCard().contains(1));
		assertTrue("", _player1.getFormulaCard().contains(10));
		assertTrue("", _player1.getFormulaCard().contains(13));	
	}

}
