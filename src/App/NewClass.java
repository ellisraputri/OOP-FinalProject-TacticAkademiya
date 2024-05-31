/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

/**
 *
 * @author asus
 */
import javax.swing.*;
import java.awt.*;

public class NewClass {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        JPanel cardPanel = new JPanel(new CardLayout());
        
        JPanel firstPanel = new JPanel();
        firstPanel.add(new JLabel("First Panel"));
        JButton switchButton1 = new JButton("Go to Second Panel");
        firstPanel.add(switchButton1);
        
        JPanel secondPanel = new JPanel();
        secondPanel.add(new JLabel("Second Panel"));
        JButton switchButton2 = new JButton("Go to First Panel");
        secondPanel.add(switchButton2);
        
        cardPanel.add(firstPanel, "First");
        cardPanel.add(secondPanel, "Second");
        
        switchButton1.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cardPanel.getLayout());
            cl.show(cardPanel, "Second");
        });
        
        switchButton2.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cardPanel.getLayout());
            cl.show(cardPanel, "First");
        });
        
        frame.add(cardPanel);
        frame.setVisible(true);
    }
}

