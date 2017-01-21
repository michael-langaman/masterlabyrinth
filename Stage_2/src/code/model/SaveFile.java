package code.model;

import java.io.*;
import java.util.*;

/**
 * <h1>SaveFile</h1>
 * Contains the variables and methods necessary to load the game from a save file. This includes 
 * parsing information from the Strings printed in a .mls file and reconstructing the players,
 * tiles, tokens, and other state information (such as illegal shifting) specified by these details.
 * 
 * @author Fen Qin, William Stewart, Tyler Barrett
 */
public class SaveFile {
	
	/**
	 * A reference to the original File object which represents the .mls save
	 */
	private File _save;
	
	/**
	 * The highest-value token collected up to the point in time specified by the save file
	 * (used for determining the next target token for players to pick up)
	 */
	private int maxToken;
	
	/**
	 * References that will hold the Strings parsed from lines 1, 2, and 3 from the _save File object
	 */
	private String line1, line2, line3;
	
	/**
	 * The total number of players in the game being loaded
	 */
	private int _numPlayers;
	
	/**
	 * A list of tiles being reconstructed from the save file information
	 * (to be placed on the game board after loading).
	 */
	private ArrayList<MoveableTile> _tiles;
	
	/**
	 * A list of tokens being reconstructed from the save file information
	 */
	private ArrayList<Token> _tokens;
	
	/**
	 * A list of information pertaining to the tile currently being loaded
	 * (cleared and re-used while constructing each tile), including the
	 * tile's type, its token value, and any players that are on it (listed
	 * by color), respectively
	 */
	private ArrayList<String> _tileinfos;
	
	/**
	 * A list of information pertaining to the player currently being loaded
	 * (cleared and re-used while constructing each player), including the
	 * player's name, its color, its 3 formula card token values, and any
	 * tokens the player has collected, respectively
	 */
	private ArrayList<String> _playerinfos;
	
	/**
	 * A list of all players to be placed on the gameboard after loading
	 */
	private ArrayList<Player> _players;
	
	/**
	 * Counters for the total number of each tile type loaded onto the board,
	 * used for inferring the free tile
	 */
	private int T, L, I;

	/**
	 * A constructor which initializes all instance variables to default values
	 * and establishes a Scanner object to parse the _save File object.
	 * The _tokens ArrayList is initialized with 21 null values so that tokens
	 * can be set at particular indices as they are loaded, rather than simply added (since they are not
	 * loaded in numerical order, but must be placed in the list by numerical order).
	 * @param filename the name of the file to be loaded from
	 * @throws IOException if a file cannot be accessed; this is caught by calling code in the Driver class
	 */
	public SaveFile(String filename) throws IOException {
		line1 = "";
		line2 = "";
		line3 = "";
		T = 0;
		L = 0;
		I = 0;
		maxToken = 0;
		_numPlayers = 0;
		_playerinfos = new ArrayList<String>();
		_players = new ArrayList<Player>();
		_tiles = new ArrayList<MoveableTile>();
		_tileinfos = new ArrayList<String>();
		_tokens = new ArrayList<Token>();
		for (int i = 1; i <= 21; i++) {
			_tokens.add(null);
		}
		System.out.println(_tokens.toString());
		_save = new File(filename);
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(_save)));
			while (s.hasNext()) {
				line1 = s.next();
				line2 = s.next();
				line3 = s.next();
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

	/**
	 * Counts the number of players to be loaded by reading the first line of text
	 * that came from the save file.
	 * @return the number of players to be loaded
	 */
	public int getPlayerNum() {
		int i = 0;
		_numPlayers = 0;
		for (i = 0; i < line1.length() - 1; i++) {
			if (line1.charAt(i) == ']') {
				if (line1.charAt(i + 1) == ']') {
					_numPlayers++;
				}
			}
		}

		return _numPlayers;
	}

	/**
	 * Parses the information contained in the first line of text
	 * from the save file, creates a new Player object for each
	 * player to be loaded, and sets all relevant parameters for that
	 * Player (such as its name, color, formula card, and token list)
	 * as read from the text.
	 * @return an ArrayList of Players to be placed on the gameboard
	 */
	public ArrayList<Player> getPlayers() {
		int num = getPlayerNum();
		String[] players = line1.split("]],");
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i]);
		}
		for (int i = 0; i < num; i++) {
			_playerinfos.clear();
			for (String s : players[i].split(",")) {
				_playerinfos.add(s.replaceAll("[\\[\\]]", ""));
			}
			for (String s : _playerinfos) {
				System.out.println(s);
			}
			_players.add(new Player(_playerinfos.get(0)));
			// if (_players.get(i).getSecretFormula().isEmpty()) {
			// System.out.println("Shit's empty!");
			// }
			_players.get(i).setName(_playerinfos.get(0));
			_players.get(i).setColor(_playerinfos.get(1));
			_players.get(i).setWands(Integer.parseInt(_playerinfos.get(2)));
			_players.get(i).setFormulaCard(new FormulaCard((Integer.parseInt(_playerinfos.get(3)))));
			_players.get(i).setSecretFormula(_players.get(i).getFormulaCard().getCardIntegers());
//			if (_players.get(i).getSecretFormula().isEmpty()) {
//				System.out.println("it's empty!");
//			}
			if (!(_playerinfos.get(6).equals(""))){
				if (_playerinfos.size() >= 7) {
					for (int j = 6; j < _playerinfos.size(); j++) {
						System.out.println("The current value of i is::" + i);
						System.out.println("_playerinfos.get(j) is::" + Integer.parseInt(_playerinfos.get(j)));
						if (Integer.parseInt(_playerinfos.get(j)) != 25) {
							_tokens.set(Integer.parseInt(_playerinfos.get(j))-1, new Token(Integer.parseInt(_playerinfos.get(j)), Token.getTokenName(Integer.parseInt(_playerinfos.get(j)))));
							System.out.println(_tokens.toString());
						} else {
							_tokens.set(20, new Token(Integer.parseInt(_playerinfos.get(j)), Token.getTokenName(Integer.parseInt(_playerinfos.get(j)))));
							System.out.println(_tokens.toString());
						}
						_players.get(i).addToken(_tokens.get(Integer.parseInt(_playerinfos.get(j))-1));
						if (Integer.parseInt(_playerinfos.get(j)) > maxToken) {
							maxToken = Integer.parseInt(_playerinfos.get(j));
							System.out.println("maxToken: " + maxToken);
						}
						
					}
				}
			}
		}

		System.out.println("\n\nNOW PRINTING INFO IN EACH PLAYER\n\n");

		for (Player p : _players) {
			System.out.println(p.getName());
			System.out.println(p.getColor());
			System.out.println(p.getWands());
			// if (p.getSecretFormula().isEmpty()) {
			// System.out.println("Shit's empty!");
			// }
			System.out.println(p.getTokens().toString());
			System.out.println(p.getSecretFormula().toString());
		}
		return _players;
	}

	/**
	 * Parses the information contained in the second line of text
	 * from the save file, creates a new MoveableTile object for each
	 * tile to be loaded, and sets all relevant parameters for that
	 * tile (such as its path type, rotation/orientation, token, and player list)
	 * as read from the text. A final tile is added (the free tile)
	 * based on which tile type counter has not reached its maximum by
	 * the time all gameboard tiles have been loaded.
	 * @return an ArrayList of MoveableTiles to be placed on the gameboard
	 */
	public ArrayList<MoveableTile> getTiles() {
		String[] tiles = line2.split("\\]],");
		T=0;
		I=0;
		L=0;
		
//		for(String s:tiles){
//			System.out.println(s);
//		}

		for (int i = 0; i < 49; i++) {
			_tileinfos.clear();
			for (String s : tiles[i].split(",")) {
				_tileinfos.add(s.replaceAll("[\\[\\]]", ""));
			}
			for (String s: _tileinfos) {
				System.out.print(s + " ");
			}
			System.out.println();
			switch (_tileinfos.get(0)) {
			case "T0":
				T++;
				_tiles.add(new MoveableTile("T", 0, 1, 1, 1, 0));
				break;
			case "T1":
				T++;
				_tiles.add(new MoveableTile("T", 1, 1, 1, 0, 1));
				break;
			case "T2":
				T++;
				_tiles.add(new MoveableTile("T", 1, 0, 1, 1, 2));
				break;
			case "T3":
				T++;
				_tiles.add(new MoveableTile("T", 1, 1, 0, 1, 3));
				break;
			case "L0":
				L++;
				_tiles.add(new MoveableTile("L", 0, 1, 0, 1, 0));
				break;
			case "L1":
				L++;
				_tiles.add(new MoveableTile("L", 0, 1, 1, 0, 1));
				break;
			case "L2":
				L++;
				_tiles.add(new MoveableTile("L", 1, 0, 1, 0, 2));
				break;
			case "L3":
				L++;
				_tiles.add(new MoveableTile("L", 1, 0, 0, 1, 3));
				break;
			case "I0":
				I++;
				_tiles.add(new MoveableTile("I", 1, 1, 0, 0, 0));
				break;
			case "I1":
				I++;
				_tiles.add(new MoveableTile("I", 0, 0, 1, 1, 1));
				break;
			case "I2":
				I++;
				_tiles.add(new MoveableTile("I", 1, 1, 0, 0, 0));
				break;
			case "I3":
				I++;
				_tiles.add(new MoveableTile("I", 0, 0, 1, 1, 1));
				break;
			default:
				break;
			}
			if (!(Integer.parseInt(_tileinfos.get(1)) == 0)) {
				if (Integer.parseInt(_tileinfos.get(1)) != 25) {
					_tokens.set(Integer.parseInt(_tileinfos.get(1)) - 1, new Token(Integer.parseInt(_tileinfos.get(1)), Token.getTokenName(Integer.parseInt(_tileinfos.get(1)))));
					_tiles.get(i).setToken(_tokens.get(Integer.parseInt(_tileinfos.get(1)) - 1));
					_tokens.get(Integer.parseInt(_tileinfos.get(1)) - 1).setTile(_tiles.get(i));;
					System.out.println(_tokens.toString());
				} else {
					_tokens.set(20, new Token(Integer.parseInt(_tileinfos.get(1)), Token.getTokenName(Integer.parseInt(_tileinfos.get(1)))));
					_tiles.get(i).setToken(_tokens.get(20));
					_tokens.get(20).setTile(_tiles.get(i));
				}
				
			}
			if (!_tileinfos.get(2).equals("")) {
				for (int j = 2; j < _tileinfos.size(); j++) {
					System.out.println(_tileinfos.get(j));
					Player p = GetPlayerByColor(_tileinfos.get(j));
					_tiles.get(i).addPlayer(p);
					p.setCurrentTile(_tiles.get(i));
				}
			}
		}
		
		if (T == 17) {
			MoveableTile freeTile = new MoveableTile("T");
			_tiles.add(freeTile);
			setIllegalShiftPosition(freeTile);
		}
		if (I == 12) {
			MoveableTile freeTile = new MoveableTile("I");
			_tiles.add(freeTile);
			setIllegalShiftPosition(freeTile);
		}
		if (L == 18) {
			MoveableTile freeTile = new MoveableTile("L");
			_tiles.add(freeTile);
			setIllegalShiftPosition(freeTile);
		}
		return _tiles;
	}
	
	/**
	 * Returns the list of tokens to be placed on the gameboard
	 * @return the list of tokens to be placed on the gameboard
	 */
	public ArrayList<Token> getTokens(){
		return _tokens;
	}
	
	/**
	 * Returns the highest token value picked up when the game was saved
	 * @return the highest token value in the save file
	 */
	public int getMaxToken () {
		return maxToken;
	}
	
	/**
	 * Parses the third line of text from the save file to find
	 * the shift index which should not be allowable upon loading
	 * the game, and sets the free tile's last board position with that value
	 * (mapped from positions counted clockwise as [1,12] to 
	 * tile positions in range [0,48]).
	 * @param tile the game's free tile, which keeps track of the
	 * 				illegal shift position by remembering its own last
	 * 				position before being shifted off the board
	 */
	public void setIllegalShiftPosition(MoveableTile tile) {
		int position;
		try {
			position = Integer.parseInt(line3);
		} catch (NumberFormatException e) {
			position = 0;
		}
		
		switch (position) {
		case 1: tile.setLastTileNum(1); break;
		case 2: tile.setLastTileNum(3); break;
		case 3: tile.setLastTileNum(5); break;
		case 4: tile.setLastTileNum(13); break;
		case 5: tile.setLastTileNum(27); break;
		case 6: tile.setLastTileNum(41); break;
		case 7: tile.setLastTileNum(47); break;
		case 8: tile.setLastTileNum(45); break;
		case 9: tile.setLastTileNum(43); break;
		case 10: tile.setLastTileNum(35); break;
		case 11: tile.setLastTileNum(21); break;
		case 12: tile.setLastTileNum(7); break;
		default: tile.setLastTileNum(-1); break;
		}
	}
	
	/**
	 * Retrieves a Player object from the list of players
	 * being loaded based in its color
	 * @param c a String representing the color of the Player being sought
	 * @return the Player whose color matches the specified color
	 */
	private Player GetPlayerByColor(String c) {
		for (Player p : _players) {
			System.out.println(p.getColor());
			if (p.getColor().equals(c))
				return p;
		}
		return null;
	}
}