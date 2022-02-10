/**
 * 
 */
package applicat;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 * Game Window creator (Principle JFrame creator)
 * @author hp
 *
 */ 
@SuppressWarnings("serial")
public class InterfaceJeu extends JFrame {
	
	/**
	 * Instances Field
	 * @param barreMenus : The menu Bar
	 * @param tabbedPane : the container of the tabs (inscription, jouer, Classement)
	 * @param first : the inscription Tab
	 * @param second : The "Jouer" Tab
	 * @param third : the Classement tab
	 */
	private BarreMenu barreMenus;
	protected static JTabbedPane tabbedPane;//Static might be the ultimate solution 
	protected InscriptionTab first;
	protected JouerTab second;
	protected ClassementTab third;
	
	/**
	 * InterfaceJeu Default Constructor
	 */
	public InterfaceJeu() {
		super();
		initFrame();
		barreMenus = new BarreMenu();
		setJMenuBar(barreMenus);
		TabbedPane();
		
		ActivateBtns();
		
	}
	/**
	 * Adder of ActionListeners to created buttons 
	 */
	private void ActivateBtns() {
		barreMenus.bleu.addActionListener(new ColorSetBlue());
		barreMenus.rouge.addActionListener(new ColorSetRed());
		barreMenus.lavande.addActionListener(new ColorSetLavande());
		
		barreMenus.nouvelle.addActionListener(new Nouvelle());
		barreMenus.recherche.addActionListener(new Recherche());
		barreMenus.quitter.addActionListener(new Quitter());
		barreMenus.joueurs.addActionListener(new Consulter());
		second.quitter.addActionListener(new QuitterJeu());
	}
	
	/**
	 * Filler of the tabbedPane
	 */
	private void TabbedPane() {
		tabbedPane = new JTabbedPane();
		add(tabbedPane);
		//Declare 3 Tabs
		first = new InscriptionTab();
		second = new JouerTab();
		third = new ClassementTab();
		
		//Add 3 Tabs with Titles
		tabbedPane.add("Inscription", first);
		tabbedPane.add("Jouer", second);
		tabbedPane.add("Consultation et classement", third);
		
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		
		
	}
	
	/**
	 * Frame initializer, Specifies title, icon, size and visility
	 */
	public void initFrame() {
		setTitle("MasterMind");
		setSize(1200,500);
		setLocationRelativeTo(null);
		//ImageIcon ImageIcon = new ImageIcon("./img/Mastermind_logo.png");
		ImageIcon ImageIcon = new ImageIcon(getClass().getClassLoader().getResource("Mastermind_logo.png"));
		Image Image = ImageIcon.getImage();
		setIconImage(Image);
		setVisible(true);
		/**
		 * Override of the window's (x) exit Listener,
		 */
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                System.exit(0);
			}
		});
	}
	/**
	 *ActionListener that changes the color of the top of a PanelBL to 
	 *Blue 
	 * @author hp
	 * 
	 */
	class ColorSetBlue implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			first.Top.setBackground(Color.BLUE);
			third.Top.setBackground(Color.BLUE);
		}
		
	}
	
	/**
	 *ActionListener that changes the color of the top of a PanelBL to 
	 *Red 
	 * @author hp
	 *
	 */
	class ColorSetRed implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			first.Top.setBackground(Color.RED);
			third.Top.setBackground(Color.RED);
		}
	}
	/**
	 *ActionListener that changes the color of the top of a PanelBL to 
	 *Magenta 
	 * @author hp
	 *
	 */
	class ColorSetLavande implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			first.Top.setBackground(Color.magenta);
			third.Top.setBackground(Color.magenta);
			
		}
	}
	/**
	 *ActionListener that Controls the visibility of the
	 * Jouer Tab 
	 * @author hp
	 *
	 *
	 */
	class Nouvelle implements ActionListener{
		
		/**
		 * @param pane : OptionPanes
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				OptionPanes pane = new OptionPanes(tabbedPane,1,"Voulez vous jouer");
				
				second.setNomUser(pane.pseudo);
				add(pane);
				third.db = new SqlJoueur();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	/**
	 *ActionListener that Controls the visibility of the
	 * Classement tab 
	 * @author hp
	 *
	 */
	class Consulter implements ActionListener{
		
		/**
		 * @param pane : OptionPane
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			OptionPanes pane;
			try {
				
				pane = new OptionPanes(tabbedPane,2,"Voulez vous consulter les classement");
				add(pane);
				third.db = new SqlJoueur();
				third.r = third.db.getSortedScore();
				
				
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	/**
	 *	ActionListener that gives information about the
	 *	Existance of a player 
	 * @author hp
	 *
	 */
	class Recherche implements ActionListener{
		
		/**
		 * @param pane : OptionPanes
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			OptionPanes pane;
			try {
				pane = new OptionPanes("Recherche");
				add(pane);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	/**
	 *ActionListener that Quits (Disables) and Resets the Jouer Tab 
	 * @author hp
	 *
	 */
	class QuitterJeu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			tabbedPane.setSelectedIndex(0);
			tabbedPane.setEnabledAt(1, false);
			second.reset();
			second = new JouerTab();
		}
		
	}
	
	/**
	 *ActionListener that Quits the game's main window (the JFrame)
	 *After Confirmation 
	 * @author hp
	 *
	 */
	class Quitter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int dialogButton = JOptionPane.showConfirmDialog (null, "Voulez vous Quitter?","WARNING", JOptionPane.YES_NO_OPTION);
            if(dialogButton == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            
		}
		
	}
	
	
}