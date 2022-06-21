package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A simple button for viewing, updating and deleting elements from a JList.
 * @author Daniel Bengl
 */
public class ListEditPanel extends JPanel {
    private JList list;
    private JButton addBtn;
    private JButton editBtn;
    private JButton remBtn;

    public ListEditPanel(JList liste) {
        super();
        this.list = liste;
        init();
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(liste);
        add(scrollPane, BorderLayout.CENTER);
        addBtns();
    }

    private void addBtns() {
        JPanel btnPanel = new JPanel(new GridLayout(1,3));
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(remBtn);
        add(btnPanel, BorderLayout.PAGE_END);
    }

    public ListEditPanel(JList list, String title) {
        super();
        this.list = list;
        init();
        setLayout(new BorderLayout());
        JScrollPane scrollPane = Util.scrollPane(title, list);
        add(scrollPane, BorderLayout.CENTER);
        addBtns();
    }

    private void init() {
        addBtn = new JButton("+");
        remBtn = new JButton("x");
        editBtn = new JButton("b");
    }

    // getters
    
    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    public Object getSelectedValue() {
        return list.getSelectedValue();
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getEditBtn() {
        return editBtn;
    }

    public JButton getRemBtn() {
        return remBtn;
    }
}