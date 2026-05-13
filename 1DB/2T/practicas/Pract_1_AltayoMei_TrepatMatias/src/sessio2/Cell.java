package sessio2;

public class Cell {
	
	// ATTRIBUTES
	private char marker;
	
	// CONSTRUCTOR
	public Cell () {
		marker = ' ';
	}
	
	// **********
	// METHODS
	// **********
	
	// Getters
	public char getContent () { // returns attribute
		return marker;
	}
	
	// Check if the Cell is empty
	public boolean isEmpty () {
		if (marker == ' ') {
			return true;
		}
		
		return false;
	}
	
	// Fill the Cell
	public void addMarker (char player) {
		marker = player;
	}
	
	
}
