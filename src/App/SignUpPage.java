/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import java.sql.*;
import DatabaseConnection.ConnectionProvider;
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author asus
 */
public class SignUpPage extends javax.swing.JFrame {
    private MP3Player bgmPlayer;
    /**
     * Creates new form SignUp
     */
    public SignUpPage() {
        initComponents();
        myinit();
        setTitle("Sign Up Page");
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    public SignUpPage(MP3Player bgmPlayer) {
        this.bgmPlayer = bgmPlayer;
        initComponents();
        myinit();
        setTitle("Sign Up Page");
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void myinit(){
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/App/image/mouse.png").getImage(), new Point(0,0),"custom cursor"));     
        
        usernameField = new App.RoundJTextField(30);
        emailField = new App.RoundJTextField(30);
        passwordField = new App.RoundJPasswordField(30);
        passwordConfirmField = new App.RoundJPasswordField(30);
        
        usernameField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        usernameField.setForeground(new java.awt.Color(130,130,130));
        getContentPane().add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 192, -1, -1));
        getContentPane().setComponentZOrder(usernameField, 0);
        
        emailField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        emailField.setForeground(new java.awt.Color(130,130,130));
        getContentPane().add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(607, 296, -1, -1));
        getContentPane().setComponentZOrder(emailField, 0);
        
        passwordField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        passwordField.setForeground(new java.awt.Color(130,130,130));
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(607, 402, -1, -1));
        getContentPane().setComponentZOrder(passwordField, 0);
        
        passwordConfirmField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        passwordConfirmField.setForeground(new java.awt.Color(130,130,130));
        getContentPane().add(passwordConfirmField, new org.netbeans.lib.awtextra.AbsoluteConstraints(607, 519, -1, -1));
        getContentPane().setComponentZOrder(passwordConfirmField, 0);
        
        
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
        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "Enter" is pressed in textField1, move focus to textField2
                passwordConfirmField.requestFocusInWindow();
            }
        });
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });
        
        passwordConfirmField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSelfStatusPasswordConfirm();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSelfStatusPasswordConfirm();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSelfStatusPasswordConfirm();
            }
        });
        passwordConfirmField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordConfirmFieldFocusLost(evt);
            }
        });
        
        
        getContentPane().setComponentZOrder(showPassword, 0);
        getContentPane().setComponentZOrder(hidePassword, 0);
        hidePassword.setVisible(false);
        passwordField.setEchoChar('*');
        
        
        getContentPane().setComponentZOrder(showPasswordConfirm, 0);
        getContentPane().setComponentZOrder(hidePasswordConfirm, 0);
        hidePasswordConfirm.setVisible(false);
        passwordConfirmField.setEchoChar('*');
        
        
        
        
        revalidate();
        repaint();
    }
    
    
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

    private static boolean validateEmail(String emailStr) {
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
    
    
    private static final Pattern VALID_PASSWORD_REGEX = 
    Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", Pattern.CASE_INSENSITIVE);
    
    private static boolean validatePassword(String passwordStr) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.matches();
    }
    
    private void updateSelfStatusPassword(){
        String text = passwordField.getText();
        String textConfirm = passwordConfirmField.getText();
        if(text.trim().isEmpty() || !(validatePassword(text))){
            passwordLabel.setForeground(new Color(251,76,76));
            passwordField.setForeground(new Color(251,76,76));
        }
        else if(!(text.equals(textConfirm))){
            passwordLabel.setForeground(new Color(251,76,76));
            passwordField.setForeground(new Color(251,76,76));
            confirmPasswordLabel.setForeground(new Color(251,76,76));
            passwordConfirmField.setForeground(new Color(251,76,76));
        }
        else if(text.equals(textConfirm)){
            passwordLabel.setForeground(new Color(133,129,119));
            passwordField.setForeground(new Color(130,130,130));
            confirmPasswordLabel.setForeground(new Color(133,129,119));
            passwordConfirmField.setForeground(new Color(130,130,130));
        }
        else{
            passwordLabel.setForeground(new Color(133,129,119));
            passwordField.setForeground(new Color(130,130,130));
        }
    }
    
    private void updateSelfStatusPasswordConfirm(){
        String text = passwordField.getText();
        String textConfirm = passwordConfirmField.getText();
        if(textConfirm.trim().isEmpty() || !(validatePassword(textConfirm))){
            confirmPasswordLabel.setForeground(new Color(251,76,76));
            passwordConfirmField.setForeground(new Color(251,76,76));
        }
        else if(!(text.equals(textConfirm))){
            passwordLabel.setForeground(new Color(251,76,76));
            passwordField.setForeground(new Color(251,76,76));
            confirmPasswordLabel.setForeground(new Color(251,76,76));
            passwordConfirmField.setForeground(new Color(251,76,76));
        }
        else if(text.equals(textConfirm)){
            passwordLabel.setForeground(new Color(133,129,119));
            passwordField.setForeground(new Color(130,130,130));
            confirmPasswordLabel.setForeground(new Color(133,129,119));
            passwordConfirmField.setForeground(new Color(130,130,130));
        }
        else{
            confirmPasswordLabel.setForeground(new Color(133,129,119));
            passwordConfirmField.setForeground(new Color(130,130,130));
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

        backButton = new javax.swing.JLabel();
        confirmPasswordLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        alreadyText = new javax.swing.JLabel();
        underline = new javax.swing.JSeparator();
        showPassword = new javax.swing.JLabel();
        hidePassword = new javax.swing.JLabel();
        hidePasswordConfirm = new javax.swing.JLabel();
        showPasswordConfirm = new javax.swing.JLabel();
        registerLabel = new javax.swing.JLabel();
        registerButton = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back1.png"))); // NOI18N
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonMouseExited(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        confirmPasswordLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        confirmPasswordLabel.setForeground(new java.awt.Color(133, 129, 119));
        confirmPasswordLabel.setText("Confirm Password");
        getContentPane().add(confirmPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 484, -1, -1));

        usernameLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(133, 129, 119));
        usernameLabel.setText("Username");
        getContentPane().add(usernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 155, -1, -1));

        emailLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(133, 129, 119));
        emailLabel.setText("Email");
        getContentPane().add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 261, -1, -1));

        passwordLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(133, 129, 119));
        passwordLabel.setText("Password");
        getContentPane().add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 367, -1, -1));

        loginLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(179, 119, 50));
        loginLabel.setText("Log In");
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginLabelMouseExited(evt);
            }
        });
        getContentPane().add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 590, -1, -1));

        alreadyText.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        alreadyText.setForeground(new java.awt.Color(179, 119, 50));
        alreadyText.setText("Already have an account? ");
        getContentPane().add(alreadyText, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 590, -1, -1));

        underline.setBackground(new java.awt.Color(179, 119, 50));
        underline.setForeground(new java.awt.Color(179, 119, 50));
        underline.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        underline.setOpaque(true);
        underline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                underlineMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                underlineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                underlineMouseExited(evt);
            }
        });
        getContentPane().add(underline, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 610, 60, 1));

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
        getContentPane().add(showPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 417, -1, -1));

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
        getContentPane().add(hidePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 415, -1, 20));

        hidePasswordConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/hide_password.png"))); // NOI18N
        hidePasswordConfirm.setToolTipText("");
        hidePasswordConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hidePasswordConfirmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hidePasswordConfirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hidePasswordConfirmMouseExited(evt);
            }
        });
        getContentPane().add(hidePasswordConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 530, -1, -1));

        showPasswordConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/show_password.png"))); // NOI18N
        showPasswordConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPasswordConfirmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showPasswordConfirmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showPasswordConfirmMouseExited(evt);
            }
        });
        getContentPane().add(showPasswordConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 533, -1, -1));

        registerLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        registerLabel.setForeground(new java.awt.Color(255, 255, 255));
        registerLabel.setText("Register");
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerLabelMouseExited(evt);
            }
        });
        getContentPane().add(registerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 637, -1, -1));

        registerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png"))); // NOI18N
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButtonMouseExited(evt);
            }
        });
        getContentPane().add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 628, -1, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_signup.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseEntered
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back2.png")));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_backButtonMouseEntered

    private void backButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseExited
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back1.png")));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_backButtonMouseExited

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        setVisible(false);
        dispose();
        bgmPlayer.stop();
        new WelcomePage().setVisible(true);
    }//GEN-LAST:event_backButtonMouseClicked

    private void loginLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseEntered
        loginLabel.setForeground(new Color(135,86,31));
        underline.setForeground(new Color(135,86,31));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        underline.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_loginLabelMouseEntered

    private void loginLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseExited
        loginLabel.setForeground(new Color(179,119,50));
        underline.setForeground(new Color(179,119,50));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        underline.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_loginLabelMouseExited

    private void loginLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseClicked
        setVisible(false);
        dispose();
        new LoginPage(bgmPlayer).setVisible(true);
    }//GEN-LAST:event_loginLabelMouseClicked

    private void underlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_underlineMouseClicked
        setVisible(false);
        dispose();
        new LoginPage(bgmPlayer).setVisible(true);
    }//GEN-LAST:event_underlineMouseClicked

    private void underlineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_underlineMouseEntered
        loginLabel.setForeground(new Color(135,86,31));
        underline.setForeground(new Color(135,86,31));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        underline.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_underlineMouseEntered

    private void underlineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_underlineMouseExited
        loginLabel.setForeground(new Color(179,119,50));
        underline.setForeground(new Color(179,119,50));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        underline.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_underlineMouseExited

    private void hidePasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordMouseEntered
        hidePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_hidePasswordMouseEntered

    private void hidePasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordMouseExited
        hidePassword.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_hidePasswordMouseExited

    private void hidePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordMouseClicked
        hidePassword.setVisible(false);
        showPassword.setVisible(true);
        passwordField.setEchoChar('*');
    }//GEN-LAST:event_hidePasswordMouseClicked

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

    private void hidePasswordConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordConfirmMouseClicked
        hidePasswordConfirm.setVisible(false);
        showPasswordConfirm.setVisible(true);
        passwordConfirmField.setEchoChar('*');
    }//GEN-LAST:event_hidePasswordConfirmMouseClicked

    private void hidePasswordConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordConfirmMouseEntered
        hidePasswordConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_hidePasswordConfirmMouseEntered

    private void hidePasswordConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePasswordConfirmMouseExited
        hidePasswordConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_hidePasswordConfirmMouseExited

    private void showPasswordConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordConfirmMouseClicked
        showPasswordConfirm.setVisible(false);
        hidePasswordConfirm.setVisible(true);
        passwordConfirmField.setEchoChar((char)0);
    }//GEN-LAST:event_showPasswordConfirmMouseClicked

    private void showPasswordConfirmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordConfirmMouseEntered
        showPasswordConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_showPasswordConfirmMouseEntered

    private void showPasswordConfirmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPasswordConfirmMouseExited
        showPasswordConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_showPasswordConfirmMouseExited

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
    
    private void registerButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseClicked
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String passwordConfirm = passwordConfirmField.getText();
        
        if(username.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Username is still empty.");
        }
        else if(email.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Email is still empty.");
        }
        else if(password.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Password is still empty.");
        }
        else if(passwordConfirm.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Confirm Password is still empty.");
        }
        else{
           if(!(validateEmail(email))){
                JOptionPane.showMessageDialog(getContentPane(), "Please input a correct email.");
           }
           
           if(!(password.equals(passwordConfirm))){
               JOptionPane.showMessageDialog(getContentPane(), "Password and Confirm Password is not the same.");
           }
           else{
               if(!(validatePassword(password))){
                    JOptionPane.showMessageDialog(getContentPane(), "Password must have 8 characters with at least one number and one character");
               }
           }
           
           if(checkExist(username, email)){
                JOptionPane.showMessageDialog(getContentPane(), "Email and username already existed.");               
           }
           
           
           if(password.equals(passwordConfirm) && validateEmail(email) && validatePassword(password) && !(checkExist(username, email))){
               try{
                   Connection con = ConnectionProvider.getCon();
                   Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                   ResultSet rs = st.executeQuery("select count(id) from user");
                   int newId=0;
                   if(rs.first()){
                       int id = rs.getInt(1);
                       newId = id + 1;
                   }
                   else{
                       newId = 1;
                   }
                   
                   String str = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
                   PreparedStatement ps = con.prepareStatement(str);
                   ps.setInt(1, newId);
                   ps.setString(2, username);
                   ps.setString(3, email);
                   ps.setString(4, password);
                   ps.setString(5,null);
                   ps.setString(6, "1a - What a Hopeful Voyage.mp3");
                   ps.setString(7, "1b - Vast and Blue.mp3");
                   ps.setString(8, "1c - Mesmerizing Waves.mp3");
                   ps.setString(9, "1d - Lovers' Oath.mp3");
                   ps.setString(10, "1e - Old Tales Preserved.mp3");
                   ps.executeUpdate();
                   
                   setVisible(false);
                   dispose();
                   new ListCharacter(newId, bgmPlayer).setVisible(true);
                   
               }catch(Exception e){
                   JOptionPane.showMessageDialog(getContentPane(), e);
               }
           }
        }
    }//GEN-LAST:event_registerButtonMouseClicked

    private void registerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseEntered
        registerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_registerButtonMouseEntered

    private void registerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseExited
        registerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_registerButtonMouseExited

    private void registerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseClicked
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String passwordConfirm = passwordConfirmField.getText();
        
        if(username.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Username is still empty.");
        }
        else if(email.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Email is still empty.");
        }
        else if(password.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Password is still empty.");
        }
        else if(passwordConfirm.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Confirm Password is still empty.");
        }
        else{
           if(!(validateEmail(email))){
                JOptionPane.showMessageDialog(getContentPane(), "Please input a correct email.");
           }
           
           if(!(password.equals(passwordConfirm))){
               JOptionPane.showMessageDialog(getContentPane(), "Password and Confirm Password is not the same.");
           }
           else{
               if(!(validatePassword(password))){
                    JOptionPane.showMessageDialog(getContentPane(), "Password must have 8 characters with at least one number and one character");
               }
           }
           
           if(checkExist(username, email)){
                JOptionPane.showMessageDialog(getContentPane(), "Email and username already existed.");               
           }
           
           if(password.equals(passwordConfirm) && validateEmail(email) && validatePassword(password) && !(checkExist(username, email))){
               try{
                   Connection con = ConnectionProvider.getCon();
                   Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                   ResultSet rs = st.executeQuery("select count(id) from user");
                   int newId=0;
                   if(rs.first()){
                       int id = rs.getInt(1);
                       newId = id + 1;
                   }
                   else{
                       newId = 1;
                   }
                   
                   String str = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
                   PreparedStatement ps = con.prepareStatement(str);
                   ps.setInt(1, newId);
                   ps.setString(2, username);
                   ps.setString(3, email);
                   ps.setString(4, password);
                   ps.setString(5,null);
                   ps.setString(6, "1a - What a Hopeful Voyage.mp3");
                   ps.setString(7, "1b - Vast and Blue.mp3");
                   ps.setString(8, "1c - Mesmerizing Waves.mp3");
                   ps.setString(9, "1d - Lovers' Oath.mp3");
                   ps.setString(10, "1e - Old Tales Preserved.mp3");
                   ps.executeUpdate();
                   
                   setVisible(false);
                   dispose();
                   new ListCharacter(newId, bgmPlayer).setVisible(true);
                   
               }catch(Exception e){
                   JOptionPane.showMessageDialog(getContentPane(), e);
               }
           }
        }
    }//GEN-LAST:event_registerLabelMouseClicked

    private void registerLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseEntered
        registerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_registerLabelMouseEntered

    private void registerLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseExited
        registerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_registerLabelMouseExited
    
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
        String textConfirm = passwordConfirmField.getText();
        if(text.trim().isEmpty() || !(validatePassword(text))){
            passwordLabel.setForeground(new Color(251,76,76));
            passwordField.setForeground(new Color(251,76,76));
        }
        else if(!(text.equals(textConfirm))){
            passwordLabel.setForeground(new Color(251,76,76));
            passwordField.setForeground(new Color(251,76,76));
            confirmPasswordLabel.setForeground(new Color(251,76,76));
            passwordConfirmField.setForeground(new Color(251,76,76));
        }
        else if(text.equals(textConfirm)){
            passwordLabel.setForeground(new Color(133,129,119));
            passwordField.setForeground(new Color(130,130,130));
            confirmPasswordLabel.setForeground(new Color(133,129,119));
            passwordConfirmField.setForeground(new Color(130,130,130));
        }
        else{
            passwordLabel.setForeground(new Color(133,129,119));
            passwordField.setForeground(new Color(130,130,130));
        }
    }                                  

    private void passwordConfirmFieldFocusLost(java.awt.event.FocusEvent evt) {                                          
        String text = passwordField.getText();
        String textConfirm = passwordConfirmField.getText();
        if(textConfirm.trim().isEmpty() || !(validatePassword(textConfirm))){
            confirmPasswordLabel.setForeground(new Color(251,76,76));
            passwordConfirmField.setForeground(new Color(251,76,76));
        }
        else if(!(text.equals(textConfirm))){
            passwordLabel.setForeground(new Color(251,76,76));
            passwordField.setForeground(new Color(251,76,76));
            confirmPasswordLabel.setForeground(new Color(251,76,76));
            passwordConfirmField.setForeground(new Color(251,76,76));
        }
        else if(text.equals(textConfirm)){
            passwordLabel.setForeground(new Color(133,129,119));
            passwordField.setForeground(new Color(130,130,130));
            confirmPasswordLabel.setForeground(new Color(133,129,119));
            passwordConfirmField.setForeground(new Color(130,130,130));
        }
        else{
            confirmPasswordLabel.setForeground(new Color(133,129,119));
            passwordConfirmField.setForeground(new Color(130,130,130));
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
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpPage().setVisible(true);
            }
        });
    }
    
    private App.RoundJTextField usernameField;
    private App.RoundJTextField emailField;
    private App.RoundJPasswordField passwordField;
    private App.RoundJPasswordField passwordConfirmField;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alreadyText;
    private javax.swing.JLabel backButton;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel hidePassword;
    private javax.swing.JLabel hidePasswordConfirm;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel registerButton;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JLabel showPassword;
    private javax.swing.JLabel showPasswordConfirm;
    private javax.swing.JSeparator underline;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
