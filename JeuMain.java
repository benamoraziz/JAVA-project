/**
 * 
 */
package applicat;

import javax.swing.SwingUtilities;

/**
 * Main Runnable of the game,
 * @author hp
 * 
 */
public class JeuMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new InterfaceJeu();
			}
		});
	}

}
