package org.projectsquirrel.models;

import com.google.gson.Gson;

/**
 * Data type for storing commands to send to the RasPi controller.
 * 
 * @author David Pounds
 * @author Alex Stylos
 *
 */
public class CommandPacket {

	private Command command;
	private CommandType commandType;

	public CommandPacket(Command command, CommandType commandType) {
		this.setCommand(command);
		this.setCommandType(commandType);
	}

	/**
	 * Converts a {@link CommandPacket} to a Json object in {link String} form.
	 * 
	 * @return A {@link String} of this CommandPacket represented in Json.
	 */
	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this, CommandPacket.class);
		return json;
	}

	/**
	 * @return The {@link Command} in this packet.
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * @param command
	 *            The {@link Command} to set in this packet.
	 */
	public void setCommand(Command command) {
		if (command == null) {
			throw new NullPointerException();
		} else {
			this.command = command;
		}
	}

	/**
	 * @return The {@link CommandType} of this packet.
	 */
	public CommandType getCommandType() {
		return commandType;
	}

	/**
	 * @param commandType
	 *            The {@link CommandType} to set in this packet.
	 */
	public void setCommandType(CommandType commandType) {
		if (commandType == null) {
			throw new NullPointerException();
		} else {
			this.commandType = commandType;
		}
	}
}
