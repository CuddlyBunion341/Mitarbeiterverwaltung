package main.java;
import javax.swing.JTabbedPane;
import javax.swing.text.View;

import main.java.controller.ViewController;
import main.java.gui.AssigmentTab;
import main.java.gui.DataTab;
import main.java.gui.EmployeeTab;
import main.java.gui.LogBookTab;
import main.java.gui.MainFrame;
import main.java.gui.OverviewTab;
import main.java.model.company.Company;

public class Main {
    public static void main(String[] args) {
        ViewController controller = new ViewController();
        controller.start();
    }
}
