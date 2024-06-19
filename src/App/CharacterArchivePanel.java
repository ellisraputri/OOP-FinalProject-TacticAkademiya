package App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;


public class CharacterArchivePanel extends JPanel{ 
    private static int panelCount = 0; 
    private static int borderRadius;
    private Color bgColor;
    private static int borderWidth;
    private ImageIcon image;
    private Color textColor;
    private String path;
    private Color bgHover;
    private Color bgDefault;
    private GameCharacter gameChar;

    public CharacterArchivePanel(int borderRadius, ImageIcon image, GameCharacter gc) {
        setLayout(null);    //set null layout
        this.borderRadius = borderRadius;
        this.borderWidth = 0;
        this.image = image;
        this.gameChar = gc;
        setOpaque(false);
        
        //set basic attributes
        String name = gameChar.getName();
        String element = gameChar.getElement();
        int stars = gameChar.getStars();
       
        //set color based on rarity
        if(stars == 4){
            bgColor = new Color(202,204,246);
            bgDefault = new Color(202,204,246);
            textColor = new Color(70,73,140);
            path ="src/App/image/circle3.png";
            bgHover = new Color(160,163,231);
        }
        else{
            bgColor = new Color (246,205,202);
            bgDefault = new Color(246,205,202);
            textColor = new Color(119,49,39);
            path = "src/App/image/circle2.png";
            bgHover = new Color(225,159,156);
        }
        
                
        // set name label
        App.WrappedLabelCenter nameLabel = new App.WrappedLabelCenter(140, textColor, new Insets(2,2,2,2));
        nameLabel.setText(name);
        nameLabel.setFont(new Font("HYWenHei-85W", 0, 18));
        nameLabel.setForeground(textColor);
        setComponentBounds(nameLabel, 25, 185, 150, 150);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(nameLabel);
        
        //set element label
        JLabel elementLabel = new JLabel();
        elementLabel.setIcon(new ImageIcon("src/App/image/Element/Medium/"+element+".png"));
        setComponentBounds(elementLabel, 133, 140, elementLabel.getPreferredSize().width, elementLabel.getPreferredSize().height);
        add(elementLabel);
        
        //set element circle
        JLabel circle = new JLabel();
        circle.setIcon(new ImageIcon(path));
        setComponentBounds(circle, 130, 135, circle.getPreferredSize().width, circle.getPreferredSize().height);
        add(circle);
        
        //set character image
        JLabel photo = new JLabel();
        photo.setIcon(image);
        setComponentBounds(photo, 25, 22, 150, 150);
        add(photo);
        
        //set cursor
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        //add mouse listener that change the panel color when being hovered
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bgColor = bgHover;
                revalidate();
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                bgColor = bgDefault;
                revalidate();
                repaint();
            }
            
        });
    }
    
    //get gamecharacter
    public GameCharacter getGameChar() {
        return gameChar;
    }

    //get character image
    public ImageIcon getImage() {
        return image;
    }

    //set component position and size
    public void setComponentBounds(Component component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height); 
    }

    
    //paint the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(bgColor); // Set background color
        // Fill the area inside the border with the background color
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
        g2d.dispose();
    }

    
    //paint the panel border
    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK); // Set border color
        g2d.setStroke(new BasicStroke(borderWidth)); // Set border width
        g2d.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth, borderRadius, borderRadius); // Adjust position and size based on border width
        g2d.dispose();
    }
}

