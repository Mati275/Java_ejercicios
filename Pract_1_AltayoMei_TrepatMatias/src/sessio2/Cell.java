package sessio2;

public class Cell {
	private char marker;
	
	// Constructor
	public Cell () {
		marker = ' ';
	}
	
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
