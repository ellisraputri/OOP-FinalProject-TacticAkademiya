/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author asus
 */
public class ProfileImage {
    private JLabel profilePic;
    private JPanel parentPanel;
    
    
    
    
    public void setProfileImage(String path){
        BufferedImage im = imageIconToBufferedImage(new ImageIcon(path));
        BufferedImage resizedImage = resizeImage(im, 70, 70);
        profilePic.setIcon(new ImageIcon(resizedImage));
        profilePic.repaint();
        profilePic.revalidate();
//        getContentPane().repaint();
//        getContentPane().revalidate();
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
}
