package code.gui;

import code.model.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 * <h1>GameBoardGUI</h1>
 * Contains the variables and methods necessary to create a visual representation
 * of MasterLabyrinth. This includes a JFrame encompassing the game window and relevant
 * formatting information that arranges window components on the screen (i.e. the game board
 * and its interactive tile/player/token buttons, player information such as wand and token counts,
 * options for rotating the free tile, and interactive buttons/menu bars for ending turns and saving the game).
 * 
 * @author Ian, Weijin
 * @author Fen, Tyler, Michael, Dan
 */
public class GameBoardGUI implements Runnable, Observer {
	
	/**
	 * collection of 21 references to token JButtons
	 */
	private ArrayList<JButton> _tokenButtons;

	/**
	 * reference to the main window of the game that holds _leftPanel and
	 * _rightPanel
	 */
	private JFrame _window;

	/**
	 * reference to the panel that the gameboard is on
	 */
	private JPanel _boardPanel;

	/**
	 * reference to the panel that is behind the _boardPanel
	 */
	private JPanel _leftPanel;

	/**
	 * the labels (triangles) indicate the places where players can insert the
	 * shiftable tile
	 */
	private JLabel _leftPanelBehind;

	/**
	 * reference to the main panel that's on the right side of the game window
	 */
	private JPanel _rightPanel;

	/**
	 * the panel that holds the JTextPane _playerInfo
	 */
	private JPanel _playerInfoPanel;

	/**
	 * the panel that holds three JButtons that are used for shifting the
	 * shiftable tile
	 */
	private JPanel _shiftableTilePanel;

	/**
	 * the panel that holds the JTextPane _gameFeedback
	 */
	private JPanel _gameFeedbackPanel;

	/**
	 * reference to the GameBoard class
	 */
	private GameBoard _gb;

	/**
	 * the JButton that used to hold the shiftable tile
	 */
	private JButton _shiftableTileButton;

	/**
	 * the JButton that is used to rotate the shiftable tile counterclockwise by
	 * 90 degree
	 */
	private JButton _rotateCounterClockwise;

	/**
	 * the JButton that is used to rotate the shiftable tile clockwise by 90
	 * degree
	 */
	private JButton _rotateClockwise;

	/**
	 * the JTextPane that displays the players' information
	 */
	private JTextPane _playerInfo;

	/**
	 * the JTextPane gives players the game feedback such as whose turn it is
	 */
	private JTextPane _gameFeedback;

	private JPanel _endTurnPanel;

	private JButton _wandButton;

	/**
	 * the JButton that is used to end each player's turn
	 */
	private JButton _endTurnButton;

	/**
	 * a JMenuBar which will contain the option for saving the game and appear at the top of the window 
	 */
	private JMenuBar menuBar;

	/**
	 * This method sets the observer for the gameboard gb refers to the
	 * GameBoard object
	 * 
	 * @param gb
	 *            refers to GameBoard
	 * @author Ian,Weijin
	 */
	public GameBoardGUI(GameBoard gb) {
		_gb = gb;
		_gb.setObserver(this);
	}

	/**
	 * This method creates the main window for the game and add two main panels
	 * with same size to the window. On the top of these two panels, there are
	 * gameboard panel, game feedback panel, players information panel, and
	 * shiftable tile panel. And set the size of these panels. This method also
	 * creates a JButton named "end turn" and add the ActionListener to it. Additionally,
	 * a JButton for using wands and a JMenuBar for saving the game are added with
	 * anonymously-defined action listeners.
	 * 
	 * @author Ian,Weijin
	 * @author Ian,Ken 04-10-16
	 * @author Fen, Tyler, Michael, Dan
	 */
	@Override
	public void run() {
		_window = new JFrame("Master Labyrinth");
		_window.setSize(1440, 720);
		_window.setBackground(new Color(245, 245, 220));
		_window.setResizable(false);
		_window.getContentPane().setLayout(new GridLayout(1, 2));

		menuBar = new JMenuBar();
		JMenuItem save = new JMenuItem("save");
		menuBar.add(save);
		save.addActionListener(new ActionListener() {
			
			/**
			 * An action listener for saving the game upon clicking the JMenuBar at the top of the window marked "save", which opens
			 * an input dialog box. If the conditions for saving are met (i.e. it is the beginning of the player's turn and a valid 
			 * file path was supplied), the GameBoard's save() method is executed and the application exits. Otherwise, a message is printed to the
			 * game feedback panel notifying the player that the save was unsuccessful.
			 * 
			 * @author Fen, Michael, Tyler
			 * @param e (unused)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(_window, "If you want to save the game, please input the file name.");
				if ((name != null && name.length() > 0) && _gb.save(name)) {
					System.exit(0);
				} else {
					_gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " + GameBoard.CURRENTPLAYER.getName() + "'s ("
							+ GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."
							+ "\nCurrent Collectible Token Number: " + _gb.getCurrentTargetTokenValue()
							+ "\n\nYou can only save at the beginning of your turn and/or you must input a valid filename");
					_gameFeedback.setFont(new Font("Garamond", Font.BOLD, 14));
				} 
			}
			
		});

		_window.setJMenuBar(menuBar);
		_leftPanel = new JPanel();
		// JPanel _leftPanelBack = new JPanel();

		try {
			Image img = ImageIO.read(getClass().getResourceAsStream("images/GameBoardBorder.bmp"));
			_leftPanelBehind = new JLabel(new ImageIcon(img));
		} catch (IOException ex) {
		}

		_rightPanel = new JPanel();
		_rightPanel.setBackground(new Color(245, 245, 220));
		_leftPanel.setBackground(new Color(245, 245, 220));
		_leftPanel.setSize(560, 560);
		_leftPanelBehind.setSize(720, 720);
		// _leftPanelBack.setSize(720,720);
		_rightPanel.setSize(720, 720);

		_endTurnPanel = new JPanel();
		_endTurnPanel.setBackground(new Color(245, 245, 220));
		_endTurnPanel.setSize(720, 180);
		_endTurnPanel.setLayout(new GridLayout(1, 2));

		_wandButton = new JButton("Use Wand");
		_wandButton.setFont(new Font("Garamond", Font.BOLD, 40));
		_wandButton.setForeground(new Color(255, 201, 14));
		_wandButton.setPreferredSize(new Dimension(720, 180));
		_wandButton.setBackground(new Color(0, 0, 0));
		_wandButton.addActionListener(new ActionListener() {

			/**
			 * An action listener for pressing the "Use Wand" button in the game window. If the proper conditions are
			 * met (i.e. a player has shifted the board, has wands available, and has not already used a wand this turn)
			 * then the player's current turn is ended, and the player's useWand() method is called. Otherwise,
			 * depending on the reason for failure, a feedback message is printed in the window to notify the player.
			 * 
			 * @author Fen, Tyler
			 * @param e (unused)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameBoard.CURRENTPLAYER.getHasInsertedThisTurn() && GameBoard.CURRENTPLAYER.getWands() > 0
						&& !GameBoard.CURRENTPLAYER.getWandUsedThisTurn()) {
					GameBoard.CURRENTPLAYER.endMyTurn();
					GameBoard.CURRENTPLAYER.useWand();
					// _gb.toggleNextPlayer();
					// _gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " +
					// GameBoard.CURRENTPLAYER.getName() + "'s ("
					// + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."
					// + "\nCurrent Collectible Token Number: " +
					// _gb.getCurrentTargetTokenValue());
					// _gameFeedback.setFont(new Font("Garamond", Font.BOLD,
					// 14));
					updateGameInfo("");
					updatePlayerInfo();
				} else if (GameBoard.CURRENTPLAYER.getWands() == 0) {
					// _gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " +
					// GameBoard.CURRENTPLAYER.getName() + "'s ("
					// + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."
					// + "\nCurrent Collectible Token Number: " +
					// _gb.getCurrentTargetTokenValue()
					// + "\n\n You do not have any wands left.");
					// _gameFeedback.setFont(new Font("Garamond", Font.BOLD,
					// 14));
					updateGameInfo("\n\n You do not have any wands left.");
				} else if (GameBoard.CURRENTPLAYER.getWandUsedThisTurn()) {
					// _gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " +
					// GameBoard.CURRENTPLAYER.getName() + "'s ("
					// + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."
					// + "\nCurrent Collectible Token Number: " +
					// _gb.getCurrentTargetTokenValue()
					// + "\n\n You have already used a wand this turn.");
					// _gameFeedback.setFont(new Font("Garamond", Font.BOLD,
					// 14));
					updateGameInfo("\n\n You have already used a wand this turn.");

				}
			}
		});

		_endTurnButton = new JButton("End Turn");
		_endTurnButton.setFont(new Font("Garamond", Font.BOLD, 40));
		_endTurnButton.setForeground(new Color(255, 201, 14));
		_endTurnButton.setPreferredSize(new Dimension(720, 180));
		_endTurnButton.setBackground(new Color(0, 0, 0));
		_endTurnButton.addActionListener(new ActionListener() {

			/**
			 * This method add ActionListen to the "end turn" button, and by
			 * clicking this button, a player can end his/her turn if the proper
			 * conditions are met. Otherwise, a relevant feedback message is printed 
			 * to the window to notify the player that they cannot end their turn.
			 * 
			 * @param e
			 *            action event generated when end turn clicked
			 * @author Ian,Weijin
			 * @author Ian,Ken 04-10-16
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameBoard.CURRENTPLAYER.getHasInsertedThisTurn()) {
					GameBoard.CURRENTPLAYER.endMyTurn();
					_gb.toggleNextPlayer();
					// _gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " +
					// GameBoard.CURRENTPLAYER.getName() + "'s ("
					// + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."
					// + "\nCurrent Collectible Token Number: " +
					// _gb.getCurrentTargetTokenValue());
					// _gameFeedback.setFont(new Font("Garamond", Font.BOLD,
					// 14));
					updateGameInfo("");
					updatePlayerInfo();
					// String t1 = "";
					// int cp = -1;
					// for(int i = 0; i < _gb.getPlayers().length; i++){
					// Player p = _gb.getPlayers()[i];
					// if(p == GameBoard.CURRENTPLAYER){cp = i;}
					// if(p != GameBoard.CURRENTPLAYER){
					// String t2 = "";
					// t1 = t1 + "Player " + i + ": " + p.getName() + " (" +
					// p.getColor() + " Pawn) -- Tokens Coll.: ";
					// for(Token t: p.getTokens()){
					// t2 = t2 + t.getValue() + " ";
					// }
					// t1 = t1 + t2 + "\n";
					// }
					// }
					// Player p = GameBoard.CURRENTPLAYER;
					// String tokens = "";
					// for(Token t: p.getTokens()){
					// tokens = tokens + t.getValue() + " ";
					// }
					// _playerInfo.setText("\t\t\t\tPLAYER INFO\n\nCurrent
					// Player (" + cp +"): " + p.getName() + " (" +
					// p.getColor() + " Pawn) \n" /*Current Score: "+
					// p.getScore() + "\n" */+ "My Tokens Collected: " + tokens
					// + "\n\n" + t1);
					// _playerInfo.setFont(new Font("Garamond", Font.BOLD, 14));
				} else {
					// _gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " +
					// GameBoard.CURRENTPLAYER.getName() + "'s ("
					// + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."
					// + "\nCurrent Collectible Token Number: " +
					// _gb.getCurrentTargetTokenValue()
					// + "\n\n You cannot end your turn yet.");
					// _gameFeedback.setFont(new Font("Garamond", Font.BOLD,
					// 14));

					updateGameInfo("\n\n You cannot end your turn yet.");
				}
			}
		});

		_endTurnPanel.add(_wandButton);
		_endTurnPanel.add(_endTurnButton);

		_leftPanel.setLayout(new GridLayout(1, 1));
		_rightPanel.setLayout(new GridLayout(4, 1));
		_leftPanelBehind.add(_leftPanel);
		_leftPanelBehind.setLayout(new GridBagLayout());
		_leftPanelBehind.setBackground(new Color(245, 245, 220));
		// _leftPanelBack.add(_leftPanelBehind);
		// _leftPanelBack.setLayout(new GridBagLayout());
		_leftPanelBehind.setVerticalAlignment(SwingConstants.CENTER);
		;
		_leftPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		_leftPanel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		// _leftPanel.setVerticalAlignment(SwingConstants.CENTER);
		// _leftPanel.setHorizontalAlignment(SwingConstants.CENTER);

		_window.add(_leftPanelBehind);
		_window.add(_rightPanel);

		createAndPopulateBoardPanel();
		createAndPopulatePlayerInfoPanel();
		createAndPopulateShiftableTilePanel();
		createAndPopulateGameFeedbackPanel();

		_leftPanel.add(_boardPanel);
		_boardPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		_boardPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		_rightPanel.add(_playerInfoPanel);
		_rightPanel.add(_shiftableTilePanel);
		_rightPanel.add(_gameFeedbackPanel);
		// _rightPanel.add(_endTurnButton);
		_rightPanel.add(_endTurnPanel);

		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// _window.pack();

		update();

		_window.setVisible(true);
	}

	/**
	 * This method creates and populates the gameboard feedback panel. It sets
	 * the size and background of the panel and there is a JTextPane on top of
	 * it that's keeping track of whose turn it is
	 * 
	 * @author Ian,Ken 04-10-16
	 */
	private void createAndPopulateGameFeedbackPanel() {
		_gameFeedbackPanel = new JPanel();
		_gameFeedbackPanel.setBackground(new Color(245, 245, 220));
		_gameFeedbackPanel.setPreferredSize(new Dimension(720, 180));
		_gameFeedback = new JTextPane();
		_gameFeedback.setEditable(false);
		_gameFeedback.setPreferredSize(new Dimension(720, 180));
		_gameFeedbackPanel.add(_gameFeedback);
		_gameFeedback.setBackground(new Color(245, 245, 220));
		// _gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " +
		// GameBoard.CURRENTPLAYER.getName() + "'s ("
		// + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn." + "\nCurrent
		// Collectible Token Number: "
		// + _gb.getCurrentTargetTokenValue());
		// _gameFeedback.setFont(new Font("Garamond", Font.BOLD, 14));
		updateGameInfo("");
	}

	/**
	 * This method creates and populates the players information panel. It sets
	 * the size and background of the panel and there is a JTextPane on top of
	 * it that displays each player's information and the number of tokens that
	 * a player has been collected, along with their wand count.
	 * 
	 * @author Ian,Ken 04-10-16
	 */
	private void createAndPopulatePlayerInfoPanel() {
		_playerInfoPanel = new JPanel();
		_playerInfoPanel.setPreferredSize(new Dimension(720, 180));
		_playerInfoPanel.setBackground(new Color(245, 245, 220));
		_playerInfo = new JTextPane();
		_playerInfo.setEditable(false);
		_playerInfo.setPreferredSize(new Dimension(720, 180));
		_playerInfoPanel.add(_playerInfo);
		_playerInfo.setBackground(new Color(245, 245, 220));

		updatePlayerInfo();
		// String t1 = "";
		// int cp = -1;
		// for(int i = 0; i < _gb.getPlayers().length; i++){
		// Player p = _gb.getPlayers()[i];
		// if(p == GameBoard.CURRENTPLAYER){cp = i;}
		// if(p != GameBoard.CURRENTPLAYER){
		// String t2 = "";
		// t1 = t1 + "Player " + i + ": " + p.getName() + " (" + p.getColor() +
		// " Pawn) -- Tokens Coll.: ";
		// for(Token t: p.getTokens()){
		// t2 = t2 + t.getValue() + " ";
		// }
		// t1 = t1 + t2 + "\n";
		// }
		// }
		// Player p = GameBoard.CURRENTPLAYER;
		// String tokens = "";
		// for(Token t: p.getTokens()){
		// tokens = tokens + t.getValue() + " ";
		// }
		// _playerInfo.setText("\t\t\t\tPLAYER INFO\n\nCurrent Player (" + cp
		// +"): " + p.getName() + " (" +
		// p.getColor() + " Pawn) \n" /*Current Score: "+
		// p.getScore() + "\n" */+ "My Tokens Collected: " + tokens
		// + "\n\n" + t1);
		// _playerInfo.setFont(new Font("Garamond", Font.BOLD, 14));
	}

	/**
	 * Updates the JTextPane displaying current user information in the game window.
	 * This includes: identification of the current player, their wand and token counts, and secret formula card;
	 * and the wand and token counts for the other players (but not secret formula cards).
	 * 
	 * @author Fen, Michael
	 */
	private void updatePlayerInfo() {
		String t1 = "";
		int cp = -1;
		for (int i = 0; i < _gb.getPlayers().length; i++) {
			Player p = _gb.getPlayers()[i];
			if (p == GameBoard.CURRENTPLAYER) {
				cp = i;
			}
			if (p != GameBoard.CURRENTPLAYER) {
				String t2 = "";
				t1 = t1 + "Player" + ": " + p.getName() + " (" + p.getColor() + " Pawn) -- Wands Left: "
						+ p.getWands() + " -- Tokens Coll.: ";
				for (Token t : p.getTokens()) {
					t2 = t2 + t.getValue() + " ";
				}
				t1 = t1 + t2 + "\n";
			}
		}
		Player p = GameBoard.CURRENTPLAYER;
		
		String tokens = "";
		for (Token t : p.getTokens()) {
			tokens = tokens + t.getValue() + " ";
		}
		ArrayList<Token> SF = p.getFormulaCard().getCardTokens();
		_playerInfo.setText("\t\t\t\tPLAYER INFO\n\nCurrent Player: " + p.getName() + " (" + p.getColor()
				+ " Pawn) \n" /*
								 * Current Score: "+ p.getScore() + "\n"
								 */
				+ "My Wands Left: " + p.getWands() + "\n" + "My secret formula: " + p.getFormulaCard().getCardName()
				+ " - " + SF.get(0).getValue() + ", " + SF.get(1).getValue() + ", " + SF.get(2).getValue()
				+ "\nMy Tokens Collected: " + tokens + "\n\n" + t1);
		_playerInfo.setFont(new Font("Garamond", Font.BOLD, 12));
	}

	private void updateGameInfo(String s) {
		_gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " + GameBoard.CURRENTPLAYER.getName() + "'s ("
				+ GameBoard.CURRENTPLAYER.getColor() + " pawn) turn." + "\nCurrent Collectible Token Number: "
				+ _gb.getCurrentTargetTokenValue() + s);
		_gameFeedback.setFont(new Font("Garamond", Font.BOLD, 14));
	}

	/**
	 * This method creates and populates the board panel. It sets the size,
	 * background, and layout of the gameboard, adds 49 JButtons to it which
	 * represents the 49 tiles on gameboard, and add ActionListener to these
	 * buttons.
	 * 
	 * @author Ian,Ken 04-10-16
	 */
	private void createAndPopulateBoardPanel() {
		_boardPanel = new JPanel();
		_boardPanel.setSize(560, 560);
		_boardPanel.setLayout(new GridLayout(7, 7));
		_tokenButtons = new ArrayList<JButton>();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				JButton button = new JButton();
				// JButton tokenButton = new JButton();
				// JLabel tokenLabel = new JLabel();
				// _tokenButtons.add(tokenButton);
				// tokenButton.setPreferredSize(new Dimension(30,30));
				// tokenButton.addActionListener(new
				// TokenButtonHandler(i,j,_gb,tokenButton));
				button.setPreferredSize(new Dimension(80, 80));
				button.setLayout(new FlowLayout());
				button.addActionListener(new GameBoardButtonHandler(i, j, _gb, this));
				_boardPanel.add(button);
				// button.add(tokenLabel);
				// tokenButton.setPreferredSize(new Dimension(30,30));
				// tokenButton.setText("t");
				AbstractTile at = _gb.getBoard()[i][j];
				// tokenLabel.setVisible(true);
				// if(!at.hasToken()){
				// tokenLabel.setVisible(false);
				// }

			}
		}
		// for(int i =1; i<=5; i= i+2 ){
		// for(int j =1; j<=5; j++){
		// JButton token = new JButton();
		// token.setPreferredSize(new Dimension(30,30));
		// _tokenButtons.add(token);
		// JButton bp =(JButton)_boardPanel.getComponent(i*7 + j);
		// bp.add(token);
		// }
		// }
		// for(int i = 2; i<=4; i= i+2 ){
		// for(int j =1; j<=5; j= j+2){
		// JButton token = new JButton();
		// token.setPreferredSize(new Dimension(30,30));
		// _tokenButtons.add(token);
		// JButton bp =(JButton)_boardPanel.getComponent(i*7 + j);
		// bp.add(token);
		// }
		// }
	}

	/**
	 * This method creates and populates the shiftable tile panel. It sets the
	 * size, background, and layout of the panel. This method also creates three
	 * JButtons, and add ActionsListeners to _rotateCounterClockwise and
	 * _rotateClockwise buttons.
	 * 
	 * @author Ian,Ken 04-10-16
	 */
	private void createAndPopulateShiftableTilePanel() {
		_shiftableTilePanel = new JPanel();
		_shiftableTilePanel.setBackground(new Color(245, 245, 220));
		_shiftableTilePanel.setSize(720, 180);
		_shiftableTilePanel.setLayout(new GridLayout(1, 3));

		_shiftableTileButton = new JButton();
		_rotateCounterClockwise = new JButton();
		_rotateClockwise = new JButton();
		_shiftableTileButton.setPreferredSize(new Dimension(80, 80));
		_rotateCounterClockwise.setPreferredSize(new Dimension(80, 80));
		_rotateClockwise.setPreferredSize(new Dimension(80, 80));
		_shiftableTileButton.setBackground(new Color(245, 245, 220));
		_rotateCounterClockwise.setBackground(new Color(245, 245, 220));
		_rotateClockwise.setBackground(new Color(245, 245, 220));
		_rotateCounterClockwise.addActionListener(new ActionListener() {

			/**
			 * This method adds ActionListener to the _rotateCounterClockwise
			 * button, so when a player clicks that button, the shiftable tile
			 * will shift counterclockwise by 90 degree.
			 * 
			 * @param e
			 *            action event generated when counterclockwise button
			 *            clicked
			 * @author Ian,Ken 04-10-16
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (GameBoard.CURRENTPLAYER.getHasInsertedThisTurn()) {
					// _gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " +
					// GameBoard.CURRENTPLAYER.getName() + "'s ("
					// + GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."
					// + "\nCurrent Collectible Token Number: " +
					// _gb.getCurrentTargetTokenValue()
					// + "\n\nYou cannot rotate the shiftable tile because you
					// have"
					// + " already inserted this turn.");
					// _gameFeedback.setFont(new Font("Garamond", Font.BOLD,
					// 14));
					updateGameInfo(
							"\n\nYou cannot rotate the shiftable tile because you have already inserted this turn.");
				}
				if (!GameBoard.CURRENTPLAYER.getHasInsertedThisTurn()) {
					GameBoard.CURRENTPLAYER.rotateShiftableTile(90);
					update();
				}
			}
		});
		_rotateClockwise.addActionListener(new ActionListener() {

			/**
			 * This method add ActionListener to the _rotateClockwise button, so
			 * when a player clicks that button, the shiftable tile will shift
			 * clockwise by 90 degree. And catch the exception if it occurs.
			 * 
			 * @param e
			 *            action event generated when counterclockwise button
			 *            clicked
			 * @author Ian,Ken 04-10-16
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (GameBoard.CURRENTPLAYER.getHasInsertedThisTurn()) {
					_gameFeedback.setText("\t\t\t\tGAME INFO\n\nIt is now " + GameBoard.CURRENTPLAYER.getName() + "'s ("
							+ GameBoard.CURRENTPLAYER.getColor() + " pawn) turn."
							+ "\nCurrent Collectible Token Number: " + _gb.getCurrentTargetTokenValue()
							+ "\n\nYou cannot rotate the shiftable tile because you have"
							+ " already inserted this turn.");
					_gameFeedback.setFont(new Font("Garamond", Font.BOLD, 14));
				}
				if (!GameBoard.CURRENTPLAYER.getHasInsertedThisTurn()) {
					GameBoard.CURRENTPLAYER.rotateShiftableTile(-90);
					update();
				}
			}
		});
		try {
			Image rotateLeft = ImageIO.read(getClass().getResourceAsStream("images/rotateLeft1.gif"));
			Image rotateRight = ImageIO.read(getClass().getResourceAsStream("images/rotateRight1.gif"));
			// _rotateCounterClockwise.setText("Rotate 90 Degrees");
			_rotateCounterClockwise.setIcon(new ImageIcon(rotateLeft));

			_rotateClockwise.setIcon(new ImageIcon(rotateRight));
			// _rotateClockwise.setText("Rotate -90 degrees");
		} catch (IOException ex) {
		}
		MoveableTile shiftableTile = _gb.getMoveableTileArray().get(0);
		_shiftableTileButton.setIcon(new ImageIcon(generateImageForTile(shiftableTile)));
		_shiftableTilePanel.add(_rotateCounterClockwise);
		_shiftableTilePanel.add(_shiftableTileButton);
		_shiftableTilePanel.add(_rotateClockwise);

		_shiftableTilePanel.add(_rotateCounterClockwise);
		_shiftableTilePanel.add(_shiftableTileButton);
		_shiftableTilePanel.add(_rotateClockwise);
	}

	/**
	 * This method puts the images to the tiles on the gameboard.
	 * 
	 * @param at
	 *            the AbstractTile class
	 * @return img the images of the tiles
	 * @author Ian Weijin
	 */
	public Image generateImageForTile(AbstractTile at) {
		Image img = null;
		int t, b, l, r;
		if (at != null) {
			t = at.getTop();
			b = at.getBottom();
			l = at.getLeft();
			r = at.getRight();
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					if (_gb.getBoard()[i][j] == at
							&& ((i == 2 && j == 2) || (i == 2 && j == 4) || (i == 4 && j == 2) || (i == 4 && j == 4))) {
						try {
							if (i == 2 && j == 2) {
								img = ImageIO.read(getClass().getResource("images/TBrownStart.gif"));
								return img;
							}

							else if (i == 2 && j == 4) {
								img = ImageIO.read(getClass().getResource("images/TBlueStart.gif"));
								return img;
							} else if (i == 4 && j == 2) {
								img = ImageIO.read(getClass().getResource("images/TRedStart.gif"));
								return img;
							} else if (i == 4 && j == 4) {
								img = ImageIO.read(getClass().getResource("images/TWhiteStart.gif"));
								return img;
							}
						} catch (IOException ex) {
						}
					}
				}
			}

			try {
				// T Tile
				if (t == 1 && b == 0 && l == 1 && r == 1) {
					img = ImageIO.read(getClass().getResource("images/T0.gif"));
				} else if (t == 1 && b == 1 && l == 1 && r == 0) {
					img = ImageIO.read(getClass().getResource("images/T90.gif"));
				} else if (t == 0 && b == 1 && l == 1 && r == 1) {
					img = ImageIO.read(getClass().getResource("images/T180.gif"));
				} else if (t == 1 && b == 1 && l == 0 && r == 1) {
					img = ImageIO.read(getClass().getResource("images/T270.gif"));
				}

				// L Tile
				else if (t == 1 && b == 0 && l == 0 && r == 1) {
					img = ImageIO.read(getClass().getResource("images/L0.gif"));
				} else if (t == 1 && b == 0 && l == 1 && r == 0) {
					img = ImageIO.read(getClass().getResource("images/L90.gif"));
				} else if (t == 0 && b == 1 && l == 1 && r == 0) {
					img = ImageIO.read(getClass().getResource("images/L180.gif"));
				} else if (t == 0 && b == 1 && l == 0 && r == 1) {
					img = ImageIO.read(getClass().getResource("images/L270.gif"));
				}

				// I Tile
				else if (t == 0 && b == 0 && l == 1 && r == 1) {
					img = ImageIO.read(getClass().getResource("images/I0.gif"));
				} else if (t == 1 && b == 1 && l == 0 && r == 0) {
					img = ImageIO.read(getClass().getResource("images/I90.gif"));
				} else if (t == 0 && b == 0 && l == 1 && r == 1) {
					img = ImageIO.read(getClass().getResource("images/I180.gif"));
				} else if (t == 1 && b == 1 && l == 0 && r == 0) { // (t==1 &&
																	// b==1 &&
																	// l==0 &&
																	// r==0)
					img = ImageIO.read(getClass().getResource("images/I270.gif"));
				}
			} catch (IOException ex) {
			}
		}
		return img;
	}

	/**
	 * This method refreshes the GUI by resetting the players, tokens, and icons
	 * on each tile as well as updating the feedback text panels.
	 * 
	 * @author Ian,Weijin
	 */
	@Override
	public void update() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				JButton button = (JButton) _boardPanel.getComponent((i * 7) + j);
				button.removeAll();
				AbstractTile at = _gb.getBoard()[i][j];
				Image icon = generateImageForTile(at);
				button.setIcon(new ImageIcon(icon));
				// button.setLayout(new FlowLayout());
				if (at.hasToken()) {
					JButton tokenButton = new JButton();
					button.add(tokenButton);
					tokenButton.setPreferredSize(new Dimension(25, 25));
					tokenButton.addActionListener(new TokenButtonHandler(i, j, _gb, tokenButton));
					Token token = at.getToken();

					try {
						int val = token.getValue();
						String path = "images/tok" + val + ".bmp";
						Image img = ImageIO.read(getClass().getResourceAsStream(path));
						tokenButton.setIcon(new ImageIcon(img));
					} catch (IOException ex) {
					}
				}

				if (at.hasPlayer()) {
					ArrayList<Player> list = at.getPlayers();
					for (Player p : list) {
						String s = p.getColor();
						JButton playerButton = new JButton();
						String path = null;
						if (s.equals("BLACK")) {
							path = "images/PawnBlack.bmp";
						} else if (s.equals("BLUE")) {
							path = "images/PawnBlue.bmp";
						} else if (s.equals("RED")) {
							path = "images/PawnRed.bmp";
						} else if (s.equals("GREEN")) {
							path = "images/PawnGreen.bmp";
						}
						try {
							Image img = ImageIO.read(getClass().getResourceAsStream(path));
							playerButton.setIcon(new ImageIcon(img));
							button.add(playerButton);
							playerButton.setPreferredSize(new Dimension(15, 15));
						} catch (IOException ex) {
						}
					}
				}
			}
		}

		updatePlayerInfo();
		// String t1 = "";
		// int cp = -1;
		// for(int i = 0; i < _gb.getPlayers().length; i++){
		// Player p = _gb.getPlayers()[i];
		// if(p == GameBoard.CURRENTPLAYER){cp = i;}
		// if(p != GameBoard.CURRENTPLAYER){
		// String t2 = "";
		// t1 = t1 + "Player " + i + ": " + p.getName() + " (" + p.getColor() +
		// " Pawn) -- Tokens Coll.: ";
		// for(Token t: p.getTokens()){
		// t2 = t2 + t.getValue() + " ";
		// }
		// t1 = t1 + t2 + "\n";
		// }
		// }
		// Player p = GameBoard.CURRENTPLAYER;
		// String tokens = "";
		// for(Token t: p.getTokens()){
		// tokens = tokens + t.getValue() + " ";
		// }
		// _playerInfo.setText("\t\t\t\tPLAYER INFO\n\nCurrent Player (" + cp
		// +"): " + p.getName() + " (" +
		// p.getColor() + " Pawn) \n" /*Current Score: "+
		// p.getScore() + "\n" */+ "My Tokens Collected: " + tokens
		// + "\n\n" + t1);
		// _playerInfo.setFont(new Font("Garamond", Font.BOLD, 14));

		MoveableTile shiftableTile = _gb.getMoveableTileArray().get(0);
		_shiftableTileButton.setIcon(new ImageIcon(generateImageForTile(shiftableTile)));

		// _window.pack();
		_window.repaint();
	}

	/**
	 * This method refreshes the GUI when the game is over, in particular
	 * disabling most interactive features (such as the save menu bar, game board buttons,
	 * and wand/turn buttons) and displaying the final scores in the game feedback panel.
	 * 
	 * @author Ian,Weijin
	 * @author Tyler, Fen
	 */
	public void updateGameIsOver() {
		menuBar.setVisible(false);
		_wandButton.setEnabled(false);
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				JButton button = (JButton) _boardPanel.getComponent((i * 7) + j);
				button.removeAll();
				AbstractTile at = _gb.getBoard()[i][j];
				Image icon = generateImageForTile(at);
				button.setIcon(new ImageIcon(icon));
				button.setEnabled(false);
				// button.setLayout(new FlowLayout());
				if (at.hasToken()) {
					JButton tokenButton = new JButton();
					button.add(tokenButton);
					tokenButton.setPreferredSize(new Dimension(25, 25));
					tokenButton.addActionListener(new TokenButtonHandler(i, j, _gb, tokenButton));
					tokenButton.setEnabled(false);
					Token token = at.getToken();

					try {
						int val = token.getValue();
						String path = "images/tok" + val + ".bmp";
						Image img = ImageIO.read(getClass().getResourceAsStream(path));
						tokenButton.setIcon(new ImageIcon(img));
					} catch (IOException ex) {
					}

				}
				if (at.hasPlayer()) {
					ArrayList<Player> list = at.getPlayers();
					for (Player p : list) {
						String s = p.getColor();
						JButton playerButton = new JButton();
						String path = null;
						if (s.equals("BLACK")) {
							path = "images/PawnBlack.bmp";
						} else if (s.equals("BLUE")) {
							path = "images/PawnBlue.bmp";
						} else if (s.equals("RED")) {
							path = "images/PawnRed.bmp";
						} else if (s.equals("GREEN")) {
							path = "images/PawnGreen.bmp";
						}
						try {
							Image img = ImageIO.read(getClass().getResourceAsStream(path));
							playerButton.setIcon(new ImageIcon(img));
							button.add(playerButton);
							playerButton.setPreferredSize(new Dimension(15, 15));
						} catch (IOException ex) {
						}
						playerButton.setEnabled(false);
					}
				}
			}
		}

		updatePlayerInfo();
		// String t1 = "";
		// int cp = -1;
		// for(int i = 0; i < _gb.getPlayers().length; i++){
		// Player p = _gb.getPlayers()[i];
		// if(p == GameBoard.CURRENTPLAYER){cp = i;}
		// if(p != GameBoard.CURRENTPLAYER){
		// String t2 = "";
		// t1 = t1 + "Player " + i + ": " + p.getName() + " (" + p.getColor() +
		// " Pawn) -- Tokens Coll.: ";
		// for(Token t: p.getTokens()){
		// t2 = t2 + t.getValue() + " ";
		// }
		// t1 = t1 + t2 + "\n";
		// }
		// }
		// Player p = GameBoard.CURRENTPLAYER;
		// String tokens = "";
		// for(Token t: p.getTokens()){
		// tokens = tokens + t.getValue() + " ";
		// }
		// _playerInfo.setText("\t\t\t\tPLAYER INFO\n\nCurrent Player (" + cp
		// +"): " + p.getName() + " (" +
		// p.getColor() + " Pawn) \n" /*Current Score: "+
		// p.getScore() + "\n" */+ "My Tokens Collected: " + tokens
		// + "\n\n" + t1);
		// _playerInfo.setFont(new Font("Garamond", Font.BOLD, 14));

		MoveableTile shiftableTile = _gb.getMoveableTileArray().get(0);
		_shiftableTileButton.setIcon(new ImageIcon(generateImageForTile(shiftableTile)));

//		_window.pack();
		_window.repaint();
	}

	/**
	 * This method sets the text that appears on the game feedback panel
	 * 
	 * @param s
	 *            the string of text
	 * @author Ian,Satya 04-14-16
	 */
	public void updateGameFeedBack(String s) {
		_gameFeedback.setText(s);
		_gameFeedback.setFont(new Font("Garamond", Font.BOLD, 14));
	}

	/**
	 * This method is triggered upon the player picking up token with value 25
	 * and the player calling the toggleNextToken() method after picking up this
	 * 25 value token. This method sorts the players by score from highest to
	 * lowest and then prints "GAME OVER" along with the winner, his name and
	 * score and that of the runners up.
	 * 
	 * @author Satya, Ian 04-14-16
	 */
	public void gameOver() {

		updateGameIsOver();
		_rotateClockwise.setEnabled(false);
		_rotateCounterClockwise.setEnabled(false);
		_endTurnButton.setEnabled(false);
		// _shiftableTileButton.setEnabled(false);

		int length = _gb.getPlayers().length;
		Player[] temp = new Player[length];
		for (int i = 0; i < length; i++) {
			temp[i] = _gb.getPlayers()[i];
		}
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int runningMax = temp[i].getScore();
				if (temp[j].getScore() > runningMax) {
					runningMax = temp[j].getScore();
					Player p_temp = temp[i];
					temp[i] = temp[j];
					temp[j] = p_temp;
				}
			}
		}
		String s1 = "The winner and Master Wizard is ";
		String s2 = "Second place: ";
		String s3 = "Third place: ";
		String s4 = "Fourth place: ";

		if (length == 2) {
			s1 = s1 + temp[0].getName() + " with " + temp[0].getScore() + " points!";
			s2 = s2 + temp[1].getName() + " with " + temp[1].getScore() + " points.";
		}
		if (length == 3) {
			s1 = s1 + temp[0].getName() + " with " + temp[0].getScore() + " points!";
			s2 = s2 + temp[1].getName() + " with " + temp[1].getScore() + " points.";
			s3 = s3 + temp[2].getName() + " with " + temp[2].getScore() + " points.";
		}
		if (length == 4) {
			s1 = s1 + temp[0].getName() + " with " + temp[0].getScore() + " points!";
			s2 = s2 + temp[1].getName() + " with " + temp[1].getScore() + " points.";
			s3 = s3 + temp[2].getName() + " with " + temp[2].getScore() + " points.";
			s4 = s4 + temp[3].getName() + " with " + temp[3].getScore() + " points.";
		}

		if (length == 2) {
			_gameFeedback.setText("\t\t\t\t***GAME OVER!***\n\n" + s1 + "\n" + s2);
			_gameFeedback.setFont(new Font("Garamond", Font.BOLD, 14));
		}
		if (length == 3) {
			_gameFeedback.setText("\t\t\t\t***GAME OVER!***\n\n" + s1 + "\n" + s2 + "\n" + s3);
			_gameFeedback.setFont(new Font("Garamond", Font.BOLD, 14));
		}
		if (length == 4) {
			_gameFeedback.setText("\t\t\t\t***GAME OVER!***\n\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s4);
			_gameFeedback.setFont(new Font("Garamond", Font.BOLD, 14));
		}
	}

} // end of GameBoardGUI class definition
