package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.company.Company;

/**
 * LogBookTab is a Tab used to view the logbook of a company.
 * @author Daniel Bengl
 */
public class LogBookTab extends JPanel {
    private JTextArea lookbookContents;

    public LogBookTab(Company model) {
        super();
        setLayout(new BorderLayout());
        init();
        build();
    }

    private void build() {
        JScrollPane scrollPane = new JScrollPane(lookbookContents);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void init() {
        lookbookContents = new JTextArea();
        lookbookContents.setColumns(50);
        lookbookContents.setEditable(false);
        lookbookContents.setFont(new Font("Arial", Font.PLAIN, 16));
        for (int i = 0; i < 50; i++) 
            lookbookContents.append("[2022-05-07 11:32:16.113] Logbuch Eintrag Test " + i + "\n");
    }

    // getters
    public JTextArea getTextarea() {
        return lookbookContents;
    }
}