package org.projectsquirrel.views.robotViews;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

/**Draws a block diagram of the robot from the side based on 3 of the joint values */


public class RobotSkeletonSide extends JPanel{


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
	 * @param alpha		pitch angle of the ball joint
	 * @param beta 		pitch angle of the entire robot
	 * @param extend 	extension of the lead screw
	 * @param color		color to draw the image in
	 */
	RobotSkeletonSide(double robotAngle, double bendAngle, double extend, Color color){
		botHeight /= scale;
		topHeight /= scale;
		height /= scale;
		len /= scale;
		isTopAttached = true;
		update(robotAngle, bendAngle, extend, color);
	}
	
	public void update(double robotAngle, double bendAngle, double extend, Color color){
		//convert to radians and change direction
		this.robotAngle = Math.toRadians(90-robotAngle);
		this.bendAngle = Math.toRadians(-1*bendAngle);
		this.extend = extend/scale;
		this.color = color;
		repaint();
	}
	
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
	
	public void updateBranchDistances(float top, float bot){
		topBranchDistance = top;
		botBranchDistance = bot;
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

	/**
	 * Draws robot using the joint variables
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawTree(g);
		drawTopBranch(g);
		drawBotBranch(g);
		drawRobot(g);
	}
	
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
	
	private void drawTree(Graphics g){
		g.setColor(new Color(97, 65, 38));
		double treeAngle = -(isTopAttached? robotAngle + bendAngle: robotAngle) + Math.PI/2;
		double treeWidth = getWidth()/2;
		double treeHeight = getHeight() + 70;
		double xtreeOffset = (7 + getWidth()/4)*Math.cos(treeAngle) + getWidth()/2;
		double ytreeOffset = (7 + getWidth()/4)*Math.sin(treeAngle) + getHeight()/2;
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
	
	private void drawTopBranch(Graphics g){
		g.setColor(new Color(97, 65, 38));
		double branchAngle = -(isTopAttached? robotAngle + bendAngle: robotAngle) + Math.PI/2;
		double branchWidth = getWidth()/4;
		double branchHeight = getHeight()/70;
		double robotOffset = extend + topHeight + height/2;
		double topBranchScaledDistance = 4 + branchHeight/2 + robotOffset + topBranchDistance*(getHeight()/2 - robotOffset)/100;
		double xbranchOffset = (9 - branchWidth/2)*Math.cos(branchAngle) + topBranchScaledDistance*Math.sin(branchAngle) + getWidth()/2;
		double ybranchOffset = (9 - branchWidth/2)*Math.sin(branchAngle) - topBranchScaledDistance*Math.cos(branchAngle) + getHeight()/2;
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
	}
	
	private void drawBotBranch(Graphics g){
		g.setColor(new Color(97, 65, 38));
		double branchAngle = -(isTopAttached? robotAngle + bendAngle: robotAngle) + Math.PI/2;
		double branchWidth = getWidth()/4;
		double branchHeight = getHeight()/70;
		double robotOffset = botHeight + height/2; 
		double botBranchScaledDistance = 4 + branchHeight/2 + robotOffset + botBranchDistance*(getHeight()/2 - robotOffset)/100;
		double xbranchOffset = (9 - branchWidth/2)*Math.cos(branchAngle) - botBranchScaledDistance*Math.sin(branchAngle) + getWidth()/2;
		double ybranchOffset = (9 - branchWidth/2)*Math.sin(branchAngle) + botBranchScaledDistance*Math.cos(branchAngle) + getHeight()/2;
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
	}
	
}
