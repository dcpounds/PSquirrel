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
	private int value;

	/**
	 * @param type
	 *            The type of sensor this data is for.
	 * @param sensorId
	 *            The unique sensor id.
	 * @param value
	 *            The measured value of the sensor. -1 if there is an error with
	 *            the data.
	 */
	public Sensor(SensorType type, int sensorId, int value) {
		this.setSensorType(type);
		this.setSensorId(sensorId);
		this.setValue(value);
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
	 * @param type
	 *            The type of sensor.
	 */
	public void setSensorType(SensorType type) {
		if (type == null) {
			throw new NullPointerException();
		} else {
			this.sensorType = type;
		}
	}

	/**
	 * @return The unique sensor id.
	 */
	public int getSensorId() {
		return sensorId;
	}

	/**
	 * @param id
	 *            The unique sensor id.
	 */
	public void setSensorId(int id) {
		if (id < 0) {
			throw new IllegalArgumentException();
		} else {
			this.sensorId = id;
		}
	}

	/**
	 * @return The measured value of the sensor.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The measured value of the sensor.
	 */
	public void setValue(int value) {
		if (value < 0) {
			throw new IllegalArgumentException();
		} else {
			this.value = value;
		}
	}
}
