package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private OverviewTab overviewTab;
    private AssigmentTab assigmentTab;
    private EmployeeTab employeeTab;
    private DataTab dataTab;
    private LogBookTab logBookTab;

    public MainFrame() {
        setTitle("Hello World");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        pack();
        setVisible(true);
    }

    private void init() {
        tabbedPane = new JTabbedPane();
        JFrame f = this;
        overviewTab = new OverviewTab();
        assigmentTab = new AssigmentTab();
        employeeTab = new EmployeeTab();
        dataTab = new DataTab();
        logBookTab = new LogBookTab();
        tabbedPane.addTab("Uebersicht", overviewTab);
        tabbedPane.addTab("Zuordnung", assigmentTab);
        tabbedPane.addTab("Personen", employeeTab);
        tabbedPane.addTab("Stammdaten", dataTab);
        tabbedPane.addTab("LogBuch", logBookTab);
        tabbedPane.setForeground(Color.BLACK);

        tabbedPane.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent arg0) {
                Component mCompo=tabbedPane.getSelectedComponent();
                tabbedPane.setPreferredSize(mCompo.getPreferredSize());
                f.pack();
            }   
        });
        add(tabbedPane);
        getContentPane().add(tabbedPane);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}

class OverviewTab extends JPanel {
    // personenliste
    private ButtonGroup sortGroup;
    private JComboBox departmentFilter;
    private JComboBox functionFilter;
    private JComboBox teamFilter;
    private JTextField nameFilter;
    private JList employeeList;

    // detail
    private JTextField nameField;
    private JTextField departmentField;
    private JList functionList;
    private JList teamList;
    private JLabel imageLabel;
    
    public OverviewTab() {
        super();
        init();
        setLayout(new BorderLayout());

        // Personen Panel
        JPanel mainPanel = Util.fieldset("Personen");
        mainPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JScrollPane persScroll = Util.scrollPane("Übersicht", employeeList);
        leftPanel.add(persScroll, BorderLayout.CENTER);
        leftPanel.add(nameFilter, BorderLayout.SOUTH);


        mainPanel.add(leftPanel, BorderLayout.WEST);
        // Detail Panel
        JPanel detailsPanel = Util.fieldset("Detail");
        detailsPanel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2,2));
        topPanel.add(new JLabel("Name: "));
        topPanel.add(nameField);
        topPanel.add(new JLabel("Abteilung: "));
        topPanel.add(departmentField);
        JPanel detailsBottomPanel = new JPanel(new GridLayout(1,2));
        JScrollPane functionsPane = Util.scrollPane("Funktionen", functionList);
        detailsBottomPanel.add(functionsPane);
        JScrollPane teamsPane = Util.scrollPane("Teams", teamList);
        detailsBottomPanel.add(teamsPane);
        //
        detailsPanel.add(topPanel, BorderLayout.NORTH);
        detailsPanel.add(detailsBottomPanel, BorderLayout.CENTER);

        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);


        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 2));

        // Sortier Panel
        JPanel sortPanel = Util.fieldset("Sortierung");
        sortGroup = new ButtonGroup();
        sortGroup.add((AbstractButton) sortPanel.add(new JRadioButton("keine")));
        sortGroup.add((AbstractButton) sortPanel.add(new JRadioButton("A-Z")));
        sortGroup.add((AbstractButton) sortPanel.add(new JRadioButton("Z-A")));
        optionsPanel.add(sortPanel);

        // Filter Panel
        JPanel filterPanel = Util.fieldset("Filter");
        filterPanel.setLayout(new GridLayout(3, 2));
        filterPanel.add(new JLabel("Abteilung"));
        filterPanel.add(departmentFilter);
        filterPanel.add(new JLabel("Funktion"));
        filterPanel.add(functionFilter);
        filterPanel.add(new JLabel("Team"));
        filterPanel.add(teamFilter);
        optionsPanel.add(filterPanel);

        add(optionsPanel, BorderLayout.PAGE_END);
    }

    private void init() {
        // personenliste
        sortGroup = new ButtonGroup();
        departmentFilter = new JComboBox<>(new String[]{"- alle -","Logistik", "IT", "Marketing", "Produktion"});
        functionFilter = new JComboBox<>(new String[]{"- alle -", "Manager", "Mitarbeiter", "Assistent"});
        teamFilter = new JComboBox<>(new String[]{"- alle -", "Next Facility", "IT", "Marketing", "Produktion"});
        nameFilter = new JTextField("Name eingeben");
        java.util.List<String> employees = new ArrayList<>();
        for (int i = 0 ; i < 30; i++) {
            employees.add("Person " + i);
        }

        employeeList = new JList<>(employees.toArray());
        // detail
        nameField = new JTextField();
        departmentField = new JTextField();
        functionList = new JList<>(new String[]{"Controller","Betriebs-Sani"});
        teamList = new JList<>(new String[]{"More cash","New Customer","Idea 3000","Leitbild"});
        imageLabel = new JLabel();
    }
}

class AssigmentTab extends JPanel {
    private JList employeeList;
    private JTextField nameField;
    private JTextField departmentField;
    private JComboBox functionsBox;
    private JComboBox teamsBox;
    public AssigmentTab() {
        super();
        init();
        setLayout(new BorderLayout());
        JPanel mainPanel = Util.fieldset("Personen bearbeiten");
        mainPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.add(Util.scrollPane("Übersicht", employeeList));
        mainPanel.add(leftPanel, BorderLayout.WEST);
        
        JPanel detailsPanel = Util.fieldset("Detail");
        detailsPanel.setLayout(new BorderLayout());
        JPanel detailsContentPanel = new JPanel(new GridLayout(4,2));
        detailsContentPanel.add(new JLabel("Name: "));
        detailsContentPanel.add(nameField);
        detailsContentPanel.add(new JLabel("Abteilung: "));
        detailsContentPanel.add(departmentField);
        detailsContentPanel.add(new JLabel("Funktion: "));  
        detailsContentPanel.add(functionsBox);
        detailsContentPanel.add(new JLabel("Team: "));
        detailsContentPanel.add(teamsBox);
        detailsPanel.add(detailsContentPanel, BorderLayout.PAGE_START);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void init() {
        String[] employees = new String[50];
        for (int i = 0; i < 50; i++) {
            employees[i] = "Person " + i;
        }
        employeeList = new JList<String>(employees);
        nameField = new JTextField();
        departmentField = new JTextField();
        functionsBox = new JComboBox<>(new String[]{"Controller","Betriebs-Sani"});
        teamsBox = new JComboBox<>(new String[]{"More cash","New Customer","Idea 3000","Leitbild"});
    }
}

class EmployeeTab extends JPanel {
    private JList employeeList;
    private JTextField nameField;
    private JCheckBox hrCheckBox;
    private JCheckBox adminCheckBox;
    private JLabel imageLabel;
    private JButton addBtn;
    private JButton delBtn;
    private JButton editBtn;

    public EmployeeTab() {
        super();
        init();

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
        employeeList = new JList<>(new String[]{"Person 1", "Person 2", "Person 3"});
        nameField = new JTextField();
        hrCheckBox = new JCheckBox();
        adminCheckBox = new JCheckBox();    
        addBtn = new JButton("+");
        delBtn = new JButton("x");
        editBtn = new JButton("E");
        imageLabel = new JLabel("Bild");
    }
}

class DataTab extends JPanel {
    private JTextField companyNameField;
    private JList departmentList;
    private JList functionsList;
    private JList teamsList;
    
    private ListEditPanel departmentPanel;
    private ListEditPanel functionsPanel;
    private ListEditPanel teamsPanel;

    public DataTab() {
        super();
        init();

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        topPanel.add(new JLabel("Firma: "));
        topPanel.add(companyNameField);
        JPanel bottomPanel = new JPanel(new GridLayout(3,2));
        bottomPanel.add(new JLabel("Abteilungen: "));
        bottomPanel.add(departmentPanel);
        bottomPanel.add(new JLabel("Funktionen: "));
        bottomPanel.add(functionsPanel);
        bottomPanel.add(new JLabel("Teams: "));
        bottomPanel.add(teamsPanel);
        add(topPanel, BorderLayout.PAGE_START);
        add(bottomPanel, BorderLayout.CENTER);
    }

    private void init() {
        companyNameField = new JTextField();
        departmentList = new JList<>(new String[]{"Logistik", "IT", "Marketing", "Produktion"});
        functionsList = new JList<>(new String[]{"Controller", "Betriebs-Sani"});
        teamsList = new JList<>(new String[]{"More cash", "New Customer", "Idea 3000", "Leitbild"});

        departmentPanel = new ListEditPanel(departmentList);
        functionsPanel = new ListEditPanel(functionsList);
        teamsPanel = new ListEditPanel(teamsList);
    }

}

class LogBookTab extends JPanel {
    private JTextArea logbookContents;

    public LogBookTab() {
        super();
        setLayout(new BorderLayout());
        logbookContents = new JTextArea();
        logbookContents.setColumns(50);
        for (int i = 0; i < 50; i++) {
            logbookContents.append("[2022-05-07 11:32:16.113] Logbuch Eintrag Test " + i + "\n");
        }
        logbookContents.setEditable(false);
        logbookContents.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(logbookContents);
        add(scrollPane, BorderLayout.CENTER);
    }
}

class ListEditPanel extends JPanel {
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