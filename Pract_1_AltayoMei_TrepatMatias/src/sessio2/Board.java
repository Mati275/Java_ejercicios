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
	
	
}