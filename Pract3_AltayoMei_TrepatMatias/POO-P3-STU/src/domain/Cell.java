package domain;

public abstract class Cell {

	private boolean investigated;
	
	protected Cell() {
		investigated =  false;
	}
	
	//COSNTRUCTOR
	
	protected void onCityCellDiscovered() {	}
	
	// *******
	// METHODS
	// *******
	
	public boolean hasBeenInvestigated () {
		return investigated;
	}
	
	public abstract boolean isEmpty();
	
	public boolean hasCityBeenSaved() {
		return false;
	}
	
	public String getCityTypeName () {
		return "";
	}
		
	
	public String getCityTypeId() {
		return null;
	}
	
	public City getCity() {
		return null;
	}
	
	public boolean investigate () {
		if (investigated) {
			return false;
		} else {
			investigated = true;
			
			if( isEmpty() ) {
				return false;
			} 
			
			onCityCellDiscovered();
			return true;
		}
	}
	
	public boolean isCityInfected() {
		return false;
	}
	
	
}
