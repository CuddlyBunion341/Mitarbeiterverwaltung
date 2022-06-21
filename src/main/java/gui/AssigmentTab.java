package main.java.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.model.company.Company;
import main.java.model.company.Person;

/**
 * AssignmentTab is a JPanel that contains all the components for the assignment tab.
 * This tab is used to create, change, and delete assignments.
 * @author Daniel Bengl
 */
public class AssigmentTab extends JPanel {
    private JList<Person> employeeList;
    private JTextField nameField;
    private JTextField departmentField;
    private JComboBox<String> functionsBox;
    private JComboBox<String> teamsBox;
    private Company model;

    public AssigmentTab(Company model) {
        super();
        this.model = model;
        init();
        build();
    }

    private void init() {
        employeeList = new JList<>(model.getEmployees());
        nameField = new JTextField();
        departmentField = new JTextField();
        functionsBox = new JComboBox<>(model.getFunctions());
        teamsBox = new JComboBox<>(model.getTeams());
    }

    private void build() {
        setLayout(new BorderLayout());
        JPanel mainPanel = Util.fieldset("Personen bearbeiten");
        mainPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.add(Util.scrollPane("Übersicht", employeeList));
        mainPanel.add(leftPanel, BorderLayout.WEST);
        
        JPanel detailsPanel = Util.fieldset("Detail");
        detailsPanel.setLayout(new BorderLayout());
        JPanel detailsContentPanel = new JPanel(new GridLayout(4,2));
        detailsContentPanel.add(new JLabel("Name: "));
        detailsContentPanel.add(nameField);
        detailsContentPanel.add(new JLabel("Abteilung: "));
        detailsContentPanel.add(departmentField);
        detailsContentPanel.add(new JLabel("Funktion: "));  
        detailsContentPanel.add(functionsBox);
        detailsContentPanel.add(new JLabel("Team: "));
        detailsContentPanel.add(teamsBox);
        detailsPanel.add(detailsContentPanel, BorderLayout.PAGE_START);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    // getters

    public JList<Person> getEmployeeList() {
        return this.employeeList;
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JTextField getDepartmentField() {
        return this.departmentField;
    }

    public JComboBox<String> getFunctionsBox() {
        return this.functionsBox;
    }

    public JComboBox<String> getTeamsBox() {
        return this.teamsBox;
    }

}