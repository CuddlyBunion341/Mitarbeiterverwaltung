package main.java.gui;

import javax.swing.*;
import java.awt.*;

class LoginFrame extends JFrame {

    // Creating the GUI.
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
 


  // A constructor.
    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }

   /**
    * This function sets the layout of the container to null.
    */
    public void setLayoutManager() {
        container.setLayout(null);
    }

   /**
    * This function sets the location and size of the components in the frame
    */
    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }
/**
 * This function adds all the components to the container
 */

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public JTextField getUserTextField(){
        return userTextField;
    }
    public JPasswordField getPasswordField(){
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JCheckBox getShowPassword() {
        return showPassword;
    }
}

/**
 * It creates a new instance of the LoginFrame class and sets the title, visibility, size, and other
 * properties of the frame
 */
public class Login {
    LoginFrame frame = new LoginFrame();
    public Login() {
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    public JTextField getUserField(){
        return frame.getUserTextField();
    }
    public JPasswordField getPasswordField(){
        return frame.getPasswordField();
    }
    public JButton getLoginButton(){
        return frame.loginButton;
    }
    public JButton getResetButton(){
        return frame.resetButton;
    }
    public JCheckBox getShowPassword(){
        return frame.showPassword;
    }
    public JFrame getFrame() {
        return frame;
    }
}


