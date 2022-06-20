import javax.swing.JTabbedPane;

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

        Company model = new Company("Super duper Firma");
        MainFrame frame = new MainFrame();
        frame.setVisible(true);

        JTabbedPane tabs = frame.getTabbedPane();

        tabs.addTab("Uebersicht", new OverviewTab());
        tabs.addTab("Zuordnung", new AssigmentTab());
        tabs.addTab("Personen", new EmployeeTab());
        tabs.addTab("Stammdaten", new DataTab());
        tabs.addTab("LogBuch", new LogBookTab());

    }
}
