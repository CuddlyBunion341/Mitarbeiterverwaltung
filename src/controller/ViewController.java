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
        frame.getOverviewTab().getEmployeeList().addListSelectionListener(new listSelection());
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
                        if (company.getEmployees().get(i).getName().toLowerCase(Locale.ROOT).contains(frame.getOverviewTab().getNameFilter().getText())){
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
    }

    class listSelection implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try{
                Person person = company.getEmployees().get(frame.getOverviewTab().getEmployeeList().getSelectedIndex());
                frame.getOverviewTab().getNameField().setText(person.getName());
                //frame.getOverviewTab().getDepartmentField().setText(person.getDepartment);
                frame.getOverviewTab().getFunctionList().setListData(person.getRoles());
                frame.getOverviewTab().getTeamList().setListData(person.getTeams());
            }
            catch (ArrayIndexOutOfBoundsException ignored){}
        }
    }

}
