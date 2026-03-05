package domain;

import exeptions.NoCityException;

public class Game {
	

	
	//ATTRIBUTES
	private Board gameBoard;
	
	private City[] savedCities;
	private int numSavedCities;
	
	private int remainingActions;
	
	//CONSTRUCTOR
	public Game() throws NoCityException {
		CityType[] cityTypes;
		int [] numOfCities;
		
		CityType.createCityTypes();
		
		cityTypes = CityType.getAvailableCityTypes();
		numOfCities = new int[cityTypes.length]; // Creates the array of number of cities
		
		// Initialize the array of "int"
		for(int i = 0; i < numOfCities.length; i ++) {
			numOfCities[i] = 1;
		}
		
		
		savedCities = new City[ cityTypes.length ]; 	// Creates the array with the length of the number of cities in the board
														// In consequence of the array of numOfCities having number 1 for each position, this array has the same length
		
		// CREATE THE BOARD --> TODO: ASK, BECAUSE THIS INSTRUCTION ISN'T IMPLEMENTED ON THE DOC.
		gameBoard = new Board( cityTypes, numOfCities );

		numSavedCities = 0;
		remainingActions = 45;
		
		
	}
	

	// *******
	// METHODS
	// *******

	// PUBLIC METHODS
	
	public int getNumRows() {
		return gameBoard.getRows();
	}
	
	public int getNumCols() {
		return gameBoard.getColumns();
	}
	

	
	
	public boolean hasEnded() {
		return gameBoard.allCitiesSaved() || remainingActions <= 0 || gameBoard.allCitiesInfected();
	}
	

	public boolean hasCityBeenSaved(int row, int col) {
		return gameBoard.hasCityBeenSaved(row, col);
	}
	
	public String getCityTypeName(int row, int col) throws NoCityException {
		return gameBoard.getCityTypeName(row, col);
	}
	
	public boolean isCellEmpty(int row, int col) {
		return gameBoard.isCellEmpty(row, col);
	}
	
	public int getRemainingActions() {
		return remainingActions;
	}
	
	public boolean allCitiesInfected() {
		return gameBoard.allCitiesInfected();
	}
	
	public boolean investigateArea( int row, int col ) throws NoCityException {
		boolean succesfullInvestigated;
		
		succesfullInvestigated = gameBoard.investigate(row, col);
		
		// The cell was uninvestigated && is filled
		if( succesfullInvestigated ) {
			
			// Is the city saved
			if( gameBoard.hasCityBeenSaved(row, col) ) {
				savedCities[numSavedCities] = gameBoard.getCity(row, col); // Add the city to the saved cities
				numSavedCities ++;
				sortSavedCitiesBySize();
			}
		}
		
		// The turn is multiple of 5
		if( remainingActions % 5 == 0 ) {
			infectCity();
		}
		
		remainingActions --;
		
		
		return succesfullInvestigated;
		
	}
	
	public String savedCitiesInfo() {
		String msg = "";
		
		for(int i = 0; i < numSavedCities; i++) {
			msg +=  savedCities[i].toString() + "\n";
		}
		
		return msg;
		
	}
	
	public String boardToString() {
		return gameBoard.toString();
	}
	


	private void sortSavedCitiesBySize() {
		
		City lastCityAdded = savedCities[numSavedCities - 1]; // The last index of the array is the last city
		
		// Last index filled --> first index 
		// This iteration moves the last city added to the correct position of the array, using a simplification of the bubble method
		for(int i = numSavedCities - 2; i >= 0; i--) {
			
			// If the size of the last city is smaller than the size of the position that we're checking
			// swap the positions
			if( lastCityAdded.compareTo(savedCities[i]) < 0 ) {
				savedCities[i + 1] = savedCities[i];
				savedCities[i] = lastCityAdded;
			}
		}
	}
	
	
	private void infectCity() {
		gameBoard.infectRandomCity();
	}
	
}

