/*
 * Programación interactiva
 * Autor: damian.espinosa@correounivalle.edu.co - 202028180 
          joshua.chicame@correounivalle.edu.co - 202074121
          adolfo.maya@correounivalle.edu.co - 20202515
 * Mini proyecto 4 - Escaleras y Serpientes
 */
package serpientesEscaleras;

// TODO: Auto-generated Javadoc
/**
 * The Class BotJugador.
 */
public class BotJugador implements Runnable {


	private ControlSerpientesEscaleras control;
	private int numeroDado;
	private int indexPreCasilla = 1;
	private int indexCasilla = 1;
	private String nombre;
	
	/**
	 * Instantiates a new bot jugador.
	 *
	 * @param nombre the nombre
	 */
	//Constructor, se necesita un nombre para instaciar el Bot
	public BotJugador(String nombre) {
		this.nombre = nombre;
		indexCasilla= 1;
		
	}
	
	/**
	 * Run.
	 */
	//utiliza la clase del control para llamar a la funcion de mover ficha, ingresando el número de la casilla en la que se encuentra el bot
	public void run() {
		control = new ControlSerpientesEscaleras();
		
		int result[] = control.moverFicha(indexCasilla, getNombre());
		
		numeroDado = result[2];
		indexPreCasilla = result[1];
		indexCasilla = result[0];
				
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Gets the numero dado.
	 *
	 * @return the numero dado
	 */
	public int getNumeroDado() {
		return numeroDado;
	}

	/**
	 * Gets the index pre casilla.
	 *
	 * @return the index pre casilla
	 */
	public int getIndexPreCasilla() {
		return indexPreCasilla;
	}

	/**
	 * Gets the index casilla.
	 *
	 * @return the index casilla
	 */
	public int getIndexCasilla() {
		return indexCasilla;
	}

	/**
	 * Sets the index casilla.
	 *
	 * @param indexCasilla the new index casilla
	 */
	public void setIndexCasilla(int indexCasilla) {
		this.indexCasilla = indexCasilla;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
}