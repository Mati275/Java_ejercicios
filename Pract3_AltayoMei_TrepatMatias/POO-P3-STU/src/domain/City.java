package domain;

public class City {

	private CityType cityType; //representa el tipo de ciudad
	private int id; //id de asignar ciudad
	private int discoveredCells; //numero de celdas que descubrió el jugador
	private CityType state; //estado de la ciudad
	
	private static int usedIds; //proximo id disponible pera crear una nueva ciudad
	
	//CONSTRUCTOR
	
	public City (CityType cityType, int discoberyCells, int id) {
		discoberyCells = 0;
		id = usedIds;
		usedIds++;
		//CitySate.DORMANT = 0;
			
		}
		
	// *******
	// METHODS
	// *******
	
	public int getId() {
		return id; //return  atribur id de ciudad
	}
	
	public CityType getCityType() {
		return cityType; 
	}
	
	public CityType getCityTypeName() {
		return cityType;
	}
	
	public CityType getSize() {
		return size; //return  mida
	}
	
	public CityState getIsInfected() {
		if(CityState.INFECTED) {
		return true;
		}
	}
	
	public CityState hasBeenSaved() {
		if (CityState.CLEARED) {
			return true;
		}
	}
	
	public boolean infect() {
		if (CityState.DORMANT && discoveredCells) {
		return true; //Infectada
		}
		
	}
	
	public 	boolean registerDiscoveredCell() {
		
		
		
		return CityState.CLEARED;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
