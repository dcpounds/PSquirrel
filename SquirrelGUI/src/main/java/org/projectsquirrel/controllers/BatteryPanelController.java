/**
 * 
 */
package org.projectsquirrel.controllers;

import java.util.List;

import org.projectsquirrel.views.BatteryPanel;
import org.projectsquirrel.views.SonarPanel;
import org.projectsquirrel.views.robotDisplay.RobotPanel;

/**
 * @author dave
 *
 */
public class BatteryPanelController {
	
	private static BatteryPanelController instance = new BatteryPanelController();
	private static BatteryPanel batteryPanel = new BatteryPanel();
	
	private BatteryPanelController(){
	}
	
	public static BatteryPanelController getInstace(){
		return instance;
	}
	
	public static BatteryPanel getBatteryPanel(){
		return batteryPanel;
	}
	
	public static void updateBattery(float value){
		batteryPanel.update(value);
	}
	
	
}