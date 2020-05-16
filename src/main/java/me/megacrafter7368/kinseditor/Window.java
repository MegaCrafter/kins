package me.megacrafter7368.kinseditor;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {
    private static final String TITLE = "Kins Editor";
    public static final int ABSWIDTH = 1024, ABSHEIGHT = 576;
    public static final int WIDTH = ABSWIDTH - 15, HEIGHT = ABSHEIGHT - 38;

    private static JFrame HNDWND = new JFrame();

    public static JFrame getHandle() {
        return HNDWND;
    }

    public static String getTitle() {
        return TITLE;
    }

    static void addPanel(JPanel panel) {
        panel.setBounds(0, 0, WIDTH, HEIGHT);
        getHandle().add(panel);
    }

    static void init() {
        HNDWND = new JFrame(TITLE);
        getHandle().setSize(ABSWIDTH, ABSHEIGHT);
        getHandle().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        getHandle().setLayout(null);
        getHandle().setLocationRelativeTo(null);
        getHandle().setResizable(false);

        getHandle().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO: Kapanırken eklenecekler...
                System.exit(-5);
            }
        });
    }

    static void start() {
        getHandle().setVisible(true);
    }
}
