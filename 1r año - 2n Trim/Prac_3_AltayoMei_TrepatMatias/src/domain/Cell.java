package domain;

import exeptions.NoCityException;

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
	
	public String getCityTypeName() throws NoCityException{
		throw new NoCityException("You are trying to get a cityTypeName in a EmptyCell");
	}
	
	public String getCityTypeId() throws NoCityException{
		throw new NoCityException("You are trying to get a cityTypeID in a EmptyCell");
	}
	
	public City getCity() throws NoCityException{
		throw new NoCityException("You are trying to get a city in a EmptyCell");
	}
	
	public boolean investigate() {
		
		// The cell was already investigated
		if( investigated ) {
			return false;
		}
		
		// There isn't a city on the cell (the cell is empty) && it's not investigated
		if( isEmpty() ) {
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

	