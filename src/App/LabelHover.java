package App;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

class LabelHover extends JLabel {
    public boolean over;
    public boolean clicked;
    private JLabel clickedCircle;
    private JLabel clickhoverCircle;
    private JLabel hoverCircle;

    public LabelHover(boolean over1, JLabel clickedCircle, JLabel clickhoverCircle, JLabel hoverCircle) {
        this.over = over1;
        this.clickedCircle = clickedCircle;
        this.clickhoverCircle = clickhoverCircle;
        this.hoverCircle = hoverCircle;
        
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

    public boolean isOver() {
        return over;
    }

    public JLabel getClickedCircle() {
        return clickedCircle;
    }

    public JLabel getClickhoverCircle() {
        return clickhoverCircle;
    }

    public JLabel getHoverCircle() {
        return hoverCircle;
    }
    
    public void setLabelIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getIconWidth() == -1) {
            System.out.println("Failed to load icon from path: " + path);
        } else {
            setIcon(icon);
        }
    }
}
