/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

/**
 *
 * @author asus
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class WrappedLabel extends JLabel {
    private int maxWidth;
    private Color bgColor;

    public WrappedLabel(int maxWidth, Color bgColor) {
        this.maxWidth = maxWidth;
        this.bgColor = bgColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Cast Graphics to Graphics2D for better control
        Graphics2D g2d = (Graphics2D) g;

        // Fill the background
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set the text color and font
        g2d.setColor(getForeground());
        g2d.setFont(getFont());

        // Get the text and font metrics
        String text = getText();
        FontMetrics fm = g2d.getFontMetrics();

        // Calculate the line height
        int lineHeight = fm.getHeight();

        // Get the wrapped lines
        String[] lines = getWrappedLines(text, fm);

        // Calculate the total height of all lines
        int totalHeight = lines.length * lineHeight;

        // Calculate the y-position to start drawing the lines for vertical centering
        int y = (getHeight() - totalHeight) / 2 + fm.getAscent();

        // Draw each wrapped line centered horizontally
        for (String line : lines) {
            // Calculate the x-position to draw the text for horizontal centering
            int x = (getWidth() - fm.stringWidth(line)) / 2;
            g2d.drawString(line, x, y);
            y += lineHeight;
        }
    }

    private String[] getWrappedLines(String text, FontMetrics fm) {
        StringBuilder currentLine = new StringBuilder();
        java.util.List<String> wrappedLines = new java.util.ArrayList<>();
        String[] words = text.split("\\s+");

        for (String word : words) {
            // Check if the word itself is longer than maxWidth
            if (fm.stringWidth(word) > maxWidth) {
                int start = 0;
                while (start < word.length()) {
                    int end = start + 1;
                    while (end <= word.length() && fm.stringWidth(word.substring(start, end)) <= maxWidth) {
                        end++;
                    }
                    String subword = word.substring(start, end - 1);
                    // Check if adding the subword to the current line exceeds maxWidth
                    if (currentLine.length() > 0 && fm.stringWidth(currentLine.toString() + " " + subword) > maxWidth) {
                        wrappedLines.add(currentLine.toString());
                        currentLine = new StringBuilder(subword); // Reset currentLine
                    } else {
                        // Add the subword to the current line
                        if (currentLine.length() > 0 && currentLine.charAt(currentLine.length() - 1) != ' ') {
                            currentLine.append(" ");
                        }
                        currentLine.append(subword);
                    }
                    start = end - 1;
                }
            } else {
                // Check if adding the word to the current line exceeds maxWidth
                if (currentLine.length() > 0 && fm.stringWidth(currentLine.toString() + " " + word) > maxWidth) {
                    wrappedLines.add(currentLine.toString());
                    currentLine = new StringBuilder(word);
                } else {
                    // Add the word to the current line
                    if (currentLine.length() > 0 && currentLine.charAt(currentLine.length() - 1) != ' ') {
                        currentLine.append(" ");
                    }
                    currentLine.append(word);
                }
            }
        }

        // Add the last line if there's any remaining content
        if (currentLine.length() > 0) {
            wrappedLines.add(currentLine.toString());    
        }

        return wrappedLines.toArray(new String[0]);
    }
    


    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        String text = getText();
        String[] lines = getWrappedLines(text, fm);

        int maxWidth = 0;
        for (String line : lines) {
            int lineWidth = fm.stringWidth(line);
            if (lineWidth > maxWidth) {
                maxWidth = lineWidth;
            }
        }

        int lineHeight = fm.getHeight();
        int totalHeight = lines.length * lineHeight;

        return new Dimension(maxWidth, totalHeight);
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.bgColor = backgroundColor;
        repaint();
    }

    public Color getBackgroundColor() {
        return bgColor;
    }
}

