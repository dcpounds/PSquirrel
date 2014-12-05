package org.projectsquirrel.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.projectsquirrel.models.Command;
import org.projectsquirrel.models.CommandPacket;
import org.projectsquirrel.models.CommandType;

public class SendCommandController implements ActionListener {

	private CommandPacket commandPacket;

	public SendCommandController(Command command, CommandType commandType) {
		commandPacket = new CommandPacket(command, commandType);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			NetworkManager.sendCommandPacket(commandPacket);
			System.out.println(NetworkManager.receiveSensorPacket().toJson());
		} catch (NetworkUninitializedException e) {
			System.out
					.println("cannot send command without initializing network");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
