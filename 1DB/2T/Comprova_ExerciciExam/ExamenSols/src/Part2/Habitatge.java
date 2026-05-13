package Part2;

public class Habitatge {

	//ATRIBUTOS STATIC
	public static final int CASA = 1;
	public static final int PIS = 2;
	public static final int DUPLEX = 3;
	
	//Proteccion
	public static final int VPPB = 1;
	public static final int VPPL = 2;
	public static final int VPPA = 3;
	public static final int VPPAOC = 4;
	public static final int VIS = 5; 
	
	
	private final int identificador;
	private int tipusHabitatge;
	private int tipusProteccion;
	private double metres; //valor positivo
	private Propietari [] propietaris;
	private int quants; //posiciones de vecotres llenas
	
	
	// CONSTRUCTOR
	public Habitatge (int identificador, int tipusHabitatge, int tipusProteccion, double metres, int quants) throws ExcepcioHabitatge {
		if (identificador <= 0 || metres <= 0) {
			throw new ExcepcioHabitatge("Parametro Negativo");
		}
		
		if (tipusHabitatge != Habitatge.PIS && tipusHabitatge != Habitatge.DUPLEX && tipusHabitatge != Habitatge.CASA) {
			throw new ExcepcioHabitatge("Habitatge incorrecto");
		}
		
		if (tipusProteccion != -1 && tipusProteccion < Habitatge.VPPB && tipusProteccion > Habitatge.VIS) {
			throw new ExcepcioHabitatge("Habitatge protegit de tipus incorrecte");	
		}
		
		this.identificador = identificador;
		this.tipusHabitatge = tipusHabitatge;
		this.tipusProteccion = tipusProteccion;
		this.metres = metres;
		this.quants = quants = 0;
		
	}
	
	public Habitatge ( int identificador, int tipusH, int tipusP, double  metres, String dni) throws ExcepcioHabitatge {
		this (identificador, tipusH, tipusP, metres, getQuants());
		
		try {
			propietaris [0] = new Propietari (dni, 100);
		} catch (ExcepcioHabitatge a) {
			
		}
		
		quants ++;
	}
	
	// GETTERS
	public int getIdent() {return identificador;}
	
	public int getTipusHabitatge() {return tipusHabitatge;}
	
	public int getTipusProteccion() {return tipusProteccion;}
	
	public double getMetres() {return metres;}
	
	public int getQuants() {return quants;}
	
	
	//OTHERS
	public void afegirPropietari(Propietari p) throws ExcepcioHabitatge,  IllegalArgumentException{ 
		if (p == null) { throw new IllegalArgumentException("Propietari inexistent"); }
		
		if (quants == propietaris.length) { throw new ExcepcioHabitatge("NO hi ha espai"); }
		
		int perc = p.getPercentatge();
		
		for (int i = 0; i < quants && perc < 100; i++) {
			perc += propietaris[i].getPercentatge();
		
			if (i == quants && perc <= 100) {
				propietaris[quants]= p; quants++;
			} else { throw new ExcepcioHabitatge("Supera el 100"); }
		}

	}
	
	public void eliminarPropietari(String dni) throws ExcepcioHabitatge,  IllegalArgumentException{
		if (dni == null) {
			throw new IllegalArgumentException("Propietari inexistent");
		}
		
		for (int i = 0; i<quants; i++) { //Cerca prematura
			
			if (propietaris[i].getDni().equals(dni)){
				propietaris[i]=propietaris[quants-1]; quants--;
				return;
			}
		
		}
		throw new ExcepcioHabitatge("Propietari inexistent");
	
	} 
	
	
	public int compareTo(Object c) throws ExcepcioHabitatge { 
		
		Habitatge a;
		
		if ( !(c instanceof Habitatge) ){
			throw new ExcepcioHabitatge(c.toString() + "\n");
		}
		
		a = (Habitatge) c;
		
		if (a.getTipusHabitatge() == (this.tipusHabitatge)) {
			return 0;
		} else if (a.getTipusHabitatge() < (this.tipusHabitatge)) {
			return 1;
		} else { return -1;}
	}
	
	
	public boolean equals (Object a) {
		int compare;
		
		try {
			compare = compareTo (a);
			
			if (compare == 0) {
				return true;
			}
			
			return false;
			
		} catch (ExcepcioHabitatge err) {
			System.out.print(err.getMessage() + "\n" + err.toString());
			return false;
		}
	}
	
	
	public String toString() {
		String a = "";
		
		if (quants != 0) {
			for (int i = 0; i < quants; i++) {
				a += propietaris[i].getDni();
			}
		} else {
			a = "Sin propieatios";
		}
		
		return a;
	}
	
	
	// Porque es -1
	public int setCategoria () {
		if (Habitatge.CASA == tipusHabitatge) { //Casa categría 5 o 6
			if (this.tipusProteccion == -1) {
				return 6;
			} else { return 5; }
			
		} else if (Habitatge.DUPLEX == tipusHabitatge) {
			if (this.tipusProteccion == -1) {
				return 4;
			} else { return 3; }
			
		} else if (tipusProteccion == -1 || tipusProteccion == Habitatge.VIS || tipusProteccion == Habitatge.VPPL) { return 2;}
		
		return 1;

	}
	
	
}
