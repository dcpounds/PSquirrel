package org.projectsquirrel.views.robotViews;

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

import org.projectsquirrel.controllers.UltrasonicPanelController;

import net.miginfocom.swing.MigLayout;

/**
 * A Frame that hold all the textBoxes and robotSkeleton objects to create drawings of the robot
 * 
 *
 */

public class RobotPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private RobotSkeletonFront robotFrontPanel;	//top view of robot
	private RobotSkeletonSide robotSidePanel;	//side view of robot
	private UltrasonicPanel ultrasonicPanel;
	private JLabel frontViewLabel;	
	private JLabel sideViewLabel;	
	private JLabel ultrasonicLabel;

	public RobotPanel() {

		frontViewLabel = new JLabel("Front View");
		sideViewLabel = new JLabel("Side View");
		ultrasonicLabel = new JLabel("Clearance");

		robotFrontPanel = new RobotSkeletonFront(0,0,0,Color.BLACK);
		robotSidePanel = new RobotSkeletonSide(0,0,0,Color.BLACK);
		ultrasonicPanel = UltrasonicPanelController.getUltrasonicPanel();

		setLayout(new MigLayout("", "[][][]", "[]"));

		add(ultrasonicLabel, "cell 0 0, flowy, alignx center, aligny top");
		add(ultrasonicPanel, "cell 0 0, flowy, alignx center");
		add(frontViewLabel, "cell 1 0, flowy, alignx center, aligny top");
		add(robotFrontPanel, "cell 1 0, flowy, alignx center");
		add(sideViewLabel, "cell 2 0, flowy, alignx center, aligny top");
		add(robotSidePanel, "cell 2 0, flowy, alignx center");
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
		this.robotSidePanel.updateClaws(attachedClaws);
	}
}
