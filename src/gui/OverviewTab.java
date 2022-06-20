package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.company.Company;

public class OverviewTab extends JPanel {
    // personenliste
    private ButtonGroup sortGroup;
    private JComboBox departmentFilter;
    private JComboBox functionFilter;
    private JComboBox teamFilter;
    private JTextField nameFilter;
    private JList employeeList;

    // detail
    private JTextField nameField;
    private JTextField departmentField;
    private JList functionList;
    private JList teamList;
    private JLabel imageLabel;
    
    public OverviewTab(Company model) {
        super();
        init();
        setLayout(new BorderLayout());
        build();
    }

    private void build() {
        // Personen Panel
        JPanel mainPanel = Util.fieldset("Personen");
        mainPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JScrollPane persScroll = Util.scrollPane("Übersicht", employeeList);
        leftPanel.add(persScroll, BorderLayout.CENTER);
        leftPanel.add(nameFilter, BorderLayout.SOUTH);


        mainPanel.add(leftPanel, BorderLayout.WEST);
        // Detail Panel
        JPanel detailsPanel = Util.fieldset("Detail");
        detailsPanel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2,2));
        topPanel.add(new JLabel("Name: "));
        topPanel.add(nameField);
        topPanel.add(new JLabel("Abteilung: "));
        topPanel.add(departmentField);
        JPanel detailsBottomPanel = new JPanel(new GridLayout(1,2));
        JScrollPane functionsPane = Util.scrollPane("Funktionen", functionList);
        detailsBottomPanel.add(functionsPane);
        JScrollPane teamsPane = Util.scrollPane("Teams", teamList);
        detailsBottomPanel.add(teamsPane);
        //
        detailsPanel.add(topPanel, BorderLayout.NORTH);
        detailsPanel.add(detailsBottomPanel, BorderLayout.CENTER);

        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);


        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 2));

        // Sortier Panel
        JPanel sortPanel = Util.fieldset("Sortierung");
        sortGroup = new ButtonGroup();
        sortGroup.add((AbstractButton) sortPanel.add(new JRadioButton("keine")));
        sortGroup.add((AbstractButton) sortPanel.add(new JRadioButton("A-Z")));
        sortGroup.add((AbstractButton) sortPanel.add(new JRadioButton("Z-A")));
        optionsPanel.add(sortPanel);

        // Filter Panel
        JPanel filterPanel = Util.fieldset("Filter");
        filterPanel.setLayout(new GridLayout(3, 2));
        filterPanel.add(new JLabel("Abteilung"));
        filterPanel.add(departmentFilter);
        filterPanel.add(new JLabel("Funktion"));
        filterPanel.add(functionFilter);
        filterPanel.add(new JLabel("Team"));
        filterPanel.add(teamFilter);
        optionsPanel.add(filterPanel);

        add(optionsPanel, BorderLayout.PAGE_END);
    }

    private void init() {
        // personenliste
        sortGroup = new ButtonGroup();
        departmentFilter = new JComboBox<>(new String[]{"- alle -","Logistik", "IT", "Marketing", "Produktion"});
        functionFilter = new JComboBox<>(new String[]{"- alle -", "Manager", "Mitarbeiter", "Assistent"});
        teamFilter = new JComboBox<>(new String[]{"- alle -", "Next Facility", "IT", "Marketing", "Produktion"});
        nameFilter = new JTextField("Name eingeben");
        java.util.List<String> employees = new ArrayList<>();
        for (int i = 0 ; i < 30; i++) {
            employees.add("Person " + i);
        }

        employeeList = new JList<>(employees.toArray());
        // detail
        nameField = new JTextField();
        departmentField = new JTextField();
        functionList = new JList<>(new String[]{"Controller","Betriebs-Sani"});
        teamList = new JList<>(new String[]{"More cash","New Customer","Idea 3000","Leitbild"});
        imageLabel = new JLabel();
    }

    // getters

    public ButtonGroup getSortGroup() {
        return this.sortGroup;
    }

    public JComboBox getDepartmentFilter() {
        return this.departmentFilter;
    }

    public JComboBox getFunctionFilter() {
        return this.functionFilter;
    }

    public JComboBox getTeamFilter() {
        return this.teamFilter;
    }

    public JTextField getNameFilter() {
        return this.nameFilter;
    }

    public JList getEmployeeList() {
        return this.employeeList;
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JTextField getDepartmentField() {
        return this.departmentField;
    }

    public JList getFunctionList() {
        return this.functionList;
    }

    public JList getTeamList() {
        return this.teamList;
    }

    public JLabel getImageLabel() {
        return this.imageLabel;
    }

}