package sessio2;

import sessio2.Board;

public class Game {

	private static char PLAYER1 = 'X';
	private static char PLAYER2 = 'O';
	
	//TODO: Add attributes as described in document
	
	private Board board;
	private char currentPlayer;
	private char winner;
	
	//TODO: Add methods
	
	//CONSTRUCTOR
	public Game() {
		board = new Board();
		
		currentPlayer = PLAYER1;
		winner = ' ';
		
	}
	
	// GETTERS
	public int getBoardRow() {		
		return board.getNumRows();
	}

	public int getBoardCol() {		
		return board.getNumCols();
	}
	
	public boolean move(int col) {
		boolean makeMove;
		
		makeMove = board.move(col, currentPlayer);
		
		if (makeMove) {
			if(currentPlayer == PLAYER1) {
				currentPlayer = PLAYER2;
			} else {
				currentPlayer = PLAYER1;
			}
		}
		
		return makeMove;
	}
	
	public boolean hasGameEnded() {
		computeWinner();
		
		if (winner != ' ' || board.isFull()) {
			
			return true;
		}
		return false;
	}
	
	public String boardToString() {
		return board.boardToString();
	}
	
	
	public String getEndMessage() {
		String endMessage = " ";
		
		if (hasGameEnded()) {
			if (winner == PLAYER1 || winner == PLAYER2) {
				endMessage = "Player " + winner + " wins the game";				
			} else {
				endMessage = "No one wins! ";
			}
		}
		
		return endMessage;
	}
	
	
	private void computeWinner() {
		if (winner == ' ') {
			if(board.hasPlayerWon(PLAYER1)) {
				winner = PLAYER1;
			} else if (board.hasPlayerWon(PLAYER2)) {
				winner = PLAYER2;
			}
		}
	}
	
	
}
