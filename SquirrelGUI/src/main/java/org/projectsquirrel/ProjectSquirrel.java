/**
 * 
 */
package org.projectsquirrel;

import java.awt.EventQueue;
import java.io.IOException;

import org.projectsquirrel.controllers.NetworkManager;
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
					MainView window = new MainView();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
