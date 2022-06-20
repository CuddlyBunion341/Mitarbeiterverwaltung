import javax.swing.JTabbedPane;
import javax.swing.text.View;

import controller.ViewController;
import gui.AssigmentTab;
import gui.DataTab;
import gui.EmployeeTab;
import gui.LogBookTab;
import gui.MainFrame;
import gui.OverviewTab;
import model.company.Company;

public class Main {
    public static void main(String[] args) {
        ViewController controller = new ViewController();
        controller.start();
    }
}
