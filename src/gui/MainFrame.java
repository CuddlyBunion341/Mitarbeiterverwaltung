package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private boolean hrMode;
    private boolean adminMode;

    public MainFrame(boolean hrMode, boolean adminMode) {
        setTitle("Mitarbeiterverwaltung");
        setSize(600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();

        tabbedPane.addTab("Uebersicht", new OverviewTab());
        if (hrMode || adminMode) {
            tabbedPane.addTab("Zuordnung", new AssigmentTab());
            tabbedPane.addTab("Personen", new EmployeeTab());
            if (adminMode) {
                tabbedPane.addTab("Stammdaten", new DataTab());
                tabbedPane.addTab("LogBuch", new LogBookTab());
            }
        }
        
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
                //f.pack();
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