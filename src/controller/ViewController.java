package controller;

import gui.*;
import model.company.Company;
import model.company.Person;
import model.log.LogBook;
import model.log.UserAction;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
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

    public void updateTables(){
        if (frame.getEmployeeTab() != null){
            frame.getEmployeeTab().getEmployeeList().setListData(company.getEmployees());
            frame.getEmployeeTab().getEmployeeList().updateUI();
        }
        if (frame.getAssigmentTab() != null){
            frame.getAssigmentTab().getEmployeeList().setListData(company.getEmployees());
        }
        if (frame.getOverviewTab() != null){
            frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
            frame.getOverviewTab().getEmployeeList().updateUI();
        }
        if (frame.getDataTab() != null){
            frame.getDataTab().getDepartmentList().setListData(company.getDepartments());
            frame.getDataTab().getFunctionsList().setListData(company.getFunctions());
            frame.getDataTab().getTeamsList().setListData(company.getTeams());
        }
    }

    public void updateDataGUI(){
        updateTables();
        insert.setVisible(false);
        insert.getInsertField().setText("");
    }

    public void initializeMainFrame(Person person){
        //prepare mainFrame
        model = new Company("Eingeloggt als " + person.getName());
        frame = new MainFrame(company,person.isHr(),person.isAdmin());
        frame.setVisible(true);
        JTabbedPane tabs = frame.getTabbedPane();
        frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
        frame.getOverviewTab().getFunctionList().setListData(new Vector());
        frame.getOverviewTab().getTeamList().setListData(new Vector());


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

        //login
        if (e.getSource() == login.getLoginButton()) {
            for (int i = 0; i < company.getEmployees().size(); i++){
                if (login.getUserField().getText().equals(company.getEmployees().get(i).getName())){
                    if (company.getEmployees().get(i).authenticate(login.getPasswordField().getText())){
                        initializeMainFrame(company.getEmployees().get(i));
                        login.getFrame().setVisible(false);
                        login.getFrame().dispose();
                        logBook.addEntry(new UserAction(company.getEmployees().get(i), 4));
                        break;
                    }
                }
            }
        }
        if (e.getSource() == login.getResetButton()) {
            login.getUserField().setText("");
            login.getPasswordField().setText("");
        }
        if (e.getSource() == login.getShowPassword()) {
            if (login.getShowPassword().isSelected()) {
                login.getPasswordField().setEchoChar((char) 0);
            } else {
                login.getPasswordField().setEchoChar('*');
            }
        }

        //Filter nach Department
        if (e.getSource() == frame.getOverviewTab().getDepartmentFilter()){
            Vector<Person> result = new Vector<Person>((Collection<? extends Person>) frame.getEmployeeTab().getEmployeeList().getModel());
            //geht???
            for (int i = 0; i < result.size(); i++){
                if (!result.get(i).getDepartment().equals(frame.getOverviewTab().getDepartmentFilter().getSelectedItem())){
                    result.remove(i);
                }
            }
            frame.getOverviewTab().getEmployeeList().setListData(result);
            frame.getOverviewTab().getEmployeeList().updateUI();
        }

        //Filet nach Funktion
        if (e.getSource() == frame.getOverviewTab().getFunctionFilter()){

        }

        //Filter nach Team
        if (e.getSource() == frame.getOverviewTab().getTeamFilter()){

        }


        if (frame.getEmployeeTab() != null){
            //Neuer Mitarbeiter
            if (e.getSource() == frame.getEmployeeTab().getAddBtn()){
                System.out.println("Test");
                String[] splited = frame.getEmployeeTab().getNameField().getText().split(" ");
                Person person = new Person(splited[0], splited[1], "");
                if (frame.getEmployeeTab().getAdminCheckBox().isSelected()){
                    person.setAdmin(true);
                }
                if (frame.getEmployeeTab().getHrCheckBox().isSelected()){
                    person.setHr(true);
                }
                company.getEmployees().add(person);
                company.writeEmployees();
                updateTables();
            }

            //Mitarbeiter entfernen
            if (e.getSource() == frame.getEmployeeTab().getDelBtn()){
                for (int i = 0; i < company.getEmployees().size(); i++){
                    if (company.getEmployees().get(i).getName().equals(frame.getEmployeeTab().getNameField().getText())){
                        company.getEmployees().remove(i);
                        updateTables();
                        frame.getEmployeeTab().getNameField().setText("");
                        frame.getEmployeeTab().getHrCheckBox().setSelected(false);
                        frame.getEmployeeTab().getAdminCheckBox().setSelected(false);
                        company.writeEmployees();
                        break;
                    }
                }
            }

            //Mitarbeiter bearbeiten

        }
        if (frame.getDataTab() != null){
            //Add Department
            if (e.getSource() == frame.getDataTab().getDepartmentPanel().getAddBtn()){
                insert.setTitle("Department hinzufügen");
                insert.setVisible(true);
            }
            //Add Function
            if (e.getSource() == frame.getDataTab().getFunctionsPanel().getAddBtn()){
                insert.setTitle("Funktion hinzufügen");
                insert.setVisible(true);
            }
            //Add Team
            if (e.getSource() == frame.getDataTab().getTeamsPanel().getAddBtn()){
                insert.setTitle("Team hinzufügen");
                insert.setVisible(true);
            }

            //____________

            //Edit Department
            if (e.getSource() == frame.getDataTab().getDepartmentPanel().getEditBtn()){
                insert.setTitle("Department bearbeiten");
                insert.setVisible(true);
            }
            //Edit Function
            if (e.getSource() == frame.getDataTab().getFunctionsPanel().getEditBtn()){
                insert.setTitle("Funktion bearbeiten");
                insert.setVisible(true);
            }
            //Edit Team
            if (e.getSource() == frame.getDataTab().getTeamsPanel().getEditBtn()){
                insert.setTitle("Team bearbeiten");
                insert.setVisible(true);
            }

            //Department, Team or Function
            if (e.getSource() == insert.getSubmit()){
                if (insert.getTitle().equals("Department hinzufügen")){
                    company.getDepartments().add(insert.getInsertField().getText());
                    company.writeDepartments();
                    updateDataGUI();
                }
                else if (insert.getTitle().equals("Funktion hinzufügen")){
                    company.getFunctions().add(insert.getInsertField().getText());
                    company.writeFunctions();
                    updateDataGUI();
                }
                else if (insert.getTitle().equals("Team hinzufügen")){
                    company.getTeams().add(insert.getInsertField().getText());
                    company.writeTeams();
                    updateDataGUI();
                }

                //____________

                else if (insert.getTitle().equals("Department bearbeiten")){
                    for (int i = 0; i < frame.getDataTab().getDepartmentList().getModel().getSize(); i++){
                        if (frame.getDataTab().getDepartmentList().getModel().getElementAt(i).equals(frame.getDataTab().getDepartmentList().getSelectedValue().toString())){
                            //frame.getDataTab().getDepartmentList().getModel(). = insert.getInsertField().getText();
                        }
                    }
                    company.writeDepartments();
                    updateDataGUI();
                }
                else if (insert.getTitle().equals("Funktion bearbeiten")){

                    company.writeFunctions();
                    updateDataGUI();
                }
                else if (insert.getTitle().equals("Team bearbeitenn")){
                    company.getTeams().add(insert.getInsertField().getText());
                    company.writeTeams();
                    updateDataGUI();
                }

            }



        }


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
