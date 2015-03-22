/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.robotViews.RobotPanel;

/**
 * @author dave
 *
 * Singleton for updating the robot graphic
 *
 */
public class RobotPanelController {
	
	private static RobotPanelController instance = new RobotPanelController();
	private static RobotPanel robotPanel = new RobotPanel();
	
	/**
	 * constructor is private because class is a singleton
	 */
	private RobotPanelController(){
	}
	
	/**
	 * get the one instance of the controller
	 * @return
	 */
	public static RobotPanelController getInstace(){
		return instance;
	}
	

	/**
	 * get the robot graphic panel view associated with this controller
	 * @return
	 */
	public static RobotPanel getRobotPanel(){
		return robotPanel;
	}
	
	/**
	 * updates the robot graphic with the given measures
	 * @param alpha - angle of robot to ground with respect to front of tree
	 * @param yaw - bend of robot with respect to front of tree
	 * @param gamma - angle of robot to ground with respect to side of tree
	 * @param pitch - bend of robot with respect to front of tree
	 * @param extend - extension of robot
	 */
	public static void updateRobotPosition(float alpha, float yaw, float gamma, float pitch, float extend){
		robotPanel.update(alpha, yaw, gamma, pitch, extend);
	}
	
	/**
	 * updates the robot graphic to indicate which claws are attached
	 * @param attachedClaws - ids of claw sensors that are attached
	 */
	public static void updateRobotClaws(List<Integer> attachedClaws){
		robotPanel.updateClaws(attachedClaws);
	}
	
	/**
	 * updates the robot graphic with any obstructions detects by the ultrasonic sensors
	 * @param top - distance to top obstruction
	 * @param bot - distance to bottom obstruction
	 */
	public static void updateBranchDistances(float top, float bot){
		robotPanel.updateBranchDistances(top, bot);
	}
	
}
