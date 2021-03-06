package org.projectsquirrel.views.miscViews;

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

/**
 * @author dave
 * 
 * View that contains buttons for controlling the robot gait
 * 
 */
public class RobotControlPanel extends JPanel {

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
	public RobotControlPanel() {
		

		lblRobotControls = new JLabel("Robot Controls");

		Dimension buttonDimension = new Dimension(15, 30);
		robotLeftBtn = new JButton("\u21B6");
		robotLeftBtn.setSize(buttonDimension);
		robotUpBtn = new JButton("\u2191");
		robotUpBtn.setSize(buttonDimension);
		robotDownBtn = new JButton("\u2193");
		robotDownBtn.setSize(buttonDimension);
		robotRightBtn = new JButton("\u21B7");
		robotRightBtn.setSize(buttonDimension);

		JPanel robotButtonPanel = new JPanel();
		setLayout(new MigLayout("", "[]", "[][][]"));
		
		add(lblRobotControls, "cell 0 0, alignx center,aligny bottom");
		add(robotUpBtn, "cell 0 1, alignx center, aligny bottom");
		add(robotLeftBtn, "cell 0 2, alignx center, flowx, aligny top");
		add(robotDownBtn, "cell 0 2, alignx center, flowx, aligny top");
		add(robotRightBtn, "cell 0 2, alignx center, flowx, aligny top");

		addButtonActionListeners();
	}

	/**
	 * Adds action listeners too all the buttons in the view.
	 */
	private void addButtonActionListeners(){
		//robotLeftBtn.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), actionMapKey);
		
		robotLeftBtn.addMouseListener(new SendCommandController(Command.LEFT, CommandType.DRIVE));
		robotUpBtn.addMouseListener(new SendCommandController(Command.UP, CommandType.DRIVE));
		robotDownBtn.addMouseListener(new SendCommandController(Command.DOWN, CommandType.DRIVE));
		robotRightBtn.addMouseListener(new SendCommandController(Command.RIGHT, CommandType.DRIVE));
	}

}
