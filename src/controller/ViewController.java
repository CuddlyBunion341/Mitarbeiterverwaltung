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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String selectedPerson = frame.getOverviewTab().getEmployeeList().getValueAt(frame.getOverviewTab().getEmployeeList().getSelectedRow(), 0).toString();
                frame.getOverviewTab().getEmployeeList().getSelectedValue();
            }
            catch (ArrayIndexOutOfBoundsException ignored){}
        }
    }


}
