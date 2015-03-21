/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.miscViews.BatteryPanel;
import org.projectsquirrel.views.miscViews.ConnectionPanel;
import org.projectsquirrel.views.robotViews.RobotPanel;

/**
 * @author dave
 * 
 * Singleton for updating the connection status
 *
 */
public class ConnectionPanelController {
	
	private static ConnectionPanelController instance = new ConnectionPanelController();
	private static ConnectionPanel connectionPanel = new ConnectionPanel();
	
	/**
	 * constructor is private because class is a singleton
	 */
	private ConnectionPanelController(){
	}
	
	/**
	 * get the one instance of the controller
	 * @return
	 */
	public static ConnectionPanelController getInstace(){
		return instance;
	}
	

	/**
	 * get the connection panel view associated with this controller
	 * @return
	 */
	public static ConnectionPanel getConnectionPanel(){
		return connectionPanel;
	}
	
	/**
	 * updates the connection with the given status
	 * @param isConnected - given status; true is connected else false
	 */
	public static void updateConnection(boolean isConnected){
		connectionPanel.update(isConnected);
	}
	
	
}
