package org.projectsquirrel;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

public class ControlView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4398392847750945455L;

	public ControlView(){
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setLayout(new MigLayout("", "[16%][16%][17%][17%][17%][17%]", "[20%][20%][20%][20%][20%]"));
        
        JLabel lblCameraControls = new JLabel("Camera Controls");
        lblCameraControls.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblCameraControls, "cell 3 0 3 1,alignx center,aligny center");
        
        JToggleButton cameraOnBtn = new JToggleButton("On");
        add(cameraOnBtn, "cell 3 1,grow");
        
        JToggleButton cameraOffBtn = new JToggleButton("Off");
        add(cameraOffBtn, "cell 5 1,grow");
        
        JLabel lblRobotControls = new JLabel("Robot Controls");
        lblRobotControls.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblRobotControls, "cell 3 2 3 1,alignx center,aligny center");
        
        JButton robotLeftBtn = new JButton("Left");
        robotLeftBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        robotLeftBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        JButton robotUpBtn = new JButton("Up");
        add(robotUpBtn, "cell 4 3,grow");
        
        JButton StopRobotBtn = new JButton("Stop Robot");
        StopRobotBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        add(StopRobotBtn, "flowx,cell 0 3 2 2,grow");
        add(robotLeftBtn, "cell 3 4,grow");
        
        JButton robotDownBtn = new JButton("Down");
        add(robotDownBtn, "cell 4 4,grow");
        
        JButton robotRightBtn = new JButton("Right");
        add(robotRightBtn, "cell 5 4,grow");
	}

}
