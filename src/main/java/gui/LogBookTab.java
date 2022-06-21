package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.java.model.company.Company;
import main.java.model.log.LogBook;

/**
 * LogBookTab is a Tab used to view the logbook of a company.
 * @author Daniel Bengl
 */
public class LogBookTab extends JPanel {
    private JTextArea textArea;

    public LogBookTab(Company model) {
        super();
        setLayout(new BorderLayout());
        init();
        build();
    }

    private void build() {
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void init() {
        textArea = new JTextArea();
        textArea.setColumns(50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        for (String entry : LogBook.getInstance().getEntries())
            textArea.append(entry);
    }

    // getters
    public JTextArea getTextarea() {
        return textArea;
    }

    public void update() {
        textArea.setText(LogBook.getInstance().toString());
    }
}