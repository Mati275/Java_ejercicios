package domain;

public class City implements Comparable{

	private CityType cityType; //representa el tipo de ciudad
	private int id; //id de asignar ciudad
	private int discoveredCells; //numero de celdas que descubrió el jugador
	private CityState state; //estado de la ciudad
	
	private static int usedIds; //proximo id disponible pera crear una nueva ciudad
	
	//CONSTRUCTOR
	
	public City (CityType cityType) {
		this.cityType= cityType; 
		
		discoveredCells = 0;
		id = usedIds;
		usedIds++;
		
		state = CityState.DORMANT;
			
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
	
	public String getCityTypeld () {
		return cityType.getId();
	}
	
	public String getCityTypeName() {
		return cityType.getName();
	}
	
	public int getSize() {
		return cityType.getSize(); //return  mida
	}
	
	public boolean isInfected() {
		if(state == CityState.INFECTED) {
			return true;
		}
		
		return false;
	}
	
	public boolean hasBeenSaved() {
		if (CityState.CLEARED == state) {
			return true;
		}
		
		return false;
	} 
	
	public boolean infect() {
		if (CityState.DORMANT == state) {
			state = CityState.INFECTED;
			return true; //Infectada
		}
		return false;
	}
	
	public void registerDiscoveredCell() {
		discoveredCells++;
		if(discoveredCells == cityType.getSize()) {
			state = CityState.CLEARED;
		}
		
	}
	
	@Override	//redefinir
	public String toString() {
		return "Id: " + id + " | City type: " + getCityTypeName() + " | Size: " + getSize();
	}
	
	@Override	//redefinir
	public boolean equals( Object a ) {
		City city;
		
		if ( !(a instanceof City) ) {
			return false;
		}
		
		city = (City) a;	//a es City
		
		if(city.getCityType().equals(this.getCityType())) {
			return true;
			
		}
		return false;
	}



	@Override
	public int compareTo(Object o) {
		City city;
		
		if (o instanceof City) {
			
			city = (City) o;	//city es igual que "o" (suponiendo que o es una City)
			
			if (this.getSize() == city.getSize()) {
				return 0;
			} else if (this.getSize() > city.getSize()) {
				return 1;
			} else {
				return -1;
			}
			
		} else {
			return 0;
		}
		
	}
	
	
	
}
