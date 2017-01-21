package tests.savetests;
import code.model.*;
import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert.*;

public class TileInfoTests {

	@Test
	public void TileInfoTests01() {
		String s = "testsave.mls";
		SaveFile save = null;
		try {
			save = new SaveFile(s);
			//ArrayList<Player> p2 = save.getPlayers();
			//ArrayList<MoveableTile> tiles = save.getTiles();
			
			//.out.println(tiles.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		GameBoard gb = new GameBoard(save);
		String boardstate = gb.printBoardState();
		String lastshift = gb.printLastShift();
		String players = gb.printPlayerRecords();
		String expect1 = "[Tyler,BLACK,2,[7,6,25],[1,6,7,8]],[Michael,BLUE,3,[17,5,6],[2,3,4,5]],[Dan,RED,0,[6,14,8],[9,11]],[Will,GREEN,1,[19,7,15],[10]]";
		String expect2 = "[L0,0,[]],[I1,0,[]],[T0,0,[]],[L1,0,[]],[T0,0,[]],[L3,0,[]],[L1,0,[]],[T2,0,[]],[I1,0,[BLUE]],[L2,0,[]],[I0,0,[]],[I1,0,[]],[T2,0,[]],[L2,0,[]],[T3,0,[]],[I0,16,[]],[T3,0,[]],[T2,17,[]],[T0,0,[RED]],[L3,18,[]],[T1,0,[]],[L2,0,[]],[I0,0,[]],[I0,0,[]],[I1,0,[]],[L3,0,[]],[L1,0,[]],[L2,0,[]],[T3,0,[]],[L2,19,[]],[T2,0,[GREEN]],[L3,20,[]],[T1,0,[BLACK]],[T1,25,[]],[T1,0,[]],[I0,0,[]],[I1,12,[]],[T2,13,[]],[I0,14,[]],[L1,15,[]],[I0,0,[]],[L3,0,[]],[L3,0,[]],[I1,0,[]],[T2,0,[]],[L3,0,[]],[T2,0,[]],[T1,0,[]],[L2,0,[]]";
		String expect3 = "10";
		System.out.println("\n\nPRINTING THE ACTUAL STUFF\n\n");
		System.out.println(boardstate + "\n" + expect2);
		System.out.println(lastshift + "\n" + expect3);
		System.out.println(players + "\n" + expect1);
		//System.out.println(players);
		assertTrue("", boardstate.equals(expect2));
		assertTrue("", players.equals(expect1));
		assertTrue("", lastshift.equals(expect3));

	}
}