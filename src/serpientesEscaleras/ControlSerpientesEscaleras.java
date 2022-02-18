/*
 * Programación interactiva
 * Autor: damian.espinosa@correounivalle.edu.co - 202028180 
          joshua.chicame@correounivalle.edu.co - 202074121
          adolfo.maya@correounivalle.edu.co - 20202515
 * Mini proyecto 4 - Escaleras y Serpientes
 */
package serpientesEscaleras;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlSerpientesEscaleras.
 */
public class ControlSerpientesEscaleras {

	private BotJugador botOne = new BotJugador("Thanatos");
	private BotJugador botTwo = new BotJugador("Hypnos");
	private ArrayList<Casilla> board = new ArrayList<Casilla>();
	
	private Dado dice;
	private int numeroDado;
	
	private int fichaJugador = 1;
	private int preFichaJugador = -1;
	
	private int numeroDadoBotOne = 1;
	private int fichaBotOne = 1;
	private int preFichaBotOne = -1;
	
	private int numeroDadoBotTwo= 1;
	private int fichaBotTwo = 1;
	private int preFichaBotTwo= -1;
	


	private Lock bloqueo =  new ReentrantLock();

	
	/**
	 * Instantiates a new control serpientes escaleras.
	 */
	//Construtor 
	public ControlSerpientesEscaleras() {
		createBoard();
		//printBoard();
		
	}
	
	/**
	 * Creates the board.
	 */
	//Crea el tablero y le agrega los valores que representan si es escalera o serpiente
	private void createBoard() {
		int x = 0;
		int y = 648;	
		
		for(int position = 1; position <= 100; position++) {
			board.add(new Casilla(position,0,0,0,0));
		}
		
		
		//first snake
		board.get(0).setSnakeTail(1);
		board.get(72).setSnakeHead(1);
		
		//second snake
		board.get(4).setSnakeTail(2);
		board.get(45).setSnakeHead(2);
		
		//third snake
		board.get(6).setSnakeTail(3);
		board.get(54).setSnakeHead(3);
		
		//fourth snake
		board.get(8).setSnakeTail(4);
		board.get(47).setSnakeHead(4);
		
		//fifth snake
		board.get(10).setSnakeTail(5);
		board.get(51).setSnakeHead(5);
		
		//sixth snake
		board.get(16).setSnakeTail(6);
		board.get(58).setSnakeHead(6);
		
		//seventh snake
		board.get(18).setSnakeTail(7);
		board.get(82).setSnakeHead(7);
		
		//eighth snake
		board.get(21).setSnakeTail(8);
		board.get(43).setSnakeHead(8);
	
		//nineth snake
		board.get(23).setSnakeTail(9);
		board.get(94).setSnakeHead(9);
		
		//tenth snake
		board.get(27).setSnakeTail(10);
		board.get(97).setSnakeHead(10);
		
		//eleventh snake
		board.get(32).setSnakeTail(11);
		board.get(68).setSnakeHead(11);
		
		//twelfth snake
		board.get(35).setSnakeTail(12);
		board.get(63).setSnakeHead(12);
		
		//thirteenth snake
		board.get(50).setSnakeTail(13);
		board.get(91).setSnakeHead(13);
		
		
		
		
		//first stair
		board.get(7).setStairDown(1);
		board.get(25).setStairUp(1);
		
		//second stair
		board.get(20).setStairDown(2);
		board.get(81).setStairUp(2);
		
		//third stair
		board.get(42).setStairDown(3);
		board.get(76).setStairUp(3);
		
		//fourth stair
		board.get(49).setStairDown(4);
		board.get(90).setStairUp(4);
		
		//fifth snake
		board.get(54).setStairDown(5);
		board.get(93).setStairUp(5);
		
		//sixth stair
		board.get(61).setStairDown(6);
		board.get(95).setStairUp(6);
		
		//seventh stair
		board.get(65).setStairDown(7);
		board.get(86).setStairUp(7);
		
		//eighth stair
		board.get(79).setStairDown(8);
		board.get(99).setStairUp(8);
		
		
		
	}
	
	
	/**
	 * Find snake tail.
	 *
	 * @param snakeHead the snake head
	 * @return the int
	 */
	//la entrada es el index de una cabeza de serpiente y devuelve el index de la cola
	public int findSnakeTail(int snakeHead) {
		int index = -1;
		for(int position = 0; position < 100; position++) {
			if(board.get(position).getSnakeTail()== snakeHead) {
				index = board.get(position).getIndex();
				break;
			}
		}
		return index;
	}
	
	
	/**
	 * Find stair up.
	 *
	 * @param stairDown the stair down
	 * @return the int
	 */
	//la entrada es el index de la parte de abajo de la escalera y devuelve el index de la parte de arriba de la escalera
	public int findStairUp(int stairDown){
		int index = -1;
		for(int position = 0; position < 100; position++) {
			if(board.get(position).getStairUp() == stairDown) {
				index = board.get(position).getIndex();
				break;
			}
		}
		return index;
	}
	
	
	/**
	 * Tirar dado.
	 *
	 * @return the int
	 */
	//tira el dado y devuelve un entero de 1-6
    public int tirarDado() {
		dice = new Dado();
		int posicionFicha = dice.getNumber();
		return posicionFicha;
	}
	
	
    //ingresa como parametro el index de la casilla actual de una ficha y el nombre del bot,
    /**
     * Mover ficha.
     *
     * @param indexCasilla the index casilla
     * @param nombre the nombre
     * @return the int[]
     */
    //luego tira  el dado y devuelve dos posiciones, si es una casilla especial (sepiente o escalera) pues guarda la posicion a la que debe caer y a la que esta antes de caer
    public int[] moverFicha(int indexCasilla, String nombre) {
		
    	bloqueo.lock();
    	
    	int indexPreCasilla = 1;
    	
    	try {
    		numeroDado = tirarDado();
    		System.out.println("dado: "+ nombre +": "+getNumeroDado());
    		
    		if(indexCasilla+getNumeroDado() <= 100) {
    			 indexCasilla += numeroDado;
    			 indexPreCasilla = indexCasilla;
    			 System.out.println("ficha "+nombre +": "+ indexCasilla );
    			 
    			 indexCasilla = estadoFicha(indexPreCasilla);
    			System.out.println("ficha analizada " +nombre +": "+indexCasilla);
    			System.out.println("");
    		}else if(indexCasilla+getNumeroDado() > 100) {
    			indexCasilla = indexCasilla;
    			indexPreCasilla = indexCasilla;
    			
    		}
    		return new int[] {indexCasilla, indexPreCasilla, numeroDado};
    		
    	}finally {
    		bloqueo.unlock();
    	}
  
	}
	
    
    //Ejecuta los hilos en orden, espera a que termine las operaciones del primer bot y empieza el segundo
    /**
     * Mover bot jugadores.
     */
    //guarda los atributos que tiene la clase Bot en variables globales de la clase control
	public void moverBotJugadores() {
    	 ExecutorService ejecutorHilos = Executors.newCachedThreadPool();
			
		 Future futureOne = ejecutorHilos.submit(botOne);
		 try {
			futureOne.get();
			
			numeroDadoBotOne = botOne.getNumeroDado();
			preFichaBotOne = botOne.getIndexPreCasilla();
			fichaBotOne = botOne.getIndexCasilla();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		 
	     Future futureTwo = ejecutorHilos.submit(botTwo);
	     try {
			futureTwo.get();
			
			numeroDadoBotTwo = botTwo.getNumeroDado();
			fichaBotTwo = botTwo.getIndexCasilla();
			preFichaBotTwo = botTwo.getIndexPreCasilla();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    //mueve la ficha del jugador, realiza un tiro de dato y actualiza el número de la casilla que se encuentra el jugador
	/**
     * Mover ficha jugador.
     */
    //luego llama a la funcion que se encarga de ejecutar los hilos de los Bots
	public void moverFichaJugador() {
		
		//realiza un tiro de dado
		numeroDado = tirarDado();
		System.out.println("dado: jugador: "+getNumeroDado());
		
		//le suma ese tiro a la casilla
		if(getFichaJugador()+getNumeroDado() <= 100) {
			 fichaJugador += numeroDado;
			 preFichaJugador = fichaJugador;
			 System.out.println("ficha  "+getFichaJugador());
			
			 //analiza la casilla que salio de la suma, para ver si es escalera o serpiente y en ese caso a donde apunta
			 fichaJugador = estadoFicha(fichaJugador);
			 System.out.println("ficha analizada "+getFichaJugador());
			 System.out.println("");
			  
		}else if(getFichaJugador()+getNumeroDado() > 100){
			fichaJugador = getFichaJugador();
			 preFichaJugador = getPreFichaJugador();
		}
	
		// llama a la función que se encarga simular los bots
	    moverBotJugadores();
	}
	
	
	//ingresa como parametro el número de una casilla y devuelve el valor al que debe apuntar esa casilla en caso que sea serpiente o escalera
	/**
	 * Estado ficha.
	 *
	 * @param ficha the ficha
	 * @return the int
	 */
	//si no es una ficha especial devuelve la misma casilla
	public int estadoFicha(int ficha) {
		int estadoFinal = ficha;
		
		if(board.get(ficha-1).getSnakeHead() != 0) {
			estadoFinal =  findSnakeTail(board.get(ficha-1).getSnakeHead());
		}else if(board.get(ficha-1).getStairDown() != 0) {
			estadoFinal =  findStairUp(board.get(ficha-1).getStairDown());
	    }
		return estadoFinal;
	}
	
	
	
	/**
	 * Gets the pre ficha jugador.
	 *
	 * @return the pre ficha jugador
	 */
	public int getPreFichaJugador() {
		return preFichaJugador;
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
	 * Gets the pre ficha bot one.
	 *
	 * @return the pre ficha bot one
	 */
	public int getPreFichaBotOne() {
		return preFichaBotOne;
	}


	/**
	 * Gets the pre ficha bot two.
	 *
	 * @return the pre ficha bot two
	 */
	public int getPreFichaBotTwo() {
		return preFichaBotTwo;
	}

	/**
	 * Gets the ficha bot one.
	 *
	 * @return the ficha bot one
	 */
	public int getFichaBotOne() {
		return fichaBotOne;
	}


	/**
	 * Gets the ficha bot two.
	 *
	 * @return the ficha bot two
	 */
	public int getFichaBotTwo() {
		return fichaBotTwo;
	}


	/**
	 * Sets the numero dado.
	 *
	 * @param numeroDado the new numero dado
	 */
	public void setNumeroDado(int numeroDado) {
		this.numeroDado = numeroDado;
	}


	/**
	 * Gets the ficha jugador.
	 *
	 * @return the ficha jugador
	 */
	public int getFichaJugador() {
		return fichaJugador;
	}


	/**
	 * Sets the ficha jugador.
	 *
	 * @param fichaJugador the new ficha jugador
	 */
	public void setFichaJugador(int fichaJugador) {
		this.fichaJugador = fichaJugador;
	}
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public ArrayList<Casilla> getBoard() {
		return board;
	}
	
	

	/**
	 * Gets the bot two.
	 *
	 * @return the bot two
	 */
	public BotJugador getBotTwo() {
		return botTwo;
	}


	/**
	 * Sets the bot two.
	 *
	 * @param botTwo the new bot two
	 */
	public void setBotTwo(BotJugador botTwo) {
		this.botTwo = botTwo;
	}


	/**
	 * Gets the numero dado bot one.
	 *
	 * @return the numero dado bot one
	 */
	public int getNumeroDadoBotOne() {
		return numeroDadoBotOne;
	}


	/**
	 * Gets the numero dado bot two.
	 *
	 * @return the numero dado bot two
	 */
	public int getNumeroDadoBotTwo() {
		return numeroDadoBotTwo;
	}



}
