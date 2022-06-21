package gui;

import javax.swing.*;
import java.awt.*;

/**
 * A simple Utility class for creating various components.
 * @author Daniel Bengl
 */
public class Util {
    public static JPanel fieldset(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }
    public static JLabel headingLabel(String title) {
        JLabel label = new JLabel(title);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        return label;
    }
    public static JScrollPane scrollPane(String title) {
        JScrollPane scroll = new JScrollPane();
        scroll.setColumnHeaderView(headingLabel(title));
        return scroll;
    }
    public static JScrollPane scrollPane(String title, JList<?> list) {
        JScrollPane scroll = scrollPane(title);
        scroll.setViewportView(list);
        return scroll;
    }
}
