package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPanel;
    private UebersichtPanel uebersichtPanel;
    private ZuordnungPanel zuordnungPanel;
    private PersonenPanel personenPanel;
    private StammdatenPanel stammdatenPanel;
    private LogBuchPanel logBuchPanel;

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
        personenPanel = new PersonenPanel();
        stammdatenPanel = new StammdatenPanel();
        logBuchPanel = new LogBuchPanel();
        tabbedPanel.addTab("Uebersicht", uebersichtPanel);
        tabbedPanel.addTab("Zuordnung", zuordnungPanel);
        tabbedPanel.addTab("Personen", personenPanel);
        tabbedPanel.addTab("Stammdaten", stammdatenPanel);
        tabbedPanel.addTab("LogBuch", logBuchPanel);
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

class PersonenPanel extends JPanel {
    private JList personenListe;
    private JTextField nameFeld;
    private JCheckBox hrCheckBox;
    private JCheckBox adminCheckBox;
    private JButton addBtn;
    private JButton loeschBtn;
    private JButton bearbeitBtn;

    public PersonenPanel() {
        super();
        init();

        setLayout(new BorderLayout());
        JPanel mainPanel = Util.fieldset("Personen bearbeiten");
        mainPanel.setLayout(new BorderLayout());
        ListenBearbeitungPanel auswahlPanel = new ListenBearbeitungPanel(personenListe,"Übersicht");

        JPanel detailPanel = Util.fieldset("Detail");
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        JPanel detailOben = new JPanel(new GridLayout(1,2));
        detailOben.add(new JLabel("Name: "));
        detailOben.add(nameFeld);
        detailPanel.add(detailOben);
        detailPanel.add(hrCheckBox);
        detailPanel.add(adminCheckBox);

        mainPanel.add(auswahlPanel, BorderLayout.WEST);
        mainPanel.add(detailPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void init() {
        personenListe = new JList<>(new String[]{"Person 1", "Person 2", "Person 3"});
        nameFeld = new JTextField();
        hrCheckBox = new JCheckBox("HR");
        adminCheckBox = new JCheckBox("Admin");    
        addBtn = new JButton("+");
        loeschBtn = new JButton("x");
        bearbeitBtn = new JButton("E");
    }
}

class StammdatenPanel extends JPanel {
    private JTextField firmaFeld;
    private JList abteilungenListe;
    private JList funktionenListe;
    private JList teamsListe;
    
    private ListenBearbeitungPanel abteilungenPanel;
    private ListenBearbeitungPanel funktionenPanel;
    private ListenBearbeitungPanel teamsPanel;

    public StammdatenPanel() {
        super();
        init();

        setLayout(new GridLayout(4,2));
        add(new JLabel("Firma: "));
        add(firmaFeld);
        add(new JLabel("Abteilungen: "));
        add(abteilungenPanel);
        add(new JLabel("Funktionen: "));
        add(funktionenPanel);
        add(new JLabel("Teams: "));
        add(teamsPanel);
    }

    private void init() {
        firmaFeld = new JTextField();
        abteilungenListe = new JList<>(new String[]{"Logistik", "IT", "Marketing", "Produktion"});
        funktionenListe = new JList<>(new String[]{"Controller", "Betriebs-Sani"});
        teamsListe = new JList<>(new String[]{"More cash", "New Customer", "Idea 3000", "Leitbild"});

        abteilungenPanel = new ListenBearbeitungPanel(abteilungenListe);
        funktionenPanel = new ListenBearbeitungPanel(funktionenListe);
        teamsPanel = new ListenBearbeitungPanel(teamsListe);
    }

}

class LogBuchPanel extends JPanel {
    private JTextArea logBuch;

    public LogBuchPanel() {
        super();
        setLayout(new BorderLayout());
        logBuch = new JTextArea();
        for (int i = 0; i < 50; i++) {
            logBuch.append("[2022-05-07 11:32:16.113] Logbuch Eintrag Test " + i + "\n");
        }
        logBuch.setEditable(false);
        logBuch.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(logBuch);
        add(scrollPane, BorderLayout.CENTER);
    }
}

class ListenBearbeitungPanel extends JPanel {
    private JList liste;
    private JButton addBtn;
    private JButton bearbeitBtn;
    private JButton loeschBtn;

    public ListenBearbeitungPanel(JList liste) {
        super();
        this.liste = liste;
        init();
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(liste);
        add(scrollPane, BorderLayout.CENTER);
        addBtns();
    }

    private void addBtns() {
        JPanel btnJPanel = new JPanel(new GridLayout(1,3));
        btnJPanel.add(addBtn);
        btnJPanel.add(bearbeitBtn);
        btnJPanel.add(loeschBtn);
        add(btnJPanel, BorderLayout.PAGE_END);
    }

    public ListenBearbeitungPanel(JList liste, String titel) {
        super();
        this.liste = liste;
        init();
        setLayout(new BorderLayout());
        JScrollPane scrollPane = Util.scrollPane(titel, liste);
        add(scrollPane, BorderLayout.CENTER);
        addBtns();
    }

    private void init() {
        addBtn = new JButton("+");
        loeschBtn = new JButton("x");
        bearbeitBtn = new JButton("b");
    }

    public int getSelectedIndex() {
        return liste.getSelectedIndex();
    }

    public Object getSelectedValue() {
        return liste.getSelectedValue();
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getBearbeitBtn() {
        return bearbeitBtn;
    }

    public JButton getLoeschBtn() {
        return loeschBtn;
    }
}