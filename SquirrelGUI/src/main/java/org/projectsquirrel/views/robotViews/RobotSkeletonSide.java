package org.projectsquirrel.views.robotViews;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author dave
 * View that contains the side robot graphic
 */
public class RobotSkeletonSide extends JPanel{

	private static final long serialVersionUID = 6314173508249085642L;
	double robotAngle;		//angle of entire robot
	double bendAngle;		//angle of middle segment
	double extend;		//extension of the screw
	double botHeight = 117; 	//distance to center of bottom segment of robot in mm
	double topHeight = 150; 	//distance to center of top segment of robot in mm
	double len = 98; //length of robot body in mm
	double height= 152; //height of robot body in mm
	double scale = 4; 	//scale: 1 -> 1 pixel/mm
	double topBranchDistance = 0;
	double botBranchDistance = 0;
	boolean isTopAttached;
	Color color;
	
	/**
	 * Constructs the initial view
	 * @param robotAngle - gamma value
	 * @param bendAngle - pitch value
	 * @param extend - extension of the robot
	 * @param color - color of the robot to be drawn
	 */
	RobotSkeletonSide(double robotAngle, double bendAngle, double extend, Color color){
		botHeight /= scale;
		topHeight /= scale;
		height /= scale;
		len /= scale;
		isTopAttached = true;
		update(robotAngle, bendAngle, extend, color);
	}
	
	/**
	 * Updates the position values for the robot
	 * @param robotAngle - alpha value
	 * @param bendAngle - yaw value
	 * @param extend - extension of the robot
	 * @param color - color of the robot to be drawn
	 */
	public void update(double robotAngle, double bendAngle, double extend, Color color){
		//convert to radians and change direction
		this.robotAngle = Math.toRadians(90-robotAngle);
		this.bendAngle = Math.toRadians(-1*bendAngle);
		this.extend = extend/scale;
		this.color = color;
		repaint();
	}
	
	/**
	 * Updates the  graphic to indicate which half of the robot is attached to the tree
	 * @param attachedClaws - IDs of claw sensors that are currently attached
	 */
	public void updateClaws(List<Integer> attachedClaws){
		int top = 0;
		int bot = 0;
		for(Integer claw : attachedClaws){
			if(claw <= 4){
				top++;
			} else {
				bot++;
			}
		}
		isTopAttached = top >= bot? true: false;
		repaint();
	}
	
	/**
	 * updates the robot graphic with any obstructions detects by the ultrasonic sensors
	 * note: if value is greater than 99 meaning there is no obstruction, it will artificially
	 *   raise the values internally to make sure the branch goes off the edge of the graphic
	 *   when the tree is at an angle
	 * @param top - distance to top obstruction
	 * @param bot - distance to bottom obstruction
	 */
	public void updateBranchDistances(float top, float bot){
		if(top >= 100){
			topBranchDistance = 170;
		} else {
			topBranchDistance = top;
		}
		if(bot >= 100){
			botBranchDistance = 170;
		} else {
			botBranchDistance = bot;
		}
		repaint();
	}
	
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
		drawTree(g);
		drawTopBranch(g);
		drawBotBranch(g);
		drawRobot(g);
	}
	
	/**
	 * draws the robot graphics
	 * @param g - graphics object passed by @paintComponent
	 */
	private void drawRobot(Graphics g){
		//x coordinate of center of ball joint
		double x2 = getWidth()/2; 	
		//x coordinate of center point of lower segment
		double x1 = x2-botHeight*Math.cos(robotAngle);	
		//x coordinate of center point of upper segment
		double x3 = x2+(topHeight+extend)*Math.cos(robotAngle+bendAngle);		

		//x coordinate of Front-left corner of upper segment 
		double x4 = x3-height/2*Math.cos(-1*(robotAngle+bendAngle))-len/2*Math.sin(-1*(robotAngle+bendAngle));
		//x coordinate of Front-right corner of upper segment 
		double x5 = x3+height/2*Math.cos(-1*(robotAngle+bendAngle))-len/2*Math.sin(-1*(robotAngle+bendAngle));
		//x coordinate of Back-right corner of upper segment 
		double x6 = x3+height/2*Math.cos(-1*(robotAngle+bendAngle))+len/2*Math.sin(-1*(robotAngle+bendAngle));
		//x coordinate of Back-left corner of upper segment 
		double x7 = x3-height/2*Math.cos(-1*(robotAngle+bendAngle))+len/2*Math.sin(-1*(robotAngle+bendAngle));

		//x coordinate of Front-left corner of lower segment 
		double x8 = x1-height/2*Math.cos(-1*robotAngle)-len/2*Math.sin(-1*robotAngle);
		//x coordinate of Front-right corner of lower segment 
		double x9 = x1+height/2*Math.cos(-1*robotAngle)-len/2*Math.sin(-1*robotAngle);
		//x coordinate of Back-right corner of lower segment 
		double x10 = x1+height/2*Math.cos(-1*robotAngle)+len/2*Math.sin(-1*robotAngle);
		//x coordinate of Back-left corner of lower segment 
		double x11 = x1-height/2*Math.cos(-1*robotAngle)+len/2*Math.sin(-1*robotAngle);

		//y coordinate of center of ball joint
		double y2 = getHeight()/2;
		//y coordinate of center point of lower segment
		double y1 = y2+botHeight*Math.sin(robotAngle);
		//y coordinate of center point of upper segment
		double y3 = y2-(topHeight+extend)*Math.sin(robotAngle+bendAngle);

		//y coordinate of Front-left corner of upper segment 
		double y4 = y3+len/2*Math.cos(-1*(robotAngle+bendAngle))-height/2*Math.sin(-1*(robotAngle+bendAngle));
		//y coordinate of Front-right corner of upper segment 
		double y5 = y3+len/2*Math.cos(-1*(robotAngle+bendAngle))+height/2*Math.sin(-1*(robotAngle+bendAngle));
		//y coordinate of Back-right corner of upper segment 
		double y6 = y3-len/2*Math.cos(-1*(robotAngle+bendAngle))+height/2*Math.sin(-1*(robotAngle+bendAngle));
		//y coordinate of Back-left corner of upper segment 
		double y7 = y3-len/2*Math.cos(-1*(robotAngle+bendAngle))-height/2*Math.sin(-1*(robotAngle+bendAngle));

		//y coordinate of Front-left corner of lower segment
		double y8 = y1+len/2*Math.cos(-1*robotAngle)-height/2*Math.sin(-1*robotAngle);
		//y coordinate of Front-right corner of lower segment 
		double y9 = y1+len/2*Math.cos(-1*robotAngle)+height/2*Math.sin(-1*robotAngle);
		//y coordinate of Back-right corner of lower segment 
		double y10 = y1-len/2*Math.cos(-1*robotAngle)+height/2*Math.sin(-1*robotAngle);
		//y coordinate of Back-left corner of lower segment 
		double y11 = y1-len/2*Math.cos(-1*robotAngle)-height/2*Math.sin(-1*robotAngle);

		g.setColor(color);
		int nPoints = 4;

		//upper segment
		int[] xPoints1 = {(int)x4,(int)x5,(int)x6,(int)x7};
		int[] yPoints1 = {(int)y4,(int)y5,(int)y6,(int)y7};
		g.fillPolygon(xPoints1, yPoints1, nPoints);

		//lower segment
		int[] xPoints2 = {(int)x8,(int)x9,(int)x10,(int)x11};
		int[] yPoints2 = {(int)y8,(int)y9,(int)y10,(int)y11};
		g.fillPolygon(xPoints2, yPoints2, nPoints);

		//middle segment
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		g.drawLine((int)x2, (int)y2, (int)x3, (int)y3);
	}
	
	/**
	 * draws the tree graphics
	 * @param g - graphics object passed by @paintComponent
	 */
	private void drawTree(Graphics g){
		g.setColor(new Color(97, 65, 38));
		double treeAngle = -(isTopAttached? robotAngle + bendAngle: robotAngle) + Math.PI/2;
		double treeWidth = getWidth()/2;
		double treeHeight = getHeight() + 70;
		
		//determine offset from center of robot (also equivalent to center of panel
		double xtreeOffset = (7 + getWidth()/4)*Math.cos(treeAngle) + getWidth()/2;
		double ytreeOffset = (7 + getWidth()/4)*Math.sin(treeAngle) + getHeight()/2;
		
		//determine points of corners of the tree
		double xTree0 = xtreeOffset - treeWidth/2*Math.cos(treeAngle) - treeHeight*Math.sin(treeAngle);
		double xTree1 = xtreeOffset + treeWidth/2*Math.cos(treeAngle) - treeHeight*Math.sin(treeAngle);
		double xTree2 = xtreeOffset + treeWidth/2*Math.cos(treeAngle) + treeHeight*Math.sin(treeAngle);
		double xTree3 = xtreeOffset - treeWidth/2*Math.cos(treeAngle) + treeHeight*Math.sin(treeAngle);

		double yTree0 = ytreeOffset - treeWidth/2*Math.sin(treeAngle) + treeHeight*Math.cos(treeAngle);
		double yTree1 = ytreeOffset + treeWidth/2*Math.sin(treeAngle) + treeHeight*Math.cos(treeAngle);
		double yTree2 = ytreeOffset + treeWidth/2*Math.sin(treeAngle) - treeHeight*Math.cos(treeAngle);
		double yTree3 = ytreeOffset - treeWidth/2*Math.sin(treeAngle) - treeHeight*Math.cos(treeAngle);
		

		int[] xPoints = {(int)xTree0,(int)xTree1,(int)xTree2,(int)xTree3};
		int[] yPoints = {(int)yTree0,(int)yTree1,(int)yTree2,(int)yTree3};
		g.fillPolygon(xPoints, yPoints, 4);
	}
	
	/**
	 * draws the top branch graphics
	 * @param g - graphics object passed by @paintComponent
	 */
	private void drawTopBranch(Graphics g){
		g.setColor(new Color(97, 65, 38));
		double branchAngle = -(isTopAttached? robotAngle + bendAngle: robotAngle) + Math.PI/2;
		double branchWidth = getWidth()/8;
		double branchHeight = getHeight()/40;
		double robotOffset = extend + topHeight + height/2;
		
		//scale the distance from 0 to 100 to the distance from the edge of the robot to the edge of the graphic
		double topBranchScaledDistance = 4 + branchHeight/2 + robotOffset + topBranchDistance*(getHeight()/2 - robotOffset)/100;
		
		//determine offset from center of robot (also equivalent to center of panel
		double xbranchOffset = (9 - branchWidth/2)*Math.cos(branchAngle) + topBranchScaledDistance*Math.sin(branchAngle) + getWidth()/2;
		double ybranchOffset = (9 - branchWidth/2)*Math.sin(branchAngle) - topBranchScaledDistance*Math.cos(branchAngle) + getHeight()/2;
		
		//determine points of corners of the branch
		double xBranch0 = xbranchOffset - branchWidth/2*Math.cos(branchAngle) - branchHeight*Math.sin(branchAngle);
		double xBranch1 = xbranchOffset + branchWidth/2*Math.cos(branchAngle) - branchHeight*Math.sin(branchAngle);
		double xBranch2 = xbranchOffset + branchWidth/2*Math.cos(branchAngle) + branchHeight*Math.sin(branchAngle);
		double xBranch3 = xbranchOffset - branchWidth/2*Math.cos(branchAngle) + branchHeight*Math.sin(branchAngle);

		double yBranch0 = ybranchOffset - branchWidth/2*Math.sin(branchAngle) + branchHeight*Math.cos(branchAngle);
		double yBranch1 = ybranchOffset + branchWidth/2*Math.sin(branchAngle) + branchHeight*Math.cos(branchAngle);
		double yBranch2 = ybranchOffset + branchWidth/2*Math.sin(branchAngle) - branchHeight*Math.cos(branchAngle);
		double yBranch3 = ybranchOffset - branchWidth/2*Math.sin(branchAngle) - branchHeight*Math.cos(branchAngle);
		

		int[] xPoints = {(int)xBranch0,(int)xBranch1,(int)xBranch2,(int)xBranch3};
		int[] yPoints = {(int)yBranch0,(int)yBranch1,(int)yBranch2,(int)yBranch3};
		g.fillPolygon(xPoints, yPoints, 4);
		
		try{
			BufferedImage warningIcon = ImageIO.read(new File("src/resources/warning.png"));
			int imageScaledWidth = (int)(branchHeight*3);
			g.drawImage(warningIcon, 
					-imageScaledWidth/2 + (int)xbranchOffset, 
					-imageScaledWidth/2 + (int)ybranchOffset, 
					imageScaledWidth, 
					imageScaledWidth, 
					null);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * draws the bottom branch graphics
	 * @param g - graphics object passed by @paintComponent
	 */
	private void drawBotBranch(Graphics g){
		g.setColor(new Color(97, 65, 38));
		double branchAngle = -(isTopAttached? robotAngle + bendAngle: robotAngle) + Math.PI/2;
		double branchWidth = getWidth()/8;
		double branchHeight = getHeight()/40;
		double robotOffset = botHeight + height/2; 
		
		//scale the distance from 0 to 100 to the distance from the edge of the robot to the edge of the graphic
		double botBranchScaledDistance = 4 + branchHeight/2 + robotOffset + botBranchDistance*(getHeight()/2 - robotOffset)/100;
		
		//determine offset from center of robot (also equivalent to center of panel
		double xbranchOffset = (9 - branchWidth/2)*Math.cos(branchAngle) - botBranchScaledDistance*Math.sin(branchAngle) + getWidth()/2;
		double ybranchOffset = (9 - branchWidth/2)*Math.sin(branchAngle) + botBranchScaledDistance*Math.cos(branchAngle) + getHeight()/2;
		
		//determine points of corners of the branch
		double xBranch0 = xbranchOffset - branchWidth/2*Math.cos(branchAngle) - branchHeight*Math.sin(branchAngle);
		double xBranch1 = xbranchOffset + branchWidth/2*Math.cos(branchAngle) - branchHeight*Math.sin(branchAngle);
		double xBranch2 = xbranchOffset + branchWidth/2*Math.cos(branchAngle) + branchHeight*Math.sin(branchAngle);
		double xBranch3 = xbranchOffset - branchWidth/2*Math.cos(branchAngle) + branchHeight*Math.sin(branchAngle);

		double yBranch0 = ybranchOffset - branchWidth/2*Math.sin(branchAngle) + branchHeight*Math.cos(branchAngle);
		double yBranch1 = ybranchOffset + branchWidth/2*Math.sin(branchAngle) + branchHeight*Math.cos(branchAngle);
		double yBranch2 = ybranchOffset + branchWidth/2*Math.sin(branchAngle) - branchHeight*Math.cos(branchAngle);
		double yBranch3 = ybranchOffset - branchWidth/2*Math.sin(branchAngle) - branchHeight*Math.cos(branchAngle);
		

		int[] xPoints = {(int)xBranch0,(int)xBranch1,(int)xBranch2,(int)xBranch3};
		int[] yPoints = {(int)yBranch0,(int)yBranch1,(int)yBranch2,(int)yBranch3};
		g.fillPolygon(xPoints, yPoints, 4);

		try{
			BufferedImage warningIcon = ImageIO.read(new File("src/resources/warning.png"));
			int imageScaledWidth = (int)(branchHeight*3);
			g.drawImage(warningIcon, 
					-imageScaledWidth/2 + (int)xbranchOffset, 
					-imageScaledWidth/2 + (int)ybranchOffset, 
					imageScaledWidth, 
					imageScaledWidth, 
					null);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
