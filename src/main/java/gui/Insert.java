package main.java.gui;

import javax.swing.*;
import java.awt.*;

public class Insert extends JFrame {

    JPanel mainPanel = new JPanel(new GridLayout(2,2));
    JLabel insert = new JLabel("Eingabe ");
    JTextField insertField = new JTextField();
    JButton submit = new JButton("Bestätigen");
    JLabel placeHolder = new JLabel(" ");


    public Insert(String name){
        setTitle(name);
        getContentPane().setLayout(new BorderLayout());
        mainPanel.add(insert);
        mainPanel.add(insertField);
        mainPanel.add(placeHolder);
        getContentPane().add(submit, BorderLayout.EAST);

        getContentPane().add(mainPanel, BorderLayout.NORTH);
        setVisible(true);
        setSize(300,100);
        setResizable(false);
    }

    public JButton getSubmit() {
        return submit;
    }

    public JTextField getInsertField() {
        return insertField;
    }

    public void setInsertField(JTextField insertField) {
        this.insertField = insertField;
    }

}
