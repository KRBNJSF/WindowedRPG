package cz.reindl.game.ui;

import cz.reindl.game.GameHub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class UserInterface {

    GameHub gameHub;
    public static JFrame window;
    public JTextArea textMessage;
    public JPanel[] panelBackground = new JPanel[20];
    public JLabel[] labelBackground = new JLabel[20];

    public String noAction = "-";

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

        textMessage = new JTextArea();
        textMessage.setText("Welcome");
        textMessage.setBounds(120, 540, 760, 150); //Text position relative to frame
        textMessage.setBackground(Color.blue);
        textMessage.setForeground(Color.white);
        textMessage.setEditable(false);
        textMessage.setLineWrap(true);
        textMessage.setWrapStyleWord(true);
        validateFont();
        textMessage.setFont(fontRpg);
        window.add(textMessage);
    }

    public void gameBackground(int screenNum, String fileName) {
        panelBackground[screenNum] = new JPanel();
        panelBackground[screenNum].setBounds(120, 60, 760, 450);  //Panel position relative to frame
        panelBackground[screenNum].setBackground(Color.red); //Just to make sure it's set properly
        panelBackground[screenNum].setLayout(null);
        window.add(panelBackground[screenNum]);

        labelBackground[screenNum] = new JLabel();
        labelBackground[screenNum].setBounds(0, 0, 760, 450);  //Image position relative to panel

        ImageIcon imgBackground = new ImageIcon(fileName);
        labelBackground[screenNum].setIcon(imgBackground); //Image = label
    }

    public void gameObject(int screenNum, int x, int y, int width, int height, String fileName, String menuItem1, String menuItem2, String menuItem3, String menuItem1Command, String menuItem2Command, String menuItem3Command) {
        //Menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem[] menuItem = new JMenuItem[3]; //Rows in the popup menu
        menuItem[0] = new JMenuItem(menuItem1);
        menuItem[0].addActionListener(gameHub.actionHandler);
        menuItem[0].setActionCommand(menuItem1Command);

        menuItem[1] = new JMenuItem(menuItem2);
        menuItem[1].addActionListener(gameHub.actionHandler);
        menuItem[1].setActionCommand(menuItem2Command);

        menuItem[2] = new JMenuItem(menuItem3);
        menuItem[2].addActionListener(gameHub.actionHandler);
        menuItem[2].setActionCommand(menuItem3Command);

        popupMenu.add(menuItem[0]);
        popupMenu.add(menuItem[1]);
        popupMenu.add(menuItem[2]);

        //Objects
        JLabel labelObject = new JLabel();
        labelObject.setBounds(x, y, width, height);

        ImageIcon imgObject = new ImageIcon(fileName);
        labelObject.setIcon(imgObject);

        //Game objects actions
        labelObject.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(labelObject, e.getX(), e.getY()); //Cursor coordinates
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        panelBackground[screenNum].add(labelObject); //Adding Label to Panel
        panelBackground[screenNum].add(labelBackground[screenNum]); //First comes the object image then background image otherwise the background will overlap the object
        //changeScreen(1, 0, 150, 50, 50, "mainScreen2");
    }

    public void ChangeScreenButton(int screenNum, int x, int y, int width, int height, String command) { //Change screen button
        final int[] i = {0};
        JButton arrowButton = new JButton();
        arrowButton.setBounds(x, y, width, height);
        arrowButton.setBackground(Color.yellow);
        arrowButton.setContentAreaFilled(true);
        arrowButton.setFocusPainted(false);
        arrowButton.setBorderPainted(false);
        arrowButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (x > 200) {
            arrowButton.setIcon(imgIcon("icon/arrowRight.png"));
        } else {
            arrowButton.setIcon(imgIcon("icon/arrowLeft.png"));
        }
        arrowButton.addActionListener(l -> System.out.println("Arrow button clicked " + ++i[0] + " times"));
        arrowButton.addActionListener(gameHub.actionHandler);
        arrowButton.setActionCommand(command);

        panelBackground[screenNum].add(arrowButton);
    }

    public void gameScreen() {
        //#1 SCREEN
        gameBackground(1, imgPath("bg/forestBg.jpg"));
        ChangeScreenButton(1, 0, 150, 50, 50, "mainScreen2");
        gameObject(1, 225, 295, 150, 180, imgPath("entity/Knight2.png"), "Talk", "-", "-", "talkKnight", "-", "-");
        gameObject(1, 350, 330, 150, 180, imgPath("object/Chest.png"), "Open", "-", "-", "openChest", "-", "-");
        gameObject(1, 0, 270, 200, 200, imgPath("object/hut.png"), "Shop", "-", "-", "shopHut", "-", "-");

        //#2 SCREEN
        gameBackground(2, imgPath("bg/tavernOutside.png"));
        ChangeScreenButton(2, 710, 150, 50, 50, "mainScreen1");
        gameObject(2, 125, 205, 150, 180, imgPath("entity/Knight2.png"), "Talk", "-", "-", "talkKnight", "-", "-");
    }


    //UTILITIES


    public void validateFont() { //Validating custom font
        try {
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
        window.add(label);
        label.setBounds(x, y, width, height);
    }

    public JLabel toImage(String imgSrc) {
        return new JLabel(new ImageIcon("src/cz/reindl/game/ui/graphics/" + imgSrc));
    }

    public ImageIcon imgIcon(String imgSrc) {
        return new ImageIcon(imgPath(imgSrc));
    }

}
