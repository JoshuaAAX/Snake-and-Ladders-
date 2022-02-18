/*
 * Programación interactiva
 * Autor: damian.espinosa@correounivalle.edu.co - 202028180 
          joshua.chicame@correounivalle.edu.co - 202074121
          adolfo.maya@correounivalle.edu.co - 20202515
 * Mini proyecto 4 - Escaleras y Serpientes
 */
package serpientesEscaleras;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Dado.
 */
public class Dado {
	
	private int number;
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber() {
		Random aleatorio = new Random();
		number = aleatorio.nextInt(6) + 1;
		return number;
	}
}
