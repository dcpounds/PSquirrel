/**
 * 
 */
package org.projectsquirrel.debug;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import org.projectsquirrel.controllers.RobotPanelController;
import org.projectsquirrel.controllers.SonarPanelController;

/**
 * @author dave
 *
 */
/**
 * @author dave
 *
 */
public class ClawButton extends JToggleButton implements ActionListener {

	private Integer number;
	private boolean pressed;
	private DebugWindow debugWindow;
	
	ClawButton(Integer number, DebugWindow debugWindow){
		super(number.toString());
		this.number = number;
		pressed = false;
		addActionListener(this);
		this.debugWindow = debugWindow;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(pressed){
			pressed = false;
		} else {
			pressed = true;
		}
		RobotPanelController.updateRobotClaws(debugWindow.getPressedClaws());
	}

	/**
	 * @return
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @return
	 */
	public boolean isPressed() {
		return pressed;
	}

}