package controller;

import gui.*;
import model.company.Company;
import view.Bridge;
import view.LogIn;
import view.LoggedIn;
import view.LoggedOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController extends JFrame implements ActionListener {
    Login login = new Login();

    public void start(){
        login.getLoginButton().addActionListener(this);
        login.getResetButton().addActionListener(this);
        login.getShowPassword().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.getLoginButton()) {

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

    public void initializeMainFrame(){
        Company model = new Company("Mitarbeiterverwaltung v2.31");
        MainFrame frame = new MainFrame(true,true);
        frame.setVisible(true);

        JTabbedPane tabs = frame.getTabbedPane();


    }
}
