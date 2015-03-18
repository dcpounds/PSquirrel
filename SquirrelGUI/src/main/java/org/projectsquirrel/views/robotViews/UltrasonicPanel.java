/**
 * 
 */
package org.projectsquirrel.views.robotViews;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import org.jfree.data.general.DefaultValueDataset;
import org.projectsquirrel.controllers.UltrasonicPanelController;

/**
 * @author dave
 *
 */
public class UltrasonicPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434788151525363749L;
	
	private DefaultValueDataset dataset1;
    private DefaultValueDataset dataset2;
    private JProgressBar ultrasonicTop;
    private JProgressBar ultrasonicBot;

	public UltrasonicPanel() {

		Color BROWN = new Color(97, 65, 38);
		
		

		ultrasonicTop = new JProgressBar();
		ultrasonicTop.setSize(new Dimension(14, 40));
		ultrasonicTop.setOrientation(SwingConstants.VERTICAL);
		ultrasonicTop.setValue(0);
		ultrasonicTop.setBorder(new LineBorder(BROWN));
		//sonarTop.setBorderPainted(false);
		ultrasonicTop.setForeground(Color.GREEN);
		ultrasonicTop.setBackground(BROWN);
		
		JPanel ultrasonicRobotPlaceHolder = new JPanel();
		ultrasonicRobotPlaceHolder.setBackground(Color.BLACK);
		ultrasonicRobotPlaceHolder.setSize(new Dimension(14, 14));
		
		ultrasonicBot = new JProgressBar();
		ultrasonicBot.setSize(new Dimension(14, 40));
		ultrasonicBot.setOrientation(SwingConstants.VERTICAL);
		ultrasonicBot.setValue(100);
		ultrasonicBot.setBorder(new LineBorder(BROWN));
		//sonarBot.setBorderPainted(false);
		ultrasonicBot.setForeground(BROWN);
		ultrasonicBot.setBackground(Color.GREEN);;

		setLayout(new MigLayout("", "[]", "[][][]"));

		add(ultrasonicTop, "flowy,cell 0 0, alignx center, aligny bottom");
		add(ultrasonicRobotPlaceHolder, "flowy,cell 0 1, alignx center, aligny center");
		add(ultrasonicBot, "flowy,cell 0 2, alignx center, aligny top");
	}
	
	public void update(float top, float bot){
		ultrasonicTop.setValue((int)top);
		ultrasonicBot.setValue(100 - (int)bot);
		repaint();
	}

}
