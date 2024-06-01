/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author asus
 */
public class TeamGuide extends javax.swing.JFrame {
    private int userId;
    private String username;
    private String email;
    private ArrayList<String> elements = new ArrayList<>();
    private ArrayList<String> weapons = new ArrayList<>();
    
    private JPanel cloneablePanelAutomaton;
    private JPanel cloneablePanelElementalLifeforms;
    private JPanel cloneablePanelEnemiesOfNote;
    private JPanel cloneablePanelFatui;
    private JPanel cloneablePanelHilichurls;
    private JPanel cloneablePanelMythicalBeasts;
    private JPanel cloneablePanelOtherHumanFactions;
    private JPanel cloneablePanelTheAbyss;
    
    private ArrayList<ArrayList<EnemyPanel>> enemyPanelList = new ArrayList<>();
    
    private ArrayList<EnemyPanel> automatonsPanel = new ArrayList<>();
    private ArrayList<EnemyPanel> elementalLifeformsPanel = new ArrayList<>();
    private ArrayList<EnemyPanel> enemiesOfNotePanel = new ArrayList<>();
    private ArrayList<EnemyPanel> fatuiPanel = new ArrayList<>();
    private ArrayList<EnemyPanel> hilichurlsPanel = new ArrayList<>();
    private ArrayList<EnemyPanel> mythicalBeastsPanel = new ArrayList<>();
    private ArrayList<EnemyPanel> otherHumanFactionsPanel = new ArrayList<>();
    private ArrayList<EnemyPanel> theAbyssPanel = new ArrayList<>();
    
    /**
     * Creates new form TeamGuide
     */
    public TeamGuide() {
        initComponents();
        this.userId = 1;
        setLocationRelativeTo(null);
        teamPage1();
    }
    
    public TeamGuide(int userId, String username, String email) {
        initComponents();
        this.userId = userId;
        this.username = username;
        this.email = email;
        setLocationRelativeTo(null);
        teamPage1();
    }
    
    private void teamPage1(){
        parentPanel.removeAll();
        parentPanel.add(teamPage1);
        parentPanel.revalidate();
        parentPanel.repaint();
        
        usernameLabel.setText(username);
        emailLabel.setText(email);
        
        enemiesPane.setOpaque(false);
        enemiesPane.setBorder(null);
        
        enemyPanelList.add(automatonsPanel);
        enemyPanelList.add(elementalLifeformsPanel);
        enemyPanelList.add(enemiesOfNotePanel);
        enemyPanelList.add(fatuiPanel);
        enemyPanelList.add(hilichurlsPanel);
        enemyPanelList.add(mythicalBeastsPanel);
        enemyPanelList.add(otherHumanFactionsPanel);
        enemyPanelList.add(theAbyssPanel);
        
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setViewportBorder(null);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        clonePanelContainer = new JPanel(); // The initial panel inside scroll pane
        clonePanelContainer.setLayout(null); // Use absolute layout
        clonePanelContainer.setPreferredSize(new Dimension(400, 200)); // Set initial size
        clonePanelContainer.setOpaque(false);
        clonePanelContainer.setBackground(new Color(0,0,0,0));
        clonePanelContainer.setBorder(null);
        scrollPane.setViewportView(clonePanelContainer); // Set this panel as viewport's view
        
        cloneablePanelAutomaton = new JPanel();
        setThePanelPage1(cloneablePanelAutomaton, 0, 0, "Automatons");
        
        cloneablePanelElementalLifeforms = new JPanel();
        setThePanelPage1(cloneablePanelElementalLifeforms, 0, cloneablePanelAutomaton.getPreferredSize().height+20 + cloneablePanelAutomaton.getY(), "Elemental Lifeforms");
        
        cloneablePanelEnemiesOfNote= new JPanel();
        setThePanelPage1(cloneablePanelEnemiesOfNote, 0, cloneablePanelElementalLifeforms.getPreferredSize().height+20+ cloneablePanelElementalLifeforms.getY(), "Enemies of Note");
        
        cloneablePanelFatui= new JPanel();
        setThePanelPage1(cloneablePanelFatui, 0, cloneablePanelEnemiesOfNote.getPreferredSize().height+20+cloneablePanelEnemiesOfNote.getY(), "Fatui");
        
        cloneablePanelHilichurls= new JPanel();
        setThePanelPage1(cloneablePanelHilichurls, 0, cloneablePanelFatui.getPreferredSize().height+20+cloneablePanelFatui.getY(), "Hilichurls");
        
        cloneablePanelMythicalBeasts= new JPanel();
        setThePanelPage1(cloneablePanelMythicalBeasts, 0, cloneablePanelHilichurls.getPreferredSize().height+20+cloneablePanelHilichurls.getY(), "Mythical Beasts");
        
        cloneablePanelOtherHumanFactions= new JPanel();
        setThePanelPage1(cloneablePanelOtherHumanFactions, 0, cloneablePanelMythicalBeasts.getPreferredSize().height+20+cloneablePanelMythicalBeasts.getY(), "Other Human Factions");
        
        cloneablePanelTheAbyss= new JPanel();
        setThePanelPage1(cloneablePanelTheAbyss, 0, cloneablePanelOtherHumanFactions.getPreferredSize().height+20+cloneablePanelOtherHumanFactions.getY(), "The Abyss");
    
        adjustContainerSizePage1();
    }
    
    private void setThePanelPage1(JPanel newPanel, int xPanel, int yPanel, String type){
        newPanel.setLayout(null);
        newPanel.setPreferredSize(new Dimension(400, 200)); 
        newPanel.setOpaque(false);
        newPanel.setBackground(new Color(0,0,0,0));
        newPanel.setBorder(BorderFactory.createEmptyBorder());
        
        JLabel typeLabel = new JLabel(type);
        typeLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 28));
        typeLabel.setForeground(new Color(252,236,214));
        typeLabel.setBounds(10,10, typeLabel.getPreferredSize().width+20, typeLabel.getPreferredSize().height);
        newPanel.add(typeLabel);
        newPanel.setComponentZOrder(typeLabel, 0);
    
        App.ImageLoader loader1 = new App.ImageLoader();
        loader1.emptyFileName();
        ArrayList<BufferedImage> imageList = loader1.loadImagesFromFolder("src/App/image/EnemyCard/"+type);
        ArrayList<String> nameList = loader1.returnFileNames();

        int row=0, column=0;
        for(int i=0; i<imageList.size();i++){
            BufferedImage image = imageList.get(i);
            String enemyName = nameList.get(i);
            
            int panelWidth = 100;
            int panelHeight = 100;
            
            EnemyPanel clonedPanel = new EnemyPanel(enemyName, type);
            clonedPanel.settingMouse(image);
            clonedPanel.settingPanel(image, enemyName, panelWidth, panelHeight,10,false);

            // Calculate the row and column indices
            row = i / 4;
            column = i % 4;

            // Calculate the x and y positions based on row and column indices
            int x = 10 + column * (panelWidth + 20);
            int y = 10 + row * (panelHeight + 20) + typeLabel.getPreferredSize().height + typeLabel.getY();

            // Set the bounds for the cloned panel with your custom size
            clonedPanel.setBounds(x, y, panelWidth, panelHeight);
            
            // Add the cloned panel to the initial panel
            newPanel.add(clonedPanel);
            // Adjust preferred size of initial panel to include new panel
            Dimension newSize = new Dimension(newPanel.getWidth(), y + panelHeight + 10 + typeLabel.getPreferredSize().height+ typeLabel.getY()); // Adjusted size
            newPanel.setPreferredSize(newSize);
            // Ensure the scroll pane updates its viewport
            scrollPane.revalidate();
            scrollPane.repaint();
            // Scroll to show the new panel
            scrollPane.getVerticalScrollBar().setValue(0);
            
            ArrayList<EnemyPanel> list = checkNameFromType(type);
            list.add(clonedPanel);
        }
        
        newPanel.setPreferredSize(new Dimension(newPanel.getWidth(), row * (100 + 20) + 100 + 20 + typeLabel.getHeight() + 10));
        newPanel.setBounds(xPanel, yPanel, 500, newPanel.getPreferredSize().height);
        clonePanelContainer.add(newPanel);
        clonePanelContainer.revalidate();
        clonePanelContainer.repaint();

    }
    
    private void adjustContainerSizePage1() {
        int totalHeight = 0;
        for (Component comp : clonePanelContainer.getComponents()) {
            if (comp instanceof JPanel) {
                totalHeight += comp.getPreferredSize().height + 20;
            }
        }
        clonePanelContainer.setPreferredSize(new Dimension(clonePanelContainer.getWidth(), totalHeight));
        scrollPane.revalidate();
        scrollPane.repaint();
    }
    
    private ArrayList<EnemyPanel> checkNameFromType(String type){
        switch(type){
            case "Automatons":
                return automatonsPanel;
                
            case "Elemental Lifeforms" :
                return elementalLifeformsPanel;
                
            case "Enemies of Note" :
                return enemiesOfNotePanel;
            
            case "Fatui" :
                return fatuiPanel;
            
            case "Hilichurls":
                return hilichurlsPanel;
            
            case "Mythical Beasts":
                return mythicalBeastsPanel;
            
            case "Other Human Factions":
                return otherHumanFactionsPanel;
            
            case "The Abyss":
                return theAbyssPanel;
        }
        return null;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parentPanel = new javax.swing.JPanel();
        teamPage1 = new javax.swing.JPanel();
        profileButton = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        enemiesPane = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        clonePanelContainer = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        modeSpiralAbyss = new javax.swing.JRadioButton();
        hydroRadButton = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        modeEnemyOnly = new javax.swing.JRadioButton();
        swordRadButton = new javax.swing.JRadioButton();
        dendroRadButton = new javax.swing.JRadioButton();
        electroRadButton = new javax.swing.JRadioButton();
        geoRadButton = new javax.swing.JRadioButton();
        physicalRadButton = new javax.swing.JRadioButton();
        cryoRadButton = new javax.swing.JRadioButton();
        anemoRadButton = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        bowRadButton = new javax.swing.JRadioButton();
        polearmRadButton = new javax.swing.JRadioButton();
        catalystRadButton = new javax.swing.JRadioButton();
        claymoreRadButton = new javax.swing.JRadioButton();
        pyroRadButton = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nextLabel = new javax.swing.JLabel();
        nextButton = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        teamPage2 = new javax.swing.JPanel();
        profileButton1 = new javax.swing.JLabel();
        usernameLabel1 = new javax.swing.JLabel();
        emailLabel1 = new javax.swing.JLabel();
        exitButton1 = new javax.swing.JLabel();
        characterPane = new javax.swing.JPanel();
        scrollPane2 = new javax.swing.JScrollPane();
        clonePanelContainer2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        generateLabel = new javax.swing.JLabel();
        generateButton = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        summaryScroll = new javax.swing.JScrollPane();
        summaryPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        teamPage3 = new javax.swing.JPanel();
        profileButton2 = new javax.swing.JLabel();
        usernameLabel2 = new javax.swing.JLabel();
        emailLabel2 = new javax.swing.JLabel();
        exitButton2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        scrollPane3 = new javax.swing.JScrollPane();
        clonePanelContainer3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        modeSpiralAbyss1 = new javax.swing.JRadioButton();
        hydroRadButton1 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        modeEnemyOnly1 = new javax.swing.JRadioButton();
        swordRadButton1 = new javax.swing.JRadioButton();
        dendroRadButton1 = new javax.swing.JRadioButton();
        electroRadButton1 = new javax.swing.JRadioButton();
        geoRadButton1 = new javax.swing.JRadioButton();
        physicalRadButton1 = new javax.swing.JRadioButton();
        cryoRadButton1 = new javax.swing.JRadioButton();
        anemoRadButton1 = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        bowRadButton1 = new javax.swing.JRadioButton();
        polearmRadButton1 = new javax.swing.JRadioButton();
        catalystRadButton1 = new javax.swing.JRadioButton();
        claymoreRadButton1 = new javax.swing.JRadioButton();
        floor9Radio = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        nextLabel1 = new javax.swing.JLabel();
        nextButton1 = new javax.swing.JLabel();
        floor10Radio = new javax.swing.JRadioButton();
        floor11Radio = new javax.swing.JRadioButton();
        floor12Radio = new javax.swing.JRadioButton();
        pyroRadButton5 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Team Guide Page");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1280, 750));
        setResizable(false);
        getContentPane().setLayout(null);

        parentPanel.setLayout(new java.awt.CardLayout());

        teamPage1.setMinimumSize(new java.awt.Dimension(1280, 720));
        teamPage1.setLayout(null);

        profileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/profile1.png"))); // NOI18N
        profileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profileButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profileButtonMouseExited(evt);
            }
        });
        teamPage1.add(profileButton);
        profileButton.setBounds(30, 15, 70, 70);

        usernameLabel.setFont(new java.awt.Font("Sunflower Medium", 0, 30)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(252, 236, 214));
        usernameLabel.setText("Username");
        teamPage1.add(usernameLabel);
        usernameLabel.setBounds(110, 25, 131, 40);

        emailLabel.setFont(new java.awt.Font("Sunflower Medium", 0, 20)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(252, 236, 214));
        emailLabel.setText("Email@gmail.com");
        teamPage1.add(emailLabel);
        emailLabel.setBounds(110, 60, 158, 26);

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit1.png"))); // NOI18N
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
        });
        teamPage1.add(exitButton);
        exitButton.setBounds(1190, 20, 70, 70);

        jLabel2.setFont(new java.awt.Font("HYWenHei-85W", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(241, 167, 84));
        jLabel2.setText("(optional)");
        jLabel2.setToolTipText("");
        teamPage1.add(jLabel2);
        jLabel2.setBounds(990, 240, 90, 30);

        enemiesPane.setLayout(null);

        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        clonePanelContainer.setBackground(new java.awt.Color(255, 204, 204));
        clonePanelContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        clonePanelContainer.setPreferredSize(new java.awt.Dimension(400, 200));
        clonePanelContainer.setLayout(null);
        scrollPane.setViewportView(clonePanelContainer);

        enemiesPane.add(scrollPane);
        scrollPane.setBounds(0, 0, 500, 470);

        teamPage1.add(enemiesPane);
        enemiesPane.setBounds(60, 170, 500, 470);

        jLabel3.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(241, 167, 84));
        jLabel3.setText("Select Enemies");
        jLabel3.setToolTipText("");
        teamPage1.add(jLabel3);
        jLabel3.setBounds(55, 120, 280, 40);

        modeSpiralAbyss.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        modeSpiralAbyss.setForeground(new java.awt.Color(252, 236, 214));
        modeSpiralAbyss.setText("Spiral Abyss");
        modeSpiralAbyss.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modeSpiralAbyss.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        teamPage1.add(modeSpiralAbyss);
        modeSpiralAbyss.setBounds(850, 150, 220, 35);

        hydroRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        hydroRadButton.setForeground(new java.awt.Color(252, 236, 214));
        hydroRadButton.setText("Hydro");
        hydroRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hydroRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        hydroRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hydroRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hydroRadButtonMouseExited(evt);
            }
        });
        hydroRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hydroRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(hydroRadButton);
        hydroRadButton.setBounds(910, 290, 120, 33);

        jLabel4.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(241, 167, 84));
        jLabel4.setText("Modes");
        jLabel4.setToolTipText("");
        teamPage1.add(jLabel4);
        jLabel4.setBounds(611, 92, 280, 40);

        modeEnemyOnly.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        modeEnemyOnly.setForeground(new java.awt.Color(252, 236, 214));
        modeEnemyOnly.setText("Enemies only");
        modeEnemyOnly.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modeEnemyOnly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        teamPage1.add(modeEnemyOnly);
        modeEnemyOnly.setBounds(615, 150, 220, 35);

        swordRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        swordRadButton.setForeground(new java.awt.Color(252, 236, 214));
        swordRadButton.setText("Sword");
        swordRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        swordRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        swordRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                swordRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                swordRadButtonMouseExited(evt);
            }
        });
        swordRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swordRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(swordRadButton);
        swordRadButton.setBounds(620, 480, 120, 33);

        dendroRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        dendroRadButton.setForeground(new java.awt.Color(252, 236, 214));
        dendroRadButton.setText("Dendro");
        dendroRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dendroRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        dendroRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dendroRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dendroRadButtonMouseExited(evt);
            }
        });
        dendroRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dendroRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(dendroRadButton);
        dendroRadButton.setBounds(1060, 290, 120, 33);

        electroRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        electroRadButton.setForeground(new java.awt.Color(252, 236, 214));
        electroRadButton.setText("Electro");
        electroRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        electroRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        electroRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                electroRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                electroRadButtonMouseExited(evt);
            }
        });
        electroRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                electroRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(electroRadButton);
        electroRadButton.setBounds(620, 340, 120, 33);

        geoRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        geoRadButton.setForeground(new java.awt.Color(252, 236, 214));
        geoRadButton.setText("Geo");
        geoRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        geoRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        geoRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                geoRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                geoRadButtonMouseExited(evt);
            }
        });
        geoRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geoRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(geoRadButton);
        geoRadButton.setBounds(780, 340, 120, 33);

        physicalRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        physicalRadButton.setForeground(new java.awt.Color(252, 236, 214));
        physicalRadButton.setText("Physical");
        physicalRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        physicalRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        physicalRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                physicalRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                physicalRadButtonMouseExited(evt);
            }
        });
        physicalRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                physicalRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(physicalRadButton);
        physicalRadButton.setBounds(1060, 340, 140, 33);

        cryoRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        cryoRadButton.setForeground(new java.awt.Color(252, 236, 214));
        cryoRadButton.setText("Cryo");
        cryoRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cryoRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        cryoRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cryoRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cryoRadButtonMouseExited(evt);
            }
        });
        cryoRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cryoRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(cryoRadButton);
        cryoRadButton.setBounds(780, 290, 120, 33);

        anemoRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        anemoRadButton.setForeground(new java.awt.Color(252, 236, 214));
        anemoRadButton.setText("Anemo");
        anemoRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anemoRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        anemoRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                anemoRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anemoRadButtonMouseExited(evt);
            }
        });
        anemoRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anemoRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(anemoRadButton);
        anemoRadButton.setBounds(910, 340, 120, 33);

        jLabel5.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(241, 167, 84));
        jLabel5.setText("Preferred Elements");
        jLabel5.setToolTipText("");
        teamPage1.add(jLabel5);
        jLabel5.setBounds(620, 230, 380, 40);

        bowRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        bowRadButton.setForeground(new java.awt.Color(252, 236, 214));
        bowRadButton.setText("Bow");
        bowRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bowRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        bowRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bowRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bowRadButtonMouseExited(evt);
            }
        });
        bowRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bowRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(bowRadButton);
        bowRadButton.setBounds(620, 530, 120, 33);

        polearmRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        polearmRadButton.setForeground(new java.awt.Color(252, 236, 214));
        polearmRadButton.setText("Polearm");
        polearmRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        polearmRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        polearmRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                polearmRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                polearmRadButtonMouseExited(evt);
            }
        });
        polearmRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polearmRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(polearmRadButton);
        polearmRadButton.setBounds(780, 480, 140, 33);

        catalystRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        catalystRadButton.setForeground(new java.awt.Color(252, 236, 214));
        catalystRadButton.setText("Catalyst");
        catalystRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        catalystRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        catalystRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                catalystRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                catalystRadButtonMouseExited(evt);
            }
        });
        catalystRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catalystRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(catalystRadButton);
        catalystRadButton.setBounds(780, 530, 130, 33);

        claymoreRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        claymoreRadButton.setForeground(new java.awt.Color(252, 236, 214));
        claymoreRadButton.setText("Claymore");
        claymoreRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        claymoreRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        claymoreRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                claymoreRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                claymoreRadButtonMouseExited(evt);
            }
        });
        claymoreRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claymoreRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(claymoreRadButton);
        claymoreRadButton.setBounds(960, 480, 150, 33);

        pyroRadButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        pyroRadButton.setForeground(new java.awt.Color(252, 236, 214));
        pyroRadButton.setText("Pyro");
        pyroRadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pyroRadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        pyroRadButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pyroRadButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pyroRadButtonMouseExited(evt);
            }
        });
        pyroRadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pyroRadButtonActionPerformed(evt);
            }
        });
        teamPage1.add(pyroRadButton);
        pyroRadButton.setBounds(620, 290, 120, 33);

        jLabel6.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(241, 167, 84));
        jLabel6.setText("Preferred Weapon");
        jLabel6.setToolTipText("");
        teamPage1.add(jLabel6);
        jLabel6.setBounds(620, 420, 380, 40);

        jLabel7.setFont(new java.awt.Font("HYWenHei-85W", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(241, 167, 84));
        jLabel7.setText("(optional)");
        jLabel7.setToolTipText("");
        teamPage1.add(jLabel7);
        jLabel7.setBounds(970, 430, 90, 30);

        nextLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        nextLabel.setForeground(new java.awt.Color(255, 255, 255));
        nextLabel.setText("Next");
        nextLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextLabelMouseExited(evt);
            }
        });
        teamPage1.add(nextLabel);
        nextLabel.setBounds(1100, 640, 60, 30);

        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png"))); // NOI18N
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextButtonMouseExited(evt);
            }
        });
        teamPage1.add(nextButton);
        nextButton.setBounds(1000, 630, 247, 46);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_teamguide.png"))); // NOI18N
        teamPage1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 720);

        parentPanel.add(teamPage1, "card4");

        teamPage2.setLayout(null);

        profileButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/profile1.png"))); // NOI18N
        profileButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profileButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profileButton1MouseExited(evt);
            }
        });
        teamPage2.add(profileButton1);
        profileButton1.setBounds(30, 15, 70, 70);

        usernameLabel1.setFont(new java.awt.Font("Sunflower Medium", 0, 30)); // NOI18N
        usernameLabel1.setForeground(new java.awt.Color(252, 236, 214));
        usernameLabel1.setText("Username");
        teamPage2.add(usernameLabel1);
        usernameLabel1.setBounds(110, 25, 131, 40);

        emailLabel1.setFont(new java.awt.Font("Sunflower Medium", 0, 20)); // NOI18N
        emailLabel1.setForeground(new java.awt.Color(252, 236, 214));
        emailLabel1.setText("Email@gmail.com");
        teamPage2.add(emailLabel1);
        emailLabel1.setBounds(110, 60, 158, 26);

        exitButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit1.png"))); // NOI18N
        exitButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton1MouseExited(evt);
            }
        });
        teamPage2.add(exitButton1);
        exitButton1.setBounds(1190, 20, 70, 70);

        characterPane.setLayout(null);

        scrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        clonePanelContainer2.setBackground(new java.awt.Color(255, 204, 204));
        clonePanelContainer2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        clonePanelContainer2.setPreferredSize(new java.awt.Dimension(400, 200));
        clonePanelContainer2.setLayout(null);
        scrollPane2.setViewportView(clonePanelContainer2);

        characterPane.add(scrollPane2);
        scrollPane2.setBounds(0, 0, 430, 510);

        teamPage2.add(characterPane);
        characterPane.setBounds(60, 170, 430, 510);

        jLabel9.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(241, 167, 84));
        jLabel9.setText("Banned Characters");
        jLabel9.setToolTipText("");
        teamPage2.add(jLabel9);
        jLabel9.setBounds(55, 120, 380, 40);

        generateLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        generateLabel.setForeground(new java.awt.Color(255, 255, 255));
        generateLabel.setText("Generate!");
        generateLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generateLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                generateLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                generateLabelMouseExited(evt);
            }
        });
        teamPage2.add(generateLabel);
        generateLabel.setBounds(1070, 640, 130, 30);

        generateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png"))); // NOI18N
        generateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generateButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                generateButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                generateButtonMouseExited(evt);
            }
        });
        teamPage2.add(generateButton);
        generateButton.setBounds(1000, 630, 247, 46);

        jLabel13.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(174, 116, 49));
        jLabel13.setText("Summary");
        jLabel13.setToolTipText("");
        teamPage2.add(jLabel13);
        jLabel13.setBounds(630, 160, 280, 40);

        summaryScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        summaryPanel.setBackground(new java.awt.Color(204, 255, 204));
        summaryScroll.setViewportView(summaryPanel);

        teamPage2.add(summaryScroll);
        summaryScroll.setBounds(630, 220, 580, 350);

        jLabel8.setFont(new java.awt.Font("HYWenHei-85W", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(241, 167, 84));
        jLabel8.setText("(optional)");
        jLabel8.setToolTipText("");
        teamPage2.add(jLabel8);
        jLabel8.setBounds(410, 130, 90, 30);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle.png"))); // NOI18N
        teamPage2.add(jLabel12);
        jLabel12.setBounds(600, 130, 650, 470);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_teamguide.png"))); // NOI18N
        teamPage2.add(jLabel14);
        jLabel14.setBounds(0, 0, 1280, 720);

        parentPanel.add(teamPage2, "card4");

        teamPage3.setMinimumSize(new java.awt.Dimension(1280, 720));
        teamPage3.setLayout(null);

        profileButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/profile1.png"))); // NOI18N
        profileButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profileButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profileButton2MouseExited(evt);
            }
        });
        teamPage3.add(profileButton2);
        profileButton2.setBounds(30, 15, 70, 70);

        usernameLabel2.setFont(new java.awt.Font("Sunflower Medium", 0, 30)); // NOI18N
        usernameLabel2.setForeground(new java.awt.Color(252, 236, 214));
        usernameLabel2.setText("Username");
        teamPage3.add(usernameLabel2);
        usernameLabel2.setBounds(110, 25, 131, 40);

        emailLabel2.setFont(new java.awt.Font("Sunflower Medium", 0, 20)); // NOI18N
        emailLabel2.setForeground(new java.awt.Color(252, 236, 214));
        emailLabel2.setText("Email@gmail.com");
        teamPage3.add(emailLabel2);
        emailLabel2.setBounds(110, 60, 158, 26);

        exitButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit1.png"))); // NOI18N
        exitButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton2MouseExited(evt);
            }
        });
        teamPage3.add(exitButton2);
        exitButton2.setBounds(1190, 20, 70, 70);

        jLabel10.setFont(new java.awt.Font("HYWenHei-85W", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(241, 167, 84));
        jLabel10.setText("(optional)");
        jLabel10.setToolTipText("");
        teamPage3.add(jLabel10);
        jLabel10.setBounds(990, 240, 90, 30);

        scrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        clonePanelContainer3.setBackground(new java.awt.Color(255, 204, 204));
        clonePanelContainer3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        clonePanelContainer3.setPreferredSize(new java.awt.Dimension(400, 200));
        clonePanelContainer3.setLayout(null);
        scrollPane3.setViewportView(clonePanelContainer3);

        teamPage3.add(scrollPane3);
        scrollPane3.setBounds(60, 310, 500, 330);

        jLabel11.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(241, 167, 84));
        jLabel11.setText("Floor");
        jLabel11.setToolTipText("");
        teamPage3.add(jLabel11);
        jLabel11.setBounds(55, 120, 120, 40);

        modeSpiralAbyss1.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        modeSpiralAbyss1.setForeground(new java.awt.Color(252, 236, 214));
        modeSpiralAbyss1.setText("Spiral Abyss");
        modeSpiralAbyss1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modeSpiralAbyss1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        teamPage3.add(modeSpiralAbyss1);
        modeSpiralAbyss1.setBounds(850, 150, 220, 35);

        hydroRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        hydroRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        hydroRadButton1.setText("Hydro");
        hydroRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hydroRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        hydroRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hydroRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hydroRadButton1MouseExited(evt);
            }
        });
        hydroRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hydroRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(hydroRadButton1);
        hydroRadButton1.setBounds(910, 290, 120, 33);

        jLabel15.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(241, 167, 84));
        jLabel15.setText("Modes");
        jLabel15.setToolTipText("");
        teamPage3.add(jLabel15);
        jLabel15.setBounds(611, 92, 280, 40);

        modeEnemyOnly1.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        modeEnemyOnly1.setForeground(new java.awt.Color(252, 236, 214));
        modeEnemyOnly1.setText("Enemies only");
        modeEnemyOnly1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modeEnemyOnly1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        teamPage3.add(modeEnemyOnly1);
        modeEnemyOnly1.setBounds(615, 150, 220, 35);

        swordRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        swordRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        swordRadButton1.setText("Sword");
        swordRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        swordRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        swordRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                swordRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                swordRadButton1MouseExited(evt);
            }
        });
        swordRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swordRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(swordRadButton1);
        swordRadButton1.setBounds(620, 480, 120, 33);

        dendroRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        dendroRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        dendroRadButton1.setText("Dendro");
        dendroRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dendroRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        dendroRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dendroRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dendroRadButton1MouseExited(evt);
            }
        });
        dendroRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dendroRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(dendroRadButton1);
        dendroRadButton1.setBounds(1060, 290, 120, 33);

        electroRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        electroRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        electroRadButton1.setText("Electro");
        electroRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        electroRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        electroRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                electroRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                electroRadButton1MouseExited(evt);
            }
        });
        electroRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                electroRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(electroRadButton1);
        electroRadButton1.setBounds(620, 340, 120, 33);

        geoRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        geoRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        geoRadButton1.setText("Geo");
        geoRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        geoRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        geoRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                geoRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                geoRadButton1MouseExited(evt);
            }
        });
        geoRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geoRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(geoRadButton1);
        geoRadButton1.setBounds(780, 340, 120, 33);

        physicalRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        physicalRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        physicalRadButton1.setText("Physical");
        physicalRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        physicalRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        physicalRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                physicalRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                physicalRadButton1MouseExited(evt);
            }
        });
        physicalRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                physicalRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(physicalRadButton1);
        physicalRadButton1.setBounds(1060, 340, 140, 33);

        cryoRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        cryoRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        cryoRadButton1.setText("Cryo");
        cryoRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cryoRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        cryoRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cryoRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cryoRadButton1MouseExited(evt);
            }
        });
        cryoRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cryoRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(cryoRadButton1);
        cryoRadButton1.setBounds(780, 290, 120, 33);

        anemoRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        anemoRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        anemoRadButton1.setText("Anemo");
        anemoRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anemoRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        anemoRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                anemoRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                anemoRadButton1MouseExited(evt);
            }
        });
        anemoRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anemoRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(anemoRadButton1);
        anemoRadButton1.setBounds(910, 340, 120, 33);

        jLabel16.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(241, 167, 84));
        jLabel16.setText("Preferred Elements");
        jLabel16.setToolTipText("");
        teamPage3.add(jLabel16);
        jLabel16.setBounds(620, 230, 380, 40);

        bowRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        bowRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        bowRadButton1.setText("Bow");
        bowRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bowRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        bowRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bowRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bowRadButton1MouseExited(evt);
            }
        });
        bowRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bowRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(bowRadButton1);
        bowRadButton1.setBounds(620, 530, 120, 33);

        polearmRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        polearmRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        polearmRadButton1.setText("Polearm");
        polearmRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        polearmRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        polearmRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                polearmRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                polearmRadButton1MouseExited(evt);
            }
        });
        polearmRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polearmRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(polearmRadButton1);
        polearmRadButton1.setBounds(780, 480, 140, 33);

        catalystRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        catalystRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        catalystRadButton1.setText("Catalyst");
        catalystRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        catalystRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        catalystRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                catalystRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                catalystRadButton1MouseExited(evt);
            }
        });
        catalystRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catalystRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(catalystRadButton1);
        catalystRadButton1.setBounds(780, 530, 130, 33);

        claymoreRadButton1.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        claymoreRadButton1.setForeground(new java.awt.Color(252, 236, 214));
        claymoreRadButton1.setText("Claymore");
        claymoreRadButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        claymoreRadButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        claymoreRadButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                claymoreRadButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                claymoreRadButton1MouseExited(evt);
            }
        });
        claymoreRadButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claymoreRadButton1ActionPerformed(evt);
            }
        });
        teamPage3.add(claymoreRadButton1);
        claymoreRadButton1.setBounds(960, 480, 150, 33);

        floor9Radio.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        floor9Radio.setForeground(new java.awt.Color(252, 236, 214));
        floor9Radio.setText("Pyro");
        floor9Radio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        floor9Radio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        floor9Radio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                floor9RadioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                floor9RadioMouseExited(evt);
            }
        });
        floor9Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floor9RadioActionPerformed(evt);
            }
        });
        teamPage3.add(floor9Radio);
        floor9Radio.setBounds(60, 170, 120, 33);

        jLabel17.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(241, 167, 84));
        jLabel17.setText("Preferred Weapon");
        jLabel17.setToolTipText("");
        teamPage3.add(jLabel17);
        jLabel17.setBounds(620, 420, 380, 40);

        jLabel18.setFont(new java.awt.Font("HYWenHei-85W", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(241, 167, 84));
        jLabel18.setText("(optional)");
        jLabel18.setToolTipText("");
        teamPage3.add(jLabel18);
        jLabel18.setBounds(970, 430, 90, 30);

        nextLabel1.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        nextLabel1.setForeground(new java.awt.Color(255, 255, 255));
        nextLabel1.setText("Next");
        nextLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextLabel1MouseExited(evt);
            }
        });
        teamPage3.add(nextLabel1);
        nextLabel1.setBounds(1100, 640, 60, 30);

        nextButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png"))); // NOI18N
        nextButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextButton1MouseExited(evt);
            }
        });
        teamPage3.add(nextButton1);
        nextButton1.setBounds(1000, 630, 247, 46);

        floor10Radio.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        floor10Radio.setForeground(new java.awt.Color(252, 236, 214));
        floor10Radio.setText("Pyro");
        floor10Radio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        floor10Radio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        floor10Radio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                floor10RadioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                floor10RadioMouseExited(evt);
            }
        });
        floor10Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floor10RadioActionPerformed(evt);
            }
        });
        teamPage3.add(floor10Radio);
        floor10Radio.setBounds(160, 170, 120, 33);

        floor11Radio.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        floor11Radio.setForeground(new java.awt.Color(252, 236, 214));
        floor11Radio.setText("Pyro");
        floor11Radio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        floor11Radio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        floor11Radio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                floor11RadioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                floor11RadioMouseExited(evt);
            }
        });
        floor11Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floor11RadioActionPerformed(evt);
            }
        });
        teamPage3.add(floor11Radio);
        floor11Radio.setBounds(260, 170, 120, 33);

        floor12Radio.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        floor12Radio.setForeground(new java.awt.Color(252, 236, 214));
        floor12Radio.setText("Pyro");
        floor12Radio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        floor12Radio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        floor12Radio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                floor12RadioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                floor12RadioMouseExited(evt);
            }
        });
        floor12Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floor12RadioActionPerformed(evt);
            }
        });
        teamPage3.add(floor12Radio);
        floor12Radio.setBounds(360, 170, 120, 33);

        pyroRadButton5.setFont(new java.awt.Font("HYWenHei-85W", 0, 22)); // NOI18N
        pyroRadButton5.setForeground(new java.awt.Color(252, 236, 214));
        pyroRadButton5.setText("Pyro");
        pyroRadButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pyroRadButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio1.png"))); // NOI18N
        pyroRadButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pyroRadButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pyroRadButton5MouseExited(evt);
            }
        });
        pyroRadButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pyroRadButton5ActionPerformed(evt);
            }
        });
        teamPage3.add(pyroRadButton5);
        pyroRadButton5.setBounds(620, 290, 120, 33);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_teamguide.png"))); // NOI18N
        teamPage3.add(jLabel19);
        jLabel19.setBounds(0, 0, 1280, 720);

        parentPanel.add(teamPage3, "card4");

        getContentPane().add(parentPanel);
        parentPanel.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_profileButtonMouseClicked

    private void profileButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButtonMouseEntered
        //        profileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/profile2.png")));
        //        profileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_profileButtonMouseEntered

    private void profileButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButtonMouseExited
        //        profileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/profile1.png")));
        //        profileButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_profileButtonMouseExited

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to go back? Your changes won't be saved", "SELECT", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            setVisible(false);
            dispose();
            new Home(userId).setVisible(true);
        }
    }//GEN-LAST:event_exitButtonMouseClicked

    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit2.png")));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_exitButtonMouseEntered

    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit1.png")));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_exitButtonMouseExited

    private void nextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseClicked
        teamPage2();
    }//GEN-LAST:event_nextButtonMouseClicked

    private void nextButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseEntered
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_nextButtonMouseEntered

    private void nextButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseExited
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        nextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_nextButtonMouseExited

    private void nextLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabelMouseClicked
        teamPage2();
    }//GEN-LAST:event_nextLabelMouseClicked

    private void nextLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabelMouseEntered
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_nextLabelMouseEntered

    private void nextLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabelMouseExited
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        nextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_nextLabelMouseExited

    
    private void drawMouseEnteredRadioButton(String type, String component, JRadioButton radio){
        component = component.substring(0,1).toUpperCase() + component.substring(1);
        if(type.equals("elements")){
            if(elements.contains(component)){
                radio.setIcon(new ImageIcon("src/App/image/radio4.png"));
            }
            else{
                radio.setIcon(new ImageIcon("src/App/image/radio3.png"));
            }
        }
        else if(type.equals("weapons")){
            if(weapons.contains(component)){
                radio.setIcon(new ImageIcon("src/App/image/radio4.png"));
            }
            else{
                radio.setIcon(new ImageIcon("src/App/image/radio3.png"));
            }
        }
    }
    
    private void drawMouseExitedRadioButton(String type, String component, JRadioButton radio){
        component = component.substring(0,1).toUpperCase() + component.substring(1);
        if(type.equals("elements")){
            if(elements.contains(component)){
                radio.setIcon(new ImageIcon("src/App/image/radio2.png"));
            }
            else{
                radio.setIcon(new ImageIcon("src/App/image/radio1.png"));
            }
        }
        else if(type.equals("weapons")){
            if(weapons.contains(component)){
                radio.setIcon(new ImageIcon("src/App/image/radio2.png"));
            }
            else{
                radio.setIcon(new ImageIcon("src/App/image/radio1.png"));
            }
        }
            
    }
    
    private void radioButtonActionPerformed(String type, String component, JRadioButton radio){
        component = component.substring(0,1).toUpperCase() + component.substring(1);
        if(type.equals("elements")){
            if(!(elements.contains(component))){
                radio.setIcon(new ImageIcon("src/App/image/radio2.png"));
                elements.add(component);
            }
            else{
                radio.setIcon(new ImageIcon("src/App/image/radio1.png"));
                elements.remove(component);
            }  
        }
        else if(type.equals("weapons")){
            if(!(weapons.contains(component))){
                radio.setIcon(new ImageIcon("src/App/image/radio2.png"));
                weapons.add(component);
            }
            else{
                radio.setIcon(new ImageIcon("src/App/image/radio1.png"));
                weapons.remove(component);
            }
        }
        
    }
    
    
    private void pyroRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pyroRadButtonMouseEntered
        drawMouseEnteredRadioButton("elements", "pyro", pyroRadButton);
    }//GEN-LAST:event_pyroRadButtonMouseEntered

    private void pyroRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pyroRadButtonMouseExited
        drawMouseExitedRadioButton("elements", "pyro", pyroRadButton);
    }//GEN-LAST:event_pyroRadButtonMouseExited

    private void pyroRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pyroRadButtonActionPerformed
        radioButtonActionPerformed("elements", "pyro", pyroRadButton);
    }//GEN-LAST:event_pyroRadButtonActionPerformed

    private void cryoRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cryoRadButtonActionPerformed
        radioButtonActionPerformed("elements", "cryo", cryoRadButton);
    }//GEN-LAST:event_cryoRadButtonActionPerformed

    private void cryoRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cryoRadButtonMouseEntered
        drawMouseEnteredRadioButton("elements", "cryo", cryoRadButton);
    }//GEN-LAST:event_cryoRadButtonMouseEntered

    private void cryoRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cryoRadButtonMouseExited
        drawMouseExitedRadioButton("elements", "cryo", cryoRadButton);
    }//GEN-LAST:event_cryoRadButtonMouseExited

    private void hydroRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hydroRadButtonActionPerformed
        radioButtonActionPerformed("elements", "hydro", hydroRadButton);
    }//GEN-LAST:event_hydroRadButtonActionPerformed

    private void hydroRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hydroRadButtonMouseEntered
        drawMouseEnteredRadioButton("elements", "hydro", hydroRadButton);
    }//GEN-LAST:event_hydroRadButtonMouseEntered

    private void hydroRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hydroRadButtonMouseExited
        drawMouseExitedRadioButton("elements", "hydro", hydroRadButton);
    }//GEN-LAST:event_hydroRadButtonMouseExited

    private void dendroRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dendroRadButtonActionPerformed
       radioButtonActionPerformed("elements", "dendro", dendroRadButton);
    }//GEN-LAST:event_dendroRadButtonActionPerformed

    private void dendroRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dendroRadButtonMouseEntered
        drawMouseEnteredRadioButton("elements", "dendro", dendroRadButton);
    }//GEN-LAST:event_dendroRadButtonMouseEntered

    private void dendroRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dendroRadButtonMouseExited
        drawMouseExitedRadioButton("elements", "dendro", dendroRadButton);
    }//GEN-LAST:event_dendroRadButtonMouseExited

    private void electroRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_electroRadButtonActionPerformed
        radioButtonActionPerformed("elements", "electro", electroRadButton);
    }//GEN-LAST:event_electroRadButtonActionPerformed

    private void electroRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_electroRadButtonMouseEntered
        drawMouseEnteredRadioButton("elements", "electro", electroRadButton);
    }//GEN-LAST:event_electroRadButtonMouseEntered

    private void electroRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_electroRadButtonMouseExited
        drawMouseExitedRadioButton("elements", "electro", electroRadButton);
    }//GEN-LAST:event_electroRadButtonMouseExited

    private void geoRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geoRadButtonActionPerformed
        radioButtonActionPerformed("elements", "geo", geoRadButton);
    }//GEN-LAST:event_geoRadButtonActionPerformed

    private void geoRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geoRadButtonMouseEntered
        drawMouseEnteredRadioButton("elements", "geo", geoRadButton);
    }//GEN-LAST:event_geoRadButtonMouseEntered

    private void geoRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geoRadButtonMouseExited
        drawMouseExitedRadioButton("elements", "geo", geoRadButton);
    }//GEN-LAST:event_geoRadButtonMouseExited

    private void anemoRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anemoRadButtonActionPerformed
        radioButtonActionPerformed("elements", "anemo", anemoRadButton);
    }//GEN-LAST:event_anemoRadButtonActionPerformed

    private void anemoRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anemoRadButtonMouseEntered
        drawMouseEnteredRadioButton("elements", "anemo", anemoRadButton);
    }//GEN-LAST:event_anemoRadButtonMouseEntered

    private void anemoRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anemoRadButtonMouseExited
        drawMouseExitedRadioButton("elements", "anemo", anemoRadButton);
    }//GEN-LAST:event_anemoRadButtonMouseExited

    private void physicalRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_physicalRadButtonActionPerformed
        radioButtonActionPerformed("elements", "physical", physicalRadButton);
    }//GEN-LAST:event_physicalRadButtonActionPerformed

    private void physicalRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_physicalRadButtonMouseEntered
        drawMouseEnteredRadioButton("elements", "physical", physicalRadButton);
    }//GEN-LAST:event_physicalRadButtonMouseEntered

    private void physicalRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_physicalRadButtonMouseExited
        drawMouseExitedRadioButton("elements", "physical", physicalRadButton);
    }//GEN-LAST:event_physicalRadButtonMouseExited

    private void swordRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swordRadButtonActionPerformed
        radioButtonActionPerformed("weapons", "sword", swordRadButton);
    }//GEN-LAST:event_swordRadButtonActionPerformed

    private void swordRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swordRadButtonMouseEntered
        drawMouseEnteredRadioButton("weapons", "sword", swordRadButton);
    }//GEN-LAST:event_swordRadButtonMouseEntered

    private void swordRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swordRadButtonMouseExited
        drawMouseExitedRadioButton("weapons", "sword", swordRadButton);
    }//GEN-LAST:event_swordRadButtonMouseExited

    private void polearmRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polearmRadButtonActionPerformed
        radioButtonActionPerformed("weapons", "polearm", polearmRadButton);
    }//GEN-LAST:event_polearmRadButtonActionPerformed

    private void polearmRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_polearmRadButtonMouseEntered
        drawMouseEnteredRadioButton("weapons", "polearm", polearmRadButton);
    }//GEN-LAST:event_polearmRadButtonMouseEntered

    private void polearmRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_polearmRadButtonMouseExited
        drawMouseExitedRadioButton("weapons", "polearm", polearmRadButton);
    }//GEN-LAST:event_polearmRadButtonMouseExited

    private void claymoreRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claymoreRadButtonActionPerformed
        radioButtonActionPerformed("weapons", "claymore", claymoreRadButton);
    }//GEN-LAST:event_claymoreRadButtonActionPerformed

    private void claymoreRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_claymoreRadButtonMouseEntered
       drawMouseEnteredRadioButton("weapons", "claymore", claymoreRadButton);
    }//GEN-LAST:event_claymoreRadButtonMouseEntered

    private void claymoreRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_claymoreRadButtonMouseExited
        drawMouseExitedRadioButton("weapons", "claymore", claymoreRadButton);
    }//GEN-LAST:event_claymoreRadButtonMouseExited

    private void bowRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bowRadButtonActionPerformed
        radioButtonActionPerformed("weapons", "bow", bowRadButton);
    }//GEN-LAST:event_bowRadButtonActionPerformed

    private void bowRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bowRadButtonMouseEntered
        drawMouseEnteredRadioButton("weapons", "bow", bowRadButton);
    }//GEN-LAST:event_bowRadButtonMouseEntered

    private void bowRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bowRadButtonMouseExited
        drawMouseExitedRadioButton("weapons", "bow", bowRadButton);
    }//GEN-LAST:event_bowRadButtonMouseExited

    private void catalystRadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catalystRadButtonActionPerformed
        radioButtonActionPerformed("weapons", "catalyst", catalystRadButton);
    }//GEN-LAST:event_catalystRadButtonActionPerformed

    private void catalystRadButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catalystRadButtonMouseEntered
        drawMouseEnteredRadioButton("weapons", "catalyst", catalystRadButton);
    }//GEN-LAST:event_catalystRadButtonMouseEntered

    private void catalystRadButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catalystRadButtonMouseExited
        drawMouseExitedRadioButton("weapons", "catalyst", catalystRadButton);
    }//GEN-LAST:event_catalystRadButtonMouseExited

    
    
    
    
    
    private ArrayList<CharacterPanel> characterBannedList = new ArrayList<>();
    private Set<String> selectedPanels;
    private App.WrappedLabel bannedNameLabel;
    private JLabel bannedCharactersLabel;
    
    private void teamPage2(){
        parentPanel.remove(teamPage1);
        parentPanel.add(teamPage2);
        parentPanel.revalidate();
        parentPanel.repaint();
        
        usernameLabel1.setText(username);
        emailLabel1.setText(email);
        
        selectedPanels = new LinkedHashSet<>();
        
        characterPane.setOpaque(false);
        characterPane.setBorder(null);
        
        scrollPane2.setOpaque(false);
        scrollPane2.getViewport().setOpaque(false);
        scrollPane2.setViewportBorder(null);
        scrollPane2.setBorder(null);
        scrollPane2.getVerticalScrollBar().setUnitIncrement(20);
        
        clonePanelContainer2 = new JPanel(); // The initial panel inside scroll pane
        clonePanelContainer2.setLayout(null); // Use absolute layout
        clonePanelContainer2.setPreferredSize(new Dimension(400, 200)); // Set initial size
        clonePanelContainer2.setOpaque(false);
        clonePanelContainer2.setBackground(new Color(0,0,0,0));
        clonePanelContainer2.setBorder(null);
        scrollPane2.setViewportView(clonePanelContainer2); // Set this panel as viewport's view
        
        App.ImageLoader loader2 = new App.ImageLoader();
        loader2.emptyFileName();
        ArrayList<BufferedImage> imageList = loader2.loadImagesFromFolder("src/App/image/CharacterCard/Small");
        ArrayList<String> nameList = loader2.returnFileNames();
        
        int row=0, column=0;
        for(int i=0; i<imageList.size();i++){
            BufferedImage image = imageList.get(i);
            String charName = nameList.get(i);
            
            int panelWidth = 120;
            int panelHeight = 120;
            
            CharacterPanel clonedPanel = new CharacterPanel(charName);
            clonedPanel.settingPanel(image, charName, panelWidth, panelHeight,12,false);
            clonedPanel.settingMouse(image);
            

            // Calculate the row and column indices
            row = i / 3;
            column = i % 3;

            // Calculate the x and y positions based on row and column indices
            int x = 10 + column * (panelWidth + 15);
            int y = 10 + row * (panelHeight + 30);

            // Set the bounds for the cloned panel with your custom size
            clonedPanel.setBounds(x, y, panelWidth, panelHeight);
            
            clonedPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handlePanelClick(clonedPanel.getName());
                }
            });
            
            // Add the cloned panel to the initial panel
            clonePanelContainer2.add(clonedPanel);
            // Adjust preferred size of initial panel to include new panel
            Dimension newSize = new Dimension(clonePanelContainer2.getWidth(), y + panelHeight + 10); // Adjusted size
            clonePanelContainer2.setPreferredSize(newSize);
            // Ensure the scroll pane updates its viewport
            scrollPane2.revalidate();
            scrollPane2.repaint();
            // Scroll to show the new panel
            scrollPane2.getVerticalScrollBar().setValue(0);
            
            characterBannedList.add(clonedPanel);
        }
        
        
        summaryScroll.setOpaque(false);
        summaryScroll.getViewport().setOpaque(false);
        summaryScroll.setViewportBorder(null);
        summaryScroll.setBorder(null);
        summaryScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        summaryPanel = new JPanel(); // The initial panel inside scroll pane
        summaryPanel.setLayout(null); // Use absolute layout
        summaryPanel.setPreferredSize(new Dimension(400, 200)); // Set initial size
        summaryPanel.setOpaque(false);
        summaryPanel.setBackground(new Color(0,0,0,0));
        summaryPanel.setBorder(null);
        summaryScroll.setViewportView(summaryPanel); // Set this panel as viewport's view
        
        JLabel modeLabel = new JLabel();
        modeLabel.setText("Mode: " + "Enemies Only");
        modeLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 24));
        modeLabel.setForeground(new Color(67,67,71));
        modeLabel.setBounds(0,0, modeLabel.getPreferredSize().width+20, modeLabel.getPreferredSize().height);
        summaryPanel.add(modeLabel);
        summaryPanel.setComponentZOrder(modeLabel, 0);
        
        JLabel enemySummaryLabel = new JLabel();
        enemySummaryLabel.setText("Enemies:");
        enemySummaryLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 24));
        enemySummaryLabel.setForeground(new Color(67,67,71));
        enemySummaryLabel.setBounds(0,modeLabel.getPreferredSize().height+modeLabel.getY()+20, enemySummaryLabel.getPreferredSize().width+20, enemySummaryLabel.getPreferredSize().height);
        summaryPanel.add(enemySummaryLabel);
        summaryPanel.setComponentZOrder(enemySummaryLabel, 0);
        
        App.WrappedLabel enemiesLabel = new App.WrappedLabel(550, new Color(0,0,0,0), new Insets(2,2,2,2));
        enemiesLabel.setOpaque(false);
        enemiesLabel.setText(getEnemyName());
        enemiesLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 18));
        enemiesLabel.setForeground(new Color(113,113,113));
        enemiesLabel.setBounds(0,enemySummaryLabel.getPreferredSize().height+enemySummaryLabel.getY()+5, enemiesLabel.getPreferredSize().width+20, enemiesLabel.getPreferredSize().height);
        summaryPanel.add(enemiesLabel);
        summaryPanel.setComponentZOrder(enemiesLabel, 0);
        
        JLabel preferredElementLabel = new JLabel();
        preferredElementLabel.setText("Preferred Elements:");
        preferredElementLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 24));
        preferredElementLabel.setForeground(new Color(67,67,71));
        preferredElementLabel.setBounds(0,enemiesLabel.getPreferredSize().height+enemiesLabel.getY()+20, preferredElementLabel.getPreferredSize().width+20, preferredElementLabel.getPreferredSize().height);
        summaryPanel.add(preferredElementLabel);
        summaryPanel.setComponentZOrder(preferredElementLabel, 0);
        
        App.WrappedLabel elementsLabel = new App.WrappedLabel(550, new Color(0,0,0,0), new Insets(2,2,2,2));
        elementsLabel.setOpaque(false);
        elementsLabel.setText(getElements());
        elementsLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 18));
        elementsLabel.setForeground(new Color(113,113,113));
        elementsLabel.setBounds(0,preferredElementLabel.getPreferredSize().height+preferredElementLabel.getY()+5, elementsLabel.getPreferredSize().width+20, elementsLabel.getPreferredSize().height);
        summaryPanel.add(elementsLabel);
        summaryPanel.setComponentZOrder(elementsLabel, 0);
        
        JLabel preferredWeaponLabel = new JLabel();
        preferredWeaponLabel.setText("Preferred Weapons:");
        preferredWeaponLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 24));
        preferredWeaponLabel.setForeground(new Color(67,67,71));
        preferredWeaponLabel.setBounds(0,elementsLabel.getPreferredSize().height+elementsLabel.getY()+20, preferredWeaponLabel.getPreferredSize().width+20, preferredWeaponLabel.getPreferredSize().height);
        summaryPanel.add(preferredWeaponLabel);
        summaryPanel.setComponentZOrder(preferredWeaponLabel, 0);
        
        App.WrappedLabel weaponsLabel = new App.WrappedLabel(550, new Color(0,0,0,0), new Insets(2,2,2,2));
        weaponsLabel.setOpaque(false);
        weaponsLabel.setText(getWeapons());
        weaponsLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 18));
        weaponsLabel.setForeground(new Color(113,113,113));
        weaponsLabel.setBounds(0,preferredWeaponLabel.getPreferredSize().height+preferredWeaponLabel.getY()+5, weaponsLabel.getPreferredSize().width+20, weaponsLabel.getPreferredSize().height);
        summaryPanel.add(weaponsLabel);
        summaryPanel.setComponentZOrder(weaponsLabel, 0);
        
        bannedCharactersLabel = new JLabel();
        bannedCharactersLabel.setText("Banned Characters:");
        bannedCharactersLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 24));
        bannedCharactersLabel.setForeground(new Color(67,67,71));
        bannedCharactersLabel.setBounds(0,weaponsLabel.getPreferredSize().height+weaponsLabel.getY()+20, bannedCharactersLabel.getPreferredSize().width+20, bannedCharactersLabel.getPreferredSize().height);
        summaryPanel.add(bannedCharactersLabel);
        summaryPanel.setComponentZOrder(bannedCharactersLabel, 0);
        
        bannedNameLabel = new App.WrappedLabel(550, new Color(0,0,0,0), new Insets(2,2,2,2));
        bannedNameLabel.setOpaque(false);
        bannedNameLabel.setText("-");
        bannedNameLabel.setFont(new Font("HYWenHei-85W", Font.PLAIN, 18));
        bannedNameLabel.setForeground(new Color(113,113,113));
        bannedNameLabel.setBounds(0,bannedCharactersLabel.getPreferredSize().height+bannedCharactersLabel.getY()+5, bannedNameLabel.getPreferredSize().width+20, bannedNameLabel.getPreferredSize().height);
        summaryPanel.add(bannedNameLabel);
        summaryPanel.setComponentZOrder(bannedNameLabel, 0);
        
        adjustContainerSizePage2();
        summaryScroll.getVerticalScrollBar().setValue(0);
    }
    
    
    private void handlePanelClick(String panelName) {
        if (selectedPanels.contains(panelName)) {
            selectedPanels.remove(panelName);
        } else {
            selectedPanels.add(panelName);
        }
        updateLabel();
    }
    
    private void updateLabel(){
        String str ="";
        int j =0;
        if(selectedPanels.isEmpty()){
            str = "-";
        }
        for(String s: selectedPanels){
            str = (j==0)? str + s : str + ", " + s;
            j++;
        }
        bannedNameLabel.setText(str);
        bannedNameLabel.setBounds(0,bannedCharactersLabel.getPreferredSize().height+bannedCharactersLabel.getY()+5, bannedNameLabel.getPreferredSize().width+20, bannedNameLabel.getPreferredSize().height);
        bannedNameLabel.repaint();
        bannedNameLabel.revalidate();
        summaryPanel.repaint();
        summaryPanel.revalidate();
        adjustContainerSizePage2();
    }
    
    private String getWeapons(){
        String str="";
        if(weapons.isEmpty()){
            return "-";
        }
        else{
            for(String e: weapons){
                str = (weapons.indexOf(e)==0)? str + e : str + ", " + e;
            }
        }
        return str;
    }
    
    private String getElements(){
        String str="";
        if(elements.isEmpty()){
            return "-";
        }
        else{
            for(String e: elements){
                str = (elements.indexOf(e)==0)? str + e : str + ", " + e;
            }
        }
        return str;
    }
    
    private String getEnemyName(){
        String str="";
        if(enemyPanelList.isEmpty()){
            str = "-";
            return str;
        }
        
        ArrayList<Integer> clickedListIndex = new ArrayList<>();
                
        for(int i=0; i<enemyPanelList.size();i++){
            for(EnemyPanel p: enemyPanelList.get(i)){
                if(p.getClicked()){
                    clickedListIndex.add(i);
                    break;
                }
            }
        }
        
        for(int index: clickedListIndex){
            str = (index==0)? str + enemyPanelList.get(index).get(0).getType() + " (" : str + ", " + enemyPanelList.get(index).get(0).getType() + " (";    
            int j=0;
            for(EnemyPanel p: enemyPanelList.get(index)){
                if(p.getClicked()){
                    str = (j == 0)? str+p.getName() : str + ", " + p.getName();
                    j++;
                }
            }
            str = str + ")";
        }
        return str;
    }
    
    
    

    private void adjustContainerSizePage2() {
        int totalHeight = 0;
        for (Component comp : summaryPanel.getComponents()) {
            totalHeight += comp.getPreferredSize().height + 12; // Adjust the offset as needed
        }
        summaryPanel.setPreferredSize(new Dimension(summaryPanel.getWidth(), totalHeight));
        summaryPanel.revalidate();
        summaryPanel.repaint();
        summaryScroll.revalidate();
        summaryScroll.repaint();
    }
    
    
    
   
    private void profileButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_profileButton1MouseClicked

    private void profileButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_profileButton1MouseEntered

    private void profileButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_profileButton1MouseExited

    private void exitButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton1MouseClicked
        int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to go back? Your changes won't be saved", "SELECT", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            setVisible(false);
            dispose();
            new Home(userId).setVisible(true);
        }
    }//GEN-LAST:event_exitButton1MouseClicked

    private void exitButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton1MouseEntered
        exitButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit2.png")));
        exitButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_exitButton1MouseEntered

    private void exitButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton1MouseExited
        exitButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit1.png")));
        exitButton1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_exitButton1MouseExited

    private void generateLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateLabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_generateLabelMouseClicked

    private void generateLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateLabelMouseEntered
        generateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        generateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generateLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_generateLabelMouseEntered

    private void generateLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateLabelMouseExited
        generateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        generateButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        generateLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_generateLabelMouseExited

    private void generateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_generateButtonMouseClicked

    private void generateButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateButtonMouseEntered
        generateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        generateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generateLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_generateButtonMouseEntered

    private void generateButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateButtonMouseExited
        generateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        generateButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        generateLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_generateButtonMouseExited

    private void profileButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_profileButton2MouseClicked

    private void profileButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButton2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_profileButton2MouseEntered

    private void profileButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButton2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_profileButton2MouseExited

    private void exitButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton2MouseClicked

    private void exitButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton2MouseEntered

    private void exitButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButton2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButton2MouseExited

    private void hydroRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hydroRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_hydroRadButton1MouseEntered

    private void hydroRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hydroRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_hydroRadButton1MouseExited

    private void hydroRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hydroRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hydroRadButton1ActionPerformed

    private void swordRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swordRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_swordRadButton1MouseEntered

    private void swordRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swordRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_swordRadButton1MouseExited

    private void swordRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swordRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_swordRadButton1ActionPerformed

    private void dendroRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dendroRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_dendroRadButton1MouseEntered

    private void dendroRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dendroRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_dendroRadButton1MouseExited

    private void dendroRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dendroRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dendroRadButton1ActionPerformed

    private void electroRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_electroRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_electroRadButton1MouseEntered

    private void electroRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_electroRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_electroRadButton1MouseExited

    private void electroRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_electroRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_electroRadButton1ActionPerformed

    private void geoRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geoRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_geoRadButton1MouseEntered

    private void geoRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_geoRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_geoRadButton1MouseExited

    private void geoRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geoRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_geoRadButton1ActionPerformed

    private void physicalRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_physicalRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_physicalRadButton1MouseEntered

    private void physicalRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_physicalRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_physicalRadButton1MouseExited

    private void physicalRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_physicalRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_physicalRadButton1ActionPerformed

    private void cryoRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cryoRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cryoRadButton1MouseEntered

    private void cryoRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cryoRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cryoRadButton1MouseExited

    private void cryoRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cryoRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cryoRadButton1ActionPerformed

    private void anemoRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anemoRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_anemoRadButton1MouseEntered

    private void anemoRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anemoRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_anemoRadButton1MouseExited

    private void anemoRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anemoRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anemoRadButton1ActionPerformed

    private void bowRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bowRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_bowRadButton1MouseEntered

    private void bowRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bowRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_bowRadButton1MouseExited

    private void bowRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bowRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bowRadButton1ActionPerformed

    private void polearmRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_polearmRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_polearmRadButton1MouseEntered

    private void polearmRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_polearmRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_polearmRadButton1MouseExited

    private void polearmRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polearmRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polearmRadButton1ActionPerformed

    private void catalystRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catalystRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_catalystRadButton1MouseEntered

    private void catalystRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_catalystRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_catalystRadButton1MouseExited

    private void catalystRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catalystRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catalystRadButton1ActionPerformed

    private void claymoreRadButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_claymoreRadButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_claymoreRadButton1MouseEntered

    private void claymoreRadButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_claymoreRadButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_claymoreRadButton1MouseExited

    private void claymoreRadButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claymoreRadButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claymoreRadButton1ActionPerformed

    private void floor9RadioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_floor9RadioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_floor9RadioMouseEntered

    private void floor9RadioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_floor9RadioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_floor9RadioMouseExited

    private void floor9RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floor9RadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_floor9RadioActionPerformed

    private void nextLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_nextLabel1MouseClicked

    private void nextLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nextLabel1MouseEntered

    private void nextLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextLabel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_nextLabel1MouseExited

    private void nextButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_nextButton1MouseClicked

    private void nextButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButton1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nextButton1MouseEntered

    private void nextButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButton1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_nextButton1MouseExited

    private void floor10RadioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_floor10RadioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_floor10RadioMouseEntered

    private void floor10RadioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_floor10RadioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_floor10RadioMouseExited

    private void floor10RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floor10RadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_floor10RadioActionPerformed

    private void floor11RadioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_floor11RadioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_floor11RadioMouseEntered

    private void floor11RadioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_floor11RadioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_floor11RadioMouseExited

    private void floor11RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floor11RadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_floor11RadioActionPerformed

    private void floor12RadioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_floor12RadioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_floor12RadioMouseEntered

    private void floor12RadioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_floor12RadioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_floor12RadioMouseExited

    private void floor12RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floor12RadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_floor12RadioActionPerformed

    private void pyroRadButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pyroRadButton5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pyroRadButton5MouseEntered

    private void pyroRadButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pyroRadButton5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pyroRadButton5MouseExited

    private void pyroRadButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pyroRadButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pyroRadButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TeamGuide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeamGuide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeamGuide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeamGuide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeamGuide().setVisible(true);
//                SwingUtilities.invokeLater(TeamGuide::new);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton anemoRadButton;
    private javax.swing.JRadioButton anemoRadButton1;
    private javax.swing.JRadioButton bowRadButton;
    private javax.swing.JRadioButton bowRadButton1;
    private javax.swing.JRadioButton catalystRadButton;
    private javax.swing.JRadioButton catalystRadButton1;
    private javax.swing.JPanel characterPane;
    private javax.swing.JRadioButton claymoreRadButton;
    private javax.swing.JRadioButton claymoreRadButton1;
    private javax.swing.JPanel clonePanelContainer;
    private javax.swing.JPanel clonePanelContainer2;
    private javax.swing.JPanel clonePanelContainer3;
    private javax.swing.JRadioButton cryoRadButton;
    private javax.swing.JRadioButton cryoRadButton1;
    private javax.swing.JRadioButton dendroRadButton;
    private javax.swing.JRadioButton dendroRadButton1;
    private javax.swing.JRadioButton electroRadButton;
    private javax.swing.JRadioButton electroRadButton1;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JLabel emailLabel2;
    private javax.swing.JPanel enemiesPane;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel exitButton1;
    private javax.swing.JLabel exitButton2;
    private javax.swing.JRadioButton floor10Radio;
    private javax.swing.JRadioButton floor11Radio;
    private javax.swing.JRadioButton floor12Radio;
    private javax.swing.JRadioButton floor9Radio;
    private javax.swing.JLabel generateButton;
    private javax.swing.JLabel generateLabel;
    private javax.swing.JRadioButton geoRadButton;
    private javax.swing.JRadioButton geoRadButton1;
    private javax.swing.JRadioButton hydroRadButton;
    private javax.swing.JRadioButton hydroRadButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton modeEnemyOnly;
    private javax.swing.JRadioButton modeEnemyOnly1;
    private javax.swing.JRadioButton modeSpiralAbyss;
    private javax.swing.JRadioButton modeSpiralAbyss1;
    private javax.swing.JLabel nextButton;
    private javax.swing.JLabel nextButton1;
    private javax.swing.JLabel nextLabel;
    private javax.swing.JLabel nextLabel1;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JRadioButton physicalRadButton;
    private javax.swing.JRadioButton physicalRadButton1;
    private javax.swing.JRadioButton polearmRadButton;
    private javax.swing.JRadioButton polearmRadButton1;
    private javax.swing.JLabel profileButton;
    private javax.swing.JLabel profileButton1;
    private javax.swing.JLabel profileButton2;
    private javax.swing.JRadioButton pyroRadButton;
    private javax.swing.JRadioButton pyroRadButton5;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JPanel summaryPanel;
    private javax.swing.JScrollPane summaryScroll;
    private javax.swing.JRadioButton swordRadButton;
    private javax.swing.JRadioButton swordRadButton1;
    private javax.swing.JPanel teamPage1;
    private javax.swing.JPanel teamPage2;
    private javax.swing.JPanel teamPage3;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel usernameLabel1;
    private javax.swing.JLabel usernameLabel2;
    // End of variables declaration//GEN-END:variables
}
