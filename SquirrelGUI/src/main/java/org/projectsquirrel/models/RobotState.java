/**
 * 
 */
package org.projectsquirrel.models;

import java.util.List;

import org.projectsquirrel.controllers.BatteryPanelController;
import org.projectsquirrel.controllers.RobotPanelController;

import com.google.gson.Gson;

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
	private List<Integer> attachedClaws;
	private float battery;
	
	/**
	 * Construct a model of the robot data based off the given sensor packet from the RasPi
	 * @param sensorPacket
	 */
	public RobotState(){
		
	}
	
	/**
	 * Update the GUI with robot information
	 */
	public void updateGUI(){
		RobotPanelController.updateRobotPosition(alpha, yaw, gamma, pitch, extend);
		RobotPanelController.updateRobotClaws(attachedClaws);
		RobotPanelController.updateBranchDistances(topClearance, botClearance);
		BatteryPanelController.updateBattery(battery);
	}
	
	/**
	 * @return A {@link String} representing the {@link RobotState} as Json.
	 */
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, RobotState.class);
		return json;
	}

	/**
	 * @param json
	 *            A {@link String} of Json to create a {@link RobotState}
	 *            from.
	 * @return A new {@link RobotState}.
	 */
	public static RobotState fromJson(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, RobotState.class);
	}
}
