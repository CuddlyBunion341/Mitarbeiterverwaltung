package gui;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JTabbedPane tabbedPanel;
    private UebersichtPanel uebersichtPanel;
    private ZuordnungPanel zuordnungPanel;

    public Main() {
        setTitle("Hello World");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        pack();
        setVisible(true);
    }

    private void init() {
        tabbedPanel = new JTabbedPane();
        uebersichtPanel = new UebersichtPanel();
        zuordnungPanel = new ZuordnungPanel();
        tabbedPanel.addTab("Uebersicht", uebersichtPanel);
        tabbedPanel.addTab("Zuordnung", zuordnungPanel);
        tabbedPanel.setForeground(Color.BLACK);
        add(tabbedPanel);
        getContentPane().add(tabbedPanel);
    }

    public static void main(String[] args) {
        new Main();
    }
}

class UebersichtPanel extends JPanel {
    // personenliste
    private ButtonGroup sortierung;
    private JComboBox abteilungFilter;
    private JComboBox funktionFilter;
    private JComboBox teamFilter;
    private JTextField nameFilter;
    private JList personenListe;

    // detail
    private JTextField nameFeld;
    private JTextField abteilungFeld;
    private JList funktionenList;
    private JList teamsList;
    private JLabel personBild;
    
    public UebersichtPanel() {
        super();
        init();
        setLayout(new BorderLayout());

        // Personen Panel
        JPanel personenPanel = Util.createFieldset("Personen");
        personenPanel.setLayout(new GridLayout(1, 2));
        JPanel persLinks = new JPanel();
        persLinks.setLayout(new BoxLayout(persLinks, BoxLayout.Y_AXIS));
        personenPanel.add(persLinks);
        // Detail Panel
        JPanel detailPanel = Util.createFieldset("Detail");
        detailPanel.setLayout(new GridLayout(4,2));
        personenPanel.add(detailPanel);
        add(personenPanel, BorderLayout.PAGE_START);


        JPanel unterePanel = new JPanel();
        unterePanel.setLayout(new GridLayout(1, 2));

        // Sortier Panel
        JPanel sortPanel = Util.createFieldset("Sortierung");
        sortierung = new ButtonGroup();
        sortierung.add((AbstractButton) sortPanel.add(new JRadioButton("keine")));
        sortierung.add((AbstractButton) sortPanel.add(new JRadioButton("A-Z")));
        sortierung.add((AbstractButton) sortPanel.add(new JRadioButton("Z-A")));
        unterePanel.add(sortPanel);

        // Filter Panel
        JPanel filterPanel = Util.createFieldset("Filter");
        filterPanel.setLayout(new GridLayout(3, 2));
        filterPanel.add(new JLabel("Abteilung"));
        filterPanel.add(abteilungFilter);
        filterPanel.add(new JLabel("Funktion"));
        filterPanel.add(funktionFilter);
        filterPanel.add(new JLabel("Team"));
        filterPanel.add(teamFilter);
        unterePanel.add(filterPanel);

        add(unterePanel, BorderLayout.PAGE_END);
    }

    private void init() {
        // personenliste
        sortierung = new ButtonGroup();
        abteilungFilter = new JComboBox<>(new String[]{"- alle -","Logistik", "IT", "Marketing", "Produktion"});
        funktionFilter = new JComboBox<>(new String[]{"- alle -", "Manager", "Mitarbeiter", "Assistent"});
        teamFilter = new JComboBox<>(new String[]{"- alle -", "Next Facility", "IT", "Marketing", "Produktion"});
        nameFilter = new JTextField();
        personenListe = new JList<>(new String[]{"Person 1", "Person 2", "Person 3", "Person 4", "Person 5"});

        // detail
        nameFeld = new JTextField();
        abteilungFeld = new JTextField();
        funktionenList = new JList<>();
        teamsList = new JList<>();
        personBild = new JLabel();
    }
}

class ZuordnungPanel extends JPanel {
    public ZuordnungPanel() {
        super();
        add(new JLabel("Zuordnung ding bums"));
    }
}
