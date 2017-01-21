package tests.player;

import code.model.GameBoard;
import code.model.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class GetColorTests {

	@Test public void test00(){
		GameBoard gb = new GameBoard(4);
		gb.setupRandomBoard();
		Player p = gb.getBoard()[2][2].getPlayers().get(0);
		String actual = p.getColor();
		String expected = "BLACK";
		assertTrue("", expected.equals(actual));
	}
	
	@Test public void test01(){
		GameBoard gb = new GameBoard(4);
		gb.setupRandomBoard();
		Player p = gb.getBoard()[2][4].getPlayers().get(0);
		String actual = p.getColor();
		String expected = "BLUE";
		assertTrue("" + p.getColor(), expected.equals(actual));
	}
	
	@Test public void test02(){
		GameBoard gb = new GameBoard(4);
		gb.setupRandomBoard();
		Player p = gb.getBoard()[4][2].getPlayers().get(0);
		String actual = p.getColor();
		String expected = "RED";
		assertTrue("", expected.equals(actual));
	}
	
	@Test public void test03(){
		GameBoard gb = new GameBoard(4);
		gb.setupRandomBoard();
		Player p = gb.getBoard()[4][4].getPlayers().get(0);
		String actual = p.getColor();
		String expected = "GREEN";
		assertTrue("", expected.equals(actual));
	}
	
}
