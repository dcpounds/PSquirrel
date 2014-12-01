package org.projectsquirrel.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class SensorPacket {
	List<Sensor> sensorData;
	
	public SensorPacket(){
		sensorData = new ArrayList<Sensor>();
	}
	
	
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, SensorPacket.class);
		return json;
	}

	
	public static SensorPacket fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, SensorPacket.class);
	}
}
