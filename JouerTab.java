/**
 * 
 */
package applicat;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The Creator of the 'Jouer' Tab
 * @author hp
 */
@SuppressWarnings("serial")
public class JouerTab extends PanelBL{
	/**
	 * Instaces Field
	 * @param construct : final string, displayed game rules
	 * @param compt : statc int, keeps count of the number of tries
	 * @param quitter: JButton, quits this tab, sets it disabled
	 * @param welcome: JLabel, welcome message, displays the pseudo of the player
	 * @param essai(i)_(j): JLabel, i [lines] in {1,2,..,10}, j [columns] in {1,2,..,5}
	 * 			for i ==1, label contains info about the number of tries made,
	 * 			else, it contains the input number the player chose at that try,
	 * @param valid(i) : JLabel, contains informative message about the try, if input is right or not
	 * @param essaiX: JLabel[][], contains lists of labels for each i
	 * @param n1: JTextField, first input
	 * @param n2: JTextField, second input
	 * @param n3: JTextField, third input
	 * @param n4: JTextField, fourth input
	 * @param s: int[], list of four random numbers between 1 & 6
	 * @param valider: JButton, it affects inputs to the respective labels (i=compt), creates the valid(i) message and determins if the player won or not , loosing if compt>10, and checks if the player wants a rematch or quit.
	 * @param pseudo: String, player's pseudo
	 */
	final String construct = "Pouvez-vous trouver ma combinaison de 4 symboles [chiffres entre 1 et 6 avec repetitions possibles] en moins de 10 coups?\nEntrez les symboles des propositions et terminer en cliquant sur valider\n(# un bien placé, o un mal placé)\nVous pouvez quitter la partie en cliquant sur le bouton Quitter";
	protected static int compt = 0;
	protected JLabel welcome;
	
	protected JButton quitter;
	
	private JLabel  essai1_1 = new JLabel(), essai1_2 = new JLabel(), essai1_3 = new JLabel(), essai1_4 = new JLabel(), valid1 = new JLabel(),
	essai2_1 = new JLabel(), essai2_2 = new JLabel(), essai2_3 = new JLabel(), essai2_4 = new JLabel(), valid2 = new JLabel(),
	essai3_1 = new JLabel(), essai3_2 = new JLabel(), essai3_3 = new JLabel(), essai3_4 = new JLabel(), valid3 = new JLabel(),
	essai4_1 = new JLabel(), essai4_2 = new JLabel(), essai4_3 = new JLabel(), essai4_4 = new JLabel(), valid4 = new JLabel(),
	essai5_1 = new JLabel(), essai5_2 = new JLabel(), essai5_3 = new JLabel(), essai5_4 = new JLabel(), valid5 = new JLabel(),
	essai6_1 = new JLabel(), essai6_2 = new JLabel(), essai6_3 = new JLabel(), essai6_4 = new JLabel(), valid6 = new JLabel(),
	essai7_1 = new JLabel(), essai7_2 = new JLabel(), essai7_3 = new JLabel(), essai7_4 = new JLabel(), valid7 = new JLabel(),
	essai8_1 = new JLabel(), essai8_2 = new JLabel(), essai8_3 = new JLabel(), essai8_4 = new JLabel(), valid8 = new JLabel(),
	essai9_1 = new JLabel(), essai9_2 = new JLabel(), essai9_3 = new JLabel(), essai9_4 = new JLabel(), valid9 = new JLabel(),
	essai10_1 = new JLabel(), essai10_2 = new JLabel(), essai10_3 = new JLabel(), essai10_4 = new JLabel(), valid10 = new JLabel();
	
	private JLabel[] essai1_List = {essai1_1, essai1_2, essai1_3, essai1_4, valid1},
			essai2_List = {essai2_1, essai2_2, essai2_3, essai2_4, valid2},
			essai3_List = {essai3_1, essai3_2, essai3_3, essai3_4, valid3},
			essai4_List = {essai4_1, essai4_2, essai4_3, essai4_4, valid4},
			essai5_List = {essai5_1, essai5_2, essai5_3, essai5_4, valid5},
			essai6_List = {essai6_1, essai6_2, essai6_3, essai6_4, valid6},
			essai7_List = {essai7_1, essai7_2, essai7_3, essai7_4, valid7},
			essai8_List = {essai8_1, essai8_2, essai8_3, essai8_4, valid8},
			essai9_List = {essai9_1, essai9_2, essai9_3, essai9_4, valid9},
			essai10_List = {essai10_1, essai10_2, essai10_3, essai10_4, valid10};
	private JLabel[][] essaiX = { essai1_List,essai2_List,essai3_List,essai4_List,essai5_List,essai6_List,essai7_List,essai8_List,essai9_List,essai10_List};
	
	
	private JTextField n1, n2, n3, n4;
	
	private int s1= (int) (1+ Math.round(Math.random()*5)), s2= (int) (1+ Math.round(Math.random()*5)), s3=(int) (1+ Math.round(Math.random()*5)),s4=(int) (1+ Math.round(Math.random()*5));
	
	private int[] s = {s1, s2, s3, s4};
	
	JButton valider;
	
	private JLabel indicator3 = new JLabel("(Reste 10 coups)"), 
			indicator1 = new JLabel(", donnez une nouvelle proposition");
	
	protected String pseudo;
	
	/**
	 * Default Constructor
	 */
	public JouerTab() {
		
		//TOP
		setTopGBL();
		JLabel titre = new JLabel("Jeu MasterMind");
		titre.setFont(new Font("Lucida Handwriting", Font.BOLD, 28));
		
		
		welcome = new JLabel("Bienvenu ");
		welcome.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JTextArea explanation = new JTextArea();
		explanation.setText(construct);
		explanation.setBackground(Color.LIGHT_GRAY);
		explanation.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
		explanation.setEditable(false);
		
		quitter = new JButton("Quitter");
		
		addToTop(titre, 0, 0);
		addToTop(welcome, 0, 1);
		addToTop(explanation, 0, 2);
		addToTop(quitter, 0, 3);
		
		//MID
		
		setCenterSplit();
		
		//MID-RIGHT
		
		n1= new JTextField(); n1.setColumns(1);
		n2= new JTextField(); n2.setColumns(1);
		n3= new JTextField(); n3.setColumns(1);
		n4= new JTextField(); n4.setColumns(1);
		
		CenterRight.add(n1); CenterRight.add(n2); CenterRight.add(n3); CenterRight.add(n4);
		
		indicator1.setFont(new Font("Tahoma", Font.BOLD, 11));
		indicator1.setForeground(Color.blue);
		
		valider = new JButton("Valider");
		JLabel indicator2 = new JLabel("Nombre d'essais restants ");
		
		indicator3.setForeground(Color.blue);
		
		CenterRight.add(indicator1); CenterRight.add(valider); CenterRight.add(indicator2); CenterRight.add(indicator3);
		
		//MID-LEFT
		
		CenterLeft.setBackground(Color.decode("#00CCCC"));
		
		JLabel essai1 = new JLabel("1er essai"); 
		JLabel essai2 = new JLabel("2eme essai");
		JLabel essai3 = new JLabel("3eme essai");
		JLabel essai4 = new JLabel("4eme essai");
		JLabel essai5 = new JLabel("5eme essai");
		JLabel essai6 = new JLabel("6eme essai");
		JLabel essai7 = new JLabel("7eme essai");
		JLabel essai8 = new JLabel("8eme essai");
		JLabel essai9 = new JLabel("9eme essai");
		JLabel essai10 = new JLabel("dernier essai");
		
		CenterLeft.add(essai10);CenterLeft.add(essai10_1);CenterLeft.add(essai10_2);CenterLeft.add(essai10_3);CenterLeft.add(essai10_4);CenterLeft.add(valid10);
		CenterLeft.add(essai9);CenterLeft.add(essai9_1);CenterLeft.add(essai9_2);CenterLeft.add(essai9_3);CenterLeft.add(essai9_4);CenterLeft.add(valid9);
		CenterLeft.add(essai8);CenterLeft.add(essai8_1);CenterLeft.add(essai8_2);CenterLeft.add(essai8_3);CenterLeft.add(essai8_4);CenterLeft.add(valid8);
		CenterLeft.add(essai7);CenterLeft.add(essai7_1);CenterLeft.add(essai7_2);CenterLeft.add(essai7_3);CenterLeft.add(essai7_4);CenterLeft.add(valid7);
		CenterLeft.add(essai6);CenterLeft.add(essai6_1);CenterLeft.add(essai6_2);CenterLeft.add(essai6_3);CenterLeft.add(essai6_4);CenterLeft.add(valid6);
		CenterLeft.add(essai5);CenterLeft.add(essai5_1);CenterLeft.add(essai5_2);CenterLeft.add(essai5_3);CenterLeft.add(essai5_4);CenterLeft.add(valid5);
		CenterLeft.add(essai4);CenterLeft.add(essai4_1);CenterLeft.add(essai4_2);CenterLeft.add(essai4_3);CenterLeft.add(essai4_4);CenterLeft.add(valid4);
		CenterLeft.add(essai3);CenterLeft.add(essai3_1);CenterLeft.add(essai3_2);CenterLeft.add(essai3_3);CenterLeft.add(essai3_4);CenterLeft.add(valid3);
		CenterLeft.add(essai2);CenterLeft.add(essai2_1);CenterLeft.add(essai2_2);CenterLeft.add(essai2_3);CenterLeft.add(essai2_4);CenterLeft.add(valid2);
		CenterLeft.add(essai1);CenterLeft.add(essai1_1);CenterLeft.add(essai1_2);CenterLeft.add(essai1_3);CenterLeft.add(essai1_4);CenterLeft.add(valid1);
		
		valider.addActionListener(new Validation());
		
	}
	/**
	 * Adds the player's pseudo to the WELCOME message
	 * @param name String
	 */
	public void setNomUser(String name) {
		welcome.setText("Bienvenu Mme/M. "+ name);
		pseudo = name;
	}
	/**
	 * resets the Tab to it's initial State & Remake the random numbers
	 */
	public void reset() {
		
		//labels
		essai1_1.setText(""); essai1_2.setText(""); essai1_3.setText(""); essai1_4.setText(""); valid1.setText("");
		essai2_1.setText(""); essai2_2.setText(""); essai2_3.setText(""); essai2_4.setText(""); valid2.setText("");
		essai3_1.setText(""); essai3_2.setText(""); essai3_3.setText(""); essai3_4.setText(""); valid3.setText("");
		essai4_1.setText(""); essai4_2.setText(""); essai4_3.setText(""); essai4_4.setText(""); valid4.setText("");
		essai5_1.setText(""); essai5_2.setText(""); essai5_3.setText(""); essai5_4.setText(""); valid5.setText("");
		essai6_1.setText(""); essai6_2.setText(""); essai6_3.setText(""); essai6_4.setText(""); valid6.setText("");
		essai7_1.setText(""); essai7_2.setText(""); essai7_3.setText(""); essai7_4.setText(""); valid7.setText("");
		essai8_1.setText(""); essai8_2.setText(""); essai8_3.setText(""); essai8_4.setText(""); valid8.setText("");
		essai9_1.setText(""); essai9_2.setText(""); essai9_3.setText(""); essai9_4.setText(""); valid9.setText("");
		essai10_1.setText(""); essai10_2.setText(""); essai10_3.setText(""); essai10_4.setText(""); valid10.setText("");
		
		compt = 0;
		
		indicator1.setText(", donnez une nouvelle proposition");
		indicator3.setText("(Reste 10 coups)");
		
		n1.setText(""); n2.setText(""); n3.setText(""); n4.setText("");
		
		s1= (int) (1+ Math.round(Math.random()*5));
		s2= (int) (1+ Math.round(Math.random()*5));
		s3=(int) (1+ Math.round(Math.random()*5));
		s4=(int) (1+ Math.round(Math.random()*5));
		s[0]=s1; s[1]=s2; s[2]=s3; s[3]= s4;
		
	}
	/**
	 * ActionListener for valider
	 * Validates the inputs and sets the score
	 * @author hp
	 *
	 */
	class Validation implements ActionListener{
		/**
		 * Checks if input is true or not
		 * Update database with obtained score if it's inferiour to the set score
		 */
		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			
			String[] inputs = {n1.getText(), n2.getText(), n3.getText(), n4.getText(), "what"};
			
			String[] sol = {"", "", "", ""};
			String res = "";
			
			String[] strS = {String.valueOf(s[0]),String.valueOf(s[1]),String.valueOf(s[2]),String.valueOf(s[3])};
			
			for( int i=0; i<4; i++) {
				String c = String.valueOf(s[i]);
				if(inputs[i].equals(c)) {sol[i] = "#";}
				else if(inputs[i].equals(strS[0])|| inputs[i].equals(strS[1])||inputs[i].equals(strS[2])||inputs[i].equals(strS[3])){
					sol[i] = "o";
				}
				res = res + sol[i];
			}
			
			if(res.equals("####")) {
				JOptionPane.showInternalMessageDialog(null, "Bravo "+ pseudo +", vous avez gagné au bout de "+ (compt+1)+ " essais");
				try {
					SqlJoueur j = new SqlJoueur();
					int co1 = compt+1;
					ResultSet player = j.recherche(pseudo);
					player.first();
					if(co1 < player.getInt(8)) {
						j.DbUpdate_score(pseudo, co1);
					}
					j.stop();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				failiure();
			}
			
			//Joue
			else {
				inputs[4] = res;
				for( int i=0; i<5; i++) {
					essaiX[compt][i].setText(inputs[i].toString());
					//System.out.println(inputs[i]);
				}
				compt++;
				indicator1.setText(res + ", donnez une nouvelle proposition");
				indicator3.setText("(Reste "+ (10-compt) + " coups)");
				n1.setText(""); n2.setText(""); n3.setText(""); n4.setText("");
				System.out.print(s1);System.out.print(s2);System.out.print(s3);System.out.println(s4);
				
				//Vous avez perdu
				if(compt ==10){
					JOptionPane.showInternalMessageDialog(null, "Désolé vous avez perdu! la combinaison à découvrir est "+s[0]+" "+s[1]+" "+s[2]+" "+s[3]);
					failiure();
				}
			}
			
		}
		/**
		 * Actions taken when player Looses
		 */
		public void failiure() {
			
			int dialogButton = JOptionPane.showConfirmDialog (null, "Voulez vous rejouer?","REJOUER", JOptionPane.YES_NO_OPTION);
            if(dialogButton == JOptionPane.YES_OPTION) {
            	reset();
            }
            else if(dialogButton == JOptionPane.NO_OPTION) {
            	reset();
            	InterfaceJeu.tabbedPane.setSelectedIndex(0);
    			InterfaceJeu.tabbedPane.setEnabledAt(1, false);
            }
		}
	}
	
}
