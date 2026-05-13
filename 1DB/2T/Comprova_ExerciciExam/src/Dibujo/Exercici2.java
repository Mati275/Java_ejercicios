package Dibujo;

import Keyboard.Keyboard;


public class Exercici2 {
	public static void main (String[] args) {
	
		int altura = 9;
		
		for (int i = 0; i < altura; i++) {
			if(i == altura/2) {
				for (int j = 0; j < altura; j++) {
					if(j == altura/2) {
						System.out.print("0");
					} else {
						System.out.print("*");
					}
				}
				System.out.println();
			} else {
				for (int j = 0; j < altura; j++) {
					if ((i == 0 && j == 0) || (i == altura-1 && j == altura-1) ||
							(i == 0 && j == altura - 1) || (i == altura -1 && j == 0)) {
						System.out.print("0");						
					} else if (i==j || j == altura-i-1 || j == altura/2) { //fallo el j == altura -1 -i
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				System.out.println();

			}
		}

		
	}

//	private static int lleguriValor() {
//			int altura;
//			
//			System.out.print("Entar la altura de la estrella (tiene que ser un número im¡npar y más grande a 7): ");
//			altura = Keyboard.readInt();
//			
//			while (altura % 2 != 0 && altura > 7) {
//				System.out.println();
//				System.out.print("Entar la altura de la estrella (tiene que ser un número im¡npar y más grande a 7): ");
//				altura = Keyboard.readInt();
//			}
//			return altura;
//	}
}
