package sessio1;

import java.awt.Color;
import java.awt.Toolkit;

import jconsole.JConsole;

public class Connect4Game {

	public static void main(String[] args) {

		//Two consoles, AVOID CHANGING
		JConsole gameConsole = new JConsole(30, 10);
		JConsole userInput = new JConsole(60, 15);

		// Screen size, AVOID CHANGING
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int halfWidthGame = screenWidth / 4;
		int halfHeightPlayer = screenHeight / 2;

		//Variables
		char[][] board; 		
		int col;
		boolean gameEnded;
		boolean exit;
		
		//Additional vars
		//TODO: Add variables here if you need them
		
		int gameOption;
		
		
		// Console positioning, AVOID CHANGING
		gameConsole.setLocation(halfWidthGame, 10);
		userInput.setLocation(halfWidthGame, halfHeightPlayer);

		gameConsole.setCursorVisible(false);

		//TODO: Show main menu
		//TODO: Variables initialised with dummy values, feel free to change them if need it
		gameEnded = false;
		exit = false;

		while (!exit) {

			//Game begins
			
			gameOption = showMenuAndSelectOption(userInput);
			
			//TODO: Create the game and prepare board
			
			initializeBoard(board); //comenta esto y ya esta, me lo miraré en casa (MATIAS 2026) CERDO!!
			
			//TODO: Show current board 
			
			showBoard(gameConsole, board);

			while (!gameEnded) {
				
				//TODO: Interact with user and make move
				col = getAndValidateColumn(userInput, board.length);
				
				
			}
			
			//TODO: Whatever needs to be done when a game has finished
			
			//TODO: Do not forget that the user should be able to play a new game when another game has ended

		}
		userInput.setCursorPosition(0, userInput.getRows()-1);
		userInput.print("Press any key to exit...");
		userInput.readKey();
		System.exit(0);

	}
	
	//Private static procedures below. Some of them return dummy values, make sure to change them as appropriate.
	
	/**
	 * Initializes board with empty spaces
	 * @param board
	 */
	private static void initializeBoard(char[][] board) {
		//TODO: Complete
		
		board = new char [6][7];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board [i][j] = ' ';
			}
		}
		
	}
	
	/**
	 * Interacts with user to obtain some valid (within the given parameters) column.
	 * @param console
	 * @param nCols
	 * @return
	 */
	private static int getAndValidateColumn(JConsole console, int nCols) {
		//TODO: Complete
		int col;
		
		console.print("Insert column number [0," + (nCols - 1) + "]: ");
		console.setForeground(Color.GREEN);
		col = console.readInt();
		console.resetColor();
		console.println();
		
		while (col >= 0 && col < nCols) {
				
			console.setForeground(Color.RED);
			console.print ("Value must be between [0," + (nCols - 1) + "]. Insert a new value: ");
			col = console.readInt();
			console.resetColor();
			console.println();
		}
		
		return col;
	}
	
	
	
	/**
	 * Col must be valid and player too. Returns true if move can be performed, false otherwise.
	 * @param board
	 * @param col
	 * @param player
	 * @return
	 */
	private static boolean move(char[][] board, int col, char currentPlayer) {
		//TODO: Complete
		
		for (int i = 0; i<board.length; i++) {
			if (board[i][col] == ' ') { // Si el tablero esta vacío
				board[i][col] = currentPlayer;
				return true; // Lo llena
			}
		}
		
		// Ya esta lleno toda la columna
		return false;
	}
	
	
	/**
	 * Checks if the given board is full. Should return true if it's full, false otherwise 
	 * @param board
	 * @return
	 */
	private static boolean isBoardFull(char[][] board) {
		//TODO: Complete
		
		for (int i = 0; i<board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board [i][j] == ' ') {
					return false; // Si encuentra algun vacío en la taula
				}
			}
		}
		
		// Devuelve si no hay ningun hueco en la taula
		return true;
	}
	
	/**
	 * Returns true if the player has won the game, false otherwise.
	 * @param board
	 * @param player
	 * @return
	 */
	private static boolean hasPlayerWon(char[][] board, char player) {
		//TODO: Complete
		
		if (checkRow (board, player) == true || 
				checkCol(board, player) == true || 
				checkDiagonal(board, player) == true) {
		
			return true;
		}
		
		return false;
	}
		
	
	private static boolean checkRow (char[][] board, char player) {
		for (int i = 0; i < board.length; i++) { // Comprovar filas
			int count = 0;
			for (int j = 0; j < board[i].length; j++) {
				// El tablero tiene char del jugador que sean las mismas
				if (board[i][j] == player) { 
					count ++;
					
					if(count == 4) {
						return true;
					} 
					
				} else {
					// Si no hay 4 seguidos el contador vuelve a 0
					count = 0; 
				}
			}
		}
		return false;
	}
	
	private static boolean checkCol (char[][] board, char player) {
		for (int j = 0; j < board[0].length; j++) { // Comprovar columnas
			int count = 0;
			for (int i = 0; i < board.length; i++) {
				// El tablero tiene char del jugador que sean las mismas
				if (board[i][j] == player) { 
					count ++;
					
					if(count == 4) {
						return true;
					} 
					
				}  else {
					// Si no hay 4 seguidos el contador vuelve a 0
					count = 0; 
				}
			}
		}
		return false;
	}
	
	private static boolean checkDiagonal (char[][] board, char player) {
		int count = 0;

		for (int i = 0; i < board.length-3; i++) { // Comprovar diagonales por columnas
			for (int row = i, col = 0; row < board.length; row++, col++) {
				if (board [row][col] == player) {
					count++;
					
					if (count == 4) {
						return true;
					}
					
				} else {
					count = 0;
				}
			}
		}		
		
		for (int j = 0; j < board[0].length-3; j++) { // Comprovar diagonales por filas 
			for (int row = 0, col = j; row < board.length; row++, col++) {
				if (board [row][col] == player) {
					count++;
					
					if (count == 4) {
						return true;
					}
					
				} else {
					count = 0;
				}
			}
		}
		
		for (int j = board[0].length; j > 2 ; j--) { // Comprovar diagonales por fila del final
			for (int row = 0, col = j; row < board.length; row++, col--) {
				if (board [row][col] == player) {
					count++;
					
					if (count == 4) {
						return true;
					}
					
				} else {
					count = 0;
				}
			}
		}		
		
		for (int i = 0; i < board.length-3 ; i++) { // Comprovar diagonales por columnas del final
			for (int row = i, col = board[0].length-1; row < board.length; row++, col--) {
				if (board [row][col] == player) {
					count++;
					
					if (count == 4) {
						return true;
					}
					
				} else {
					count = 0;
				}
			}
		}		
		
		return false;
	}

	/**
	 * Shows main menu and returns option selected by user
	 * 
	 * @param console
	 * @return
	 */
	private static int showMenuAndSelectOption(JConsole userInput) {
		//TODO: Complete
		int num;
		
		userInput.println ("Select option");
		userInput.println ("1. New game");
		userInput.println ("2. Exit");
		userInput.setForeground(Color.GREEN);
		num = userInput.readInt();
		
		while (num != 1 || num != 2) {
			userInput.setForeground(Color.RED);
			userInput.println ("Menu opion must be betwwn 1 (New Game) and 2 (Exit). Reenter your selection:");
			userInput.setForeground(Color.GREEN);
			num = userInput.readInt();
		}
		
		userInput.resetColor();
		
		return num;
	}
	
	
	private static void showBoard(JConsole gameConsole, char[][] board) {
		//TODO: Complete
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				gameConsole.print(board [i][j]);
			}
			gameConsole.println();
		}
		
	}
	
	private static void showEndGameMessage(char[][]board, char player, JConsole console) {
		//TODO: Complete
		
		if(hasPlayerWon(board, player)) { // Tablero tiene algun connect4
			console.print("Player " + player + " wins the game");
			
		} else { //Si el tablero esta lleno
			console.print("No one wins! =(");
		}
	}
		
	
	/*Auxiliary procedures and functions to test code. DO NOT MODIFY*/
	
	/**
	 * Generates a board that allows player 1 to win by placing token on column 0.
	 * @return
	 */
	private static char[][] createBoardWithKDiagonalWin(){
		char[] testRow1 = {' ',' ',' ',' ',' ',' ',' '};
		char[] testRow2 = {' ',' ',' ',' ',' ',' ',' '};	
		char[] testRow3 = {' ','O','O',' ',' ',' ',' '};
		char[] testRow4 = {'O','X','O','X',' ','X','O'};
		char[] testRow5 = {'X','O','X','O','O','X','X'};
		char[] testRow6 = {'X','O','X','X','O','O','X'};

		char[][] testBoard3 = {testRow1,testRow2,testRow3,testRow4,testRow5,testRow6}; //K-diagonal below main
		
		return testBoard3;

	}
	
	/**
	 * Generates a board that allows player 1 to win by placing token on column 5.
	 * @return
	 */
	private static char[][] createBoardWithSkewDiagonalWin(){
		char[] testRow1 = {' ',' ',' ',' ',' ',' ',' '};
		char[] testRow2 = {' ',' ',' ',' ',' ',' ',' '};
		char[] testRow3 = {'O','X','O','X',' ','X','O'};
		char[] testRow4 = {'X','O','X','O','O','X','X'};
		char[] testRow5 = {' ',' ',' ',' ',' ',' ',' '};
		char[] testRow6 = {'X','O','O',' ','X','O',' '};
				
		
		char[][] testBoard4 = {testRow1,testRow2,testRow5,testRow6,testRow3,testRow4};//Skew diagonal
		
		return testBoard4;
	}
	
	/**
	 * Generates a board that leads to a tie. Player 1 can only play token on column 6
	 * @return
	 */
	private static char[][] createBoardWithTie(){
		char[][] board = {
			    { 'O', 'O', 'X', 'X', 'O', 'O', ' ' }, 
			    { 'X', 'X', 'O', 'O', 'X', 'X', 'O' },
			    { 'O', 'O', 'X', 'X', 'O', 'O', 'X' },
			    { 'X', 'X', 'O', 'O', 'X', 'X', 'O' },
			    { 'O', 'O', 'X', 'X', 'O', 'O', 'X' },
			    { 'X', 'X', 'O', 'O', 'X', 'X', 'O' }  
			};
		
		return board;
	}

}
