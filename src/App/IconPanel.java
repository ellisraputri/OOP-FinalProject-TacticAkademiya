package App;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;


public class IconPanel extends ClonePanel{
    private String name;       //icon name
    private BufferedImage image;    //icon image
    private int index;  //icon index
    private String path;    //image path
    
    public IconPanel(String name, BufferedImage image, int index, String path){
        this.name = name;
        this.image = image;
        this.index = index;
        this.path = path;
    }
    
    //get icon name
    public String getName() {
        return name;
    }

    //get icon path
    public String getPath() {
        return path;
    }
    
    //get image
    public BufferedImage getImage() {
        return image;
    }
    
    
    //setting for mouse
    public void settingMouse(SettingProfile settingprofile){    
        //set cursor
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                charName.setVisible(true);      //show icon name
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                charName.setVisible(false);     //hide icon name
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if(clicked == false){
                    clicked = true;
                    checkmark.setVisible(true);
                    
                    //set up the profile image in the SettingProfile
                    settingprofile.lastClicked = index;
                    BufferedImage circledImage = settingprofile.cropIntoCircle(image);
                    settingprofile.setProfileImage(circledImage);
                }
                else{
                    clicked = false;
                    checkmark.setVisible(false);
                    
                    //set the profile image to default
                    settingprofile.setProfileImage("null");
                    settingprofile.lastClicked = -1;
                }
            }

        }); 
    }
    
    

    
}
