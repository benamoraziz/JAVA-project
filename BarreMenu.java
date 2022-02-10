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
		regles = new JMenuItem("R�gles du jeu/explication");
			
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
			String msg = "Pour acc�der au jeu, vous devez �tre inscrit, vous pouvez le faire par remplir les forum et cliquer sur ajouter, votre pseudo doit �tre.\n"
					+ "Votre pseudo doit �tre unique, s'il est d�ja existant, vous devez choisir un pseudo diff�rent.\n"
					+ "Si vous voulez confirm� votre inscription vous pouvez rechercher votre pseudo en utilisant l'option recherche trouv� dans le menu partie.\n"
					+ "Vous pouvez quitter le jeu en utilisant l'option quitter dans le menu partie ou fermer la fen�tre.\n"
					+ "L'onglet consultation et classement n'est accessible que par les admin.\n"
					+ "Vous pouvez changer le couleur du panel nord en utilisant les options present�s dans le menu couleurs.\n"
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
			String msg = "Le programme tire une combinaison au hasard et demande au joueur de la retrouver sur la base de ses r�ponses successives.\nLe programme indique � chaque coup combien d��l�ments sont bien devin�s et correctement plac�s (par #) oubien devin�s mais mal plac�s (par o).\nLe joueur dispose d�un nombre maximum de tentatives (10). Une combinaison est repr�sent�e par une s�quence de n (4) symboles tir�s parmi m (6).\nPour chaque proposition du joueur, un message le nombre d��l�ments correctement plac�s et mal plac�s.";
			JOptionPane.showInternalMessageDialog(null, msg);
		}
		
	}
}
