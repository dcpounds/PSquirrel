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


import net.miginfocom.swing.MigLayout;

/**
 * @author dave
 *
 * View that contains the robot graphic display
 * There are two separate view contained within: front view and side view
 * 
 */
public class RobotPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private RobotSkeletonFront robotFrontPanel;	//top view of robot
	private RobotSkeletonSide robotSidePanel;	//side view of robot
	private JLabel frontViewLabel;	
	private JLabel sideViewLabel;	

	/**
	 *  Constructs the view composing of the two separate robot graphics
	 */
	public RobotPanel() {

		frontViewLabel = new JLabel("Front View");
		sideViewLabel = new JLabel("Side View");

		robotFrontPanel = new RobotSkeletonFront(0,0,0,Color.BLACK);
		robotSidePanel = new RobotSkeletonSide(0,0,0,Color.BLACK);

		setLayout(new MigLayout("", "[][]", "[]"));

		add(frontViewLabel, "cell 0 0, flowy, alignx center, aligny top");
		add(robotFrontPanel, "cell 0 0, flowy, alignx center");
		add(sideViewLabel, "cell 1 0, flowy, alignx center, aligny top");
		add(robotSidePanel, "cell 1 0, flowy, alignx center");
	}

	
	/**
	 * updates the position of the robot
	 * @param alpha
	 * @param yaw
	 * @param gamma
	 * @param pitch
	 * @param extend
	 */
	public void update(float alpha, float yaw, float gamma, float pitch, float extend) {
		robotFrontPanel.update(alpha, yaw, extend, Color.BLACK);
		robotSidePanel.update(gamma, pitch, extend, Color.BLACK);
	}

	/**
	 * updates which claws are attached to the tree
	 * @param attachedClaws - a list of the claw ID corresponding to the claws that are attached
	 */
	public void updateClaws(List<Integer> attachedClaws){
		robotFrontPanel.updateClaws(attachedClaws);
		robotSidePanel.updateClaws(attachedClaws);
	}
	
	/**
	 * updates the graphics with any obstruction detected by the ultrasonic sensors
	 *   if no obstructions are detected then the distance should be large enough that the obstructions end 
	 *   up off the edge of the graphic 
	 * @param top - distance to top obstruction
	 * @param bot - distance to bottom obstruction
	 */
	public void updateBranchDistances(float top, float bot){
		robotSidePanel.updateBranchDistances(top, bot);
		robotFrontPanel.updateBranchDistances(top, bot);
	}
}
