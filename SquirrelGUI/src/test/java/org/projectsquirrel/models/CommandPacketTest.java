package org.projectsquirrel.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for {@link CommandPacket}
 * @author Stylos
 *
 */
public class CommandPacketTest {

	private Command commandUp;
	private Command commandDown;
	private Command commandLeft;
	private Command commandRight;
	private Command commandStop;
	private CommandType typeMotor;
	private CommandType typeCamera;
	private CommandPacket command;

	@Before
	public void setup(){
		commandUp = Command.UP;
		commandDown = Command.DOWN;
		commandLeft = Command.LEFT;
		commandRight = Command.RIGHT;
		commandStop = Command.STOP;
		typeMotor = CommandType.MOTOR;
		typeCamera = CommandType.CAMERA;
		command = new CommandPacket(commandUp, typeMotor);
	}
	
	@Test 
	public void testGetCommandReturnsProperCommand(){
		Command testCommand = command.getCommand();
		assertEquals(testCommand, commandUp);
	}
	
	@Test 
	public void testGetCommandTypeReturnsProperCommandType(){
		CommandType testCommandType = command.getCommandType();
		assertEquals(testCommandType, typeMotor);
	}
	
	@Test (expected=NullPointerException.class)
	public void testSetCommandFailWithNull(){
		command.setCommand(null);
	}
	
	@Test (expected=NullPointerException.class)
	public void testSetCommandTypeFailWithNull(){
		command.setCommandType(null);
	}
	
	@Test
	public void testSetCommandSucceed(){
		command.setCommand(commandDown);
		assertEquals(command.getCommand(), commandDown);
	}
	
	@Test
	public void testToJsonReturnsProperString(){
		CommandPacket packet = new CommandPacket(commandUp, typeMotor);
		String testJson = packet.toJson();
		assertEquals("{\"command\":\"UP\",\"commandType\":\"MOTOR\"}", testJson);
	}
}

