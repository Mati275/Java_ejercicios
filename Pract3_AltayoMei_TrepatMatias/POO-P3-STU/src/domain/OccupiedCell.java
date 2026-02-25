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
	
	@Override
	public boolean hasCityBeenSaved () {
		return this.city.hasBeenSaved();
	}
	@Override
	public City getCity() {
		return city;
	}
	
	@Override
	public String getCityTypeName() {
		return city.getCityTypeName();
	}
	
	@Override
	public String getCityTypeId() {
		return city.getCityTypeld();
	}
	
	@Override
	public boolean isCityInfected() {
		return city.isInfected();
	}
	
	@Override
	protected void onCityCellDiscovered() {
		city.registerDiscoveredCell();
	}
	
	
}
