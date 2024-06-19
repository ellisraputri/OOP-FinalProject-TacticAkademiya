package App;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;


public class CharacterPanel extends ClonePanel{
    private String name;
    
    public CharacterPanel(String name){
        this.name = name;
    }
    
    //get character name
    public String getName() {
        return name;
    }
    
    
    public void settingMouse(BufferedImage image, BufferedImage imageHover){    
        //  add mouse listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                charImage.setIcon(new ImageIcon(imageHover));   //diplay the zoom image
                charName.setVisible(true);          //display character name
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                charImage.setIcon(new ImageIcon(image));    //display default image
                charName.setVisible(false);     //hide character name
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if(clicked == false){
                    clicked = true;
                    checkmark.setVisible(true);     //display checkmark
                }
                else{
                    clicked = false;
                    checkmark.setVisible(false);    //hide checkmark
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    charImage.setIcon(new ImageIcon(imageHover));   //display zoom image
                } else {
                    charImage.setIcon(new ImageIcon(image));        //display default image
                }
            }
        }); 
    }
    
    

    
}
