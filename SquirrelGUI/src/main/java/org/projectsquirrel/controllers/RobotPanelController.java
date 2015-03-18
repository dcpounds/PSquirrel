/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.robotViews.RobotPanel;

/**
 * @author dave
 *
 */
public class RobotPanelController {
	
	private static RobotPanelController instance = new RobotPanelController();
	private static RobotPanel robotPanel = new RobotPanel();
	
	private RobotPanelController(){
		//RobotPanel;
	}
	
	public static RobotPanelController getInstace(){
		return instance;
	}
	
	public static RobotPanel getRobotPanel(){
		return robotPanel;
	}
	
	public static void updateRobotPosition(float alpha, float yaw, float gamma, float pitch, float extend){
		robotPanel.update(alpha, yaw, gamma, pitch, extend);
	}
	
	public static void updateRobotClaws(List<Integer> attachedClaws){
		robotPanel.updateClaws(attachedClaws);
	}
	
	public static void updateBranchDistances(float top, float bot){
		robotPanel.updateBranchDistances(top, bot);
	}
	
}
