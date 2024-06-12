/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import DatabaseConnection.ConnectionProvider;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author asus
 */
public class Settings extends javax.swing.JFrame {
    private int userId;
    private String username;
    private String email;
    private String password;
    public ArrayList<String> ownedChars = new ArrayList<>();
    /**
     * Creates new form Settings
     */
    public Settings() {
        initComponents();
        this.userId = 1;
        myinit();
    }
    
    public Settings(int userId){
        initComponents();
        this.userId = userId;
        setLocationRelativeTo(null);
        myinit();
    }
    
    private void myinit(){
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
        
        imagePanel = new JPanel();
        imagePanel.setLayout(null);
        imagePanel.setPreferredSize(new Dimension(400, 200)); 
        imagePanel.setOpaque(false);
        imagePanel.setBackground(new Color(0,0,0,0));
        
        retrieveFromDatabase();
        setTextField();
        setSomeCharImages(ownedChars);
        
        //repaint components
        parentPanel.revalidate();
        parentPanel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    
    private void retrieveFromDatabase(){
        ArrayList<Boolean> ownedOrNot = new ArrayList<>();
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select * from user where id='" + userId + "'");
            if(rs.first()){
                username = rs.getString(2);
                email = rs.getString(3);
                password = rs.getString(4);
            }
            
            
            ResultSet rs1 = st.executeQuery("select * from characters where userId=" + userId + "");
            if(rs1.first()){
                for(int i=2; i<87; i++){
                    ownedOrNot.add(rs1.getBoolean(i));
                }
            }
            else{
               JOptionPane.showMessageDialog(getContentPane(), "No user ID found");
            }
        
        
            ResultSet rs2 = st.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='characters'");
            int index=0;
            while(rs2.next()){
                if(index==0){
                    index++;
                    continue;
                }
                if(ownedOrNot.get(index-1) == true){
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
    
    
    private void setTextField(){
        usernameField = new App.RoundJTextField(30);
        emailField = new App.RoundJTextField(30);
        passwordField = new App.RoundJPasswordField(30);
        
        usernameField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        usernameField.setText(username);
        usernameField.setForeground(new java.awt.Color(130,130,130));
        parentPanel.add(usernameField);
        usernameField.setBounds(0, 50, 530, usernameField.getPreferredSize().height);
        parentPanel.setComponentZOrder(usernameField, 0);
        
        emailField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        emailField.setForeground(new java.awt.Color(130,130,130));
        emailField.setText(email);
        parentPanel.add(emailField);
        emailField.setBounds(0, 162, 530, emailField.getPreferredSize().height);
        parentPanel.setComponentZOrder(emailField, 0);
        
        passwordField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        passwordField.setForeground(new java.awt.Color(130,130,130));
        passwordField.setText(password);
        parentPanel.add(passwordField);
        passwordField.setBounds(0, 277, 530, passwordField.getPreferredSize().height);
        parentPanel.setComponentZOrder(passwordField, 0);
        parentPanel.setComponentZOrder(hidePassword, 0);
        parentPanel.setComponentZOrder(showPassword, 0);
        
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
        
        hidePassword.setVisible(false);
        passwordField.setEchoChar('*');
    }
    
    public void setSomeCharImages(ArrayList<String> characters){
        imagePanel.removeAll();
        int x=10;
        
        for(int i=0; i<4; i++){
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(new ImageIcon("src/App/image/CharacterCard/Archive/Portraits " +characters.get(i)+ ".png"));
            imageLabel.setBounds(x, 0, 100, 100);
            imagePanel.add(imageLabel);
            imagePanel.setComponentZOrder(imageLabel, 0);
            x+=120;
            imagePanel.revalidate();
            imagePanel.repaint();
        }
        
        imagePanel.setPreferredSize(new Dimension(imagePanel.getWidth(), 500));
        imagePanel.setBounds(10, myCharLabel.getY()+myCharLabel.getPreferredSize().height+15, 500, imagePanel.getPreferredSize().height);
        
        parentPanel.add(imagePanel);
        parentPanel.setPreferredSize(new Dimension(parentPanel.getWidth(), imagePanel.getHeight()+90));
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
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

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

        jLabel3.setFont(new java.awt.Font("HYWenHei-85W", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(179, 119, 50));
        jLabel3.setText("Settings");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(80, 70, 280, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/Rectangle11.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 40, 640, 630);

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_setting.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
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
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
    
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
    
    
    public static final Pattern VALID_PASSWORD_REGEX = 
    Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", Pattern.CASE_INSENSITIVE);
    
    public static boolean validatePassword(String passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.matches();
    }
    
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
    
    private boolean checkExist(String username, String email){
        ArrayList<String> allUsername = new ArrayList<>();
        ArrayList<String> allEmail = new ArrayList<>();
        
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
        
        if(allUsername.contains(username) && allEmail.contains(email)){
            return true;
        }
        return false;
    }
    
    
    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        String usernameInput = usernameField.getText();
        String emailInput = emailField.getText();
        String passwordInput = passwordField.getText();
        

        if(usernameInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Username cannot be empty.");
        }
        else if(emailInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Email cannot be empty.");
        }
        else if(passwordInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Password cannot be empty.");
        }
        else{
            if(!(validateEmail(emailInput))){
                JOptionPane.showMessageDialog(getContentPane(), "Please input a correct email.");
            }            
            if(!(validatePassword(passwordInput))){
                JOptionPane.showMessageDialog(getContentPane(), "Password must have 8 characters with at least one number and one character");
            }
            
            if(checkExist(usernameInput, emailInput)){
                JOptionPane.showMessageDialog(getContentPane(), "Email and username already existed.");
            }

            if(validateEmail(emailInput) && validatePassword(passwordInput) && !(checkExist(usernameInput, emailInput))){
                try{
                    Connection con = ConnectionProvider.getCon();
                    String str = "update user set ";
                    str = str + "username='" + usernameInput + "', ";
                    str = str + "email='" + emailInput + "', ";
                    str = str + "password='" + passwordInput + "' ";
                    str = str + "where id=" + userId;

                    PreparedStatement ps = con.prepareStatement(str);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(getContentPane(), "Information has been saved.");
                    setVisible(false);
                    dispose();
                    new Home(userId).setVisible(true);

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
        

        if(usernameInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Username cannot be empty.");
        }
        else if(emailInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Email cannot be empty.");
        }
        else if(passwordInput.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Password cannot be empty.");
        }
        else{
            if(!(validateEmail(emailInput))){
                JOptionPane.showMessageDialog(getContentPane(), "Please input a correct email.");
            }            
            if(!(validatePassword(passwordInput))){
                JOptionPane.showMessageDialog(getContentPane(), "Password must have 8 characters with at least one number and one character");
            }
            
            if(checkExist(usernameInput, emailInput)){
                JOptionPane.showMessageDialog(getContentPane(), "Email and username already existed.");
            }

            if(validateEmail(emailInput) && validatePassword(passwordInput) && !(checkExist(usernameInput, emailInput))){
                try{
                    Connection con = ConnectionProvider.getCon();
                    String str = "update user set ";
                    str = str + "username='" + usernameInput + "', ";
                    str = str + "email='" + emailInput + "', ";
                    str = str + "password='" + passwordInput + "' ";
                    str = str + "where id=" + userId;

                    PreparedStatement ps = con.prepareStatement(str);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(getContentPane(), "Information has been saved.");
                    setVisible(false);
                    dispose();
                    new Home(userId).setVisible(true);

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
        passwordField.setEchoChar((char)0);
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
        passwordField.setEchoChar('*');
    }//GEN-LAST:event_hidePasswordMouseClicked

    private void hidePasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordMouseEntered
        hidePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_hidePasswordMouseEntered

    private void hidePasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordMouseExited
        hidePassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_hidePasswordMouseExited

    private void myCharLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCharLabelMouseEntered
        myCharArrow.setBounds(200, myCharArrow.getY(), 20, 20);
        myCharArrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        myCharLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        parentPanel.revalidate();
        parentPanel.repaint();
    }//GEN-LAST:event_myCharLabelMouseEntered

    private void myCharLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myCharLabelMouseExited
        myCharArrow.setBounds(190, myCharArrow.getY(), 20, 20);
        myCharArrow.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        myCharLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        parentPanel.revalidate();
        parentPanel.repaint();
    }//GEN-LAST:event_myCharLabelMouseExited
    
    
    public static boolean open=false;
    Settings setting = (Settings) SwingUtilities.getRoot(this);
    
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel hidePassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel myCharArrow;
    private javax.swing.JLabel myCharLabel;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel saveButton;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel showPassword;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
