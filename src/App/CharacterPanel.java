/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
public class CharacterPanel extends JPanel{
    private boolean over;
    private boolean clicked = false;
    
    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }
    
    public CharacterPanel(BufferedImage image, BufferedImage imageHover, String characterName, int imageWidth, int imageHeight){
        setOpaque(false);
        setLayout(null);
        
        //setting up the character image
        JLabel charImage = new JLabel();
        charImage.setIcon(new ImageIcon(image));
        charImage.setBounds(0,0,imageWidth, imageHeight);
        add(charImage);
      
        //setting up the checkmark
        JLabel checkmark = new JLabel();
        checkmark.setIcon(new ImageIcon("src/App/image/checkmark.png"));
        checkmark.setBounds(imageWidth-checkmark.getPreferredSize().width,0, checkmark.getPreferredSize().width, checkmark.getPreferredSize().height);
        add(checkmark);
        checkmark.setVisible(false);
        
        //setting up the name of character
        JLabel charName = new JLabel();
        charName.setText(characterName);
        charName.setFont(new Font("HYWenHei-85W", Font.PLAIN, 14));
        charName.setBackground(new Color(228,220,209));
        charName.setForeground(new Color(66,72,86));
        charName.setOpaque(true);
        charName.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        charName.setVerticalAlignment(SwingConstants.CENTER);   // Center text vertically
        Dimension size = charName.getPreferredSize();
        charName.setBounds(0, imageHeight-size.height-10, size.width+20, size.height+10);
        add(charName);
        charName.setVisible(false);
        
        setComponentZOrder(charImage, 1);
        setComponentZOrder(checkmark, 0);
        setComponentZOrder(charName, 0);
        
        
        
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                charImage.setIcon(new ImageIcon(imageHover));
                charName.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                charImage.setIcon(new ImageIcon(image));
                charName.setVisible(false);
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

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    charImage.setIcon(new ImageIcon(imageHover));
                } else {
                    charImage.setIcon(new ImageIcon(image));
                }
            }
        });
    }
}
