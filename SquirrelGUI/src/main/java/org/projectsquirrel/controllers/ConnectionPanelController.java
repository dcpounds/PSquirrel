/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.controlViews.BatteryPanel;
import org.projectsquirrel.views.controlViews.ConnectionPanel;
import org.projectsquirrel.views.robotViews.RobotPanel;
import org.projectsquirrel.views.robotViews.UltrasonicPanel;

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
