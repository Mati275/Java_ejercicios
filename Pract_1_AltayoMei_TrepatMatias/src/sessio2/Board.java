package sessio2;

public class Board {
	
	// ATTRIBUTES
	private final static int NUM_ROWS=6; 
	private final static int NUM_COLS=7; 

	private Cell [][] cells; // Declare the array
		
	// CONSTRUCTOR
	public Board () {
		cells = new Cell [NUM_ROWS][NUM_COLS]; // Create the array
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(); // Create every cell
			}
		}
	}
	
	// **********
	// METHODS
	// **********
	
	// GETTERS
	public int getNumRows() {		
		return NUM_ROWS;
	}
	
	public int getNumCols() {		
		return NUM_COLS;
	}
	
	// Get the content of a cell in a specific row and column
	public char getCellContent(int row, int col) {	
		return cells[row][col].getContent();
	}
	

	// Called when the currentPlayer make a move 
	public boolean move(int col, char currentPlayer) {
		
		// Start from the bottom
		for ( int i = cells.length - 1; i >= 0; i-- ) {
			
			// Detect one void row in the column
			if (cells[i][col].getContent() == ' ') {
				
				cells[i][col].addMarker(currentPlayer); // Add the currentPlayer 
				return true; // The column isn't totally filled --> The currentPlayer can make a move
			}
		}
		// Don't detect any void position --> Column totally filled --> the currentPlayer can't make a move
		return false;
	}
	
	// Check if the currentPlayer has won 
	public boolean hasPlayerWon (char currentPlayer) {
		
		// Check all the win conditions
		if (checkRow(currentPlayer) || 
			checkCol(currentPlayer) || 
			checkDiagonal(currentPlayer)) {
			
			return true; 
		}
		return false;
	}
	
	// Check if the board is full
	public boolean isFull() {
		
		for (int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[i].length; j++) {
				
				if (cells [i][j].isEmpty()) {
					return false; // If one cell is empty --> return false
				}
			}
		}
		return true; // If all of the cells are filled --> return true
	}
		
	// Transform the board to an string ready to be printed
	public String boardToString() {
		String board = "";
			
		for (int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[i].length; j++){
				
				board = board + "|"+ cells[i][j].getContent();
			}
		
			board = board + "|\n";
		}	
		return board;
	}
	
	
	// OTHERS METHODS //
	
	public boolean checkRow (char currentPlayer) {
		int count = 0;
		
		for (int i = 0; i < cells.length; i++) {
			
			count = 0;
			for (int j = 0; j < cells[i].length; j++) {
				
				if (cells[i][j].getContent() == currentPlayer) { 
					count ++;
					
					if(count == 4) {
						return true;
					}
					
				} else {

					count = 0; 
				}
			}
		}
		return false;
	}
	
	
	public boolean checkCol (char currentPlayer) {
		int count = 0;

		for (int j = 0; j < cells[0].length; j++) { 
			
			count = 0;
			for (int i = 0; i < cells.length; i++) {

				if (cells[i][j].getContent() == currentPlayer) { 
					count ++;
					
					if(count == 4) {
						return true;
					} 
					
				}  else {
					count = 0; 
				}
			}
		}
		return false;
	}
	
	public boolean checkDiagonal(char player) {

		// Diagonal DOWN (up - left to down - right) 
		for (int row = 0; row < cells.length - 3; row++) {
			for (int col = 0; col < cells[0].length - 3; col++) {
				// Check for diagonal down
				if (cells[row][col].getContent() == player &&
						cells[row + 1][col + 1].getContent() == player &&
						cells[row + 2][col + 2].getContent() == player &&
						cells[row + 3][col + 3].getContent() == player) {
					return true;
				}
			}
		}

		// Diagonal UP (down - left to up - right)
		for (int row = 3; row < cells.length; row++) {
			for (int col = 0; col < cells[ row ].length - 3; col++) {
				// Check for diagonal up
				if (cells[row][col].getContent() == player &&
						cells[row - 1][col + 1].getContent() == player &&
						cells[row - 2][col + 2].getContent() == player &&
						cells[row - 3][col + 3].getContent() == player) {
					return true;
				}
			}
		}

		return false;
	}
	

}