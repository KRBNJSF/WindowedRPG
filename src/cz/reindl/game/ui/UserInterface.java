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
    public JPanel[] panelBackgroundImage = new JPanel[20];
    public JLabel[] labelBackground = new JLabel[20];

    public Font fontBold, fontRegular, fontRpg;
    public Font font;
    //ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png")); //File path if JAR is wanted
    ImageIcon logo = new ImageIcon("src/cz/reindl/game/ui/graphics/logo/logo.png");

    public UserInterface(GameHub gameHub) {
        this.gameHub = gameHub;

        mainField();
        gameScreen();

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
        textMessage = new JTextArea();
        textMessage.setText("Welcome");
        textMessage.setBounds(120, 540, 760, 150); //Text position relative to frame
        textMessage.setBackground(Color.blue);
        textMessage.setForeground(Color.white);
        textMessage.setEditable(false);
        textMessage.setLineWrap(true);
        textMessage.setWrapStyleWord(true);
        textMessage.setFont(fontRpg);
        window.add(textMessage);
    }

    public void gameBackground(int screenNum, String fileName) {
        panelBackgroundImage[screenNum] = new JPanel();
        panelBackgroundImage[screenNum].setBounds(120, 60, 760, 450);  //Panel position relative to frame
        panelBackgroundImage[screenNum].setBackground(Color.red); //Just to make sure it's set properly
        panelBackgroundImage[screenNum].setLayout(null);
        window.add(panelBackgroundImage[1]);

        labelBackground[1] = new JLabel();
        labelBackground[1].setBounds(0, 0, 760, 450);  //Image position relative to panel

        ImageIcon imgBackground = new ImageIcon(fileName);
        labelBackground[1].setIcon(imgBackground); //Image = label
    }

    public void gameObject(int screenNum, int x, int y, int width, int height, String fileName) {
        JLabel labelObject = new JLabel();
        labelObject.setBounds(x, y, width, height);

        ImageIcon imgObject = new ImageIcon(fileName);
        labelObject.setIcon(imgObject);

        panelBackgroundImage[screenNum].add(labelObject); //Adding Label to Panel
        panelBackgroundImage[screenNum].add(labelBackground[screenNum]); //First comes the object image then background image otherwise the background will overlap the object
    }

    public void gameScreen() {
        //#1 SCREEN
        gameBackground(1, "src/cz/reindl/game/ui/graphics/bg/forestBg.jpg");
        gameObject(1, 225, 295, 150, 180, "src/cz/reindl/game/ui/graphics/entity/Knight2.png");
        gameObject(1, 350, 330, 150, 180, "src/cz/reindl/game/ui/graphics/object/Chest.png");
        gameObject(1, 0, 270, 200, 200, "src/cz/reindl/game/ui/graphics/object/hut.png");

        //#2 SCREEN
    }

    public void validateFont() { //Validating custom font
        try {
            fontRpg = Font.createFont(Font.TRUETYPE_FONT, new File("src/cz/reindl/game/ui/font/rpgFire.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/cz/reindl/game/ui/font/rpgFire.ttf")));
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

}
