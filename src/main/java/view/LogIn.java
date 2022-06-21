package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame{

    private Bridge bridge;
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel panel = new JPanel(new GridLayout(2,2));
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JLabel userName = new JLabel("Username");
    JLabel password = new JLabel("Password");
    JTextField userNameInput = new JTextField();
    JTextField passwordInput = new JTextField();
    JButton loginButton = new JButton("Login");

    public LogIn(){
        getContentPane().add(mainPanel);
        mainPanel.add(panel, BorderLayout.CENTER);
        panel.add(userName);
        panel.add(userNameInput);
        panel.add(password);
        panel.add(passwordInput);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(loginButton, BorderLayout.EAST);
        loginButton.addActionListener(new Listener());
        setTitle("Login");
        setVisible(true);
        setSize(300,125);
    }

    class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bridge.loginPressed(1);
        }
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }
}
