package org.projectsquirrel.views;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.projectsquirrel.models.Command.*;
import static org.projectsquirrel.models.CommandType.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

import org.projectsquirrel.controllers.SendCommandController;

import net.miginfocom.swing.MigLayout;

public class ControlView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4398392847750945455L;

	public ControlView() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new MigLayout("", "[16%][16%][17%][17%][17%][17%]",
				"[20%][20%][20%][20%][20%]"));

		JLabel lblCameraControls = new JLabel("Camera Controls");
		lblCameraControls.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblCameraControls, "cell 3 0 3 1,alignx center,aligny center");

		JToggleButton cameraLeftBtn = new JToggleButton("Left");
		cameraLeftBtn
				.addActionListener(new SendCommandController(LEFT, CAMERA));
		add(cameraLeftBtn, "cell 3 1,grow");

		JToggleButton cameraRightBtn = new JToggleButton("Right");
		cameraRightBtn.addActionListener(new SendCommandController(RIGHT,
				CAMERA));
		add(cameraRightBtn, "cell 5 1,grow");

		JLabel lblRobotControls = new JLabel("Robot Controls");
		lblRobotControls.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblRobotControls, "cell 3 2 3 1,alignx center,aligny center");

		JButton robotLeftBtn = new JButton("Left");
		robotLeftBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		robotLeftBtn.addActionListener(new SendCommandController(LEFT, MOTOR));
		add(robotLeftBtn, "cell 3 4,grow");

		JButton robotUpBtn = new JButton("Up");
		robotUpBtn.addActionListener(new SendCommandController(UP, MOTOR));
		add(robotUpBtn, "cell 4 3,grow");

		JButton stopRobotBtn = new JButton("Stop Robot");
		stopRobotBtn.addActionListener(new SendCommandController(STOP, MOTOR));
		add(stopRobotBtn, "flowx,cell 0 3 2 2,grow");

		JButton robotDownBtn = new JButton("Down");
		robotDownBtn.addActionListener(new SendCommandController(DOWN, MOTOR));
		add(robotDownBtn, "cell 4 4,grow");

		JButton robotRightBtn = new JButton("Right");
		robotRightBtn
				.addActionListener(new SendCommandController(RIGHT, MOTOR));
		add(robotRightBtn, "cell 5 4,grow");
	}

}
