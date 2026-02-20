package domain;

public class OccupiedCell extends Cell {
	
	private City city;
	
	
	//CONSTRUCTOR
	
	public OccupiedCell (City city) {
		super();
		this.city = city;
		
	}
	
	@Override
	public boolean isEmpty() {
		return false; 	//nunca estará vacia
	}
	
	public boolean hasCityBeenSaved () {
		return this.city.hasBeenSaved();
	}
	
	public City getCity() {
		return city;
	}
	
	public String getCityTypeName() {
		return city.getCityTypeName();
	}
	
	public String getCityTypeId() {
		return city.getCityTypeld();
	}
	
	public boolean isCityInfected() {
		return city.isInfected();
	}
	
	@Override
	protected void onCityCellDiscovered() {
		city.registerDiscoveredCell();
	}
	
	
}
