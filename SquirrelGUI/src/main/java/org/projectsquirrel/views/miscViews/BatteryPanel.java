package org.projectsquirrel.views.miscViews;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import net.miginfocom.swing.MigLayout;

/**
 * @author dave
 *
 * This view displays the battery remaining percentage bar
 *
 */
public class BatteryPanel extends JPanel{
	
	private static final long serialVersionUID = 6507516338199185322L;
	
	private JProgressBar batteryCharge;
	
	/**
	 *  Constructs a new view
	 */
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
	 * Updates the percentage of the battery
	 * @value - battery percentage remaining
	 */
	public void update(float value){
		batteryCharge.setValue((int)value);
	}

}
