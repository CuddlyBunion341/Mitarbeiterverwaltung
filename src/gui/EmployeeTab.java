package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.company.Company;

public class EmployeeTab extends JPanel {
    private JList employeeList;
    private JTextField nameField;
    private JCheckBox hrCheckBox;
    private JCheckBox adminCheckBox;
    private JLabel imageLabel;
    private JButton addBtn;
    private JButton delBtn;
    private JButton editBtn;

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
        ListEditPanel selectionPanel = new ListEditPanel(employeeList,"Übersicht");

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
        nameField = new JTextField();
        hrCheckBox = new JCheckBox();
        adminCheckBox = new JCheckBox();    
        addBtn = new JButton("+");
        delBtn = new JButton("x");
        editBtn = new JButton("E");
        imageLabel = new JLabel("Bild");
    }
    // getters

    public JList getEmployeeList() {
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
        return this.addBtn;
    }

    public JButton getDelBtn() {
        return this.delBtn;
    }

    public JButton getEditBtn() {
        return this.editBtn;
    }
}