package org.projectsquirrel.models;

import com.google.gson.Gson;

public class Sensor {
	private SensorType type;
	private int number;
	private int value;
	
	public Sensor(SensorType type, int number, int value){
		this.type = type;
		this.number = number;
		this.value = value;
	}
	
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, Sensor.class);
		return json;
	}

	
	public static Sensor fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, Sensor.class);
	}
}
