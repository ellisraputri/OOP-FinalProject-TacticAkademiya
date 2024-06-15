/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import App.ImageLoader;
import DatabaseConnection.ConnectionProvider;
import jaco.mp3.player.MP3Player;
import java.awt.Point;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;

/**
 *
 * @author asus
 */
public class ListCharacter extends javax.swing.JFrame {
    private int userId;
    private JPanel cloneablePanel;
    private ArrayList<CharacterPanel> panelList = new ArrayList<>();
    private MP3Player bgmPlayer;
    
    /**
     * Creates new form ListCharacter
     */
    public ListCharacter() {
        initComponents();
        setTitle("Character Listing");
        setResizable(false);
        setLocationRelativeTo(null);
        myinit();
    }
    
    public ListCharacter(int userId, MP3Player bgmPlayer) {
        initComponents();
        this.bgmPlayer = bgmPlayer;
        this.userId = userId;
        setTitle("Character Listing");
        setResizable(false);
        myinit();
    }
    
    private void myinit(){ 
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/App/image/mouse.png").getImage(), new Point(0,0),"custom cursor"));

        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        
        cloneablePanel = new JPanel(); // The initial panel inside scroll pane
        cloneablePanel.setLayout(null); // Use absolute layout
        cloneablePanel.setPreferredSize(new Dimension(400, 200)); // Set initial size
        cloneablePanel.setBounds(80, 200, 1200, 1500); // Set bounds for the initial panel
        cloneablePanel.setOpaque(false);
        cloneablePanel.setBackground(new Color(0,0,0,0));
        cloneablePanel.setBorder(null);
        scroll.setViewportView(cloneablePanel); // Set this panel as viewport's view
        
        App.ImageLoader loader1 = new App.ImageLoader();
        ArrayList<BufferedImage> imageList = loader1.loadImagesFromFolder("src/App/image/CharacterCard/NotZoom");
        ArrayList<String> nameList = loader1.returnFileNames();
        
        App.ImageLoader loader2 = new App.ImageLoader();
        ArrayList<BufferedImage> imageZoomList = loader2.loadImagesFromFolder("src/App/image/CharacterCard/Zoom");        
        
        
        int row=0, column=0;
        for(int i=0; i<imageList.size();i++){
            BufferedImage image = imageList.get(i);
            String charName = nameList.get(i);
            BufferedImage imageHover = imageZoomList.get(i);
            
            int panelWidth = 150;
            int panelHeight = 150;
            
            CharacterPanel clonedPanel = new CharacterPanel(charName);
            clonedPanel.settingPanel(image, charName, panelWidth, panelHeight, 16, true);
            clonedPanel.settingMouse(image, imageHover);
            

            // Calculate the row and column indices
            row = i / 4;
            column = i % 4;

            // Calculate the x and y positions based on row and column indices
            int x = 10 + column * (panelWidth + 40);
            int y = 10 + row * (panelHeight + 50);

            // Set the bounds for the cloned panel with your custom size
            clonedPanel.setBounds(x, y, panelWidth, panelHeight);
            
            // Add the cloned panel to the initial panel
            cloneablePanel.add(clonedPanel);
            // Adjust preferred size of initial panel to include new panel
            Dimension newSize = new Dimension(cloneablePanel.getWidth(), y + panelHeight + 10); // Adjusted size
            cloneablePanel.setPreferredSize(newSize);
            // Ensure the scroll pane updates its viewport
            scroll.revalidate();
            scroll.repaint();
            // Scroll to show the new panel
            scroll.getVerticalScrollBar().setValue(0);
            
            panelList.add(clonedPanel);
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
        nextLabel = new javax.swing.JLabel();
        nextButton = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

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
        getContentPane().add(nextLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 640, -1, -1));

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
        getContentPane().add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 630, -1, -1));

        scroll.setBackground(new java.awt.Color(255, 255, 255));
        scroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scroll.setForeground(new java.awt.Color(255, 255, 255));
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getContentPane().add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 780, 370));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_listchar.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to go back? You changes will not be saved.", "SELECT", JOptionPane.YES_NO_OPTION);
        if(option == JOptionPane.YES_OPTION){
            setVisible(false);
            dispose();
            bgmPlayer.stop();
            new WelcomePage().setVisible(true);
        }
        
        try{
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement("delete from user where id=" + userId);
            ps.execute();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back2.png")));
        jLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/back1.png")));
        jLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel3MouseExited

    private void nextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseClicked
        boolean[] clickedArray = new boolean[panelList.size()];
        int i=0;
        for(CharacterPanel panel : panelList){
            clickedArray[i] = panel.getClicked();
            i++;
        }
        
        if(clickedArray.length<8){
            JOptionPane.showMessageDialog(getContentPane(), "Characters must be at least 8.");
        }
        else{
            try{
                Connection con = ConnectionProvider.getCon();
                String str = "insert into characters values(" + userId;
                for(int j=0; j<clickedArray.length; j++){
                    str = str + "," + clickedArray[j];
                } 
                str += ")";

                PreparedStatement ps = con.prepareStatement(str);
                ps.executeUpdate();

                setVisible(false);
                dispose();
                new Home(userId, bgmPlayer).setVisible(true);

            }catch(Exception e){
                JOptionPane.showMessageDialog(getContentPane(), e);
            }
        }
        
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
        boolean[] clickedArray = new boolean[panelList.size()];
        int i=0;
        for(CharacterPanel panel : panelList){
            clickedArray[i] = panel.getClicked();
            i++;
        }
        
        if(clickedArray.length<8){
            JOptionPane.showMessageDialog(getContentPane(), "Characters must be at least 8.");
        }
        else{
            try{
                Connection con = ConnectionProvider.getCon();
                String str = "insert into characters values(" + userId;
                for(int j=0; j<clickedArray.length; j++){
                    str = str + "," + clickedArray[j];
                } 
                str += ")";

                PreparedStatement ps = con.prepareStatement(str);
                ps.executeUpdate();

                setVisible(false);
                dispose();
                new Home(userId, bgmPlayer).setVisible(true);

            }catch(Exception e){
                JOptionPane.showMessageDialog(getContentPane(), e);
            }
        }
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
            java.util.logging.Logger.getLogger(ListCharacter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListCharacter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListCharacter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListCharacter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListCharacter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel nextButton;
    private javax.swing.JLabel nextLabel;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
