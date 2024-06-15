/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author asus
 */
public class CharInfoHome extends javax.swing.JFrame {
    private int userId;
    private String username;
    private String email;
    private ImageIcon profileImage;
    private MP3Player bgmPlayer;
    
    //attributes for elements button
    private ArrayList<JLabel> clickedElementCircle = new ArrayList<>();
    private ArrayList<JLabel> clickhoverElementCircle = new ArrayList<>();
    private ArrayList<JLabel> hoverElementCircle = new ArrayList<>();
    private String[] elementsName = {"Pyro", "Hydro", "Dendro", "Electro", "Anemo", "Cryo", "Geo"};
    
    //attributes for weapons button
    private ArrayList<JLabel> clickedWeaponCircle = new ArrayList<>();
    private ArrayList<JLabel> clickhoverWeaponCircle = new ArrayList<>();
    private ArrayList<JLabel> hoverWeaponCircle = new ArrayList<>();
    private String[] weaponsName = {"Sword", "Claymore", "Bow", "Catalyst", "Polearm"};
    
    private ArrayList<GameCharacter> gameChars = new ArrayList<>();
    private HashMap<GameCharacter, ImageIcon> allCharacters = new LinkedHashMap<>();
    private ArrayList<CharacterArchivePanel> charPanels = new ArrayList<>();
    
    /**
     * Creates new form CharInfoHome
     */
    public CharInfoHome() {
        initComponents();
        this.userId = 1;
        this.username = "ellis";
        this.email = "ellis@mail.com";
        setLocationRelativeTo(null);
        myinit();
    }
    
    public CharInfoHome(int userId, String username, String email, ImageIcon profileImage, MP3Player bgmPlayer, boolean type) {
        initComponents();
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.bgmPlayer = bgmPlayer;
        if(!type){
            this.bgmPlayer.play();
        }
        
        this.profileImage = profileImage;
        setLocationRelativeTo(null);
        myinit();
    }
    
    
    
    private void myinit(){
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/App/image/mouse.png").getImage(), new Point(0,0),"custom cursor"));
        
        //username and email label
        usernameLabel.setText(username);
        emailLabel.setText(email);
        usernameLabel.setBounds(110, 25, usernameLabel.getPreferredSize().width+10, usernameLabel.getPreferredSize().height);
        emailLabel.setBounds(110, 60, emailLabel.getPreferredSize().width+10, emailLabel.getPreferredSize().height);
        getContentPane().setComponentZOrder(usernameLabel, 0);
        getContentPane().setComponentZOrder(emailLabel, 0);
        
        //set up profile image
        profileButton.setIcon(profileImage);
        
        
        //set up hover and click for the elements button
        Collections.addAll(clickedElementCircle, clickedElementCircle1, clickedElementCircle2, clickedElementCircle3, clickedElementCircle4, clickedElementCircle5, clickedElementCircle6, clickedElementCircle7);
        Collections.addAll(clickhoverElementCircle, clickhoverElementCircle1, clickhoverElementCircle2, clickhoverElementCircle3, clickhoverElementCircle4, clickhoverElementCircle5, clickhoverElementCircle6, clickhoverElementCircle7);
        Collections.addAll(hoverElementCircle, hoverElementCircle1, hoverElementCircle2, hoverElementCircle3, hoverElementCircle4, hoverElementCircle5, hoverElementCircle6, hoverElementCircle7);
        
        int x = 870;
        for(int i=0; i<elementsName.length; i++){
            App.LabelHover lab = new App.LabelHover(false, clickedElementCircle.get(i), clickhoverElementCircle.get(i), hoverElementCircle.get(i), elementsName[i]);
            lab.setLabelIcon("src/App/image/Element/Small/" + elementsName[i] +".png");
            getContentPane().add(lab);
            lab.setBounds(x, 315, 34, 35);
            
            lab.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(lab.isClicked()){
                        elementFilter.add(lab.getName());
                        handleClick();
                    }
                    else{
                        elementFilter.remove(lab.getName());
                        handleReleaseClick();
                    }
                }
            });
            
            getContentPane().add(clickedElementCircle.get(i));
            clickedElementCircle.get(i).setBounds(x-2, 315, 36, 36);
            getContentPane().add(clickhoverElementCircle.get(i));
            clickhoverElementCircle.get(i).setBounds(x-2, 315, 36, 36);
            getContentPane().add(hoverElementCircle.get(i));
            hoverElementCircle.get(i).setBounds(x-2, 315, 36, 36);
            
            clickedElementCircle.get(i).setVisible(false);
            clickhoverElementCircle.get(i).setVisible(false);
            hoverElementCircle.get(i).setVisible(false);
            
            getContentPane().setComponentZOrder(lab, 0);
            lab.setVisible(true);
            getContentPane().setComponentZOrder(clickedElementCircle.get(i), 1);
            getContentPane().setComponentZOrder(clickhoverElementCircle.get(i), 1);
            getContentPane().setComponentZOrder(hoverElementCircle.get(i), 1);
            
            x+=40;
        }
        
        
        //set up the hover and click for weapon buttons
        Collections.addAll(clickedWeaponCircle, clickedWeaponCircle1, clickedWeaponCircle2, clickedWeaponCircle3, clickedWeaponCircle4, clickedWeaponCircle5);
        Collections.addAll(clickhoverWeaponCircle, clickhoverWeaponCircle1, clickhoverWeaponCircle2, clickhoverWeaponCircle3, clickhoverWeaponCircle4, clickhoverWeaponCircle5);
        Collections.addAll(hoverWeaponCircle, hoverWeaponCircle1, hoverWeaponCircle2, hoverWeaponCircle3, hoverWeaponCircle4, hoverWeaponCircle5);
        
        x = 872;
        for(int i=0; i<weaponsName.length; i++){
            App.LabelHover lab = new App.LabelHover(false, clickedWeaponCircle.get(i), clickhoverWeaponCircle.get(i), hoverWeaponCircle.get(i), weaponsName[i]);
            lab.setLabelIcon("src/App/image/Weapon/Small/" + weaponsName[i] +".png");
            getContentPane().add(lab);
            lab.setBounds(x, 420, 34, 35);
            
            lab.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(lab.isClicked()){
                        weaponFilter.add(lab.getName());
                        handleClick();
                    }
                    else{
                        weaponFilter.remove(lab.getName());
                        handleReleaseClick();
                    }
                }
            });
            
            getContentPane().add(clickedWeaponCircle.get(i));
            clickedWeaponCircle.get(i).setBounds(x-3, 420, 36, 36);
            getContentPane().add(clickhoverWeaponCircle.get(i));
            clickhoverWeaponCircle.get(i).setBounds(x-3, 420, 36, 36);
            getContentPane().add(hoverWeaponCircle.get(i));
            hoverWeaponCircle.get(i).setBounds(x-3, 420, 36, 36);
            
            clickedWeaponCircle.get(i).setVisible(false);
            clickhoverWeaponCircle.get(i).setVisible(false);
            hoverWeaponCircle.get(i).setVisible(false);
            
            getContentPane().setComponentZOrder(lab, 0);
            lab.setVisible(true);
            getContentPane().setComponentZOrder(clickedWeaponCircle.get(i), 1);
            getContentPane().setComponentZOrder(clickhoverWeaponCircle.get(i), 1);
            getContentPane().setComponentZOrder(hoverWeaponCircle.get(i), 1);
            
            x+=50;
        }
        
        
        //set up hover and click for the 4-star button
        x=875;
        App.LabelHover star4 = new App.LabelHover(false, fourStarClicked, fourStarHoverClick, fourStarHover, "fourstar");
        star4.setLabelIcon("src/App/image/fourstar.png");
        getContentPane().add(star4);
        star4.setBounds(x, 530, star4.getPreferredSize().width, star4.getPreferredSize().height);
        star4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(star4.isClicked()){
                    starFilter.add("4");
                    handleClick();
                }
                else{
                    starFilter.remove("4");
                    handleReleaseClick();
                }
            }
        });

        getContentPane().add(fourStarClicked);
        fourStarClicked.setBounds(x-11, 521, fourStarClicked.getPreferredSize().width, fourStarClicked.getPreferredSize().height);
        getContentPane().add(fourStarHoverClick);
        fourStarHoverClick.setBounds(x-11, 521,fourStarClicked.getPreferredSize().width, fourStarClicked.getPreferredSize().height);
        getContentPane().add(fourStarHover);
        fourStarHover.setBounds(x-11, 521, fourStarClicked.getPreferredSize().width, fourStarClicked.getPreferredSize().height);
        fourStarClicked.setVisible(false);
        fourStarHoverClick.setVisible(false);
        fourStarHover.setVisible(false);

        getContentPane().setComponentZOrder(star4, 0);
        star4.setVisible(true);
        getContentPane().setComponentZOrder(fourStarClicked, 1);
        getContentPane().setComponentZOrder(fourStarHoverClick, 1);
        getContentPane().setComponentZOrder(fourStarHover, 1);
        
        
        //set up hover and click for the 5-star button
        x=x+star4.getPreferredSize().width+30;
        App.LabelHover star5 = new App.LabelHover(false, fiveStarClick, fiveStarHoverClick, fiveStarHover, "fivestar");
        star5.setLabelIcon("src/App/image/fivestar.png");
        getContentPane().add(star5);
        star5.setBounds(x, 530, star5.getPreferredSize().width, star5.getPreferredSize().height);
        star5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(star5.isClicked()){
                    starFilter.add("5");
                    handleClick();
                }
                else{
                    starFilter.remove("5");
                    handleReleaseClick();
                }
            }
        });

        getContentPane().add(fiveStarClick);
        fiveStarClick.setBounds(x-11, 521, fiveStarClick.getPreferredSize().width, fiveStarClick.getPreferredSize().height);
        getContentPane().add(fiveStarHoverClick);
        fiveStarHoverClick.setBounds(x-11, 521, fiveStarClick.getPreferredSize().width, fiveStarClick.getPreferredSize().height);
        getContentPane().add(fiveStarHover);
        fiveStarHover.setBounds(x-11, 521, fiveStarClick.getPreferredSize().width, fiveStarClick.getPreferredSize().height);
        fiveStarClick.setVisible(false);
        fiveStarHoverClick.setVisible(false);
        fiveStarHover.setVisible(false);

        getContentPane().setComponentZOrder(star5, 0);
        star5.setVisible(true);
        getContentPane().setComponentZOrder(fiveStarClick, 1);
        getContentPane().setComponentZOrder(fiveStarHoverClick, 1);
        getContentPane().setComponentZOrder(fiveStarHover, 1);
        
        getContentPane().revalidate();
        getContentPane().repaint();
        
        
        fillCharacterInfo();
        setPanels();
    }
    
    
    private void setPanels(){
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setViewportBorder(null);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        parentPanel = new JPanel(); // The initial panel inside scroll pane
        parentPanel.setLayout(null); // Use absolute layout
        parentPanel.setPreferredSize(new Dimension(400, 200)); // Set initial size
        parentPanel.setOpaque(false);
        parentPanel.setBackground(new Color(0, 0, 0, 0));
        parentPanel.setBorder(null);
        scrollPane.setViewportView(parentPanel); // Set this panel as viewport's view
        
        App.ImageLoader loader2 = new App.ImageLoader();
        loader2.emptyFileName();
        ArrayList<BufferedImage> imageList = loader2.loadImagesFromFolder("src/App/image/CharacterCard/NotZoom");
        ArrayList<String> nameList = loader2.returnFileNames();
        
        ImageIcon travelerImg = new ImageIcon("src/App/image/CharacterCard/Portraits Aether.png");
        for(int i=0; i<imageList.size(); i++){
            ImageIcon im = new ImageIcon(imageList.get(i));
            for(GameCharacter gc: gameChars){
                if(gc.getName().equals(nameList.get(i))){
                    if(gc.getName().contains("Traveler")){
                        allCharacters.put(gc, travelerImg);
                    }
                    else{
                        allCharacters.put(gc, im);
                    }
                }
            }
        }
        
        
        for(GameCharacter gc:allCharacters.keySet()){
            ImageIcon image = allCharacters.get(gc);
            App.CharacterArchivePanel panel = new App.CharacterArchivePanel(40, image, gc);
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    setVisible(false);
                    bgmPlayer.stop();
                    new CharInfo(panel.getGameChar(), userId, username, email, profileImage, bgmPlayer).setVisible(true);
                }
            });
            charPanels.add(panel);
        }
        
        showPanels(charPanels);
        
    }
    
    
    private void fillCharacterInfo(){
        try {
            File myObj = new File("src/App/text/character_1.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("#");
                boolean pneuma = Boolean.parseBoolean(parts[5]);
                boolean ousia = Boolean.parseBoolean(parts[6]);
                int stars = Integer.parseInt(parts[7].trim());
                GameCharacter gc = new GameCharacter(parts[0], parts[1], parts[2], parts[4], pneuma, ousia);
                gc.setStars(stars);
                gameChars.add(gc);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    ArrayList<CharacterArchivePanel> filteredPanels = new ArrayList<>();
    ArrayList<String> elementFilter = new ArrayList<>();
    ArrayList<String> weaponFilter = new ArrayList<>();
    ArrayList<String> starFilter = new ArrayList<>();
    
    private void handleClick(){
        filteredPanels.clear();
        Set<CharacterArchivePanel> setAll = new LinkedHashSet<>();
        ArrayList<CharacterArchivePanel> tempElement = new ArrayList<>();
        ArrayList<CharacterArchivePanel> tempWeapon = new ArrayList<>();
        ArrayList<CharacterArchivePanel> tempStar = new ArrayList<>();
        
        //filter based on elements
        for(CharacterArchivePanel c: charPanels){
            if(elementFilter.contains(c.getGameChar().getElement())){
                tempElement.add(c);
            }
        }
        
        //filter based on weapon
        for(CharacterArchivePanel c: charPanels){
            if(weaponFilter.contains(c.getGameChar().getWeapon())){
                tempWeapon.add(c);
            }
        }
        
        //filter based on stars
        for(CharacterArchivePanel c: charPanels){
            String star = String.valueOf(c.getGameChar().getStars());
            if(starFilter.contains(star)){
                tempStar.add(c);
            }
        }
        
        //intersect all characters with the filtered characters
        setAll.addAll(charPanels);
        if(!(tempElement.isEmpty())){
            setAll.retainAll(tempElement);
        }
        if(!(tempWeapon.isEmpty())){
            setAll.retainAll(tempWeapon);
        }
        if(!(tempStar.isEmpty())){
            setAll.retainAll(tempStar);
        }
        
        //add to filteredPanels to show it
        filteredPanels.addAll(setAll);
        if(weaponFilter.contains("Claymore") && weaponFilter.size()==1 && elementFilter.contains("Hydro") && elementFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Sword") && weaponFilter.size()==1 && elementFilter.contains("Pyro") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Sword") && weaponFilter.size()==1 && elementFilter.contains("Geo") && elementFilter.size()==1 && starFilter.contains("4") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Claymore") && weaponFilter.size()==1 && elementFilter.contains("Dendro") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Claymore") && weaponFilter.size()==1 && elementFilter.contains("Electro") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Claymore") && weaponFilter.size()==1 && elementFilter.contains("Anemo") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Bow") && weaponFilter.size()==1 && elementFilter.contains("Hydro") && elementFilter.size()==1 && starFilter.contains("4") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Bow") && weaponFilter.size()==1 && elementFilter.contains("Electro") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Bow") && weaponFilter.size()==1 && elementFilter.contains("Geo") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Catalyst") && weaponFilter.size()==1 && elementFilter.contains("Dendro") && elementFilter.size()==1 && starFilter.contains("4") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Catalyst") && weaponFilter.size()==1 && elementFilter.contains("Geo") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Polearm") && weaponFilter.size()==1 && elementFilter.contains("Hydro") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Polearm") && weaponFilter.size()==1 && elementFilter.contains("Dendro") && elementFilter.size()==1 && starFilter.contains("5") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Polearm") && weaponFilter.size()==1 && elementFilter.contains("Electro") && elementFilter.size()==1 && starFilter.contains("4") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else if(weaponFilter.contains("Polearm") && weaponFilter.size()==1 && elementFilter.contains("Anemo") && elementFilter.size()==1 && starFilter.contains("4") && starFilter.size()==1){
            scrollPane.setVisible(false);
        }
        else{
            scrollPane.setVisible(true);
            showPanels(filteredPanels);
        }
    }
    
    private void handleReleaseClick(){
        //show all characters if no filter
        if(weaponFilter.isEmpty() && elementFilter.isEmpty() && starFilter.isEmpty()){
            showPanels(charPanels);
        }
        else{
            handleClick();
        }
        
    }
    
    
    private void showPanels(ArrayList<CharacterArchivePanel> charList){
        parentPanel.removeAll();
        int row=0, column=0;
        for(int i=0; i<charList.size(); i++){
            CharacterArchivePanel clonedPanel = charList.get(i);
            int panelWidth = 200;
            int panelHeight = 250;

            // Calculate the row and column indices
            row = i / 3;
            column = i % 3;

            // Calculate the x and y positions based on row and column indices
            int x = 10 + column * (panelWidth + 15);
            int y = 10 + row * (panelHeight + 30);

            // Set the bounds for the cloned panel with your custom size
            clonedPanel.setBounds(x, y, panelWidth, panelHeight);
            
            // Add the cloned panel to the initial panel
            parentPanel.add(clonedPanel);
            // Adjust preferred size of initial panel to include new panel
            Dimension newSize = new Dimension(parentPanel.getWidth(), y + panelHeight + 10); // Adjusted size
            parentPanel.setPreferredSize(newSize);
            parentPanel.revalidate();
            parentPanel.repaint();
            // Ensure the scroll pane updates its viewport
            scrollPane.revalidate();
            scrollPane.repaint();
            // Scroll to show the new panel
            scrollPane.getVerticalScrollBar().setValue(0);
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profileButton = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        hoverElementCircle1 = new javax.swing.JLabel();
        hoverElementCircle2 = new javax.swing.JLabel();
        hoverElementCircle3 = new javax.swing.JLabel();
        hoverElementCircle4 = new javax.swing.JLabel();
        hoverElementCircle5 = new javax.swing.JLabel();
        hoverElementCircle6 = new javax.swing.JLabel();
        hoverElementCircle7 = new javax.swing.JLabel();
        clickhoverElementCircle1 = new javax.swing.JLabel();
        clickhoverElementCircle2 = new javax.swing.JLabel();
        clickhoverElementCircle3 = new javax.swing.JLabel();
        clickhoverElementCircle4 = new javax.swing.JLabel();
        clickhoverElementCircle5 = new javax.swing.JLabel();
        clickhoverElementCircle6 = new javax.swing.JLabel();
        clickhoverElementCircle7 = new javax.swing.JLabel();
        clickedElementCircle1 = new javax.swing.JLabel();
        clickedElementCircle2 = new javax.swing.JLabel();
        clickedElementCircle3 = new javax.swing.JLabel();
        clickedElementCircle4 = new javax.swing.JLabel();
        clickedElementCircle5 = new javax.swing.JLabel();
        clickedElementCircle6 = new javax.swing.JLabel();
        clickedElementCircle7 = new javax.swing.JLabel();
        clickhoverWeaponCircle1 = new javax.swing.JLabel();
        clickhoverWeaponCircle2 = new javax.swing.JLabel();
        clickhoverWeaponCircle3 = new javax.swing.JLabel();
        clickhoverWeaponCircle4 = new javax.swing.JLabel();
        clickhoverWeaponCircle5 = new javax.swing.JLabel();
        clickedWeaponCircle1 = new javax.swing.JLabel();
        clickedWeaponCircle2 = new javax.swing.JLabel();
        clickedWeaponCircle3 = new javax.swing.JLabel();
        clickedWeaponCircle4 = new javax.swing.JLabel();
        clickedWeaponCircle5 = new javax.swing.JLabel();
        hoverWeaponCircle1 = new javax.swing.JLabel();
        hoverWeaponCircle2 = new javax.swing.JLabel();
        hoverWeaponCircle3 = new javax.swing.JLabel();
        hoverWeaponCircle4 = new javax.swing.JLabel();
        hoverWeaponCircle5 = new javax.swing.JLabel();
        fourStarClicked = new javax.swing.JLabel();
        fourStarHover = new javax.swing.JLabel();
        fourStarHoverClick = new javax.swing.JLabel();
        fiveStarClick = new javax.swing.JLabel();
        fiveStarHover = new javax.swing.JLabel();
        fiveStarHoverClick = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        parentPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Character Archive");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(null);

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
        getContentPane().add(profileButton);
        profileButton.setBounds(30, 15, 70, 70);

        usernameLabel.setFont(new java.awt.Font("Sunflower Medium", 0, 28)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(252, 236, 214));
        usernameLabel.setText("Username");
        getContentPane().add(usernameLabel);
        usernameLabel.setBounds(110, 25, 122, 36);

        emailLabel.setFont(new java.awt.Font("Sunflower Medium", 0, 20)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(252, 236, 214));
        emailLabel.setText("Email@gmail.com");
        getContentPane().add(emailLabel);
        emailLabel.setBounds(110, 60, 157, 26);

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
        getContentPane().add(exitButton);
        exitButton.setBounds(1180, 20, 70, 70);

        jLabel3.setFont(new java.awt.Font("HYWenHei-85W", 0, 32)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(251, 172, 71));
        jLabel3.setText("Filter");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(870, 210, 110, 40);

        jLabel4.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(252, 236, 214));
        jLabel4.setText("Weapons:");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(870, 380, 280, 40);

        jLabel5.setFont(new java.awt.Font("HYWenHei-85W", 0, 40)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(252, 236, 214));
        jLabel5.setText("Character Archive");
        jLabel5.setToolTipText("");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 120, 380, 40);

        jLabel6.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(252, 236, 214));
        jLabel6.setText("Rarity:");
        jLabel6.setToolTipText("");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(870, 480, 280, 40);

        jLabel7.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(252, 236, 214));
        jLabel7.setText("Elemental Type:");
        jLabel7.setToolTipText("");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(870, 270, 280, 40);

        hoverElementCircle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverElementCircle1);
        hoverElementCircle1.setBounds(880, 420, 36, 36);

        hoverElementCircle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverElementCircle2);
        hoverElementCircle2.setBounds(1080, 230, 36, 36);

        hoverElementCircle3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverElementCircle3);
        hoverElementCircle3.setBounds(1080, 230, 36, 36);

        hoverElementCircle4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverElementCircle4);
        hoverElementCircle4.setBounds(1080, 230, 36, 36);

        hoverElementCircle5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverElementCircle5);
        hoverElementCircle5.setBounds(1080, 230, 36, 36);

        hoverElementCircle6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverElementCircle6);
        hoverElementCircle6.setBounds(1080, 230, 36, 36);

        hoverElementCircle7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverElementCircle7);
        hoverElementCircle7.setBounds(1080, 230, 36, 36);

        clickhoverElementCircle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverElementCircle1);
        clickhoverElementCircle1.setBounds(980, 230, 36, 36);

        clickhoverElementCircle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverElementCircle2);
        clickhoverElementCircle2.setBounds(980, 230, 36, 36);

        clickhoverElementCircle3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverElementCircle3);
        clickhoverElementCircle3.setBounds(980, 230, 36, 36);

        clickhoverElementCircle4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverElementCircle4);
        clickhoverElementCircle4.setBounds(980, 230, 36, 36);

        clickhoverElementCircle5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverElementCircle5);
        clickhoverElementCircle5.setBounds(980, 230, 36, 36);

        clickhoverElementCircle6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverElementCircle6);
        clickhoverElementCircle6.setBounds(980, 230, 36, 36);

        clickhoverElementCircle7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverElementCircle7);
        clickhoverElementCircle7.setBounds(980, 230, 36, 36);

        clickedElementCircle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedElementCircle1);
        clickedElementCircle1.setBounds(1030, 230, 36, 36);

        clickedElementCircle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedElementCircle2);
        clickedElementCircle2.setBounds(1030, 230, 36, 36);

        clickedElementCircle3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedElementCircle3);
        clickedElementCircle3.setBounds(1030, 230, 36, 36);

        clickedElementCircle4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedElementCircle4);
        clickedElementCircle4.setBounds(1030, 230, 36, 36);

        clickedElementCircle5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedElementCircle5);
        clickedElementCircle5.setBounds(1030, 230, 36, 36);

        clickedElementCircle6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedElementCircle6);
        clickedElementCircle6.setBounds(1030, 230, 36, 36);

        clickedElementCircle7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedElementCircle7);
        clickedElementCircle7.setBounds(1030, 230, 36, 36);

        clickhoverWeaponCircle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverWeaponCircle1);
        clickhoverWeaponCircle1.setBounds(980, 230, 36, 36);

        clickhoverWeaponCircle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverWeaponCircle2);
        clickhoverWeaponCircle2.setBounds(980, 230, 36, 36);

        clickhoverWeaponCircle3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverWeaponCircle3);
        clickhoverWeaponCircle3.setBounds(980, 230, 36, 36);

        clickhoverWeaponCircle4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverWeaponCircle4);
        clickhoverWeaponCircle4.setBounds(980, 230, 36, 36);

        clickhoverWeaponCircle5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hoverclicked.png"))); // NOI18N
        getContentPane().add(clickhoverWeaponCircle5);
        clickhoverWeaponCircle5.setBounds(980, 230, 36, 36);

        clickedWeaponCircle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedWeaponCircle1);
        clickedWeaponCircle1.setBounds(1030, 230, 36, 36);

        clickedWeaponCircle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedWeaponCircle2);
        clickedWeaponCircle2.setBounds(1030, 230, 36, 36);

        clickedWeaponCircle3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedWeaponCircle3);
        clickedWeaponCircle3.setBounds(1030, 230, 36, 36);

        clickedWeaponCircle4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedWeaponCircle4);
        clickedWeaponCircle4.setBounds(1030, 230, 36, 36);

        clickedWeaponCircle5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_clicked.png"))); // NOI18N
        getContentPane().add(clickedWeaponCircle5);
        clickedWeaponCircle5.setBounds(1030, 230, 36, 36);

        hoverWeaponCircle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverWeaponCircle1);
        hoverWeaponCircle1.setBounds(1080, 230, 36, 36);

        hoverWeaponCircle2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverWeaponCircle2);
        hoverWeaponCircle2.setBounds(1080, 230, 36, 36);

        hoverWeaponCircle3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverWeaponCircle3);
        hoverWeaponCircle3.setBounds(1080, 230, 36, 36);

        hoverWeaponCircle4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverWeaponCircle4);
        hoverWeaponCircle4.setBounds(1080, 230, 36, 36);

        hoverWeaponCircle5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle1_hover.png"))); // NOI18N
        getContentPane().add(hoverWeaponCircle5);
        hoverWeaponCircle5.setBounds(1080, 230, 36, 36);

        fourStarClicked.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle8_clicked.png"))); // NOI18N
        getContentPane().add(fourStarClicked);
        fourStarClicked.setBounds(890, 516, 120, 40);

        fourStarHover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle8_hover.png"))); // NOI18N
        getContentPane().add(fourStarHover);
        fourStarHover.setBounds(890, 496, 140, 60);

        fourStarHoverClick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle8_hoverclicked.png"))); // NOI18N
        getContentPane().add(fourStarHoverClick);
        fourStarHoverClick.setBounds(890, 540, 114, 39);

        fiveStarClick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle9_clicked.png"))); // NOI18N
        getContentPane().add(fiveStarClick);
        fiveStarClick.setBounds(890, 540, 138, 39);

        fiveStarHover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle9_hover.png"))); // NOI18N
        getContentPane().add(fiveStarHover);
        fiveStarHover.setBounds(890, 540, 138, 39);

        fiveStarHoverClick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle9_hoverclick.png"))); // NOI18N
        getContentPane().add(fiveStarHoverClick);
        fiveStarHoverClick.setBounds(890, 540, 138, 39);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle7.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(840, 180, 381, 450);

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        parentPanel.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout parentPanelLayout = new javax.swing.GroupLayout(parentPanel);
        parentPanel.setLayout(parentPanelLayout);
        parentPanelLayout.setHorizontalGroup(
            parentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        parentPanelLayout.setVerticalGroup(
            parentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(parentPanel);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(60, 190, 700, 440);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_charinfohome.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    private void profileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButtonMouseClicked
        setVisible(false);
        dispose();
        new Settings(userId, bgmPlayer).setVisible(true);
    }//GEN-LAST:event_profileButtonMouseClicked

    private void profileButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButtonMouseEntered
        profileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_profileButtonMouseEntered

    private void profileButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButtonMouseExited
        profileButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_profileButtonMouseExited

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        setVisible(false);
        dispose();
        new Home(userId, bgmPlayer).setVisible(true);
    }//GEN-LAST:event_exitButtonMouseClicked

    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit2.png")));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_exitButtonMouseEntered

    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit1.png")));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_exitButtonMouseExited
    
    
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
            java.util.logging.Logger.getLogger(CharInfoHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CharInfoHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CharInfoHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CharInfoHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CharInfoHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clickedElementCircle1;
    private javax.swing.JLabel clickedElementCircle2;
    private javax.swing.JLabel clickedElementCircle3;
    private javax.swing.JLabel clickedElementCircle4;
    private javax.swing.JLabel clickedElementCircle5;
    private javax.swing.JLabel clickedElementCircle6;
    private javax.swing.JLabel clickedElementCircle7;
    private javax.swing.JLabel clickedWeaponCircle1;
    private javax.swing.JLabel clickedWeaponCircle2;
    private javax.swing.JLabel clickedWeaponCircle3;
    private javax.swing.JLabel clickedWeaponCircle4;
    private javax.swing.JLabel clickedWeaponCircle5;
    private javax.swing.JLabel clickhoverElementCircle1;
    private javax.swing.JLabel clickhoverElementCircle2;
    private javax.swing.JLabel clickhoverElementCircle3;
    private javax.swing.JLabel clickhoverElementCircle4;
    private javax.swing.JLabel clickhoverElementCircle5;
    private javax.swing.JLabel clickhoverElementCircle6;
    private javax.swing.JLabel clickhoverElementCircle7;
    private javax.swing.JLabel clickhoverWeaponCircle1;
    private javax.swing.JLabel clickhoverWeaponCircle2;
    private javax.swing.JLabel clickhoverWeaponCircle3;
    private javax.swing.JLabel clickhoverWeaponCircle4;
    private javax.swing.JLabel clickhoverWeaponCircle5;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel fiveStarClick;
    private javax.swing.JLabel fiveStarHover;
    private javax.swing.JLabel fiveStarHoverClick;
    private javax.swing.JLabel fourStarClicked;
    private javax.swing.JLabel fourStarHover;
    private javax.swing.JLabel fourStarHoverClick;
    private javax.swing.JLabel hoverElementCircle1;
    private javax.swing.JLabel hoverElementCircle2;
    private javax.swing.JLabel hoverElementCircle3;
    private javax.swing.JLabel hoverElementCircle4;
    private javax.swing.JLabel hoverElementCircle5;
    private javax.swing.JLabel hoverElementCircle6;
    private javax.swing.JLabel hoverElementCircle7;
    private javax.swing.JLabel hoverWeaponCircle1;
    private javax.swing.JLabel hoverWeaponCircle2;
    private javax.swing.JLabel hoverWeaponCircle3;
    private javax.swing.JLabel hoverWeaponCircle4;
    private javax.swing.JLabel hoverWeaponCircle5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel profileButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
    

}
