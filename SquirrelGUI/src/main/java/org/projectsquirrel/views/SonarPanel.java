/**
 * 
 */
package org.projectsquirrel.views;

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
import org.projectsquirrel.controllers.SonarPanelController;
import org.projectsquirrel.views.robotDisplay.RobotPanel;

/**
 * @author dave
 *
 */
public class SonarPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434788151525363749L;
	
	private DefaultValueDataset dataset1;
    private DefaultValueDataset dataset2;
    private JProgressBar sonarTop;
    private JProgressBar sonarBot;

	public SonarPanel() {

		JLabel label = new JLabel("Clearance");
		Color BROWN = new Color(97, 65, 38);
		
		

		sonarTop = new JProgressBar();
		sonarTop.setSize(new Dimension(14, 40));
		sonarTop.setOrientation(SwingConstants.VERTICAL);
		sonarTop.setValue(0);
		sonarTop.setBorder(new LineBorder(BROWN));
		//sonarTop.setBorderPainted(false);
		sonarTop.setForeground(Color.GREEN);
		sonarTop.setBackground(BROWN);
		
		JPanel sonarRobotPlaceHolder = new JPanel();
		sonarRobotPlaceHolder.setBackground(Color.BLACK);
		sonarRobotPlaceHolder.setSize(new Dimension(14, 14));
		
		sonarBot = new JProgressBar();
		sonarBot.setSize(new Dimension(14, 40));
		sonarBot.setOrientation(SwingConstants.VERTICAL);
		sonarBot.setValue(100);
		sonarBot.setBorder(new LineBorder(BROWN));
		//sonarBot.setBorderPainted(false);
		sonarBot.setForeground(BROWN);
		sonarBot.setBackground(Color.GREEN);;

		setLayout(new MigLayout("", "[]", "[][][][]"));

		add(label, "flowy,cell 0 0,alignx center,aligny top");
		add(sonarTop, "flowy,cell 0 1, alignx center, aligny bottom");
		add(sonarRobotPlaceHolder, "flowy,cell 0 2, alignx center, aligny center");
		add(sonarBot, "flowy,cell 0 3, alignx center, aligny top");
	}
	
	public void update(float top, float bot){
		sonarTop.setValue((int)top);
		sonarBot.setValue(100 - (int)bot);
		repaint();
	}

}
