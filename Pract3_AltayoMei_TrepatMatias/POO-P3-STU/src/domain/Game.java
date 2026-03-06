package domain;

public class Game {
	
	private Board gameBoard;
	private City [] savedCities;
	private int numSavedCities;
	private int remainingActions;

	public Game() {
		
	}
	
	public int getNumRow() {
		return gameBoard.getRows();
	}
	public int getNumRCols() {
		return gameBoard.getColumns();
	}
	
	public boolean hasEnded() {
		return gameBoard.allCitiesSaved() || gameBoard. allCitiesInfected() || (remainingActions == 0);
	
	}
	
	public boolean hasCityBeenSaved(int row, int col) {
		return gameBoard.hasCityBeenSaved(row, col);
	}
	
	public boolean isCellEmpty(int row, int col) {
		return gameBoard.isCellEmpty(row, col);
	}
	
	public int getRemainingActions() {
		return remainingActions;	//Numero de acciones que le quedan al jugador
	}
	
	public boolean allCitiesInfected() {
		return gameBoard.allCitiesInfected();
	}
	
	public boolean investigateArea(int row, int col) {
		return true;
	}
	
	public String savedCitiesInfo() {
		String mgs = "";
		for (int i = 0; i < numSavedCities; i++) {
			mgs +=  "Id: " + savedCities [i].getId() + " | City type: " + savedCities[i].getCityType()
					+ " | Size: " + savedCities[i].getSize();	
		}
		
		return mgs;
	}
	
	public String boardToString() {
		return gameBoard.toString();
	}
	
	
	private void sortSavedCitiesBySize() {
		City ultima = savedCities[numSavedCities-1];
		
		for(int i = numSavedCities-2; i >= 0;  i--) { // Intercanvi 
			if (ultima.compareTo(savedCities[i]) < 0) {
				savedCities[i+1] = savedCities [i];
				savedCities [i] = ultima;
			}
		}
	}
	
	private void infectCity() {
		gameBoard.infectRandomCity();
	}
	
}
