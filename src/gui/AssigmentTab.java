package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AssigmentTab extends JPanel {
    private JList employeeList;
    private JTextField nameField;
    private JTextField departmentField;
    private JComboBox functionsBox;
    private JComboBox teamsBox;
    public AssigmentTab() {
        super();
        init();
        build();
    }

    private void init() {
        String[] employees = new String[50];
        for (int i = 0; i < 50; i++) {
            employees[i] = "Person " + i;
        }
        employeeList = new JList<String>(employees);
        nameField = new JTextField();
        departmentField = new JTextField();
        functionsBox = new JComboBox<>(new String[]{"Controller","Betriebs-Sani"});
        teamsBox = new JComboBox<>(new String[]{"More cash","New Customer","Idea 3000","Leitbild"});
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

    public JList getEmployeeList() {
        return this.employeeList;
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JTextField getDepartmentField() {
        return this.departmentField;
    }

    public JComboBox getFunctionsBox() {
        return this.functionsBox;
    }

    public JComboBox getTeamsBox() {
        return this.teamsBox;
    }

}