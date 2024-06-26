package App;

import DatabaseConnection.ConnectionProvider;
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Settings extends javax.swing.JFrame {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String profilePath;
    private ArrayList<String> musicList = new ArrayList<>();
    private MP3Player bgmPlayer;
    public ArrayList<String> ownedChars = new ArrayList<>();
    
    
    public Settings() {
        initComponents();
    }
    
    public Settings(int userId, MP3Player bgmPlayer){
        initComponents();
        this.userId = userId;
        this.bgmPlayer = bgmPlayer;
        setLocationRelativeTo(null);    //set frame location
        myinit();
    }
    
    private void myinit(){
        //set cursor image
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/App/image/mouse.png").getImage(), new Point(0,0),"custom cursor"));        
        
        //setting scroll pane
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setViewportBorder(null);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        //setting parentPanel
        parentPanel.setLayout(null); // Use absolute layout
        parentPanel.setPreferredSize(new Dimension(560, 470)); // Set initial size
        parentPanel.setOpaque(false);
        parentPanel.setBackground(new Color(0,0,0,0));
        parentPanel.setBorder(null);
        scrollPane.setViewportView(parentPanel); // Set this panel as viewport's view
        
        //set image panel
        imagePanel = new JPanel();
        imagePanel.setLayout(null);
        imagePanel.setPreferredSize(new Dimension(400, 200)); 
        imagePanel.setOpaque(false);
        imagePanel.setBackground(new Color(0,0,0,0));
        
        retrieveFromDatabase();
        setTextField();
        setSomeCharImages(ownedChars);
        setProfilePicture();
        
        //repaint components
        parentPanel.revalidate();
        parentPanel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    
    private void retrieveFromDatabase(){
        ArrayList<Boolean> ownedOrNot = new ArrayList<>();
        try{
            //retrieve username, email,password, profile path from database
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select * from user where id='" + userId + "'");
            if(rs.first()){
                username = rs.getString(2);
                email = rs.getString(3);
                password = rs.getString(4);
                profilePath = rs.getString(5);
            }
            
            //retrieve user's owned characters 
            ResultSet rs1 = st.executeQuery("select * from characters where userId=" + userId + "");
            if(rs1.first()){
                for(int i=2; i<87; i++){
                    ownedOrNot.add(rs1.getBoolean(i));
                }
            }
            else{
               JOptionPane.showMessageDialog(getContentPane(), "No user ID found");
            }
        
            //retrieve the character name
            ResultSet rs2 = st.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='characters'");
            int index=0;
            while(rs2.next()){
                if(index==0){
                    index++;
                    continue;
                }
                else if(ownedOrNot.get(index-1) == true){
                    String name = rs2.getString(1);
                    name = name.replaceAll("([a-z])([A-Z])", "$1 $2");
                    ownedChars.add(name);
                }
                index++;
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(getContentPane(), e);
        }
    }
    
    
    //set up text fields
    private void setTextField(){
        //initialization
        usernameField = new App.RoundJTextField(30);
        emailField = new App.RoundJTextField(30);
        passwordField = new App.RoundJPasswordField(30);
        
        //set username field
        usernameField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        usernameField.setText(username);
        usernameField.setForeground(new java.awt.Color(130,130,130));
        parentPanel.add(usernameField);
        usernameField.setBounds(0, 50, 530, usernameField.getPreferredSize().height);
        parentPanel.setComponentZOrder(usernameField, 0);
        
        //set email field
        emailField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        emailField.setForeground(new java.awt.Color(130,130,130));
        emailField.setText(email);
        parentPanel.add(emailField);
        emailField.setBounds(0, 162, 530, emailField.getPreferredSize().height);
        parentPanel.setComponentZOrder(emailField, 0);
        
        //set password field
        passwordField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        passwordField.setForeground(new java.awt.Color(130,130,130));
        passwordField.setText(password);
        parentPanel.add(passwordField);
        passwordField.setBounds(0, 277, 530, passwordField.getPreferredSize().height);
        parentPanel.setComponentZOrder(passwordField, 0);
        parentPanel.setComponentZOrder(hidePassword, 0);
        parentPanel.setComponentZOrder(showPassword, 0);
        
        //add document listener, action listener, and focus listener
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSelfStatusUsername();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSelfStatusUsername();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSelfStatusUsername();
            }
        });
        usernameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "Enter" is pressed in textField1, move focus to textField2
                emailField.requestFocusInWindow();
            }
        });
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFieldFocusLost(evt);
            }
        });
        
        //add document listener, action listener, and focus listener
        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSelfStatusEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSelfStatusEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSelfStatusEmail();
            }
        });
        emailField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "Enter" is pressed in textField1, move focus to textField2
                passwordField.requestFocusInWindow();
            }
        });
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });
        
        //add document listener and focus listener
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSelfStatusPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSelfStatusPassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSelfStatusPassword();
            }
        });
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });
        
        //setup hidepassword
        hidePassword.setVisible(false);
        passwordField.setEchoChar('*');
    }
    
    //setup char image in image panel
    public void setSomeCharImages(ArrayList<String> characters){
        imagePanel.removeAll();
        int x=10;
        
        for(int i=0; i<4; i++){
            //the label for image
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon("src/App/image/CharacterCard/Archive/Portraits " +characters.get(i)+ ".png"));
            imageLabel.setBounds(x, 0, 100, 100);
            imagePanel.add(imageLabel);
            imagePanel.setComponentZOrder(imageLabel, 0);
            x+=120;
            imagePanel.revalidate();
            imagePanel.repaint();
        }
        
        //set new size to image panel
        imagePanel.setPreferredSize(new Dimension(imagePanel.getWidth(), 500));
        imagePanel.setBounds(10, myCharLabel.getY()+myCharLabel.getPreferredSize().height+15, 500, imagePanel.getPreferredSize().height);
        
        parentPanel.add(imagePanel);        
    }
    
    //set profile picture
    private void setProfilePicture(){
        //set title
        myProfile = new JLabel();
        myProfile.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        myProfile.setForeground(new Color(131,113,90));
        myProfile.setText("Profile Picture");
        myProfile.setBounds(0, 540, myProfile.getPreferredSize().width+4, 60);    
        parentPanel.add(myProfile);
        myProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                myProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                myProfileMouseExited(evt);
            }
        });
        parentPanel.setComponentZOrder(myProfile, 0);
        
        //set arrow
        myProfileArrow = new JLabel();
        myProfileArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/right_arrow.png"))); // NOI18N
        myProfileArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                myProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                myProfileMouseExited(evt);
            }
        });
        parentPanel.add(myProfileArrow);
        myProfileArrow.setBounds(myProfile.getPreferredSize().width+13, myProfile.getY()+20, 20, 20);
        parentPanel.setComponentZOrder(myProfileArrow,0);
        
        //set profile picture
        profilePic = new JLabel();
        if(profilePath == null){
           profilePath = "src/App/image/profile1.png";
           setProfileImage(profilePath);
        }
        else if(profilePath.contains("GenshinIcons")){
            BufferedImage im = imageIconToBufferedImage(new ImageIcon(profilePath));
            cropIntoCircle(im);
        }
        else{
            setProfileImage(profilePath);
        }
        parentPanel.add(profilePic);
        profilePic.setBounds(15, myProfileArrow.getY()+40, 110,110);
        
        //set new size for parent
        parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), profilePic.getY()+120));
        setMusic();
    }
    
    //crop image into circle
    public void cropIntoCircle(BufferedImage croppedImage){
        BufferedImage img = croppedImage;
        
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int circleDiameter = Math.min(width,height);
        Ellipse2D.Double circle = new Ellipse2D.Double(0,0,circleDiameter,circleDiameter);
        g2.setClip(circle);
        g2.drawImage(img,0,0,null);
        
        setProfileImage(bi);
    }
    
    //set profile image based on bufferedimage
    public void setProfileImage(BufferedImage im){
        BufferedImage resizedImage = resizeImage(im, 110, 110);
        profilePic.setIcon(new ImageIcon(resizedImage));
        profilePic.repaint();
        profilePic.revalidate();
        parentPanel.repaint();
        parentPanel.revalidate();
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
    //set profile image based on path
    public void setProfileImage(String path){
        BufferedImage im = imageIconToBufferedImage(new ImageIcon(path));
        BufferedImage resizedImage = resizeImage(im, 110, 110);
        profilePic.setIcon(new ImageIcon(resizedImage));
        profilePic.repaint();
        profilePic.revalidate();
        parentPanel.repaint();
        parentPanel.revalidate();
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
    //convert imageicon to bufferedimage
    public static BufferedImage imageIconToBufferedImage(ImageIcon icon) {
        Image img = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
    
    //resize image to target width and height
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }
    
    //setup music
    private void setMusic(){
        //set title
        myMusic = new JLabel();
        myMusic.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        myMusic.setForeground(new Color(131,113,90));
        myMusic.setText("Background Music");
        myMusic.setBounds(0, parentPanel.getPreferredSize().height+15, myMusic.getPreferredSize().width+4, 60);    
        parentPanel.add(myMusic);
        myMusic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myMusicMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                myMusicMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                myMusicMouseExited(evt);
            }
        });
        parentPanel.setComponentZOrder(myMusic, 0);
        
        //set arrow
        myMusicArrow = new JLabel();
        myMusicArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/right_arrow.png"))); // NOI18N
        myMusicArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myMusicMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                myMusicMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                myMusicMouseExited(evt);
            }
        });
        parentPanel.add(myMusicArrow);
        myMusicArrow.setBounds(myMusic.getPreferredSize().width+13, myMusic.getY()+20, 20, 20);
        parentPanel.setComponentZOrder(myMusicArrow,0);
        
        //set music panel
        musicPanel = new JPanel();
        musicPanel.setLayout(null);
        musicPanel.setPreferredSize(new Dimension(400, 200)); 
        musicPanel.setOpaque(false);
        musicPanel.setBackground(new Color(0,0,0,0));
        musicPanel.setBounds(0, myMusicArrow.getY()+myMusicArrow.getPreferredSize().height+25, musicPanel.getPreferredSize().width, musicPanel.getPreferredSize().height);
        parentPanel.add(musicPanel);
        parentPanel.setComponentZOrder(musicPanel, 0);
        
        setMusicList();
        
        //set new size for parentpanel
        parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), myMusicArrow.getY()+260));
        parentPanel.repaint();
        parentPanel.revalidate();
    }
    
    //set music list
    public void setMusicList(){
        musicPanel.removeAll();
        checkFromDatabase();
        
        //display label in the panel
        int y=0;
        for(int i=0; i<musicList.size(); i++){
            //label for each music title
            JLabel musicTitle = new JLabel();
            String[] filename = musicList.get(i).split(" - ");
            String name = filename[1].replaceAll(".mp3", "").trim();
            
            musicTitle.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
            musicTitle.setForeground(new Color(131,113,90));
            musicTitle.setText("- "+ name);
            musicTitle.setBounds(0, y, musicTitle.getPreferredSize().width+20, musicTitle.getPreferredSize().height);    
            musicPanel.add(musicTitle);
            musicPanel.setComponentZOrder(musicTitle,0);
            musicPanel.revalidate();
            musicPanel.repaint();
            
            y+=40;
        }
        
        //setup music panel and parentpanel after adding labels
        musicPanel.setPreferredSize(new Dimension(musicPanel.getWidth(), y+90));
        musicPanel.setBounds(10, myMusicArrow.getY()+myMusicArrow.getPreferredSize().height+25, musicPanel.getPreferredSize().width, musicPanel.getPreferredSize().height);
        parentPanel.add(musicPanel);
        parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), musicPanel.getY()+musicPanel.getHeight()+15));
        parentPanel.revalidate();
        parentPanel.repaint();
    }
    
    //check music from database
    private void checkFromDatabase(){
        musicList.clear();
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select * from user where id='" + userId + "'");
            if(rs.first()){
                musicList.add(rs.getString(6));
                musicList.add(rs.getString(7));
                musicList.add(rs.getString(8));
                musicList.add(rs.getString(9));
                musicList.add(rs.getString(10));
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(getContentPane(), e);
        }
    }
    
    //update the bgm player to play the latest updated music
    public void updateBgmPlayer(){
        bgmPlayer = new MP3Player();
        bgmPlayer.addToPlayList(new File("src/App/audio/bgm/"+musicList.get(0)));
        bgmPlayer.addToPlayList(new File("src/App/audio/bgm/"+musicList.get(1)));
        bgmPlayer.addToPlayList(new File("src/App/audio/bgm/"+musicList.get(2)));
        bgmPlayer.addToPlayList(new File("src/App/audio/bgm/"+musicList.get(3)));
        bgmPlayer.addToPlayList(new File("src/App/audio/bgm/"+musicList.get(4)));
        bgmPlayer.setRepeat(true);
        bgmPlayer.play();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saveLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        parentPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        myCharLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        hidePassword = new javax.swing.JLabel();
        showPassword = new javax.swing.JLabel();
        myCharArrow = new javax.swing.JLabel();
        settingText = new javax.swing.JLabel();
        settingBg = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Settings");
        setMinimumSize(new java.awt.Dimension(1290, 750));
        setResizable(false);
        getContentPane().setLayout(null);

        saveLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        saveLabel.setForeground(new java.awt.Color(255, 255, 255));
        saveLabel.setText("Save!");
        saveLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveLabelMouseExited(evt);
            }
        });
        getContentPane().add(saveLabel);
        saveLabel.setBounds(520, 640, 70, 30);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png"))); // NOI18N
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButtonMouseExited(evt);
            }
        });
        getContentPane().add(saveButton);
        saveButton.setBounds(430, 630, 247, 46);

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);

        parentPanel.setOpaque(false);
        parentPanel.setLayout(null);

        usernameLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(131, 113, 90));
        usernameLabel.setText("Username");
        parentPanel.add(usernameLabel);
        usernameLabel.setBounds(0, 10, 124, 30);

        emailLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(131, 113, 90));
        emailLabel.setText("Email");
        parentPanel.add(emailLabel);
        emailLabel.setBounds(0, 130, 68, 30);

        myCharLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        myCharLabel.setForeground(new java.awt.Color(131, 113, 90));
        myCharLabel.setText("My Characters");
        myCharLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myCharLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                myCharLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                myCharLabelMouseExited(evt);
            }
        });
        parentPanel.add(myCharLabel);
        myCharLabel.setBounds(0, 360, 190, 30);

        passwordLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(131, 113, 90));
        passwordLabel.setText("Password");
        parentPanel.add(passwordLabel);
        passwordLabel.setBounds(0, 240, 130, 30);

        hidePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/hide_password.png"))); // NOI18N
        hidePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hidePasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hidePasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hidePasswordMouseExited(evt);
            }
        });
        parentPanel.add(hidePassword);
        hidePassword.setBounds(480, 288, 28, 20);

        showPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/show_password.png"))); // NOI18N
        showPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPasswordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showPasswordMouseExited(evt);
            }
        });
        parentPanel.add(showPassword);
        showPassword.setBounds(480, 290, 28, 15);

        myCharArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/right_arrow.png"))); // NOI18N
        myCharArrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myCharLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                myCharLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                myCharLabelMouseExited(evt);
            }
        });
        parentPanel.add(myCharArrow);
        myCharArrow.setBounds(190, 366, 20, 20);

        scrollPane.setViewportView(parentPanel);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(80, 130, 560, 470);

        settingText.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        settingText.setForeground(new java.awt.Color(179, 119, 50));
        settingText.setText("Settings");
        settingText.setToolTipText("");
        getContentPane().add(settingText);
        settingText.setBounds(80, 70, 280, 50);

        settingBg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle11.png"))); // NOI18N
        getContentPane().add(settingBg);
        settingBg.setBounds(40, 40, 640, 630);

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

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_setting.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //if username field is empty, then field become red
    private void updateSelfStatusUsername(){
        String text = usernameField.getText();
        if(text.trim().isEmpty()){
            usernameLabel.setForeground(new Color(251,76,76));
            usernameField.setForeground(new Color(251,76,76));
        }
        else{
            usernameLabel.setForeground(new Color(133,129,119));
            usernameField.setForeground(new Color(130,130,130));
        }
    }
    
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    //to validate email regex
    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
    
    //if email is empty or not valid, then field become red
    private void updateSelfStatusEmail(){
        String text = emailField.getText();
        if(text.trim().isEmpty() || !(validateEmail(text))){
            emailLabel.setForeground(new Color(251,76,76));
            emailField.setForeground(new Color(251,76,76));
        }
        else{
            emailLabel.setForeground(new Color(133,129,119));
            emailField.setForeground(new Color(130,130,130));
        }
    }
    
    
    private static final Pattern VALID_PASSWORD_REGEX = 
    Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", Pattern.CASE_INSENSITIVE);
    
    //to validate password regex
    private static boolean validatePassword(String passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.matches();
    }
    
    //if password is empty or not valid, then field become red
    private void updateSelfStatusPassword(){
        String text = passwordField.getText();
        if(text.trim().isEmpty() || !(validatePassword(text))){
            passwordLabel.setForeground(new Color(251,76,76));
            passwordField.setForeground(new Color(251,76,76));
        }
        else{
            passwordLabel.setForeground(new Color(133,129,119));
            passwordField.setForeground(new Color(130,130,130));
        }
    }
    
    //if field is focus lost and empty, then field become red
    private void usernameFieldFocusLost(java.awt.event.FocusEvent evt) {                                   
        String text = usernameField.getText();
        if(text.trim().isEmpty()){
            usernameLabel.setForeground(new Color(251,76,76));
            usernameField.setForeground(new Color(251,76,76));
        }
        else{
            usernameLabel.setForeground(new Color(133,129,119));
            usernameField.setForeground(new Color(130,130,130));
        }
    }                                  
    
    //if field is focus lost and empty, then field become red
    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {                                
        String text = emailField.getText();
        if(text.trim().isEmpty() || !(validateEmail(text))){
            emailLabel.setForeground(new Color(251,76,76));
            emailField.setForeground(new Color(251,76,76));
        }
        else{
            emailLabel.setForeground(new Color(133,129,119));
            emailField.setForeground(new Color(130,130,130));
        }
    }                               

    //if field is focus lost and empty, then field become red
    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {                                   
        String text = passwordField.getText();
        if(text.trim().isEmpty() || !(validatePassword(text))){
            passwordLabel.setForeground(new Color(251,76,76));
            passwordField.setForeground(new Color(251,76,76));
        }
        else{
            passwordLabel.setForeground(new Color(133,129,119));
            passwordField.setForeground(new Color(130,130,130));
        }
    }
    
    //check whether the username and email already exist 
    private boolean checkExist(String username, String email){
        ArrayList<String> allUsername = new ArrayList<>();
        ArrayList<String> allEmail = new ArrayList<>();
        
        //call the database to check
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select username from user");
            while(rs.next()){
                allUsername.add(rs.getString(1));
            }
            ResultSet rs1 = st.executeQuery("select email from user");
            while(rs1.next()){
                allEmail.add(rs1.getString(1));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(getContentPane(), e);
        }
        
        if(allUsername.contains(username) && allEmail.contains(email) && allUsername.indexOf(username)!=userId-1 && allEmail.indexOf(email)!=userId-1){
            return true;
        }
        return false;
    }
    
    
    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        String usernameInput = usernameField.getText();
        String emailInput = emailField.getText();
        String passwordInput = passwordField.getText();
        
        //if username is empty
        if(usernameInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Username cannot be empty.");
        }
        //if email is empty
        else if(emailInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Email cannot be empty.");
        }
        //if password is empty
        else if(passwordInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Password cannot be empty.");
        }
        else{
            //if email is not valid
            if(!(validateEmail(emailInput))){
                JOptionPane.showMessageDialog(getContentPane(), "Please input a correct email.");
            }            
            //if password is not valid
            if(!(validatePassword(passwordInput))){
                JOptionPane.showMessageDialog(getContentPane(), "Password must have 8 characters with at least one number and one character");
            }
            
            //if the username and email already exist
            if(checkExist(usernameInput, emailInput)){
                JOptionPane.showMessageDialog(getContentPane(), "Email and username already existed.");
            }

            if(validateEmail(emailInput) && validatePassword(passwordInput) && !(checkExist(usernameInput, emailInput))){
                //update database
                try{
                    Connection con = ConnectionProvider.getCon();
                    String str = "update user set ";
                    str = str + "username='" + usernameInput + "', ";
                    str = str + "email='" + emailInput + "', ";
                    str = str + "password='" + passwordInput + "' ";
                    str = str + "where id=" + userId;

                    PreparedStatement ps = con.prepareStatement(str);
                    ps.executeUpdate();
                    
                    //close frame
                    JOptionPane.showMessageDialog(getContentPane(), "Information has been saved.");
                    setVisible(false);
                    dispose();
                    new Home(userId, bgmPlayer).setVisible(true);

                }catch(Exception e){
                    JOptionPane.showMessageDialog(getContentPane(), e);
                }
            }
        }
    }//GEN-LAST:event_saveButtonMouseClicked

    private void saveButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseEntered
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_saveButtonMouseEntered

    private void saveButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseExited
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        saveLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_saveButtonMouseExited

    private void saveLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseClicked
        String usernameInput = usernameField.getText();
        String emailInput = emailField.getText();
        String passwordInput = passwordField.getText();
        
        //if username is empty
        if(usernameInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Username cannot be empty.");
        }
        //if email is empty
        else if(emailInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Email cannot be empty.");
        }
        //if password is empty
        else if(passwordInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Password cannot be empty.");
        }
        else{
            //if email is not valid
            if(!(validateEmail(emailInput))){
                JOptionPane.showMessageDialog(getContentPane(), "Please input a correct email.");
            }            
            //if password is not valid
            if(!(validatePassword(passwordInput))){
                JOptionPane.showMessageDialog(getContentPane(), "Password must have 8 characters with at least one number and one character");
            }
            
            //if the username and email already exist
            if(checkExist(usernameInput, emailInput)){
                JOptionPane.showMessageDialog(getContentPane(), "Email and username already existed.");
            }

            if(validateEmail(emailInput) && validatePassword(passwordInput) && !(checkExist(usernameInput, emailInput))){
                //update database
                try{
                    Connection con = ConnectionProvider.getCon();
                    String str = "update user set ";
                    str = str + "username='" + usernameInput + "', ";
                    str = str + "email='" + emailInput + "', ";
                    str = str + "password='" + passwordInput + "' ";
                    str = str + "where id=" + userId;

                    PreparedStatement ps = con.prepareStatement(str);
                    ps.executeUpdate();
                    
                    //close frame
                    JOptionPane.showMessageDialog(getContentPane(), "Information has been saved.");
                    setVisible(false);
                    dispose();
                    new Home(userId, bgmPlayer).setVisible(true);

                }catch(Exception e){
                    JOptionPane.showMessageDialog(getContentPane(), e);
                }
            }
        }
    }//GEN-LAST:event_saveLabelMouseClicked

    private void saveLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseEntered
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_saveLabelMouseEntered

    private void saveLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveLabelMouseExited
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        saveLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_saveLabelMouseExited

    private void showPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordMouseClicked
        showPassword.setVisible(false);
        hidePassword.setVisible(true);
        passwordField.setEchoChar((char)0);     //set character to be as usual
    }//GEN-LAST:event_showPasswordMouseClicked

    private void showPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordMouseEntered
        showPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_showPasswordMouseEntered

    private void showPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordMouseExited
        showPassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_showPasswordMouseExited

    private void hidePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordMouseClicked
        hidePassword.setVisible(false);
        showPassword.setVisible(true);
        passwordField.setEchoChar('*');     //set character as *
    }//GEN-LAST:event_hidePasswordMouseClicked

    private void hidePasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordMouseEntered
        hidePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_hidePasswordMouseEntered

    private void hidePasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordMouseExited
        hidePassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_hidePasswordMouseExited

    private void myCharLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCharLabelMouseEntered
        //make the arrow move to right
        myCharArrow.setBounds(200, myCharArrow.getY(), 20, 20);
        myCharArrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myCharLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        parentPanel.revalidate();
        parentPanel.repaint();
    }//GEN-LAST:event_myCharLabelMouseEntered

    private void myCharLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCharLabelMouseExited
        //make the arrow move to left
        myCharArrow.setBounds(190, myCharArrow.getY(), 20, 20);
        myCharArrow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        myCharLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        parentPanel.revalidate();
        parentPanel.repaint();
    }//GEN-LAST:event_myCharLabelMouseExited
    
    
    public static boolean open=false;
    private Settings setting = (Settings) SwingUtilities.getRoot(this);
    
    private void myCharLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCharLabelMouseClicked
        if(open==false){
            new SettingCharacters(userId, ownedChars, setting).setVisible(true);
            open = true;
        }
        else{
            JOptionPane.showMessageDialog(parentPanel, "The frame is already opened.");
        }
    }//GEN-LAST:event_myCharLabelMouseClicked

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        int ans = JOptionPane.showConfirmDialog(getContentPane(), "Do you really want to go back? Your changes will not be saved", "SELECT", JOptionPane.YES_NO_OPTION);
        
        if(ans == JOptionPane.YES_OPTION){
            setVisible(false);
            dispose();
            new Home(userId, bgmPlayer).setVisible(true);
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
    
    
    public static boolean openProfile = false;
    
    private void myProfileMouseEntered(MouseEvent evt){
        //make arrow move to right
        myProfileArrow.setBounds(myProfile.getPreferredSize().width+20, myProfileArrow.getY(), 20, 20);
        myProfileArrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        parentPanel.revalidate();
        parentPanel.repaint();
    }
    
    private void myProfileMouseExited(MouseEvent evt){
        //make arrow move to left
        myProfileArrow.setBounds(myProfile.getPreferredSize().width+13, myProfileArrow.getY(), 20, 20);
        myProfileArrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myProfile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        parentPanel.revalidate();
        parentPanel.repaint();
    }
    
    private void myProfileMouseClicked(MouseEvent evt){
        if(openProfile==false){
            new SettingProfile(userId, setting).setVisible(true);
            openProfile = true;
        }
        else{
            JOptionPane.showMessageDialog(parentPanel, "The frame is already opened.");
        }
    }
    
    
    public static boolean openMusic = false;
    
    private void myMusicMouseEntered(MouseEvent evt){
        //make arrow move to right
        myMusicArrow.setBounds(myMusic.getPreferredSize().width+20, myMusicArrow.getY(), 20, 20);
        myMusicArrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myMusic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        parentPanel.revalidate();
        parentPanel.repaint();
    }
    
    private void myMusicMouseExited(MouseEvent evt){
        //make arrow move to left
        myMusicArrow.setBounds(myMusic.getPreferredSize().width+13, myMusicArrow.getY(), 20, 20);
        myMusicArrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myMusic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        parentPanel.revalidate();
        parentPanel.repaint();
    }
    
    private void myMusicMouseClicked(MouseEvent evt){
        if(openMusic==false){
            new SettingMusic(userId, bgmPlayer, setting).setVisible(true);
            openMusic = true;
        }
        else{
            JOptionPane.showMessageDialog(parentPanel, "The frame is already opened.");
        }
    }
    
    
    
    
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
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settings().setVisible(true);
            }
        });
    }
    
    private App.RoundJTextField usernameField;
    private App.RoundJTextField emailField;
    private App.RoundJPasswordField passwordField;
    private JPanel imagePanel;
    private JLabel myProfile;
    private JLabel myProfileArrow;
    private JLabel profilePic;
    private JLabel myMusic;
    private JLabel myMusicArrow;
    private JPanel musicPanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel hidePassword;
    private javax.swing.JLabel myCharArrow;
    private javax.swing.JLabel myCharLabel;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel saveButton;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel settingBg;
    private javax.swing.JLabel settingText;
    private javax.swing.JLabel showPassword;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
