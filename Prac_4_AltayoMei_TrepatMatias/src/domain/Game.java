package domain;


public class Game {

	// ATTRIBUTES
	private static char PLAYER1 = 'X';
	private static char PLAYER2 = 'O';
		
	private static Board board;
	private char currentPlayer;
	private char winner;
	
	
	// CONSTRUCTOR
	public Game() {
		board = new Board();
		
		currentPlayer = PLAYER1;
		winner = ' ';
		
	}
	
	// **********
	// METHODS
	// **********
	
	// GETTERS
	public int getBoardRow() {		
		return board.getNumRows();
	}

	public int getBoardCol() {		
		return board.getNumCols();
	}
	
	// Called when the currentPlayer make a move 
	public boolean move(int col) {
		boolean makeMove;
		
		// Do a move
		makeMove = board.move(col, currentPlayer); 
		
		// If a movement it's done --> change the currentPlayer
		if (makeMove) {
			if(currentPlayer == PLAYER1) {
				currentPlayer = PLAYER2;
			} else {
				currentPlayer = PLAYER1;
			}
		}
		
		return makeMove; // Return whether the move is done or not
	}
	
	// Check if any end condition has already happened
	public boolean hasGameEnded() {
		computeWinner();
		
		if (winner != ' ' || board.isFull()) {
			
			return true;
		}
		return false;
	}
	
	// Transform the board to an string ready to be printed
	public String boardToString() {
		return board.boardToString();
	}
	
	
	// Get an end message depending on the ending
	
	public String getEndMessage() {
		String endMessage = " "; // Initialize the local variable void: " "
		
		// Change the variable if the game has already ended
		
		// TODO: CHECK CHANGES
		if (hasGameEnded()) {
			if (winner == PLAYER1) {
				endMessage = "Player 1 wins the game";	 // Player X wins the game --> Player 1	wins the game
			}
			
			else if( winner == PLAYER2) {
				endMessage = "Player 2 wins the game";
			}
			
			else {
				endMessage = "No one wins! ";
			}
		}
		
		return endMessage;
	}
	
	// Change the attribute winner if any of both players won
	private void computeWinner() {
		if (winner == ' ') {
			if(board.hasPlayerWon(PLAYER1)) {
				winner = PLAYER1;
			} else if (board.hasPlayerWon(PLAYER2)) {
				winner = PLAYER2;
			}
		}
	}
	
	// TODO: CHECK CHANGES
	public char whoMoved( int row, int col ) {
		return board.getCellContent(row, col);
	}
	
	// ************
	// OTHER METHODS
	// ************
	

}