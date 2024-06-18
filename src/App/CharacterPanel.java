/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author asus
 */
public class CharacterPanel extends ClonePanel{
    private String name;
    
    public CharacterPanel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
    
    public void settingMouse(BufferedImage image, BufferedImage imageHover){    
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
