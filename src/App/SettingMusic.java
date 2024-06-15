/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import DatabaseConnection.ConnectionProvider;
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author asus
 */
public class SettingMusic extends javax.swing.JFrame {
    private int userId;
    private ArrayList<MusicPanel> panelList = new ArrayList<>();
    private ArrayList<String> musicList = new ArrayList<>();
    private Settings setting;
    private MusicPanel nowPlaying = null;
    private MP3Player bgmPlayer;
    private MP3Player musicPlayer;

    /**
     * Creates new form Settings
     */
    public SettingMusic() {
        initComponents();
        this.userId = 1;
        myinit();
    }
    
    public SettingMusic(int userId, MP3Player bgmPlayer, Settings setting){
        initComponents();
        this.userId = userId;
        this.setting = setting;
        this.bgmPlayer = bgmPlayer;
        this.bgmPlayer.stop();
        setLocationRelativeTo(null);
        myinit();
    }
    
    private void myinit(){
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/App/image/mouse.png").getImage(), new Point(0,0),"custom cursor"));
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Custom close operation logic
                int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to end editing? Your changes will not be saved.", null, JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    dispose();
                    Settings.open = false;
                } 
            }
        });
        
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
        
        setScrollPane("src/App/audio/bgm", 10);
        checkFromDatabase();
        
        //repaint components
        parentPanel.revalidate();
        parentPanel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    SettingMusic settingmusic = (SettingMusic) SwingUtilities.getRoot(this);
    private int index=-1;
    
    private void setScrollPane(String folderpath, int prevY){
        File folder = new File(folderpath);
        File[] listOfFiles = folder.listFiles();
        
        int x=10;
        
        for(int i=1; i<listOfFiles.length+1;i++){
            index++;
            // Create a new cloned panel
            MusicPanel clonedPanel = new MusicPanel(Color.white, new Color(131,113,90), listOfFiles[i-1], settingmusic, index);
            // Set your custom width and height for the cloned panel
            int panelWidth = 550;
            int panelHeight = 55;
            
            int y;
            if(i==1){
                y=prevY;
            }
            else{
                int newY = prevY +90;
                y = newY;
                prevY = newY;
            }
            

            // Set the bounds for the cloned panel with your custom size
            clonedPanel.setBounds(x, y, panelWidth, panelHeight);
            clonedPanel.setBackground(Color.white);
            
            // Add the cloned panel to the initial panel
            parentPanel.add(clonedPanel);
            // Adjust preferred size of initial panel to include new panel
            Dimension newSize = new Dimension(parentPanel.getWidth(), y + panelHeight + 20); // Adjusted size
            parentPanel.setPreferredSize(newSize);
            // Ensure the scroll pane updates its viewport
            scrollPane.revalidate();
            scrollPane.repaint();
            // Scroll to show the new panel
            scrollPane.getVerticalScrollBar().setValue(0);
            
            panelList.add(clonedPanel);
        }
        parentPanel.revalidate();
        parentPanel.repaint();
    }
    
    private void checkFromDatabase(){
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
        
        for(MusicPanel panel: panelList){
            String name = panel.getFile().getName();
            if(musicList.contains(name)){
                panel.setClickedCheckbox(true);
            }
        }
    }
    
    public boolean checkClickedCheckboxAmount(){
        int amount=0;
        for(MusicPanel panel: panelList){
            if(panel.isClickedCheckbox()){
                amount++;
            }
        }
        
        if(amount>=5){
            return false;
        }
        return true;
    }
    
    public void checkOtherMusic(int index){
        if(nowPlaying!=null){
            nowPlaying.setClickedButton(false);
            musicPlayer.stop();
        }
        nowPlaying = panelList.get(index);
        musicPlayer = new MP3Player(nowPlaying.getFile());
        musicPlayer.play();
        musicPlayer.setRepeat(true);
    }
    
    public void stopMusicPlayer(){
        musicPlayer.stop();
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
        saveLabel.setBounds(340, 640, 70, 30);

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
        saveButton.setBounds(250, 630, 247, 46);

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);

        parentPanel.setOpaque(false);
        scrollPane.setViewportView(parentPanel);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(80, 190, 590, 410);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_settingmusic.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        MusicPanel[] musics = new MusicPanel[5];
        int track=0;
        for(MusicPanel panel: panelList){
            if(panel.isClickedCheckbox()){
                musics[track] = panel;
                System.out.println(panel.getFile().getName());
                track++;
            }
        }
        
        System.out.println(track);
        
        if(track!=5){
            JOptionPane.showMessageDialog(getContentPane(), "Please select five background music.");
        }
        else{
            try{
                Connection con = ConnectionProvider.getCon();
                String str = "update user set music1=?, music2=?, music3=?, music4=?, music5=? where id="+userId;
                PreparedStatement ps = con.prepareStatement(str);
                ps.setString(1, musics[0].getFile().getName());
                ps.setString(2, musics[1].getFile().getName());
                ps.setString(3, musics[2].getFile().getName());
                ps.setString(4, musics[3].getFile().getName());
                ps.setString(5, musics[4].getFile().getName());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(getContentPane(), "Music list has been saved.");
                if(musicPlayer!=null){
                    musicPlayer.stop();
                }
                setVisible(false);
                dispose();
                Settings.openMusic =false;
                setting.setMusicList();
                setting.updateBgmPlayer();

            }catch(Exception e){
                JOptionPane.showMessageDialog(getContentPane(), e);
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
        MusicPanel[] musics = new MusicPanel[5];
        int track=0;
        for(MusicPanel panel: panelList){
            if(panel.isClickedCheckbox()){
                musics[track] = panel;
                System.out.println(panel.getFile().getName());
                track++;
            }
        }
        
        System.out.println(track);
        
        if(track!=5){
            JOptionPane.showMessageDialog(getContentPane(), "Please select five background music.");
        }
        else{
            try{
                Connection con = ConnectionProvider.getCon();
                String str = "update user set music1=?, music2=?, music3=?, music4=?, music5=? where id="+userId;
                PreparedStatement ps = con.prepareStatement(str);
                ps.setString(1, musics[0].getFile().getName());
                ps.setString(2, musics[1].getFile().getName());
                ps.setString(3, musics[2].getFile().getName());
                ps.setString(4, musics[3].getFile().getName());
                ps.setString(5, musics[4].getFile().getName());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(getContentPane(), "Music list has been saved.");
                if(musicPlayer!=null){
                    musicPlayer.stop();
                }
                setVisible(false);
                dispose();
                Settings.openMusic =false;
                setting.setMusicList();
                setting.updateBgmPlayer();

            }catch(Exception e){
                JOptionPane.showMessageDialog(getContentPane(), e);
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
            java.util.logging.Logger.getLogger(SettingMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingMusic().setVisible(true);
            }
        });
    }
    
    private App.RoundJTextField usernameField;
    private App.RoundJTextField emailField;
    private App.RoundJPasswordField passwordField;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel saveButton;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
