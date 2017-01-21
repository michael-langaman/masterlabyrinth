package code.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * <h1> Formula Card </h1>
 * Contains the variables and methods for generating a random formula card for each player upon initialization of the game. 
 * <p>
 * <b> Variables </b>
 * <p>
 * {@code ArrayList<Integer>}: _deck - a static ArrayList of type Integer used to randomize the card number.
 * {@code int}: _timesShuffled - a static integer used to ensure that the <code> _deck </code> is only shuffled once.
 * {@code ArrayList<Token>}: _tokens - the card's list of tokens
 * {@code ArrayList<Integer>}: _tokenInts - the token number of each token inside the <code> _tokens </code> list.
 * {@code int}: _cardNumber - the number of the card.
 * {@code String}: _cardName - the name of the card.
 * 
 * @author Michael Langaman
 * @author William Stewart
 */

public class FormulaCard {
	
	private static ArrayList<Integer> _deck = new ArrayList<Integer>();
	private static int _timesShuffled = 0;
	private ArrayList<Token> _tokens;
	private ArrayList<Integer> _tokenInts;
	private int _cardNumber;
	private String _cardName;
	
	/**
	 * Generates a random formula card numbered from 1-21.
	 */
	public FormulaCard() {
		_tokens = new ArrayList<Token>();
		_tokenInts = new ArrayList<Integer>();
		for(int i = 1; i < 22; i++) {
			_deck.add(i);
		}
		if(_timesShuffled == 0) {
			Collections.shuffle(_deck);
			_timesShuffled = 1;
		}

		setCardTokens();
	}
	
	/**
	 * Generates a formula card with the number determined by given parameter. Used for testing purposes.
	 * 
	 * @param number the card number
	 */
	public FormulaCard(int number) {
		_tokens = new ArrayList<Token>();
		_tokenInts = new ArrayList<Integer>();
		if(number == 25) {
			setCardTokens(21);
		} else { setCardTokens(number);	}
	}
	
	/**
	 * Determines the card number and name upon initialization. This method also determines the list of tokens and integers the card contains.
	 * 
	 */
	public void setCardTokens() {
		switch(_deck.get(0)) {
		case 1: 
			_cardNumber = 1;
			_cardName = "Crab Apples";
			_tokens.add(new Token(1, "Crab Apples"));
			_tokens.add(new Token(10, "Skull Moss"));
			_tokens.add(new Token(13, "Toad"));
			_tokenInts.add(1);
			_tokenInts.add(10);
			_tokenInts.add(13);
			_deck.remove(_deck.get(1));
			break;
		case 2: 
			_cardNumber = 2;
			_cardName = "Pine Cones";
			_tokens.add(new Token(2, "Pine Cones"));
			_tokens.add(new Token(8, "Henbane"));
			_tokens.add(new Token(17, "Snake"));
			_tokenInts.add(2);
			_tokenInts.add(8);
			_tokenInts.add(17);
			break;
		case 3:
			_cardNumber = 3; 
			_cardName = "Oak Leaves";
			_tokens.add(new Token(3, "Oak Leaves"));
			_tokens.add(new Token(18, "Emerald"));
			_tokens.add(new Token(1, "Crab Apples"));
			_tokenInts.add(3);
			_tokenInts.add(18);
			_tokenInts.add(1);
			break;
		case 4: 
			_cardNumber = 4;
			_cardName = "Oil of Black Slugs";
			_tokens.add(new Token(4, "Oil of Black Slugs"));
			_tokens.add(new Token(13, "Toad"));
			_tokens.add(new Token(20, "Black Rooster"));
			_tokenInts.add(4);
			_tokenInts.add(13);
			_tokenInts.add(20);
			break;
		case 5:
			_cardNumber = 5;
			_cardName = "Four Leaf Clover";
			_tokens.add(new Token(5, "Four Leaf Clover"));
			_tokens.add(new Token(25, "Berries of Misletoe"));
			_tokens.add(new Token(18, "Emerald"));
			_tokenInts.add(5);
			_tokenInts.add(25);
			_tokenInts.add(18);
			break;
		case 6:
			_cardNumber = 6;
			_cardName = "Garlic";
			_tokens.add(new Token(6, "Garlic"));
			_tokens.add(new Token(14, "Fire Salamander"));
			_tokens.add(new Token(8, "Henbane"));
			_tokenInts.add(6);
			_tokenInts.add(14);
			_tokenInts.add(8);
			break;
		case 7: 
			_cardNumber = 7;
			_cardName = "Raven's Feather";
			_tokens.add(new Token(7, "Raven's Feather"));
			_tokens.add(new Token(6, "Garlic"));
			_tokens.add(new Token(25, "Berries of Misletoe"));
			_tokenInts.add(7);
			_tokenInts.add(6);
			_tokenInts.add(25);
			break;
		case 8:
			_cardNumber = 8;
			_cardName = "Henbane";
			_tokens.add(new Token(8, "Henbane"));
			_tokens.add(new Token(19, "Root of Mandrake"));
			_tokens.add(new Token(5, "Four Leaf Clover"));
			_tokenInts.add(8);
			_tokenInts.add(19);
			_tokenInts.add(5);
			break;
		case 9:
			_cardNumber = 9;
			_cardName = "Spider";
			_tokens.add(new Token(9, "Spider"));
			_tokens.add(new Token(20, "Black Rooster"));
			_tokens.add(new Token(11, "Magic Wand Made of Blindworm"));
			_tokenInts.add(9);
			_tokenInts.add(20);
			_tokenInts.add(11);
			break;
		case 10:
			_cardNumber = 10;
			_cardName = "Skull Moss";
			_tokens.add(new Token(10, "Skull Moss"));
			_tokens.add(new Token(12, "Quartz"));
			_tokens.add(new Token(16, "Silver Thistle"));
			_tokenInts.add(10);
			_tokenInts.add(12);
			_tokenInts.add(16);
			break;
		case 11:
			_cardNumber = 11; 
			_cardName = "Magic Wand Made of Blindworm";
			_tokens.add(new Token(11, "Magic Wand Made of Blindworm"));
			_tokens.add(new Token(3, "Oak Leaves"));
			_tokens.add(new Token(14, "Fire Salamander"));
			_tokenInts.add(11);
			_tokenInts.add(3);
			_tokenInts.add(14);
			break;
		case 12: 
			_cardNumber = 12;
			_cardName = "Quartz";
			_tokens.add(new Token(12, "Quartz"));
			_tokens.add(new Token(1, "Crab Apples"));
			_tokens.add(new Token(9, "Spider"));
			_tokenInts.add(12);
			_tokenInts.add(1);
			_tokenInts.add(9);
			break;
		case 13: 
			_cardNumber = 13;
			_cardName = "Toad";
			_tokens.add(new Token(13, "Toad"));
			_tokens.add(new Token(15, "Weasel Spit"));
			_tokens.add(new Token(12, "Quartz"));
			_tokenInts.add(13);
			_tokenInts.add(15);
			_tokenInts.add(12);
			break;
		case 14:
			_cardNumber = 14;
			_cardName = "Fire Salamander";
			_tokens.add(new Token(14, "Fire Salamander"));
			_tokens.add(new Token(4, "Oil of Black Slugs"));
			_tokens.add(new Token(10, "Skull Moss"));
			_tokenInts.add(14);
			_tokenInts.add(4);
			_tokenInts.add(10);
			break;
		case 15:
			_cardNumber = 15;
			_cardName = "Weasel Spit";
			_tokens.add(new Token(15, "Weasel Spit"));
			_tokens.add(new Token(2, "Pine Cones"));
			_tokens.add(new Token(4, "Oil of Black Slugs"));
			_tokenInts.add(15);
			_tokenInts.add(2);
			_tokenInts.add(4);
			break;
		case 16:
			_cardNumber = 16;
			_cardName = "Silver Thistle";
			_tokens.add(new Token(16, "Silver Thistle"));
			_tokens.add(new Token(9, "Spider"));
			_tokens.add(new Token(7, "Raven's Feather"));
			_tokenInts.add(16);
			_tokenInts.add(9);
			_tokenInts.add(7);
			break;
		case 17:
			_cardNumber = 17;
			_cardName = "Snake";
			_tokens.add(new Token(17, "Snake"));
			_tokens.add(new Token(5, "Four Leaf Clover"));
			_tokens.add(new Token(6, "Garlic"));
			_tokenInts.add(17);
			_tokenInts.add(5);
			_tokenInts.add(6);
			break;
		case 18:
			_cardNumber = 18;
			_cardName = "Emerald";
			_tokens.add(new Token(18, "Emerald"));
			_tokens.add(new Token(11, "Magic Wand Made of Blindworm"));
			_tokens.add(new Token(19, "Root of Mandrake"));
			_tokenInts.add(18);
			_tokenInts.add(11);
			_tokenInts.add(19);
			break;
		case 19:
			_cardNumber = 19;
			_cardName = "Root of Mandrake";
			_tokens.add(new Token(19, "Root of Mandrake"));
			_tokens.add(new Token(7, "Raven's Feather"));
			_tokens.add(new Token(15, "Weasel Spit"));
			_tokenInts.add(19);
			_tokenInts.add(7);
			_tokenInts.add(15);
			break;
		case 20:
			_cardNumber = 20;
			_cardName = "Black Rooster";
			_tokens.add(new Token(20, "Black Rooster"));
			_tokens.add(new Token(17, "Snake"));
			_tokens.add(new Token(3, "Oak Leaves"));
			_tokenInts.add(20);
			_tokenInts.add(17);
			_tokenInts.add(3);
			break;
		case 21:
			_cardNumber = 25;
			_cardName = "Berries of Evergreen";
			_tokens.add(new Token(25, "Berries of Misletoe"));
			_tokens.add(new Token(16, "Silver Thistle"));
			_tokens.add(new Token(2, "Pine Cones"));
			_tokenInts.add(25);
			_tokenInts.add(16);
			_tokenInts.add(2);
			break;
		case 25:
			_cardNumber = 25;
			_cardName = "Berries of Evergreen";
			_tokens.add(new Token(25, "Berries of Misletoe"));
			_tokens.add(new Token(16, "Silver Thistle"));
			_tokens.add(new Token(2, "Pine Cones"));
			_tokenInts.add(25);
			_tokenInts.add(16);
			_tokenInts.add(2);
			break;
		}
		_deck.remove(0);
	}
	
	/**
	 * Determines what number the card is. Used for testing purposes.
	 * 
	 * @param number to set the card number
	 */
	public void setCardTokens(int number) {
		switch(number) {
		case 1: 
			_cardNumber = 1;
			_cardName = "Crab Apples";
			_tokens.add(new Token(1, "Crab Apples"));
			_tokens.add(new Token(10, "Skull Moss"));
			_tokens.add(new Token(13, "Toad"));
			_tokenInts.add(1);
			_tokenInts.add(10);
			_tokenInts.add(13);
			_deck.remove(_deck.get(1));
			break;
		case 2: 
			_cardNumber = 2;
			_cardName = "Pine Cones";
			_tokens.add(new Token(2, "Pine Cones"));
			_tokens.add(new Token(8, "Henbane"));
			_tokens.add(new Token(17, "Snake"));
			_tokenInts.add(2);
			_tokenInts.add(8);
			_tokenInts.add(17);
			break;
		case 3:
			_cardNumber = 3; 
			_cardName = "Oak Leaves";
			_tokens.add(new Token(3, "Oak Leaves"));
			_tokens.add(new Token(18, "Emerald"));
			_tokens.add(new Token(1, "Crab Apples"));
			_tokenInts.add(3);
			_tokenInts.add(18);
			_tokenInts.add(1);
			break;
		case 4: 
			_cardNumber = 4;
			_cardName = "Oil of Black Slugs";
			_tokens.add(new Token(4, "Oil of Black Slugs"));
			_tokens.add(new Token(13, "Toad"));
			_tokens.add(new Token(20, "Black Rooster"));
			_tokenInts.add(4);
			_tokenInts.add(13);
			_tokenInts.add(20);
			break;
		case 5:
			_cardNumber = 5;
			_cardName = "Four Leaf Clover";
			_tokens.add(new Token(5, "Four Leaf Clover"));
			_tokens.add(new Token(25, "Berries of Misletoe"));
			_tokens.add(new Token(18, "Emerald"));
			_tokenInts.add(5);
			_tokenInts.add(25);
			_tokenInts.add(18);
			break;
		case 6:
			_cardNumber = 6;
			_cardName = "Garlic";
			_tokens.add(new Token(6, "Garlic"));
			_tokens.add(new Token(14, "Fire Salamander"));
			_tokens.add(new Token(8, "Henbane"));
			_tokenInts.add(6);
			_tokenInts.add(14);
			_tokenInts.add(8);
			break;
		case 7: 
			_cardNumber = 7;
			_cardName = "Raven's Feather";
			_tokens.add(new Token(7, "Raven's Feather"));
			_tokens.add(new Token(6, "Garlic"));
			_tokens.add(new Token(25, "Berries of Misletoe"));
			_tokenInts.add(7);
			_tokenInts.add(6);
			_tokenInts.add(25);
			break;
		case 8:
			_cardNumber = 8;
			_cardName = "Henbane";
			_tokens.add(new Token(8, "Henbane"));
			_tokens.add(new Token(19, "Root of Mandrake"));
			_tokens.add(new Token(5, "Four Leaf Clover"));
			_tokenInts.add(8);
			_tokenInts.add(19);
			_tokenInts.add(5);
			break;
		case 9:
			_cardNumber = 9;
			_cardName = "Spider";
			_tokens.add(new Token(9, "Spider"));
			_tokens.add(new Token(20, "Black Rooster"));
			_tokens.add(new Token(11, "Magic Wand Made of Blindworm"));
			_tokenInts.add(9);
			_tokenInts.add(20);
			_tokenInts.add(11);
			break;
		case 10:
			_cardNumber = 10;
			_cardName = "Skull Moss";
			_tokens.add(new Token(10, "Skull Moss"));
			_tokens.add(new Token(12, "Quartz"));
			_tokens.add(new Token(16, "Silver Thistle"));
			_tokenInts.add(10);
			_tokenInts.add(12);
			_tokenInts.add(16);
			break;
		case 11:
			_cardNumber = 11; 
			_cardName = "Magic Wand Made of Blindworm";
			_tokens.add(new Token(11, "Magic Wand Made of Blindworm"));
			_tokens.add(new Token(3, "Oak Leaves"));
			_tokens.add(new Token(14, "Fire Salamander"));
			_tokenInts.add(11);
			_tokenInts.add(3);
			_tokenInts.add(14);
			break;
		case 12: 
			_cardNumber = 12;
			_cardName = "Quartz";
			_tokens.add(new Token(12, "Quartz"));
			_tokens.add(new Token(1, "Crab Apples"));
			_tokens.add(new Token(9, "Spider"));
			_tokenInts.add(12);
			_tokenInts.add(1);
			_tokenInts.add(9);
			break;
		case 13: 
			_cardNumber = 13;
			_cardName = "Toad";
			_tokens.add(new Token(13, "Toad"));
			_tokens.add(new Token(15, "Weasel Spit"));
			_tokens.add(new Token(12, "Quartz"));
			_tokenInts.add(13);
			_tokenInts.add(15);
			_tokenInts.add(12);
			break;
		case 14:
			_cardNumber = 14;
			_cardName = "Fire Salamander";
			_tokens.add(new Token(14, "Fire Salamander"));
			_tokens.add(new Token(4, "Oil of Black Slugs"));
			_tokens.add(new Token(10, "Skull Moss"));
			_tokenInts.add(14);
			_tokenInts.add(4);
			_tokenInts.add(10);
			break;
		case 15:
			_cardNumber = 15;
			_cardName = "Weasel Spit";
			_tokens.add(new Token(15, "Weasel Spit"));
			_tokens.add(new Token(2, "Pine Cones"));
			_tokens.add(new Token(4, "Oil of Black Slugs"));
			_tokenInts.add(15);
			_tokenInts.add(2);
			_tokenInts.add(4);
			break;
		case 16:
			_cardNumber = 16;
			_cardName = "Silver Thistle";
			_tokens.add(new Token(16, "Silver Thistle"));
			_tokens.add(new Token(9, "Spider"));
			_tokens.add(new Token(7, "Raven's Feather"));
			_tokenInts.add(16);
			_tokenInts.add(9);
			_tokenInts.add(7);
			break;
		case 17:
			_cardNumber = 17;
			_cardName = "Snake";
			_tokens.add(new Token(17, "Snake"));
			_tokens.add(new Token(5, "Four Leaf Clover"));
			_tokens.add(new Token(6, "Garlic"));
			_tokenInts.add(17);
			_tokenInts.add(5);
			_tokenInts.add(6);
			break;
		case 18:
			_cardNumber = 18;
			_cardName = "Emerald";
			_tokens.add(new Token(18, "Emerald"));
			_tokens.add(new Token(11, "Magic Wand Made of Blindworm"));
			_tokens.add(new Token(19, "Root of Mandrake"));
			_tokenInts.add(18);
			_tokenInts.add(11);
			_tokenInts.add(19);
			break;
		case 19:
			_cardNumber = 19;
			_cardName = "Root of Mandrake";
			_tokens.add(new Token(19, "Root of Mandrake"));
			_tokens.add(new Token(7, "Raven's Feather"));
			_tokens.add(new Token(15, "Weasel Spit"));
			_tokenInts.add(19);
			_tokenInts.add(7);
			_tokenInts.add(15);
			break;
		case 20:
			_cardNumber = 20;
			_cardName = "Black Rooster";
			_tokens.add(new Token(20, "Black Rooster"));
			_tokens.add(new Token(17, "Snake"));
			_tokens.add(new Token(3, "Oak Leaves"));
			_tokenInts.add(20);
			_tokenInts.add(17);
			_tokenInts.add(3);
			break;
		case 21:
			_cardNumber = 25;
			_cardName = "Berries of Evergreen";
			_tokens.add(new Token(25, "Berries of Misletoe"));
			_tokens.add(new Token(16, "Silver Thistle"));
			_tokens.add(new Token(2, "Pine Cones"));
			_tokenInts.add(25);
			_tokenInts.add(16);
			_tokenInts.add(2);
			break;
		case 25:
			_cardNumber = 25;
			_cardName = "Berries of Evergreen";
			_tokens.add(new Token(25, "Berries of Misletoe"));
			_tokens.add(new Token(16, "Silver Thistle"));
			_tokens.add(new Token(2, "Pine Cones"));
			_tokenInts.add(25);
			_tokenInts.add(16);
			_tokenInts.add(2);
			break;
		}
		_deck.remove(new Integer(number));
	}
	
	/**
	 * Returns the instance of the card number
	 * 
	 * @return the card number
	 */
	public int getCardNumber() {
		return _cardNumber;
	}
	
	/**
	 * Sets the card number to given integer parameter
	 * 
	 * @param number 
	 */
	public void setCardNumber(int number) {
		_cardNumber = number;
	}
	
	/**
	 * Returns the instance of the card name
	 * 
	 * @return the name of the card
	 */
	public String getCardName() {
		return _cardName;
	}
	
	/**
	 * Sets the instance of card name to the given parameter
	 * 
	 * @param name for the card
	 */
	public void setCardName(String name) {
		_cardName = name;
	}
	
	/**
	 * Returns the card's list of tokens
	 * 
	 * @return the tokens that the card contains
	 */
	public ArrayList<Token> getCardTokens() {
		return _tokens;
	}
	
	
	/**
	 * Returns token numbers of every token in the card's token list.
	 * 
	 * @return the token numbers that the card contains
	 */
	public ArrayList<Integer> getCardIntegers() {
		return _tokenInts;
	}
	
	/**
	 * Returns whether or not this card's list of integers contains the given integer argument.
	 * 
	 * @param number 
	 * @return <code> true </code> if <code> _tokenInts </code> contains the given integer.
	 * 		   <code> false </code> if <code> _tokenInts </code> does not contain the given integer.
	 */
	public boolean contains(Integer number) {
		return _tokenInts.contains(number);
	}
}
