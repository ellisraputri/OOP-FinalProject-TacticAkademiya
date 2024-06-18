package App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JLabel;

public class WrappedLabelVerticalCenter extends JLabel {
    private int maxWidth;
    private Color bgColor;
    private Insets insets;

    public WrappedLabelVerticalCenter(){
        super();
    }

    public WrappedLabelVerticalCenter(int maxWidth, Color bgColor, Insets insets) {
        this.maxWidth = maxWidth;
        this.bgColor = bgColor != null ? bgColor : new Color(0, 0, 0, 0); // default to transparent if null
        this.insets = insets != null ? insets : new Insets(0, 0, 0, 0); // default to no insets if null
        setOpaque(false);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        revalidate();
        repaint();
    }

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

            String[] lines = getWrappedLines(text, fm);
            int lineHeight = fm.getHeight();
            int totalTextHeight = lines.length * lineHeight;

            // Calculate the starting y-coordinate to center the text vertically
            int y = (getHeight() - totalTextHeight) / 2 + fm.getAscent();

            for (String line : lines) {
                g2d.drawString(line, insets.left, y);
                y += lineHeight;
            }
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

        return new Dimension(maxWidth + insets.left + insets.right, totalHeight + insets.top + insets.bottom);
    }

    @Override
    public Insets getInsets() {
        return insets;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.bgColor = backgroundColor;
        repaint();
    }

    public Color getBackgroundColor() {
        return bgColor;
    }

    public void setInsets(Insets insets) {
        this.insets = insets;
        revalidate();
        repaint();
    }
}
