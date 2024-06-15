/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import DatabaseConnection.ConnectionProvider;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author asus
 */
public class Cropping extends javax.swing.JFrame {
    private File fileInput;
    private BufferedImage resizedImage;
    private SettingProfile settingProfile;
    private int userId;
    private static int profileCount;
    /**
     * Creates new form Cropping
     */
    public Cropping() {
        initComponents();
    }
    
    public Cropping(File f, SettingProfile settingProfile, int userId){
        initComponents();
        this.fileInput =f;
        this.settingProfile = settingProfile;
        this.userId = userId;
        
        Random rand = new Random();
        profileCount=rand.nextInt();
        setLocationRelativeTo(null);
        myinit();
    }
    
    private void myinit(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Custom close operation logic
                int option = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to end editing? Your changes will not be saved.", null, JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    dispose();
                    SettingProfile.openCrop = false;
                } 
            }
        });
        
        resizeImageAndPanel();
        setOverlayPanel();
        showCropper();
    }
    
    private void setOverlayPanel() {
        overlayPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Fill the background with a semi-transparent black color
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(0, 0, getWidth(), getHeight());

                // Create a hole where the movablePanel is
                g.setColor(new Color(0, 0, 0, 0));
                g.fillRect(movablePanel.getX(), movablePanel.getY(), movablePanel.getWidth(), movablePanel.getHeight());
            }
        };
        parentPanel.add(overlayPanel);
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(0, 0, parentPanel.getWidth(), parentPanel.getHeight());
        parentPanel.setComponentZOrder(overlayPanel, 0);
    }

    private void showCropper() {
        // Create the panel to be moved
        movablePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Ensure the interior is transparent
                g.setColor(new Color(255, 255, 255, 40)); // Brighter color with some transparency
                g.fillRect(0, 0, getWidth(), getHeight());

                // Draw the border
                g.setColor(Color.WHITE);
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };

        movablePanel.setOpaque(false);
        movablePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
        movablePanel.setBounds(50, 50, 200, 200); // Initial position and size

        // Add mouse listeners to the panel
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Calculate new position of the panel
                int newX = movablePanel.getX() + e.getX() - initialClick.x;
                int newY = movablePanel.getY() + e.getY() - initialClick.y;

                // Constrain panel within frame bounds
                newX = Math.max(0, Math.min(parentPanel.getWidth() - movablePanel.getWidth(), newX));
                newY = Math.max(0, Math.min(parentPanel.getHeight() - movablePanel.getHeight(), newY));

                // Set the new position
                movablePanel.setLocation(newX, newY);
            }
        };
        movablePanel.addMouseListener(mouseAdapter);
        movablePanel.addMouseMotionListener(mouseAdapter);

        addResizeHandles(movablePanel);

        // Add the panel to the parent panel
        overlayPanel.add(movablePanel);
        overlayPanel.setComponentZOrder(movablePanel, 0);
    }
    
    private void resizeImageAndPanel(){
        try {
            // Load the image
            BufferedImage image = ImageIO.read(fileInput);
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int MAX_WIDTH = 1000;
            int MAX_HEIGHT =550;
            
            if (imageWidth > MAX_WIDTH || imageHeight > MAX_HEIGHT) {
                // Calculate the new dimensions while retaining the aspect ratio
                float aspectRatio = (float) imageWidth / imageHeight;
                int targetWidth = MAX_WIDTH;
                int targetHeight = MAX_HEIGHT;

                if (imageWidth > imageHeight) {
                    targetHeight = Math.round(MAX_WIDTH / aspectRatio);
                    if (targetHeight > MAX_HEIGHT) {
                        targetWidth = Math.round(MAX_HEIGHT * aspectRatio);
                        targetHeight = MAX_HEIGHT;
                    }
                } else {
                    targetWidth = Math.round(MAX_HEIGHT * aspectRatio);
                    if (targetWidth > MAX_WIDTH) {
                        targetHeight = Math.round(MAX_WIDTH / aspectRatio);
                        targetWidth = MAX_WIDTH;
                    }
                }

                // Resize the image
                resizedImage = resizeImage(image, targetWidth, targetHeight);
                imageLabel.setIcon(new ImageIcon(resizedImage));
                imageLabel.setPreferredSize(new Dimension(targetWidth, targetHeight));
            }
            else {
                // No resizing needed
                resizedImage = image;
                imageLabel.setIcon(new ImageIcon(image));
                imageLabel.setPreferredSize(new Dimension(imageWidth, imageHeight));
            }
            
            // set bounds of the image label
            imageLabel.setBounds(0,0, imageLabel.getPreferredSize().width, imageLabel.getPreferredSize().height);

            // Add the label to the frame
            parentPanel.add(imageLabel);
            parentPanel.setPreferredSize(new Dimension(imageLabel.getWidth(), imageLabel.getHeight()));
            int parentX = (1280-imageLabel.getWidth())/2;
            int parentY = (660-imageLabel.getHeight())/2;
            parentPanel.setBounds(parentX, parentY, parentPanel.getPreferredSize().width, parentPanel.getPreferredSize().height);
            
            //adjust position of the save button and save label
            int saveButtonx = parentPanel.getX()+parentPanel.getPreferredSize().width-saveButton.getPreferredSize().width;
            saveButton.setBounds(saveButtonx, saveButton.getY(), saveButton.getPreferredSize().width, saveButton.getPreferredSize().height);
            saveLabel.setBounds(saveButton.getX()+90, saveButton.getY()+10, saveLabel.getPreferredSize().width, saveLabel.getPreferredSize().height);

            // Adjust the frame size and layout
            pack();
            
            //repaint 
            parentPanel.repaint();
            parentPanel.revalidate();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }
    
    
    private void addResizeHandles(JPanel panel) {
        // Create and add resize handles
        JPanel seHandle = createResizeHandle();
        panel.add(seHandle);
        panel.setLayout(null); // Absolute positioning for resize handles
        int HANDLE_SIZE = 12;

        seHandle.setBounds(panel.getWidth() - HANDLE_SIZE, panel.getHeight() - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE);

        // Add mouse listener for resizing
        seHandle.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Calculate new size maintaining aspect ratio 1:1
                int newWidth = panel.getWidth() + e.getX();
                int newHeight = panel.getHeight() + e.getY();

                int newSize = Math.max(newWidth, newHeight); // Maintain 1:1 aspect ratio

                // Constrain size within frame bounds
                newSize = Math.min(newSize, parentPanel.getWidth() - panel.getX());
                newSize = Math.min(newSize, parentPanel.getHeight() - panel.getY());

                panel.setSize(newSize, newSize);

                // Reposition the resize handle
                seHandle.setLocation(panel.getWidth() - HANDLE_SIZE, panel.getHeight() - HANDLE_SIZE);
                panel.revalidate();
                panel.repaint();
            }
        });
    }

    private JPanel createResizeHandle() {
        JPanel handle = new JPanel();
        handle.setBackground(Color.white);
        handle.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        return handle;
    }
    
    
    private BufferedImage cropIntoCircle(BufferedImage croppedImage){
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
        
        String outputFolderPath = "src/App/image/Profile";
        try {
            // Create the output file path
            String outputImagePath = outputFolderPath + File.separator + "circular_image"+profileCount+".png";
            ImageIO.write(bi, "png", new File(outputImagePath));
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return bi;
    }
    
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parentPanel = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        saveLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cropping");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(1290, 760));
        setResizable(false);
        getContentPane().setLayout(null);

        parentPanel.setLayout(null);
        parentPanel.add(imageLabel);
        imageLabel.setBounds(424, 183, 37, 0);

        getContentPane().add(parentPanel);
        parentPanel.setBounds(140, 30, 1000, 550);

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
        saveLabel.setBounds(1000, 620, 70, 30);

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
        saveButton.setBounds(900, 610, 247, 46);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/App/image/bg_setting.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        BufferedImage cropped = resizedImage.getSubimage(movablePanel.getX(), movablePanel.getY(), movablePanel.getWidth(), movablePanel.getHeight());
        cropIntoCircle(cropped);
        
        try{
            Connection con = ConnectionProvider.getCon();
            String str = "update user set profile= 'src/App/image/Profile/circular_image"+profileCount+".png' where id='" + userId +"'";
            System.out.println(str);
            
            PreparedStatement ps = con.prepareStatement(str);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(getContentPane(), "Profile image has been saved.");
            setVisible(false);
            dispose();
            SettingProfile.openCrop = false;
            settingProfile.setProfileImage("src/App/image/Profile/circular_image"+profileCount+".png");

        }catch(Exception e){
            JOptionPane.showMessageDialog(getContentPane(), e);
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
        BufferedImage cropped = resizedImage.getSubimage(movablePanel.getX(), movablePanel.getY(), movablePanel.getWidth(), movablePanel.getHeight());
        cropIntoCircle(cropped);
        
        try{
            Connection con = ConnectionProvider.getCon();
            String str = "update user set profile= 'src/App/image/Profile/circular_image"+profileCount+".png' where id='" + userId +"'";

            PreparedStatement ps = con.prepareStatement(str);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(getContentPane(), "Profile image has been saved.");
            setVisible(false);
            dispose();
            SettingProfile.openCrop = false;
            settingProfile.setProfileImage("src/App/image/Profile/circular_image"+profileCount+".png");

        }catch(Exception e){
            JOptionPane.showMessageDialog(getContentPane(), e);
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
            java.util.logging.Logger.getLogger(Cropping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cropping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cropping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cropping.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cropping().setVisible(true);
            }
        });
    }
    
    private JPanel movablePanel;
    private JPanel overlayPanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel saveButton;
    private javax.swing.JLabel saveLabel;
    // End of variables declaration//GEN-END:variables
}
