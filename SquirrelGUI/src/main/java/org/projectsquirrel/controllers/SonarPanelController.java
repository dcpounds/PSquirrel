/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.SonarPanel;
import org.projectsquirrel.views.robotDisplay.RobotPanel;

/**
 * @author dave
 *
 */
public class SonarPanelController {
	
	private static SonarPanelController instance = new SonarPanelController();
	private static SonarPanel sonarPanel = new SonarPanel();
	
	private SonarPanelController(){
		//RobotPanel;
	}
	
	public static SonarPanelController getInstace(){
		return instance;
	}
	
	public static SonarPanel getSonarPanel(){
		return sonarPanel;
	}
	
	public static void updateSonar(float top, float bot){
		sonarPanel.update(top, bot);
	}
	
	
}
