package org.projectsquirrel.views;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import org.projectsquirrel.controllers.SendCommandController;
import org.projectsquirrel.models.Command;
import org.projectsquirrel.models.CommandType;

public class ControlView extends JPanel {

	private static final long serialVersionUID = -4398392847750945455L;

	private JButton btnReleaseClaws = new JButton("Release Claws");
	private JButton robotLeftBtn = new JButton("Left");
	private JButton robotUpBtn = new JButton("Up");
	private JButton robotDownBtn = new JButton("Down");
	private JButton robotRightBtn = new JButton("Right");
	private JButton cameraDownBtn = new JButton("Down");
	private JButton cameraUpBtn = new JButton("Up");

	private JLabel lblCameraControls = new JLabel("Camera Controls");
	private JLabel lblRobotControls = new JLabel("Robot Controls");

	/**
	 * Sets up the layout for the panel, then adds in the action listeners.
	 */
	public ControlView() {
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow][grow]"));
		add(lblCameraControls, "cell 0 0,alignx center,aligny center");
		
				lblCameraControls.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblRobotControls, "cell 1 0 3 1,alignx center,aligny center");
		
				lblRobotControls.setFont(new Font("Tahoma", Font.PLAIN, 18));

		add(cameraUpBtn, "cell 0 1,grow");

		add(btnReleaseClaws, "cell 2 1,grow");
		add(cameraDownBtn, "cell 0 2,grow");
		add(robotLeftBtn, "cell 1 4,grow");
		add(robotUpBtn, "cell 2 3,grow");
		add(robotDownBtn, "cell 2 4,grow");
		add(robotRightBtn, "cell 3 4,grow");

		addButtonActionListeners();
	}

	/**
	 * Adds action listeners too all the buttons in the view.
	 */
	private void addButtonActionListeners(){
		//robotLeftBtn.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), actionMapKey);
		
		robotLeftBtn.addActionListener(new SendCommandController(Command.LEFT, CommandType.MOTOR));
		cameraDownBtn.addActionListener(new SendCommandController(Command.LEFT, CommandType.CAMERA));
		cameraUpBtn.addActionListener(new SendCommandController(Command.RIGHT, CommandType.CAMERA));
		robotUpBtn.addActionListener(new SendCommandController(Command.UP, CommandType.MOTOR));
		robotDownBtn.addActionListener(new SendCommandController(Command.DOWN, CommandType.MOTOR));
		robotRightBtn.addActionListener(new SendCommandController(Command.RIGHT, CommandType.MOTOR));
	}

}
