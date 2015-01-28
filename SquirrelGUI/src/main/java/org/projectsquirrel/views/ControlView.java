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

	private JButton btnReleaseClaws;
	private JButton robotLeftBtn;
	private JButton robotUpBtn;
	private JButton robotDownBtn;
	private JButton robotRightBtn;
	private JButton cameraDownBtn;
	private JButton cameraUpBtn;

	private JLabel lblCameraControls;
	private JLabel lblRobotControls;

	/**
	 * Sets up the layout for the panel, then adds in the action listeners.
	 */
	public ControlView() {
		

		lblRobotControls = new JLabel("Robot Controls");
		lblRobotControls.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnReleaseClaws = new JButton("Release Claws");
		robotLeftBtn = new JButton("\u2190");
		robotUpBtn = new JButton("\u2191");
		robotDownBtn = new JButton("\u2193");
		robotRightBtn = new JButton("\u2192");
		
		lblCameraControls = new JLabel("Camera Controls");
		lblCameraControls.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cameraDownBtn = new JButton("\u2193");
		cameraUpBtn = new JButton("\u2191");


		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[grow][grow][grow][grow][grow]"));

		add(lblCameraControls, "cell 0 0,alignx center,aligny center");
		add(cameraUpBtn, "cell 0 1,grow");
		add(cameraDownBtn, "cell 0 2,grow");
		
		add(lblRobotControls, "cell 1 0 3 1,alignx center,aligny center");
		add(btnReleaseClaws, "cell 2 1,grow");
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
