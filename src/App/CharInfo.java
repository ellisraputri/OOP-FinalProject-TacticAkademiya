/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author asus
 */
public class CharInfo extends javax.swing.JFrame {
    private GameCharacter gamechar;
    private int userId;
    private String username;
    private String email;
    private GameCharacterDetail charDetails;
    private boolean cnPlaying=false;
    private MP3Player cnVoicePlayer;
    private boolean jpPlaying=false;
    private MP3Player jpVoicePlayer;
    private ImageIcon profileImage;
    private MP3Player bgmPlayer;
    private MP3Player prevbgmPlayer;
    
    /**
     * Creates new form charInfo
     */
    public CharInfo() {
        initComponents();
    }
    
    public CharInfo(GameCharacter gamechar, int userId, String username, String email, ImageIcon profileImage, MP3Player prevbgmPlayer) {
        this.gamechar = gamechar;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profileImage = profileImage;
        this.prevbgmPlayer = prevbgmPlayer;
        initComponents();
        setTitle("Character "+gamechar.getName() + " Details");
        bg.setIcon(new ImageIcon("src/App/image/bg_"+gamechar.getElement() +".png"));
        splashArtLabel.setIcon(new ImageIcon("src/App/image/CharacterCard/SplashArt/"+gamechar.getName()+".png"));
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
        
        //setting profile picture
        profileButton.setIcon(profileImage);
        
        //set bgm
        loadBgm();
        
        //setting name and element
        nameLabel.setText(gamechar.getName());
        int xElementLabel = 990 - nameLabel.getPreferredSize().width / 2 - 57;
        nameLabel.setBounds(nameLabel.getX(), nameLabel.getY(), 520, nameLabel.getPreferredSize().height);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        elementLabel.setIcon(new ImageIcon("src/App/image/Element/Medium/" +gamechar.getElement()+ ".png"));
        elementLabel.setBounds(xElementLabel, elementLabel.getY()-2, elementLabel.getPreferredSize().width, elementLabel.getPreferredSize().height);
        
        //setting scroll pane
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setViewportBorder(null);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        //setting parentPanel
        parentPanel.setLayout(null); // Use absolute layout
        parentPanel.setPreferredSize(new Dimension(550, 480)); // Set initial size
        parentPanel.setOpaque(false);
        parentPanel.setBackground(new Color(0,0,0,0));
        parentPanel.setBorder(null);
        scrollPane.setViewportView(parentPanel); // Set this panel as viewport's view
        
        if(!(gamechar.getName().contains("Traveler"))){
            lumineButton.setVisible(false);
            aetherButton.setVisible(false);
        }
        
        charDetails = new App.GameCharacterDetail(gamechar);
        setBasicInfo();
        setWeapons();
        setArtifacts();
        setArtifactsStats();
        setTeams();
        setNamecard();
        setVoicebox();
        
        
        
        //repaint components
        parentPanel.revalidate();
        parentPanel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    private void loadBgm() {
        String folderPath = "src/App/audio/bgm";
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        File selectedFile = null;

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if(file.isFile() && file.getAbsolutePath().contains(gamechar.getName())){
                    selectedFile = file;
                    break;
                }
            }
        }
        
        bgmPlayer = new MP3Player();
        bgmPlayer.addToPlayList(selectedFile);
        bgmPlayer.play();
        bgmPlayer.setRepeat(true);
    }
    
    private void setBasicInfo(){
        //set rarity
        String star = (gamechar.getStars() == 4)? "four" : "five";
        rarityPic.setIcon(new ImageIcon("src/App/image/" + star+ "star.png"));
        
        //set weapon
        weaponPic.setIcon(new ImageIcon("src/App/image/Weapon/Small/" +gamechar.getWeapon()+ ".png"));
        weaponTypeLabel1.setText(gamechar.getWeapon());
        weaponTypeLabel1.setBounds(weaponTypeLabel1.getX(), weaponTypeLabel1.getY(), weaponTypeLabel1.getPreferredSize().width, weaponTypeLabel1.getPreferredSize().height);
        
        //set constellation
        consLabel.setText(charDetails.getConstellation());
        consLabel.setBounds(consLabel.getX(), consLabel.getY(), consLabel.getPreferredSize().width, consLabel.getPreferredSize().height);
        
        //set affiliation
        App.WrappedLabel affiliationLabel1 = new App.WrappedLabel(400, null, new Insets(2,2,2,2));
        affiliationLabel1.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        affiliationLabel1.setForeground(new java.awt.Color(67, 67, 71));
        affiliationLabel1.setText(charDetails.getAffiliation());
        affiliationLabel1.setBounds(affiliationLabel.getX()+affiliationLabel.getPreferredSize().width+5, affiliationLabel.getY(), affiliationLabel1.getPreferredSize().width+4, affiliationLabel1.getPreferredSize().height);    
        parentPanel.add(affiliationLabel1);

        //set birthday
        birthdayLabel.setText(charDetails.getBirthday());
        birthdayLabel.setBounds(birthdayLabel.getX(), affiliationLabel1.getPreferredSize().height + affiliationLabel1.getY()+15, birthdayLabel.getPreferredSize().width, birthdayLabel.getPreferredSize().height);
        
        //set cn voice
        cnVoiceLabel.setText("CN:"+charDetails.getCnVoice());
        
        if(gamechar.getName().equals("Alhaitham")){
            jpVoiceLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 13));
        }
        jpVoiceLabel.setText("JP:"+charDetails.getJpVoice());
        
        
        if(gamechar.getName().contains("Traveler")){
            setVoiceTraveler();
        }
        else{
            cnVoiceLabel1.setVisible(false);
            jpVoiceLabel1.setVisible(false);

            if(gamechar.getName().equals("Fischl") || gamechar.getName().equals("Yun Jin")){
                cnVoicebox.setBounds(cnVoicebox.getX(), cnVoicebox.getY(), cnVoicebox.getWidth(), 100);
                jpVoicebox.setBounds(jpVoicebox.getX(), jpVoicebox.getY(), jpVoicebox.getWidth(), 100);
                cnVoiceLabel1.setVisible(true);
                jpVoiceLabel1.setVisible(true);

                if(gamechar.getName().equals("Fischl")){
                    cnVoiceLabel.setText("CN:"+charDetails.getCnVoice().substring(0, 5));
                    cnVoiceLabel1.setText(charDetails.getCnVoice().substring(8));
                    jpVoiceLabel.setText("JP:" + charDetails.getJpVoice().substring(0, 20));
                    jpVoiceLabel1.setText(charDetails.getJpVoice().substring(22));
                }

                else{
                    cnVoiceLabel.setText("CN:"+charDetails.getCnVoice().substring(0, 17));
                    cnVoiceLabel1.setText("Opera:"+charDetails.getCnVoice().substring(18));
                    jpVoiceLabel.setText("JP:" + charDetails.getJpVoice().substring(0, 23));
                    jpVoiceLabel1.setText("Opera:"+charDetails.getJpVoice().substring(24));
                }

            }
        }
        
        
        
        
        Dimension newSize = new Dimension(parentPanel.getWidth(), birthdayLabel.getY()+birthdayLabel.getPreferredSize().height+30); // Adjusted size
        parentPanel.setPreferredSize(newSize);
    }
    
    
    private void setWeapons(){
        ArrayList<ImageIcon> weaponImages = charDetails.getWeaponImages();
        ArrayList<String> weaponNames = charDetails.getWeapons();
        System.out.println(weaponNames);
        weaponTitle.setBounds(weaponTitle.getX(), birthdayLabel.getY()+birthdayLabel.getPreferredSize().height+30, weaponTitle.getWidth(), weaponTitle.getHeight());
        int y=weaponTitle.getY()+60;
        for(int i=0; i<weaponImages.size(); i++){
            int x = (i%2==0)? 0 : 260;
            if(i>0){
              y = (i%2==0)? y+90 : y;  
            }
            JLabel weaponImageLabel = new JLabel();
            weaponImageLabel.setIcon(weaponImages.get(i));
            weaponImageLabel.setBounds(x, y, 70, 70);
            parentPanel.add(weaponImageLabel);
            
            App.WrappedLabelVerticalCenter weaponNameLabel = new App.WrappedLabelVerticalCenter(160, null, new Insets(2,10,2,2));
            weaponNameLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
            weaponNameLabel.setForeground(new java.awt.Color(67, 67, 71));
            weaponNameLabel.setText(weaponNames.get(i));
            weaponNameLabel.setVerticalAlignment(SwingConstants.CENTER);
            weaponNameLabel.setBounds(x+70, y, weaponNameLabel.getPreferredSize().width+4, 70);    
            parentPanel.add(weaponNameLabel);
            
        }
        Dimension newSize = new Dimension(parentPanel.getWidth(), y + 90); // Adjusted size
        parentPanel.setPreferredSize(newSize);
    }
    
    private void setArtifacts(){
        ArrayList<ImageIcon> artifactImages = charDetails.getArtifactImages();
        ArrayList<String> artifactNames = charDetails.getArtifacts();
        System.out.println(artifactNames);
        
        JLabel title = new JLabel();
        title.setFont(new java.awt.Font("HYWenHei-85W", 0, 28)); // NOI18N
        title.setForeground(Color.black);
        title.setText("Best Artifacts");
        title.setBounds(10, parentPanel.getPreferredSize().height+15, title.getPreferredSize().width+4, 60);    
        parentPanel.add(title);
        
        int y=title.getY() + title.getHeight();
        
        for(int i=0; i<artifactImages.size(); i++){
            int x = (i%2==0)? 0 : 260;
            if(i>0){
              y = (i%2==0)? y+90 : y;  
            }
            
            JLabel artifactImageLabel = new JLabel();
            artifactImageLabel.setIcon(artifactImages.get(i));
            artifactImageLabel.setBounds(x, y, 70, 70);
            parentPanel.add(artifactImageLabel);
            
            App.WrappedLabelVerticalCenter artifactNameLabel = new App.WrappedLabelVerticalCenter(160, null, new Insets(2,2,2,2));
            artifactNameLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
            artifactNameLabel.setForeground(new java.awt.Color(67, 67, 71));
            artifactNameLabel.setText(artifactNames.get(i));
            artifactNameLabel.setBounds(x+70, y, artifactNameLabel.getPreferredSize().width+4, 70);
            parentPanel.add(artifactNameLabel);
            
            
        }
        Dimension newSize = new Dimension(parentPanel.getWidth(), y + 90); // Adjusted size
        parentPanel.setPreferredSize(newSize);
    }
    
    private void setArtifactsStats(){
        JLabel titleStats = new JLabel();
        titleStats.setFont(new java.awt.Font("HYWenHei-85W", 0, 28)); // NOI18N
        titleStats.setForeground(Color.black);
        titleStats.setText("Best Artifacts Stats");
        titleStats.setBounds(10, parentPanel.getPreferredSize().height+15, titleStats.getPreferredSize().width+4, 60);    
        parentPanel.add(titleStats);
        
        JLabel sandsImage = new JLabel();
        sandsImage.setIcon(new ImageIcon("src/App/image/sands.png"));
        sandsImage.setBounds(13, titleStats.getY()+titleStats.getHeight(), sandsImage.getPreferredSize().width, sandsImage.getPreferredSize().height);
        parentPanel.add(sandsImage);
        
        JLabel sandsLabel = new JLabel();
        sandsLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        sandsLabel.setForeground(new java.awt.Color(67, 67, 71));
        sandsLabel.setText(charDetails.getArtifactSands());
        sandsLabel.setBounds(sandsImage.getX()+sandsImage.getPreferredSize().width+10, sandsImage.getY(), sandsLabel.getPreferredSize().width+10, 36);
        sandsLabel.setVerticalAlignment(SwingConstants.CENTER);
        parentPanel.add(sandsLabel);
        
        JLabel gobletImage = new JLabel();
        gobletImage.setIcon(new ImageIcon("src/App/image/goblet.png"));
        gobletImage.setBounds(20, sandsImage.getY()+60, gobletImage.getPreferredSize().width, gobletImage.getPreferredSize().height);
        parentPanel.add(gobletImage);
        
        JLabel gobletLabel = new JLabel();
        gobletLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        gobletLabel.setForeground(new java.awt.Color(67, 67, 71));
        gobletLabel.setText(charDetails.getArtifactGoblet());
        gobletLabel.setBounds(gobletImage.getX()+gobletImage.getPreferredSize().width+15, gobletImage.getY(), gobletLabel.getPreferredSize().width+10, 36);
        gobletLabel.setVerticalAlignment(SwingConstants.CENTER);
        parentPanel.add(gobletLabel);
        
        JLabel circletImage = new JLabel();
        circletImage.setIcon(new ImageIcon("src/App/image/circlet.png"));
        circletImage.setBounds(13, gobletImage.getY()+60, circletImage.getPreferredSize().width, circletImage.getPreferredSize().height);
        parentPanel.add(circletImage);
        
        JLabel circletLabel = new JLabel();
        circletLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        circletLabel.setForeground(new java.awt.Color(67, 67, 71));
        circletLabel.setText(charDetails.getArtifactCirclet());
        circletLabel.setBounds(circletImage.getX()+circletImage.getPreferredSize().width+10, circletImage.getY(), circletLabel.getPreferredSize().width+10, 36);
        circletLabel.setVerticalAlignment(SwingConstants.CENTER);
        parentPanel.add(circletLabel);
        
        JLabel substatTitle = new JLabel();
        substatTitle.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        substatTitle.setForeground(new java.awt.Color(67, 67, 71));
        substatTitle.setText("Substats: ");
        substatTitle.setBounds(18, circletImage.getY()+50, substatTitle.getPreferredSize().width+5, substatTitle.getPreferredSize().height);
        substatTitle.setVerticalAlignment(SwingConstants.CENTER);
        parentPanel.add(substatTitle);
        
        App.WrappedLabel substatLabel = new App.WrappedLabel(390, null, new Insets(2,2,2,2));
        substatLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        substatLabel.setForeground(new java.awt.Color(67, 67, 71));
        substatLabel.setText(charDetails.getArtifactSubstats());
        substatLabel.setBounds(substatTitle.getX()+substatTitle.getPreferredSize().width+2, substatTitle.getY()-2, substatLabel.getPreferredSize().width+10, substatLabel.getPreferredSize().height);
        substatLabel.setVerticalAlignment(SwingConstants.CENTER);
        parentPanel.add(substatLabel);
        
        Dimension newSize = new Dimension(parentPanel.getWidth(), substatLabel.getY()+ 90); // Adjusted size
        parentPanel.setPreferredSize(newSize);
    }
    
    private void setTeams(){
        ArrayList<String> teams = charDetails.getTeams();
        System.out.println(teams);
        
        JLabel titleTeams = new JLabel();
        titleTeams.setFont(new java.awt.Font("HYWenHei-85W", 0, 28)); // NOI18N
        titleTeams.setForeground(Color.black);
        titleTeams.setText("Team Comps");
        titleTeams.setBounds(10, parentPanel.getPreferredSize().height-5, titleTeams.getPreferredSize().width+4, 60);    
        parentPanel.add(titleTeams);
        
        int row=0, column=0, lastY=titleTeams.getY();
        for(int i=0; i<teams.size();i++){
            ImageIcon image = new ImageIcon("src/App/image/CharacterCard/Archive/Portraits "+teams.get(i)+".png");
            String name = teams.get(i);
            
            int panelWidth = 100;
            int panelHeight = 100;
            
            CharacterPanelNonClick clonedPanel = new CharacterPanelNonClick(name);
            clonedPanel.settingMouse();
            clonedPanel.settingPanel(image, name, panelWidth, panelHeight,11,false);

            // Calculate the row and column indices
            row = i / 4;
            column = i % 4;

            // Calculate the x and y positions based on row and column indices
            int x = 10 + column * (panelWidth + 30);
            int y = 10 + row * (panelHeight + 40) + titleTeams.getPreferredSize().height+20 + titleTeams.getY();

            // Set the bounds for the cloned panel with your custom size
            clonedPanel.setBounds(x, y, panelWidth, panelHeight);
            
            // Add the cloned panel to the initial panel
            parentPanel.add(clonedPanel);
            lastY = y;
        }
        // Adjust preferred size of initial panel to include new panel
        Dimension newSize = new Dimension(parentPanel.getWidth(), lastY+150); // Adjusted size
        parentPanel.setPreferredSize(newSize);
        
    }
    
    
    private void setNamecard(){
        JLabel titleNamecard = new JLabel();
        titleNamecard.setFont(new java.awt.Font("HYWenHei-85W", 0, 28)); // NOI18N
        titleNamecard.setForeground(Color.black);
        titleNamecard.setText("Namecard");
        titleNamecard.setBounds(10, parentPanel.getPreferredSize().height-5, titleNamecard.getPreferredSize().width+4, 60);    
        parentPanel.add(titleNamecard);
        
        JLabel cardImage = new JLabel();
        if(gamechar.getName().contains("Traveler")){
            cardImage.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
            cardImage.setForeground(new java.awt.Color(67, 67, 71));
            cardImage.setText("-");
        }
        else{
            cardImage.setIcon(charDetails.getNamecard());
        }
        cardImage.setBounds(13, titleNamecard.getY()+60, cardImage.getPreferredSize().width+10, cardImage.getPreferredSize().height);
        parentPanel.add(cardImage);
        
        Dimension newSize = new Dimension(parentPanel.getWidth(), cardImage.getY()+cardImage.getHeight()+40); // Adjusted size
        parentPanel.setPreferredSize(newSize);
    }
    
    private void setVoicebox(){
        if(gamechar.getName().contains("Traveler")){
            setVoiceTraveler();
        }
        else{
            cnVoicePlayer = new MP3Player();
            cnVoicePlayer.addToPlayList(new File("src/App/audio/CN/"+gamechar.getName()+".mp3"));
            cnVoicePlayer.addToPlayList(new File("src/App/audio/silence.mp3"));
            cnVoicePlayer.setRepeat(true);

            jpVoicePlayer = new MP3Player();
            jpVoicePlayer.addToPlayList(new File("src/App/audio/JP/"+gamechar.getName()+".mp3"));
            jpVoicePlayer.addToPlayList(new File("src/App/audio/silence.mp3"));
            jpVoicePlayer.setRepeat(true); 
        }
    }
    
    private void setVoiceTraveler(){
        if(!(lumineButton.isClicked()) && !(aetherButton.isClicked())){
            cnVoiceLabel1.setVisible(true);
            jpVoiceLabel1.setVisible(true);
            cnVoicebox.setBounds(cnVoicebox.getX(), cnVoicebox.getY(), cnVoicebox.getWidth(), 100);
            jpVoicebox.setBounds(jpVoicebox.getX(), jpVoicebox.getY(), jpVoicebox.getWidth(), 100);
            cnVoiceLabel.setText("CN Female: 宴宁 / Yan Ning");
            cnVoiceLabel1.setText("CN Male: 鹿喑 / Lu Yin");
            jpVoiceLabel.setText("JP Female: 悠木碧 / Yuki Aoi");
            jpVoiceLabel1.setText("JP Male: 堀江瞬 / Horie Shun");
            
            splashArtLabel.setIcon(new ImageIcon("src/App/image/CharacterCard/SplashArt/"+gamechar.getName()+".png"));
            cnVoicePlayer = new MP3Player();
            cnVoicePlayer.addToPlayList(new File("src/App/audio/silence.mp3"));
            cnVoicePlayer.setRepeat(true);

            jpVoicePlayer = new MP3Player();
            jpVoicePlayer.addToPlayList(new File("src/App/audio/silence.mp3"));
            jpVoicePlayer.setRepeat(true); 
        }
        else if (lumineButton.isClicked()){
            cnVoiceLabel.setText("CN: 宴宁 / Yan Ning");
            cnVoiceLabel1.setVisible(false);
            jpVoiceLabel.setText("JP: 悠木碧 / Yuki Aoi");
            jpVoiceLabel1.setVisible(false);
            
            cnVoicePlayer = new MP3Player();
            cnVoicePlayer.addToPlayList(new File("src/App/audio/CN/Female Traveler.mp3"));
            cnVoicePlayer.addToPlayList(new File("src/App/audio/silence.mp3"));
            cnVoicePlayer.setRepeat(true);

            jpVoicePlayer = new MP3Player();
            jpVoicePlayer.addToPlayList(new File("src/App/audio/JP/Female Traveler.mp3"));
            jpVoicePlayer.addToPlayList(new File("src/App/audio/silence.mp3"));
            jpVoicePlayer.setRepeat(true); 
            
            splashArtLabel.setIcon(new ImageIcon("src/App/image/CharacterCard/SplashArt/Lumine.png"));
            cnVoicebox.setBounds(cnVoicebox.getX(), cnVoicebox.getY(), cnVoicebox.getWidth(), 80);
            jpVoicebox.setBounds(jpVoicebox.getX(), jpVoicebox.getY(), jpVoicebox.getWidth(), 80);
        }
        else if(aetherButton.isClicked()){
            cnVoiceLabel.setText("CN: 鹿喑 / Lu Yin");
            cnVoiceLabel1.setVisible(false);
            jpVoiceLabel.setText("JP: 堀江瞬 / Horie Shun");
            jpVoiceLabel1.setVisible(false);
            
            cnVoicePlayer = new MP3Player();
            cnVoicePlayer.addToPlayList(new File("src/App/audio/CN/Male Traveler.mp3"));
            cnVoicePlayer.addToPlayList(new File("src/App/audio/silence.mp3"));
            cnVoicePlayer.setRepeat(true);

            jpVoicePlayer = new MP3Player();
            jpVoicePlayer.addToPlayList(new File("src/App/audio/JP/Male Traveler.mp3"));
            jpVoicePlayer.addToPlayList(new File("src/App/audio/silence.mp3"));
            jpVoicePlayer.setRepeat(true); 
            
            splashArtLabel.setIcon(new ImageIcon("src/App/image/CharacterCard/SplashArt/Aether.png"));
            cnVoicebox.setBounds(cnVoicebox.getX(), cnVoicebox.getY(), cnVoicebox.getWidth(), 80);
            jpVoicebox.setBounds(jpVoicebox.getX(), jpVoicebox.getY(), jpVoicebox.getWidth(), 80);
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
        scrollPane = new javax.swing.JScrollPane();
        parentPanel = new javax.swing.JPanel();
        birthdayLabel = new javax.swing.JLabel();
        weaponTitle = new javax.swing.JLabel();
        rarityLabel = new javax.swing.JLabel();
        weaponTypeLabel1 = new javax.swing.JLabel();
        weaponTypeLabel = new javax.swing.JLabel();
        consLabel = new javax.swing.JLabel();
        rarityPic = new javax.swing.JLabel();
        basicInfoTitle = new javax.swing.JLabel();
        affiliationLabel = new javax.swing.JLabel();
        weaponPic = new javax.swing.JLabel();
        panelbg = new javax.swing.JLabel();
        lumineButton = new App.ButtonCustom();
        aetherButton = new App.ButtonCustom();
        splashArtLabel = new javax.swing.JLabel();
        elementLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jpVoiceButton = new javax.swing.JLabel();
        cnVoiceButton = new javax.swing.JLabel();
        jpVoiceLabel1 = new javax.swing.JLabel();
        jpVoiceLabel = new javax.swing.JLabel();
        cnVoiceLabel1 = new javax.swing.JLabel();
        cnVoiceLabel = new javax.swing.JLabel();
        jpVoicebox = new javax.swing.JLabel();
        cnVoicebox = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1290, 750));
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
        exitButton.setBounds(1190, 20, 70, 70);

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        parentPanel.setBackground(new java.awt.Color(255, 204, 204));
        parentPanel.setLayout(null);

        birthdayLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        birthdayLabel.setForeground(new java.awt.Color(67, 67, 71));
        birthdayLabel.setText("Birthday:");
        parentPanel.add(birthdayLabel);
        birthdayLabel.setBounds(10, 220, 90, 30);

        weaponTitle.setFont(new java.awt.Font("HYWenHei-85W", 0, 28)); // NOI18N
        weaponTitle.setText("Best Weapons");
        parentPanel.add(weaponTitle);
        weaponTitle.setBounds(10, 270, 270, 60);

        rarityLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        rarityLabel.setForeground(new java.awt.Color(67, 67, 71));
        rarityLabel.setText("Rarity:");
        parentPanel.add(rarityLabel);
        rarityLabel.setBounds(10, 60, 80, 30);

        weaponTypeLabel1.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        weaponTypeLabel1.setForeground(new java.awt.Color(67, 67, 71));
        weaponTypeLabel1.setText("Weapon:");
        parentPanel.add(weaponTypeLabel1);
        weaponTypeLabel1.setBounds(130, 103, 90, 30);

        weaponTypeLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        weaponTypeLabel.setForeground(new java.awt.Color(67, 67, 71));
        weaponTypeLabel.setText("Weapon:");
        parentPanel.add(weaponTypeLabel);
        weaponTypeLabel.setBounds(10, 100, 90, 30);

        consLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        consLabel.setForeground(new java.awt.Color(67, 67, 71));
        consLabel.setText("Constellation:");
        parentPanel.add(consLabel);
        consLabel.setBounds(10, 140, 140, 30);

        rarityPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/fivestar.png"))); // NOI18N
        parentPanel.add(rarityPic);
        rarityPic.setBounds(80, 63, 118, 22);

        basicInfoTitle.setFont(new java.awt.Font("HYWenHei-85W", 0, 28)); // NOI18N
        basicInfoTitle.setText("Basic Information");
        parentPanel.add(basicInfoTitle);
        basicInfoTitle.setBounds(10, 0, 270, 60);

        affiliationLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        affiliationLabel.setForeground(new java.awt.Color(67, 67, 71));
        affiliationLabel.setText("Affiliation:");
        parentPanel.add(affiliationLabel);
        affiliationLabel.setBounds(10, 180, 110, 30);

        weaponPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Weapon/Small/Bow.png"))); // NOI18N
        parentPanel.add(weaponPic);
        weaponPic.setBounds(95, 100, 30, 30);

        scrollPane.setViewportView(parentPanel);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(110, 150, 540, 480);

        panelbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle10.png"))); // NOI18N
        panelbg.setToolTipText("");
        getContentPane().add(panelbg);
        panelbg.setBounds(73, 111, 620, 570);

        lumineButton.setBorder(null);
        lumineButton.setForeground(new java.awt.Color(255, 255, 255));
        lumineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/lumine_logo.png"))); // NOI18N
        lumineButton.setText(" Lumine");
        lumineButton.setToolTipText("");
        lumineButton.setBorderColor(new java.awt.Color(204, 236, 252));
        lumineButton.setBorderColorNotOver(new java.awt.Color(204, 236, 252));
        lumineButton.setBorderColorOver(new java.awt.Color(204, 236, 252));
        lumineButton.setColor(new java.awt.Color(66, 167, 217));
        lumineButton.setColor2(java.awt.Color.white);
        lumineButton.setColorClick(new java.awt.Color(30, 127, 175));
        lumineButton.setColorClick2(java.awt.Color.white);
        lumineButton.setColorOver(new java.awt.Color(30, 127, 175));
        lumineButton.setColorOver2(java.awt.Color.white);
        lumineButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        lumineButton.setRadius(20);
        lumineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lumineButtonActionPerformed(evt);
            }
        });
        getContentPane().add(lumineButton);
        lumineButton.setBounds(810, 110, 150, 40);

        aetherButton.setBackground(new java.awt.Color(192, 134, 50));
        aetherButton.setBorder(null);
        aetherButton.setForeground(new java.awt.Color(255, 255, 255));
        aetherButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/aether_logo.png"))); // NOI18N
        aetherButton.setText(" Aether");
        aetherButton.setToolTipText("");
        aetherButton.setBorderColor(new java.awt.Color(252, 222, 179));
        aetherButton.setBorderColorNotOver(new java.awt.Color(252, 222, 179));
        aetherButton.setBorderColorOver(new java.awt.Color(252, 222, 179));
        aetherButton.setColor(new java.awt.Color(192, 134, 50));
        aetherButton.setColor2(java.awt.Color.white);
        aetherButton.setColorClick(new java.awt.Color(134, 94, 38));
        aetherButton.setColorClick2(java.awt.Color.white);
        aetherButton.setColorOver(new java.awt.Color(134, 94, 38));
        aetherButton.setColorOver2(java.awt.Color.white);
        aetherButton.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        aetherButton.setRadius(20);
        aetherButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aetherButtonActionPerformed(evt);
            }
        });
        getContentPane().add(aetherButton);
        aetherButton.setBounds(1000, 110, 150, 40);

        splashArtLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        splashArtLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/CharacterCard/SplashArt/Anemo Traveler.png"))); // NOI18N
        splashArtLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(splashArtLabel);
        splashArtLabel.setBounds(680, 100, 590, 415);

        elementLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Element/Medium/Anemo.png"))); // NOI18N
        getContentPane().add(elementLabel);
        elementLabel.setBounds(910, 520, 44, 40);

        nameLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(252, 236, 214));
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("Test");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(720, 520, 520, 44);

        jpVoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/play.png"))); // NOI18N
        jpVoiceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpVoiceButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpVoiceButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpVoiceButtonMouseExited(evt);
            }
        });
        getContentPane().add(jpVoiceButton);
        jpVoiceButton.setBounds(1100, 590, 30, 30);

        cnVoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/play.png"))); // NOI18N
        cnVoiceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cnVoiceButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cnVoiceButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cnVoiceButtonMouseExited(evt);
            }
        });
        getContentPane().add(cnVoiceButton);
        cnVoiceButton.setBounds(820, 590, 30, 30);

        jpVoiceLabel1.setFont(new java.awt.Font("HYWenHei-85W", 0, 14)); // NOI18N
        jpVoiceLabel1.setForeground(new java.awt.Color(252, 236, 214));
        jpVoiceLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpVoiceLabel1.setText("JP:");
        getContentPane().add(jpVoiceLabel1);
        jpVoiceLabel1.setBounds(990, 655, 250, 18);

        jpVoiceLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 14)); // NOI18N
        jpVoiceLabel.setForeground(new java.awt.Color(252, 236, 214));
        jpVoiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jpVoiceLabel.setText("JP:");
        getContentPane().add(jpVoiceLabel);
        jpVoiceLabel.setBounds(990, 630, 250, 18);

        cnVoiceLabel1.setFont(new java.awt.Font("HYWenHei-85W", 0, 14)); // NOI18N
        cnVoiceLabel1.setForeground(new java.awt.Color(252, 236, 214));
        cnVoiceLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cnVoiceLabel1.setText("CN:");
        getContentPane().add(cnVoiceLabel1);
        cnVoiceLabel1.setBounds(730, 655, 220, 18);

        cnVoiceLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 14)); // NOI18N
        cnVoiceLabel.setForeground(new java.awt.Color(252, 236, 214));
        cnVoiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cnVoiceLabel.setText("CN:");
        getContentPane().add(cnVoiceLabel);
        cnVoiceLabel.setBounds(730, 630, 220, 18);

        jpVoicebox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 236, 214), 3));
        getContentPane().add(jpVoicebox);
        jpVoicebox.setBounds(980, 580, 270, 80);

        cnVoicebox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cnVoicebox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(252, 236, 214), 3));
        getContentPane().add(cnVoicebox);
        cnVoicebox.setBounds(720, 580, 240, 80);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_Anemo.png"))); // NOI18N
        bg.setToolTipText("");
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileButtonMouseClicked
        setVisible(false);
        dispose();
        bgmPlayer.stop();
        prevbgmPlayer.play();
        new Settings(userId, prevbgmPlayer).setVisible(true);
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
        bgmPlayer.stop();
        new CharInfoHome(userId, username, email, profileImage, prevbgmPlayer, false).setVisible(true);
    }//GEN-LAST:event_exitButtonMouseClicked

    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseEntered
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit2.png")));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_exitButtonMouseEntered

    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/exit1.png")));
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_exitButtonMouseExited

    private void cnVoiceButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cnVoiceButtonMouseEntered
        cnVoiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cnVoiceButtonMouseEntered

    private void cnVoiceButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cnVoiceButtonMouseExited
        cnVoiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_cnVoiceButtonMouseExited
    
    
    private void cnVoiceButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cnVoiceButtonMouseClicked
        cnPlaying = !cnPlaying;
        if(cnPlaying){
            cnVoicePlayer.play();
            cnVoiceButton.setIcon(new ImageIcon("src/App/image/pause.png"));
        }
        else{
            cnVoicePlayer.pause();
            cnVoiceButton.setIcon(new ImageIcon("src/App/image/play.png"));
        }
    }//GEN-LAST:event_cnVoiceButtonMouseClicked

    private void jpVoiceButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpVoiceButtonMouseEntered
        jpVoiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jpVoiceButtonMouseEntered

    private void jpVoiceButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpVoiceButtonMouseExited
        jpVoiceButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jpVoiceButtonMouseExited

    private void jpVoiceButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpVoiceButtonMouseClicked
        jpPlaying = !jpPlaying;
        if(jpPlaying){
            jpVoicePlayer.play();
            jpVoiceButton.setIcon(new ImageIcon("src/App/image/pause.png"));
        }
        else{
            jpVoicePlayer.pause();
            jpVoiceButton.setIcon(new ImageIcon("src/App/image/play.png"));
        }
    }//GEN-LAST:event_jpVoiceButtonMouseClicked

    private void lumineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lumineButtonActionPerformed
        boolean click = lumineButton.isClicked();
        click = !click;
        lumineButton.setClicked(click);
        
        if(click){
            aetherButton.setClicked(false);
            lumineButton.setBackground(lumineButton.getColorClick());
            lumineButton.setForeground(lumineButton.getColorClick2());
            lumineButton.setBorderColor(lumineButton.getBorderColorOver());
            aetherButton.setBackground(aetherButton.getColor());
            aetherButton.setForeground(aetherButton.getColor2());
            aetherButton.setBorderColor(aetherButton.getBorderColorNotOver());
        }
        setVoiceTraveler();
    }//GEN-LAST:event_lumineButtonActionPerformed

    private void aetherButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aetherButtonActionPerformed
        boolean click = aetherButton.isClicked();
        click = !click;
        aetherButton.setClicked(click);
        
        if(click){
            lumineButton.setClicked(false); 
            aetherButton.setBackground(aetherButton.getColorClick());
            aetherButton.setForeground(aetherButton.getColorClick2());
            aetherButton.setBorderColor(aetherButton.getBorderColorOver());
            lumineButton.setBackground(lumineButton.getColor());
            lumineButton.setForeground(lumineButton.getColor2());
            lumineButton.setBorderColor(lumineButton.getBorderColorNotOver());
        }
        setVoiceTraveler();
    }//GEN-LAST:event_aetherButtonActionPerformed

     
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
            java.util.logging.Logger.getLogger(CharInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CharInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CharInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CharInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CharInfo().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private App.ButtonCustom aetherButton;
    private javax.swing.JLabel affiliationLabel;
    private javax.swing.JLabel basicInfoTitle;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel birthdayLabel;
    private javax.swing.JLabel cnVoiceButton;
    private javax.swing.JLabel cnVoiceLabel;
    private javax.swing.JLabel cnVoiceLabel1;
    private javax.swing.JLabel cnVoicebox;
    private javax.swing.JLabel consLabel;
    private javax.swing.JLabel elementLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel jpVoiceButton;
    private javax.swing.JLabel jpVoiceLabel;
    private javax.swing.JLabel jpVoiceLabel1;
    private javax.swing.JLabel jpVoicebox;
    private App.ButtonCustom lumineButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel panelbg;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel profileButton;
    private javax.swing.JLabel rarityLabel;
    private javax.swing.JLabel rarityPic;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel splashArtLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel weaponPic;
    private javax.swing.JLabel weaponTitle;
    private javax.swing.JLabel weaponTypeLabel;
    private javax.swing.JLabel weaponTypeLabel1;
    // End of variables declaration//GEN-END:variables
}
