package org.projectsquirrel.models;

import com.google.gson.Gson;

/**
 * Data type for storing sensor information on the robot.
 * 
 * @author David Pounds
 * @author Alex Stylos
 *
 */
public class Sensor {
	private SensorType sensorType;
	private int sensorId;
	private float value;

	/**
	 * @param type
	 *            The type of sensor this data is for.
	 * @param sensorId
	 *            The unique sensor id.
	 * @param value
	 *            The measured value of the sensor. -1 if there is an error with
	 *            the data.
	 */
	public Sensor(SensorType type, int id, float value) {
		sensorType = type;
		sensorId = id;
		this.value = value;
	}

	/**
	 * @param json
	 *            A {@link String} with the Sensor information in Json object
	 *            form.
	 * @return A {@link Sensor}.
	 */
	public static Sensor fromJson(String json) {
		final Gson parser = new Gson();
		return parser.fromJson(json, Sensor.class);
	}

	/**
	 * @return The type of sensor.
	 */
	public SensorType getSensorType() {
		return sensorType;
	}

	/**
	 * @return The unique sensor id.
	 */
	public int getSensorId() {
		return sensorId;
	}

	/**
	 * @return The measured value of the sensor.
	 */
	public float getValue() {
		return value;
	}
	
}
