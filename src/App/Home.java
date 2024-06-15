/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import DatabaseConnection.ConnectionProvider;
import jaco.mp3.player.MP3Player;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class Home extends javax.swing.JFrame {
    private int userId;
    private String username;
    private String email;
    private String profilePath;
    private ImageIcon profileImage;
    private MP3Player bgmPlayer;
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        this.userId = 1;
        myinit();
    }
    
    public Home(int userId, MP3Player bgmPlayer) {
        initComponents();
        this.bgmPlayer = bgmPlayer;
        this.userId = userId;
        setTitle("Home Page");
        setResizable(false);
        setLocationRelativeTo(null);
        myinit();
    }
    
    private void myinit(){
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/App/image/mouse.png").getImage(), new Point(0,0),"custom cursor"));
        
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select * from user where id='" + userId + "'");
            if(rs.first()){
                this.username = rs.getString("username");
                this.email = rs.getString("email");
                this.profilePath = rs.getString("profile");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(getContentPane(), e);
        }
        
        usernameLabel.setText(username);
        emailLabel.setText(email);
        usernameLabel.setBounds(110, 25, usernameLabel.getPreferredSize().width+10, usernameLabel.getPreferredSize().height);
        emailLabel.setBounds(110, 60, emailLabel.getPreferredSize().width+10, emailLabel.getPreferredSize().height);
        getContentPane().setComponentZOrder(usernameLabel, 0);
        getContentPane().setComponentZOrder(emailLabel, 0);
        
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
    }
    
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
    
    public void setProfileImage(BufferedImage im){
        BufferedImage resizedImage = resizeImage(im, 70, 70);
        profileButton.setIcon(new ImageIcon(resizedImage));
        profileImage = new ImageIcon(resizedImage);
        profileButton.repaint();
        profileButton.revalidate();
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
    public void setProfileImage(String path){
        BufferedImage im = imageIconToBufferedImage(new ImageIcon(path));
        BufferedImage resizedImage = resizeImage(im, 70, 70);
        profileButton.setIcon(new ImageIcon(resizedImage));
        profileImage = new ImageIcon(resizedImage);
        profileButton.repaint();
        profileButton.revalidate();
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
    public static BufferedImage imageIconToBufferedImage(ImageIcon icon) {
        Image img = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emailLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        profileButton = new javax.swing.JLabel();
        goLabel2 = new javax.swing.JLabel();
        goLabel1 = new javax.swing.JLabel();
        goButton1 = new javax.swing.JLabel();
        goButton2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emailLabel.setFont(new java.awt.Font("Sunflower Medium", 0, 20)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(252, 236, 214));
        emailLabel.setText("Email@gmail.com");
        getContentPane().add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        usernameLabel.setFont(new java.awt.Font("Sunflower Medium", 0, 28)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(252, 236, 214));
        usernameLabel.setText("Username");
        getContentPane().add(usernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 25, -1, -1));

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
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 20, -1, -1));

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
        getContentPane().add(profileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 15, -1, -1));

        goLabel2.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        goLabel2.setForeground(new java.awt.Color(255, 255, 255));
        goLabel2.setText("Go!");
        goLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                goLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                goLabel2MouseExited(evt);
            }
        });
        getContentPane().add(goLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 575, -1, -1));

        goLabel1.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        goLabel1.setForeground(new java.awt.Color(255, 255, 255));
        goLabel1.setText("Go!");
        goLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                goLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                goLabel1MouseExited(evt);
            }
        });
        getContentPane().add(goLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 292, -1, -1));

        goButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button3.png"))); // NOI18N
        goButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goButton1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                goButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                goButton1MouseExited(evt);
            }
        });
        getContentPane().add(goButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 275, -1, -1));

        goButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button3.png"))); // NOI18N
        goButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                goButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                goButton2MouseExited(evt);
            }
        });
        getContentPane().add(goButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 558, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_home.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goButton1MouseClicked
        setVisible(false);
        dispose();
        new TeamGuide(userId, username, email, profileImage, bgmPlayer).setVisible(true);
    }//GEN-LAST:event_goButton1MouseClicked

    private void goButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goButton1MouseEntered
        goButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button4.png")));
        goButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_goButton1MouseEntered

    private void goButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goButton1MouseExited
        goButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button3.png")));
        goButton1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        goLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_goButton1MouseExited

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to log out?", "SELECT", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            setVisible(false);
            dispose();
            bgmPlayer.stop();
            new WelcomePage().setVisible(true);
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

    private void goButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goButton2MouseClicked
        setVisible(false);
        dispose();
        new CharInfoHome(userId, username, email, profileImage, bgmPlayer, true).setVisible(true);
    }//GEN-LAST:event_goButton2MouseClicked

    private void goButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goButton2MouseEntered
        goButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button4.png")));
        goButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_goButton2MouseEntered

    private void goButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goButton2MouseExited
        goButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button3.png")));
        goButton2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        goLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_goButton2MouseExited

    private void goLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goLabel2MouseClicked
        setVisible(false);
        dispose();
        new CharInfoHome(userId, username, email, profileImage, bgmPlayer, true).setVisible(true);
    }//GEN-LAST:event_goLabel2MouseClicked

    private void goLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goLabel2MouseEntered
        goButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button4.png")));
        goButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_goLabel2MouseEntered

    private void goLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goLabel2MouseExited
        goButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button3.png")));
        goButton2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        goLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_goLabel2MouseExited

    private void goLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goLabel1MouseClicked
        setVisible(false);
        dispose();
        new TeamGuide(userId, username, email, profileImage, bgmPlayer).setVisible(true);
    }//GEN-LAST:event_goLabel1MouseClicked

    private void goLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goLabel1MouseEntered
        goButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button4.png")));
        goButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_goLabel1MouseEntered

    private void goLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goLabel1MouseExited
        goButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/button3.png")));
        goButton1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        goLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_goLabel1MouseExited

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel goButton1;
    private javax.swing.JLabel goButton2;
    private javax.swing.JLabel goLabel1;
    private javax.swing.JLabel goLabel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel profileButton;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
