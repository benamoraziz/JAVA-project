/**
 * 
 */
package applicat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 * Creator of Menu Bar used in the Main frame ,Containes 4 Menues each containes multiple items
 * @author hp
 *
 */
@SuppressWarnings("serial")
public class BarreMenu extends JMenuBar {
	/**
	 * @param parite: JMenu
	 * @param gestion: JMenu,
	 * @param couleur: JMenu
	 * @param aide: JMenu
	 * @param nouvelle: JMenuItem, nouvelle partie..
	 * @param recherche: JMenuItem
	 * @param quitter: JMenuItem
	 * @param joueurs: JMenuItem, consulter joueurs/classement
	 * @param bleu: JMenuItem
	 * @param rouge: JMenuItem
	 * @param lavande: JMenuItem
	 * @param instr: JMenuItem
	 * @param regles: JMenuItem
	 */
	protected JMenu partie, gestion, couleurs, aide;
	protected JMenuItem nouvelle, recherche, quitter, joueurs, bleu, rouge, lavande, instr ,regles;
	public BarreMenu() {
		//PARTIE
		partie = new JMenu("Partie");		
		nouvelle = new JMenuItem("Nouvelle Partie...");
		recherche = new JMenuItem("Rechercher");
		quitter = new JMenuItem("Quitter");
			
		add(partie);
		partie.add(nouvelle);
		partie.add(new JSeparator());
		partie.add(recherche);
		partie.add(new JSeparator());
		partie.add(quitter);
				
		//GESTION
		gestion = new JMenu("Gestion");
		joueurs = new JMenuItem("consulter joueurs/classement");
			
		add(gestion);
		gestion.add(joueurs);
		
		//COULEURS
		
		couleurs = new JMenu("Couleurs");
		bleu = new JMenuItem("Bleu");
		rouge = new JMenuItem("Rouge");
		lavande = new JMenuItem("Lavande");
		
		add(couleurs);
		couleurs.add(bleu);
		couleurs.add(new JSeparator());
		couleurs.add(rouge);
		couleurs.add(new JSeparator());
		couleurs.add(lavande);
		
		//AIDE
		aide = new JMenu("Aide");
		instr = new JMenuItem("Instructions");
		regles = new JMenuItem("Règles du jeu/explication");
			
		add(aide);
		aide.add(instr);
		aide.add(new JSeparator());
		aide.add(regles);
		
		instr.addActionListener(new Instruction());
		regles.addActionListener(new Regles());
	}
	/**
	 * ActionListener that Distplayes the instructions of the game
	 * @author hp
	 *
	 */
	class Instruction implements ActionListener{
		
		/**
		 * @param pane : OptionPanes
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = "Pour accéder au jeu, vous devez être inscrit, vous pouvez le faire par remplir les forum et cliquer sur ajouter, votre pseudo doit être.\n"
					+ "Votre pseudo doit être unique, s'il est déja existant, vous devez choisir un pseudo différent.\n"
					+ "Si vous voulez confirmé votre inscription vous pouvez rechercher votre pseudo en utilisant l'option recherche trouvé dans le menu partie.\n"
					+ "Vous pouvez quitter le jeu en utilisant l'option quitter dans le menu partie ou fermer la fenêtre.\n"
					+ "L'onglet consultation et classement n'est accessible que par les admin.\n"
					+ "Vous pouvez changer le couleur du panel nord en utilisant les options presentés dans le menu couleurs.\n"
					+ "Pour plus d'information contactez Ben Amor Aziz ou Khalsi Skander.";
			JOptionPane.showInternalMessageDialog(null, msg);
		}
		
	}
	/**
	 * ActionListener that Displayes Game Rules
	 * @author hp
	 *
	 */
	class Regles implements ActionListener{
		
		/**
		 * @param pane : OptionPanes
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = "Le programme tire une combinaison au hasard et demande au joueur de la retrouver sur la base de ses réponses successives.\nLe programme indique à chaque coup combien d’éléments sont bien devinés et correctement placés (par #) oubien devinés mais mal placés (par o).\nLe joueur dispose d’un nombre maximum de tentatives (10). Une combinaison est représentée par une séquence de n (4) symboles tirés parmi m (6).\nPour chaque proposition du joueur, un message le nombre d’éléments correctement placés et mal placés.";
			JOptionPane.showInternalMessageDialog(null, msg);
		}
		
	}
}
