/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author asus
 */
public abstract class ClonePanel extends JPanel{
    public boolean over;
    public boolean clicked = false;
    public JLabel charImage;
    public App.WrappedLabel charName;
    public JLabel checkmark;
    
    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
    
    private void settingNameAndCheckMark(String name, int imageWidth, int imageHeight, int fontSize, boolean bigCheckmark){
        if(bigCheckmark){
            drawBigCheckmark(imageWidth);
        }
        else{
            drawCheckmark(imageWidth);
        }
        
        
        //setting up the name of character
        charName = new App.WrappedLabel(imageWidth, new Color(228,220,209), new Insets(5,5,5,5));
        charName.setText(name);
        charName.setFont(new Font("HYWenHei-85W", Font.PLAIN, fontSize));
        charName.setOpaque(true);
        charName.setForeground(Color.black);
        Dimension size = charName.getPreferredSize();
        charName.setBounds(0, imageHeight-size.height, size.width, size.height);
        charName.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        charName.setVerticalAlignment(SwingConstants.CENTER);   // Center text vertically
        charName.revalidate();
        charName.repaint();
        add(charName);
        charName.setVisible(false);
    }
    
    public void settingPanel(ImageIcon image, String name, int imageWidth, int imageHeight, int fontSize, boolean bigCheckmark){
        setOpaque(false);
        setLayout(null);
        
        //setting up the character image
        charImage = new JLabel();
        charImage.setIcon(image);
        charImage.setBounds(0,0,imageWidth, imageHeight);
        add(charImage);
        
        settingNameAndCheckMark(name, imageWidth, imageHeight, fontSize, bigCheckmark);
        
        setComponentZOrder(charImage, 1);
        setComponentZOrder(checkmark, 0);
        setComponentZOrder(charName, 0);
    }
    
    
    public void settingPanel(BufferedImage image, String name, int imageWidth, int imageHeight, int fontSize, boolean bigCheckmark){
        setOpaque(false);
        setLayout(null);
        
        //setting up the character image
        charImage = new JLabel();
        charImage.setIcon(new ImageIcon(image));
        charImage.setBounds(0,0,imageWidth, imageHeight);
        add(charImage);
        
        settingNameAndCheckMark(name, imageWidth, imageHeight, fontSize, bigCheckmark);
        
        setComponentZOrder(charImage, 1);
        setComponentZOrder(checkmark, 0);
        setComponentZOrder(charName, 0);
    }
    
    
    public void drawCheckmark(int imageWidth){
        //setting up the checkmark
        checkmark = new JLabel();
        checkmark.setIcon(new ImageIcon("src/App/image/checkmark2.png"));
        checkmark.setBounds(imageWidth-checkmark.getPreferredSize().width,0, checkmark.getPreferredSize().width, checkmark.getPreferredSize().height);
        add(checkmark);
        checkmark.setVisible(false);
    }
    
    public void drawBigCheckmark(int imageWidth){
        //setting up the checkmark
        checkmark = new JLabel();
        checkmark.setIcon(new ImageIcon("src/App/image/checkmark1.png"));
        checkmark.setBounds(imageWidth-checkmark.getPreferredSize().width,0, checkmark.getPreferredSize().width, checkmark.getPreferredSize().height);
        add(checkmark);
        checkmark.setVisible(false);
    }
        
   
    public void settingMouse(){
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                charName.setVisible(true);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                charName.setVisible(false);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent me) {     
                if(clicked == false){
                    clicked = true;
                    checkmark.setVisible(true);
                }
                else{
                    clicked = false;
                    checkmark.setVisible(false);
                }
            }
        });
    }
    
    public boolean getClicked(){
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
    
}
