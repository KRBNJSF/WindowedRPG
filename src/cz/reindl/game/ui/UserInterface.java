package cz.reindl.game.ui;

import cz.reindl.game.GameHub;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class UserInterface {

    GameHub gameHub;
    public JFrame window;
    public JTextArea textMessage;
    public JPanel[] panelBackground = new JPanel[20];
    public JLabel[] labelBackground = new JLabel[20];

    public String noAction = "-";

    public Font fontBold, fontRegular, fontRpg;
    public Font font;
    //ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.png")); //File path if JAR is wanted
    ImageIcon logo = new ImageIcon("src/cz/reindl/game/ui/graphics/logo/logo.png");

    public JLabel labelTitle;
    public JButton restartButton;

    //PLAYER UI
    public JPanel panelHp;
    public JLabel[] labelHp = new JLabel[10];
    public JPanel panelInventory;

    public JLabel labelWeapon, labelChestArmor, labelShield, labelCoin, labelKnife;


    public UserInterface(GameHub gameHub) {
        this.gameHub = gameHub;

        mainField();
        playerStats();
        gameOverScreen();
        gameScreen();

        window.setVisible(true);
    }

    public void gameOverScreen() {
        Border border = BorderFactory.createLineBorder(Color.yellow, 2);
        //validateFont();
        labelTitle = new JLabel("YOU DIED", JLabel.CENTER);
        labelTitle.setBounds(0, 0, 1024, 768);
        labelTitle.setForeground(Color.red);
        labelTitle.setFont(new Font("Times New Roman", Font.PLAIN, 70));
        labelTitle.setBorder(border);
        labelTitle.setVisible(false);
        window.add(labelTitle);

        restartButton = new JButton("Restart");
        restartButton.setBounds(440, 420, 120, 50);
        restartButton.setBorder(null);
        restartButton.setBackground(null);
        restartButton.setForeground(Color.white);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener(gameHub.actionHandler);
        restartButton.setActionCommand("restart");
        restartButton.setVisible(false);
        window.add(restartButton);
    }

    public Font setFont(Font font, String pathName) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path(pathName))).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path(pathName))));
        } catch (IOException | FontFormatException e) {
            System.out.println("Font not found, regular font is used");
        }
        return font;
    }

    public void mainField() {
        window = new JFrame("Windowed RPG");
        window.setSize(1024, 768);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLocationRelativeTo(null); //Center window
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
        textMessage.setFont(setFont(fontRpg, "font/Ancient.ttf"));
        window.add(textMessage);
    }

    public void playerStats() {
        ToolTipManager.sharedInstance().setInitialDelay(50);
        panelHp = new JPanel();
        panelHp.setBounds(120, 5, 320, 50);
        panelHp.setBackground(Color.green);
        panelHp.setLayout(new GridLayout(1, 5)); //Equipment grid
        panelHp.setToolTipText("HealthPoints");
        window.add(panelHp);
        //Image img = imgIcon("/icon/heath.png").getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT); //Image scaling to my preferred size

        for (int i = 0; i < 10; i++) {
            labelHp[i] = new JLabel();
            labelHp[i].setIcon(imgIcon("/icon/hearth.png"));
            panelHp.add(labelHp[i]);
        }

        panelInventory = new JPanel();
        panelInventory.setBounds(650, 5, 200, 50);
        panelInventory.setBackground(Color.GREEN);
        panelInventory.setLayout(new GridLayout(1, 5));
        panelInventory.setToolTipText("Inventory");
        window.add(panelInventory);

        //ITEMS
        labelWeapon = new JLabel();
        labelWeapon.setIcon(imgIcon("/icon/hand.png"));
        //labelKnife.setToolTipText("Weapon");
        panelInventory.add(labelWeapon);

        labelCoin = new JLabel();
        labelCoin.setIcon(imgIcon("/icon/coin.png"));
        //labelCoin.setToolTipText("Quest item");
        panelInventory.add(labelCoin);

        labelShield = new JLabel();
        labelShield.setIcon(imgIcon("/icon/shield.png"));
        //labelShield.setToolTipText("Shield");
        panelInventory.add(labelShield);

        labelChestArmor = new JLabel();
        labelChestArmor.setIcon(imgIcon("/icon/chestArmor.png"));
        //labelChestArmor.setToolTipText("Torso");
        panelInventory.add(labelChestArmor);
    }

    public void gameBackground(int screenNum, String fileName) {
        panelBackground[screenNum] = new JPanel();
        panelBackground[screenNum].setBounds(120, 60, 760, 450);  //Panel position relative to frame
        panelBackground[screenNum].setBackground(Color.red); //Just to make sure it's set properly
        panelBackground[screenNum].setLayout(null);
        panelBackground[screenNum].setVisible(false);
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
        Border border = BorderFactory.createLineBorder(Color.yellow, 2);
        JLabel labelObject = new JLabel();
        labelObject.setBounds(x, y, width, height);
        labelObject.setBorder(border);
        //labelObject.setOpaque(true); //Setting visible background of the object
        //labelObject.setBackground(Color.yellow); //Setting opaques color

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
        final int[] j = {0};
        JButton arrowButton = new JButton();
        arrowButton.setBounds(x, y, width, height);
        arrowButton.setBackground(Color.yellow);
        arrowButton.setContentAreaFilled(true);
        arrowButton.setFocusPainted(false);
        arrowButton.setBorderPainted(false);
        arrowButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (x > 200) {
            arrowButton.setIcon(imgIcon("icon/arrowRight.png"));
            arrowButton.addActionListener(l -> System.out.println("Right arrow button clicked " + ++i[0] + " times"));
        } else {
            arrowButton.setIcon(imgIcon("icon/arrowLeft.png"));
            arrowButton.addActionListener(l -> System.out.println("Left arrow button clicked " + ++j[0] + " times"));
        }
        arrowButton.addActionListener(gameHub.actionHandler);
        arrowButton.setActionCommand(command);

        panelBackground[screenNum].add(arrowButton);
    }

    public void gameScreen() {
        //#SPAWN
        gameBackground(1, imgPath("bg/forestBg.jpg"));
        ChangeScreenButton(1, 0, 150, 50, 50, "mainScreen2");
        gameObject(1, 225, 295, 98, 120, imgPath("entity/Knight2.png"), "Talk", "-", "-", "talkKnight", "-", "-");
        gameObject(1, 350, 330, 150, 180, imgPath("object/Chest.png"), "Open", "-", "-", "openChest", "-", "-");
        gameObject(1, 0, 270, 200, 200, imgPath("object/hut.png"), "Shop", "-", "-", "shopHut", "-", "-");
        //gameObject(1, 0, 50, 300, 300, imgPath("entity/blackSmith.png"));

        //#TOWN
        gameBackground(2, imgPath("bg/townSquare.png"));
        ChangeScreenButton(2, 710, 150, 50, 50, "mainScreen1");
        gameObject(2, 125, 205, 98, 120, imgPath("entity/Knight2.png"), "Talk", "-", "-", "talkKnight", "-", "-");
        gameObject(2, 100, 100, 100, 100, imgPath("object/blankTransparent.png"), "Enter", "Knock", "-", "enterPub", "knock", "-");
        gameObject(2, 240, 210, 100, 125, imgPath("object/blankTransparent.png"), "Search", "-", "-", "searchWell", "-", "-");

        //#PUB INSIDE
        gameBackground(3, imgPath("bg/tavernInside.png"));
        ChangeScreenButton(3, 710, 150, 50, 50, "mainScreen2");
        gameObject(3, 100, 100, 100, 100, imgPath("object/blankTransparent.png"), "Enter", "-", "-", "enterDungeon", "-", "-");

        //#DUNGEON
        gameBackground(4, imgPath("bg/Dungeon.png"));
        gameObject(4, 50, 56, 91, 70, imgPath("entity/Rat.png"), "Attack", "Defend", "Run", "fightEnemy", "", "");
        gameObject(4, 150, 56, 222, 200, imgPath("entity/wolf.png"), "Attack", "Defend", "Run", "fightEnemy", "", "");
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
