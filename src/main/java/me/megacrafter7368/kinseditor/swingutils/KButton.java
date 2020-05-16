package me.megacrafter7368.kinseditor.swingutils;

import javax.swing.*;
import java.awt.*;

public class KButton extends JButton {

    public KButton(String text) {
        super(text);

        setFocusPainted(false);
        setBorderPainted(false);
    }

    public KButton font(String fontName, int style, int size) {
        setFont(new Font(fontName, style, size));
        return this;
    }

    public KButton fontName(String fontName) {
        setFont(new Font(fontName, getFont().getStyle(), getFont().getSize()));
        return this;
    }

    public KButton style(int style) {
        setFont(getFont().deriveFont(style));
        return this;
    }

    public KButton fontsize(float size) {
        setFont(getFont().deriveFont(size));
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
