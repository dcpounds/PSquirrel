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
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer.Pin;
import org.jfree.chart.plot.dial.DialPointer.Pointer;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class InfoView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434788151525363749L;
	
	DefaultValueDataset dataset1;
    DefaultValueDataset dataset2;

	public InfoView() {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setLayout(new MigLayout("", "[][grow][grow][grow][grow]0[grow]", "[grow][grow][grow]0[grow]0[grow]"));

		JLabel label = new JLabel("Battery");
		add(label, "flowy,cell 0 0,alignx left,aligny top");

		JProgressBar batteryCharge = new JProgressBar();
		batteryCharge.setMinimumSize(new Dimension(50, 14));
		batteryCharge.setStringPainted(true);
		batteryCharge.setValue(90);
		add(batteryCharge, "flowy,cell 0 0,alignx left");

		JLabel clawLbl = new JLabel("Claws Engaged");
		add(clawLbl, "cell 0 1,alignx center,aligny bottom");

		JLabel sonarLbl = new JLabel("Sonar");
		add(sonarLbl, "cell 2 1,alignx center,aligny bottom");

		JPanel topClawPanel = new JPanel();
		topClawPanel.setBackground(Color.LIGHT_GRAY);
		topClawPanel.setLayout(new MigLayout("", "[grow]15px[grow]15px[grow]", "[grow]15px[grow]15px[grow]"));
		JPanel topClaw3 = new JPanel();
		topClaw3.setBackground(Color.GREEN);
		topClawPanel.add(topClaw3, "cell 0 0,grow");
		JPanel topClaw2 = new JPanel();
		topClaw2.setBackground(Color.GREEN);
		topClawPanel.add(topClaw2, "cell 2 0,grow");

		JPanel topClawMidSegment = new JPanel();
		topClawMidSegment.setBackground(Color.GREEN);
		topClawPanel.add(topClawMidSegment, "cell 1 1,grow");
		JPanel topClaw1 = new JPanel();
		topClaw1.setBackground(Color.GREEN);
		topClawPanel.add(topClaw1, "cell 0 2,grow");
		JPanel topClaw4 = new JPanel();
		topClaw4.setBackground(Color.GREEN);
		topClawPanel.add(topClaw4, "cell 2 2,grow");
		add(topClawPanel, "cell 0 2,grow");

		JPanel sonarTopPanel = new JPanel();
		add(sonarTopPanel, "cell 2 2,grow");
		sonarTopPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow][grow]"));

		JPanel longestRangeSonarPanel = new JPanel();
		longestRangeSonarPanel.setBackground(new Color(204, 255, 204));
		sonarTopPanel.add(longestRangeSonarPanel, "cell 0 0 6 1,grow");

		JPanel midRangeSonarPanel = new JPanel();
		midRangeSonarPanel.setBackground(new Color(51, 255, 51));
		sonarTopPanel.add(midRangeSonarPanel, "cell 1 1 3 1,grow");

		JPanel shortRangeSonarPanel = new JPanel();
		shortRangeSonarPanel.setBackground(new Color(0, 204, 0));
		sonarTopPanel.add(shortRangeSonarPanel, "cell 2 2,grow");
		
		/* Dial Plot Stuff */
		
		DialPlot topMotorDial = new DialPlot();
		dataset1 = new DefaultValueDataset(10D);
        dataset2 = new DefaultValueDataset(50D);
        topMotorDial.setView(0.0D, 0.0D, 1.0D, 0.6D);
        topMotorDial.setDataset(0, dataset1);
        topMotorDial.setDataset(1, dataset2);
        StandardDialFrame standarddialframe = new StandardDialFrame();
        standarddialframe.setBackgroundPaint(Color.lightGray);
        standarddialframe.setForegroundPaint(Color.darkGray);
        topMotorDial.setDialFrame(standarddialframe);
        GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
        DialBackground dialbackground = new DialBackground(gradientpaint);
        
        dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        topMotorDial.setBackground(dialbackground);
        
        DialTextAnnotation dialtextannotation = new DialTextAnnotation("Top Claw Angle");
        dialtextannotation.setFont(new Font("Dialog", 1, 14));
        dialtextannotation.setRadius(0.05D);
        topMotorDial.addLayer(dialtextannotation);
        
        StandardDialScale standarddialscale = new StandardDialScale(-25D, 25D, 180D, -180D, 5D, 4);
        standarddialscale.setTickRadius(0.88D);
        standarddialscale.setTickLabelOffset(0.15D);
        standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
        topMotorDial.addScale(0, standarddialscale);
        
        topMotorDial.mapDatasetToScale(1, 1);
        
        Pin pin = new Pin(0);
        pin.setRadius(0.55D);
        topMotorDial.addPointer(pin);
        
        Pointer pointer = new Pointer(0);
        topMotorDial.addPointer(pointer);
        
        DialCap dialcap = new DialCap();
        dialcap.setRadius(0.025D);
        topMotorDial.setCap(dialcap);
        
        JFreeChart jfreechart = new JFreeChart(topMotorDial);
        ChartPanel motorPanel = new ChartPanel(jfreechart);
		motorPanel.setPreferredSize(new Dimension(200, 200));
		add(motorPanel, "cell 4 1 1 2,grow");
        
        /* End Dial Plot Stuff */
		
		/* Dial Plot Stuff */
		DialPlot botMotorDial = new DialPlot();
		dataset1 = new DefaultValueDataset(-10D);
        dataset2 = new DefaultValueDataset(50D);
        botMotorDial.setView(0.0D, 0.0D, 1.0D, 0.6D);
        botMotorDial.setDataset(0, dataset1);
        botMotorDial.setDataset(1, dataset2);
        StandardDialFrame standarddialframe1 = new StandardDialFrame();
        standarddialframe1.setBackgroundPaint(Color.lightGray);
        standarddialframe1.setForegroundPaint(Color.darkGray);
        botMotorDial.setDialFrame(standarddialframe1);
        GradientPaint gradientpaint1 = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
        DialBackground dialbackground1 = new DialBackground(gradientpaint1);
        
        dialbackground1.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        botMotorDial.setBackground(dialbackground1);
        
        DialTextAnnotation dialtextannotation1 = new DialTextAnnotation("Bottom Claw Angle");
        dialtextannotation1.setFont(new Font("Dialog", 1, 14));
        dialtextannotation1.setRadius(0.05D);
        botMotorDial.addLayer(dialtextannotation1);
        
        StandardDialScale standarddialscale1 = new StandardDialScale(-25D, 25D, 180D, -180D, 5D, 4);
        standarddialscale1.setTickRadius(0.88D);
        standarddialscale1.setTickLabelOffset(0.15D);
        standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 14));
        botMotorDial.addScale(0, standarddialscale1);
        
        botMotorDial.mapDatasetToScale(1, 1);
        
        Pin pin1 = new Pin(0);
        pin1.setRadius(0.55D);
        botMotorDial.addPointer(pin1);
        
        Pointer pointer1 = new Pointer(0);
        botMotorDial.addPointer(pointer1);
        
        DialCap dialcap1 = new DialCap();
        dialcap1.setRadius(0.025D);
        botMotorDial.setCap(dialcap1);
        
        JFreeChart jfreechart2 = new JFreeChart(botMotorDial);
        ChartPanel motorPanel2 = new ChartPanel(jfreechart2);
		motorPanel2.setPreferredSize(new Dimension(200, 200));
		add(motorPanel2, "cell 5 1 1 2,grow");

		/* End Dial Plot Stuff */
		
		JPanel middleSegmentPanel = new JPanel();
		add(middleSegmentPanel, "cell 0 3,grow");
		middleSegmentPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow]"));

		JPanel screwPanel = new JPanel();
		screwPanel.setBackground(Color.LIGHT_GRAY);
		middleSegmentPanel.add(screwPanel, "cell 1 0,grow");

		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setBorder(null);
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "cell 1 1 1 4,alignx center,growy");

		JPanel sonarRobotPanel = new JPanel();
		sonarRobotPanel.setBackground(UIManager.getColor("Button.background"));
		add(sonarRobotPanel, "cell 2 3,grow");
		sonarRobotPanel.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][grow]"));

		JPanel topSegment = new JPanel();
		topSegment.setBackground(Color.LIGHT_GRAY);
		sonarRobotPanel.add(topSegment, "cell 0 0 3 1,grow");
		
		JPanel midSegment = new JPanel();
		midSegment.setBackground(Color.LIGHT_GRAY);
		sonarRobotPanel.add(midSegment, "cell 1 1,grow");

		JPanel botSegment = new JPanel();
		botSegment.setBackground(Color.LIGHT_GRAY);
		sonarRobotPanel.add(botSegment, "cell 0 2 3 1,grow");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(new Color(0, 0, 0));
		add(separator_1, "cell 3 1 1 4,alignx center,growy");
		
		JPanel currentProgressPanel = new JPanel();
		add(currentProgressPanel, "cell 5 3 1 2,growx,aligny center");
		currentProgressPanel.setLayout(new MigLayout("", "[146px]", "[14px][][][][]"));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(60);
		currentProgressPanel.add(progressBar, "cell 0 0,alignx left,aligny top");
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setValue(30);
		currentProgressPanel.add(progressBar_1, "cell 0 1");
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setValue(40);
		currentProgressPanel.add(progressBar_2, "cell 0 2");
		
		JProgressBar progressBar_3 = new JProgressBar();
		currentProgressPanel.add(progressBar_3, "cell 0 3");
		
		JProgressBar progressBar_4 = new JProgressBar();
		progressBar_4.setForeground(Color.RED);
		progressBar_4.setValue(90);
		currentProgressPanel.add(progressBar_4, "cell 0 4");

		JPanel bottomClawPanel = new JPanel();
		bottomClawPanel.setBackground(Color.LIGHT_GRAY);
		bottomClawPanel.setLayout(new MigLayout("", "[grow]15px[grow]15px[grow]", "[grow]15px[grow]15px[grow]"));
		JPanel bottomClaw1 = new JPanel();
		bottomClaw1.setBackground(Color.RED);
		bottomClawPanel.add(bottomClaw1, "cell 0 0,grow");
		JPanel bottomClaw2 = new JPanel();
		bottomClaw2.setBackground(Color.RED);
		bottomClawPanel.add(bottomClaw2, "cell 2 0,grow");

		JPanel bottomClawMidSegment = new JPanel();
		bottomClawMidSegment.setBackground(Color.RED);
		bottomClawPanel.add(bottomClawMidSegment, "cell 1 1,grow");
		JPanel bottomClaw4 = new JPanel();
		bottomClaw4.setBackground(Color.RED);
		bottomClawPanel.add(bottomClaw4, "cell 0 2,grow");
		JPanel bottomClaw3 = new JPanel();
		bottomClaw3.setBackground(Color.RED);
		bottomClawPanel.add(bottomClaw3, "cell 2 2,grow");
		add(bottomClawPanel, "cell 0 4,grow");

		JPanel sonarBottomPanel = new JPanel();
		add(sonarBottomPanel, "cell 2 4,grow");
		sonarBottomPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow][grow]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 153));
		sonarBottomPanel.add(panel, "cell 2 0,grow");

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 102, 102));
		sonarBottomPanel.add(panel_1, "cell 1 1 3 1,grow");

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 0));
		sonarBottomPanel.add(panel_2, "cell 0 2 5 1,grow");
		
		JPanel currentLabelPanel = new JPanel();
		add(currentLabelPanel, "cell 4 3 1 2,growx,aligny center");
		currentLabelPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JLabel lblScrewMotorCurrent = new JLabel("Screw Motor Current");
		currentLabelPanel.add(lblScrewMotorCurrent, "flowy,cell 0 0,alignx right,aligny top");
		
		JLabel lblYawMotor = new JLabel("Yaw Motor Current");
		currentLabelPanel.add(lblYawMotor, "cell 0 0,alignx right");
		
		JLabel lblPitchMotor = new JLabel("Pitch Motor Current");
		currentLabelPanel.add(lblPitchMotor, "cell 0 0,alignx right");
		
		JLabel lblTopClawMotor = new JLabel("Top Claw Motor Current");
		currentLabelPanel.add(lblTopClawMotor, "cell 0 0,alignx right");
		
		JLabel lblBotClawMotor = new JLabel("Bot Claw Motor Current");
		currentLabelPanel.add(lblBotClawMotor, "cell 0 0,alignx right");

	}
}
