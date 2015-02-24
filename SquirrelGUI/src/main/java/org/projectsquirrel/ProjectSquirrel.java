/**
 * 
 */
package org.projectsquirrel;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.projectsquirrel.controllers.CameraController;
import org.projectsquirrel.controllers.NetworkManager;
import org.projectsquirrel.controllers.NetworkUninitializedException;
import org.projectsquirrel.debug.DebugWindow;
import org.projectsquirrel.views.MainView;

/**
 * @author dave 
 *
 */
public class ProjectSquirrel {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NetworkManager.initialize("localhost", 9003, 9004);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Thread cameraThread = new Thread(){
			public void run() {
				BufferedImage imageBuf;
				while(true){
					try {
						imageBuf = NetworkManager.receiveCameraPacket();
						if(imageBuf != null){
							CameraController.updateCameraPanel(imageBuf);
							System.out.println("received: " + imageBuf.toString());
						} else {
							System.out.println("no luck");
						}
						Thread.sleep(200);
					} catch (IOException | NetworkUninitializedException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		try {
			MainView mainWindow = new MainView();
			mainWindow.getFrame().setVisible(true);
			DebugWindow debugWindow = new DebugWindow();
			debugWindow.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cameraThread.setDaemon(true);
		cameraThread.start();
		
		
	}
}
