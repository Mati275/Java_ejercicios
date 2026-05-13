package Part1;

public class Concursant extends Aspirant implements PuntuarGala {

	
	public static final int MAX_GALES = 5; //Numero de galas del concurso
	private Magatzem cancons; //Guarda Strings
	private Puntuacio [] puntuacions; //Guarda las puntuaciones de la gala
	private int numGales; //Numero de las posiciones llenas del vector. Nº galas que han participado un concursante
	
			
	public Concursant(Aspirant a) throws ExcepcioAcademia {
		super(a.getNom(), a.getNumAspirant(), a.getPunts());
		
		try {
			cancons = new Contenidor(MAX_GALES*2);
		} catch (Exception e) {
			throw new ExcepcioAcademia ("No se pudo crear"); 
		}
	}
		
		
	public int puntuacioFinal(int quina) throws ExcepcioGala {
		if (quina <= 0 || quina > numGales) {
			throw new ExcepcioGala("Gala inexistent");
			return puntuacions[quina - 1].getPuntsCoreo() + puntuacions[quina - 1].getsPuntVeu()+ puntuacions[quina - 1].getsPuntInterpretacio()/3; 
		}
	}
	
	
	public void setPuntuar(int[] m, int ambit, int quina) throws ExcepcioGala {
		int valor = 0;
		
		if (quina <= 0 || quina > numGales) {
			throw new ExcepcioGala("Gala inexistent");
			for (int i = 0; i < m.length; i++) {
				valor += m[i];
				
				switch (ambit) {
					case PuntuarGala.COREO:
						puntuacions[quina - 1].setPuntsCoreo(valor/m.length);
						break;
					
					case PuntuarGala.VEU:
						puntuacions[quina - 1].setPuntsVeu(valor/m.length);
						break;
					
					case PuntuarGala.INTERPRETACIO:
						puntuacions[quina - 1].setPuntsInterpretacio(valor / m.length);
						break;
						
					default:
						throw new ExcepcioGala("No existe");
					} 
				}
		
		}
	}
		
	public void afegirGala(String canço1, String canço2, int[] veu, int[]  coreo, int[] interpretacio){ 
		
			
		
	
	}

}
