package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Game;

public class Connect4Game extends JFrame implements ActionListener{
	
	
	// ATTRIBUTES
	private JButton [][] gridButton;
	private Game game;
	private JButton newGameButton, closeButton;
	private ImageIcon playerX, playerO;	
	
	private String playerXName;
	private String playerOName;
	private int moveCounter;
	private JLabel moveCounterNumberLabel;
	
	// CONSTRUCTOR
	public Connect4Game (String message) {
		super(message);
		
		initComponents();
	}
	
	
	
	/**
	 * Positioning the containers and creating all the components inside them
	 * This method goes inside the constructor, allowing the creation of all the components only creating this object
	 */
	private void initComponents() { 	
		
		// Local variables 
		JPanel gridContainer, btnContainer, playerInfoContainer;
		Dimension windowDimension, dimBtnContainer, dimPlayerInfoContainer;
		JLabel labelP1, labelP1Name, labelP2, labelP2Name, labelMove;
		Font font, boldFont;

		// Set close operation --> EXIT_ON_CLOSE
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		playerInfoContainer = new JPanel();
		gridContainer = new JPanel();
		btnContainer = new JPanel();
		game = new Game();
		
		// POSITIONING THE CONTAINERS
		
		// Container of main JFrame
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		
		windowDimension = new Dimension (800,600); // Value of pixels of the JFrame
		setSize(windowDimension);			
		setMinimumSize(windowDimension);	
		
		// playerInfoContainer
		dimPlayerInfoContainer = new Dimension (Integer.MAX_VALUE, 70);
		playerInfoContainer.setMaximumSize(dimPlayerInfoContainer);
		this.getContentPane().add(playerInfoContainer);		

		
		// gridContainer
		gridContainer.setLayout(new GridLayout(game.getBoardRow(), game.getBoardCol()));
		
		gridContainer.setPreferredSize(new Dimension (800,450));
		
		this.getContentPane().add(gridContainer);
		
		// btnContainer 
		
		// btnContainer.setLayout(new FlowLayout());
		dimBtnContainer = new Dimension(800,50); 
		btnContainer.setPreferredSize(dimBtnContainer);	
		btnContainer.setMaximumSize(dimBtnContainer);	
		
		this.getContentPane().add(btnContainer);
		
		// ASK NAME + ADDING THE LABELS
		
		// Ask name
		playerXName = JOptionPane.showInputDialog("Nombre jugador 1: ");
		
		playerOName = JOptionPane.showInputDialog("Nombre jugador 2: ");
		
		// Create and add the labels & fonts
		
		// Creating the fonts
		font = new Font (Font.SANS_SERIF, Font.PLAIN, 20); 		// Normal font
		boldFont = new Font (Font.SANS_SERIF, Font.BOLD, 20);	// Bold font
		
		// Creating the labels
		labelP1 = new JLabel("Player 1: ");
		labelP1Name = new JLabel(playerXName);
		labelP2 = new JLabel("Player 2");
		labelP2Name = new JLabel(playerOName);
		labelMove = new JLabel("Move counter");
		moveCounterNumberLabel = new JLabel(Integer.toString(moveCounter));
		   
		// Setting the font to the labels
		labelP1.setFont(boldFont);
		labelP1Name.setFont(font);
		
		labelP2.setFont(boldFont);
		labelP2Name.setFont(font);

		labelMove.setFont(boldFont);
		moveCounterNumberLabel.setFont(font);
		moveCounterNumberLabel.setForeground(Color.RED);
		
		// Adding the labels
		playerInfoContainer.add(labelP1);
		playerInfoContainer.add(labelP1Name);
		playerInfoContainer.add(Box.createHorizontalStrut(20));

		
		playerInfoContainer.add(labelP2);
		playerInfoContainer.add(labelP2Name);
		playerInfoContainer.add(Box.createHorizontalStrut(30));

		playerInfoContainer.add(labelMove);
		playerInfoContainer.add(moveCounterNumberLabel);
		
		
		// CREATING THE BUTTONS, CONFIGURE THEM AND ADDING THEM TO THE GUI
		newGameButton = new JButton ("NEW GAME");
		closeButton = new JButton ("CLOSE");
		
		gridButton = new JButton [game.getBoardRow()][game.getBoardCol()]; // Setting the dimensions of the grid of buttons
		
		for (int i = 0; i < gridButton.length; i++) {		
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j] = new JButton(); 
				
				gridButton[i][j].setContentAreaFilled(false); // Flat the button (remove the background)
				gridButton[i][j].setOpaque(true);

				gridButton[i][j].setName(i + "," + j); // Setting a name (the same as "ID") to identify the row and column of each button
				
				gridButton[i][j].addActionListener(this); // Adding a listener to the event of clicking this button
				
				gridContainer.add(gridButton[i][j]); // Adding the button to the screen
			}
		}
		
		restartBoard(); // Call the method that disables all the buttons of the gridButton except for the ones in the last row
		
		// Creating the button and adding a listener for the ActionEvent
		newGameButton = new JButton("New game");
		newGameButton.addActionListener(this);
		
		// Creating the button and adding a listener for the ActionEvent
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		
		// Adding the buttons to their container
		btnContainer.add(newGameButton);
		btnContainer.add(closeButton);

		
        this.setVisible(true);

		// Create the imageIcon for each player and scale the images for having the same size as the buttons
        
        // As the parameter of the first constructor it's has the scaled image, it's obtained 
        // by creating another imageIcon and getting it's image scaled (with the same size as the button at the start)
		playerX = new ImageIcon( new ImageIcon("img/PlayerX.png").getImage().getScaledInstance(gridButton[0][0].getBounds().width, gridButton[0][0].getBounds().height, Image.SCALE_SMOOTH)    );
		playerO = new ImageIcon( new ImageIcon("img/PlayerO.png").getImage().getScaledInstance(gridButton[0][0].getBounds().width, gridButton[0][0].getBounds().height, Image.SCALE_SMOOTH)    );
		
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] strCoordinates;
		int butRow, butCol;
		
		JButton but;
		
		// Checking if the element which is clicked is a JButton
		if( (e.getSource() instanceof JButton) ) {
			
			but = (JButton) e.getSource();
			
			// Clicking the button to create a new game
			if( but.equals(newGameButton)) {
				
				// The game hasn't ended --> Ask if the player want to reset the game
				if( !game.hasGameEnded() ) {
					// Return 0: The user clicks "yes"
					// Return 1: The user clicks "no"
					if( JOptionPane.showConfirmDialog(this, "Do you want to reset the game?", "New Game", JOptionPane.YES_NO_OPTION) == 0) {
						resetGame();
					}
				} 
				
				// The game has ended --> Reset the game automatically
				else {
					resetGame();
				}
				
			}
			
			// Clicking the button to exit the game
			else if( but.equals(closeButton) ) {
				
				// The game hasn't ended --> Ask if the player want to close the game
				if( !game.hasGameEnded() ) {
					if( JOptionPane.showConfirmDialog(this, "Do you want to leave?", "Exit", JOptionPane.YES_NO_OPTION) == 0) {
						this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Close window
					}
				} 
				
				// The game has ended --> Close the game automatically
				else {
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Close window
				}
			}
			
			// Clicking any button of the grid (there aren't any JButton created)
			else {
				
				// 1. Get the int values of the coordinates of the buttons
				strCoordinates = but.getName().split(",");
				butRow = Integer.parseInt(strCoordinates[0]);
				butCol = Integer.parseInt(strCoordinates[1]);
				
				// 2. Make the move (in the logic of the game)
				
				game.move(butCol); 
				moveCounter ++;
				moveCounterNumberLabel.setText(Integer.toString(moveCounter));
				
				// 3. Make the move (in the GUI) --> Set an image to represent that the player made a move
				if(game.whoMoved(butRow, butCol) == 'X' ) {
					
					// Show the image of playerX in both states (disabled and enabled)
					gridButton[butRow][butCol].setIcon(playerX);
					gridButton[butRow][butCol].setDisabledIcon(playerX);
					
				} else {
					// Show the image of playerO in both states (disabled and enabled)
					gridButton[butRow][butCol].setIcon(playerO);
					gridButton[butRow][butCol].setDisabledIcon(playerO);
				}

				//setValidButton(butCol); // Call the function that enables/disables the buttons following the logic of the game
				
				// 4. Disable and enable the correct buttons + changing their color
				gridButton[butRow][butCol].setEnabled(false);
				gridButton[butRow][butCol].setBackground(new Color(238, 238, 238));
				
				// If the button which is clicked isn't on the first row --> Enable the button that is over the button which is currenly clicked
				if( butRow > 0 ) {
					gridButton[butRow - 1][butCol].setEnabled(true);
					gridButton[butRow -1][butCol].setBackground(Color.WHITE);
				}
				
				
				
				// 5. Check if the game has ended
				if(game.hasGameEnded()) {
					JOptionPane.showMessageDialog(this, game.getEndMessage(), "END", JOptionPane.INFORMATION_MESSAGE);
					
					// Deactivate all the buttons, in consequence of the ending of the game
					for (int i = 0; i < gridButton.length; i++) {		
						for (int j = 0; j < gridButton[i].length; j++) {
							gridButton[i][j].setEnabled(false);
						}
					}
				}
				
			}
			
		}

	}
	
	
	
	// ************
	// EXTRA METHODS (private methods)
	// *************

		
	/**
	 * Called when the user wants to reset the game, reseting all the values of the logic of the game and the GUI 
	 */
	private void resetGame() {
		
		game = new Game(); // Make another game
		moveCounter = 0;
		moveCounterNumberLabel.setText(Integer.toString(moveCounter));
		
		// Setting all the buttons the default icon (without image)
		for (int i = 0; i < gridButton.length; i++) {		
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j].setIcon(null); 
				gridButton[i][j].setDisabledIcon(null);
			}
		}
		
		restartBoard(); // Set the correct enabled buttons
	}
	
	
	/**
	 * Disables all the buttons of the array of gridButton except of the ones that are in the last row
	 */	
	
	private void restartBoard() {
		
		for (int i = 0; i < gridButton.length - 1; i++) {		
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j].setEnabled(false); // Disable all the buttons except for the ones in the last row
				gridButton[i][j].setBackground(new Color(238, 238, 238));
			}
		}
			
		for (int j = 0; j < gridButton[gridButton.length - 1].length; j++) {
			gridButton[gridButton.length - 1][j].setEnabled(true); // Enable the buttons of the last row
			gridButton[gridButton.length - 1][j].setBackground(Color.WHITE);
		}
		
		
	}
		
	public static void main (String args[]) {

        EventQueue.invokeLater(
                new Runnable() {

                	@Override
                    public void run() {
                        JFrame frame = new Connect4Game("Connecta 4");

                    }
                }
        );

    }


}