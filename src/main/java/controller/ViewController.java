package main.java.controller;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;

import main.java.gui.*;
import main.java.model.company.Company;
import main.java.model.company.Person;
import main.java.model.log.ActionEnum;
import main.java.model.log.LogBook;
import main.java.model.log.UserAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Vector;

public class ViewController extends JFrame implements ActionListener {
    Login login = new Login();
    Insert insert = new Insert("Insert");
    Company company = new Company("Mitarbeiterverwaltung v2.31");
    Company model;
    MainFrame frame;
    private Person user;

    public void start() {
        login.getLoginButton().addActionListener(this);
        login.getResetButton().addActionListener(this);
        login.getShowPassword().addActionListener(this);
        insert.setVisible(false);
    }

    private void updateEmployeeLists() {
        if (frame.getEmployeeTab() != null) {
            frame.getEmployeeTab().getEmployeeList().setListData(company.getEmployees());
            frame.getEmployeeTab().getEmployeeList().updateUI();
        }
        if (frame.getAssigmentTab() != null) {
            frame.getAssigmentTab().getEmployeeList().setListData(company.getEmployees());
            frame.getAssigmentTab().getEmployeeList().updateUI();
        }
        if (frame.getOverviewTab() != null) {
            frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
            frame.getOverviewTab().getEmployeeList().updateUI();
        }
    }

    private DefaultComboBoxModel<String> createBoxModel(Vector<String> elements) {
        Vector<String> clone = new Vector<>(elements);
        clone.add(0, "-- alle --");
        return new DefaultComboBoxModel<>(clone);
    }

    private void updateDesignations() {
        if (frame.getOverviewTab() != null) {
            frame.getOverviewTab().getFunctionFilter().setModel(createBoxModel(company.getFunctions()));
            frame.getOverviewTab().getFunctionFilter().updateUI();
            frame.getOverviewTab().getTeamFilter().setModel(createBoxModel(company.getTeams()));
            frame.getOverviewTab().getTeamFilter().updateUI();
            frame.getOverviewTab().getDepartmentFilter().setModel(createBoxModel(company.getDepartments()));
            frame.getOverviewTab().getDepartmentFilter().updateUI();
        }
        if (frame.getAssigmentTab() != null) {
            frame.getAssigmentTab().getFunctionsBox().setModel(createBoxModel(company.getFunctions()));
            frame.getAssigmentTab().getFunctionsBox().updateUI();
            frame.getAssigmentTab().getTeamsBox().setModel(createBoxModel(company.getTeams()));
            frame.getAssigmentTab().getTeamsBox().updateUI();
        }
        if (frame.getDataTab() != null) {
            frame.getDataTab().getDepartmentList().setListData(company.getDepartments());
            frame.getDataTab().getDepartmentList().updateUI();
            frame.getDataTab().getFunctionsList().setListData(company.getFunctions());
            frame.getDataTab().getFunctionsList().updateUI();
            frame.getDataTab().getTeamsList().setListData(company.getTeams());
            frame.getDataTab().getTeamsList().updateUI();
        }
        if (insert != null){
            insert.setVisible(false);
            insert.getInsertField().setText("");
        }
    }

    private void addLog(UserAction entry) {
        LogBook.getInstance().addEntry(entry);
        if (frame.getLogBookTab() != null) {
            frame.getLogBookTab().update();
        }
    }

    private void initializeMainFrame(Person person) {
        this.user = person;
        // prepare mainFrame
        model = new Company("Eingeloggt als " + person.getName());
        frame = new MainFrame(company, person.isHr(), person.isAdmin(), new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                addLog(new UserAction(person, ActionEnum.LOGOUT_ACTION));
                LogBook.getInstance().closeFile();
            }
        });
        addLog(new UserAction(person, ActionEnum.LOGIN_SUCCESS));
        frame.setVisible(true);
        frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
        frame.getOverviewTab().getFunctionList().setListData(new Vector<String>());
        frame.getOverviewTab().getTeamList().setListData(new Vector<String>());

        // addEventListeners
        if (frame.getEmployeeTab() != null) {
            frame.getEmployeeTab().getDelBtn().addActionListener(this);
            frame.getEmployeeTab().getAddBtn().addActionListener(this);
            frame.getEmployeeTab().getEditBtn().addActionListener(this);
        }
        if (frame.getDataTab() != null) {
            frame.getDataTab().getDepartmentPanel().getAddBtn().addActionListener(this);
            frame.getDataTab().getDepartmentPanel().getEditBtn().addActionListener(this);
            frame.getDataTab().getDepartmentPanel().getRemBtn().addActionListener(this);
            frame.getDataTab().getFunctionsPanel().getAddBtn().addActionListener(this);
            frame.getDataTab().getFunctionsPanel().getEditBtn().addActionListener(this);
            frame.getDataTab().getFunctionsPanel().getRemBtn().addActionListener(this);
            frame.getDataTab().getTeamsPanel().getAddBtn().addActionListener(this);
            frame.getDataTab().getTeamsPanel().getEditBtn().addActionListener(this);
            frame.getDataTab().getTeamsPanel().getRemBtn().addActionListener(this);
            insert.getSubmit().addActionListener(this);
        }
        if (frame.getOverviewTab() != null) {
            frame.getOverviewTab().getEmployeeList().addListSelectionListener(new listSelection());
            frame.getOverviewTab().getDepartmentFilter().addActionListener(this);
            frame.getOverviewTab().getTeamFilter().addActionListener(this);
            frame.getOverviewTab().getFunctionFilter().addActionListener(this);
            frame.getOverviewTab().getSortNoneRadio().addActionListener(this);
            frame.getOverviewTab().getSortAscRadio().addActionListener(this);
            frame.getOverviewTab().getSortDescRadio().addActionListener(this);
        }

        if (frame.getAssigmentTab() != null) {
            frame.getAssigmentTab().getEmployeeList().addListSelectionListener(new listSelection2());
        }

        updateDesignations();

        // Namenseingabe
        frame.getOverviewTab().getNameFilter().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update(e);
            }

            private void update(DocumentEvent e) {
                if (frame.getOverviewTab().getNameFilter().getText().equals("")) {
                    frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
                    frame.getOverviewTab().getEmployeeList().updateUI();
                } else {
                    String nameFilter = frame.getOverviewTab().getNameField().getText().toLowerCase(Locale.ROOT);
                    Vector<Person> prefiltered = new Vector<>();
                    for (int i = 0 ; i < frame.getOverviewTab().getEmployeeList().getModel().getSize(); i++) {
                        prefiltered.add(frame.getOverviewTab().getEmployeeList().getModel().getElementAt(i));
                    }
                    prefiltered.removeIf(employee -> !employee.getName().toLowerCase(Locale.ROOT).contains(nameFilter));
                    frame.getOverviewTab().getEmployeeList().setListData(prefiltered);
                    frame.getOverviewTab().getEmployeeList().updateUI();
                }
            }
        });

        JTextField nameFilter = frame.getOverviewTab().getNameFilter();
        nameFilter.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (nameFilter.getText().equals("Name eingeben")) {
                    nameFilter.setText("");
                    nameFilter.setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameFilter.getText().isEmpty()) {
                    nameFilter.setForeground(Color.GRAY);
                    nameFilter.setText("Name eingeben");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        // login
        if (source == login.getLoginButton()) {
            String name = login.getUserField().getText();
            String password = new String(login.getPasswordField().getPassword());
            for (Person employee : company.getEmployees()) {
                if (employee.getName().equals(name)) {
                    if (employee.authenticate(password)) {
                        // log in successful
                        initializeMainFrame(employee);
                        login.getFrame().setVisible(false);
                        login.getFrame().dispose();
                        break;
                    }
                }
            }
            return;
        }
        if (source == login.getResetButton()) {
            login.getUserField().setText("");
            login.getPasswordField().setText("");
            return;
        }
        if (source == login.getShowPassword()) {
            if (login.getShowPassword().isSelected()) {
                login.getPasswordField().setEchoChar((char) 0);
            } else {
                login.getPasswordField().setEchoChar('*');
            }
            return;
        }

        OverviewTab overviewTab = frame.getOverviewTab();
        System.out.println(source);
        if (source == overviewTab.getDepartmentFilter() || source == overviewTab.getFunctionFilter()
                || source == overviewTab.getTeamFilter() || source == overviewTab.getSortNoneRadio()
                || source == overviewTab.getSortAscRadio() || source == overviewTab.getSortDescRadio()) {
            Vector<Person> filtered = new Vector<>(company.getEmployees());
            // Filter nach Department
            String selectedDepartment = overviewTab.getDepartmentFilter().getSelectedItem().toString();
            if (!selectedDepartment.equals("-- alle --")) {
                filtered.removeIf(employee -> !employee.getDepartment().equals(selectedDepartment));
            }

            // Filter nach Funktion
            String selectedFunction = overviewTab.getFunctionFilter().getSelectedItem().toString();
            if (!selectedFunction.equals("-- alle --")) {

                filtered.removeIf(employee -> !employee.getFunctions().contains(selectedFunction));
            }

            // Filter nach Team
            String selectedTeam = overviewTab.getTeamFilter().getSelectedItem().toString();

            if (!selectedTeam.equals("-- alle --")) {

                filtered.removeIf(employee -> !employee.getTeams().contains(selectedTeam));
            }
            // sort ascending
            if (overviewTab.getSortAscRadio().isSelected()) {
                Collections.sort(filtered);
            }
            if (overviewTab.getSortDescRadio().isSelected()) {
                Collections.sort(filtered);
                Collections.reverse(filtered);
            }

            overviewTab.getEmployeeList().setListData(filtered);
            overviewTab.getEmployeeList().updateUI();
        }

        EmployeeTab employeeTab;
        if ((employeeTab = frame.getEmployeeTab()) != null) {
            JTextField nameField = employeeTab.getNameField();
            JCheckBox hrBox = employeeTab.getHrCheckBox();
            JCheckBox adminBox = employeeTab.getAdminCheckBox();
            // Neuer Mitarbeiter
            if (source == employeeTab.getAddBtn()) {
                String[] splited = nameField.getText().split(" ");
                Person person = new Person(splited[0], splited[1], "");
                if (employeeTab.getAdminCheckBox().isSelected())
                    person.setAdmin(true);
                if (employeeTab.getHrCheckBox().isSelected())
                    person.setHr(true);
                company.getEmployees().add(person);
                company.writeEmployees();
                updateEmployeeLists();
                addLog(new UserAction(this.user, person, ActionEnum.CREATE_PERSON));
                return;
            }

            // Mitarbeiter entfernen
            if (source == employeeTab.getDelBtn()) {
                for (Person employee : company.getEmployees()) {
                    if (employee.getName().equals(nameField.getText())) {
                        nameField.setText("");
                        hrBox.setSelected(false);
                        adminBox.setSelected(false);
                        company.getEmployees().remove(employee);
                        company.writeEmployees();
                        updateEmployeeLists();
                        addLog(new UserAction(this.user, employee, ActionEnum.DELETE_PERSON));
                        break;
                    }
                }
                return;
            }

            // Mitarbeiter bearbeiten
            if (source == employeeTab.getEditBtn()){

            }

        }
        DataTab dataTab;
        if ((dataTab = frame.getDataTab()) != null) {
            ListEditPanel departmentPanel = dataTab.getDepartmentPanel();
            ListEditPanel functionsPanel = dataTab.getFunctionsPanel();
            ListEditPanel teamsPanel = dataTab.getTeamsPanel();
            JList<String> departmentList = dataTab.getDepartmentList();
            JList<String> functionsList = dataTab.getFunctionsList();
            JList<String> teamsList = dataTab.getTeamsList();
            // Add Department
            if (source == departmentPanel.getAddBtn()) {
                insert.setTitle("Department hinzufügen");
                insert.setVisible(true);
                return;
            }
            // Add Function
            if (source == functionsPanel.getAddBtn()) {
                insert.setTitle("Funktion hinzufügen");
                insert.setVisible(true);
                return;
            }
            // Add Team
            if (source == teamsPanel.getAddBtn()) {
                insert.setTitle("Team hinzufügen");
                insert.setVisible(true);
                return;
            }

            // ____________

            // Edit Department
            if (source == departmentPanel.getEditBtn()) {
                insert.setTitle("Department bearbeiten");
                insert.setVisible(true);
                return;
            }
            // Edit Function
            if (source == functionsPanel.getEditBtn()) {
                insert.setTitle("Funktion bearbeiten");
                insert.setVisible(true);
                return;
            }
            // Edit Team
            if (source == teamsPanel.getEditBtn()) {
                insert.setTitle("Team bearbeiten");
                insert.setVisible(true);
                return;
            }

            // Delete
            if (source == departmentPanel.getRemBtn()) {
                company.getDepartments().remove(departmentList.getSelectedValue());
                company.writeDepartments();
                updateDesignations();
                return;
            }
            if (source == functionsPanel.getRemBtn()) {
                company.getFunctions().remove(functionsList.getSelectedValue());
                company.writeFunctions();
                updateDesignations();
                return;
            }
            if (source == teamsPanel.getRemBtn()) {
                company.getTeams().remove(teamsList.getSelectedValue());
                company.writeTeams();
                updateDesignations();
                return;
            }

            // Department, Team or Function
            if (source == insert.getSubmit()) {
                if (insert.getTitle().equals("Department hinzufügen")) {
                    if (!company.getDepartments().contains(insert.getInsertField().getText())){
                        company.getDepartments().add(insert.getInsertField().getText());
                        company.writeDepartments();
                        updateDesignations();
                    }

                } else if (insert.getTitle().equals("Funktion hinzufügen")) {
                    if (!company.getFunctions().contains(insert.getInsertField().getText())){
                        company.getFunctions().add(insert.getInsertField().getText());
                        company.writeFunctions();
                        updateDesignations();
                    }
                } else if (insert.getTitle().equals("Team hinzufügen")) {
                    if (!company.getTeams().contains(insert.getInsertField().getText())){
                        company.getTeams().add(insert.getInsertField().getText());
                        company.writeTeams();
                        updateDesignations();
                    }
                }

                // ____________

                else if (insert.getTitle().equals("Department bearbeiten")) {
                    for (int i = 0; i < dataTab.getDepartmentList().getModel().getSize(); i++) {
                        if (departmentList.getModel().getElementAt(i).equals(departmentList.getSelectedValue().toString())) {
                            if (!company.getDepartments().contains(insert.getInsertField().getText())){
                                company.getDepartments().set(i, insert.getInsertField().getText());
                                company.writeDepartments();
                                updateDesignations();
                            }
                        }
                    }
                } else if (insert.getTitle().equals("Funktion bearbeiten")) {
                    for (int i = 0; i < functionsList.getModel().getSize(); i++) {
                        if (functionsList.getModel().getElementAt(i).equals(functionsList.getSelectedValue().toString())) {
                            if (!company.getFunctions().contains(insert.getInsertField().getText())){
                                company.getFunctions().set(i, insert.getInsertField().getText());
                                company.writeFunctions();
                                updateDesignations();
                            }
                        }
                    }
                } else if (insert.getTitle().equals("Team bearbeitenn")) {
                    for (int i = 0; i < teamsList.getModel().getSize(); i++) {
                        if (teamsList.getModel().getElementAt(i).equals(dataTab.getTeamsList().getSelectedValue().toString())) {
                            if (!company.getTeams().contains(insert.getInsertField().getText())){
                                company.getTeams().set(i, insert.getInsertField().getText());
                                company.writeTeams();
                                updateDesignations();
                            }
                        }
                    }
                }
                addLog(new UserAction(this.user, ActionEnum.UNKNOWN_ACTION));
                return;
            }
        }
    }

    class listSelection implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try {
                Person person = (Person) frame.getOverviewTab().getEmployeeList().getSelectedValue();
                if (person == null)
                    return;
                frame.getOverviewTab().getNameField().setText(person.getName());
                frame.getOverviewTab().getDepartmentField().setText(person.getDepartment());
                frame.getOverviewTab().getFunctionList().setListData(person.getFunctions());
                frame.getOverviewTab().getTeamList().setListData(person.getTeams());
                frame.getOverviewTab().getNameField().updateUI();
                frame.getOverviewTab().getDepartmentField().updateUI();
                frame.getOverviewTab().getFunctionList().updateUI();
                frame.getOverviewTab().getTeamList().updateUI();
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }

    class listSelection2 implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try {
                Person person = frame.getAssigmentTab().getEmployeeList().getSelectedValue();
                frame.getAssigmentTab().getNameField().setText(person.getName());
                frame.getAssigmentTab().getDepartmentBox().setSelectedItem(person.getDepartment());
                // set funktion kombo box
                // set team kombo box

                frame.getAssigmentTab().getNameField().updateUI();
                frame.getAssigmentTab().getDepartmentBox().updateUI();
                frame.getAssigmentTab().getFunctionsBox().updateUI();
                frame.getAssigmentTab().getTeamsBox().updateUI();
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }

}
