package main.java.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.model.company.Company;
import main.java.model.company.Person;

/**
 * AssignmentTab is a JPanel that contains all the components for the assignment tab.
 * This tab is used to view employee data.
 * @author Daniel Bengl
 */
public class EmployeeTab extends JPanel {
    private JList<Person> employeeList;
    private JTextField nameField;
    private JCheckBox hrCheckBox;
    private JCheckBox adminCheckBox;
    private JLabel imageLabel;
    private JButton addBtn;
    private JButton delBtn;
    private JButton editBtn;
    private ListEditPanel selectionPanel;

    private Company model;

    public EmployeeTab(Company model) {
        super();
        this.model = model;
        init();
        build();
    }
    private void build() {
        setLayout(new BorderLayout());
        JPanel mainPanel = Util.fieldset("Personen bearbeiten");
        mainPanel.setLayout(new BorderLayout());
        JPanel detailPanel = Util.fieldset("Detail");
        detailPanel.setLayout(new BorderLayout());
        JPanel detailContent = new JPanel(new GridLayout(4,2));
        detailContent.add(new JLabel("Name: "));
        detailContent.add(nameField);
        detailContent.add(new JLabel("[Auswaehlen]"));
        detailContent.add(imageLabel);
        detailContent.add(new JLabel("HR"));
        detailContent.add(hrCheckBox);
        detailContent.add(new JLabel("Admin"));
        detailContent.add(adminCheckBox);

        detailPanel.add(detailContent,BorderLayout.NORTH);

        mainPanel.add(selectionPanel, BorderLayout.WEST);
        mainPanel.add(detailPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }
    private void init() {
        employeeList = new JList<>(model.getEmployees());
        selectionPanel = new ListEditPanel(employeeList,"Übersicht");
        nameField = new JTextField();
        hrCheckBox = new JCheckBox();
        adminCheckBox = new JCheckBox();    
        imageLabel = new JLabel("Bild");
    }
    // getters

    public JList<Person> getEmployeeList() {
        return this.employeeList;
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JCheckBox getHrCheckBox() {
        return this.hrCheckBox;
    }

    public JCheckBox getAdminCheckBox() {
        return this.adminCheckBox;
    }

    public JLabel getImageLabel() {
        return this.imageLabel;
    }

    public JButton getAddBtn() {
        return selectionPanel.getAddBtn();
    }

    public JButton getDelBtn() {
        return selectionPanel.getRemBtn();
    }

    public JButton getEditBtn() {
        return selectionPanel.getEditBtn();
    }
}