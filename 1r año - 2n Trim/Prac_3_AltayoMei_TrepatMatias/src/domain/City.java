package domain;

public class City implements Comparable {

	// ATTRIBUTES
	private CityType cityType; 
	private int id; 
	private int discoveredCells; 
	private CityState state; 
	
	private static int usedIds = 0; 
	
	//CONSTRUCTOR
	public City (CityType cityType) {
		this.cityType = cityType;
		
		discoveredCells = 0;
		id = usedIds;
		usedIds++;
		
		state = CityState.DORMANT;
			
		}
		
	// *******
	// METHODS
	// *******
		
	// GETTERS
	public int getId() {
		return id;
	}
	
	public String getCityTypeId() {
		return cityType.getId();
	}
	
	public String getCityTypeName() {
		return cityType.getName();
	}
	
	public int getSize() {
		return cityType.getSize(); 
	}
	
	// OTHER METHODS
	
	public boolean isInfected() {
		if(state == CityState.INFECTED) {
			return true;
		}
		return false;
	}
	
	public boolean hasBeenSaved() {
		if (state == CityState.CLEARED) {
			return true;
		}
		return false;
	}
	
	
	// If it has the conditions requested, infects the city
	public boolean infect() {
		if (state == CityState.DORMANT && discoveredCells == 0) {
			
			state = CityState.INFECTED;
			return true; 
			
		}
		return false;
		
	}
	
	// Called when the player discovers a city
	public void registerDiscoveredCell() {
		discoveredCells ++;
		if( discoveredCells == getSize() ) {
			state = CityState.CLEARED;
		}
		
	}
	
	
	@Override
	public String toString() {
		return "Id: " + id + " | City type: " + getCityTypeName() + " | Size: " + getSize();
	}
	

	@Override
	public boolean equals( Object obj ) {
		City otherCity;
		
		if( !( obj instanceof City ) ) {
			return false;
		}
		
		otherCity = (City) obj;
		
		if( getCityTypeId().equals( otherCity.getCityTypeId() ) ) {
			return true;
		}
		
		return false;
		
	}
	
	
	// IMPLEMENT THE METHODS OF THE INTERFACE "COMPARABLE"
	
	public int compareTo(Object obj) {
		City city;
		
		// The object is not a city
		if( !(obj instanceof City) ) {
			
			throw new ClassCastException();
			
		} // The object is a city

		city = (City) obj;
		
		// Compare the size
		
		// Both are equal
		if( getSize() == city.getSize() ) {
			return 0;
		} 
		// The city in the parameter is smaller
		else if( city.getSize() < getSize() ) {
			return 1; 
		}
		// The city in the parameter is bigger
		else {
			return -1; 
		}
			
	}
	
	
	
}