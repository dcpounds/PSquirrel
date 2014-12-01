package org.projectsquirrel.views;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class CameraView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6507516338199185322L;

	public CameraView(){
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setLayout(new MigLayout("", "[100%]", "[10%][90%]"));
        
        JLabel videoLbl = new JLabel("Video");
        videoLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(videoLbl, "cell 0 0, alignx center");
        
        JPanel cameraPanel = new JPanel();
        cameraPanel.setBackground(Color.GRAY);
        cameraPanel.setPreferredSize(new Dimension(300, 300));
        add(cameraPanel, "cell 0 1,alignx center,aligny center");
		
	}
}
