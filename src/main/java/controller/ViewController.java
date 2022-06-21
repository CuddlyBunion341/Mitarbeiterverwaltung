package main.java.controller;

import javax.swing.*;
import javax.swing.event.*;

import main.java.gui.*;
import main.java.model.company.Company;
import main.java.model.company.Person;
import main.java.model.log.LogBook;
import main.java.model.log.UserAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Vector;

public class ViewController extends JFrame implements ActionListener {
    Login login = new Login();
    Insert insert = new Insert("Insert");
    Company company = new Company("Mitarbeiterverwaltung v2.31");
    Company model;
    MainFrame frame;
    LogBook logBook = LogBook.getInstance();

    public void start(){
        login.getLoginButton().addActionListener(this);
        login.getResetButton().addActionListener(this);
        login.getShowPassword().addActionListener(this);
        insert.setVisible(false);
    }

    private void updateEmployeeLists() {
        if (frame.getEmployeeTab() != null){
            frame.getEmployeeTab().getEmployeeList().setListData(company.getEmployees());
            frame.getEmployeeTab().getEmployeeList().updateUI();
        }
        if (frame.getAssigmentTab() != null){
            frame.getAssigmentTab().getEmployeeList().setListData(company.getEmployees());
            frame.getAssigmentTab().getEmployeeList().updateUI();
        }
        if (frame.getOverviewTab() != null){
            frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
            frame.getOverviewTab().getEmployeeList().updateUI();
        }
    }

    private void updateComboBoxes() {
        if (frame.getDataTab() != null){
            frame.getDataTab().getDepartmentList().setListData(company.getDepartments());
            frame.getDataTab().getFunctionsList().setListData(company.getFunctions());
            frame.getDataTab().getTeamsList().setListData(company.getTeams());
            frame.getDataTab().getDepartmentList().updateUI();
            frame.getDataTab().getFunctionsList().updateUI();
            frame.getDataTab().getTeamsList().updateUI();
        }
    }

    // public void updateDataGUI(){
    //     updateLists();
    //     insert.setVisible(false);
    //     insert.getInsertField().setText("");
    // }

    public void initializeMainFrame(Person person){
        //prepare mainFrame
        model = new Company("Eingeloggt als " + person.getName());
        frame = new MainFrame(company,person.isHr(),person.isAdmin());
        frame.setVisible(true);
        frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
        frame.getOverviewTab().getFunctionList().setListData(new Vector<String>());
        frame.getOverviewTab().getTeamList().setListData(new Vector<String>());

        //addEventListeners
        if (frame.getEmployeeTab() != null){
            frame.getEmployeeTab().getEmployeeList().setEnabled(false);
            frame.getEmployeeTab().getDelBtn().addActionListener(this);
            frame.getEmployeeTab().getAddBtn().addActionListener(this);
            frame.getEmployeeTab().getEditBtn().addActionListener(this);
        }
        if (frame.getDataTab() != null){
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
        if (frame.getOverviewTab() != null){
            frame.getOverviewTab().getEmployeeList().addListSelectionListener(new listSelection());
            frame.getOverviewTab().getDepartmentFilter().addActionListener(this);
        }

        frame.getAssigmentTab().getEmployeeList().addListSelectionListener(new listSelection2());

        //Namenseingabe
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
            public void update(DocumentEvent e){
                if (frame.getOverviewTab().getNameFilter().getText().equals("")){
                    frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
                    frame.getOverviewTab().getEmployeeList().updateUI();
                }
                else{
                    Vector<Person> result = new Vector<Person>();
                    for (int i = 0; i < company.getEmployees().size(); i++){
                        if (company.getEmployees().get(i).getName().toLowerCase(Locale.ROOT).contains(frame.getOverviewTab().getNameFilter().getText().toLowerCase(Locale.ROOT))){
                            result.add(company.getEmployees().get(i));
                        }
                    }
                    frame.getOverviewTab().getEmployeeList().setListData(result);
                    frame.getOverviewTab().getEmployeeList().updateUI();
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        //login
        if (source == login.getLoginButton()) {
            String name = login.getUserField().getText();
            String password = new String(login.getPasswordField().getPassword());
            for (Person employee : company.getEmployees()) {
                if (employee.getName().equals(name)) {
                    if (employee.authenticate(password)) {
                        initializeMainFrame(employee);
                        login.getFrame().setVisible(false);
                        login.getFrame().dispose();
                        logBook.addEntry(new UserAction(employee, 4));
                        break;
                    }
                }
            }
        }
        if (source == login.getResetButton()) {
            login.getUserField().setText("");
            login.getPasswordField().setText("");
        }
        if (source == login.getShowPassword()) {
            if (login.getShowPassword().isSelected()) {
                login.getPasswordField().setEchoChar((char) 0);
            } else {
                login.getPasswordField().setEchoChar('*');
            }
        }

        //Filter nach Department
        OverviewTab overviewTab = frame.getOverviewTab();
        if (source == overviewTab.getDepartmentFilter() || source == overviewTab.getFunctionFilter() || source == overviewTab.getTeamFilter()) {
            Vector<Person> filtered = getEmployees(overviewTab.getEmployeeList());
            // Filter nach Department
            if (source == overviewTab.getDepartmentFilter()){
                String selectedDepartment = overviewTab.getDepartmentFilter().getSelectedItem().toString();
    
                for (Person employee : filtered) {
                    if (!employee.getDepartment().equals(selectedDepartment)) {
                        filtered.remove(employee);
                    }
                }
            }
    
            //Filter nach Funktion
            if (source == overviewTab.getFunctionFilter()){
                String selectedFunction = overviewTab.getFunctionFilter().getSelectedItem().toString();
    
                for (Person employee : filtered) {
                    if (!employee.getFunctions().contains(selectedFunction)) {
                        filtered.remove(employee);
                    }
                }
            }
    
            //Filter nach Team
            if (source == overviewTab.getTeamFilter()){
                String selectedTeam = overviewTab.getTeamFilter().getSelectedItem().toString();
                
                for (Person employee : filtered) {
                    if (!employee.getTeams().contains(selectedTeam)) {
                        filtered.remove(employee);
                    }
                }
    
            }
            overviewTab.getEmployeeList().setListData(filtered);
            overviewTab.getEmployeeList().updateUI();
        }

        EmployeeTab employeeTab;
        if ((employeeTab = frame.getEmployeeTab()) != null){
            
            JTextField nameField = employeeTab.getNameField();
            JCheckBox hrBox = employeeTab.getHrCheckBox();
            JCheckBox adminBox = employeeTab.getAdminCheckBox();
            //Neuer Mitarbeiter
            if (source == employeeTab.getAddBtn()){
                String[] splited = nameField.getText().split(" ");
                Person person = new Person(splited[0], splited[1], "");
                if (employeeTab.getAdminCheckBox().isSelected())
                    person.setAdmin(true);
                if (employeeTab.getHrCheckBox().isSelected())
                    person.setHr(true);
                company.getEmployees().add(person);
                company.writeEmployees();
                updateEmployeeLists();
            }

            //Mitarbeiter entfernen
            if (source == employeeTab.getDelBtn()){
                for (Person employee : company.getEmployees()) {
                    if (employee.getName().equals(nameField.getText())) {
                        nameField.setText("");
                        hrBox.setSelected(false);
                        adminBox.setSelected(false);
                        company.getEmployees().remove(employee);
                        company.writeEmployees();
                        updateEmployeeLists();
                        break;
                    }
                }
            }

            //Mitarbeiter bearbeiten

        }
        DataTab dataTab;
        if ((dataTab = frame.getDataTab()) != null){
            ListEditPanel departmentPanel = dataTab.getDepartmentPanel();
            ListEditPanel functionsPanel = dataTab.getFunctionsPanel();
            ListEditPanel teamsPanel = dataTab.getTeamsPanel();
            JList<String> departmentList = dataTab.getDepartmentList();
            JList<String> functionsList = dataTab.getFunctionsList();
            JList<String> teamsList = dataTab.getTeamsList();
            //Add Department
            if (source == departmentPanel.getAddBtn()){
                insert.setTitle("Department hinzufügen");
                insert.setVisible(true);
            }
            //Add Function
            if (source == functionsPanel.getAddBtn()){
                insert.setTitle("Funktion hinzufügen");
                insert.setVisible(true);
            }
            //Add Team
            if (source == teamsPanel.getAddBtn()){
                insert.setTitle("Team hinzufügen");
                insert.setVisible(true);
            }

            //____________

            //Edit Department
            if (source == departmentPanel.getEditBtn()){
                insert.setTitle("Department bearbeiten");
                insert.setVisible(true);
            }
            //Edit Function
            if (source == functionsPanel.getEditBtn()){
                insert.setTitle("Funktion bearbeiten");
                insert.setVisible(true);
            }
            //Edit Team
            if (source == teamsPanel.getEditBtn()){
                insert.setTitle("Team bearbeiten");
                insert.setVisible(true);
            }

            //Delete
            if (source == departmentPanel.getRemBtn()){
                company.getDepartments().remove(departmentList.getSelectedValue());
                company.writeDepartments();
                updateComboBoxes();
            }
            if (source == functionsPanel.getRemBtn()){
                company.getFunctions().remove(functionsList.getSelectedValue());
                company.writeFunctions();
                updateComboBoxes();
            }
            if (source == teamsPanel.getRemBtn()){
                company.getTeams().remove(teamsList.getSelectedValue());
                company.writeTeams();
                updateComboBoxes();
            }


            //Department, Team or Function
            if (source == insert.getSubmit()){
                if (insert.getTitle().equals("Department hinzufügen")){
                    company.getDepartments().add(insert.getInsertField().getText());
                    company.writeDepartments();
                    updateComboBoxes();
                }
                else if (insert.getTitle().equals("Funktion hinzufügen")){
                    company.getFunctions().add(insert.getInsertField().getText());
                    company.writeFunctions();
                    updateComboBoxes();
                }
                else if (insert.getTitle().equals("Team hinzufügen")){
                    company.getTeams().add(insert.getInsertField().getText());
                    company.writeTeams();
                    updateComboBoxes();
                }

                //____________

                else if (insert.getTitle().equals("Department bearbeiten")){
                    for (int i = 0; i < dataTab.getDepartmentList().getModel().getSize(); i++){
                        if (departmentList.getModel().getElementAt(i).equals(departmentList.getSelectedValue().toString())){
                            company.getDepartments().set(i, insert.getInsertField().getText());
                        }
                    }
                    company.writeDepartments();
                    updateComboBoxes();
                }
                else if (insert.getTitle().equals("Funktion bearbeiten")){
                    for (int i = 0; i < functionsList.getModel().getSize(); i++){
                        if (functionsList.getModel().getElementAt(i).equals(functionsList.getSelectedValue().toString())){
                            company.getFunctions().set(i, insert.getInsertField().getText());
                        }
                    }
                    company.writeFunctions();
                    updateComboBoxes();
                }
                else if (insert.getTitle().equals("Team bearbeitenn")){
                    for (int i = 0; i < teamsList.getModel().getSize(); i++){
                        if (teamsList.getModel().getElementAt(i).equals(dataTab.getTeamsList().getSelectedValue().toString())){
                            company.getTeams().set(i, insert.getInsertField().getText());
                        }
                    }
                    company.writeTeams();
                    updateComboBoxes();
                }
            }
        }
    }

    private Vector<Person> getEmployees(JList<Person> list) {
        Vector<Person> employees = new Vector<>();
        for (int i = 0; i < list.getModel().getSize(); i++) {
            employees.add(list.getModel().getElementAt(i));
        }
        return employees;
    }

    class listSelection implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try{
                Person person = (Person) frame.getOverviewTab().getEmployeeList().getSelectedValue();
                frame.getOverviewTab().getNameField().setText(person.getName());
                frame.getOverviewTab().getDepartmentField().setText(person.getDepartment());
                frame.getOverviewTab().getFunctionList().setListData(person.getFunctions());
                frame.getOverviewTab().getTeamList().setListData(person.getTeams());

                frame.getOverviewTab().getNameField().updateUI();
                frame.getOverviewTab().getDepartmentField().updateUI();
                frame.getOverviewTab().getFunctionList().updateUI();
                frame.getOverviewTab().getTeamList().updateUI();
            }
            catch (ArrayIndexOutOfBoundsException ignored){}
        }
    }

    class listSelection2 implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try{
                Person person = company.getEmployees().get(frame.getAssigmentTab().getEmployeeList().getSelectedIndex());
                frame.getAssigmentTab().getNameField().setText(person.getName());
                frame.getAssigmentTab().getDepartmentField().setText(person.getDepartment());
                //set funktion kombo box
                //set team kombo box

                frame.getAssigmentTab().getNameField().updateUI();
                frame.getAssigmentTab().getDepartmentField().updateUI();
                frame.getAssigmentTab().getFunctionsBox().updateUI();
                frame.getAssigmentTab().getTeamsBox().updateUI();
            }
            catch (ArrayIndexOutOfBoundsException ignored){}
        }
    }

}
