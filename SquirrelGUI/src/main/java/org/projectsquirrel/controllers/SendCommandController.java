package org.projectsquirrel.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.projectsquirrel.models.Command;
import org.projectsquirrel.models.CommandPacket;
import org.projectsquirrel.models.CommandType;
import org.projectsquirrel.network.NetworkUninitializedException;
import org.projectsquirrel.network.SocketManager;

public class SendCommandController implements ActionListener {

	private CommandPacket commandPacket;

	public SendCommandController(Command command, CommandType commandType) {
		commandPacket = new CommandPacket(command, commandType);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			SocketManager.sendCommandPacket(commandPacket);
			System.out.println(SocketManager.receiveSensorPacket().toJson());
		} catch (NetworkUninitializedException e) {
			System.out
					.println("cannot send command without initializing network");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
