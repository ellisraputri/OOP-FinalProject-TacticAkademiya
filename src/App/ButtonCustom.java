package App;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class ButtonCustom extends JButton {

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColorNotOver() {
        return borderColorNotOver;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Color getColorOver2() {
        return colorOver2;
    }

    public void setColorOver2(Color colorOver2) {
        this.colorOver2 = colorOver2;
    }

    public Color getColorClick2() {
        return colorClick2;
    }

    public void setColorClick2(Color colorClick2) {
        this.colorClick2 = colorClick2;
    }

    public Color getBorderColorOver() {
        return borderColorOver;
    }

    public void setBorderColorOver(Color borderColorOver) {
        this.borderColorOver = borderColorOver;
    }

    public void setBorderColorNotOver(Color borderColor) {
        this.borderColorNotOver = borderColor;
    }
    
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    
    public ButtonCustom() {
        //  Init Color
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setColor(null);
        colorOver = null;
        colorClick = null;
        borderColorNotOver = null;
        color2 = null;
        colorOver2 = null;
        colorClick2 = null;
        borderColorOver = null;
        setContentAreaFilled(false);
        
        
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                setForeground(colorOver2);
                setBorderColor(borderColorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                setForeground(color2);
                setBorderColor(borderColorNotOver);
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
                setForeground(colorClick2);
                setBorderColor(borderColorOver);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(colorOver);
                    setForeground(colorOver2);
                    setBorderColor(borderColorOver);
                } else {
                    setBackground(color);
                    setForeground(color2);
                    setBorderColor(borderColorNotOver);
                }
            }
        });
    }

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color color2;
    private Color colorOver2;
    private Color colorClick2;
    private Color borderColorOver;
    private Color borderColorNotOver;
    private Color borderColor;    
    private int radius = 0;

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        //  Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}
