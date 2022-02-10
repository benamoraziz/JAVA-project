/**
 * 
 */
package applicat;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *The Creator of the 'Classement et Gestion' Tab
 *@author hp
 */
@SuppressWarnings("serial")
public class ClassementTab extends PanelBL{
	
	/**
	 * Instances field
	 * @param pseudo: JTextfield, none-editable, takes for values the pseudos of created players
	 * @param nom: JTextfield, editable, takes original name of the selected player
	 * @param email: JTextfield, editable, takes original email of the selected player
	 * @param prenom: JTextfield, editable, takes original family name of the selected player
	 * @param score: JTextfield, none-editable, takes for value the calculated score of the player 
	 * @param genre: JComboBox, none-editable, containes two options ("Masculin", "Feminin")
	 * @param role: JComboBox, none-editable, containes two options ("Admin", "Joueur")
	 * @param previous: JButton, set's displayed info to info about the previous player
	 * @param next: JButton, set's displayed info to info about the next player
	 * @param supprimer: JButton, deletes selected player
	 * @param modifier: JButton, Updates info about selected player
	 * @param recherche: JButton, Researches for player, checks if it exists, sets displayed info to info about that player 
	 * @param quitter: JButton, Quits this tab and sets it disabled 
	 * @param db: SqlJoueur
	 * @param r: ResultSet, aquired from db 
	 */
	private JTextField pseudo, nom, email, prenom, score;
	private JFormattedTextField dateInscription;
	private JComboBox<String> genre, role;
	private JButton previous, next,supprimer,modifier;
	
	protected JButton recherche,quitter;
	
	protected SqlJoueur db;
	protected ResultSet r;
	
	/**
	 * Default Constructor
	 */
	public ClassementTab() {
		
		//TOP
		//setTopIcon(new ImageIcon("img/goodpod1.png"));
		setTopIcon(new ImageIcon(getClass().getClassLoader().getResource("goodpod1.PNG")));
		
		//MID
		
		setCenterGBL();
		Center.setBackground(Color.decode("#00CCCC"));

		JLabel intro1 = new JLabel("Bienvenu ....");
		JLabel intro2 = new JLabel("Les joueurs sont classifié par ordre croissant de leurs nombre d'essai");
		JLabel formPseudo = new JLabel("Pseudo");
		JLabel formNom = new JLabel("Nom");
		JLabel formEmail = new JLabel("E-mail");
		JLabel formGenre = new JLabel("Genre");
		JLabel formRole = new JLabel("Role");
		JLabel formPrenom = new JLabel("Prenom");
		JLabel formDate = new JLabel("Date d'inscription");
		JLabel formScore = new JLabel("Score");
		
		pseudo = new JTextField();
		pseudo.setEditable(false);
		pseudo.setColumns(10);
		nom = new JTextField();
		nom.setColumns(10);
		email = new JTextField();
		email.setColumns(10);
		prenom = new JTextField();
		prenom.setColumns(10);
		score = new JTextField();
		score.setEditable(false);
		score.setColumns(10);
		//Auto-generated Date
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");  
		dateInscription = new JFormattedTextField(f.format(new java.util.Date()));
		genre = new JComboBox<String>(new String[] {"Masculin","Féminin"});
		role = new JComboBox<String>(new String[] {"Joueur","Admin"});
		
		gbcConstraints(intro1, 1, 0);
		gbcConstraints(intro2, 3, 0);
		gbcConstraints(formPseudo, 0, 1); gbcConstraints(pseudo, 1, 1);
		gbcConstraints(formNom, 0, 2); gbcConstraints(nom, 1, 2);
		gbcConstraints(formPrenom, 0, 3); gbcConstraints(prenom, 1, 3);
		gbcConstraints(formGenre, 0, 4); gbcConstraints(genre, 1, 4);
		gbcConstraints(formRole, 2, 1); gbcConstraints(role, 3, 1);
		gbcConstraints(formEmail, 2, 3); gbcConstraints(email, 3, 3);
		gbcConstraints(formDate, 2, 4); gbcConstraints(dateInscription, 3, 4);
		gbcConstraints(formScore, 4, 1); gbcConstraints(score, 5, 1);
		
		previous = new JButton("<"); gbcConstraints(previous, 1, 5);
		next = new JButton(">"); gbcConstraints(next, 3, 5);
		supprimer = new JButton("Supprimer"); gbcConstraints(supprimer, 1, 6);
		modifier = new JButton("Modifier"); gbcConstraints(modifier, 3, 6);
		
		///////////
		
		//Right
		setRightGBL();
		Right.setLayout(new FlowLayout());
		
		recherche = new JButton("Recherche");
		quitter = new JButton("Quitter");
		
		Right.add(recherche);
		Right.add(quitter);
		
		Right.setBackground(Color.decode("#0066CC"));
		
		//////////////////SQL
		try {
			db = new SqlJoueur();
			r = db.getSortedScore();
			fill();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			
		next.addActionListener(new Next());
		previous.addActionListener(new Previous());
		supprimer.addActionListener(new Delete());
		modifier.addActionListener(new Modify());
		recherche.addActionListener(new Recherche());
		quitter.addActionListener(new QuitterClassement());
	}
	/**
	 * 
	 * @throws SQLException
	 * Fills the TextField with information aquired from FIRST row of r (ResultSet)
	 */
	public void fill() throws SQLException {
		
		pseudo.setText(r.getString(1));
		role.setSelectedIndex(r.getInt(2));
		nom.setText(r.getString(3));
		prenom.setText(r.getString(4));
		dateInscription.setText(r.getString(5));
		email.setText(r.getString(6));
		score.setText(r.getString(8));
		genre.setSelectedItem(r.getString(7));
	}
	/**
	 * 
	 * @throws SQLException
	 * Fills the TextField with information aquired from NEXT row of r (ResultSet)
	 * if r is run-through completely, it goes back to FIRST and repeat.
	 */
	public void fill_next() throws SQLException {
		
		if(r.next()) {
			fill();
		}
		else if(r.first()) {
			fill();
		}
	}
	/**
	 * 
	 * @throws SQLException
	 * Fills the TextField with information aquired from PREVIOUS row of r (ResultSet)
	 * if r is run-through completely, it goes back to LAST and repeat.
	 */
	public void fill_previous() throws SQLException {
		
		if(r.previous()) {
			fill();
		}
		else if(r.last()) {
			fill();
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 * Resets classement Tab by,
	 * Selecting FIRST row of r, and refilling the textfield with the info.
	 */
	public void reset() throws SQLException {
		r.first();
		fill();
	}
	
	/**
	 *ActionListener for JButton:
	 *next 
	 * @author hp
	 *
	 */
	class Next implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				fill_next();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 *ActionListener for JButton:
	 *previous 
	 * @author hp
	 *
	 */
	class Previous implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				fill_previous();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 *ActionListener for JButton:
	 *modifier 
	 * @author hp
	 *
	 */
	class Modify implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				//db = new SqlJoueur();
				db.DbUpdate(pseudo.getText(), role.getSelectedIndex(), nom.getText(), prenom.getText(), email.getText(), genre.getSelectedItem().toString());
				
				r = db.getSortedScore();
				fill();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	/**
	 *ActionListener for JButton:
	 *supprimer 
	 * @author hp
	 *
	 */
	class Delete implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				//db = new SqlJoueur();
				db.DbDelete(pseudo.getText());
				r = db.getSortedScore();
				fill();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 *ActionListener for JButton:
	 *recherche 
	 * @author hp
	 *
	 */
	class Recherche implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				OptionPanes pane = new OptionPanes("Recherche");
				String pseudo = pane.pseudo;
				
				r.beforeFirst();
				do{
					r.next();
					if(r.getString(1).equals(pseudo)) {
						fill();
					}
				}while(!r.getString(1).equals(pseudo));
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			
			
			
		}
	}
	/**
	 *ActionListener for JButton:
	 *quitter 
	 * @author hp
	 *
	 */
	class QuitterClassement implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			InterfaceJeu.tabbedPane.setSelectedIndex(0);
			InterfaceJeu.tabbedPane.setEnabledAt(2, false);
			try {
				fill();
				reset();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
