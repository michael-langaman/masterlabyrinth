package code.model;

import java.util.ArrayList;

import code.model.Token;

/**
 * The player class holds common instance variables and methods that create the player
 * and define the player's functionality
 * 
 * @author Weijin,Ken,Ian (3-17-16)
 * @author Ian,Ken 04-10-16
 * @author Dan Palacio
 * @author Michael Langaman
 *
 */
public class Player {  
	
	/**
	 * static final int value that stores the maximum number of players during a game.
	 * It is final because the max# can not change
	 */
	public static final int maxNumberOfPlayers = 4;
	
	/**
	 * a String array holding the valid colors for the game
	 */
	public static final String[] _validColors = new String[] {"BLACK","BLUE","RED","GREEN"};
	
	/**
	 * true if player has shifted this turn; false otherwise
	 */
	private boolean _hasInsertedThisTurn;
	
	/**
	 * true if player has moved this turn; false otherwise
	 */
	private boolean _hasMovedThisTurn;
	
	/**
	 * A boolean representing whether or not this player has used a wand during the current turn
	 */
	private boolean _wandUsedThisTurn;
	
	/**
	 * String holding the current color of the player
	 */
	private String _color;
	
	/**
	 * reference to the AbstractTile on which this player currently resides
	 */
	private AbstractTile _currentTile;
	
	/**
	 * reference to the gameboard object with which this player is associated
	 */
	private GameBoard _gb;
	
	/**
	 * ArrayList of Tokens; the tokens that this player has collected
	 */
	private ArrayList<Token> _myTokens;
	
	/**
	 * Name of the human who controls this player/pawn
	 */
	private String _playerName;
	
	/**
	 * Current score of the player
	 */
	private int _score;
	
	/**
	 * The number of wands this player currently has
	 */
	private int _wands;
	
	/**
	 * A list of integers representing the tokens contained in this player's secret formula card
	 */
	private ArrayList<Integer> _secretFormula;
	
	/**
	 * Appears unused; from the code base candidate's original code
	 */
	private Token _toke;
	
	/**
	 * A reference to this player's secret formula card
	 */
	private FormulaCard _formulaCard;
	
	/**
	 * The constructor Player assigns the instance variable _color to the String c
	 * 
	 * @param c is the color of the player
	 * @author Weijin,Ken,Ian 
	 */
	public Player(String c){
		_color = c;		
		_myTokens = new ArrayList<Token>();
		_secretFormula = new ArrayList<Integer>();
		_score = 0;
		_wands = 3;
		_formulaCard = new FormulaCard();
	}

	/**
	 * This method assigns the value gb, an reference to the Gameboard object, to the 
	 * instance variable _gb.
	 * This allows for accessability of Gameboard public methods in the Player class
	 * so the players can call methods on the gameboard
	 * @param gb a refeence to the gameboard
	 * @author Weijin, Ken, Ian
	 */
	public void setGameBoard(GameBoard gb){
		_gb = gb;
	}
	
	/**
	 * This method is a getter that returns the color
	 * 
	 * @return _color the color as a String of the player
	 * @author Weijin, Ken, Ian
	 */
	public String getColor(){
		return _color;
	}
	
	/**
	 * This method sets the current tile
	 * 
	 * @param at is a tile that is set as the current tile
	 * @author Weijin, Ken, Ian
	 * @author Ian, Satya 04-10-16
	 */
	public void setCurrentTile(AbstractTile at){
		_currentTile = at;
	}
	
	/**
	 * This method is a getter that returns the current tile
	 * 
	 * @return _currentTile the current tile on which this player resides
	 * @author Weijin, Ken, Ian
	 */
	public AbstractTile getCurrentTile(){
		return _currentTile;
	}
	
	/**
	 * This method tells you if you can insert a tile.
	 * 
	 * @param insertShiftableTileAtTileNumberPosition this is the tile that is going to be inserted.
	 * @return _gb.checkIfInsertShiftableTileLegal(insertionTile); this returns a 
	 * a true or false value if the tile can be inserted into the board
	 * @author Weijin, Ken, Ian
	 * @author Ian, Ken 04-10-16
	 */
	public boolean insertShiftableTile(int insertShiftableTileAtTileNumberPosition){
		boolean canInsert = false;
		if(!_hasInsertedThisTurn){
			canInsert = _gb.checkIfInsertShiftableTileLegal(insertShiftableTileAtTileNumberPosition);
				if(canInsert){
					_hasInsertedThisTurn = true;
				}
		}
		return canInsert;
	}
	
	/**
	 * This method moves a player to the destination the player wants to go 
	 * It checks to see if the player can move to the destination then moves the player,but if it can't it will say that you can't move
	 * It finally informs the gameboard that it has moved, which in turn alerts the GameBoardGUI
	 * to update.
	 * 
	 * @param destinationTileNum This is the tile number of the destination tile that the player wants to move to
	 * @return canMove which is an if statement that checks if a player can move to the destination tile
	 * if it can it moves the tile and returns a statement that informs the player that his/her pawn has moved, like wise
	 * if it can't move it returns a statement that informs the player that his/her pawn can't be moved to that location
	 * @author Ken, Ian
	 * @author Ian, Satya 04-10-16
	 * @author Ian,Ken 04-10-16
	 */
	public boolean moveToTile(int destinationTileNum){
		
		boolean canMove = false;
		if(!_hasMovedThisTurn){
			AbstractTile destinationTile = _gb.getTileFromTileNumber(destinationTileNum);
			canMove = _gb.checkIfMoveLegal(_currentTile, destinationTile);
			if(canMove){
				_currentTile.removePlayer(this);
	//			_currentTile = destinationTile;
				setCurrentTile(destinationTile);
				_currentTile.addPlayer(this);
				_hasMovedThisTurn = true;
				System.out.println("I can move to tileNum: " + destinationTileNum + " and have thus moved.");
			}
			else{System.out.println("I can't move to tileNum: " + destinationTileNum + " and have not moved.");
			}
			if(canMove){_gb.playerHasAlteredBoard();}
		}
		return canMove;
	}
	
	/**
	 * This method rotates the shiftable tile, which is the only remaining tile in the
	 * MoveableTile Array after populating the game board
	 * @param d the degree of rotation desired by the player
	 * @return The definition of the shiftable tile, is that it is the only remaining
	 * element of the MoveableTile Array. The player is always free to rotate this tile
	 * as long as it is shiftable; thus we return true.
	 * @author Ken, Satya
	 */
	public boolean rotateShiftableTile(int d){
		MoveableTile shiftable = _gb.getMoveableTileArray().get(0);
		shiftable.rotate(d);
		return true;
	}
	
	/**
	 * This method add the newly picked up Token t to the player's _myTokens ArrayList
	 * @param t the token picked up
	 * @author Weijin, Ian 03-18-16
	 * @author Ian, Satya 04-10-16
	 */
	public boolean pickUpToken(Token t){
		
		if(!_hasMovedThisTurn){
			String s = "\t\t\t\tGAME INFO\n\nIt is now " + GameBoard.CURRENTPLAYER.getName() +
					"'s (" + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."+
					"\nCurrent Collectible Token Number: " + _gb.getCurrentTargetTokenValue()
					+"\n\nYou must first move before picking up a token! Try again!";
			_gb.updateGameFeedBack(s);
		}
		
		else if(_currentTile != t.getTile()){
			System.out.println(_currentTile==t.getTile());
			String s = "\t\t\t\tGAME INFO\n\nIt is now " + GameBoard.CURRENTPLAYER.getName() +
					"'s (" + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."+
					"\nCurrent Collectible Token Number: " + _gb.getCurrentTargetTokenValue()
					+"\n\nThat token is not on your current tile! Try again!";
			_gb.updateGameFeedBack(s);
		}
		
		else if(_gb.getCurrentTargetToken() != t){
			String s = "\t\t\t\tGAME INFO\n\nIt is now " + GameBoard.CURRENTPLAYER.getName() +
					"'s (" + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."+
					"\nCurrent Collectible Token Number: " + _gb.getCurrentTargetTokenValue()
					+"\n\nThis token is not the current collectible token! Try again!";
			_gb.updateGameFeedBack(s);
		}
		
		if(_hasMovedThisTurn && _gb.getCurrentTargetToken() == t 
				&& _currentTile == t.getTile()){
			System.out.println("It is " + (_currentTile == t.getTile()) + " that my tile is the same as the token's");
			_myTokens.add(t);
			System.out.println(t.getValue() + " added to myTokens!");
			AbstractTile at = t.getTile();
			at.removeToken();
			//t.setTile(null);
			_score = _score + t.getValue();
			_gb.toggleNextToken();
			_gb.playerHasAlteredBoard();
			
			if(t.getValue() != 25){
				String s = "\t\t\t\tGAME INFO\n\nIt is now " + GameBoard.CURRENTPLAYER.getName() +
			
					"'s (" + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."+
					"\nCurrent Collectible Token Number: " + _gb.getCurrentTargetTokenValue();
			_gb.updateGameFeedBack(s);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * This method sets players' names
	 * @param name 
	 * @author Ian, Weijin
	 */
	public void setName(String name){
		_playerName = name;
	}
	
	/**
	 * This method gets players' names
	 * @return players' names
	 * @author Ian, Weijin
	 */
	public String getName(){
		return _playerName;
	}
	

	/**
	 * This method returns the player's tokens that he/she picked up 
	 * @return toekns of the player

	 * @author satya, Josh 04-15-16
	 */
	public void setScore(int score){
		_score = score;
	}
	
	/**
	 * Returns the amount of tokens the current player has.
	 * @return my tokens
	 * @author Ian, Weijin
	 */
	public ArrayList<Token> getTokens(){
		return _myTokens;
	}
	
	/**
	 * Sets an arraylist of tokens for the player currently possessing.
	 * @author satya, Josh
	 */
	public void setTokens(ArrayList<Token> tokens){
		_myTokens = tokens;
	}
	
	/**
	 * This method makes sure a player has inserted the tile 
	 * @return boolean _hasInsertedThisTurn
	 * @author Ian, Ken 04-10-16
	 */
	public boolean getHasInsertedThisTurn(){
		return _hasInsertedThisTurn;
	}
	
	/**
	 *This method makes sure a player has moved
	 * @return boolean _hasMovedThisTurn;
	 * @author Ian, Ken 04-10-16
	 */
	public boolean getHasMovedThisTurn(){
		return _hasMovedThisTurn;
	}
	
	/**
	 * This method sets boolean _hasInsertedThisTurn and _hasMovedThisTurn to false
	 * @author Ian,Ken 04-10-16
	 */
	public void endMyTurn(){
		_hasInsertedThisTurn = false;
		_hasMovedThisTurn = false;
		_wandUsedThisTurn = false;
	}
	
	/**
	 * This method adds tokens to the player's tokens list
	 * @param t tokens
	 */
	public void addToken(Token t){
		_myTokens.add(t);
	}
	
	/**
	 * sets the number of wands a player will have.
	 * @param w - number of wands the player has.
	 * @author Tyler Barrett
	 * @author Fen Qin
	 */
	public void setWands(int w){
		_wands = w;
	}
	
	/**
	 * A void method that is called upon a player to use it's abilities 
	 * @author Tyler Barrett 
	 * @author Fen Qin
	 */
	public void useWand() {
		if (_wands > 0) {
			_wands--;
			_wandUsedThisTurn = true;
		}
	}
	
	/**
	 * Returns the number of wands (allows player to take another turn) the player has remaining.
	 * @return number of wands that the player has remaining
	 * @author Tyler Barrett
	 * @author Fen Qin
	 */
	public int getWands() {
		return _wands;
	}
	
	/**
	 * Returns a boolean type based on whether the player used the wands abilities 
	 * or not.
	 * @return _wandUsedThisTurn - A boolean value of the current status of 
	 * the wands ability usage.
	 */
	public boolean getWandUsedThisTurn(){
		return _wandUsedThisTurn;
	}
	
	/**
	 * set the player's unique secret formula card
	 * @param formula - arraylist containing the tokens of a secret formula
	 */
	public void setSecretFormula(ArrayList<Integer> formula) {
		_secretFormula = formula;
	}
	
	/**
	 * Returns the ArrayList<Integer> that contains the secret formula tokens for a player
	 * @return - ArrayList<Integer> containing the secret formula tokens for a player instance
	 */
	public ArrayList<Integer> getSecretFormula(){ 
		return _secretFormula;
	}

	/**
	 * Returns the integer value of the total score for the player,
	 *  which is made up of _myTokens, _formulaCard, and _wands.
	 * 
	 * @return - Integer value x - Integer x consists of the values of all tokens, wands, and formula cards. 
	 * @author Dan Palacio
	 * @author Michael Langaman
	 */
	public int getScore(){
		int x = 0;
		for(int i = 0; i < _myTokens.size(); i++){
			
			x = x +_myTokens.get(i).getValue();
			
			if(_formulaCard.contains(_myTokens.get(i).getValue())) {
				x = x + 20;
			}
		}
		x = x + (_wands * 3);
		return x;
		
	}
	
	/**
	 * Returns a formula card that consists of three different tokens contained in an array, that would eventually
	 * be compared with the tokens currently obtained by the player.
	 * 
	 * @return FormulaCard - Which is an array with tokens to be compared later with the tokens of the player.
	 * @author Dan Palacio
	 * @author Michael Langaman
	 */
	public FormulaCard getFormulaCard(){
		return _formulaCard;	
	}
	
	/**
	 * Sets the current Formula card to the Formula card obtained within the parameter. 
	 * @param card - The formula card the would with a given array of tokens.
	 * @author Michael Langaman
	 * @author Dan Palacio
	 */
	public void setFormulaCard(FormulaCard card) {
		_formulaCard = card;
	}
	
	/**
	 * Associates _color with the string within the parameter(String s) to set 
	 * the refereed color of the player.
	 * @param s - The string you're trying to set the color to.
	 * @author Will Stewart
	 * @author Tyler Barrett
	 */
	public void setColor(String s) {
		_color = s;
	}
}
