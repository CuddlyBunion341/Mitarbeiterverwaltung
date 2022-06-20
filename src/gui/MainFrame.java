package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.company.Company;

import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private boolean hrMode;
    private boolean adminMode;
    private Company model;

    public MainFrame(Company model,boolean hrMode, boolean adminMode) {
        this.hrMode = hrMode;
        this.adminMode = adminMode;
        this.model = model;

        setTitle("Mitarbeiterverwaltung");
        setSize(600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();

        tabbedPane.addTab("Uebersicht", new OverviewTab(model));
        if (hrMode || adminMode) {
            tabbedPane.addTab("Zuordnung", new AssigmentTab(model));
            tabbedPane.addTab("Personen", new EmployeeTab(model));
            if (adminMode) {
                tabbedPane.addTab("Stammdaten", new DataTab(model));
                tabbedPane.addTab("LogBuch", new LogBookTab(model));
            }
        }
        
        pack();
        setVisible(true);
    }

    private void init() {
        tabbedPane = new JTabbedPane();
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