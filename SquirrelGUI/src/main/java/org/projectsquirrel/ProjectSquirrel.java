/**
 * 
 */
package org.projectsquirrel;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.projectsquirrel.GUIdebug.DebugWindow;
import org.projectsquirrel.controllers.CameraController;
import org.projectsquirrel.network.CameraSocketManager;
import org.projectsquirrel.network.NetworkUninitializedException;
import org.projectsquirrel.network.SocketManager;
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
		/*try {
			String ip = "localhost";
			SocketManager.initialize(ip, 9003);
			CameraSocketManager.initialize(ip, 9004);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		Thread cameraThread = new Thread(){
			public void run() {
				BufferedImage imageBuf;
				while(true){
					try {
						imageBuf = CameraSocketManager.receiveCameraPacket();
						if(imageBuf != null){
							CameraController.updateCameraPanel(imageBuf);
						}
					} catch (IOException | NetworkUninitializedException e) {
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
		//cameraThread.start();
		
		
	}
}
