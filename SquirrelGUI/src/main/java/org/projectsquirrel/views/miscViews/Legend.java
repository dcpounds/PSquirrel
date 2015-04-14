package org.projectsquirrel.views.miscViews;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author dave
 * View that contains legend for the robot graphic
 */
public class Legend extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Overridden method to prevent sizing issues
	 * 
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}

	/**
	 * Is called internally by the GUI to update the graphics
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g); 

		g.drawString("Legend:", 10, 15);
		g.setColor(Color.GREEN);
		g.fillOval(10, 35, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString(": Attached Claw", 30, 40);
		g.setColor(Color.RED);
		g.fillOval(10, 50, 10, 10);
		g.setColor(Color.BLACK);
		g.drawString(": Dettached Claw", 30, 60);
		
		try{
			BufferedImage warningIcon = ImageIO.read(new File("src/resources/warning.png"));
			g.drawImage(warningIcon, 10, 70, 15, 15, null);
			g.drawString(": Obstruction Detected", 30, 80);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
