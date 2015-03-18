package org.projectsquirrel.views.controlViews;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import net.miginfocom.swing.MigLayout;

public class BatteryPanel extends JPanel{
	
	private static final long serialVersionUID = 6507516338199185322L;
	
	private JProgressBar batteryCharge;
	
	public BatteryPanel() {
		JLabel label = new JLabel("Battery");
		batteryCharge = new JProgressBar();
		batteryCharge.setMinimumSize(new Dimension(50, 14));
		batteryCharge.setStringPainted(true);
		update(0);

		setLayout(new MigLayout("", "[]", "[][]"));

		add(label, "flowy,cell 0 1, alignx center");
		add(batteryCharge, "cell 0 2,alignx center");

	}
	
	/**
	 * Updates the view with the new camera frame to display
	 */
	public void update(float value){
		batteryCharge.setValue((int)value);
	}

}
