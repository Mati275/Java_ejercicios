package domain;

import java.util.Random;

public class Board implements IBoard {
	
	private Cell [][] cells;
	private City [] cities;
	
	//CONSTRUCTOR
	
	public Board (int row, int col, CityType [] cityType, int [] numOfCities){
		cells = new Cell [row][col]; 	//Define la mida de la matriz de las celdas
		
		createEmptyBoard();
		
		addCitiesToBoard(cityType, numOfCities);
		
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
	
	public boolean allCitiesSaved () {
		for(int i = 0; i < cities.length; i++) { //recorre las ciudades
				if (!cities [i].hasBeenSaved()) { // Si encuentra una que no este salvada
					return false;
				}
			}
		return true;	//Todas salvadas
	}
	
	//TODO
	public boolean allCitiesInfected () {
		for(int i = 0; i < cities.length; i++) { //recorre las ciudades
				if (!cities [i].isInfected()) { //Alguna no infectada
					return false;
				}
			}
		return true;	//Infectadas
	}
	
	@Override
	public int getRows() { 		//Return del numero de filas
		return cells.length;
		
	}
	
	@Override
	public int getColumns() { 		//Return del numero de filas
		return cells[0].length;
		
	}
	
	//TODO
	public boolean hasCellBeenInvestigated(int row, int col) {
		return (cells[row][col].hasBeenInvestigated()); //return true, if celda investigada
	
	}
	
	//TODO
	@Override
	public boolean isCellEmpty(int row, int col) {
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
		Random alea = new Random ();
		return cities[alea.nextInt(0, cities.length)].infect();
	}
	
	//TODO
	@Override
	public String toString() {
		return "";
	}
	
	// ***************
	// METHODS PRIVATE
	// ***************
	
	//Crear createEmptyBoard: Omple la matriu cells amb cel·les buides (EmptyCell)
	
	private void addCitiesToBoard(CityType [] cities, int [] quantityCities) {
		
			
		
	}
	
	private void createEmptyBoard() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new EmptyCell();	//Inicializa las celdas
			}
		}
	}
	
	
		
}
