package Part1;

public class Puntuacio {
	
	private int puntsCoreo;
	private int puntsInterpretacio;
	private int puntsVeu; 
	
	public Puntuacio() {
		this.puntsCoreo = 0;
		this.puntsInterpretacio = 0;
		this.puntsVeu = 0; 
	}
	
	public int getPuntsCoreo() {
		return this.puntsCoreo;
	}
	
	public void setPuntsCoreo(int punts) {
		this.puntsCoreo += punts;
	}
	
	public int getPuntsInterpretacio() {
		return this.puntsInterpretacio;
	}
	
	public void setPuntsInterpretacio(int punts) {
		this.puntsInterpretacio += punts;
	}
	public int getPuntsVeu() {
		return this.puntsVeu;
	}
	
	public void setPuntsVeu(int punts) {
		this.puntsVeu += punts;
	}

}
