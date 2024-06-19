package App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WrappedLabelCenter extends JLabel {
    private int maxWidth;
    private Color bgColor;
    private Insets insets;
    private int horizontalAlignment;
    
    public WrappedLabelCenter(){
        super();
    }
    
    public WrappedLabelCenter(int maxWidth, Color bgColor, Insets insets) {
        this.maxWidth = maxWidth;
        this.bgColor = bgColor != null ? bgColor : new Color(0, 0, 0, 0); // default to transparent if null
        this.insets = insets != null ? insets : new Insets(0, 0, 0, 0); // default to no insets if null
        this.horizontalAlignment = SwingConstants.LEFT;
        setOpaque(false);
    }
    
    //set text for the label
    @Override
    public void setText(String text) {
        super.setText(text);
        revalidate();
        repaint();
    }

    //paint label
    @Override
    protected void paintComponent(Graphics g) {
        // Custom background handling
        if (isOpaque()) {
            g.setColor(bgColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            // Ensure transparent background
            g.setColor(new Color(0, 0, 0, 0));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        
        // Check if the text is HTML
        if (getText() != null && getText().startsWith("<html>")) {
            super.paintComponent(g);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(getForeground());
            g2d.setFont(getFont());

            String text = getText();
            FontMetrics fm = g.getFontMetrics();

            int lineHeight = fm.getHeight();
            int y = insets.top + fm.getAscent();
            int componentWidth = getWidth() - insets.left - insets.right;

            for (String line : getWrappedLines(text, fm)) {
                int x;
                
                //set the label with the horizontal alignment as consideration
                switch (getHorizontalAlignment()) {
                    case SwingConstants.CENTER:
                        x = (componentWidth - fm.stringWidth(line)) / 2;
                        break;
                    case SwingConstants.RIGHT:
                        x = componentWidth - fm.stringWidth(line);
                        break;
                    case SwingConstants.LEFT:
                    default:
                        x = 0;
                        break;
                }
                g2d.drawString(line, insets.left + x, y);
                y += lineHeight;
            }
        }
        
    }

    //make the text to be wrap text when exceeding the max width
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

    
    //adjusting the preferred size according to maxwidth and line height
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

        return new Dimension(maxWidth + insets.left + insets.right, totalHeight + insets.top + insets.bottom);
    }

    //get label insets
    @Override
    public Insets getInsets() {
        return insets;
    }

    //set label background color
    public void setBackgroundColor(Color backgroundColor) {
        this.bgColor = backgroundColor;
        repaint();
    }

    //get label background color
    public Color getBackgroundColor() {
        return bgColor;
    }

    //set label insets
    public void setInsets(Insets insets) {
        this.insets = insets;
        revalidate();
        repaint();
    }
}
