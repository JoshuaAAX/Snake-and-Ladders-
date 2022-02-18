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
 * The Class Casilla.
 */
public class Casilla {
	
	/** The stairs down. */
	private int index, snakeTail, snakeHead, stairsUp, stairsDown;
	
	/**
	 * Instantiates a new casilla.
	 *
	 * @param index the index
	 * @param snakeTail the snake tail
	 * @param snakeHead the snake head
	 * @param stairsUp the stairs up
	 * @param stairsDown the stairs down
	 */
	//Constructor de casilla 
	public Casilla(int index, int snakeTail, int snakeHead, int stairsUp, int stairsDown) {
		//casilla contiene una posición que es un valor entero
		this.index = index;
		//si es una cabeza o cola de serpiente es diferente de 0
		this.snakeTail = snakeTail;
		this.snakeHead = snakeHead;
		//si es la parte de arriba o parte de abajo de una escalera es diferente de 0
		this.stairsUp = stairsUp;
		this.stairsDown = stairsDown;
	}

	/**
	 * Gets the snake tail.
	 *
	 * @return the snake tail
	 */
	public int getSnakeTail() {
		return snakeTail;
	}

	/**
	 * Sets the snake tail.
	 *
	 * @param snakeTail the new snake tail
	 */
	public void setSnakeTail(int snakeTail) {
		this.snakeTail = snakeTail;
	}

	/**
	 * Gets the snake head.
	 *
	 * @return the snake head
	 */
	public int getSnakeHead() {
		return snakeHead;
	}

	/**
	 * Sets the snake head.
	 *
	 * @param snakeHead the new snake head
	 */
	public void setSnakeHead(int snakeHead) {
		this.snakeHead = snakeHead;
	}

	/**
	 * Gets the stair up.
	 *
	 * @return the stair up
	 */
	public int getStairUp() {
		return stairsUp;
	}

	/**
	 * Sets the stair up.
	 *
	 * @param stairsUp the new stair up
	 */
	public void setStairUp(int stairsUp) {
		this.stairsUp = stairsUp;
	}

	/**
	 * Gets the stair down.
	 *
	 * @return the stair down
	 */
	public int getStairDown() {
		return stairsDown;
	}

	/**
	 * Sets the stair down.
	 *
	 * @param stairsDown the new stair down
	 */
	public void setStairDown(int stairsDown) {
		this.stairsDown = stairsDown;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}	
}
