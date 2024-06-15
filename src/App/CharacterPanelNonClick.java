/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.awt.Color;
import java.awt.Cursor;
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
public class CharacterPanelNonClick extends ClonePanel{
    private String name;
    
    public CharacterPanelNonClick(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
    @Override
    public void settingMouse(){    
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
                }
                else{
                    clicked = false;
                }
            }

            
        }); 
    }
    
    

    
}
