package me.megacrafter7368.kinseditor;

import me.megacrafter7368.kinseditor.swingutils.MainPanel;

import javax.swing.*;

public class Core {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Window.init();

        Window.addPanel(new MainPanel());

        Window.start();
    }
}
