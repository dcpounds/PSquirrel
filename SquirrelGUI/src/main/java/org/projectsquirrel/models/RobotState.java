/**
 * 
 */
package org.projectsquirrel.models;

import java.util.List;

import org.projectsquirrel.controllers.BatteryPanelController;
import org.projectsquirrel.controllers.RobotPanelController;

/**
 * @author dave
 * 
 * class for model that contains robot position, clearance, battery, and connection data obtained from the RasPi
 * this is where data received from the RasPi is processed and then sent to the GUI.
 * 
 */
public class RobotState {
	private float alpha;
	private float yaw;
	private float gamma;
	private float pitch;
	private float extend;
	private float topClearance;
	private float botClearance;
	private List<Integer> claws;
	private float battery;
	
	/**
	 * Construct a model of the robot data based off the given sensor packet from the RasPi
	 * @param sensorPacket
	 */
	public RobotState(SensorPacket sensorPacket){
		
	}
	
	/**
	 * Update the GUI with robot information
	 */
	public void updateGUI(){
		RobotPanelController.updateRobotPosition(alpha, yaw, gamma, pitch, extend);
		RobotPanelController.updateRobotClaws(claws);
		RobotPanelController.updateBranchDistances(topClearance, botClearance);
		BatteryPanelController.updateBattery(battery);
	}
}
