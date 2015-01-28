/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.BatteryPanel;
import org.projectsquirrel.views.ConnectionPanel;
import org.projectsquirrel.views.SonarPanel;
import org.projectsquirrel.views.robotDisplay.RobotPanel;

/**
 * @author dave
 *
 */
public class ConnectionPanelController {
	
	private static ConnectionPanelController instance = new ConnectionPanelController();
	private static ConnectionPanel connectionPanel = new ConnectionPanel();
	
	private ConnectionPanelController(){
	}
	
	public static ConnectionPanelController getInstace(){
		return instance;
	}
	
	public static ConnectionPanel getConnectionPanel(){
		return connectionPanel;
	}
	
	public static void updateConnection(boolean isPressed){
		connectionPanel.update(isPressed);
	}
	
	
}
