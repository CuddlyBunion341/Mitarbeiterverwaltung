package main.java.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.model.company.Company;

/**
 * AssignmentTab is a JPanel that contains all the components for the assignment tab.
 * This tab is used to create, change, and delete assignments.
 * @author Daniel Bengl
 */
public class DataTab extends JPanel {
    private JTextField companyNameField;
    private JList<String> departmentList;
    private JList<String> functionsList;
    private JList<String> teamsList;
    
    private ListEditPanel departmentPanel;
    private ListEditPanel functionsPanel;
    private ListEditPanel teamsPanel;

    private Company model;

    public DataTab(Company model) {
        super();
        this.model = model;
        init();
        build();
    }

    private void init() {
        companyNameField = new JTextField();
        departmentList = new JList<>(model.getDepartments());
        functionsList = new JList<>(model.getFunctions());
        teamsList = new JList<>(model.getTeams());

        departmentPanel = new ListEditPanel(departmentList);
        functionsPanel = new ListEditPanel(functionsList);
        teamsPanel = new ListEditPanel(teamsList);
    }

    private void build() {
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        topPanel.add(new JLabel("Firma: "));
        topPanel.add(companyNameField);
        JPanel bottomPanel = new JPanel(new GridLayout(3,2));
        bottomPanel.add(new JLabel("Abteilungen: "));
        bottomPanel.add(departmentPanel);
        bottomPanel.add(new JLabel("Funktionen: "));
        bottomPanel.add(functionsPanel);
        bottomPanel.add(new JLabel("Teams: "));
        bottomPanel.add(teamsPanel);
        add(topPanel, BorderLayout.PAGE_START);
        add(bottomPanel, BorderLayout.CENTER);
    }

    // getters

    public JTextField getCompanyNameField() {
        return this.companyNameField;
    }

    public JList<String> getDepartmentList() {
        return this.departmentList;
    }

    public JList<String> getFunctionsList() {
        return this.functionsList;
    }

    public JList<String> getTeamsList() {
        return this.teamsList;
    }

    public ListEditPanel getDepartmentPanel() {
        return this.departmentPanel;
    }

    public ListEditPanel getFunctionsPanel() {
        return this.functionsPanel;
    }

    public ListEditPanel getTeamsPanel() {
        return this.teamsPanel;
    }


}