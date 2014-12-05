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


public class MainView {

    private JFrame frame;

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
        frame = new JFrame();
        frame.setBounds(100, 100, 942, 596);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[50%][50%]", "[66%][34%]"));
        
        JPanel cameraPanel = new CameraView();
        frame.getContentPane().add(cameraPanel, "cell 0 0 2 1,grow");
        
        JPanel infoPanel = new InfoView();
        frame.getContentPane().add(infoPanel, "cell 0 1,grow");
        
        JPanel controlPanel = new ControlView();
        frame.getContentPane().add(controlPanel, "cell 1 1,grow");
    }
    
    public JFrame getFrame(){
    	return frame;
    }

}
