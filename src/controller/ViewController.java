package controller;

import gui.*;
import model.company.Company;
import view.Bridge;
import view.LogIn;
import view.LoggedIn;
import view.LoggedOut;

import javax.swing.*;

public class ViewController {
    public void start(){
        //ViewController controller = new ViewController();
        //controller.start();

        Company model = new Company("Mitarbeiterverwaltung v2.31");
        MainFrame frame = new MainFrame(true,true);
        frame.setVisible(true);

        JTabbedPane tabs = frame.getTabbedPane();


    }
}
