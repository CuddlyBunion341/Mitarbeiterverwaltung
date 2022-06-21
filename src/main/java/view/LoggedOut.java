package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggedOut extends JFrame {

    private Bridge bridge;
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel panel = new JPanel(new GridLayout(1,2));
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JLabel mainLabel = new JLabel("Hier sieht man eine Tabelle mit allen Mitarbeitern");
    JButton loginButton = new JButton("Login");

    public LoggedOut(){
        getContentPane().add(mainPanel);
        mainPanel.add(panel, BorderLayout.CENTER);
        panel.add(mainLabel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(loginButton, BorderLayout.EAST);
        loginButton.addActionListener(new Listener());
        setTitle("Eingeloggt als Mitarbeiter");
        setVisible(true);
        setSize(800, 800);
    }

    class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bridge.loginPressed(0);
        }
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    public JTable getMitarbeiterListe(){
        JTable table = new JTable();
        return table;
    }
}
