package App;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CharacterPanelNonClick extends ClonePanel{
    private String name;
    
    public CharacterPanelNonClick(String name){
        this.name = name;
    }
    
    //get character name
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
                charName.setVisible(true);  //display character name
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                charName.setVisible(false);     //hide character name
            }
            
            //do nothing when the panel is clicked
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
