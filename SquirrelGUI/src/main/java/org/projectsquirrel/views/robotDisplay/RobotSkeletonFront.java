package org.projectsquirrel.views.robotDisplay;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class RobotSkeletonFront extends JPanel{

	double robotAngle;		//angle of entire robot
	double bendAngle;		//angle of middle segment
	double extend;		//extension of the screw
	double botHeight = 117; 	//distance to center of bottom segment of robot in mm
	double topHeight = 150; 	//distance to center of top segment of robot in mm
	double width = 92; 	//width of robot body in mm
	double height= 152;	//height of robot body in mm
	double scale = 3; 	//scale 1 -> 1 pixel/mm
	List<Integer> attachedClaws = new LinkedList<Integer>();
	Color color;

	RobotSkeletonFront(double robotAngle, double bendAngle, double extend, Color color){
		botHeight /= scale;
		topHeight /= scale;
		height /= scale;
		width /= scale;
		update(robotAngle, bendAngle, extend, color);
	}

	public void update(double robotAngle, double bendAngle, double extend, Color color){
		//convert to radians and change direction
		this.robotAngle = (90-robotAngle) * 3.14159/180;
		this.bendAngle = (-1*bendAngle) * 3.14159/180;
		this.extend = extend/scale;
		this.color = color;
		repaint();
	}

	public void updateClaws(List<Integer> attachedClaws){
		this.attachedClaws = attachedClaws;
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

		//x coordinate of center of ball joint
		double x2 = getWidth()/2; 	
		//x coordinate of center point of lower segment
		double x1 = x2-botHeight*Math.cos(robotAngle);	
		//x coordinate of center point of upper segment
		double x3 = x2+(topHeight+extend)*Math.cos(robotAngle+bendAngle);	

		//x coordinate of Front-left corner of upper segment 
		double x4 = x3-height/2*Math.cos(-1*(robotAngle+bendAngle))-width/2*Math.sin(-1*(robotAngle+bendAngle));
		//x coordinate of Front-right corner of upper segment 
		double x5 = x3+height/2*Math.cos(-1*(robotAngle+bendAngle))-width/2*Math.sin(-1*(robotAngle+bendAngle));
		//x coordinate of Back-right corner of upper segment 
		double x6 = x3+height/2*Math.cos(-1*(robotAngle+bendAngle))+width/2*Math.sin(-1*(robotAngle+bendAngle));
		//x coordinate of Back-left corner of upper segment 
		double x7 = x3-height/2*Math.cos(-1*(robotAngle+bendAngle))+width/2*Math.sin(-1*(robotAngle+bendAngle));

		//x coordinate of Front-left corner of lower segment 
		double x8 = x1-height/2*Math.cos(-1*robotAngle)-width/2*Math.sin(-1*robotAngle);
		//x coordinate of Front-right corner of lower segment 
		double x9 = x1+height/2*Math.cos(-1*robotAngle)-width/2*Math.sin(-1*robotAngle);
		//x coordinate of Back-right corner of lower segment 
		double x10 = x1+height/2*Math.cos(-1*robotAngle)+width/2*Math.sin(-1*robotAngle);
		//x coordinate of Back-left corner of lower segment 
		double x11 = x1-height/2*Math.cos(-1*robotAngle)+width/2*Math.sin(-1*robotAngle);


		//y coordinate of center of ball joint
		double y2 = getHeight()/2;
		//y coordinate of center point of lower segment
		double y1 = y2+botHeight*Math.sin(robotAngle);
		//y coordinate of center point of upper segment
		double y3 = y2-(topHeight+extend)*Math.sin(robotAngle+bendAngle);

		//y coordinate of Front-left corner of upper segment 
		double y4 = y3+width/2*Math.cos(-1*(robotAngle+bendAngle))-height/2*Math.sin(-1*(robotAngle+bendAngle));
		//y coordinate of Front-right corner of upper segment 
		double y5 = y3+width/2*Math.cos(-1*(robotAngle+bendAngle))+height/2*Math.sin(-1*(robotAngle+bendAngle));
		//y coordinate of Back-right corner of upper segment 
		double y6 = y3-width/2*Math.cos(-1*(robotAngle+bendAngle))+height/2*Math.sin(-1*(robotAngle+bendAngle));
		//y coordinate of Back-left corner of upper segment 
		double y7 = y3-width/2*Math.cos(-1*(robotAngle+bendAngle))-height/2*Math.sin(-1*(robotAngle+bendAngle));

		//y coordinate of Front-left corner of lower segment
		double y8 = y1+width/2*Math.cos(-1*robotAngle)-height/2*Math.sin(-1*robotAngle);
		//y coordinate of Front-right corner of lower segment 
		double y9 = y1+width/2*Math.cos(-1*robotAngle)+height/2*Math.sin(-1*robotAngle);
		//y coordinate of Back-right corner of lower segment 
		double y10 = y1-width/2*Math.cos(-1*robotAngle)+height/2*Math.sin(-1*robotAngle);
		//y coordinate of Back-left corner of lower segment 
		double y11 = y1-width/2*Math.cos(-1*robotAngle)-height/2*Math.sin(-1*robotAngle);

		g.setColor(new Color(97, 65, 38));
		g.fillRect(getWidth()/4, 0, getWidth()/2, getHeight());

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


		//claw contact indicators
		g.setColor(getClawColor(1));
		g.fillOval(xPoints1[0]-5, yPoints1[0]-5, 10, 10);
		g.setColor(getClawColor(2));
		g.fillOval(xPoints1[1]-5, yPoints1[1]-5, 10, 10);
		g.setColor(getClawColor(3));
		g.fillOval(xPoints1[2]-5, yPoints1[2]-5, 10, 10);
		g.setColor(getClawColor(4));
		g.fillOval(xPoints1[3]-5, yPoints1[3]-5, 10, 10);


		g.setColor(getClawColor(5));
		g.fillOval(xPoints2[0]-5, yPoints2[0]-5, 10, 10);
		g.setColor(getClawColor(6));
		g.fillOval(xPoints2[1]-5, yPoints2[1]-5, 10, 10);
		g.setColor(getClawColor(7));
		g.fillOval(xPoints2[2]-5, yPoints2[2]-5, 10, 10);
		g.setColor(getClawColor(8));
		g.fillOval(xPoints2[3]-5, yPoints2[3]-5, 10, 10);

	}

	private Color getClawColor(Integer claw){
		if(!attachedClaws.contains(claw)){
			return Color.RED;
		} else {
			return Color.GREEN;
		}
	}
}
