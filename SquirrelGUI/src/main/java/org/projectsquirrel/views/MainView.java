package org.projectsquirrel.views;

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

import org.projectsquirrel.controllers.NetworkManager;
import org.projectsquirrel.models.Sensor;
import org.projectsquirrel.models.SensorPacket;
import org.projectsquirrel.models.SensorType;

import java.awt.Font;
import java.io.IOError;
import java.io.IOException;
import java.awt.Dimension;

public class MainView {

	private JFrame frmProjectSquirrelController;

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
		frmProjectSquirrelController = new JFrame();
		frmProjectSquirrelController.setMinimumSize(new Dimension(950, 850));
		frmProjectSquirrelController.setTitle("Project Squirrel Controller");
		frmProjectSquirrelController.setBounds(100, 100, 942, 596);
		frmProjectSquirrelController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjectSquirrelController.getContentPane().setLayout(
				new MigLayout("", "[grow][]", "[66%][34%]"));

		JPanel cameraPanel = new CameraView();
		frmProjectSquirrelController.getContentPane().add(cameraPanel, "cell 0 0 2 1,grow");

		JPanel infoPanel = new InfoView();
		frmProjectSquirrelController.getContentPane().add(infoPanel, "cell 0 1,grow");

		JPanel controlPanel = new ControlView();
		frmProjectSquirrelController.getContentPane().add(controlPanel, "cell 1 1,grow");
	}

	public JFrame getFrame() {
		return frmProjectSquirrelController;
	}

}
