package App;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

class LabelHover extends JLabel {
    private boolean over;
    private boolean clicked;
    private JLabel clickedCircle;   //circle that will appear when clicked
    private JLabel clickhoverCircle;    //circle that will appear when hovering a clicked label
    private JLabel hoverCircle;     //circle that will appear when hovering
    private String name;

    //get label name
    public String getName() {
        return name;
    }

    //constructor
    public LabelHover(boolean over1, JLabel clickedCircle, JLabel clickhoverCircle, JLabel hoverCircle, String name) {
        this.over = over1;
        this.clickedCircle = clickedCircle;
        this.clickhoverCircle = clickhoverCircle;
        this.hoverCircle = hoverCircle;
        this.name = name;
        
        //add mouse listener
        //display circle accordingly based on the mouse event
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                over = true;
                if (clicked) {
                    clickhoverCircle.setVisible(true);
                    clickedCircle.setVisible(false);
                    hoverCircle.setVisible(false);
                } else {
                    hoverCircle.setVisible(true);
                    clickhoverCircle.setVisible(false);
                    clickedCircle.setVisible(false);
                }
                
                //set cursor to hand cursor
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                over = false;
                if (clicked) {
                    clickedCircle.setVisible(true);
                    hoverCircle.setVisible(false);
                    clickhoverCircle.setVisible(false);
                } else {
                    clickedCircle.setVisible(false);
                    hoverCircle.setVisible(false);
                    clickhoverCircle.setVisible(false);
                }
                
                //set cursor to default
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (!clicked) {
                    clicked = true;
                    clickhoverCircle.setVisible(true);
                    clickedCircle.setVisible(false);
                    hoverCircle.setVisible(false);
                } else {
                    clicked = false;
                    hoverCircle.setVisible(true);
                    clickhoverCircle.setVisible(false);
                    clickedCircle.setVisible(false);
                }
            }
        });
    }

    //is the label being hovered
    public boolean isOver() {
        return over;
    }

    //is the label being clicked
    public boolean isClicked() {
        return clicked;
    }

    //get clickedCircle
    public JLabel getClickedCircle() {
        return clickedCircle;
    }

    //get clickhovercircle
    public JLabel getClickhoverCircle() {
        return clickhoverCircle;
    }

    //get hovercircle
    public JLabel getHoverCircle() {
        return hoverCircle;
    }
    
    //set the image of the label
    public void setLabelIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getIconWidth() == -1) {
            System.out.println("Failed to load icon from path: " + path);
        } else {
            setIcon(icon);
        }
    }
}
