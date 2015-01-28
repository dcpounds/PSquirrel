package org.projectsquirrel.views;

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
import org.projectsquirrel.controllers.RobotPanelController;
import org.projectsquirrel.controllers.SonarPanelController;
import org.projectsquirrel.views.robotDisplay.RobotPanel;

public class InfoView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434788151525363749L;
    private JProgressBar sonarTop;
    private JProgressBar sonarBot;

	public InfoView() {
		SonarPanel sonarPanel = SonarPanelController.getSonarPanel();
		RobotPanel robotPanel = RobotPanelController.getRobotPanel();

		setLayout(new MigLayout("", "[][]", "[]"));
		
		add(sonarPanel, "cell 0 0");
		add(robotPanel, "cell 1 0");

	}
}
