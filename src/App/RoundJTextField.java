package App;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JTextField;

public class RoundJTextField extends JTextField {
    private Shape shape;
    private final Insets insets;

    public RoundJTextField(int size) {
        super(size);
        setOpaque(false); 
        // Define the insets (top, left, bottom, right)
        insets = new Insets(11, 12, 11, 12); // Adjust these values as needed
    }
    
    //paint the component, making sure it has backgroundcolor
    @Override
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
         super.paintComponent(g);
    }
    
    //paint the border, making it rounded
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke(); // Get the original stroke
        
        g2.setStroke(new BasicStroke(1)); // Set the thickness of the border
        g.setColor(getForeground());
        g.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 15, 15); // Draw the round rect for the border
        
        g2.setStroke(oldStroke); // Restore the original stroke
    }
    
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }

    @Override
    public Insets getInsets() {
        return insets;
    }
}
