package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;

    public MainFrame() {
        setTitle("Hello World");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        pack();
        setVisible(true);
    }

    private void init() {
        tabbedPane = new JTabbedPane();
        JFrame f = this;
        tabbedPane.setForeground(Color.BLACK);

        tabbedPane.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent arg0) {
                Component mCompo=tabbedPane.getSelectedComponent();
                tabbedPane.setPreferredSize(mCompo.getPreferredSize());
                f.pack();
            }   
        });
        add(tabbedPane);
        getContentPane().add(tabbedPane);
    }

    // getters
    
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}