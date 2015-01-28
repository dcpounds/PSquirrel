package org.projectsquirrel.views.robotDisplay;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

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
	double scale = 3; 	//scale: 1 -> 1 pixel/mm
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
}
