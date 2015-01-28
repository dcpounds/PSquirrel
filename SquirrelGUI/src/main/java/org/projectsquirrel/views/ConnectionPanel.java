/**
 * 
 */
package org.projectsquirrel.views;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import net.miginfocom.swing.MigLayout;

/**
 * @author dave
 *
 */
public class ConnectionPanel extends JPanel {
	

	private JLabel lblConnected;
	
	public ConnectionPanel(){
		JLabel lblConnectionStatus = new JLabel("Connection Status");
		lblConnected = new JLabel();
		update(false);
		
		setLayout(new MigLayout("", "[]", "[][]"));
		add(lblConnectionStatus, "cell 0 0,alignx center");
		add(lblConnected, "cell 0 1, alignx center");
	}
	
	public void update(boolean isConnected){
		if(isConnected){
			lblConnected.setText("Connected");
			lblConnected.setForeground(Color.GREEN);
		} else {
			lblConnected.setText("Not Connected");
			lblConnected.setForeground(Color.RED);
		}
	}
}
