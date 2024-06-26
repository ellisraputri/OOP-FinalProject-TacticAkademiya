package App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;

public class MusicPanel extends JPanel{ 
    private static Color bgColor;
    private static Color borderColor;
    private File file;
    private boolean clickedCheckbox = false;
    private boolean overCheckbox = false;
    private boolean clickedButton = false;
    private JLabel checkbox;
    private JLabel button;
    private SettingMusic settingmusic;
    private int index;

    public MusicPanel(Color bgColor, Color borderColor, File file, SettingMusic settingmusic, int index) {
        //set panel
        setLayout(null);
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.settingmusic = settingmusic;
        this.file = file;
        this.index = index;
        setBorder(BorderFactory.createLineBorder(borderColor, 2));
        
        //set title
        String[] filename = file.getName().split(" - ");
        String title = filename[1].replaceAll(".mp3", "");

        // set component
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new java.awt.Font("HYWenHei-85W", 0, 18)); // NOI18N
        titleLabel.setForeground(new Color(131,113,90));
        titleLabel.setText(title);
        titleLabel.setBounds(65, 0, titleLabel.getPreferredSize().width+120, 55);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(titleLabel);
        setComponentZOrder(titleLabel,0);        
        
        //set play and pause button
        button = new JLabel();
        button.setIcon(new ImageIcon("src/App/image/play2.png"));
        button.setBounds(25, 15, button.getPreferredSize().width, button.getPreferredSize().height);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(button);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMouseClicked(evt);
            }
        });
        setComponentZOrder(button,0);
        
        //set checkbox
        checkbox = new JLabel();
        checkbox.setIcon(new ImageIcon("src/App/image/Rectangle12.png"));
        checkbox.setBounds(504, 16, checkbox.getPreferredSize().width, checkbox.getPreferredSize().height);
        checkbox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkboxMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkboxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkboxMouseExited(evt);
            }
        });
        add(checkbox);
        setComponentZOrder(checkbox,0);
    }
    
    //if the button is clicked
    private void buttonMouseClicked(MouseEvent evt){
        //if clickedButton is true, then stop music player and display play button
        if(clickedButton){
            button.setIcon(new ImageIcon("src/App/image/play2.png"));
            clickedButton = !clickedButton;
            settingmusic.stopMusicPlayer();
        }
        //otherwise, play music player and display pause button
        else{
            clickedButton = !clickedButton;
            settingmusic.checkOtherMusic(index);
            button.setIcon(new ImageIcon("src/App/image/pause2.png"));
        }
    }
    
    //if the checkbox is clicked
    private void checkboxMouseClicked(MouseEvent evt){
        //if clickedCheckbox is true, then dont show checkmark
        if(clickedCheckbox){
            clickedCheckbox = !clickedCheckbox;
            checkbox.setIcon(new ImageIcon("src/App/image/Rectangle12.png"));
        }
        //otherwise, show checkmark
        else{
            boolean check = settingmusic.checkClickedCheckboxAmount();  //check for music amount
            if(check){
                clickedCheckbox = !clickedCheckbox;
                checkbox.setIcon(new ImageIcon("src/App/image/checkmark3.png"));
            }
            else{
                //if music amount is greater than 5, then show alert
                JOptionPane.showMessageDialog(settingmusic.getContentPane(), "You have already selected five music");
            }  
        }
        
    }
   
    private void checkboxMouseEntered(MouseEvent evt){
        overCheckbox = true;
        if(!clickedCheckbox){
            checkbox.setIcon(new ImageIcon("src/App/image/Rectangle12_hover.png"));
        }
    }
    
    private void checkboxMouseExited(MouseEvent evt){
        overCheckbox = false;
        if(!clickedCheckbox){
            checkbox.setIcon(new ImageIcon("src/App/image/Rectangle12.png"));
        }
    }

    public boolean isClickedCheckbox() {
        return clickedCheckbox;
    }

    public void setClickedCheckbox(boolean clickedCheckbox) {
        this.clickedCheckbox = clickedCheckbox;
        checkbox.setIcon(new ImageIcon("src/App/image/checkmark3.png"));
    }

    //get music file
    public File getFile() {
        return file;
    }
    
    public boolean isClickedButton() {
        return clickedButton;
    }

    public void setClickedButton(boolean clickedButton) {
        this.clickedButton = clickedButton;
        button.setIcon(new ImageIcon("src/App/image/play2.png"));
    }

    //paint panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(bgColor); // Set background color
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill the panel with background color
        g2d.dispose();
    }

   
}

