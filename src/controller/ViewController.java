package controller;

import gui.*;
import model.company.Company;
import model.company.Person;
import model.log.LogBook;
import model.log.UserAction;
import view.Bridge;
import view.LogIn;
import view.LoggedIn;
import view.LoggedOut;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RectangularShape;
import java.util.Locale;
import java.util.Vector;

public class ViewController extends JFrame implements ActionListener {
    Login login = new Login();
    Company company = new Company("Mitarbeiterverwaltung v2.31");
    Company model;
    MainFrame frame;
    LogBook logBook = new LogBook();

    public void start(){
        login.getLoginButton().addActionListener(this);
        login.getResetButton().addActionListener(this);
        login.getShowPassword().addActionListener(this);
    }

    public void initializeMainFrame(Person person){
        model = new Company("Eingeloggt als " + person.getName());
        frame = new MainFrame(company,person.isHr(),person.isAdmin());
        frame.setVisible(true);
        JTabbedPane tabs = frame.getTabbedPane();
        frame.getOverviewTab().getEmployeeList().setListData(company.getEmployees());
        frame.getOverviewTab().getFunctionList().setListData(new Vector());
        frame.getOverviewTab().getTeamList().setListData(new Vector());
        frame.getEmployeeTab().getDelBtn().addActionListener(this);
        frame.getDataTab().getDepartmentPanel().getAddBtn().addActionListener(this);
        frame.getOverviewTab().getEmployeeList().addListSelectionListener(new listSelection());
        frame.getOverviewTab().getDepartmentFilter().addActionListener(this);
        frame.getEmployeeTab().getAddBtn().addActionListener(this);
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
                }
                else{
                    Vector<Person> result = new Vector<Person>();
                    for (int i = 0; i < company.getEmployees().size(); i++){
                        if (company.getEmployees().get(i).getName().toLowerCase(Locale.ROOT).contains(frame.getOverviewTab().getNameFilter().getText().toLowerCase(Locale.ROOT))){
                            result.add(company.getEmployees().get(i));
                        }
                    }
                    frame.getOverviewTab().getEmployeeList().setListData(result);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Vector<Person> result = company.getEmployees();
        Vector<Person> result2;
        if (e.getSource() == login.getLoginButton()) {
            for (int i = 0; i < company.getEmployees().size(); i++){
                if (login.getUserField().getText().equals(company.getEmployees().get(i).getName())){
                    if (company.getEmployees().get(i).authenticate(login.getPasswordField().getText())){
                        initializeMainFrame(company.getEmployees().get(i));
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
            if (frame.getOverviewTab().getDepartmentFilter().getSelectedItem().toString().equals("Alle")){
                result = company.getEmployees();
            }
            else{
                result = null;
                result = new Vector<Person>();
                for (int i = 0; i < company.getEmployees().size(); i++){
                    if (company.getEmployees().get(i).getDepartment().equals(frame.getOverviewTab().getDepartmentFilter().getSelectedItem().toString())){
                        result.add(company.getEmployees().get(i));
                    }
                }
            }
            frame.getOverviewTab().getEmployeeList().setListData(result);
        }

        //Filter nach Team
        if (e.getSource() == frame.getOverviewTab().getTeamFilter()){
            result2 = new Vector<Person>();
            if (frame.getOverviewTab().getTeamFilter().getSelectedItem().toString().equals("Alle")){
                result2 = result;
            }
            else{
                for (int i = 0; i < result.size(); i++){
                    //if (result.get(i).get)
                }
            }
        }

        //Andere Filter
        if (e.getSource() == frame.getEmployeeTab().getAddBtn()){
            String[] splited = frame.getEmployeeTab().getNameField().getText().split(" ");
            Person person = new Person(splited[0], splited[1], "");
            if (frame.getEmployeeTab().getAdminCheckBox().isSelected()){
                person.setAdmin(true);
            }
            if (frame.getEmployeeTab().getHrCheckBox().isSelected()){
                person.setHr(true);
            }
            company.getEmployees().add(person);
            frame.getEmployeeTab().getEmployeeList().setListData(company.getEmployees());
        }
        if (e.getSource() == frame.getEmployeeTab().getDelBtn()){
            Person person = company.getEmployees().get(frame.getEmployeeTab().getEmployeeList().getSelectedIndex());
            company.getEmployees().remove(person);
            frame.getEmployeeTab().getEmployeeList().setListData(company.getEmployees());
        }
        if (e.getSource() == frame.getDataTab().getDepartmentPanel().getAddBtn()){
            //company.getDepartments().add(frame.getDataTab().getDepartmentPanel().get);
        }
        /*
        if (e.getSource() == frame.getDataTab().g){
            //company.getDepartments().add(frame.getDataTab().getDepartmentPanel().getName());
        }
        if (e.getSource() == frame.getDataTab().getAddBtn){
            //company.getDepartments().add(frame.getDataTab().getDepartmentPanel().getName());
        }

         */

    }

    class listSelection implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try{
                Person person = company.getEmployees().get(frame.getOverviewTab().getEmployeeList().getSelectedIndex());
                frame.getOverviewTab().getNameField().setText(person.getName());
                frame.getOverviewTab().getNameField().updateUI();

               /*
                frame.getOverviewTab().getDepartmentField().setText(person.getDepartment());
                frame.getOverviewTab().getDepartmentField().updateUI();
                frame.getOverviewTab().getFunctionList().setListData(person.getFunctions());
                frame.getOverviewTab().getFunctionList().updateUI();
                frame.getOverviewTab().getTeamList().setListData(person.getTeams());
                frame.getOverviewTab().getTeamList().updateUI();
                 */

            }
            catch (ArrayIndexOutOfBoundsException ignored){}
        }
    }

}
