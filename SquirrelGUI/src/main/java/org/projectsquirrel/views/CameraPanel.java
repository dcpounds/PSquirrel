package org.projectsquirrel.views;

import javax.swing.ImageIcon;
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
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This view displays the camera frame on the GUI.
 * 
 * @author Alex Stylos
 * @author David Pounds
 *
 */
public class CameraPanel extends JPanel {
	
	private static final long serialVersionUID = 6507516338199185322L;
	
	private JLabel picture = new JLabel();
	private BufferedImage image;
	
	public CameraPanel() {
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		setPreferredSize(new Dimension(480, 360));
		setMinimumSize(new Dimension(360, 270));
		add(picture, "cell 0 0, alignx left, aligny center");
	}
	
    
	/**
	 * Updates the view with the new camera frame to display
	 */
	public void update(BufferedImage image){
		picture.setIcon(new ImageIcon(image));
	}
}
