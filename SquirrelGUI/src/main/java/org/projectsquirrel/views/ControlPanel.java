package org.projectsquirrel.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import org.projectsquirrel.controllers.BatteryPanelController;
import org.projectsquirrel.controllers.ConnectionPanelController;
import org.projectsquirrel.controllers.SendCommandController;
import org.projectsquirrel.models.Command;
import org.projectsquirrel.models.CommandType;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = -4398392847750945455L;

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
	public ControlPanel() {
		

		lblRobotControls = new JLabel("Robot Controls");
		lblRobotControls.setFont(new Font("Tahoma", Font.PLAIN, 18));

		Dimension buttonDimension = new Dimension(15, 30);
		robotLeftBtn = new JButton("\u2190");
		robotLeftBtn.setSize(buttonDimension);
		robotUpBtn = new JButton("\u2191");
		robotLeftBtn.setSize(buttonDimension);
		robotDownBtn = new JButton("\u2193");
		robotLeftBtn.setSize(buttonDimension);
		robotRightBtn = new JButton("\u2192");
		robotLeftBtn.setSize(buttonDimension);
		
		lblCameraControls = new JLabel("Camera Controls");
		lblCameraControls.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cameraDownBtn = new JButton("\u2193");
		cameraDownBtn.setSize(buttonDimension);
		cameraUpBtn = new JButton("\u2191");
		cameraUpBtn.setSize(buttonDimension);

		JPanel robotButtonPanel = new JPanel();
		setLayout(new MigLayout("", "[]", "[][]40[][]70[][]"));
		robotButtonPanel.setLayout(new MigLayout("", "[][][]", "[][]"));
		
		ConnectionPanel connectionPanel = ConnectionPanelController.getConnectionPanel();
		add(connectionPanel, "flowy,cell 0 0, alignx center, aligny top, flowy");
		
		BatteryPanel batteryPanel = BatteryPanelController.getBatteryPanel();
		add(batteryPanel, "flowy,cell 0 1, alignx center, aligny top, flowy");

		add(lblCameraControls, "cell 0 2, alignx center,aligny bottom");
		add(cameraUpBtn, "cell 0 3, flowy, alignx center, aligny top");
		add(cameraDownBtn, "cell 0 3, flowy, alignx center");
		
		add(lblRobotControls, "cell 0 4,alignx center,aligny bottom");
		//add(btnReleaseClaws, "cell 2 1,grow");
		robotButtonPanel.add(robotUpBtn, "cell 1 0, alignx center, aligny bottom");
		robotButtonPanel.add(robotLeftBtn, "cell 0 1, alignx right, aligny top");
		robotButtonPanel.add(robotDownBtn, "cell 1 1, alignx center, aligny top");
		robotButtonPanel.add(robotRightBtn, "cell 2 1, alignx left, aligny top");
		add(robotButtonPanel, "cell 0 5, alignx center, aligny top");

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
