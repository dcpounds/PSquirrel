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
import org.projectsquirrel.views.robotDisplay.RobotPanel;

public class InfoView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434788151525363749L;
	
	private DefaultValueDataset dataset1;
    private DefaultValueDataset dataset2;
    private JProgressBar sonarTop;
    private JProgressBar sonarBot;

	public InfoView() {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setLayout(new MigLayout("", "[14]0[grow]", "[][][][]"));

		JLabel label = new JLabel("Sonar");
		add(label, "flowy,cell 0 0,alignx center,aligny top");
		
		sonarTop = new JProgressBar();
		sonarTop.setSize(new Dimension(50, 14));
		sonarTop.setOrientation(SwingConstants.VERTICAL);
		sonarTop.setValue(90);
		add(sonarTop, "flowy,cell 0 1, alignx center, aligny bottom");
		
		JPanel sonarRobotPlaceHolder = new JPanel();
		sonarRobotPlaceHolder.setBackground(Color.BLACK);
		sonarTop.setSize(new Dimension(14, 14));
		add(sonarRobotPlaceHolder, "flowy,cell 0 2, alignx center, aligny center");
		
		sonarBot = new JProgressBar();
		sonarBot.setSize(new Dimension(50, 14));
		sonarBot.setOrientation(SwingConstants.VERTICAL);
		sonarBot.setValue(-90);
		add(sonarBot, "flowy,cell 0 3, alignx center, aligny top");

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(new Color(0, 0, 0));
		add(separator_1, "cell 1 1 1 4,alignx center,growy");
	

		RobotPanel robotPanel = RobotPanelController.getRobotPanel();
		robotPanel.setVisible(true);
		add(robotPanel, "cell 2 0 2 4");

	}
}
