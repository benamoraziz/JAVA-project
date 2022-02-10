/**
 * 
 */
package applicat;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * The Creator of Inscription Tab
 * @author hp
 */
@SuppressWarnings("serial")
public class InscriptionTab extends PanelBL {
	/**
	 * Instances Field
	 * @param pseudo : JTextField, Player's pseudo
	 * @param nom : JTextField, Player's Family name
	 * @param email : JTextField,Player's email
	 * @param prenom : JTextField, Player's name
	 * @param dateInscription : JFormattedTextField, none-editable , takes today's date in formate (yyyy-mm-dd)
	 * @param genre : Combobox, none-editable, containes two options ("Masculin", "Feminin")
	 */
	private JTextField pseudo, nom, email, prenom;
	private JFormattedTextField dateInscription;
	private JComboBox<String> genre;
	
	/**
	 * Default Constructor
	 */
	public InscriptionTab() {
		
		//setTopIcon(new ImageIcon("img/inscription.gif"));
		setTopIcon(new ImageIcon(getClass().getClassLoader().getResource("inscription.gif")));
		Top.setBackground(Color.blue);
		
		// Inscription Mid
		setCenterGBL();
		
		//Labels Declaration
		JLabel phraseInscriptionLabel = new JLabel("Inscrivez vous avant de commencer à jouer");
		JLabel formPseudo = new JLabel("Pseudo");
		JLabel formNom = new JLabel("Nom");
		JLabel formEmail = new JLabel("E-mail");
		JLabel formGenre = new JLabel("Genre");
		JLabel formPrenom = new JLabel("Prenom");
		JLabel formDate = new JLabel("Date d'inscription");
		
		//TextFields Set-Up
		pseudo = new JTextField();
		pseudo.setColumns(10);
		nom = new JTextField();
		nom.setColumns(10);
		email = new JTextField();
		email.setColumns(10);
		prenom = new JTextField();
		prenom.setColumns(10);
		//Auto-generated Date
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");  
		dateInscription = new JFormattedTextField(f.format(new java.util.Date()));
		
		dateInscription.setEditable(false);
		
		genre = new JComboBox<String>(new String[] {"Masculin","Féminin"});
		
		//Center Fill-Up with the Inscription Forum
		gbcConstraints(phraseInscriptionLabel, 0, 0);
		gbcConstraints(formPseudo, 0, 1);
		gbcConstraints(formNom, 0, 2);
		gbcConstraints(formEmail, 0, 3);
		gbcConstraints(formGenre, 2, 1);
		gbcConstraints(formPrenom, 2, 2);
		gbcConstraints(formDate, 2, 3);
		gbcConstraints(pseudo, 1, 1);
		gbcConstraints(nom, 1, 2);
		gbcConstraints(email, 1, 3);
		gbcConstraints(genre, 3, 1);
		gbcConstraints(prenom, 3, 2);
		gbcConstraints(dateInscription, 3, 3);
		
		//First-Bottom
		setBottom();
		Bottom.setBackground(Color.WHITE);
		//Declare & Add Bottons
		JButton formSubmit = new JButton("Ajouter");
		GridBagConstraints gbcFormSubCan = new GridBagConstraints();
		gbcFormSubCan.anchor = GridBagConstraints.WEST;
		Bottom.add(formSubmit,gbcFormSubCan);
		JButton formCancel = new JButton("Annuler");
		Bottom.add(formCancel,gbcFormSubCan);
		
		formCancel.addActionListener(new Annuler());
		formSubmit.addActionListener(new Ajouter());
		
	}
	/**
	 * ActionListener Resets this Tab 
	 * @author hp
	 *
	 */
	class Annuler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			pseudo.setText("");
			nom.setText("");
			prenom.setText("");
			email.setText("");
			genre.setSelectedIndex(0);
		}
		
	}
	/**
	 *ActionListener, Adds entered info to the database 
	 * @author hp
	 *
	 */
	class Ajouter implements ActionListener{
		/**
		 * @param ps : String, text entered in 'pseudo' textfield
		 * @param name : String, text enterd in 'nom' textfield
		 * @param surname : String, text entered in 'prenom' textfield
		 * @param mail : String, text entered in 'email' textfield
		 * @param sexe : String, text selected from 'genre' Combobox
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String ps = pseudo.getText();
			String name = nom.getText();
			String surname = prenom.getText();
			String mail = email.getText();
			String sexe = (String) genre.getSelectedItem();
			
			try {
				SqlJoueur db = new SqlJoueur();
				db.DbInsert(ps, name, surname, mail, sexe);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			pseudo.setText("");
			nom.setText("");
			prenom.setText("");
			email.setText("");
			genre.setSelectedIndex(0);
		}
		
	}
}
