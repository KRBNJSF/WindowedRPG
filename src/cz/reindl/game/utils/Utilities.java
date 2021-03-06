package cz.reindl.game.utils;

import cz.reindl.game.GameHub;
import cz.reindl.game.ui.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Utilities {

    GameHub hub;

    public Utilities(GameHub hub) {
        this.hub = hub;
    }

    public void validateFont() { //Validating custom font
        try {
            Font fontRpg;
            fontRpg = Font.createFont(Font.TRUETYPE_FONT, new File(path("font/rpgFire.ttf"))).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path("font/rpgFire.ttf"))));
        } catch (IOException | FontFormatException e) {
            System.out.println("Font not found, regular font is used");
        }
    }

    public static void printText(String text) {
        try {
            for (String s : text.split(" ")) {
                for (char c : s.toCharArray()) {
                    Thread.sleep(35);
                    System.out.print(c);
                }
                System.out.print(" ");
                Thread.sleep(60);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String imgPath(String imgSrc) {
        return "src/cz/reindl/game/ui/graphics/" + imgSrc;
    }

    public String path(String fileSrc) {
        return "src/cz/reindl/game/ui/" + fileSrc;
    }

    public void addItem(JLabel label, int x, int y, int width, int height) {
        hub.ui.window.add(label);
        label.setBounds(x, y, width, height);
    }

    public JLabel toImage(String imgSrc) {
        return new JLabel(new ImageIcon("src/cz/reindl/game/ui/graphics/" + imgSrc));
    }

    public ImageIcon imgIcon(String imgSrc) {
        return new ImageIcon(imgPath(imgSrc));
    }

    public void buttonType(int screenNum, int x, int y, int width, int height, String command) {
        JButton buttonIcon = new JButton();
        buttonIcon.setBounds(x, y, width, height);
        buttonIcon.setContentAreaFilled(true);
        buttonIcon.setFocusPainted(false); //Border around image
        buttonIcon.setBorderPainted(false); //Border around button
        buttonIcon.setBackground(Color.gray);

        buttonIcon.addActionListener(hub.actionHandler);
        buttonIcon.setActionCommand(command);

        hub.ui.panelBackground[screenNum].add(buttonIcon);
    }

}
