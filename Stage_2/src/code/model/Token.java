package code.model;


	/**
	 * This token class contains instance variables and methods for Token
	 * @author Satya, Josh, Ian 03-16-16
	 * @author Weijin, Ian 03-18-16
	 * @author Will, Tyler
	 */
	public class Token {
		
		/**
		 * int value of this token
		 */
		private int _value;
		
		/**
		 * String name of this token
		 */
		private String _name;
		
		/**
		 * true if already picked up by a player, false otherwise
		 */
		private boolean _pickedUp;
		
		/**
		 * The player that has picked up this token (null if none)
		 */
		private Player _myPlayer;
		
		/**
		 * The tile on which this token currently resides (null if none)
		 */
		private AbstractTile _myTile;
		
		/**
		 * @author Satya, Ken (April 3rd,2016) (2:48pm)
		 */
	
		public Token(int v, String name){
			_value = v;
			_name = name;
			_myPlayer = null;
			_myTile = null;
			_pickedUp = false;
		}
		
		/**
		 * Returns this token's number
		 * 
		 * @return tokens' value
		 * @author Weijin, Ian 03-18-16
		 */
		public int getValue(){
			 return _value;
		}
		
		/**
		 * Returns this card's token name
		 * 
		 * @return the token's name
		 * @author Weijin, Ian 03-18-16
		 */
		public String getName(){
			return _name;
		}
		
		/**
		 * This method sets the token on the tile in the given parameter
		 * @param aT the tile the token is to be set on
		 * @author Weijin, Ian 03-18-16
		 */
		public void setTile(AbstractTile aT){
			_myTile = aT;
		}
		
		/**
		 * This method returns the tile on which this token resides
		 * @return the tile on which this token resides
		 * @author Weijin, Ian 03-18-16
		 */
		public AbstractTile getTile(){
			return _myTile;
		}
		
		/**
		 * This method sets the player who has picked up this token
		 * @param p Player who has picked up token
		 * @author Weijin, Ian 03-18-16
		 */
		public void setPlayer(Player p){
			_myPlayer = p;
		}
		
		/**
		 * This method returns the player who holds this token
		 * @return the Player who holds/owns this token
		 * @author Weijin, Ian 03-18-16
		 */
		public Player getPlayer(){
			return _myPlayer;
		}
		
		
		/**
		 * Sets the token's boolean <code> _pickedUp </code> to true.
		 */
		public void setTokenPickedUpStatus() {
			_pickedUp = true;
		}
		
		/**
		 * this method returns whether or not the token has been picked up
		 * @return <code> true </code> if the token has been picked up 
		 * 		   <code> false </code> if the token has not been picked up
		 */
		public boolean getTokenPickedUpStatus(){
			return _pickedUp;
		}
		
		/**
		 * Sets the token's name dependent upon the number in the parameter
		 * 
		 * @param n the number of the token
		 */
		public void setName(int n) {
			switch(n) {
			case 1:
				_name = "Crab Apples";
				break;
			case 2:
				_name = "Pine Cones";
				break;
			case 3:
				_name = "Oak Leaves";
				break;
			case 4:
				_name = "Oil of Black Slugs";
				break;
			case 5:
				_name = "Four Leaf Clover";
				break;
			case 6:
				_name = "Garlic";
				break;
			case 7:
				_name = "Raven's Feather";
				break;
			case 8:
				_name = "Henbane";
				break;
			case 9:
				_name = "Spider";
				break;
			case 10:
				_name = "Skull Moss";
				break;
			case 11:
				_name = "Magic Wand Made of Blindworm";
				break;
			case 12:
				_name = "Quartz";
				break;
			case 13:
				_name = "Toad";
				break;
			case 14:
				_name = "Fire Salamander";
				break;
			case 15:
				_name = "Weasel Spit";
				break;
			case 16:
				_name = "Silver Thistle";
				break;
			case 17:
				_name = "Snake";
				break;
			case 18:
				_name = "Emerald";
				break;
			case 19:
				_name = "Root of Mandrake";
				break;
			case 20:
				_name = "Black Rooster";
				break;
			case 25:
				_name = "Berries of Evergreen";
				break;
				
			}
		}
		
		/**
		 * Returns the token's name
		 * 
		 * @param n the number of the token
		 * @return the token name
		 */
		public static String getTokenName (int n) {
			String cardname = null;
			switch(n) {
			case 1:
				cardname = "Crab Apples";
				break;
			case 2:
				cardname = "Pine Cones";
				break;
			case 3:
				cardname = "Oak Leaves";
				break;
			case 4:
				cardname = "Oil of Black Slugs";
				break;
			case 5:
				cardname = "Four Leaf Clover";
				break;
			case 6:
				cardname = "Garlic";
				break;
			case 7:
				cardname = "Raven's Feather";
				break;
			case 8:
				cardname = "Henbane";
				break;
			case 9:
				cardname = "Spider";
				break;
			case 10:
				cardname = "Skull Moss";
				break;
			case 11:
				cardname = "Magic Wand Made of Blindworm";
				break;
			case 12:
				cardname = "Quartz";
				break;
			case 13:
				cardname = "Toad";
				break;
			case 14:
				cardname = "Fire Salamander";
				break;
			case 15:
				cardname = "Weasel Spit";
				break;
			case 16:
				cardname = "Silver Thistle";
				break;
			case 17:
				cardname = "Snake";
				break;
			case 18:
				cardname = "Emerald";
				break;
			case 19:
				cardname = "Root of Mandrake";
				break;
			case 20:
				cardname = "Black Rooster";
				break;
			case 25:
				cardname = "Berries of Evergreen";
				break;
				
		}
			return cardname;
	}
		
		/**
		 * Returns a string version of the integer value of the token.
		 * @return - returns a string version of the value fr token.
		 */
		@Override
		public String toString() {
			return (""+_value);
		}
}