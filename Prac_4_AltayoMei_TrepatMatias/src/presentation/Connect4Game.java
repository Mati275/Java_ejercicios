package presentation;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.Game;

public class Connect4Game extends JFrame implements ActionListener{
	
	//Declarar paneles
	
	private JButton [][] gridButton;
	private Game game;
	private JButton newGameButton, closeButton;
	private ImageIcon playerX, playerO;	
	
	
	public Connect4Game (String message) {
		super(message);
		
		initComponents();
	}
	
	
	
	private void initComponents() { 	
		JPanel gridContainer, btnContainer;
		Dimension windowDimension, dimBtnContainer;

		// Set close operation --> EXIT_ON_CLOSE
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		gridContainer = new JPanel();
		btnContainer = new JPanel();
		game = new Game();
		
		// POSITIONING THE CONTAINERS
		
		// Main JFrame
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		
		windowDimension = new Dimension (800,600); // Value of pixels of the JFrame
		setSize(windowDimension);			
		setMinimumSize(windowDimension);	
		
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
		
		
		// CREATING THE BUTTONS, CONFIGURE THEM AND ADDING THEM TO THE GUI
		newGameButton = new JButton ("NEW GAME");
		closeButton = new JButton ("CLOSE");
		
		gridButton = new JButton [game.getBoardRow()][game.getBoardCol()]; // Setting the dimensions of the grid of buttons
		
		for (int i = 0; i < gridButton.length; i++) {		
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j] = new JButton(); 
				
				gridButton[i][j].setContentAreaFilled(false); // Flat the button (remove the background)
				
				gridButton[i][j].setName(i + "," + j); // Setting a name (the same as "ID") to identify the row and column of each button
				
				// TODO: ASK
				// Set the last button row enabled, otherwise, disable them
				
//				if( i != game.getBoardRow() - 1){
//					gridButton[i][j].setEnabled(false);
//				}
				
				gridButton[i][j].addActionListener(this); // Adding a listener to the event of clicking this button
				
				gridContainer.add(gridButton[i][j]); // Adding the button to the screen
			}
		}
		
		restartBoard(); // Call the method that disables all the buttons of the gridButton except for the ones in the last row
		
		// Creating the button and adding a listener for the ActionEvent
		newGameButton = new JButton("New game");
		newGameButton.addActionListener(this);

		// TODO: ASK (IF IT'S BETTER TO PUT THE NAME OR NOT)
		//newGameButton.setName("newGameButton");
		
		// Creating the button and adding a listener for the ActionEvent
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		//closeButton.setName("closeButton");
		
		// Adding the buttons to their container
		btnContainer.add(newGameButton);
		btnContainer.add(closeButton);

		
		
		setVisible(true); // Set visible the JFrame 
		
		// Create the imageIcon for each player
		playerX = new ImageIcon( new ImageIcon("img/PlayerX.jpg").getImage().getScaledInstance(gridButton[0][0].getBounds().width, gridButton[0][0].getBounds().height, Image.SCALE_SMOOTH)    );
		playerO = new ImageIcon( new ImageIcon("img/PlayerO.jpg").getImage().getScaledInstance(gridButton[0][0].getBounds().width, gridButton[0][0].getBounds().height, Image.SCALE_SMOOTH)    );
		
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] strCoordinates;
		int butRow, butCol;
		
		JButton but;
		
		// Cheking if the element which is clicked is a JButton
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
				
				game.move(butCol); // 2. Make the move (in the logic of the game)
				
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
				
				// 4. Disable and enable the correct buttons
				// TODO: ASK
				
				gridButton[butRow][butCol].setEnabled(false);
				// If the button which is clicked isn't on the first row --> Enable the button that is over the button which is currenly clicked
				if( butRow > 0 ) {
					gridButton[butRow - 1][butCol].setEnabled(true);
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
	 * Called just after making a move, in order to enable and disable teh 
	 * @param row
	 * @param col
	 */

//	private void setValidButton(int row, int col) {
//		
//		gridButton[row][col].setEnabled(true);
//		gridButton[row + 1][col].setEnabled(false);
		
//		boolean found = false; // Indicates if there's found a null value or not
//		
//		for(int i = game.getBoardRow() - 2; i >= 0 && !found; i-- ) {
//			
//			// If there's some button which isn't filled
//			if( game.whoMoved(i, col) == ' ' ) {
//				
//				gridButton[i][col].setEnabled(true);
//				gridButton[i + 1][col].setEnabled(false);
//				found = true;
//				
//				//return;
//			}
//			
//		} 
//		
//		// All the buttons are filled
//		if( !found ) {
//			gridButton[0][col].setEnabled(false);
//		}
		
		
		
	/**
	 * Called when the user wants to reset the game, reseting all the values of the logic of the game and the GUI 
	 */
	private void resetGame() {
		
		game = new Game(); // Make another game
		
		
		for (int i = 0; i < gridButton.length; i++) {		//Crear las celdas
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j].setIcon(null); 
				gridButton[i][j].setDisabledIcon(null);
			
				//Activa los btn de la parte inferior
//				if (i == game.getBoardRow() -1) {
//					gridButton[i][j].setEnabled(true);
//					
//					
//				} else {
//					gridButton[i][j].setEnabled(false);
//
//				}
			}
		}
		
		restartBoard();
	}
	
	
	/**
	 * Disables all the buttons of the array of gridButton except of the ones that are in the last row
	 */	
	private void restartBoard() {
		
		for (int i = 0; i < gridButton.length - 1; i++) {		
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j].setEnabled(false); // Disable all the buttons except for the ones in the last row
			}
		}
			
		for (int j = 0; j < gridButton[gridButton.length - 1].length; j++) {
			gridButton[gridButton.length - 1][j].setEnabled(true); // Enable the buttons of the last row
		}
		
		
	}
		
	
	
	
	
	
	
	public static void main (String args[]) {

        EventQueue.invokeLater(
                new Runnable() {

                	// TODO: ASK IF IT'S CORRECT CREATING THE JFRAME LIKE THIS
                    @Override
                    public void run() {
                        JFrame frame = new Connect4Game("Connecta 4");
                        //frame.setSize(800,600);
                        //frame.setVisible(true);

                    }
                }
        );

    }


}