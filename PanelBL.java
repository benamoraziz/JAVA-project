/**
 * 
 */
package applicat;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 * Creator of Panels with certain structures and Layouts
 * @author hp
 */
@SuppressWarnings("serial")
public class PanelBL extends JPanel {

	/**
	 * @param Top: top panel
	 * @param Center: center panel
	 * @param Right: Right panel
	 * @param Left: Left panel
	 * @param Bottom: Bottom panel
	 * @param CenterRight: Center-Right panel when center is Split
	 * @param Center-Left: Center-Left panel when center is Split
	 */
	protected JPanel Top, Center, Right, Left, Bottom, CenterRight, CenterLeft;
	/**
	 * Default Constructor
	 */
	public PanelBL() {
		super(new BorderLayout());
		
	}
	/**
	 * adds label to Top
	 * @param label JLabel
	 */
	public void setTopLabel(JLabel label) {
		Top = new JPanel();
		
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		Top.add(label);
		add(Top, BorderLayout.NORTH);
	}
	/**
	 * Adds image to Top
	 * @param image ImageIcon
	 */
	public void setTopIcon(ImageIcon image) {
		JLabel label = new JLabel();
		label.setIcon(image);
		setTopLabel(label);
	}
	/**
	 * Sets top layout to GridBagLayout
	 */
	public void setTopGBL() {
		Top = new JPanel();
		Top.setLayout(new GridBagLayout());
		add(Top, BorderLayout.NORTH);
	}
	/**
	 * Adds c to Top in position (x,y)
	 * @param c component
	 * @param x gridx
	 * @param y gridy
	 */
	public void addToTop(Component c,int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = x;
		gbc.gridy = y;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		Top.add(c, gbc);
	}
	/**
	 * Sets center layout to GBL
	 */
	public void setCenterGBL() {
		Center = new JPanel();
		Center.setLayout(new GridBagLayout());
		add(Center, BorderLayout.CENTER);
	}
	/**
	 * Splits Center in two and makes centerRight and centerLeft
	 */
	public void setCenterSplit() {
		Center = new JPanel();
		add(Center, BorderLayout.CENTER);
		Center.setLayout(new BorderLayout());
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setOneTouchExpandable(false);
		
		Center.add(splitPane);
		
		CenterRight = new JPanel();
		CenterRight.setLayout(new FlowLayout());
		CenterRight.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		CenterLeft = new JPanel();
		CenterLeft.setLayout(new GridLayout(10,6));
		
		splitPane.setRightComponent(CenterRight);
		splitPane.setLeftComponent(CenterLeft);
		
	}
	
	//Add to center with constraints
	/**
	 * Adds c to center at position (x,y)
	 * @param c Component
	 * @param x gridx
	 * @param y gridy
	 */
	public void gbcConstraints(Component c,int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(2, 2, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		Center.add(c, gbc);
	}
	/**
	 * sets bottom layout to GBL
	 */
	public void setBottom() {
		Bottom = new JPanel();
		Bottom.setLayout(new GridBagLayout());
		add(Bottom, BorderLayout.SOUTH);
	}
	/**
	 * Creates the Right panel
	 */
	public void setRightGBL() {
		Right = new JPanel();
		Right.setLayout(new GridBagLayout());
		add(Right, BorderLayout.EAST);
	}
	
	/**
	 * Adds c to Right in position (x,y)
	 * @param c Component
	 * @param x gridx
	 * @param y gridy
	 */
	public void addToRight(Component c,int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		Right.add(c, gbc);
	}

}
