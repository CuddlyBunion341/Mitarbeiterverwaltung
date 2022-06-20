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
    private OverviewTab overviewTab;
    private AssigmentTab assigmentTab;
    private EmployeeTab employeeTab;
    private DataTab dataTab;
    private LogBookTab logBookTab;

    public MainFrame(Company model,boolean hrMode, boolean adminMode) {
        this.hrMode = hrMode;
        this.adminMode = adminMode;
        this.model = model;

        setTitle("Mitarbeiterverwaltung");
        setSize(600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();

        overviewTab = new OverviewTab(model);
        tabbedPane.addTab("Uebersicht", overviewTab);
        if (hrMode || adminMode) {
            assigmentTab = new AssigmentTab(model);
            tabbedPane.addTab("Zuordnung", assigmentTab);
            employeeTab = new EmployeeTab(model);
            tabbedPane.addTab("Personen", employeeTab);
            if (adminMode) {
                dataTab = new DataTab(model);
                tabbedPane.addTab("Stammdaten", dataTab);
                logBookTab = new LogBookTab(model);
                tabbedPane.addTab("LogBuch", logBookTab);
            }
        }
        
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


    public boolean getHrMode() {
        return this.hrMode;
    }

    public boolean isHrMode() {
        return this.hrMode;
    }

    public boolean getAdminMode() {
        return this.adminMode;
    }

    public boolean isAdminMode() {
        return this.adminMode;
    }

    public Company getModel() {
        return this.model;
    }

    public OverviewTab getOverviewTab() {
        return this.overviewTab;
    }

    public AssigmentTab getAssigmentTab() {
        return this.assigmentTab;
    }

    public EmployeeTab getEmployeeTab() {
        return this.employeeTab;
    }

    public DataTab getDataTab() {
        return this.dataTab;
    }

    public LogBookTab getLogBookTab() {
        return this.logBookTab;
    }

}