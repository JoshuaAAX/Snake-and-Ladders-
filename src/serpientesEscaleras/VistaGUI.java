/*
 * Programación interactiva
 * Autor: damian.espinosa@correounivalle.edu.co - 202028180 
          joshua.chicame@correounivalle.edu.co - 202074121
          adolfo.maya@correounivalle.edu.co - 20202515
 * Mini proyecto 4 - Escaleras y Serpientes
 */
package serpientesEscaleras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import misComponentes.Titulos;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaGUI.
 */
public class VistaGUI extends JFrame {

	private Timer timerOne, timerTwo, timerThree, timerFour, timerFive, timerSix;
	private Escucha escucha;
	private Titulos titulo;
	private JPanel  zonaResultados, panelInicial;
	private JPanelBackground zonaJuego;
	private JButton salir, dado;
	private JLabel jugador, botOne, botTwo;
	private JLabel player, estrellaMorada, estrellaBlanca;
	private JTextField  fichaJugador, fichaBotOne, fichaBotTwo;
	private ImageIcon imagen;
	private JFrame vistaGridBagLayout;
	private ControlSerpientesEscaleras control;
	

	private int x, y;
	
	private int flagOne = 1;
	private int flagTwo = 1;
	private int flagThree  = 1;
	
	private int velocidad = 500;
	
	/**
	 * Instantiates a new vista GUI.
	 */
	public VistaGUI() 
	{
	   initGUI();
	   
	   this.setUndecorated(true);
	   this.setTitle("Escaleras y Serpientes");
	   this.setResizable(false);
	   this.pack();
	   this.setLocationRelativeTo(null);
	   this.setVisible(true);
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
	
		//set up window container - Layout
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
					
		//create Control, Listener and supports classes
		escucha = new Escucha();
		control = new ControlSerpientesEscaleras();
		vistaGridBagLayout=this;
		
		//Timers 
		timerOne = new Timer(velocidad, escucha);
		timerTwo = new Timer(velocidad, escucha);
		timerThree = new Timer(velocidad, escucha);
		timerFour = new Timer(velocidad, escucha);
		timerFive = new Timer(velocidad, escucha);
		timerSix = new Timer(velocidad, escucha);
		
			
		//init
		panelInicial = new JPanel();
		panelInicial.setLayout(new BorderLayout());
			
			
		titulo = new Titulos("Escaleras y Serpientes", 17, new Color(56,57,59));
		titulo.addMouseListener(escucha);
		titulo.addMouseMotionListener(escucha);
	    panelInicial.add(titulo, BorderLayout.CENTER );
			
		salir = new JButton();
		imagen = new ImageIcon("src/imagenes/salir.png");
		salir.setIcon(imagen);
		salir.setBorder(null);
		salir.setFocusPainted(false);
		salir.setBackground(new Color(56,57,59));
		salir.addActionListener(escucha);
		panelInicial.add(salir, BorderLayout.LINE_END);
			
		contraints.gridx=0;
		contraints.gridy=0;
		contraints.gridwidth=2;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.BOTH;
		add(panelInicial,contraints);
		
		
		//game zone
		zonaJuego = new JPanelBackground();
		zonaJuego.setLayout(null);
		zonaJuego.setPreferredSize(new Dimension(720,720));
		zonaJuego.setBackground("src/imagenes/boardNew.jpg");
		
		imagen = new ImageIcon("src/imagenes/jugador.png");
		player = new JLabel(imagen);
		player.setBounds(24,648,24,24);
		zonaJuego.add(player);
		
		
		imagen = new ImageIcon("src/imagenes/thanatos.png");
		estrellaMorada= new JLabel(imagen);
		estrellaMorada.setBounds(24,672,24,24);
		zonaJuego.add(estrellaMorada);
		
		imagen = new ImageIcon("src/imagenes/hypnos.png");
		estrellaBlanca= new JLabel(imagen);
		estrellaBlanca.setBounds(24,696,24,24);
		zonaJuego.add(estrellaBlanca);
		
		
		contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=2;
		contraints.fill=GridBagConstraints.BOTH;
		contraints.anchor=GridBagConstraints.CENTER;
		add(zonaJuego,contraints);
		
		//zona result
		zonaResultados = new JPanel();
		zonaResultados.setLayout(new GridLayout(6,1));
				
				
		jugador = new JLabel("Jugador ");
		jugador.setHorizontalAlignment(JLabel.CENTER);
		botOne = new JLabel("Thanatos");
		botOne.setHorizontalAlignment(JLabel.CENTER);
		botTwo = new JLabel("Hypnos"); 
		botTwo.setHorizontalAlignment(JLabel.CENTER);
			
		//ficha del jugador
		fichaJugador = new JTextField();
		fichaJugador.setText(String.valueOf("dado"));
		fichaJugador.setHorizontalAlignment(JLabel.CENTER);
		fichaJugador.setEditable(false);
		
		//ficha del primer bot
		fichaBotOne = new JTextField();
		fichaBotOne.setText(String.valueOf("dado"));
		fichaBotOne.setHorizontalAlignment(JLabel.CENTER);
		fichaBotOne.setEditable(false);
			
		//ficha del segundo bot
		fichaBotTwo = new JTextField();
		fichaBotTwo.setText(String.valueOf("dado"));
		fichaBotTwo.setHorizontalAlignment(JLabel.CENTER);
		fichaBotTwo.setEditable(false);
	

		zonaResultados.add(jugador);
		zonaResultados.add(fichaJugador);
		zonaResultados.add(botOne);
		zonaResultados.add(fichaBotOne);
		zonaResultados.add(botTwo);
		zonaResultados.add(fichaBotTwo);
		
		
		contraints.gridx=1; 
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.CENTER;		
		add(zonaResultados,contraints);		
		
		
		
		//button
		dado = new JButton();
		imagen = new ImageIcon("src/imagenes/1.png");
		dado.setIcon(imagen);
		dado.setBorder(null);
		dado.setFocusPainted(false);
		dado.addActionListener(escucha);
		
		
		contraints.gridx=1; 
		contraints.gridy=2;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.LAST_LINE_END;	
		add(dado,contraints);
	}
	
	/**
	 * Menu.
	 *
	 * @param titulo the titulo
	 */
	//ventana de opciones del juego
	private void menu(String titulo) {
		ImageIcon icono = new ImageIcon("src/imagenes/iconEnd.png");

		String[] botones = {"Reiniciar", "Continuar", "Salir"};

		int opciones = JOptionPane.showOptionDialog(null, 
				titulo, 
				"opciones de juego", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				icono, 
				botones, 
				botones[0]
		);

		//reiniciar
		if(opciones == 0) {
			System.gc();
			dispose();
			VistaGUI nuevaVista = new VistaGUI();
		} else if(opciones == 1) {
			
		} else {
			System.exit(0);		
		}
	}
	
	
	/**
	 * Menu final.
	 *
	 * @param titulo the titulo
	 */
	//ventana cuando finaliza el juego
	private void menuFinal(String titulo) {
		ImageIcon icono = new ImageIcon("src/imagenes/iconEnd.png");

		String[] botones = {"Reiniciar", "Salir"};

		int opciones = JOptionPane.showOptionDialog(null, 
				titulo, 
				"opciones de juego", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				icono, 
				botones, 
				botones[0]
		);

		//reiniciar
		if(opciones == 0) {
			
			
			System.gc();
			dispose();
			VistaGUI nuevaVista = new VistaGUI();
		}
		//salir
		else {
			System.exit(0);
		}
	}
	

	//En la función ingresa como parametro el valor int de una casilla, y la posición inicial de Y en pixeles en la que se encuentra esa casilla, 
    /**
	 * Index board.
	 *
	 * @param index the index
	 * @param positionY the position Y
	 * @return the int[]
	 */
	//devuelve la posición final(en pixeles) de acuerdo al valor de la casilla
	private int[] indexBoard(int index, int positionY) {
		int x = 24;
		int y = positionY;	
		
		for(int position = 2; position<=index; position++) {
			
			if(position <=10) {
			    x = x  + 72;	   
			}else if(position == 11) {
				//x = 648;
				y = y - 72;
			}else if(position >11 && position <= 20) {
				x = x - 72;
			}else if(position == 21) {
				y = y - 72;
			}else if(position >21 && position <= 30) {
				x = x  + 72;
			}else if(position == 31) {
				y = y - 72;
			}else if(position >31 && position <= 40) {
				x = x  - 72;
			}else if(position == 41) {
				y = y - 72;
			}else if(position >41 && position <= 50) {
				x = x  + 72;
			}else if(position == 51) {
				y = y - 72;
			}else if(position >51 && position <= 60) {
				x = x  - 72;
			}else if(position == 61) {
				y = y - 72;
    		}else if(position >61 && position <= 70) {
				x = x  + 72;
			}else if(position == 71) {
				y = y - 72;
			}else if(position >71 && position <= 80) {
				x = x  - 72;
			}else if(position == 81) {
				y = y - 72;
			}else if(position >81 && position <= 90) {
				x = x  + 72;
			}else if(position == 91) {
				y = y - 72;
			}else if(position >91 && position <= 100) {
				x = x  - 72;
			}			
			
		}
		return new int[] {x,y};
	}
	
	
	/**
	 * Index board one.
	 *
	 * @param index the index
	 * @return the int[]
	 */
	//llama a la función que determina en que posicion debe estar la ficha y la inicia en una posicion Y(648)
	private int[] indexBoardOne(int index) {
		int[] positions = indexBoard(index, 648);
		return new int[] {positions[0],positions[1]};
	}
	
	/**
	 * Index board two.
	 *
	 * @param index the index
	 * @return the int[]
	 */
	//llama a la función que determina en que posicion debe estar la ficha y la inicia en una posicion Y(672)
	private int[] indexBoardTwo(int index) {
		int[] positions = indexBoard(index, 672);
		return new int[] {positions[0],positions[1]};
	}
	
	/**
	 * Index board three.
	 *
	 * @param index the index
	 * @return the int[]
	 */
	//llama a la función que determina en que posicion debe estar la ficha y la inicia en una posicion Y(696)
	private int[] indexBoardThree(int index) {
		int[] positions = indexBoard(index, 696);
		return new int[] {positions[0],positions[1]};
	}
	
	
	/**
	 * The Class Escucha.
	 */
	private class Escucha extends MouseAdapter implements ActionListener {

		//private int flag =1;
		
		/** The y. */
		private int x, y;
		
		
		/**
		 * Action performed.
		 *
		 * @param eventAction the event action
		 */
		public void actionPerformed(ActionEvent eventAction) {
			
			//si da click al boton salir muentra el menú del juego
			if(eventAction.getSource()==salir) {
				menu("Opciones de Juego");
			} 
			
			//si da click en dado realiza un tiro y mueve las fichas
			if(eventAction.getSource()==dado) {
				
				//llama a la funcion que se encarga de mover la ficha del jugador
				control.moverFichaJugador();
				
				
				//ajusta el icono del dado
				dado.setIcon(new ImageIcon("src/imagenes/"+control.getNumeroDado()+".png"));
				
				fichaJugador.setText(String.valueOf("dado"));
				fichaBotOne.setText(String.valueOf("dado"));
				fichaBotTwo.setText(String.valueOf("dado"));
				
				//inicia el primer timer
				timerOne.start();
			}
			
			//timer que realiza  el movimiento de  las casilla del jugador
			if(eventAction.getSource()== timerOne) {
				dado.setEnabled(false);
				fichaJugador.setText(String.valueOf(control.getNumeroDado()));
				
				if(flagOne != control.getPreFichaJugador()) {
					int vida = control.getPreFichaJugador();
					
					//System.out.println(flagOne);
					int result[] = indexBoardOne(flagOne); 
					//System.out.println(result[0]+","+result[1]);
					player.setBounds(result[0],result[1],24,24);
					
					if( flagOne < vida) {
						flagOne = flagOne +1;
				    }	
					//System.out.println(control.getPreFichaJugador());
				} else {
					int positionFicha[] = indexBoardOne(flagOne); 
					player.setBounds(positionFicha[0], positionFicha[1], 24, 24);
					
					timerOne.stop();
					timerTwo.start();
				}
				
				
			} 
			
			//timer que realiza el movimiento de la casilla especial sea serpiente o escalera del jugador
			if(eventAction.getSource()== timerTwo) {
				flagOne = control.getFichaJugador();
				int positionFicha[] = indexBoardOne(control.getFichaJugador()); 
				player.setBounds(positionFicha[0], positionFicha[1], 24, 24);
				
				if(flagOne == 100) { 
					menuFinal("Ganaste!");
				}
				
				timerTwo.stop();
				timerThree.start();
			} 
			
			
			//timer que realiza  el movimiento de  las casilla del primer Bot (thanatos)
			if(eventAction.getSource()== timerThree) {
				fichaBotOne.setText(String.valueOf(control.getNumeroDadoBotOne()));
				
				if(flagTwo != control.getPreFichaBotOne()) {
					int vida = control.getPreFichaBotOne();
					
					//System.out.println(flagTwo);
					int result[] = indexBoardTwo(flagTwo); 
					//System.out.println(result[0]+","+result[1]);
					estrellaMorada.setBounds(result[0],result[1],24,24);
					
					if( flagTwo < vida) {
						flagTwo = flagTwo +1;
				    }	
					//System.out.println(control.getPreFichaBotOne());
				} else {
					int positionFichaTwo[] = indexBoardTwo(flagTwo); 
					estrellaMorada.setBounds(positionFichaTwo[0], positionFichaTwo[1], 24, 24);
					
					
					
					timerThree.stop();
					timerFour.start();
				}
					
			} 
			
			//timer que realiza el movimiento de la casilla especial sea serpiente o escalera del  primer bot
			if(eventAction.getSource()== timerFour) {
				flagTwo = control.getFichaBotOne();
				int positionFichaTwo[] = indexBoardTwo(control.getFichaBotOne()); 
				estrellaMorada.setBounds(positionFichaTwo[0], positionFichaTwo[1], 24, 24);
				
				if(flagTwo == 100) {
					menuFinal("Perdiste! Gano Thanatos");
				}
				
				timerFour.stop();
				timerFive.start();
			} 
			
			
			//timer que realiza  el movimiento de  las casilla del segundo Bot (hypnos)
            if(eventAction.getSource()== timerFive) {
            	fichaBotTwo.setText(String.valueOf(control.getNumeroDadoBotTwo()));
				
				if(flagThree!= control.getPreFichaBotTwo()) {
					int vida = control.getPreFichaBotTwo();
					
					//System.out.println(flagTwo);
					int result[] = indexBoardThree(flagThree); 
					//System.out.println(result[0]+","+result[1]);
					estrellaBlanca.setBounds(result[0],result[1],24,24);
					
					if( flagThree < vida) {
						flagThree = flagThree +1;
				    }	
					//System.out.println(control.getPreFichaBotOne());
				} else {
					int positionFichaThree[] = indexBoardThree(flagThree); 
					estrellaBlanca.setBounds(positionFichaThree[0], positionFichaThree[1], 24, 24);
					
					timerFive.stop();
					timerSix.start();
				}
				
				
			} 
			
            
          //timer que realiza el movimiento de la casilla especial sea serpiente o escalera del segundo bot
			if(eventAction.getSource()== timerSix) {
				flagThree = control.getFichaBotTwo();
				int positionFichaThree[] = indexBoardThree(control.getFichaBotTwo()); 
				estrellaBlanca.setBounds(positionFichaThree[0], positionFichaThree[1], 24, 24);
				
				if(flagThree == 100) {
					menuFinal("Perdiste! Gano Hypnos");
				}
				
				dado.setEnabled(true);
				timerSix.stop();
				
			} 
			
	}
		
		
		
		/**
		 * Mouse dragged.
		 *
		 * @param eventMouseMotion the event mouse motion
		 */
		public void mouseDragged(MouseEvent eventMouseMotion) {
			
			setLocation(vistaGridBagLayout.getLocation().x+eventMouseMotion.getX()-x,
					    vistaGridBagLayout.getLocation().y+eventMouseMotion.getY()-y);
				
		}
		  
		 
	  	/**
	  	 * Mouse pressed.
	  	 *
	  	 * @param eventMouse the event mouse
	  	 */
	  	public void mousePressed(MouseEvent eventMouse) {
			
			x = eventMouse.getX();
			y = eventMouse.getY();
				
		}
		
	   
	
	 }
	
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

		
	 
	
   }

