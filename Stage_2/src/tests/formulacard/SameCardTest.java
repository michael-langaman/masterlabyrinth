package tests.formulacard;

import static org.junit.Assert.*;

import org.junit.Test;

import code.model.FormulaCard;
import code.model.Player;

public class SameCardTest {
	
	private void sameCardTest(Player player1, Player player2) {
		String playerOneName = player1.getFormulaCard().getCardName();
		String playerTwoName = player2.getFormulaCard().getCardName();
		int playerOneCardNum = player1.getFormulaCard().getCardNumber();
		int playerTwoCardNum = player2.getFormulaCard().getCardNumber();
		// System.out.println(FormulaCard._deck);
		assertTrue("Player 1's Card: " + playerOneName + " / Number: " + playerOneCardNum + " Player 2's Card and Number: " + playerTwoName + " / Number: " + playerTwoCardNum, playerOneName != playerTwoName && playerOneCardNum != playerTwoCardNum);
	}
	
	@Test public void test01() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test02() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test03() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test04() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test05() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test06() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test07() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test08() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test09() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test10() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test11() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test12() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test13() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test14() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test15() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test16() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test17() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test18() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test19() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test20() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test21() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test22() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test23() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test24() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test25() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test26() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test27() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test28() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test29() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test30() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test31() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test32() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test33() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test34() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test35() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test36() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test37() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test38() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test39() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test40() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test41() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test42() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test43() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test44() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test45() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test46() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test47() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test48() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test49() { sameCardTest(new Player("p1"), new Player("p2")); }
	@Test public void test50() { sameCardTest(new Player("p1"), new Player("p2")); }
}
