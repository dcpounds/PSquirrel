/**
 * 
 */
package org.projectsquirrel;

import java.awt.EventQueue;
import java.io.IOException;

import org.projectsquirrel.controllers.NetworkManager;
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
			NetworkManager.initialize("localhost", 10004);
		} catch (IOException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView mainWindow = new MainView();
					mainWindow.getFrame().setVisible(true);
					DebugWindow debugWindow = new DebugWindow();
					debugWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
