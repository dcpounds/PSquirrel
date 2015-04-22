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
 * View that contains buttons for controlling the camera
 * 
 */
public class CameraControlPanel extends JPanel {

	private static final long serialVersionUID = -4398392847750945455L;

	private JButton robotLeftBtn;
	private JButton robotUpBtn;
	private JButton robotDownBtn;
	private JButton robotRightBtn;
	private JButton cameraCCWBtn;
	private JButton cameraCWBtn;

	private JLabel lblCameraControls;
	private JLabel lblRobotControls;

	/**
	 * Sets up the layout for the panel, then adds in the action listeners.
	 */
	public CameraControlPanel() {

		Dimension buttonDimension = new Dimension(15, 30);
		
		lblCameraControls = new JLabel("Camera Controls");
		cameraCCWBtn = new JButton("\u21ba");
		cameraCCWBtn.setSize(buttonDimension);
		cameraCWBtn = new JButton("\u21bb");
		cameraCWBtn.setSize(buttonDimension);


		setLayout(new MigLayout("", "[]", "[][]"));
		add(lblCameraControls, "cell 0 0, alignx center,aligny bottom");
		add(cameraCWBtn, "cell 0 1, flowx, alignx center, aligny top");
		add(cameraCCWBtn, "cell 0 1, flowx, alignx center, aligny top");
	

		addButtonActionListeners();
	}

	/**
	 * Adds action listeners to all the buttons in the view.
	 */
	private void addButtonActionListeners(){
		cameraCCWBtn.addMouseListener(new SendCommandController(Command.LEFT, CommandType.CAMERA));
		cameraCWBtn.addMouseListener(new SendCommandController(Command.RIGHT, CommandType.CAMERA));
	}

}
