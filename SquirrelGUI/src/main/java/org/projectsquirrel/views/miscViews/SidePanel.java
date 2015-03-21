package org.projectsquirrel.views.miscViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.projectsquirrel.controllers.BatteryPanelController;
import org.projectsquirrel.controllers.ConnectionPanelController;
import org.projectsquirrel.controllers.RobotPanelController;
import org.projectsquirrel.controllers.SendCommandController;
import org.projectsquirrel.models.Command;
import org.projectsquirrel.models.CommandType;
import org.projectsquirrel.views.robotViews.RobotPanel;

/**
 * @author dave
 * 
 * View that contains the battery, connection, camera control and robot control views
 * 
 */
public class SidePanel extends JPanel {
	private static final long serialVersionUID = -8434788151525363749L;

	private BatteryPanel batteryPanel;
	private ConnectionPanel connectionPanel;
	private CameraControlPanel cameraControlPanel;
	private RobotControlPanel robotControlPanel;
	
	/**
	 * Constructs the View
	 */
	public SidePanel() {
		batteryPanel = BatteryPanelController.getBatteryPanel();
		connectionPanel = ConnectionPanelController.getConnectionPanel();
		cameraControlPanel = new CameraControlPanel();
		robotControlPanel = new RobotControlPanel();
		
		setLayout(new MigLayout("", "[]", "[][]30[]70[]"));
		add(batteryPanel, "cell 0 1, alignx center");
		add(connectionPanel, "cell 0 2, alignx center");
		add(cameraControlPanel, "cell 0 3, alignx center");
		add(robotControlPanel, "cell 0 4, alignx center");
		
		
	}
}
