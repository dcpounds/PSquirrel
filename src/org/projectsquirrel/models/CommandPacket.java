package org.projectsquirrel.models;

import com.google.gson.Gson;


public class CommandPacket {
	private Command command;
	private CommandType commandType;
	
	public CommandPacket(Command command, CommandType commandType){
		this.command = command;
		this.commandType = commandType;
	}
	
	public String toJson() {
		String json;
		Gson gson = new Gson();
		json = gson.toJson(this, CommandPacket.class);
		return json;
	}

	
	public static CommandPacket fromJson(String json) {
        final Gson parser = new Gson();
        return parser.fromJson(json, CommandPacket.class);
	}
	
}
