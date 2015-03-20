/**
 * 
 */
package org.projectsquirrel.GUIdebug;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import org.projectsquirrel.controllers.ConnectionPanelController;
import org.projectsquirrel.controllers.RobotPanelController;

/**
 * @author dave
 *
 * debug class for toggle button that toggles connection state
 *
 */
public class ConnectionButton extends JToggleButton implements ActionListener {

	private Integer number;
	private boolean pressed;
	private DebugWindow debugWindow;
	
	ConnectionButton(){
		super("Toggle Connection");
		pressed = false;
		addActionListener(this);
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
		ConnectionPanelController.updateConnection(pressed);
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
