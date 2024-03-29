package main.java.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.java.model.company.Company;
import main.java.model.company.Person;

/**
 * OverviewTab is a Tab for viewing Employee data.
 * @author Daniel Bengl
 */
public class OverviewTab extends JPanel {
    // personenliste
    private ButtonGroup sortGroup;
    private JRadioButton sortNoneRadio;
    private JRadioButton sortAscRadio;
    private JRadioButton sortDescRadio;
    private JComboBox<String> departmentFilter;
    private JComboBox<String> functionFilter;
    private JComboBox<String> teamFilter;
    private JTextField nameFilter;
    private JList<Person> employeeList;

    // detail
    private JTextField nameField;
    private JTextField departmentField;
    private JList<String> functionList;
    private JList<String> teamList;
    private JLabel imageLabel;

    private Company model;
    
    /**
     * Constructor for the OverviewTab.
     * @param model Company model.
     */
    public OverviewTab(Company model) {
        super();
        this.model = model;
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
    
        sortGroup.add((AbstractButton) sortPanel.add(sortNoneRadio));
        sortGroup.add((AbstractButton) sortPanel.add(sortAscRadio));
        sortGroup.add((AbstractButton) sortPanel.add(sortDescRadio));
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
        departmentFilter = new JComboBox<>(model.getDepartments());
        functionFilter = new JComboBox<>(model.getFunctions());
        teamFilter = new JComboBox<>(model.getTeams());
        nameFilter = new JTextField("Name eingeben");

        employeeList = new JList<>(model.getEmployees());
        // detail
        nameField = new JTextField();
        nameField.setEditable(false);
        departmentField = new JTextField();
        departmentField.setEditable(false);
        functionList = new JList<>();
        teamList = new JList<>();
        imageLabel = new JLabel();
        // radio
        sortNoneRadio = new JRadioButton("keine");
        sortNoneRadio.setSelected(true);
        sortAscRadio = new JRadioButton("A-Z");
        sortDescRadio = new JRadioButton("Z-A");
    }

    // getters

    public ButtonGroup getSortGroup() {
        return this.sortGroup;
    }

    public JComboBox<String> getDepartmentFilter() {
        return this.departmentFilter;
    }

    public JComboBox<String> getFunctionFilter() {
        return this.functionFilter;
    }

    public JComboBox<String> getTeamFilter() {
        return this.teamFilter;
    }

    public JTextField getNameFilter() {
        return this.nameFilter;
    }

    public JList<Person> getEmployeeList() {
        return this.employeeList;
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JTextField getDepartmentField() {
        return this.departmentField;
    }

    public JList<String> getFunctionList() {
        return this.functionList;
    }

    public JList<String> getTeamList() {
        return this.teamList;
    }

    public JLabel getImageLabel() {
        return this.imageLabel;
    }

    public JRadioButton getSortNoneRadio() {
        return sortNoneRadio;
    }

    public JRadioButton getSortAscRadio() {
        return sortAscRadio;
    }

    public JRadioButton getSortDescRadio() {
        return sortDescRadio;
    }

}