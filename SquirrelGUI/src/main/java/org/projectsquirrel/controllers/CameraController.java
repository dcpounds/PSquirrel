/**
 * 
 */
package org.projectsquirrel.controllers;

import java.awt.image.BufferedImage;
import java.util.List;

import org.projectsquirrel.views.cameraViews.CameraPanel;
import org.projectsquirrel.views.controlViews.BatteryPanel;
import org.projectsquirrel.views.robotViews.RobotPanel;

/**
 * @author dave
 * 
 * Singleton for updating the camera image
 *
 */
public class CameraController {
	
	private static CameraController instance = new CameraController();
	private static CameraPanel cameraPanel = new CameraPanel();
	
	/**
	 * constructor is private because class is a singleton
	 */
	private CameraController(){
	}
	
	/**
	 * get the one instance of the controller
	 * @return
	 */
	public static CameraController getInstace(){
		return instance;
	}
	

	/**
	 * get the camera panel view associated with this controller
	 * @return
	 */
	public static CameraPanel getCameraPanel(){
		return cameraPanel;
	}
	
	/**
	 * Updates the camera panel with the given image
	 * @param image - image to update the view with
	 */
	public static void updateCameraPanel(BufferedImage image){
		cameraPanel.update(image);
	}
	
	
}
