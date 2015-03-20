/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.controlViews.BatteryPanel;
import org.projectsquirrel.views.robotViews.RobotPanel;

/**
 * @author dave
 *
 *Singleton for updating the battery information
 *
 */
/**
 * @author dave
 *
 */
/**
 * @author dave
 *
 */
public class BatteryPanelController {
	
	private static BatteryPanelController instance = new BatteryPanelController();
	private static BatteryPanel batteryPanel = new BatteryPanel();
	
	/**
	 * constructor is private because class is a singleton
	 */
	private BatteryPanelController(){
	}
	
	/**
	 * get the one instance of the controller
	 * @return
	 */
	public static BatteryPanelController getInstace(){
		return instance;
	}
	
	/**
	 * get the battery panel view associated with this controller
	 * @return
	 */
	public static BatteryPanel getBatteryPanel(){
		return batteryPanel;
	}
	
	/**
	 * updates the battery remaining percentage
	 * @param value - battery percentage remaining
	 */
	public static void updateBattery(float value){
		batteryPanel.update(value);
	}
	
	
}
