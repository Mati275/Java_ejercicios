package Part1;

public class Aspirant {

	
	private final String nom; 
	private final int numAspirant; // identificació del candidat 
	private int punts; // valor [0,10] 
	
	
	public Aspirant (String nom, int numAspirant, int punts) {
		
		if (punts > 10 || punts < 0) {
			throw new IllegalArgumentException ("Puntuación incorrecta");
		}
		
		this.nom = nom;
		this.numAspirant = numAspirant;
		this.punts = punts;		
		
	} 
	
	public Aspirant (String nom, int numAspirant) {
		this(nom, numAspirant, 0);
		
		//Nunca llegará a la excepción
		//No se pude poner try-catch, ya que la primera invocación deve de ser
		//si o si la primera instrucción
	}
	
	public int  getNumAspirant() {
		return numAspirant;
	}
	
	public String getNom() {
		return nom;
	}
	
	
	public int getPunts() {
		return punts;
	}
	
	public void setPunts(int punts) {
		this.punts+=punts;
	} 
	
	@Override
	public int compareTo (Object a) {
		Aspirant b;
		
		if ( !(a instanceof Aspirant) ) {	//Mira que a sea una clase Aspirante
			throw new ClassCastException ("No es un aspirant");
		}
		
		b = (Aspirant) a;
		
		if (b.getNumAspirant() < this.numAspirant) {
			return -1;
			
		} else if (b.getNumAspirant() == this.numAspirant) {
			return 0;
			
		} else {
			return 1;
		}
		
	}
	
	@Override
	public boolean equals (Object a) {
		int comparar;
		
		try {
			comparar = compareTo (a);
			
			if (comparar == 1) {
				return true;
			}
			
			return false;
			
		} catch (ClassCastException b) {
			System.out.print(b.getMessage() + "\n" + b.toString());
			return false;
		}
		
	}
	

	

}
