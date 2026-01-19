package sessio2;

import sessio2.Cell;

public class Board {
	
	private final static int NUM_ROWS=6; 
	private final static int NUM_COLS=7; 

	//TODO: Add additional private attributes. 
	private Cell [][] cells; // Declare the array
	
	//TODO: Add methods as described in the document
	
	//Constructor
	public Board () {
		cells = new Cell [NUM_ROWS][NUM_COLS]; // Create the array
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(); // Create every cell
			}
		}
	}
	
	// GETTERS
	public int getNumRows() {		
		return NUM_ROWS;
	}
	
	public int getNumCols() {		
		return NUM_COLS;
	}
	
	
	public char getCellContent(int row, int col) {	
		Cell currentCell;
		char content;
		
		currentCell = cells [row][col];
		content = currentCell.getContent();
		
		return content;
	}
	

	// Called when the current currentPlayer make a move 
	public boolean move(int col, char currentPlayer) {
		
		// Start from the bottom
		for ( int i = cells.length - 1; i >= 0; i-- ) {
			
			// Detect one void row in the column
			if (cells[i][col].getContent() == ' ') {
				
				cells[i][col].addMarker(currentPlayer);
				return true; // The column isn't filled --> The currentPlayer can make a move
			}
		}
		// Don't detect any void position --> Column filled --> the currentPlayer can't make a move
		return false;
	}
	
	public boolean hasPlayerWon (char currentPlayer) {
		
		if (checkRow(currentPlayer) || 
			checkCol(currentPlayer) || 
			checkDiagonal(currentPlayer)) {
			
			return true;
		}
		return false;
	}
	
	public boolean isFull() {
		
		// Recorre las filas
		for (int i = 0; i < cells.length; i++){
			
			// Recorre las columnas, y la i esta fijada
			for (int j = 0; j < cells[i].length; j++) {
				
				/* Mira en las coordenadas si esta vacio, isEmpty == true, entoces
				 * devuelve un false, porque esta vacio */
				if (cells [i][j].isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
		
	public String boardToString() {
		String board = "";
		
		for (int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[i].length; j++){
				
				board = board + cells[i][j];
			}
		}	
		return board;
	}
	
	
	// OTHERS FUNCTIONS //
	public boolean checkRow (char currentPlayer) {
		int count = 0;
		
		for (int i = 0; i < cells.length; i++) { // Comprovar filas
			
			count = 0;
			for (int j = 0; j < cells[i].length; j++) {
				
				// El tablero tiene char del jugador que sean las mismas
				if (cells[i][j].getContent() == currentPlayer) { 
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
	
	
	public boolean checkCol (char currentPlayer) {
		int count = 0;

		for (int j = 0; j < cells[0].length; j++) { // Comprovar columnas
			
			count = 0;
			for (int i = 0; i < cells.length; i++) {
				// El tablero tiene char del jugador que sean las mismas
				if (cells[i][j].getContent() == currentPlayer) { 
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
	
	public boolean checkDiagonal (char currentPlayer) {
		int count = 0;

		
		// Check every row of column 0 and the last column its diagonals ()
		for (int i = 0; i < cells.length ; i++) { 
			
			count = 0; // Restart the count when checking a new row / column (or a different direction)
			
			// 1.
			// Check every diagonal from column 0 and a row until the row it's out of bounds
			for (int row = i, col = 0; row < cells.length; row++, col++) {

				// If it is detected the currentPlayer in the specific position, add one to the counter
				if (cells [row][col].getContent() == currentPlayer) {
					count++;
					
					// If the counter arrives to 4, end the function returning true 
					if (count == 4) {
						return true;
					}
					
				// If it isn't detected the currentPlayer in the specific position, reset the counter
				} else {
					count = 0;
				}
			} // End of the "for" that checks the diagonal
			
			count = 0; // Restart the count when checking a new row / column (or a different direction
			
			// 2.
			// Check every diagonal from the last column and a row (starting in the second one) until the row or the column it's out of bounds
			for (int row = 1, col = cells[0].length - 1; row < cells.length && col > 0; row++, col--) {
				
				// If it is detected the currentPlayer in the specific position, add one to the counter
				if (cells [row][col].getContent() == currentPlayer) {
					count++;
					
					// If the counter arrives to 4, end the function returning true 
					if (count == 4) {
						return true;
					}
					
				// If it isn't detected the currentPlayer in the specific position, reset the counter
				} else {
					count = 0;
				}
			} // End of the "for" that checks the diagonal
				
		} // End of the main "for"
		
		
		
		

		// Check every column of the row 0 its diagonals, except the first column (because one side of the diagonal it's already checked and the other one is impossible in the opposite diagonal)
		for (int j = 1; j < cells[0].length; j++) { 
			
			count = 0; // Restart the count when checking a new row / column (or a different direction)
			
			// 3.
			// Check every diagonal from row 0 and a column (starting in the second one) until the column it's out of bounds
			for (int row = 0, col = j; col < cells[0].length; row++, col++) {
				
				// If it is detected the currentPlayer in the specific position, add one to the counter
				if (cells [row][col].getContent() == currentPlayer) {
					count++;
					
					// If the counter arrives to 4, end the function returning true 
					if (count == 4) {
						return true;
					}
					
				// If it isn't detected the currentPlayer in the specific position, reset the counter
				} else {
					count = 0;
				}
			} // End of the "for" that checks the diagonal
			
			count = 0; // Restart the count when checking a new row / column (or a different direction)
			
			// 4.
			// Check every diagonal from row 0 and a column (starting in the last one) until the row or the column it's out of bounds
			for (int row = 0, col = j; row < cells.length && col > 0; row++, col--) {

				// If it is detected the currentPlayer in the specific position, add one to the counter
				if (cells [row][col].getContent() == currentPlayer) {
					count++;
					
					// If the counter arrives to 4, end the function returning true 
					if (count == 4) {
						return true;
					}
					
				// If it isn't detected the currentPlayer in the specific position, reset the counter
				} else {
					count = 0;
				}
			} // End of the "for" that checks the diagonal
			
		} // End of the main "for"
	
		return false;
	}
	
	

}