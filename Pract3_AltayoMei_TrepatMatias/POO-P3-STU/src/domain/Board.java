package domain;

public class Board {
	
	private Cell [][] cells;
	private City [] cities;
	
	//CONSTRUCTOR
	
	public Board (int row, int col, CityType [] cityType, int [] numOfCities){
		cells = new Cell [row][col]; 	//Define la mida de la matriz de las celdas
		
		//TODO
		createEmptyBoard();
		
		addCitiesToBoard();
		
	}
	
	public Board(CityType [] cityType, int [] numOfCities) {
		this(10,10, cityType, numOfCities);
	}
	
	
	// ***************
	// METHODS PUBLICS
	// ***************
	
	public boolean investigate(int row, int col) {
		return cells[row][col].investigate();
	}
	
	public boolean hasCityBeenSaved (int row, int col) {
		return cells[row][col].hasBeenInvestigated();
	}
	
	//TODO: Te marco las que estpy haciendo y pienso que estan mal
	public boolean allCitiesSaved () {
		for(int i = 0; i < cities.length; i++) { //recorre las ciudades
				if (cities [i].hasBeenSaved()) {
					return true;
				}
			}
		return false;
	}
	
	//TODO
	public boolean allCitiesInfected () {
		for(int i = 0; i < cities.length; i++) { //recorre las ciudades
				if (cities [i].infect()) {
					return true;
				}
			}
		return false;
	}
	
	//TODO: esta mal
	@Override
	public int getRows() { 		//Return del numero de filas
		int totalRow;
		for (int i = 0; i < cells.length; i++) {
			totalRow += cells[0][i];
		}
		
	}
	
	//TODO: esta mal
	@Override
	public int getColums() { 		//Return del numero de filas
		int totalCol;
		for (int i = 0; i < cells.length; i++) {
			totalCol += cells[i][0];
		}
		
	}
	
	//TODO
	public boolean hasCellBeenInvestigated(int row, int col) {
		if(cells[row][col].investigate()){ //return true, if celda investigada
			return true;
		} 
		return false;
		
	}
	
	//TODO
	@Override
	public boolean isCityEmpty(int row, int col) {
		return cells[row][col].isEmpty();
	}
	
	//TODO
	public String getCityTypeName(int row, int col) {
		return cells[row][col].getCityTypeName();
	}

	//TODO
	public String getCityTypeId(int row, int col) {
		return cells[row][col].getCityTypeId();
	}
	
	//TODO
	public City getCity(int row, int col) {
		return cells[row][col].getCity();
	}
	
	public boolean infectRandomCity() {	//Infecta una ciudad al azar
		return true;
	}
	
	//TODO
	@Override
	public void toSting() {
		
	}
	
	// ***************
	// METHODS PRIVATE
	// ***************
	
	//Crear createEmptyBoard: Omple la matriu cells amb cel·les buides (EmptyCell)
	
	private void addCitiesToBoard(CityType [] cities, int [] quantityCities) {
		if (cities.equals(quantityCities)) {
			
		}
	}
	
		
}
