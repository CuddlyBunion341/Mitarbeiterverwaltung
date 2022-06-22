package main.java.gui;

import javax.swing.*;
import java.awt.*;

/**
 * A simple Utility class for creating various components.
 * @author Daniel Bengl
 */
public class Util {
    /**
     * Creats a fieldset with a title and a content panel.
     * @param title Title of the fieldset. 
     * @return JPanel with the fieldset.
     */
    public static JPanel fieldset(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }
    /**
     * Creates a label with heading format.
     * @param title Title of the label. 
     * @return JLabel with the heading format.
     */
    public static JLabel headingLabel(String title) {
        JLabel label = new JLabel(title);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }
    /**
     * Creates a titled scroll pane
     * @param title Title of the scroll pane.
     * @return JScrollPane with the titled scroll pane.
     */
    public static JScrollPane scrollPane(String title) {
        JScrollPane scroll = new JScrollPane();
        scroll.setColumnHeaderView(headingLabel(title));
        return scroll;
    }
    /**
     * Creates a titled scrollpane with a JList
     * @param title Title of the scroll pane.
     * @param list JList to be placed in the scroll pane.
     * @return JScrollPane with the titled scroll pane.
     */
    public static JScrollPane scrollPane(String title, JList<?> list) {
        JScrollPane scroll = scrollPane(title);
        scroll.setViewportView(list);
        return scroll;
    }
}
