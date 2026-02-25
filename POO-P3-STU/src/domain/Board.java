package domain;

import java.util.Random;

public class Board implements IBoard {

	//ATTRIBUTES
	private Cell cells[][];
	private City cities [];
	
	// CONSTRUCTOR
	public Board(int row, int col, CityType[] cityTypes, int[] numOfCities) {
		cells = new Cell[row][col];
		
		createEmptyBoard();
		
		addCitiesToBoard( cityTypes, numOfCities );
		
	}
	
	public Board(CityType[] cityTypes, int[] numOfCities) {
		this(10, 10, cityTypes, numOfCities);
	}

	// *******
	// METHODS
	// *******
	
	/**
	 * Investigate the city
	 * @param row
	 * @param col
	 * @return True if there is a cell on the city && this cell isn't investigated, false otherwise
	 */
	public boolean investigate(int row, int col) {
		return cells[row][col].investigate();
	}
	
	/**
	 * Check if the city on the cell passed in the parameters is saved
	 * @param row
	 * @param col
	 * @return True if this city is saved, false otherwise
	 */
	public boolean hasCityBeenSaved(int row, int col) {
		return cells[row][col].hasCityBeenSaved();
	}
	
	/**
	 * Check if all cities are saves
	 * @return True if all the cities are saved, false otherwise
	 */
	public boolean allCitiesSaved() {
		for(int i = 0; i < cells.length; i++) {
			if( !cities[i].hasBeenSaved() ) {
				return false; // At least one city isn't saved
			}
		}
		return true; // All the cities are saved
	}
	
	/**
	 * Check if all cities are infected
	 * @return True if all the cities are infected, false otherwise
	 */
	public boolean allCitiesInfected() {
		for(int i = 0; i < cells.length; i++) {
			if( !cities[i].isInfected() ) {
				return false; // At least one city isn't infected
			}
		}
		return true; // All the cities are infected
	}
	
	public int getRows() { return cells.length; }
	public int getColumns() { return cells[0].length; }
	
	/**
	 * Check if the city on the cell passed in the parameters is investigated
	 * @param row
	 * @param col
	 * @return True if this city is investigated, false otherwise
	 */
	public boolean hasCellBeenInvestigated(int row, int col) {
		return cells[row][col].hasBeenInsvestigated();
	}
	
	/**
	 * Check if the cell passed in the parameters is empty
	 * @param row
	 * @param col
	 * @return True if this cell is empty, false otherwise
	 */
	public boolean isCellEmpty(int row, int col) {
		return cells[row][col].isEmpty();
	}
	
	/**
	 * Get the name of the city type on the cell indicated
	 * @param row
	 * @param col
	 * @return
	 */
	public String getCityTypeName(int row, int col) {
		return cells[row][col].getCityTypeName();
	}
	
	/**
	 * Get the id of the city type on the cell indicated
	 * @param row
	 * @param col
	 * @return
	 */
	public String getCityTypeId(int row, int col) {
		return cells[row][col].getCityTypeId();
	}
	
	/**
	 * Get the city on the cell indicated
	 * @param row
	 * @param col
	 * @return
	 */
	public City getCity(int row, int col) {
		return cells[row][col].getCity();
	}
	
	/**
	 * Infects a random city (if possible)
	 * @return True if the city was infected, false otherwise
	 */
	
	public boolean infectRandomCity() {
		Random alea = new Random();
		
		return cities[alea.nextInt(0, cities.length)].infect();
	}
	
	
	@Override
	public String toString() {
		String board = "";
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[0].length; j++) {
				// If this cell is discovered
				// TODO: CHECK WHICH FUNCTION TO USE
				if( cells[i][j].hasBeenInsvestigated() ) {
					
					// Is an EMPTYCELL
					if( cells[i][j].isEmpty() ) {
						board += "OO ";
					}
					
					// Is an OCCUPIEDCELL
					else {
						// City infected
						if( cells[i][j].isCityInfected() ) {
							board += "CC ";
						}
						// City not infected
						else {
							board += cells[i][j].getCityTypeId();
						}
					}
					
				}
				// If this cell isn't discovered
				else {
					board += "?? ";
				}
				
			}
			board += "\n"; // Jump of line
		}
		
		return board;
	}
	
	// Private methods
	
	/**
	 * Create the array of cells[][] and put every cell void
	 */
	private void createEmptyBoard() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[0].length; j++) {
				cells[i][j] = new EmptyCell();
			}
		
		}
	}
	
	
	/**
	 * Add all the necessary cities to the board, also creates and fill the array of 
	 * @param cityTypes
	 * @param numOfCities
	 */
	private void addCitiesToBoard( CityType[] cityTypes, int[] numOfCities ) {
		Random alea = new Random();
		
		int count = 0;			// A counter to count how many times was one cityType created
		int idxCount = 0; 		// A counter that contains the current idx of both arrays in @param to check
		
		int randomRow, randomCol;
		int totalCities = 0; 	// A number that indicates the number of all the cities that are created
		
		// Both arrays in the parameters have the same length
		if( cityTypes.length == numOfCities.length ) {
			
			
			for(int i = 0; i < numOfCities.length; i++) {
				totalCities += numOfCities[0];
			}
			
			cities = new City[totalCities]; // Initialize the array of cities with the total number of cities defined
			
			// Loop that creates each city
			for(int k = 0; k < totalCities; k++) {
				
				randomRow = alea.nextInt(0, cells.length);
				randomCol = alea.nextInt(0, cells[0].length);
				

				// While this random position isn't valid
				while( !validPosition(randomRow, randomCol, cityTypes[idxCount]) ) {
					randomRow = alea.nextInt(0, cells.length);
					randomCol = alea.nextInt(0, cells[0].length);
				} // Here we have a valid position
					
				cities[k] = new City(cityTypes[idxCount]); // Creates and saves a new city

				for(int i = randomRow; i < randomRow + cityTypes[idxCount].getHeight(); i ++) {
					for(int j = randomCol; j < randomCol + cityTypes[idxCount].getWidth(); j++ ) {
						cells[i][j] = new OccupiedCell(cities[k]); // Creates occupied cells according to the city that was created in this loop
					}
				} // End of the loop to create cities
				
				count ++;
				
				// If the counter of how many number of each city types reach to the limit, start creating other type of city
				if( count >= numOfCities[idxCount] ) {
					count = 0;
					idxCount ++;
				}
				
			}
				
				
				
				
		}
			
	}
		
	
	
	
	// OTHER METHODS
	
	private boolean validPosition( int row, int col, CityType cityType ) {
		
		// IF
		// Row not valid --> >= of the length of the rows
		// Column not valid --> >= of the length of the columns
		// Return false
		
		if ( (row + cityType.getHeight() ) >= cells.length || ( col + cityType.getWidth() ) >= cells[0].length ) {
			return false;
		}
		
		// Row && column valid (aren't out of bounds with this city type)
		// Both iterations: Start from the random point to make a walkthrought to all the spots that this city will occupy
		// To check if any of them is already occupied
		
		for(int i = row; i < row + cityType.getHeight(); i ++) {
			for(int j = col; j < col + cityType.getWidth(); j++ ) {
				// One cell isn't empty
				if( !cells[i][j].isEmpty() ) {
					return false;
				}
			}
		}
	
		return true; // Row and column are valid
		
	}
	
}

