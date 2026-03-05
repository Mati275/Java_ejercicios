package domain;

import exeptions.NoCityException;

public class OccupiedCell extends Cell{
	//ATTRIBUTES
	private City city;
		
	// CONSTRUCTOR
	public OccupiedCell( City city ) throws NoCityException{
		super();
		if( city == null ) {
			throw new NoCityException("You are trying to assign a inexistent city to a cell that must contain a city");
		}
		
		this.city = city;
	}
	
	// *******
	// METHODS
	// *******
	
	// PUBLIC METHODS
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override	
	public boolean hasCityBeenSaved() {
		return city.hasBeenSaved();
		
	}
	
	// Attributes of the city
	
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
		return city.getCityTypeId();
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