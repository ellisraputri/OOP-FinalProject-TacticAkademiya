/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class TeamResult extends javax.swing.JFrame {
    private int userId;
    private String username;
    private String email;
    private ArrayList<String> teamGenerated;
    private ArrayList<String> teamInfo = new ArrayList<>();
    private int maxWidth=0;
    private HashMap<String,ArrayList<String>>teamSpiralAbyss = new LinkedHashMap<>();
    /**
     * Creates new form TeamResult
     */
    public TeamResult() {
        initComponents();
    }
    
    public TeamResult(int userId, String username, String email, ArrayList<String>teamGenerated){
        initComponents();
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.teamGenerated = teamGenerated;
        resultPage1();
    }
    
    public TeamResult(int userId, String username, String email, HashMap<String,ArrayList<String>>teamSpiralAbyss){
        initComponents();
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.teamSpiralAbyss = teamSpiralAbyss;
        resultPage2();
    }
    
    private void resultPage1(){
        parentPanel.removeAll();
        parentPanel.add(resultPage1);
        parentPanel.revalidate();
        parentPanel.repaint();
        
        usernameLabel.setText(username);
        emailLabel.setText(email);
        usernameLabel.setBounds(110, 25, usernameLabel.getPreferredSize().width+10, usernameLabel.getPreferredSize().height);
        emailLabel.setBounds(110, 60, emailLabel.getPreferredSize().width+10, emailLabel.getPreferredSize().height);
        
        setImages(0);
        setImages(1);
        setImages(2);
        setImages(3);
        
        findCharInfo(teamGenerated.get(0));
        findCharInfo(teamGenerated.get(1));
        findCharInfo(teamGenerated.get(2));
        findCharInfo(teamGenerated.get(3));
        
        setTextAndLayout(char1Label1, char1Label, 0);
        setTextAndLayout(char2Label1, char2Label, 1);
        setTextAndLayout(char3Label1, char3Label, 2);
        setTextAndLayout(char4Label1, char4Label, 3);
        
        rectangle1.setBounds(90, 370, maxWidth+70, rectangle1.getPreferredSize().height);
        resultPage1.revalidate();
        resultPage1.repaint();
        
        System.out.println(jLabel8.getY());
        System.out.println(rectangle1.getY());
    }
    
    private void setImages(int index){
        String charName = teamGenerated.get(index);
        ImageIcon icon = new ImageIcon("src/App/image/CharacterCard/NotZoom/Portraits "+charName+".png");
        
        CharacterPanel charPanel = new CharacterPanel(charName);
        charPanel.settingPanel(icon, charName, 150, 150,14,false);
        charPanel.settingMouse();
        
        int x = 100 + (index*150) + (index*10);
        charPanel.setBounds(x,180,150,150);
        charPanel.setOpaque(false);
        charPanel.setBackground(new java.awt.Color(0,0,0,0));
        resultPage1.add(charPanel);
        resultPage1.setComponentZOrder(charPanel, 0);
        resultPage1.revalidate();
        resultPage1.repaint();
    }
    
    private void setTextAndLayout(JLabel label, JLabel label2, int index){
        String str = teamGenerated.get(index) + ": ";
        label.setText(str);
        label.setBounds(label.getX(), label.getY(), label.getPreferredSize().width, label.getPreferredSize().height);
        
        String str2 = teamInfo.get(index);
        label2.setText(str2);
        label2.setBounds(label.getPreferredSize().width+label.getX()+5, label.getY(), label2.getPreferredSize().width, label2.getPreferredSize().height);
        
        if(label2.getPreferredSize().width+label.getPreferredSize().width > maxWidth){
            maxWidth = label2.getPreferredSize().width+label.getPreferredSize().width;
        }
    }
    
    private void findCharInfo(String name){
        try {
            File myObj = new File("src/App/text/character.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] part = data.split("#");
                if(part[0].equals(name)){
                    teamInfo.add(part[3]);
                }
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    private void resultPage2(){
        parentPanel.removeAll();
        parentPanel.add(resultPage2);
        parentPanel.revalidate();
        parentPanel.repaint();
        
        usernameLabel1.setText(username);
        emailLabel1.setText(email);
        usernameLabel1.setBounds(110, 25, usernameLabel1.getPreferredSize().width+10, usernameLabel1.getPreferredSize().height);
        emailLabel1.setBounds(110, 60, emailLabel1.getPreferredSize().width+10, emailLabel1.getPreferredSize().height);
        
        setImages2(0, "First Half");
        setImages2(1, "First Half");
        setImages2(2, "First Half");
        setImages2(3, "First Half");
        setImages2(0, "Second Half");
        setImages2(1, "Second Half");
        setImages2(2, "Second Half");
        setImages2(3, "Second Half");
        
        for(String key: teamSpiralAbyss.keySet()){
            findCharInfo(teamSpiralAbyss.get(key).get(0));
            findCharInfo(teamSpiralAbyss.get(key).get(1));
            findCharInfo(teamSpiralAbyss.get(key).get(2));
            findCharInfo(teamSpiralAbyss.get(key).get(3));
        }
        
        Insets insets = new Insets(2,2,2,2);
        char1Detail = new App.WrappedLabel(340, new Color(0,0,0,0), insets);
        char1Detail.setFont(new java.awt.Font("HYWenHei-85W", Font.PLAIN, 18)); // NOI18N
        char1Detail.setForeground(new java.awt.Color(255, 247, 235));
        char1Detail.setOpaque(false);
        resultPage2.add(char1Detail);
               
        char2Detail = new App.WrappedLabel(340, new Color(0,0,0,0), insets);
        char2Detail.setFont(new java.awt.Font("HYWenHei-85W", Font.PLAIN, 18)); // NOI18N
        char2Detail.setForeground(new java.awt.Color(255, 247, 235));
        char2Detail.setOpaque(false);
        resultPage2.add(char2Detail);
        
        char3Detail = new App.WrappedLabel(340, new Color(0,0,0,0), insets);
        char3Detail.setFont(new java.awt.Font("HYWenHei-85W", Font.PLAIN, 18)); // NOI18N
        char3Detail.setForeground(new java.awt.Color(255, 247, 235));
        char3Detail.setOpaque(false);
        resultPage2.add(char3Detail);
        
        char4Detail = new App.WrappedLabel(340, new Color(0,0,0,0), insets);
        char4Detail.setFont(new java.awt.Font("HYWenHei-85W", Font.PLAIN, 18)); // NOI18N
        char4Detail.setForeground(new java.awt.Color(255, 247, 235));
        char4Detail.setOpaque(false);
        resultPage2.add(char4Detail);
        
        resetClick();
        
        resultPage2.revalidate();
        resultPage2.repaint();

    }
    
    private void setImages2(int index, String type){
        String charName ="";
        int y=0;
        if(type.equals("First Half")){
            charName = teamSpiralAbyss.get("First Half").get(index);
            y=240;
        }
        else{
            charName = teamSpiralAbyss.get("Second Half").get(index);
            y=460;
        }
        ImageIcon icon = new ImageIcon("src/App/image/CharacterCard/Small/Portraits "+charName+".png");
        
        CharacterPanelNonClick charPanel = new CharacterPanelNonClick(charName);
        charPanel.settingPanel(icon, charName, 120, 120,12,false);
        charPanel.settingMouse();
        
        int x = 100 + (index*120) + (index*10);
        charPanel.setBounds(x,y,120,120);
        charPanel.setOpaque(false);
        charPanel.setBackground(new java.awt.Color(0,0,0,0));
        resultPage2.add(charPanel);
        resultPage2.setComponentZOrder(charPanel, 0);
        resultPage2.revalidate();
        resultPage2.repaint();
    }
    
   
    private int heightTrackFirst=320;
    private int heightTrackSecond=320;
    private void setTextAndLayout(JLabel label, App.WrappedLabel label2, int index, String type){
        label2.setOpaque(false);
        String str = (type.equals("First Half"))? teamSpiralAbyss.get("First Half").get(index) : teamSpiralAbyss.get("Second Half").get(index-4);
        str = str + ":";
        label.setText(str);
        int y = (type.equals("First Half"))? heightTrackFirst : heightTrackSecond;
        label.setBounds(label.getX(), y, label.getPreferredSize().width, label.getPreferredSize().height);
        
        String str2 = teamInfo.get(index);
        if(str.equals("Sangonomiya Kokomi:")){
            System.out.println("kokomi");
            String[]strArr = str2.split(", ");
            String str3 = "<html>" + strArr[0].trim() + ",<br>" + strArr[1].trim() + "</html>";;
            label2.setText(str3);
        }
        else{
            label2.setText(str2);
        }
        
        label2.setBounds(label.getPreferredSize().width+label.getX()+5, y-2, label2.getPreferredSize().width, label2.getPreferredSize().height);
        
        if(type.equals("First Half")){
            heightTrackFirst = heightTrackFirst + 20 + label2.getPreferredSize().height;
        }
        else{
            heightTrackSecond = heightTrackSecond + 20 + label2.getPreferredSize().height;
        }
        
        
        label2.setOpaque(false);
        label.setBackground(new Color(0,0,0,0));
        label2.setBackgroundColor(Color.red);
        
        
        resultPage2.add(label);
        resultPage2.add(label2);
        resultPage2.setComponentZOrder(label, 0);
        resultPage2.setComponentZOrder(label2, 0);
        resultPage2.revalidate();
        resultPage2.repaint();
    }
    
    private void handleClickFirstHalf(){
        heightTrackFirst = 320;
        char1Name.setVisible(true);
        char2Name.setVisible(true);
        char3Name.setVisible(true);
        char4Name.setVisible(true);
        char1Detail.setVisible(true);
        char2Detail.setVisible(true);
        char3Detail.setVisible(true);
        char4Detail.setVisible(true);
        setTextAndLayout(char1Name, char1Detail, 0, "First Half");
        setTextAndLayout(char2Name, char2Detail, 1, "First Half");
        setTextAndLayout(char3Name, char3Detail, 2, "First Half");
        setTextAndLayout(char4Name, char4Detail, 3, "First Half");
    }
    
    private void handleClickSecondHalf(){
        heightTrackSecond = 320;
        char1Name.setVisible(true);
        char2Name.setVisible(true);
        char3Name.setVisible(true);
        char4Name.setVisible(true);
        char1Detail.setVisible(true);
        char2Detail.setVisible(true);
        char3Detail.setVisible(true);
        char4Detail.setVisible(true);
        System.out.println('t');
        setTextAndLayout(char1Name, char1Detail, 4, "Second Half");
        setTextAndLayout(char2Name, char2Detail, 5, "Second Half");
        setTextAndLayout(char3Name, char3Detail, 6, "Second Half");
        setTextAndLayout(char4Name, char4Detail, 7, "Second Half");
    }
    
    private void resetClick(){
        char1Name.setVisible(false);
        char2Name.setVisible(false);
        char3Name.setVisible(false);
        char4Name.setVisible(false);
        char1Detail.setVisible(false);
        char2Detail.setVisible(false);
        char3Detail.setVisible(false);
        char4Detail.setVisible(false);
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
        resultPage1 = new javax.swing.JPanel();
        profileButton = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        char2Label = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        char3Label = new javax.swing.JLabel();
        char4Label = new javax.swing.JLabel();
        char1Label = new javax.swing.JLabel();
        char1Label1 = new javax.swing.JLabel();
        char2Label1 = new javax.swing.JLabel();
        char3Label1 = new javax.swing.JLabel();
        char4Label1 = new javax.swing.JLabel();
        rectangle1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        resultPage2 = new javax.swing.JPanel();
        profileButton1 = new javax.swing.JLabel();
        usernameLabel1 = new javax.swing.JLabel();
        emailLabel1 = new javax.swing.JLabel();
        exitButton1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        char2Name = new javax.swing.JLabel();
        char4Name = new javax.swing.JLabel();
        char3Name = new javax.swing.JLabel();
        char1Name = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        secondHalfButton = new javax.swing.JLabel();
        firstHalfButton = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Team Result");
        setMinimumSize(new java.awt.Dimension(1293, 750));
        setResizable(false);
        getContentPane().setLayout(null);

        parentPanel.setBackground(new java.awt.Color(255, 255, 255));
        parentPanel.setLayout(new java.awt.CardLayout());

        resultPage1.setLayout(null);

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
        resultPage1.add(profileButton);
        profileButton.setBounds(30, 15, 70, 70);

        usernameLabel.setFont(new java.awt.Font("Sunflower Medium", 0, 28)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(252, 236, 214));
        usernameLabel.setText("Username");
        resultPage1.add(usernameLabel);
        usernameLabel.setBounds(110, 25, 122, 40);

        emailLabel.setFont(new java.awt.Font("Sunflower Medium", 0, 20)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(252, 236, 214));
        emailLabel.setText("Email@gmail.com");
        resultPage1.add(emailLabel);
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
        resultPage1.add(exitButton);
        exitButton.setBounds(1190, 20, 70, 70);

        char2Label.setBackground(new java.awt.Color(252, 236, 214));
        char2Label.setFont(new java.awt.Font("HYWenHei-85W", 0, 20)); // NOI18N
        char2Label.setForeground(new java.awt.Color(255, 247, 235));
        char2Label.setText("Team Explanation");
        resultPage1.add(char2Label);
        char2Label.setBounds(330, 490, 190, 30);

        jLabel7.setBackground(new java.awt.Color(241, 167, 84));
        jLabel7.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 175, 85));
        jLabel7.setText("Best Team");
        resultPage1.add(jLabel7);
        jLabel7.setBounds(100, 120, 200, 50);

        jLabel8.setBackground(new java.awt.Color(241, 167, 84));
        jLabel8.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Team Explanation");
        resultPage1.add(jLabel8);
        jLabel8.setBounds(110, 380, 340, 50);

        char3Label.setBackground(new java.awt.Color(252, 236, 214));
        char3Label.setFont(new java.awt.Font("HYWenHei-85W", 0, 20)); // NOI18N
        char3Label.setForeground(new java.awt.Color(255, 247, 235));
        char3Label.setText("Team Explanation");
        resultPage1.add(char3Label);
        char3Label.setBounds(340, 540, 190, 30);

        char4Label.setBackground(new java.awt.Color(252, 236, 214));
        char4Label.setFont(new java.awt.Font("HYWenHei-85W", 0, 20)); // NOI18N
        char4Label.setForeground(new java.awt.Color(255, 247, 235));
        char4Label.setText("Team Explanation");
        resultPage1.add(char4Label);
        char4Label.setBounds(340, 590, 190, 30);

        char1Label.setBackground(new java.awt.Color(252, 236, 214));
        char1Label.setFont(new java.awt.Font("HYWenHei-85W", 0, 20)); // NOI18N
        char1Label.setForeground(new java.awt.Color(255, 247, 235));
        char1Label.setText("Team Explanation");
        resultPage1.add(char1Label);
        char1Label.setBounds(330, 440, 190, 30);

        char1Label1.setBackground(new java.awt.Color(252, 236, 214));
        char1Label1.setFont(new java.awt.Font("HYWenHei-85W", 0, 20)); // NOI18N
        char1Label1.setForeground(new java.awt.Color(111, 70, 10));
        char1Label1.setText("Team Explanation");
        resultPage1.add(char1Label1);
        char1Label1.setBounds(120, 440, 190, 30);

        char2Label1.setBackground(new java.awt.Color(252, 236, 214));
        char2Label1.setFont(new java.awt.Font("HYWenHei-85W", 0, 20)); // NOI18N
        char2Label1.setForeground(new java.awt.Color(111, 70, 10));
        char2Label1.setText("Team Explanation");
        resultPage1.add(char2Label1);
        char2Label1.setBounds(120, 490, 190, 30);

        char3Label1.setBackground(new java.awt.Color(252, 236, 214));
        char3Label1.setFont(new java.awt.Font("HYWenHei-85W", 0, 20)); // NOI18N
        char3Label1.setForeground(new java.awt.Color(111, 70, 10));
        char3Label1.setText("Team Explanation");
        resultPage1.add(char3Label1);
        char3Label1.setBounds(120, 540, 190, 30);

        char4Label1.setBackground(new java.awt.Color(252, 236, 214));
        char4Label1.setFont(new java.awt.Font("HYWenHei-85W", 0, 20)); // NOI18N
        char4Label1.setForeground(new java.awt.Color(111, 70, 10));
        char4Label1.setText("Team Explanation");
        resultPage1.add(char4Label1);
        char4Label1.setBounds(120, 590, 190, 30);

        rectangle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle3.png"))); // NOI18N
        resultPage1.add(rectangle1);
        rectangle1.setBounds(90, 370, 1130, 300);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_teamresult.png"))); // NOI18N
        resultPage1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 720);

        parentPanel.add(resultPage1, "card2");

        resultPage2.setLayout(null);

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
        resultPage2.add(profileButton1);
        profileButton1.setBounds(30, 15, 70, 70);

        usernameLabel1.setFont(new java.awt.Font("Sunflower Medium", 0, 28)); // NOI18N
        usernameLabel1.setForeground(new java.awt.Color(252, 236, 214));
        usernameLabel1.setText("Username");
        resultPage2.add(usernameLabel1);
        usernameLabel1.setBounds(110, 25, 150, 40);

        emailLabel1.setFont(new java.awt.Font("Sunflower Medium", 0, 20)); // NOI18N
        emailLabel1.setForeground(new java.awt.Color(252, 236, 214));
        emailLabel1.setText("Email@gmail.com");
        resultPage2.add(emailLabel1);
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
        resultPage2.add(exitButton1);
        exitButton1.setBounds(1190, 20, 70, 70);

        jLabel9.setBackground(new java.awt.Color(241, 167, 84));
        jLabel9.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 175, 85));
        jLabel9.setText("Best Teams");
        resultPage2.add(jLabel9);
        jLabel9.setBounds(90, 140, 230, 50);

        jLabel10.setBackground(new java.awt.Color(241, 167, 84));
        jLabel10.setFont(new java.awt.Font("HYWenHei-85W", 0, 28)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Second Half");
        resultPage2.add(jLabel10);
        jLabel10.setBounds(100, 410, 190, 50);

        char2Name.setBackground(new java.awt.Color(252, 236, 214));
        char2Name.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        char2Name.setForeground(new java.awt.Color(111, 70, 10));
        char2Name.setText("Team Explanation");
        resultPage2.add(char2Name);
        char2Name.setBounds(750, 370, 190, 30);

        char4Name.setBackground(new java.awt.Color(252, 236, 214));
        char4Name.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        char4Name.setForeground(new java.awt.Color(111, 70, 10));
        char4Name.setText("Team Explanation");
        resultPage2.add(char4Name);
        char4Name.setBounds(750, 470, 190, 30);

        char3Name.setBackground(new java.awt.Color(252, 236, 214));
        char3Name.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        char3Name.setForeground(new java.awt.Color(111, 70, 10));
        char3Name.setText("Team Explanation");
        resultPage2.add(char3Name);
        char3Name.setBounds(750, 420, 190, 30);

        char1Name.setBackground(new java.awt.Color(252, 236, 214));
        char1Name.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        char1Name.setForeground(new java.awt.Color(111, 70, 10));
        char1Name.setText("Team Explanation");
        resultPage2.add(char1Name);
        char1Name.setBounds(750, 320, 190, 30);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Team Explanation");
        resultPage2.add(jLabel12);
        jLabel12.setBounds(740, 160, 340, 50);

        secondHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle6.png"))); // NOI18N
        secondHalfButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                secondHalfButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                secondHalfButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                secondHalfButtonMouseExited(evt);
            }
        });
        resultPage2.add(secondHalfButton);
        secondHalfButton.setBounds(960, 220, 201, 70);

        firstHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle5.png"))); // NOI18N
        firstHalfButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                firstHalfButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                firstHalfButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                firstHalfButtonMouseExited(evt);
            }
        });
        resultPage2.add(firstHalfButton);
        firstHalfButton.setBounds(740, 220, 210, 70);

        jLabel13.setBackground(new java.awt.Color(241, 167, 84));
        jLabel13.setFont(new java.awt.Font("HYWenHei-85W", 0, 28)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("First Half");
        resultPage2.add(jLabel13);
        jLabel13.setBounds(100, 190, 160, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle4.png"))); // NOI18N
        resultPage2.add(jLabel3);
        jLabel3.setBounds(720, 140, 470, 530);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_teamresult.png"))); // NOI18N
        resultPage2.add(jLabel2);
        jLabel2.setBounds(0, 0, 1280, 720);

        parentPanel.add(resultPage2, "card2");

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
        int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to go back?", "SELECT", JOptionPane.YES_NO_OPTION);
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
        int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to go back?", "SELECT", JOptionPane.YES_NO_OPTION);
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

    
    private boolean firstHalfOver = false;
    private boolean firstHalfClicked = false;
    
    private void firstHalfButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstHalfButtonMouseClicked
        firstHalfClicked = !(firstHalfClicked);
        if(firstHalfClicked){
            firstHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle5_hoverclicked.png")));
            firstHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            handleClickFirstHalf();
        }
        else{
            firstHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle5_hover.png")));
            firstHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        
        if(firstHalfClicked == false && secondHalfClicked == false){
            resetClick();
        }
        
        if(secondHalfClicked){
            secondHalfClicked = false;
            secondHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle6.png")));
            secondHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_firstHalfButtonMouseClicked

    private void firstHalfButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstHalfButtonMouseEntered
        firstHalfOver = true;
        if(firstHalfClicked){
            firstHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle5_hoverclicked.png")));
            firstHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else{
            firstHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle5_hover.png")));
            firstHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_firstHalfButtonMouseEntered

    private void firstHalfButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstHalfButtonMouseExited
        firstHalfOver = false;
        if(firstHalfClicked){
            firstHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle5_clicked.png")));
            firstHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        else{
            firstHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle5.png")));
            firstHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_firstHalfButtonMouseExited

    
    
    private boolean secondHalfOver = false;
    private boolean secondHalfClicked = false;
    
    private void secondHalfButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secondHalfButtonMouseClicked
        secondHalfClicked = !(secondHalfClicked);
        if(secondHalfClicked){
            secondHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle6_hoverclicked.png")));
            secondHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            handleClickSecondHalf();
        }
        else{
            secondHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle6_hover.png")));
            secondHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        
        if(firstHalfClicked == false && secondHalfClicked == false){
            resetClick();
        }
        if(firstHalfClicked){
            firstHalfClicked = false;
            firstHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle5.png")));
            firstHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_secondHalfButtonMouseClicked

    private void secondHalfButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secondHalfButtonMouseEntered
        secondHalfOver = true;
        if(secondHalfClicked){
            secondHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle6_hoverclicked.png")));
            secondHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        else{
            secondHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle6_hover.png")));
            secondHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_secondHalfButtonMouseEntered

    private void secondHalfButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secondHalfButtonMouseExited
        secondHalfOver = false;
        if(secondHalfClicked){
            secondHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle6_clicked.png")));
            secondHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        else{
            secondHalfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle6.png")));
            secondHalfButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_secondHalfButtonMouseExited

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
            java.util.logging.Logger.getLogger(TeamResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeamResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeamResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeamResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeamResult().setVisible(true);
            }
        });
    }
    
    private App.WrappedLabel char1Detail;
    private App.WrappedLabel char2Detail;
    private App.WrappedLabel char3Detail;
    private App.WrappedLabel char4Detail;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel char1Label;
    private javax.swing.JLabel char1Label1;
    private javax.swing.JLabel char1Name;
    private javax.swing.JLabel char2Label;
    private javax.swing.JLabel char2Label1;
    private javax.swing.JLabel char2Name;
    private javax.swing.JLabel char3Label;
    private javax.swing.JLabel char3Label1;
    private javax.swing.JLabel char3Name;
    private javax.swing.JLabel char4Label;
    private javax.swing.JLabel char4Label1;
    private javax.swing.JLabel char4Name;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel exitButton1;
    private javax.swing.JLabel firstHalfButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel profileButton;
    private javax.swing.JLabel profileButton1;
    private javax.swing.JLabel rectangle1;
    private javax.swing.JPanel resultPage1;
    private javax.swing.JPanel resultPage2;
    private javax.swing.JLabel secondHalfButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel usernameLabel1;
    // End of variables declaration//GEN-END:variables
}