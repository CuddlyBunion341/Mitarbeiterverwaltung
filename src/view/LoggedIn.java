package view;

import javax.swing.*;
import java.awt.*;

public class LoggedIn extends JFrame {

    JPanel mainPanel = new JPanel(new BorderLayout());
    JLabel mainLabel = new JLabel("Hier kommen die Admin / HR Tools hin");


    public LoggedIn(){
        getContentPane().add(mainPanel);
        mainPanel.add(mainLabel, BorderLayout.CENTER);
        setTitle("Eingeloggt als Admin / HR");
        setVisible(true);
        setSize(800,800);
    }

}
