package org.projectsquirrel.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * Data type for sending {@link Sensor} information as a packet.
 * @author David Pounds
 * @author Alex Stylos
 *
 */
public class SensorPacket {
	
	private List<Sensor> sensorData;
	
	public SensorPacket(){
		sensorData = new ArrayList<Sensor>();
	}
	
	/**
	 * @param sensor The {@link Sensor} to add to the data list.
	 */
	public void addSensor(Sensor sensor){
		sensorData.add(sensor);
	}
	
	/**
	 * @param sensor The {@link Sensor} to remove from the data list.
	 */
	public void removeSensor(Sensor sensor){
		sensorData.remove(sensor);
	}
	
	/**
	 * @return An {@link ArrayList} of {@link Sensor} data.
	 */
	public List<Sensor> getSensorData(){
		return new ArrayList<Sensor>(sensorData);
	}
	
	/**
	 * @param sensorData The {@link ArrayList} to set this packet to.
	 */
	public void setSensorData(List<Sensor> sensorData){
		this.sensorData = new ArrayList<Sensor>(sensorData);
	}
	
	/**
	 * @return A {@link String} representing the {@link SensorPacket} as Json.
	 */
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, SensorPacket.class);
		return json;
	}

	
	/**
	 * @param json A {@link String} of Json to create a {@link SensorPacket} from.
	 * @return A new {@link SensorPacket}.
	 */
	public static SensorPacket fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, SensorPacket.class);
	}
}
