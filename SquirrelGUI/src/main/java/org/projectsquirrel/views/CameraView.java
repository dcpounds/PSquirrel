package org.projectsquirrel.views;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import org.projectsquirrel.controllers.BatteryPanelController;
import org.projectsquirrel.controllers.ConnectionPanelController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * This view displays the camera frame on the GUI.
 * 
 * @author Alex Stylos
 * @author David Pounds
 *
 */
public class CameraView extends JPanel {
	
	private static final long serialVersionUID = 6507516338199185322L;
	
	private JPanel cameraPanel;
	
	public CameraView() {
		setLayout(new MigLayout("", "[grow][]", "[grow]"));

		cameraPanel = new JPanel();
		cameraPanel.setBackground(Color.GRAY);
		cameraPanel.setPreferredSize(new Dimension(480, 360));
		add(cameraPanel, "cell 0 0, alignx left, aligny center");
		
		ConnectionPanel connectionPanel = ConnectionPanelController.getConnectionPanel();
		add(connectionPanel, "flowy,cell 1 0, alignx center, aligny top, flowy");
		
		BatteryPanel batteryPanel = BatteryPanelController.getBatteryPanel();
		add(batteryPanel, "flowy,cell 1 0, alignx center, aligny top, flowy");
	}
	
	/**
	 * Updates the view with the new camera frame to display
	 */
	public void updateCameraFrame(){
		
	}
}
