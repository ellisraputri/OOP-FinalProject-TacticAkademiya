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


public abstract class ClonePanel extends JPanel{
    public boolean over;        //if the panel is being hovered, then over is true
    public boolean clicked = false;     //if the panel is clicked, then clicked is true
    public JLabel charImage;        //character image
    public App.WrappedLabel charName;       //label that display character name
    public JLabel checkmark;       
    
    //is the panel is being hovered
    public boolean isOver() {
        return over;
    }

    //set the "over" attribute
    public void setOver(boolean over) {
        this.over = over;
    }
    
    //set name and checkmark
    private void settingNameAndCheckMark(String name, int imageWidth, int imageHeight, int fontSize, boolean bigCheckmark){
        //if need big checkmark, then draw big checkmark
        //otherwise, draw usual checkmark
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
    
    //setting panel with imageicon
    public void settingPanel(ImageIcon image, String name, int imageWidth, int imageHeight, int fontSize, boolean bigCheckmark){
        setOpaque(false);   //transparent background
        setLayout(null);    //null layout
        
        //setting up the character image
        charImage = new JLabel();
        charImage.setIcon(image);
        charImage.setBounds(0,0,imageWidth, imageHeight);
        add(charImage);
        
        //setting name and checkmark
        settingNameAndCheckMark(name, imageWidth, imageHeight, fontSize, bigCheckmark);
        
        //set all component z order accordingly
        setComponentZOrder(charImage, 1);
        setComponentZOrder(checkmark, 0);
        setComponentZOrder(charName, 0);
    }
    
    //setting panel with bufferedimage
    public void settingPanel(BufferedImage image, String name, int imageWidth, int imageHeight, int fontSize, boolean bigCheckmark){
        setOpaque(false);   //transparent background
        setLayout(null);    //null layout
        
        //setting up the character image
        charImage = new JLabel();
        charImage.setIcon(new ImageIcon(image));
        charImage.setBounds(0,0,imageWidth, imageHeight);
        add(charImage);
        
        //setting name and checkmark
        settingNameAndCheckMark(name, imageWidth, imageHeight, fontSize, bigCheckmark);
        
        //set components z order accordingly
        setComponentZOrder(charImage, 1);
        setComponentZOrder(checkmark, 0);
        setComponentZOrder(charName, 0);
    }
    
    //draw usual checkmark
    public void drawCheckmark(int imageWidth){
        //setting up the checkmark
        checkmark = new JLabel();
        checkmark.setIcon(new ImageIcon("src/App/image/checkmark2.png"));
        checkmark.setBounds(imageWidth-checkmark.getPreferredSize().width,0, checkmark.getPreferredSize().width, checkmark.getPreferredSize().height);
        add(checkmark);
        checkmark.setVisible(false);
    }
    
    //draw big checkmark
    public void drawBigCheckmark(int imageWidth){
        //setting up the checkmark
        checkmark = new JLabel();
        checkmark.setIcon(new ImageIcon("src/App/image/checkmark1.png"));
        checkmark.setBounds(imageWidth-checkmark.getPreferredSize().width,0, checkmark.getPreferredSize().width, checkmark.getPreferredSize().height);
        add(checkmark);
        checkmark.setVisible(false);
    }
        
   //setting mouse 
    public void settingMouse(){
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                charName.setVisible(true);      //display character name
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  //cursor become hand cursor
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                charName.setVisible(false);     //hide character name
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));   //cursor become default
            }

            @Override
            public void mousePressed(MouseEvent me) {     
                if(clicked == false){
                    clicked = true;
                    checkmark.setVisible(true);     //show checkmark
                }
                else{
                    clicked = false;
                    checkmark.setVisible(false);    //hide checkmark
                }
            }
        });
    }
    
    //is the panel clicked
    public boolean getClicked(){
        return clicked;
    }

    //set the clicked attribute
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    
    
}
