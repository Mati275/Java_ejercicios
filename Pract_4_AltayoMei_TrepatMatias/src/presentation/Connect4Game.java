package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
	
	//Declarar paneles
	
	private JButton [][] gridButton;
	private Game game;
	private JButton newGameButton, closeButton;
	private ImageIcon playerX, playerO;	
	
	//New atributes
	private String playerXName;
	private String playerOName;
	private int moveCounter;
	private JLabel moveCounterNumberLabel;
	
	
	public Connect4Game (String message) {

		super(message);
		
		initComponents();
	}
	
	private void initComponents() { 	
		JPanel gridContainer, btnContainer, playerInfoContainer;
		Dimension windowDimension, dimBtnContainer, dimPlayerInfoContainer;
		JLabel labelP1, labelP1Name, labelP2, labelP2Name, labelMove;
		Font font, boldFont;
		moveCounter = 0;

		gridContainer = new JPanel();
		btnContainer = new JPanel();
		playerInfoContainer = new JPanel();
		
		game = new Game();
		
		//POSIOCIONAMIENTO
		
		//Ventana de juego
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		
		windowDimension = new Dimension (800,600);
		setSize(windowDimension);			// Por defecto
		setMinimumSize(windowDimension);	// Minimo
		
		//Parte de arriba del player
		//playerInfoContainer.setLayout(new FlowLayout()); 
		
		dimPlayerInfoContainer = new Dimension (Integer.MAX_VALUE, 70);
		playerInfoContainer.setMaximumSize(dimPlayerInfoContainer);
		
		playerInfoContainer.setBackground(new Color (220, 220, 220));
		this.getContentPane().add(playerInfoContainer);		
		
		
		//Parte de arriba para poder jugar (medio)
		gridContainer.setLayout(new GridLayout(game.getBoardRow(), game.getBoardCol()));
		
		gridContainer.setPreferredSize(new Dimension (800,450));
		
		this.getContentPane().add(gridContainer);		//Panel de JFrame
		
		//Parte de abajo, new game y close
		//TODO: btnContainer.setLayout(new FlowLayout());
		dimBtnContainer = new Dimension(800,50); 
		btnContainer.setPreferredSize(dimBtnContainer);	// Tamaño por defecto
		btnContainer.setMaximumSize(dimBtnContainer);	// Tamaño maximo	
		
		this.getContentPane().add(btnContainer);	//Panel de JFrame
		
		//Pedir name
		
		playerXName = JOptionPane.showInputDialog("Nombre jugador 1: ");
		
		playerOName = JOptionPane.showInputDialog("Nombre jugador 2: ");
		
		//creacion de etiquetas
		
		
		font = new Font (Font.SANS_SERIF, Font.PLAIN, 20); //Normal
		boldFont = new Font (Font.SANS_SERIF, Font.BOLD, 20);	//Bold
		
		labelP1 = new JLabel("Player 1: ");
		labelP1Name = new JLabel(playerXName);
		labelP2 = new JLabel("Player 2");
		labelP2Name = new JLabel(playerOName);
		labelMove = new JLabel("Move counter");
		moveCounterNumberLabel = new JLabel(Integer.toString(moveCounter));
		   
		labelP1.setFont(boldFont);
		labelP1Name.setFont(font);
		
		labelP2.setFont(boldFont);
		labelP2Name.setFont(font);

		labelMove.setFont(boldFont);
		labelP1Name.setFont(font);
		
		playerInfoContainer.add(labelP1);
		playerInfoContainer.add(labelP1Name);
		playerInfoContainer.add(Box.createHorizontalStrut(20));

		
		playerInfoContainer.add(labelP2);
		playerInfoContainer.add(labelP2Name);
		playerInfoContainer.add(Box.createHorizontalStrut(30));

		playerInfoContainer.add(labelMove);
		playerInfoContainer.add(moveCounterNumberLabel);

		
		//Creacion de los btn y agregarlos a la GUI
		newGameButton = new JButton ("NEW GAME");
		closeButton = new JButton ("CLOSE");
		
		gridButton = new JButton [game.getBoardRow()][game.getBoardCol()];
		
		
		for (int i = 0; i < gridButton.length; i++) {		//Crear las celdas
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j] = new JButton();
				gridButton[i][j].setName(i + "," + j); // 034,0535 --> ["034","0535"]
				
				gridButton[i][j].setContentAreaFilled(false); //Per què no sembli un botó
				
				gridButton[i][j].setOpaque(true);
				// Poner la base activada de btn
				if (i != game.getBoardRow() -1) {
					gridButton[i][j].setEnabled(false);

				}
				else {
					// Activado
					gridButton[i][j].setBackground(Color.WHITE);
				}
				
				gridButton[i][j].addActionListener(this);
				gridContainer.add(gridButton[i][j]);

			}
		}
		
		newGameButton = new JButton ("New game");
		//Nombre del btn newGameButton
		newGameButton.setName("newGameButton");
		newGameButton.addActionListener(this);
		
		closeButton = new JButton ("Close");
		closeButton.setName("closeButton");
		closeButton.addActionListener(this);

		btnContainer.add(newGameButton);
		btnContainer.add(closeButton);

		this.setVisible(true);
		
		playerX = new ImageIcon( new ImageIcon("img/mei.jpg").getImage().getScaledInstance(gridButton[0][0].getBounds().width, gridButton[0][0].getBounds().height, Image.SCALE_SMOOTH)    );
		playerO = new ImageIcon( new ImageIcon("img/matias.jpeg").getImage().getScaledInstance(gridButton[0][0].getBounds().width, gridButton[0][0].getBounds().height, Image.SCALE_SMOOTH)    );

		
		
	}
	
	
	
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String[] strCoordinates;
		int btnRow, btnCol;
		JButton btn;
		
		//e.Source --> Obtiene el objeto/elemento en pantalla en el que se produce el evento		
		// Si e es un btn
		if (e.getSource() instanceof JButton) {		//instanceof --> compara que si el objeto es un btn
			
			btn = (JButton) e.getSource();
			
			if (btn.getName().equals("newGameButton")){
				if (!game.hasGameEnded()) { //Crear el pop up de que no se a acabajo
					if (JOptionPane.showConfirmDialog(this, "¿Quieres reiniciar la partida?", "Nuevo juego", JOptionPane.YES_NO_OPTION) == 0) {
						restartGame();
					} 
				} else {
					restartGame();
				}
				
			} else  if (btn.getName().equals("closeButton")) {
				
				if (!game.hasGameEnded()) { //Crear el pop up de que no se a acabajo
					if (JOptionPane.showConfirmDialog(this, "¿Quieres salir?", "Salir", JOptionPane.YES_NO_OPTION) == 0) {
						this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
					} 
				} else {
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				}
				
			} else {		//pulsa el user
				
				// "9,6" --> ["9", "6"]
				
				// 1. Pillar las cordenadas
				strCoordinates = btn.getName().split(",");
				
				btnRow = Integer.parseInt(strCoordinates[0]);
				btnCol = Integer.parseInt(strCoordinates[1]);

				// 2. Rellenar 
				
				game.move(btnCol);
				moveCounter++;
				
				moveCounterNumberLabel.setText(Integer.toString(moveCounter));
				
				// 3. Asignar img
				
				if (game.whoMoved(btnRow, btnCol) == 'X') {
					gridButton[btnRow][btnCol].setIcon(playerX); //Per mostrar imatge en el botó habilitat
					gridButton[btnRow][btnCol].setDisabledIcon(playerX); //Per mostrar imatge en el botó quan deshab.
				} else {
					gridButton[btnRow][btnCol].setIcon(playerO); //Per mostrar imatge en el botó habilitat
					gridButton[btnRow][btnCol].setDisabledIcon(playerO); //Per mostrar imatge en el botó quan deshab.
				
				}
				
				// 4. Llamar funcion (cambiar btn habilitados)
				
				//setValidButton(btnCol);
				
				gridButton[btnRow][btnCol].setEnabled(false);
				gridButton[btnRow][btnCol].setBackground(new Color (238, 238, 238));

				
				if (btnRow > 0) {
					gridButton[btnRow-1][btnCol].setEnabled(true);
					gridButton[btnRow-1][btnCol].setBackground(Color.WHITE);

				 }
				
				
				// 5. Comprobar si ha acabado el juego
				
				if (game.hasGameEnded()) {
					JOptionPane.showMessageDialog(this, game.getEndMessage() , "Fin Juego", JOptionPane.INFORMATION_MESSAGE);	// Salir popo up de quien a ganado 
					
					//Inabilitar los btn cuyando se termina la partida
					for (int i = 0; i < gridButton.length; i++) {		//Crear las celdas
						for (int j = 0; j < gridButton[i].length; j++) {
							gridButton[i][j].setEnabled(false);
						}
					}
					

				} 
				
			}
		}
		
	}
	
	
	private void restartGame() {
		game = new Game();
		
		//Elimina las img del tablero
		
		for (int i = 0; i < gridButton.length; i++) {		//Crear las celdas
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j].setIcon(null); //Per mostrar imatge en el botó habilitat
				gridButton[i][j].setDisabledIcon(null); //Per mostrar imatge en el botó quan deshab.
			
				//Activa los btn de la parte inferior
				if (i == game.getBoardRow() -1) {
					gridButton[i][j].setEnabled(true);
					gridButton[i][j].setBackground(Color.WHITE);

					
					
				} else {
					gridButton[i][j].setEnabled(false);
					gridButton[i][j].setBackground(new Color (238, 238, 238));


				}
			}
		}
		
	}
	
//	// Llama después de hacer el movimiento
//	private void setValidButton(int col) {
//		
//		boolean found = false;
//		
//		for (int i = game.getBoardRow()-2 ; i >= 0 && !found; i--){
//			if (game.whoMoved(i, col) == ' ') {
//				gridButton[i][col] .setEnabled(true); 		//Activa el boton
//				gridButton[i+1][col] .setEnabled(false);	//Desactiva el btn
//				found = true;
//			}
//		}
//		
//		if (!found) {	//Botones estan todos llenos
//			gridButton[0][col] .setEnabled(false);	//Desactiva el btn
//		}
//	}


	
	public static void main (String args[]) {
	
	    EventQueue.invokeLater(
	            new Runnable() {
	
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