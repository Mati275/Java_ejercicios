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
		return gameBoard.getColums();
	}
	
	public boolean hasEnded() {
		if (/*True partida acabada*/) {
		return gameBoard.allCitiesSaved(); //Partida acabada
		} else { /*Si el jugador no puede hacer más acciones o si estan infectadas*/
			return gameBoard. allCitiesInfected(); 
		}
	}
	
	public boolean hasCityBeenSaved(int row, int col) {
		return gameBoard.hasCityBeenSaved(row, col);
	}
	
	public boolean isCellEmpty(int row, int col) {
		return gameBoard.isCityEmpty(row, col);
	}
	
	public getRemainingActions() {
		return //Numero de acciones que le quedan al jugador
	}
	
	public boolean allCitiesInfected() {
		return gameBoard.allCitiesInfected();
	}
	
	public boolean investigateArea(int row, int col) {
		return true;
	}
	
}
