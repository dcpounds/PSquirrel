/** 
 * 
 */
package org.projectsquirrel.GUIdebug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.projectsquirrel.controllers.BatteryPanelController;
import org.projectsquirrel.controllers.RobotPanelController;

import net.miginfocom.swing.MigLayout;

/**
 * @author dave
 *
 * debug class for window that is able to toggle the different values in the GUI
 *
 */
public class DebugWindow extends JFrame {

	private JTextField gamma;		 //overall robot pitch angle
	private JTextField pitch;		 //middle segment pitch angle
	private JTextField alpha;		 //overall robot yaw angle
	private JTextField yaw;		     //middle segment yaw angle
	private JTextField extend;		 //screw extension
	private JTextField topClearance; //distance to any top obstructions
	private JTextField botClearance; //distance to any bottom obstructions
	private JTextField battery;      //battery percentage left
	private JLabel alphaLabel;		
	private JLabel yawLabel;		
	private JLabel extendLabel;		
	private JLabel gammaLabel;		
	private JLabel pitchLabel;
	private JLabel topLabel;
	private JLabel botLabel;
	private JLabel batteryLabel;
	private JLabel clawLabel;
	private JPanel debugView;   
	private List<ClawButton> clawButtons;        //buttons for toggling claws
	private ConnectionButton connectionButton;   //button for toggling connection status

	public DebugWindow(){
		setTitle("Project Squirrel GUI Debugger");
		setBounds(950, 75, 300, 625);

		debugView = new JPanel();

		//overall robot yaw angle
		alpha = new JTextField("0");
		alpha.setColumns(3);
		alphaLabel = new JLabel("Alpha:");

		//middle segment yaw angle
		yaw = new JTextField("0");
		yaw.setColumns(3);
		yawLabel = new JLabel("Yaw:");

		//overall robot pitch angle
		gamma = new JTextField("0");
		gamma.setColumns(3);
		gammaLabel = new JLabel("Gamma:");

		//middle segment pitch angle
		pitch = new JTextField("0");
		pitch.setColumns(3);
		pitchLabel = new JLabel("Pitch");

		//screw extension
		extend = new JTextField("0");
		extend.setColumns(3);
		extendLabel = new JLabel("Extend:");

		KeyListener robotPositionKeyListener= new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				robotPositionUpdateHelper();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				robotPositionUpdateHelper();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				robotPositionUpdateHelper();
			}
		};

		alpha.addKeyListener(robotPositionKeyListener);
		yaw.addKeyListener(robotPositionKeyListener);
		gamma.addKeyListener(robotPositionKeyListener);
		pitch.addKeyListener(robotPositionKeyListener);
		extend.addKeyListener(robotPositionKeyListener);

		clawLabel = new JLabel("Toggle Claws:");
		clawButtons = new LinkedList<ClawButton>();
		for(Integer i = 1; i <= 8; i++){
			clawButtons.add(new ClawButton(i, this));
		}
		
		topClearance = new JTextField("0");
		topClearance.setColumns(3);
		topLabel = new JLabel("Top Clearance:");

		botClearance = new JTextField("0");
		botClearance.setColumns(3);
		botLabel = new JLabel("Bottom Clearance:");

		KeyListener sonarKeyListener= new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				sonarUpdateHelper();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				sonarUpdateHelper();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				sonarUpdateHelper();
			}
		};
		
		topClearance.addKeyListener(sonarKeyListener);
		botClearance.addKeyListener(sonarKeyListener);

		battery = new JTextField("0");
		battery.setColumns(3);
		batteryLabel = new JLabel("Battery:");

		KeyListener batteryKeyListener= new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				batteryUpdateHelper();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				batteryUpdateHelper();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				batteryUpdateHelper();
			}
		};
		
		battery.addKeyListener(batteryKeyListener);
		
		connectionButton = new ConnectionButton();
		
		debugView.setLayout(new MigLayout("", "[][]", "[][][][][][][][]"));
		debugView.add(alphaLabel, "cell 0 0");
		debugView.add(alpha, "cell 1 0");
		debugView.add(yawLabel, "cell 0 1");
		debugView.add(yaw, "cell 1 1");
		debugView.add(gammaLabel, "cell 0 2");
		debugView.add(gamma, "cell 1 2");
		debugView.add(pitchLabel, "cell 0 3");
		debugView.add(pitch, "cell 1 3");
		debugView.add(extendLabel, "cell 0 4");
		debugView.add(extend, "cell 1 4");
		debugView.add(clawLabel, "cell 0 5, flowy");
		for(ClawButton clawButton: clawButtons){
			debugView.add(clawButton, "cell 0 5, flowy");
		}
		debugView.add(topLabel, "cell 0 6");
		debugView.add(topClearance, "cell 1 6");
		debugView.add(botLabel, "cell 0 7");
		debugView.add(botClearance, "cell 1 7");
		debugView.add(batteryLabel, "cell 0 8");
		debugView.add(battery, "cell 1 8");
		debugView.add(connectionButton, "cell 0 9");

		this.getContentPane().add(debugView);
	}

	public List<Integer> getPressedClaws(){
		List<Integer> attachedClaws = new LinkedList<Integer>();
		for(ClawButton clawButton: clawButtons){
			if(clawButton.isPressed()){
				attachedClaws.add(clawButton.getNumber());
			}
		}
		return attachedClaws;
	}

	private void robotPositionUpdateHelper(){
		try {
			RobotPanelController.updateRobotPosition(
					Float.parseFloat(alpha.getText()),
					Float.parseFloat(yaw.getText()),
					Float.parseFloat(gamma.getText()),
					Float.parseFloat(pitch.getText()),
					Float.parseFloat(extend.getText()));
		} catch(NumberFormatException e) {
			//do nothing
		}
	}
	
	private void sonarUpdateHelper(){
		try {
			RobotPanelController.updateBranchDistances(
					Float.parseFloat(topClearance.getText()),
					Float.parseFloat(botClearance.getText()));
		} catch(NumberFormatException e) {
			//do nothing
		}
	}
	
	private void batteryUpdateHelper(){
		try {
			BatteryPanelController.updateBattery(
					Float.parseFloat(battery.getText()));
		} catch(NumberFormatException e) {
			//do nothing
		}
	}

}
