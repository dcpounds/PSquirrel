package org.projectsquirrel.views.robotDisplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

/**
 * A Frame that hold all the textBoxes and robotSkeleton objects to create drawings of the robot
 * 
 *
 */

public class RobotPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	RobotSkeletonFront robotFrontPanel;	//top view of robot
	RobotSkeletonSide robotSidePanel;	//side view of robot
	JLabel frontViewLabel;	
	JLabel sideViewLabel;	

	public RobotPanel() {

		frontViewLabel = new JLabel("Front View");
		sideViewLabel = new JLabel("Side View");

		robotFrontPanel = new RobotSkeletonFront(0,0,0,Color.BLACK);
		robotSidePanel = new RobotSkeletonSide(0,0,0,Color.BLACK);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(0, 0, 0));

		setLayout(new MigLayout("", "[grow]0[grow]", "[][]"));

		add(separator, "cell 1 0 1 1, alignx center");
		add(frontViewLabel, "cell 0 0, alignx center, aligny top");
		add(robotFrontPanel, "cell 0 1, alignx center");
		add(sideViewLabel, "cell 2 0, alignx center, aligny top");
		add(robotSidePanel, "cell 2 1, alignx center");
	}

	/**
	 * updates the drawing of the robot by destroying the current one a drawing a new one with the new values
	 */
	public void update(float alpha, float yaw, float gamma, float pitch, float extend) {
		robotFrontPanel.update(alpha, yaw, extend, Color.BLACK);
		robotSidePanel.update(gamma, pitch, extend, Color.BLACK);
	}

	public void updateClaws(List<Integer> attachedClaws){
		this.robotFrontPanel.updateClaws(attachedClaws);
	}
}
