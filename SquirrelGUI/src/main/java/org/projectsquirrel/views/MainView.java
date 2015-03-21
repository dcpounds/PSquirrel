package org.projectsquirrel.views;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;

import java.awt.Component;

import javax.swing.JLabel;

import org.projectsquirrel.controllers.CameraController;
import org.projectsquirrel.controllers.RobotPanelController;
import org.projectsquirrel.models.Sensor;
import org.projectsquirrel.models.SensorPacket;
import org.projectsquirrel.models.SensorType;
import org.projectsquirrel.network.SocketManager;
import org.projectsquirrel.views.miscViews.SidePanel;
import org.projectsquirrel.views.robotViews.RobotPanel;

import java.awt.Font;
import java.io.IOError;
import java.io.IOException;
import java.awt.Dimension;

public class MainView {

	private JFrame window;

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		Container contentPane = window.getContentPane();
		
		//frmProjectSquirrelController.setMinimumSize(new Dimension(950, 850));
		window.setTitle("Project Squirrel Controller");
		window.setBounds(200, 75, 700, 625);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(new MigLayout("", "[grow][]", "[][]"));

		
		JPanel cameraPanel = CameraController.getCameraPanel();
		contentPane.add(cameraPanel, "cell 0 0, grow");

		JPanel robotPanel = RobotPanelController.getRobotPanel();
		contentPane.add(robotPanel, "cell 0 1, grow");

		JPanel controlPanel = new SidePanel();
		contentPane.add(controlPanel, "cell 1 0 1 2, grow");
	}

	public JFrame getFrame() {
		return window;
	}

}
