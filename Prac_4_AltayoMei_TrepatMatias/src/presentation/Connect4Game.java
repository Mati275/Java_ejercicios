package presentation;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

		playerX = new ImageIcon("img/PlayerX.jpg");
		playerO = new ImageIcon("img/PlayerO.jpg");

		
		gridContainer = new JPanel();
		btnContainer = new JPanel();
		game = new Game();
		
		// POSICIONAMIENTO
		
		//Ventana de juego
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		
		windowDimension = new Dimension (800,600);
		setSize(windowDimension);			// Por defecto
		setMinimumSize(windowDimension);	// Minimo
		
		//Parte de arriba para poder jugar
		gridContainer.setLayout(new GridLayout(game.getBoardRow(), game.getBoardCol()));
		
		gridContainer.setPreferredSize(new Dimension (800,450));
		
		this.getContentPane().add(gridContainer);
		
		//Parte de abajo, new game y close
		//TODO: btnContainer.setLayout(new FlowLayout());
		dimBtnContainer = new Dimension(800,50); 
		btnContainer.setPreferredSize(dimBtnContainer);	// Tamaño por defecto
		btnContainer.setMaximumSize(dimBtnContainer);	// Tamaño maximo	
		
		this.getContentPane().add(btnContainer);
		
		
		//Creacion de los btn y agregarlos a la GUI
		newGameButton = new JButton ("NEW GAME");
		closeButton = new JButton ("CLOSE");
		
		gridButton = new JButton [game.getBoardRow()][game.getBoardCol()];
		for (int i = 0; i < gridButton.length; i++) {		//Crear las celdas
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j] = new JButton();
				
				gridButton[i][j].setContentAreaFilled(false); // Flat the button (remove the background)
				
				gridButton[i][j].setName(i + "," + j);
				
				// TODO: ASK
				// Set the last button row enabled
				if( i == game.getBoardRow() - 1){
					gridButton[i][j].setEnabled(true);
				} else {
					gridButton[i][j].setEnabled(false);
				}
				
				gridButton[i][j].addActionListener(this);
				
				gridContainer.add(gridButton[i][j]);
			}
		}
		
		newGameButton = new JButton("New game");
		// TODO: ASK
		newGameButton.setName("newGameButton");
		newGameButton.addActionListener(this);
		
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		closeButton.setName("closeButton");
		
		btnContainer.add(newGameButton);
		btnContainer.add(closeButton);

		
		
		
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] strCoordinates;
		int butRow, butCol;
		
		JButton but;
		
		// Clicking the button to create a new game
		if( (e.getSource() instanceof JButton) ) {
			
			but = (JButton) e.getSource();
			
			if( but.getName().equals("newGameButton")) {
				
				if( !game.hasGameEnded() ) {
					if( JOptionPane.showConfirmDialog(this, "Do you want to reset the game?", "New Game", JOptionPane.YES_NO_OPTION) == 1) {
						resetGame();
					}
				}
				
			}
			
			// Clicking the button to exit the game
			else if(but.getName().equals("closeButton")) {
				if( !game.hasGameEnded() ) {
					if( JOptionPane.showConfirmDialog(this, "Do you want to leave?", "Exit", JOptionPane.YES_NO_OPTION) == 0) {
						this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Close window
					}
				} else {
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Close window
				}
			}
			else {
				
				strCoordinates = but.getName().split(",");
				butRow = Integer.parseInt(strCoordinates[0]);
				butCol = Integer.parseInt(strCoordinates[1]);
				
				game.move(butCol);
				
				if(game.whoMoved(butRow, butCol) == 'X' ) {
					//System.out.print("Hola");
					// To show the image in both states (disabled and enabled)
					gridButton[butRow][butCol].setIcon(playerX);
					gridButton[butRow][butCol].setDisabledIcon(playerX);
				} else {
					// To show the image in both states (disabled and enabled)
					gridButton[butRow][butCol].setIcon(playerO);
					gridButton[butRow][butCol].setDisabledIcon(playerO);
				}

				setValidButton(butCol);
				
				if(game.hasGameEnded()) {
					JOptionPane.showMessageDialog(this, game.getEndMessage(), "END", JOptionPane.INFORMATION_MESSAGE);
				}
				

			}
			
			
			
		}
		
		
		

		
	}
	
	
	
	// ************
	// EXTRA METHODS
	// *************
	
	// Called just after making a move
	private void setValidButton(int col) {
		
		boolean found = false; // Indicates if there's found a null value or not
		
		for(int i = game.getBoardRow() - 2; i >= 0 && !found; i-- ) {
			
			// If there's some button which isn't filled
			if( game.whoMoved(i, col) == ' ' ) {
				
				gridButton[i][col].setEnabled(true);
				gridButton[i + 1][col].setEnabled(false);
				found = true;
				
				//return;
			}
			
		} 
		
		// All the buttons are filled
		if( !found ) {
			gridButton[0][col].setEnabled(false);
		}
		
		
		
	}
	
	private void resetGame() {
		
		game = new Game(); // Make another game
		
	}
	
	
	
	
	
	
	
	
	public static void main (String args[]) {

        EventQueue.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                        JFrame frame = new Connect4Game("Connecta 4");
                        //frame.setSize(800,600);
                        frame.setVisible(true);

                    }
                }
        );

    }


}