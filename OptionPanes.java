/**
 * 
 */
package applicat;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Creator of Custom JOptionPanes used in the Project
 * @author hp
 */
@SuppressWarnings("serial")
public class OptionPanes extends JOptionPane{
	/**
	 * @param pseudo String, the input
	 */
	protected String pseudo;
	/**
	 * uses input to return message about the existance of the player
	 * @param windowName Title of the OptionPane
	 * @throws ClassNotFoundException Class Not Found Exception 
	 * @throws SQLException SQL Exception
	 */
	public OptionPanes(String windowName) throws ClassNotFoundException, SQLException {
		/**
		 * Pane in the Dialog
		 * label: informative message in pane
		 * text: textfield in pane
		 */
		JPanel pane = new JPanel(new GridLayout(2,0));
		JLabel label = new JLabel("Saisir votre pseudo:");
		JTextField text = new JTextField(20);
		
		pane.add(label);
		pane.add(text);
		
		int opt;
		do {
		    opt = JOptionPane.showConfirmDialog(null, pane,
		            windowName, JOptionPane.OK_CANCEL_OPTION);
		    if (opt == JOptionPane.OK_OPTION) {//if the user press OK then
		    	SqlJoueur j = new SqlJoueur();
		    	pseudo = text.getText();
		    	if (j.pseudoExist(pseudo) && !pseudo.equals("")) {
		    		j.stop();
		    		JOptionPane.showMessageDialog (null, "'"+pseudo+"' Existe." ,"WARNING", 0);
		    		opt= JOptionPane.CLOSED_OPTION;
		    	}
		    	else {
		    		j.stop();
		    		JOptionPane.showMessageDialog (null, "Le pseudo '"+pseudo+"' n'Existe pas." ,"WARNING", 0);
		    		
		    	}
		    }
		}while (opt != JOptionPane.CANCEL_OPTION && opt != JOptionPane.CLOSED_OPTION );
	}
	/**
	 * Makes researches through the DB using input pseudo to allow access to certain tabs
	 * @param tab tabbedPane
	 * @param i : index of affected tab in the tabbedpane 
	 * @param windowName Title of the dialog
	 * @throws ClassNotFoundException Class Not Found Exception 
	 * @throws SQLException SQL Exception
	 */
	public OptionPanes(JTabbedPane tab, int i,String windowName) throws ClassNotFoundException, SQLException {
		JPanel pane = new JPanel(new GridLayout(2,0));
		JLabel label = new JLabel("Saisir votre pseudo:");
		JTextField text = new JTextField(20);
		
		pane.add(label);
		pane.add(text);
		
		int opt;
		do {
		    opt = JOptionPane.showConfirmDialog(null, pane,
		            windowName, JOptionPane.OK_CANCEL_OPTION);
		    if (opt == JOptionPane.OK_OPTION) {//if the user press OK then
		    	SqlJoueur j = new SqlJoueur();
		    	pseudo = text.getText();
		    	if (j.pseudoExist(pseudo) && !pseudo.equals("")) {
		        	
		        	//System.out.println(pseudo);
		        	if(i == 1) { 
		        		tab.setEnabledAt(i, true);
		        		tab.setSelectedIndex(i);
		        		break;
		        		}
		        	else if(i == 2) {
		        		if(j.isAdmin(pseudo)) {
		        			tab.setEnabledAt(i, true);
		        			tab.setSelectedIndex(i);
		        		break;
		        		}
		        		else {
		        			JOptionPane.showMessageDialog (null, "'"+pseudo+"' n'est pas Admin." ,"WARNING", 0);
		        		}
		        	}
		    
		        }
		    	else if(!j.pseudoExist(pseudo)){
		    		JOptionPane.showMessageDialog (null, "Le pseudo '"+pseudo+"' n'Existe pas." ,"WARNING", 0);
		    	}
		    	
		        
		    }
		} while (opt != JOptionPane.CANCEL_OPTION && opt != JOptionPane.CLOSED_OPTION );//If the user hit cancel then exit
		
	}
	
}
