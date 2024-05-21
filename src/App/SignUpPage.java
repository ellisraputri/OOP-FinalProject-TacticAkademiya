/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author asus
 */
public class SignUpPage extends javax.swing.JFrame {

    /**
     * Creates new form SignUp
     */
    public SignUpPage() {
        initComponents();
        myinit();
        setTitle("Sign Up Page");
        setResizable(false);
    }
    
    private void myinit(){
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

        jLabel2 = new javax.swing.JLabel();
        confirmPasswordLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        showPassword = new javax.swing.JLabel();
        hidePassword = new javax.swing.JLabel();
        hidePasswordConfirm = new javax.swing.JLabel();
        showPasswordConfirm = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back1.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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
        getContentPane().add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 581, -1, -1));

        jLabel4.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(179, 119, 50));
        jLabel4.setText("Already have an account? ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 581, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(179, 119, 50));
        jSeparator1.setForeground(new java.awt.Color(179, 119, 50));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jSeparator1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSeparator1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSeparator1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jSeparator1MouseExited(evt);
            }
        });
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 600, 60, 20));

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
        getContentPane().add(hidePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 415, -1, -1));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_signup.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back2.png")));
        jLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back1.png")));
        jLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        setVisible(false);
        new WelcomePage().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void loginLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseEntered
        loginLabel.setForeground(new Color(135,86,31));
        jSeparator1.setForeground(new Color(135,86,31));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jSeparator1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_loginLabelMouseEntered

    private void loginLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseExited
        loginLabel.setForeground(new Color(179,119,50));
        jSeparator1.setForeground(new Color(179,119,50));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        jSeparator1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_loginLabelMouseExited

    private void loginLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseClicked
        setVisible(false);
        new LoginPage().setVisible(true);
    }//GEN-LAST:event_loginLabelMouseClicked

    private void jSeparator1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator1MouseClicked
        setVisible(false);
        new LoginPage().setVisible(true);
    }//GEN-LAST:event_jSeparator1MouseClicked

    private void jSeparator1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator1MouseEntered
        loginLabel.setForeground(new Color(135,86,31));
        jSeparator1.setForeground(new Color(135,86,31));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jSeparator1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jSeparator1MouseEntered

    private void jSeparator1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator1MouseExited
        loginLabel.setForeground(new Color(179,119,50));
        jSeparator1.setForeground(new Color(179,119,50));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        jSeparator1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jSeparator1MouseExited

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
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel hidePassword;
    private javax.swing.JLabel hidePasswordConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel showPassword;
    private javax.swing.JLabel showPasswordConfirm;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
