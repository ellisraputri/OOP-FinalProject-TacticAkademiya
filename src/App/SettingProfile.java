package App;

import DatabaseConnection.ConnectionProvider;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SettingProfile extends javax.swing.JFrame {
    private int userId;
    private Settings setting;
    private String profilePath;
    private ArrayList<IconPanel> panelList = new ArrayList<>();
    public int lastClicked=-1;

    
    public SettingProfile() {
        initComponents();
    }
    
    public SettingProfile(int userId, Settings setting){
        initComponents();
        this.userId = userId;
        this.setting = setting;
        setProfilePath();       //set profile path
        setLocationRelativeTo(null);    //set frame location
        myinit();
    }
    
    //retrieve the profile path from database
    private void setProfilePath(){
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("select * from user where id='" + userId + "'");
            if(rs.first()){
                profilePath = rs.getString(5);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(getContentPane(), e);
        }
    }
    
    
    private void myinit(){
        //set cursor image
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("src/App/image/mouse.png").getImage(), new Point(0,0),"custom cursor"));
        
        //if the frame is closed, then the program wont stop
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Custom close operation logic
                int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to end editing? Your changes will not be saved.", null, JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    dispose();
                    Settings.openProfile = false;
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
        
        //set profile image
        if(profilePath == null){
           profilePath = "src/App/image/profile1.png";
           setProfileImage(profilePath);
        }
        else if(profilePath.contains("GenshinIcons")){
            BufferedImage im = imageIconToBufferedImage(new ImageIcon(profilePath));
            im = cropIntoCircle(im);
            setProfileImage(im);
        }
        else{
            setProfileImage(profilePath);
        }
        setScrollPane();
        
        
        //repaint components
        parentPanel.revalidate();
        parentPanel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
    }
    
    //set up scroll pane
    private void setScrollPane(){
        //load images 
        App.ImageLoader loaderr = new App.ImageLoader();
        loaderr.emptyFileName();
        ArrayList<BufferedImage> imageList = loaderr.loadImagesFromFolder("src/App/image/GenshinIcons");
        ArrayList<String> nameList = new ArrayList<>();     //list that contains the name that will be displayed to user
        ArrayList<String> oldNameList = loaderr.returnFileNames();      //list that contains the actual file name
        
        for(String name: loaderr.returnFileNames()){
            String newName = name.substring(0, name.length()-1);       //edit the name 
            nameList.add(newName);
        }
        
        //show panels
        int row=0, column=0;
        for(int i=0; i<imageList.size();i++){
            BufferedImage image = imageList.get(i);
            String charName = nameList.get(i);
            String path = "src/App/image/GenshinIcons/" + oldNameList.get(i) +".png";
            
            int panelWidth = 120;
            int panelHeight = 120;
            
            //initialize panel
            IconPanel clonedPanel = new IconPanel(charName, image, i, path);
            clonedPanel.settingPanel(image, charName, panelWidth, panelHeight, 12, false);
            clonedPanel.settingMouse(settingProfile);
            
            //add mouse listener
            clonedPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    for(IconPanel p: panelList){
                        if(panelList.indexOf(p) != lastClicked && p.getClicked()){
                            p.setClicked(false);
                            p.checkmark.setVisible(false);
                        }
                    }
                }
            });
            
            //set clicked true for icons that is in profile path
            if(path.equals(profilePath)){
                clonedPanel.setClicked(true);
                clonedPanel.checkmark.setVisible(true);
            }

            // Calculate the row and column indices
            row = i / 4;
            column = i % 4;

            // Calculate the x and y positions based on row and column indices
            int x = 10 + column * (panelWidth + 20);
            int y = 10 + row * (panelHeight + 40);

            // Set the bounds for the cloned panel with your custom size
            clonedPanel.setBounds(x, y, panelWidth, panelHeight);
            
            // Add the cloned panel to the initial panel
            parentPanel.add(clonedPanel);
            // Adjust preferred size of initial panel to include new panel
            Dimension newSize = new Dimension(parentPanel.getWidth(), y + panelHeight + 10); // Adjusted size
            parentPanel.setPreferredSize(newSize);
            // Ensure the scroll pane updates its viewport
            scrollPane.revalidate();
            scrollPane.repaint();
            // Scroll to show the new panel
            scrollPane.getVerticalScrollBar().setValue(0);
            
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

        saveLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        parentPanel = new javax.swing.JPanel();
        profileCircle = new javax.swing.JLabel();
        uploadRad = new javax.swing.JRadioButton();
        genshinIconRad = new javax.swing.JRadioButton();
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
        scrollPane.setBounds(80, 300, 590, 300);

        profileCircle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/circle4.png"))); // NOI18N
        getContentPane().add(profileCircle);
        profileCircle.setBounds(90, 150, 110, 120);

        uploadRad.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        uploadRad.setForeground(new java.awt.Color(126, 119, 111));
        uploadRad.setText(" Upload from Computer");
        uploadRad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        uploadRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio7.png"))); // NOI18N
        uploadRad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                uploadRadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                uploadRadMouseExited(evt);
            }
        });
        uploadRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadRadActionPerformed(evt);
            }
        });
        getContentPane().add(uploadRad);
        uploadRad.setBounds(240, 220, 340, 35);

        genshinIconRad.setFont(new java.awt.Font("HYWenHei-85W", 0, 24)); // NOI18N
        genshinIconRad.setForeground(new java.awt.Color(126, 119, 111));
        genshinIconRad.setSelected(true);
        genshinIconRad.setText(" Genshin Icons");
        genshinIconRad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        genshinIconRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/radio5.png"))); // NOI18N
        genshinIconRad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                genshinIconRadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                genshinIconRadMouseExited(evt);
            }
        });
        genshinIconRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genshinIconRadActionPerformed(evt);
            }
        });
        getContentPane().add(genshinIconRad);
        genshinIconRad.setBounds(240, 170, 220, 35);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_settingprofile.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        if(genshinIconRad.isSelected()){
            String path="";
            //if last clicked==-1 (it means that the user not clicking any icons)
            //then, we set the default icon
            if(lastClicked==-1){
                path = "src/App/image/profile1.png";
            }
            else{
                path = panelList.get(lastClicked).getPath();
            }
            try{
                //update user database
                Connection con = ConnectionProvider.getCon();
                String str = "update user set profile= '" + path + "' where id='" + userId +"'";

                PreparedStatement ps = con.prepareStatement(str);
                ps.executeUpdate();
                
                //close frame
                JOptionPane.showMessageDialog(getContentPane(), "Profile image has been saved.");
                setVisible(false);
                dispose();
                Settings.openProfile = false;
                
                //set profile image in settings
                if(lastClicked==-1){
                    setting.setProfileImage(path);
                }
                else{
                    setting.cropIntoCircle(panelList.get(lastClicked).getImage());
                }
                

            }catch(Exception e){
                JOptionPane.showMessageDialog(getContentPane(), e);
            }
        }
        
        //if the user is using the "upload from computer"
        else{
            try{
                //retrieving info from database
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery("select * from user where id='" + userId + "'");
                if(rs.first()){
                    String path = rs.getString(5);
                    JOptionPane.showMessageDialog(getContentPane(), "Profile image has been saved.");
                    
                    //close frame and set up profile image in settings
                    setVisible(false);
                    dispose();
                    Settings.openProfile = false;
                    setting.setProfileImage(path);
                }

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
        if(genshinIconRad.isSelected()){
            String path="";
            //if last clicked==-1 (it means that the user not clicking any icons)
            //then, we set the default icon
            if(lastClicked==-1){
                path = "src/App/image/profile1.png";
            }
            else{
                path = panelList.get(lastClicked).getPath();
            }
            try{
                //update user database
                Connection con = ConnectionProvider.getCon();
                String str = "update user set profile= '" + path + "' where id='" + userId +"'";

                PreparedStatement ps = con.prepareStatement(str);
                ps.executeUpdate();
                
                //close frame
                JOptionPane.showMessageDialog(getContentPane(), "Profile image has been saved.");
                setVisible(false);
                dispose();
                Settings.openProfile = false;
                
                //set profile image in settings
                if(lastClicked==-1){
                    setting.setProfileImage(path);
                }
                else{
                    setting.cropIntoCircle(panelList.get(lastClicked).getImage());
                }
                

            }catch(Exception e){
                JOptionPane.showMessageDialog(getContentPane(), e);
            }
        }
        
        //if the user is using the "upload from computer"
        else{
            try{
                //retrieving info from database
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery("select * from user where id='" + userId + "'");
                if(rs.first()){
                    String path = rs.getString(5);
                    JOptionPane.showMessageDialog(getContentPane(), "Profile image has been saved.");
                    
                    //close frame and set up profile image in settings
                    setVisible(false);
                    dispose();
                    Settings.openProfile = false;
                    setting.setProfileImage(path);
                }

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

    private void uploadRadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadRadMouseEntered
       if(uploadRad.isSelected()){
            uploadRad.setIcon(new ImageIcon("src/App/image/radio6.png"));
        }
        else{
            uploadRad.setIcon(new ImageIcon("src/App/image/radio8.png"));
        }
    }//GEN-LAST:event_uploadRadMouseEntered

    private void uploadRadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uploadRadMouseExited
        if(uploadRad.isSelected()){
            uploadRad.setIcon(new ImageIcon("src/App/image/radio5.png"));
        }
        else{
            uploadRad.setIcon(new ImageIcon("src/App/image/radio7.png"));
        }
    }//GEN-LAST:event_uploadRadMouseExited

    private void genshinIconRadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genshinIconRadMouseEntered
        if(genshinIconRad.isSelected()){
            genshinIconRad.setIcon(new ImageIcon("src/App/image/radio6.png"));
        }
        else{
            genshinIconRad.setIcon(new ImageIcon("src/App/image/radio8.png"));
        }
    }//GEN-LAST:event_genshinIconRadMouseEntered

    private void genshinIconRadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genshinIconRadMouseExited
        if(genshinIconRad.isSelected()){
            genshinIconRad.setIcon(new ImageIcon("src/App/image/radio5.png"));
        }
        else{
            genshinIconRad.setIcon(new ImageIcon("src/App/image/radio7.png"));
        }
    }//GEN-LAST:event_genshinIconRadMouseExited

    private void uploadRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadRadActionPerformed
        genshinIconRad.setSelected(false);
        uploadRad.setSelected(true);
        uploadRad.setIcon(new ImageIcon("src/App/image/radio6.png"));
        genshinIconRad.setIcon(new ImageIcon("src/App/image/radio7.png"));
        scrollPane.setVisible(false);
        openFileChooser();
    }//GEN-LAST:event_uploadRadActionPerformed

    private void genshinIconRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genshinIconRadActionPerformed
        genshinIconRad.setSelected(true);
        uploadRad.setSelected(false);
        genshinIconRad.setIcon(new ImageIcon("src/App/image/radio6.png"));
        uploadRad.setIcon(new ImageIcon("src/App/image/radio7.png"));
        scrollPane.setVisible(true);
    }//GEN-LAST:event_genshinIconRadActionPerformed
    
    //crop images into circle
    public BufferedImage cropIntoCircle(BufferedImage croppedImage){
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
        
        return bi;
    }
    
    //set profile image with buffered image
    public void setProfileImage(BufferedImage im){
        BufferedImage resizedImage = resizeImage(im, profileCircle.getWidth(), profileCircle.getHeight());
        profileCircle.setIcon(new ImageIcon(resizedImage));
        profileCircle.repaint();
        profileCircle.revalidate();
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
    //set profile image with path
    public void setProfileImage(String path){
        //if path is null, then set default profile image
        if(path.equals("null")){
            path = "src/App/image/profile1.png";
        }
        
        BufferedImage im = imageIconToBufferedImage(new ImageIcon(path));
        BufferedImage resizedImage = resizeImage(im, profileCircle.getWidth(), profileCircle.getHeight());
        profileCircle.setIcon(new ImageIcon(resizedImage));
        
        profileCircle.repaint();
        profileCircle.revalidate();
        getContentPane().repaint();
        getContentPane().revalidate();
    }
    
    
    public static boolean openCrop = false;
    private SettingProfile settingProfile = (SettingProfile) SwingUtilities.getRoot(this);
    
    //open file chooser to choose file
    private void openFileChooser() {
        if (!openCrop) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
            chooser.setFileFilter(filter);      //can only select image files

            int returnValue = chooser.showOpenDialog(getContentPane());
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                if (f != null) {
                    //open cropping frame if the file is valid
                    new Cropping(f, settingProfile, userId).setVisible(true);
                    openCrop = true;
                }
            } 
        }         
        else {
            JOptionPane.showMessageDialog(getContentPane(), "A frame is already opened.");
        }
    }

    //change imageicon to bufferedimage
    public static BufferedImage imageIconToBufferedImage(ImageIcon icon) {
        Image img = icon.getImage();
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
    
    //resize image based on target width and height
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
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
            java.util.logging.Logger.getLogger(SettingProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingProfile().setVisible(true);
            }
        });
    }
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JRadioButton genshinIconRad;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel profileCircle;
    private javax.swing.JLabel saveButton;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JRadioButton uploadRad;
    // End of variables declaration//GEN-END:variables
}
