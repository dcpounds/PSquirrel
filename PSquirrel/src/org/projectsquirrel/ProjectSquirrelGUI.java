package org.projectsquirrel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;


public class ProjectSquirrelGUI {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProjectSquirrelGUI window = new ProjectSquirrelGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ProjectSquirrelGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 942, 596);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[50%][50%]", "[66%][34%]"));
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, "cell 0 0 2 1,grow");
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        frame.getContentPane().add(panel_1, "cell 0 1,grow");
        panel_1.setLayout(new MigLayout("", "[]", "[]"));
        
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        frame.getContentPane().add(panel_2, "cell 1 1,grow");
        panel_2.setLayout(new MigLayout("", "[16%][16%][17%][17%][17%][17%]", "[20%][20%][20%][20%][20%]"));
        
        JLabel lblCameraControls = new JLabel("Camera Controls");
        lblCameraControls.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel_2.add(lblCameraControls, "cell 3 0 3 1,alignx center,aligny center");
        
        JToggleButton tglbtnOn = new JToggleButton("On");
        panel_2.add(tglbtnOn, "cell 3 1,grow");
        
        JToggleButton tglbtnOff = new JToggleButton("Off");
        panel_2.add(tglbtnOff, "cell 5 1,grow");
        
        JButton btnNewButton_2 = new JButton("Left");
        btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        JButton up = new JButton("Up");
        panel_2.add(up, "cell 4 3,grow");
        
        JButton btnNewButton_5 = new JButton("Stop Robot");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel_2.add(btnNewButton_5, "flowx,cell 0 3 2 2,grow");
        panel_2.add(btnNewButton_2, "cell 3 4,grow");
        
        JButton btnNewButton_1 = new JButton("Down");
        panel_2.add(btnNewButton_1, "cell 4 4,grow");
        
        JButton btnNewButton = new JButton("Right");
        panel_2.add(btnNewButton, "cell 5 4,grow");
    }

}
