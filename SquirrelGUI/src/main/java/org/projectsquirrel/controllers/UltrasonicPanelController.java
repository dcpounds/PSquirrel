/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.robotViews.RobotPanel;
import org.projectsquirrel.views.robotViews.UltrasonicPanel;

/**
 * @author dave
 *
 */
public class UltrasonicPanelController {
	
	private static UltrasonicPanelController instance = new UltrasonicPanelController();
	private static UltrasonicPanel ultrasonicPanel = new UltrasonicPanel();
	
	private UltrasonicPanelController(){
		//RobotPanel;
	}
	
	public static UltrasonicPanelController getInstace(){
		return instance;
	}
	
	public static UltrasonicPanel getUltrasonicPanel(){
		return ultrasonicPanel;
	}
	
	public static void updateSonar(float top, float bot){
		ultrasonicPanel.update(top, bot);
	}
	
	
}
