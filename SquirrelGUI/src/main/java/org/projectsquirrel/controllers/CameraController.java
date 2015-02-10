/**
 * 
 */
package org.projectsquirrel.controllers;

import java.awt.image.BufferedImage;
import java.util.List;

import org.projectsquirrel.views.BatteryPanel;
import org.projectsquirrel.views.CameraPanel;
import org.projectsquirrel.views.SonarPanel;
import org.projectsquirrel.views.robotDisplay.RobotPanel;

/**
 * @author dave
 *
 */
public class CameraController {
	
	private static CameraController instance = new CameraController();
	private static CameraPanel cameraPanel = new CameraPanel();
	
	private CameraController(){
	}
	
	public static CameraController getInstace(){
		return instance;
	}
	
	public static CameraPanel getCameraPanel(){
		return cameraPanel;
	}
	
	public static void updateCameraPanel(BufferedImage image){
		cameraPanel.update(image);
	}
	
	
}
