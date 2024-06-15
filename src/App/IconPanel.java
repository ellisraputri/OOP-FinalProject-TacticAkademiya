/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author asus
 */
public class IconPanel extends ClonePanel{
    private String name;
    private BufferedImage image;
    private int index;
    private String path;
    
    public IconPanel(String name, BufferedImage image, int index, String path){
        this.name = name;
        this.image = image;
        this.index = index;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    
    
    public void settingMouse(SettingProfile settingprofile){    
        //set cursor
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                charName.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                charName.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if(clicked == false){
                    clicked = true;
                    checkmark.setVisible(true);
                    settingprofile.lastClicked = index;
                    BufferedImage circledImage = settingprofile.cropIntoCircle(image);
                    settingprofile.setProfileImage(circledImage);
                    
                }
                else{
                    clicked = false;
                    checkmark.setVisible(false);
                    settingprofile.setProfileImage("null");
                    settingprofile.lastClicked = -1;
                }
            }

        }); 
    }
    
    

    
}
