package presentation;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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

		gridContainer = new JPanel();
		btnContainer = new JPanel();
		game = new Game();
		
		//Ventana de juego
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		
		windowDimension = new Dimension (800,600);
		setSize(windowDimension);			// Por defecto
		setMinimumSize(windowDimension);	// Minimo
		
		//Parte de arriba para poder jugar
		gridContainer.setLayout(new GridLayout(game.getBoardRow(), game.getBoardCol()));
		
		gridContainer.setPreferredSize(new Dimension (800,450));
		
		//Parte de abajo, new game y close
		//TODO: btnContainer.setLayout(new FlowLayout());
		dimBtnContainer = new Dimension(800,50); 
		btnContainer.setPreferredSize(dimBtnContainer);	// Tamaño por defecto
		btnContainer.setMaximumSize(dimBtnContainer);	// Tamaño maximo	
		
		//Creacion de los btn y agregarlos a la GUI
		newGameButton = new JButton ("NEW GAME");
		closeButton = new JButton ("CLOSE");
		
		gridButton = new JButton [game.getBoardRow()][game.getBoardCol()];
		for (int i = 0; i < gridButton.length; i++) {		//Crear las celdas
			for (int j = 0; j < gridButton[i].length; j++) {
				gridButton[i][j] = new JButton();
			}
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
