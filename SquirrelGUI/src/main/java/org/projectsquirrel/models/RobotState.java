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
 */
public class RobotState {
	private float alpha;
	private float yaw;
	private float gamma;
	private float pitch;
	private float extend;
	private float topClearance;
	private float bottomClearance;
	private List<Integer> claws;
	private float battery;

	public RobotState(SensorPacket sensorPacket){
		
	}
	
	public void updateGUI(){
		RobotPanelController.updateRobotPosition(alpha, yaw, gamma, pitch, extend);
		RobotPanelController.updateRobotClaws(claws);
		BatteryPanelController.updateBattery(battery);
	}
}
