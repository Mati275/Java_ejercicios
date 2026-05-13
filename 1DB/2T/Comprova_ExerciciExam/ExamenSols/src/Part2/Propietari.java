package Part2;

public class Propietari {

	private String dni; //Identificador
	private int percentatge; //percentatge de propietat d’un habitatge
	
	public Propietari(String dni, int percentatge) throws Exception{
		if (percentatge > 0 && percentatge <= 100 ) {
			this.dni = dni; this.percentatge = percentatge;
		} else {
			throw new Exception("Error de construcció");
		} 
	}
	
	
	public String getDni(){
		return dni;
	}
	
	public int getPercentatge(){
		return percentatge; 
	}
}
