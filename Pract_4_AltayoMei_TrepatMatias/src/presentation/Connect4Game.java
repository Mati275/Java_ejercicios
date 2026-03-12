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
	
	
	public Connect4Game (String message) {

		super(message);
		
		initComponents();
	}
	
	private void initComponents() { 	
		JPanel gridContainer, btnContainer;
		Dimension windowDimension, dimBtnContainer;
		
		playerX = new ImageIcon("img/mei.jpg");
		playerO = new ImageIcon("img/matias.jpeg");


		gridContainer = new JPanel();
		btnContainer = new JPanel();
		game = new Game();
		
		//POSIOCIONAMIENTO
		
		//Ventana de juego
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		
		windowDimension = new Dimension (800,600);
		setSize(windowDimension);			// Por defecto
		setMinimumSize(windowDimension);	// Minimo
		
		
		//Parte de arriba para poder jugar
		gridContainer.setLayout(new GridLayout(game.getBoardRow(), game.getBoardCol()));
		
		gridContainer.setPreferredSize(new Dimension (800,450));
		
		this.getContentPane().add(gridContainer);		//Panel de JFrame
		
		//Parte de abajo, new game y close
		//TODO: btnContainer.setLayout(new FlowLayout());
		dimBtnContainer = new Dimension(800,50); 
		btnContainer.setPreferredSize(dimBtnContainer);	// Tamaño por defecto
		btnContainer.setMaximumSize(dimBtnContainer);	// Tamaño maximo	
		
		this.getContentPane().add(btnContainer);	//Panel de JFrame
		
		
		//Creacion de los btn y agregarlos a la GUI
		newGameButton = new JButton ("NEW GAME");
		closeButton = new JButton ("CLOSE");
		
		gridButton = new JButton [game.getBoardRow()][game.getBoardCol()];
		
		
		for (int i = 0; i < gridButton.length; i++) {		//Crear las celdas
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j] = new JButton();
				gridButton[i][j].setName(i + "," + j); // 034,0535 --> ["034","0535"]
				
				gridButton[i][j].setContentAreaFilled(false); //Per què no sembli un botó
				
				// Poner la base activada de btn
				if (i == game.getBoardRow() -1) {
					gridButton[i][j].setEnabled(true);
					
					
				} else {
					gridButton[i][j].setEnabled(false);

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
					if (JOptionPane.showConfirmDialog(this, "¿Quieres reiniciar la partida?", "Nuevo juego", JOptionPane.YES_NO_OPTION) == 1) {
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
				
				
				// 3. Asignar img
				
				if (game.whoMoved(btnRow, btnCol) == 'X') {
					gridButton[btnRow][btnCol].setIcon(playerX); //Per mostrar imatge en el botó habilitat
					gridButton[btnRow][btnCol].setDisabledIcon(playerX); //Per mostrar imatge en el botó quan deshab.
				} else {
					gridButton[btnRow][btnCol].setIcon(playerO); //Per mostrar imatge en el botó habilitat
					gridButton[btnRow][btnCol].setDisabledIcon(playerO); //Per mostrar imatge en el botó quan deshab.
				
				}
				
				// 4. Llamar funcion (cambiar btn habilitados)
				
				setValidButton(btnCol);
				
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
					
					
				} else {
					gridButton[i][j].setEnabled(false);

				}
			}
		}
		
	}
	
	// Llama después de hacer el movimiento
	private void setValidButton(int col) {
		
		boolean found = false;
		
		for (int i = game.getBoardRow()-2 ; i >= 0 && !found; i--){
			if (game.whoMoved(i, col) == ' ') {
				gridButton[i][col] .setEnabled(true); 		//Activa el boton
				gridButton[i+1][col] .setEnabled(false);	//Desactiva el btn
				found = true;
			}
		}
		
		if (!found) {	//Botones estan todos llenos
			gridButton[0][col] .setEnabled(false);	//Desactiva el btn
		}
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