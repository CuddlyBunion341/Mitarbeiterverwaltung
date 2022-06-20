package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.company.Company;

public class DataTab extends JPanel {
    private JTextField companyNameField;
    private JList departmentList;
    private JList functionsList;
    private JList teamsList;
    
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

    public JList getDepartmentList() {
        return this.departmentList;
    }

    public JList getFunctionsList() {
        return this.functionsList;
    }

    public JList getTeamsList() {
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