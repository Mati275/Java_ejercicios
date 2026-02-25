package domain;


public class Game {
	

	
	//ATTRIBUTES
	private Board gameBoard;
	
	private City[] savedCities;
	private int numSavedCities;
	
	private int remainingActions;
	
	//CONSTRUCTOR
	public Game(  ) {
		CityType[] cityTypes;
		int [] numOfCities;
		
		CityType.createCityTypes();
		
		cityTypes = CityType.getAvailableCityTypes();
		numOfCities = new int[cityTypes.length]; // Creates the array of number of cities
		
		// Initialize the array of "int"
		for(int i = 0; i < numOfCities.length; i ++) {
			numOfCities[i] = 1;
		}
		
		
		savedCities = new City[ numOfCities.length ]; 	// Creates the array with the length of the number of cities in the board
														// In consequence of the array of numOfCities having number 1 for each position, this array has the same length
		
		// CREATE THE BOARD --> TODO: ASK, BECAUSE THIS INSTRUCTION ISN'T IMPLEMENTED ON THE DOC.
		gameBoard = new Board( cityTypes, numOfCities );
		
		numSavedCities = 0;
		remainingActions = 45;
		
		
	}
	

	//*******
	//METHODS
	//*******

	// PUBLIC METHODS
	
	public int getNumRows() {
		return gameBoard.getRows();
	}
	
	public int getNumCols() {
		return gameBoard.getColumns();
	}
	

	
	
	
	public boolean hasEnded() {
		return gameBoard.allCitiesSaved() || remainingActions == 0 || gameBoard.allCitiesInfected();
	}
	

	public boolean hasCityBeenSaved(int row, int col) {
		return gameBoard.hasCityBeenSaved(row, col);
	}
	
	public String getCityTypeName(int row, int col) {
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
	
	public boolean investigateArea( int row, int col ) {
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
	
	
	// PRIVATE METHODS
	private void sortSavedCitiesBySize() {
		City lastCity;
		lastCity = savedCities[numSavedCities - 1];
		
		for(int i = 0; i < numSavedCities - 1; i ++) {
			// If the size of the last city is smaller to the size than one city already in the parameters
			if( lastCity.compareTo(savedCities[i]) < 0 ) {
			
				for(int j = numSavedCities - 1; j > i; j--) {
					savedCities[j] = savedCities[j - 1];
				}
				
				// Add the last added city to the position that is duplicated after this loop
				savedCities[i] = lastCity;
			
			}
		}
		
	}
	
	
	private void infectCity() {
		gameBoard.infectRandomCity();
	}
	
}

// ATTRIBUTES

// CONSTRUCTOR


//*******
//METHODS
//*******

//GETTERS