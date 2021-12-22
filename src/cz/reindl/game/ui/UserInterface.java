package cz.reindl.game.ui;

import cz.reindl.game.GameHub;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UserInterface {

    GameHub gameHub;
    JFrame window;
    public JTextArea textMessage;
    public JPanel bgPanelImage[] = new JPanel[20];
    public JLabel bgLabelImg[] = new JLabel[20];

    public Font fontBold, fontRegular, fontRpg;
    public Font font;
    //ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png")); //File path if JAR is wanted
    ImageIcon logo = new ImageIcon("src/cz/reindl/game/ui/graphics/logo.png");

    public UserInterface(GameHub gameHub) {
        this.gameHub = gameHub;
        mainField();
        background();
        window.setVisible(true);
    }

    public void setFont(Font font) {
        this.font = font; // FIXME: 22.12.2021
    }

    public void mainField() {
        window = new JFrame("Windowed RPG");
        window.setSize(1024, 768);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        window.setResizable(false);
        window.setIconImage(logo.getImage()); //App logo settings

        validateFont();
        textMessage = new JTextArea("Welcome");
        textMessage.setBounds(162, 480, 700, 150); //Text position relative to frame
        textMessage.setBackground(Color.blue);
        textMessage.setForeground(Color.white);
        textMessage.setEditable(false);
        textMessage.setLineWrap(true);
        textMessage.setWrapStyleWord(true);
        textMessage.setFont(fontRpg);
        window.add(textMessage);
    }

    public void background() {
        bgPanelImage[1] = new JPanel();
        bgPanelImage[1].setBounds(162, 90, 700, 350);  //Panel position relative to frame
        bgPanelImage[1].setBackground(Color.red); //Just to make sure it's set properly
        bgPanelImage[1].setLayout(null);
        window.add(bgPanelImage[1]);

        bgLabelImg[1] = new JLabel();
        bgLabelImg[1].setBounds(0, 0, 700, 350);  //Image position relative to panel

        ImageIcon imgObject = new ImageIcon("src/cz/reindl/game/ui/graphics/bg/forestBg.jpg");
        bgLabelImg[1].setIcon(imgObject); //Image = label

        bgPanelImage[1].add(bgLabelImg[1]); //Adding Label to Panel

    }

    public void validateFont() { //Validating custom font
        try {
            fontRpg = Font.createFont(Font.TRUETYPE_FONT, new File("src/cz/reindl/game/ui/font/rpgFire.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/cz/reindl/game/ui/font/rpgFire.ttf")));
        } catch (IOException | FontFormatException e) {
        }
    }

}
