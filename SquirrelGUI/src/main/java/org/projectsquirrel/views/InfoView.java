package org.projectsquirrel.views;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;

public class InfoView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434788151525363749L;

	public InfoView() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new MigLayout("",
				"[13%,grow][12%][12%][12%][12%][13%][13%][13%]",
				"[15%,grow][5%][20%][30%][30%]"));

		JLabel label = new JLabel("Battery");
		add(label, "flowy,cell 0 0,alignx left,aligny top");

		JProgressBar batteryCharge = new JProgressBar();
		batteryCharge.setMinimumSize(new Dimension(50, 14));
		batteryCharge.setStringPainted(true);
		batteryCharge.setValue(90);
		add(batteryCharge, "flowy,cell 0 0,alignx left");

		JLabel connectionTitleLbl = new JLabel("ConnectionStatus");
		add(connectionTitleLbl, "flowy,cell 3 0 5 1,alignx center,aligny top");

		JLabel connectionStatusLbl = new JLabel("Connected");
		connectionStatusLbl.setForeground(new Color(0, 255, 0));
		add(connectionStatusLbl, "flowy,cell 3 0 5 1,alignx center");

		JLabel rollLbl = new JLabel("Roll");
		add(rollLbl, "cell 0 2,alignx center,aligny bottom");

		JLabel pitchLbl = new JLabel("Pitch");
		add(pitchLbl, "cell 2 2,alignx center,aligny bottom");

		JLabel sonarLbl = new JLabel("Sonar");
		add(sonarLbl, "cell 4 2 2 1,alignx center,aligny bottom");

		JLabel clawLbl = new JLabel("Claws");
		add(clawLbl, "cell 7 2,alignx center,aligny bottom");

		JPanel rollDial = new JPanel();
		rollDial.setBackground(Color.GRAY);
		add(rollDial, "cell 0 3,grow");

		JPanel pitchDial = new JPanel();
		pitchDial.setBackground(Color.GRAY);
		add(pitchDial, "cell 2 3,grow");

		JLabel lblTop = new JLabel("Top:");
		add(lblTop, "flowy,cell 4 3,alignx right");

		JLabel topStatusLbl = new JLabel("Free");
		topStatusLbl.setForeground(Color.GREEN);
		add(topStatusLbl, "flowy,cell 5 3,alignx left");

		JLabel lblBottom = new JLabel("Bot:");
		add(lblBottom, "cell 4 3,alignx right");

		JLabel bottomStatusLbl = new JLabel("Blocked");
		bottomStatusLbl.setForeground(Color.RED);
		add(bottomStatusLbl, "cell 5 3,alignx left");

		JPanel topClawPanel = new JPanel();
		topClawPanel.setLayout(new MigLayout("", "[20%][20%][20%][20%][20%]",
				"[20%][20%][20%][20%][20%]"));
		JPanel topClaw1 = new JPanel();
		topClaw1.setBackground(Color.RED);
		topClawPanel.add(topClaw1, "grow, cell 2 1");
		JPanel topClaw2 = new JPanel();
		topClaw2.setBackground(Color.RED);
		topClawPanel.add(topClaw2, "grow, cell 1 2");
		JPanel topClaw3 = new JPanel();
		topClaw3.setBackground(Color.RED);
		topClawPanel.add(topClaw3, "grow, cell 3 2");
		JPanel topClaw4 = new JPanel();
		topClaw4.setBackground(Color.RED);
		topClawPanel.add(topClaw4, "grow, cell 2 3");
		add(topClawPanel, "cell 7 3");

		JPanel bottomClawPanel = new JPanel();
		bottomClawPanel.setLayout(new MigLayout("",
				"[20%][20%][20%][20%][20%]", "[20%][20%][20%][20%][20%]"));
		JPanel bottomClaw1 = new JPanel();
		bottomClaw1.setBackground(Color.RED);
		bottomClawPanel.add(bottomClaw1, "grow, cell 2 1");
		JPanel bottomClaw2 = new JPanel();
		bottomClaw2.setBackground(Color.RED);
		bottomClawPanel.add(bottomClaw2, "grow, cell 1 2");
		JPanel bottomClaw3 = new JPanel();
		bottomClaw3.setBackground(Color.RED);
		bottomClawPanel.add(bottomClaw3, "grow, cell 3 2");
		JPanel bottomClaw4 = new JPanel();
		bottomClaw4.setBackground(Color.RED);
		bottomClawPanel.add(bottomClaw4, "grow, cell 2 3");
		add(bottomClawPanel, "cell 7 4");

	}
}
