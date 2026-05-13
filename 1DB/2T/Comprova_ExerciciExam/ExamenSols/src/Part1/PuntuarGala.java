package Part1;

public interface PuntuarGala {

	public final static int COREO = 10;
	public final static int VEU = 15;
	public final static int INTERPRETACIO = 10;
	
	public int puntuacioFinal (int quina) throws ExcepcioGala;
	
	public void setPuntuar (int [] m, int ambit, int gala) throws ExcepcioGala;
	
}
