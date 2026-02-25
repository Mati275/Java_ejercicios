package domain;

public abstract class Cell {
	
	// ATTRIBUTES
	private boolean investigated;
	
	// CONSTRUCTOR (it's protected type, so it's childs inherits )
	protected Cell() {
		investigated = false;
	}
	
	// *******
	// METHODS
	// *******
	
	// PROTECTED METHODS
	protected void onCityCellDiscovered() {
		
	}
	
	// PUBLIC METHODS
	
	public boolean hasBeenInsvestigated() {
		return investigated;
	}
	
	public abstract boolean isEmpty();
	
	public boolean hasCityBeenSaved() {
		return false;
	}
	
	public String getCityTypeName() {
		return "";
	}
	
	public String getCityTypeId() {
		return null;
	}
	
	public City getCity() {
		return null;
	}
	
	public boolean investigate() {
		
		// The cell was already investigated
		if( investigated ) {
			return false;
		}
		
		// There isn't a city on the cell && it's not investigated
		if( getCity() == null ) {
			investigated = true;
			return false;
		}
		
		// There is a city on the cell && it's not investigated	
		onCityCellDiscovered();
		investigated = true;
		return true;
		
	}
	
	public boolean isCityInfected() {
		return false;
	}
	
	
}

	