/**
 * 
 */
package applicat;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * Creator of System JOptionPanes for pseudo input and process, NOT USED
 * @author hp
 *
 */
@SuppressWarnings("serial")
public class pseudoOptionPane extends JOptionPane {
	protected String pseudo;
	/**
	 * 
	 */
	public pseudoOptionPane() {
		
		try {
			do {
			
				pseudo = showInputDialog(null,"Saisissez Pseudo SVP:", "Vous voulez jouer", 3);
		
			} while(pseudo.equals("") || pseudo == null);
		}
		catch(NullPointerException e) {
			
		}
	}
	
	public pseudoOptionPane(JTabbedPane tbp, int i) {
		if(i==0) {
			do {
				pseudo = JOptionPane.showInputDialog(null,"Saisissez Pseudo SVP:", "Vous voulez jouer", 3);
			} while(pseudo.equals(""));
			tbp.setEnabledAt(1, true);
			tbp.setSelectedIndex(1);
		}
		else if(i==1) {
			do {
				pseudo = JOptionPane.showInputDialog(null,"Saisissez Pseudo SVP:", "Vous voulez jouer", 3);
			} while(pseudo.equals("") || pseudo == null);
			tbp.setEnabledAt(2, true);
			tbp.setSelectedIndex(2);
		}
	}

}
