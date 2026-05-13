package Part2;

final public class Casa extends Habitatge {
	private final int numPlantes; // Valor mínim 1 
	private boolean jardi;
	private double[][] estances;//metres de cada estança, distribuïdes per pis  
	private int[] comptadors;
	
	
	public Casa(int identificador, double metres, int proteccio, int plantes,  boolean jardi, int quantes) throws ExcepcioHabitatge, IllegalArgumentException { 
		super(identificador, Habitatge.CASA, proteccio, metres); 
		
		if (plantes <= 0 || quantes <= 0) {
			throw new IllegalArgumentException("No s'ha pogut crear"); 
		}
		
		this.numPlantes = plantes;
		this.jardi = jardi;
		estances = new double[plantes][quantes];
		comptadors = new int[plantes]; //es suposen inicialitzats a zero 
		
	}

	public int getNumPlantes() {return numPlantes;} 
	
	public double[] estancesMesGran() { 
		double [] metresQuadrats = new double [numPlantes];
		
		for (int i = 0; i < numPlantes; i++) {
			metresQuadrats[i] = estances [i][0];
			
			for (int j = 1; j < comptadors [i]; j++) {
				if (estances [i][j] > metresQuadrats [i]) {
					metresQuadrats [i] = estances [i][j];
				}
			}
		}
		
		return metresQuadrats;
	}

	
	public int estancaMesGran() {
		double[] grans = estancesMesGran();
		int quina = 0;
		
		for (int i = 1; i < numPlantes; i++) {
			if (grans[i] > grans[quina]) {
				quina = i;
			}
		}
		
		return quina; 
	
	}

	public void afegirEstança(double metres, int planta) throws  ExcepcioHabitatge, IllegalArgumentException { 
		
		if (metres <= 0) {
			throw new IllegalArgumentException("Parametros incorrectos");
		}
		
		if (planta < 0 || numPlantes <= planta){
			throw new ExcepcioHabitatge("No existe la planta");
		}
		
		if (comptadors[planta] == estances[planta].length) {
			ampliar();
		}
		
		estances[planta][comptadors[planta]] = metres;
		comptadors[planta]++; 
		
	}


	public int eliminarEstanca(double metres, int planta) throws  ExcepcioHabitatge, IllegalArgumentException { 
		int quantes = 0;
		
		if (metres <= 0) {
			throw new IllegalArgumentException("Parametros incorrectos");
		}
		
		if (planta < 0 || numPlantes <= planta){
			throw new ExcepcioHabitatge("No existe la planta");
		}
		
		for (int i = 0; i < comptadors[planta]; i++) { 
			if (estances [planta] [i] == metres) {
				estances[planta][i] = estances[planta][comptadors[planta] - 1];
				quantes ++;
				i ++; //Important ¿Why?
				comptadors[planta]--;
			}
		}
		
		return quantes;
		
	}

	
	public boolean equals(Habitatge h) { 
		Habitatge a;
		
		if (! (h instanceof Habitatge) ){
			return false;
		}
		
		a = (Habitatge) h;
		
		if () {
		
		}
		
	}
	
	
	
	
	
	
	
	
	//Otros metodos
	
	private void ampliar() { 
		double copia[][] = new double[numPlantes][estances[0].length + 10]; 
		
		for (int i = 0; i < numPlantes; i++) {
			for (int j = 0; j < comptadors[i]; j++) {
				copia[i][j] = estances[i][j]; 
			}
		}
		
		estances = copia;

	}

}
