/*
 * Programación interactiva
 * Autor: damian.espinosa@correounivalle.edu.co - 202028180 
          joshua.chicame@correounivalle.edu.co - 202074121
          adolfo.maya@correounivalle.edu.co - 20202515
 * Mini proyecto 4 - Escaleras y Serpientes
 */
package serpientesEscaleras;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;


// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalSerpientesEscaleras.
 */
public class PrincipalSerpientesEscaleras {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		  String javaLookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
		  UIManager.setLookAndFeel(javaLookAndFeel);
		} catch (Exception e) {
		  JOptionPane.showMessageDialog(null, "Opps hay una daño en la JVM");
		} 
				
		EventQueue.invokeLater(new Runnable() {
		  @Override
		  public void run() {
			VistaGUI myVista = new VistaGUI();
			 
		  }	
		});
			
			
	}

}
