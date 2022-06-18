package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPanel;
    private UebersichtPanel uebersichtPanel;
    private ZuordnungPanel zuordnungPanel;

    public MainFrame() {
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
        new MainFrame();
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
    private JList funktionenListe;
    private JList teamsListe;
    private JLabel personBild;
    
    public UebersichtPanel() {
        super();
        init();
        setLayout(new BorderLayout());

        // Personen Panel
        JPanel personenPanel = Util.fieldset("Personen");
        personenPanel.setLayout(new BorderLayout());
        JPanel persLinks = new JPanel(new BorderLayout());
        JScrollPane persScroll = Util.scrollPane("Übersicht", personenListe);
        persLinks.add(persScroll, BorderLayout.CENTER);
        persLinks.add(nameFilter, BorderLayout.SOUTH);


        personenPanel.add(persLinks, BorderLayout.WEST);
        // Detail Panel
        JPanel detailPanel = Util.fieldset("Detail");
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        JPanel detailOben = new JPanel(new GridLayout(2,2));
        detailOben.add(new JLabel("Name: "));
        detailOben.add(nameFeld);
        detailOben.add(new JLabel("Abteilung: "));
        detailOben.add(abteilungFeld);
        detailPanel.add(detailOben);
        JPanel detailUnten = new JPanel(new GridLayout(1,2));
        JScrollPane funktionenScroll = Util.scrollPane("Funktionen", funktionenListe);
        detailUnten.add(funktionenScroll);
        JScrollPane teamsScroll = Util.scrollPane("Teams", teamsListe);
        detailUnten.add(teamsScroll);
        detailPanel.add(detailUnten);

        personenPanel.add(detailPanel, BorderLayout.CENTER);
        add(personenPanel, BorderLayout.PAGE_START);


        JPanel unterePanel = new JPanel();
        unterePanel.setLayout(new GridLayout(1, 2));

        // Sortier Panel
        JPanel sortPanel = Util.fieldset("Sortierung");
        sortierung = new ButtonGroup();
        sortierung.add((AbstractButton) sortPanel.add(new JRadioButton("keine")));
        sortierung.add((AbstractButton) sortPanel.add(new JRadioButton("A-Z")));
        sortierung.add((AbstractButton) sortPanel.add(new JRadioButton("Z-A")));
        unterePanel.add(sortPanel);

        // Filter Panel
        JPanel filterPanel = Util.fieldset("Filter");
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
        nameFilter = new JTextField("Name eingeben");
        java.util.List<String> personen = new ArrayList<>();
        for (int i = 0 ; i < 30; i++) {
            personen.add("Person " + i);
        }

        personenListe = new JList<>(personen.toArray());
        // detail
        nameFeld = new JTextField();
        abteilungFeld = new JTextField();
        funktionenListe = new JList<>(new String[]{"Controller","Betriebs-Sani"});
        teamsListe = new JList<>(new String[]{"More cash","New Customer","Idea 3000","Leitbild"});
        personBild = new JLabel();
    }
}

class ZuordnungPanel extends JPanel {
    private JList personenListe;
    private JTextField nameFeld;
    private JTextField abteilungFeld;
    private JComboBox funktionBox;
    private JComboBox teamBox;
    public ZuordnungPanel() {
        super();
        init();
        setLayout(new BorderLayout());
        JPanel mainPanel = Util.fieldset("Personen bearbeiten");
        mainPanel.setLayout(new BorderLayout());
        JPanel links = new JPanel();
        links.add(Util.scrollPane("Übersicht", personenListe));
        mainPanel.add(links, BorderLayout.WEST);
        
        JPanel detailPanel = Util.fieldset("Detail");
        detailPanel.setLayout(new BorderLayout());
        JPanel detailOben = new JPanel(new GridLayout(4,2));
        detailOben.add(new JLabel("Name: "));
        detailOben.add(nameFeld);
        detailOben.add(new JLabel("Abteilung: "));
        detailOben.add(abteilungFeld);
        detailOben.add(new JLabel("Funktion: "));  
        detailOben.add(funktionBox);
        detailOben.add(new JLabel("Team: "));
        detailOben.add(teamBox);
        detailPanel.add(detailOben, BorderLayout.PAGE_START);
        mainPanel.add(detailPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void init() {
        String[] personen = new String[50];
        for (int i = 0; i < 50; i++) {
            personen[i] = "Person " + i;
        }
        personenListe = new JList<String>(personen);
        nameFeld = new JTextField();
        abteilungFeld = new JTextField();
        funktionBox = new JComboBox<>(new String[]{"Controller","Betriebs-Sani"});
        teamBox = new JComboBox<>(new String[]{"More cash","New Customer","Idea 3000","Leitbild"});
    }
}
