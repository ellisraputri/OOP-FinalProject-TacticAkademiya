package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewClass().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Draggable Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null); // Use absolute layout for custom positioning
        frame.setResizable(false);

        // Create the panel to be moved
        JPanel movablePanel = new JPanel();
        movablePanel.setBackground(Color.BLUE);
        movablePanel.setBounds(50, 50, 200, 200); // Initial position and size

        // Add mouse listeners to the panel
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
//                getComponentAt(initialClick);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Calculate new position of the panel
                int newX = movablePanel.getX() + e.getX() - initialClick.x;
                int newY = movablePanel.getY() + e.getY() - initialClick.y;

                // Constrain panel within frame bounds
                newX = Math.max(0, Math.min(frame.getWidth() - movablePanel.getWidth(), newX));
                newY = Math.max(0, Math.min(frame.getHeight() - movablePanel.getHeight(), newY));

                // Set the new position
                movablePanel.setLocation(newX, newY);
            }
        };
        movablePanel.addMouseListener(mouseAdapter);
        movablePanel.addMouseMotionListener(mouseAdapter);

        // Add the panel to the frame
        frame.add(movablePanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
