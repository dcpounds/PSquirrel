package org.projectsquirrel.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import org.projectsquirrel.models.Command;
import org.projectsquirrel.models.CommandPacket;
import org.projectsquirrel.models.CommandType;
import org.projectsquirrel.network.NetworkUninitializedException;
import org.projectsquirrel.network.SocketManager;


/**
 * @author dave
 *
 * Class for sending a command received by the GUI
 * 
 */
public class SendCommandController extends MouseAdapter {

	private CommandPacket commandPacket;
	private CommandPacket stopCommandPacket;

	/**
	 * Initializes the controller for the given command and command type
	 * @param command - direction to move in, either STOP, UP, DOWN, LEFT, or RIGHT for DRIVE type commands
	 * 	and STOP, CW, or CCW for CAMERA type commands
	 * @param commandType - indicates type of command which is either CAMERA or DRIVE
	 */
	public SendCommandController(Command command, CommandType commandType) {
		commandPacket = new CommandPacket(command, commandType);
		stopCommandPacket = new CommandPacket(Command.STOP, commandType);
	}

	/**
	 * Sends the command through the network manager when button is pressed
	 *  (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {
		try {
			System.out.println(commandPacket);
			SocketManager.sendCommandPacket(commandPacket);
		} catch (NetworkUninitializedException e) {
			System.out.println("cannot send command without initializing network");
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a stop command through the network manager when button is released
	 *  (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		try {
			System.out.println(commandPacket);
			SocketManager.sendCommandPacket(stopCommandPacket);
		} catch (NetworkUninitializedException e) {
			System.out.println("cannot send command without initializing network");
			e.printStackTrace();
		}
	}

}
