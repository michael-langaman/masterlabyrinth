package code.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import code.model.*;

/**
 * This class implements token button with ActionListener.
 * Implements the ability for the user to click on a token in the gui and attempt to collect it.
 * 
 * @author Ian,Satya 04-15-16
 *
 */
public class TokenButtonHandler implements ActionListener {
	
	/**
	 * refers to the row the token button is currently in
	 */
	private int _row;
	
	/**
	 * refers to the column the token button is currently in
	 */
	private int _col;
	
	/**
	 * refers to the GameBoard that the handler will be associated with
	 */
	private GameBoard _gb;
	
	/**
	 * refers to the JButton on the GameBoard that the handler will be associated with
	 */
	private JButton _jb;
	
	/**
	 * This method takes in the the value of row and column
	 * and reference to the gameboard and jbutton and sets the row and column to the button.
	 * In effect, it is setting the board location that the token is on top of.
	 * @param r the row to be set to the token
	 * @param c the column to be set to the token
	 * @param gb the GameBoard object
	 * @param jb the JButton - to make it clickable
	 * @author Ian,Satya 04-15-16
	 */
	public TokenButtonHandler(int r, int c, GameBoard gb, JButton jb){
		_row = r;
		_col = c;
		_gb = gb;
		_jb = jb;
	}
	
	/**
	 * This method calculates the Jbutton that is clicked using row and column and then lets the player to
	 * pick up the token on that tile.
	 * @param e - unused, required by ActionListener
	 * @author Ian,Satya 04-15-16
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int tokenTileNum = _row*7 +_col;
		AbstractTile at = _gb.getTileFromTileNumber(tokenTileNum);
		Token t = at.getToken();
		GameBoard.CURRENTPLAYER.pickUpToken(t);
	}
}
