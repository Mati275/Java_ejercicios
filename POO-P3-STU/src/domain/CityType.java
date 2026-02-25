package domain;

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
	
	// STATIC METHODS
	/**
	 * Creates and initialize the vector of "availableCityTypes"
	 * @param id[]
	 * @param name[]
	 * @param width[]
	 * @param height[]
	 */
	public static void createCityTypes (String id[],String name[], int width[], int height[]) {
		if (id.length == name.length && 
				id.length == width.length && 
				id.length == height.length && 
				name.length == width.length &&
				name.length == height.length &&
				width.length == height.length) {
			
			availableCityTypes = new CityType [name.length]; 
			
			// Asigning each value of the parameters with the correct position to create the objects
			for (int i = 0; i < availableCityTypes.length; i++) {
				availableCityTypes [i] = new CityType(id [i], name [i], width [i], height [i]);
			}	
		}
	}
	
	// (Overcharge the methods, creating two with the same name with different parameters)
	
	/**
	 * Call "createCityTypes (String id[],String name[], int width[], int height[])" with parameters by default 
	 */
	public static void createCityTypes(){
		// Creating by default all the parameters
		String[] id = { "METROPOLIS", "URBAN CENTER", "TOWN", "VILLAGE", "HAMLET" };
		String name[] = { "ME", "UC", "TO", "VI", "HA" } ;
		int width[] = { 5, 7, 3, 2, 1 };
		int height[] = { 2, 1, 2, 2, 1 };
		
		createCityTypes(id, name, width, height);
	}
	
	public static CityType[] getAvailableCityTypes() {
		return availableCityTypes;
	}
	
	// GETTERS
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