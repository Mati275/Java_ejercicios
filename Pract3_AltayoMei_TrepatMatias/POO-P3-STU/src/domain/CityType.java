package domain;

import sun.security.x509.AVA;

public class CityType {

	// ATRIBUTES
	private String name;
	private int size;
	private int height;
	private int width; //ancho
	private String id;
	
	private static CityType availableCityTypes [];
	
	//CONSTRUCTOR
	
	private CityType (String id, String name, int width, int height) {
		this.name = name;
		this.id = id;
		this.width = width;
		this.height = height;
		
		size = width * height;
		
	}
	
	// *******
	// METHODS
	// *******
	
	public static void createCityTypes (String id[],String name[], int width[], int height[]) {
		if (id.length == name.length && 
				id.length == width.length && 
				id.length == height.length && 
				name.length == width.length &&
				name.length == height.length &&
				width.length == height.length) {
			
			availableCityTypes = new CityType [name.length];
			
			for (int i = 0; i < availableCityTypes.length; i++) {
				availableCityTypes [i] = new CityType(id [i], name [i], width [i], height [i]);
			}	
		}
	}

	
	
//TODO
//	public static void createCityTypes(){ //Sobrecarga
//		String[] id = new String [5];
//		String name[] = new String [5];
//		int width[] = new  int [5];
//		int height[] = new int [5];
//		createCityTypes();
//	}
	
	public String getId () {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
