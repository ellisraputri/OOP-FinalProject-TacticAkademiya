/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import DatabaseConnection.ConnectionProvider;
import java.sql.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author asus
 */
public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        initComponents();
        myinit();
        setResizable(false);
        setTitle("Log In Page");
    }
    
    private void myinit(){
        usernameEmailField = new App.RoundJTextField(30);
        passwordField = new App.RoundJPasswordField(30);
        
        usernameEmailField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        usernameEmailField.setForeground(new java.awt.Color(130,130,130));
        getContentPane().add(usernameEmailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));
        getContentPane().setComponentZOrder(usernameEmailField, 0);
        
        passwordField.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        passwordField.setForeground(new java.awt.Color(130,130,130));
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 394, -1, -1));
        getContentPane().setComponentZOrder(passwordField, 0);
        
        usernameEmailField.getDocument().addDocumentListener(new DocumentListener() {
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
        usernameEmailField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When "Enter" is pressed in textField1, move focus to textField2
                passwordField.requestFocusInWindow();
            }
        });
        usernameEmailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameEmailFieldFocusLost(evt);
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
        
        
        getContentPane().setComponentZOrder(hidePassword, 0);
        getContentPane().setComponentZOrder(showPassword, 0);
        hidePassword.setVisible(false);
        passwordField.setEchoChar('*');
    }
    
    private void updateSelfStatusUsername(){
        String text = usernameEmailField.getText();
        if(text.trim().isEmpty()){
            usernameEmailLabel.setForeground(new Color(251,76,76));
            usernameEmailField.setForeground(new Color(251,76,76));
        }
        else{
            usernameEmailLabel.setForeground(new Color(133,129,119));
            usernameEmailField.setForeground(new Color(130,130,130));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        usernameEmailLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        registerLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        loginLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JLabel();
        showPassword = new javax.swing.JLabel();
        hidePassword = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back1.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        usernameEmailLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        usernameEmailLabel.setForeground(new java.awt.Color(133, 129, 119));
        usernameEmailLabel.setText("Username / Email");
        getContentPane().add(usernameEmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));

        passwordLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(133, 129, 119));
        passwordLabel.setText("Password");
        getContentPane().add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, -1, -1));

        jLabel4.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(179, 119, 50));
        jLabel4.setText("Not a member?");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 487, -1, -1));

        registerLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        registerLabel.setForeground(new java.awt.Color(179, 119, 50));
        registerLabel.setText("Register here");
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
        getContentPane().add(registerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 487, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(179, 119, 50));
        jSeparator1.setForeground(new java.awt.Color(179, 119, 50));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jSeparator1.setOpaque(true);
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
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 508, 127, 1));

        loginLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(255, 255, 255));
        loginLabel.setText("Sign In");
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
        getContentPane().add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 538, -1, -1));

        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png"))); // NOI18N
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButtonMouseExited(evt);
            }
        });
        getContentPane().add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 530, -1, -1));

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
        getContentPane().add(showPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 408, -1, -1));

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
        getContentPane().add(hidePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 406, -1, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_login.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        setVisible(false);
        new WelcomePage().setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back2.png")));
        jLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back1.png")));
        jLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel3MouseExited

    private void registerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseClicked
        setVisible(false);
        new SignUpPage().setVisible(true);
    }//GEN-LAST:event_registerLabelMouseClicked

    private void registerLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseEntered
        registerLabel.setForeground(new Color(135,86,31));
        jSeparator1.setForeground(new Color(135,86,31));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jSeparator1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_registerLabelMouseEntered

    private void registerLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLabelMouseExited
        registerLabel.setForeground(new Color(179,119,50));
        jSeparator1.setForeground(new Color(179,119,50));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        jSeparator1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_registerLabelMouseExited

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        String usernameEmail = usernameEmailField.getText();
        String password = passwordField.getText();

        if(usernameEmail.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Username is still empty.");
        }
        else if(password.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Password is still empty.");
        }
        else{
            if(!(validatePassword(password))){
                JOptionPane.showMessageDialog(getContentPane(), "Password must have 8 characters with at least one number and one character");
            }

            if(validatePassword(password)){
                String passwordConfirmation ="";
                int id = 0;
                try{
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = st.executeQuery("select * from user where username='" + usernameEmail + "'");
                    if(rs.first()){
                        id = rs.getInt(1);
                        passwordConfirmation = rs.getString(4);
                        
                        if(passwordConfirmation.equals(password)){
                            setVisible(false);
                            new Home().setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(getContentPane(), "Incorrect Password");
                        }
                    }
                    else{
                        ResultSet rs1 = st.executeQuery("select * from user where email='" + usernameEmail + "'");
                        if(rs1.first()){
                            id = rs1.getInt(1);
                            passwordConfirmation = rs1.getString(4);

                            if(passwordConfirmation.equals(password)){
                                setVisible(false);
                                new Home().setVisible(true);
                            }
                            else{
                                JOptionPane.showMessageDialog(getContentPane(), "Incorrect Password");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(getContentPane(), "Username or email does not exist");
                        }
                    }
   
                }catch(Exception e){
                    JOptionPane.showMessageDialog(getContentPane(), e);
                }
            }
        }
    }//GEN-LAST:event_loginButtonMouseClicked

    private void loginButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseEntered
        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_loginButtonMouseEntered

    private void loginButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseExited
        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_loginButtonMouseExited

    private void loginLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseClicked
        String usernameEmail = usernameEmailField.getText();
        String password = passwordField.getText();

        if(usernameEmail.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Username is still empty.");
        }
        else if(password.trim().isEmpty()){
            JOptionPane.showMessageDialog(getContentPane(), "Password is still empty.");
        }
        else{
            if(!(validatePassword(password))){
                JOptionPane.showMessageDialog(getContentPane(), "Password must have 8 characters with at least one number and one character");
            }

            if(validatePassword(password)){
                String passwordConfirmation ="";
                int id = 0;
                try{
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = st.executeQuery("select * from user where username='" + usernameEmail + "'");
                    if(rs.first()){
                        id = rs.getInt(1);
                        passwordConfirmation = rs.getString(4);
                        
                        if(passwordConfirmation.equals(password)){
                            setVisible(false);
                            new Home().setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(getContentPane(), "Incorrect Password");
                        }
                    }
                    else{
                        ResultSet rs1 = st.executeQuery("select * from user where email='" + usernameEmail + "'");
                        if(rs1.first()){
                            id = rs1.getInt(1);
                            passwordConfirmation = rs1.getString(4);

                            if(passwordConfirmation.equals(password)){
                                setVisible(false);
                                new Home().setVisible(true);
                            }
                            else{
                                JOptionPane.showMessageDialog(getContentPane(), "Incorrect Password");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(getContentPane(), "Username or email does not exist");
                        }
                    }
   
                }catch(Exception e){
                    JOptionPane.showMessageDialog(getContentPane(), e);
                }
            }
        }
    }//GEN-LAST:event_loginLabelMouseClicked

    private void loginLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseEntered
        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button2.png")));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_loginLabelMouseEntered

    private void loginLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginLabelMouseExited
        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button1.png")));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_loginLabelMouseExited

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

    private void jSeparator1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator1MouseExited
        registerLabel.setForeground(new Color(179,119,50));
        jSeparator1.setForeground(new Color(179,119,50));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        jSeparator1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jSeparator1MouseExited

    private void jSeparator1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator1MouseEntered
        registerLabel.setForeground(new Color(135,86,31));
        jSeparator1.setForeground(new Color(135,86,31));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jSeparator1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jSeparator1MouseEntered

    private void jSeparator1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSeparator1MouseClicked
        setVisible(false);
        new SignUpPage().setVisible(true);
    }//GEN-LAST:event_jSeparator1MouseClicked
    
    private void usernameEmailFieldFocusLost(java.awt.event.FocusEvent evt) {                                   
        String text = usernameEmailField.getText();
        if(text.trim().isEmpty()){
            usernameEmailLabel.setForeground(new Color(251,76,76));
            usernameEmailField.setForeground(new Color(251,76,76));
        }
        else{
            usernameEmailLabel.setForeground(new Color(133,129,119));
            usernameEmailField.setForeground(new Color(130,130,130));
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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
    
    
    private App.RoundJTextField usernameEmailField;
    private App.RoundJPasswordField passwordField;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hidePassword;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JLabel showPassword;
    private javax.swing.JLabel usernameEmailLabel;
    // End of variables declaration//GEN-END:variables
}
