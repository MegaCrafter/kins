package me.megacrafter7368.kinseditor.swingutils;

import me.megacrafter7368.kinseditor.Window;

import javax.swing.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        super(null);

        KButton btn = new KButton("Ame ni");

        JList<String> list = new JList<>();

        btn.setBounds(30, 40, 150, 100);
        btn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(Window.getHandle());
        });

        add(btn);
    }

}
