package tests.savetests;
import code.model.*;
import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert.*;
public class PlayerInfoTests {

	@Test
	public void playerInfoTest01 () {
		SaveFile save = null;
		String s = "testsave.mls";
		ArrayList<Player> p = new ArrayList<Player>();
		ArrayList<Player> p2 = new ArrayList<Player>();
		ArrayList<MoveableTile> tiles = new ArrayList<MoveableTile>();
		boolean g;
		try {
			save = new SaveFile(s);
			p2 = save.getPlayers();
			tiles = save.getTiles();
		}
		catch(FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		catch(IOException e) {
			System.err.println(e.toString());
			System.err.println("Could not find file" + s);
		}
		int f = save.getPlayerNum();
		g = (f==4);
		for (int i = 0; i < 4; i++) {
			p.add(new Player(null));
			switch (i) {
			case 0:
				p.get(0).setName("Tyler");
				p.get(0).setColor("BLACK");
				p.get(0).addToken(new Token(1, Token.getTokenName(1)));
				p.get(0).addToken(new Token(6, Token.getTokenName(6)));
				p.get(0).addToken(new Token(7, Token.getTokenName(7)));
				p.get(0).addToken(new Token(8, Token.getTokenName(8)));
				p.get(0).setFormulaCard(new FormulaCard(7));
				p.get(0).setSecretFormula( p.get(0).getFormulaCard().getCardIntegers());
				p.get(0).setWands(2);
				break;
			case 1:
				p.get(1).setName("Michael");
				p.get(1).setColor("BLUE");
				p.get(1).addToken(new Token(2, Token.getTokenName(2)));
				p.get(1).addToken(new Token(3, Token.getTokenName(3)));
				p.get(1).addToken(new Token(4, Token.getTokenName(4)));
				p.get(1).addToken(new Token(5, Token.getTokenName(5)));
				p.get(1).setFormulaCard(new FormulaCard(17));
				p.get(1).setSecretFormula( p.get(1).getFormulaCard().getCardIntegers());
				p.get(1).setWands(3);
				break;
			case 2:
				p.get(2).setName("Dan");
				p.get(2).setColor("RED");
				p.get(2).addToken(new Token(9, Token.getTokenName(9)));
				p.get(2).addToken(new Token(11, Token.getTokenName(11)));
				p.get(2).setFormulaCard(new FormulaCard(6));
				p.get(2).setSecretFormula( p.get(2).getFormulaCard().getCardIntegers());
				p.get(2).setWands(0);
				break;
			case 3:
				p.get(3).setName("Will");
				p.get(3).setColor("GREEN");
				p.get(3).addToken(new Token(10, Token.getTokenName(10)));
				p.get(3).setFormulaCard(new FormulaCard(19));
				p.get(3).setSecretFormula( p.get(3).getFormulaCard().getCardIntegers());
				p.get(3).setWands(1);
				break;
			default:
				break;
			}
		}
		for(int i = 0; i < 4; i++) {
			System.out.println("i is == " + i);
			assertTrue("", p.get(i).getName().equals(p2.get(i).getName()));
			assertTrue("", p.get(i).getColor().equals(p2.get(i).getColor()));
			assertTrue("", (p.get(i).getWands() == p2.get(i).getWands()));
			//assertTrue("", p.get(i).getFormulaCard().equals(p2.get(i).getFormulaCard()));
			//assertTrue("Expected:  "+p.get(i).getSecretFormula().toString() + "--Actual:  "+p2.get(i).getSecretFormula().toString() , p.get(i).getSecretFormula().equals(p2.get(i).getSecretFormula()));
			//assertTrue("", p.get(i).getTokens().equals(p2.get(i).getTokens()));
			System.out.println(p.get(i).getTokens().toString());
			System.out.println(p2.get(i).getTokens().toString());
			System.out.println(p.get(i).getSecretFormula().toString());
			System.out.println(p2.get(i).getSecretFormula().toString());
			assertTrue("",p.get(i).getSecretFormula().toString().equals(p2.get(i).getSecretFormula().toString()));
			assertTrue("", p.get(i).getTokens().toString().equals(p2.get(i).getTokens().toString()));
		}
		assertTrue("", g);
	}
}
