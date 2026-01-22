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
		
		board = new char[6][7]; // Create the board
		
		
		char currentPlayer; // The char of the player who make the next move
		
		int gameOption; // 1 or 2, depending if the player want to start a new game or exit
		
		
		// Console positioning, AVOID CHANGING
		gameConsole.setLocation(halfWidthGame, 10);
		userInput.setLocation(halfWidthGame, halfHeightPlayer);

		gameConsole.setCursorVisible(false);

		//TODO: Show main menu
		gameOption = showMenuAndSelectOption(userInput);

		
		//TODO: Variables initialised with dummy values, feel free to change them if need it
		
		
		
		if (gameOption == 1) {
			exit = false;
		}
		
		else {
			exit = true;
		}
		
		while (!exit) {

			//Game begins
			
			//TODO: Create the game and prepare board
			//board = createBoardWithTie();
			initializeBoard(board); 

			
			//TODO: Show current board 
			showBoard(gameConsole, board);
			
			
			gameEnded = false; // To enter to the loop
			currentPlayer = 'X'; // Set the initial player
			
			while (!gameEnded) {
				
				//TODO: Interact with user and make move
				col = getAndValidateColumn(userInput, board[0].length);
				
				// While the current player cannot make a move
				while( !move(board, col, currentPlayer) ) {
					
					userInput.setForegroundColor(Color.red);
					userInput.println("Column already full. You need to enter another column");
					userInput.println();
					
					col = getAndValidateColumn(userInput, board[0].length);
					
				}
				
				
				// When the player makes a move, show the board
				
				showBoard(gameConsole, board);
				
				
				// If there is an end condition, stop the loop
				if( isBoardFull(board) || hasPlayerWon(board, currentPlayer) ) {
					
					gameEnded = true;
					
				}
				
				// If there isn't an end condition, continue the game, changing the current player
				else {
					
					if (currentPlayer == 'X') {
						currentPlayer = 'O';
					}
					else {
						currentPlayer = 'X';
					}
				
				}
					
				
				
			}
			
			//TODO: Whatever needs to be done when a game has finished
			gameConsole.println();
			showEndGameMessage(board, currentPlayer, gameConsole);
			
			//TODO: Do not forget that the user should be able to play a new game when another game has ended
			gameOption = showMenuAndSelectOption(userInput);
			
			
			if (gameOption == 1) {
				exit = false;
			}
			
			else {
				exit = true;
			}
			
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
		
		
		// Initialize the board with empty spaces
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
		int col;
		
		console.resetColor();
		console.print("Insert column number [0," + (nCols - 1) + "]: ");
		
		console.setForegroundColor(Color.GREEN);
		col = console.readInt();
		
		//console.resetColor();

		console.println();
		
		while (col < 0 || col > nCols - 1) {
				
			console.setForegroundColor(Color.RED);
			console.print ("Value must be between [0," + (nCols - 1) + "]. Insert a new value: ");
			
			console.setForegroundColor(Color.GREEN);
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
		
		
		// Start from the bottom
		for ( int i = board.length - 1; i >= 0; i-- ) {
			
			// Detect one void row in the column
			if (board[i][col] == ' ') {
				
				board[i][col] = currentPlayer;
				return true; // The column isn't filled --> The player can make a move
			}
			
		}
		// Don't detect any void position --> Column filled --> the player can't make a move
		return false;
	}
	
	
	/**
	 * Checks if the given board is full. Should return true if it's full, false otherwise 
	 * @param board
	 * @return
	 */
	private static boolean isBoardFull(char[][] board) {
		//TODO: Complete
		
		for (int i = 0; i < board.length; i++) {
			
			for (int j = 0; j < board[i].length; j++) {
				
				if (board [i][j] == ' ') {
					
					return false; // There is at least one void space in the board
				}
			}
		}
		
		return true; // The board is fill (there isn't any void space in the board)
	}
	
	/**
	 * Returns true if the player has won the game, false otherwise.
	 * @param board
	 * @param player
	 * @return
	 */
	private static boolean hasPlayerWon(char[][] board, char player) {
		//TODO: Complete
		
		if (checkRow (board, player) || 
			checkCol(board, player) || 
			checkDiagonal(board, player)) {
		
			return true;
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
		
		// Clears the text before
		userInput.clear();
		
		// Show both options
		userInput.resetColor();
		userInput.println ("Select option");
		userInput.println ("1. New game");
		userInput.println ("2. Exit");
		
		// Ask the user one number 
		userInput.setForegroundColor(Color.GREEN);
		num = userInput.readInt();
		userInput.println();

		
		// The number isn't between 1 and 2, so ask again the number
		while (num < 1 || num > 2) {
			
			// Show the error message
			userInput.setForegroundColor(Color.RED);
			userInput.println("Menu opion must be betwwn 1 (New Game) and 2 (Exit). Reenter your selection:");
			
			// Ask the user one number 
			userInput.setForegroundColor(Color.GREEN);
			num = userInput.readInt();
			userInput.println();
		}
		
		
		userInput.resetColor();
		
		return num; // The number must be between 1 and 2
	}
	
	
	private static void showBoard(JConsole gameConsole, char[][] board) {
		//TODO: Complete
		
		// Clear the board that was before (if it was one)
		gameConsole.clear();
		
		for (int i = 0; i < board.length; i++) {
			
			for (int j = 0; j < board[i].length; j++) {
				gameConsole.print("|");
				gameConsole.print(board [i][j]);
			}
			
			gameConsole.print("|");

			gameConsole.println();
			
		}
		
	}
	
	private static void showEndGameMessage(char[][]board, char player, JConsole console) {
		//TODO: Complete
		
		// The player make a connect4
		if( hasPlayerWon(board, player) ) { 
			console.print("Player " + player + " wins the game");
			
		// board filled	
		} else { 
			console.print("No one wins! =(");
		}
	}
		
	
	// ***********
	// Additional functions to check the possibilities of a plater winning in a row, column, or diagonal
	// ***********
	
	private static boolean checkRow (char[][] board, char player) {
		int count = 0;
		
		for (int i = 0; i < board.length; i++) { // Comprovar filas
			count = 0;
			
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
		int count = 0;

		for (int j = 0; j < board[0].length; j++) { // Comprovar columnas
			
			count = 0;
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
	
	private static boolean checkDiagonal(char[][] board, char player) {

		// Diagonal DOWN (up - left to down - right) 
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				// Check for diagonal down
				if (board[row][col] == player &&
						board[row + 1][col + 1] == player &&
						board[row + 2][col + 2] == player &&
						board[row + 3][col + 3] == player) {
					return true;
				}
			}
		}

		// Diagonal UP (down - left to up - right)
		for (int row = 3; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				// Check for diagonal up
				if (board[row][col] == player &&
						board[row - 1][col + 1] == player &&
						board[row - 2][col + 2] == player &&
						board[row - 3][col + 3] == player) {
					return true;
				}
			}
		}

		return false;
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
