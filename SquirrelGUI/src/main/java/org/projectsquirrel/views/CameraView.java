package org.projectsquirrel.views;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

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
	private JLabel lblConnected;
	private JProgressBar batteryCharge;
	
	public CameraView() {
		setLayout(new MigLayout("", "[grow][]", "[grow]"));

		cameraPanel = new JPanel();
		cameraPanel.setBackground(Color.GRAY);
		cameraPanel.setPreferredSize(new Dimension(320, 240));
		add(cameraPanel, "cell 0 0, alignx center, aligny center");
		
		JLabel lblConnectionStatus = new JLabel("Connection Status");
		add(lblConnectionStatus, "flowy,cell 1 0,alignx center,aligny top, flowy");
		
		lblConnected = new JLabel("Connected");
		lblConnected.setForeground(Color.GREEN);
		add(lblConnected, "cell 1 0, alignx center, flowy");
		
		JLabel label = new JLabel("Battery");
		add(label, "flowy,cell 1 0, alignx center, flowy");
		batteryCharge = new JProgressBar();
		batteryCharge.setMinimumSize(new Dimension(50, 14));
		batteryCharge.setStringPainted(true);
		batteryCharge.setValue(90);
		add(batteryCharge, "flowy,cell 1 0,alignx center, flowy");

	}
	
	/**
	 * Updates the view with the new camera frame to display
	 */
	public void updateCameraFrame(){
		
	}
}
