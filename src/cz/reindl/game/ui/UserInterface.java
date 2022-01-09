package cz.reindl.game.ui;

import cz.reindl.game.GameHub;
import cz.reindl.game.entity.Enemies;

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
    public Container container; //Never used
    public JTextArea textMessage, textInfo, textStats;
    public JPanel[] panelBackground = new JPanel[40];
    public JLabel[] labelBackground = new JLabel[40];
    JProgressBar bar;

    public String noAction = "-";
    public Border border = BorderFactory.createLineBorder(Color.yellow, 2);
    public JLabel moneyCount;

    public Font fontBold, fontRegular, fontRpg;
    ImageIcon logo = jarImg("logo/logo.png");

    //ImageIcon logo = new javax.swing.ImageIcon(getClass().getResource("/cz/reindl/game/ui/graphics/logo/logo.png")); //File path if JAR is wanted
    //ImageIcon logo = new ImageIcon("src/cz/reindl/game/ui/graphics/logo/logo.png");

    public JLabel labelTitle, labelLoadingText;
    public JButton restartButton, buttonIncreaseDmg, buttonIncreaseHP, buttonIncreaseArmor;

    //PLAYER UI
    public JPanel panelHp;
    public JLabel[] labelHp = new JLabel[60];
    public JPanel panelInventory;
    public JLabel labelInventory, labelHpBg, labelTextBg;
    public JLabel labelDungeonEntrance, labelPubEntrance, labelTown2, labelWell;
    //items
    public JLabel labelWeapon, labelChestArmor, labelShield, labelQuestItem, labelCoins;
    public JButton buttonMapItem1, buttonMapItem2, buttonMapItem3, buttonMapItem4, buttonStats;
    //FORGE SHOP
    public JButton buttonKnife, buttonTorso, buttonOldKnife, buttonWarHammer, buttonShieldBasic, buttonTorsoBasic, buttonTorsoBetter, buttonHelmet;
    public JLabel labelBeer, labelLiquor, labelPork, labelCheese, labelHolyWater; //shop items

    //FIGHT UI
    public JLabel labelRat, labelWolf, labelKnight, labelOgre, labelWizard, labelWarriorOgre, labelFrogMonster, labelCoffinMonster, labelDoubleMonster, labelGhastliness, labelSpiderMonster, labelWizard2, labelWitch; //enemies
    public JLabel labelChest, labelChestFinal; //rewards
    public JPanel panelHeathBar, panelXpBar;
    public JProgressBar healthBarProgress, xpProgress;


    public UserInterface(GameHub gameHub) {
        this.gameHub = gameHub;

        mainField();
        playerStats();
        gameOverScreen();
        loadingScreen();
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
        //labelTitle.setBorder(border);
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
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path(pathName))).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path(pathName))));
        } catch (IOException | FontFormatException e) {
            System.out.println("Font not found, regular font is used");
        }
        return font;
    }

    public void mainField() {
        window = new JFrame("Windowed RPG");
        window.setSize(1024, 820); //1024, 768
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLocationRelativeTo(null); //Center window
        window.setLayout(null);
        window.setResizable(false);
        window.setIconImage(logo.getImage()); //App logo settings
        cursorIconMain();

        textMessage = new JTextArea();
        textMessage.setText("");
        textMessage.setBounds(120, 530, 760, 120); //Text position relative to frame
        textMessage.setBackground(Color.BLUE); // FIXME: 02.01.2022 Set new color
        textMessage.setForeground(Color.white);
        textMessage.setEditable(false);
        textMessage.setLineWrap(true);
        textMessage.setWrapStyleWord(true);
        textMessage.setFont(setFont(fontRpg, "font/Ancient.ttf"));
        window.add(textMessage);

        /*
        labelTextBg = new JLabel();
        labelTextBg.setBounds(120, 515, 760, 120);
        labelTextBg.setIcon(jarImg("panels/textPanel.png"));
        labelTextBg.add(textMessage);
        window.add(labelTextBg); */
        textInfo();
        textStats();

        panelHeathBar = new JPanel();
        panelHeathBar.setBounds(100, 100, 200, 30);
        panelHeathBar.setBackground(Color.BLUE);
        panelHeathBar.setOpaque(false);

        healthBarProgress = new JProgressBar();
        healthBarProgress.setMinimum(0);
        healthBarProgress.setStringPainted(true); //Paints the percentage of bar
        healthBarProgress.setPreferredSize(new Dimension(200, 30));
        healthBarProgress.setForeground(Color.RED);
        healthBarProgress.setBackground(Color.BLACK);
        healthBarProgress.setFont(new Font("MV Boli", Font.BOLD, 25));
        panelHeathBar.add(healthBarProgress);
        window.add(panelHeathBar);

        panelXpBar = new JPanel();
        panelXpBar.setBounds(120, 505, 760, 20);
        panelXpBar.setBackground(Color.RED);
        panelXpBar.setOpaque(false);

        xpProgress = new JProgressBar();
        xpProgress.setStringPainted(true);
        xpProgress.setMinimum(0);
        xpProgress.setValue(10);
        xpProgress.setString(0 + "/" + 100);
        xpProgress.setPreferredSize(new Dimension(7600, 20));
        xpProgress.setForeground(Color.GREEN);
        xpProgress.setBackground(Color.lightGray);
        xpProgress.setFont(new Font("MV Boli", Font.BOLD, 12));
        panelXpBar.add(xpProgress);
        window.add(panelXpBar);
    }

    public void playerStats() {
        ToolTipManager.sharedInstance().setInitialDelay(50);
        panelHp = new JPanel();
        panelHp.setBounds(120, 5, 320, 50);
        panelHp.setBackground(Color.lightGray);
        //panelHp.setOpaque(false);
        panelHp.setLayout(new GridLayout(3, 20)); //HP grid
        panelHp.setToolTipText("HealthPoints");
        window.add(panelHp);
        //Image img = imgIcon("/icon/heath.png").getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT); //Image scaling to my preferred size

        for (int i = 0; i < 60; i++) {
            labelHp[i] = new JLabel();
            labelHp[i].setIcon(jarImg("/icon/hearth.png"));
            panelHp.add(labelHp[i]);
        }

        //INVENTORY
        panelInventory = new JPanel();
        panelInventory.setBounds(650, 5, 200, 50);
        panelInventory.setBackground(Color.GREEN);
        //panelInventory.setOpaque(false); // FIXME: 07.01.2022
        panelInventory.setLayout(new GridLayout(1, 6)); //Equipment grid
        panelInventory.setToolTipText("Inventory");
        //window.add(panelInventory);

        labelInventory = new JLabel("", SwingConstants.CENTER);
        labelInventory.setBounds(650, 5, 200, 50);
        labelInventory.setIcon(jarImg("panels/inventoryPanel.png"));
        labelInventory.setLayout(new GridLayout(1, 6));
        labelInventory.setToolTipText("Inventory");
        window.add(labelInventory);

        //ITEMS
        labelWeapon = new JLabel();
        labelWeapon.setIcon(jarImg("/icon/hand.png"));
        labelWeapon.setToolTipText("Weapon");
        labelInventory.add(labelWeapon);

        labelQuestItem = new JLabel();
        labelQuestItem.setIcon(jarImg("/icon/coin.png"));
        labelQuestItem.setToolTipText("Quest item");
        labelQuestItem.setVisible(false);
        labelInventory.add(labelQuestItem);


        labelShield = new JLabel();
        labelShield.setIcon(jarImg("/icon/shield.png"));
        labelShield.setToolTipText("Shield");
        labelShield.setVisible(false);
        labelInventory.add(labelShield);

        labelChestArmor = new JLabel();
        labelChestArmor.setIcon(jarImg("/icon/chestArmor.png"));
        labelChestArmor.setToolTipText("Torso");
        labelChestArmor.setVisible(false);
        labelInventory.add(labelChestArmor);

        labelCoins = new JLabel();
        labelCoins.setIcon(jarImg("icon/currency.png"));
        labelCoins.setToolTipText("Currency");
        labelCoins.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        labelInventory.add(labelCoins);

        moneyCount = new JLabel();
        moneyCount.setText(String.valueOf(0));
        labelCoins.add(moneyCount);

        //JOptionPane.showMessageDialog(null, labelCoins); //Inspection window
    }

    public void gameBackground(int screenNum, String fileName) {
        panelBackground[screenNum] = new JPanel();
        panelBackground[screenNum].setBounds(120, 60, 760, 450);  //Panel position relative to frame
        panelBackground[screenNum].setBackground(Color.BLACK); //Just to make sure it's set properly
        panelBackground[screenNum].setLayout(null);
        panelBackground[screenNum].setVisible(false);
        window.add(panelBackground[screenNum]);

        labelBackground[screenNum] = new JLabel("", SwingConstants.CENTER);
        labelBackground[screenNum].setBounds(0, 0, 760, 450);  //Image position relative to panel

        ImageIcon imgBackground = jarImg(fileName);
        labelBackground[screenNum].setIcon(imgBackground); //Image = label
    }

    public JLabel gameObject(int screenNum, int x, int y, int width, int height, String fileName, String menuItem1, String menuItem2, String menuItem3, String menuItem1Command, String menuItem2Command, String menuItem3Command) {
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
        //labelObject.setBorder(border);
        //labelObject.setOpaque(true); //Setting visible background of the object
        //labelObject.setBackground(Color.yellow); //Setting opaques color

        ImageIcon imgObject = jarImg(fileName);
        labelObject.setIcon(imgObject);

        //Game objects actions
        labelObject.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (labelObject == labelDungeonEntrance) {
                    gameHub.event.sceneDungeon();
                }
                if (labelObject == labelPubEntrance) {
                    gameHub.event.pubDoor();
                }
                if (labelObject == labelWell) {
                    gameHub.event.well();
                }
                if (labelObject == labelTown2) {
                    gameHub.event.sceneTownSquare2();
                }
                /*try {
                    labelObject.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Thread.sleep(100);
                    labelObject.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }*/
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
                if (labelObject == labelDungeonEntrance || labelObject == labelPubEntrance || labelObject == labelTown2) {
                    setCursorIcon(labelObject, "icon/cursor/cursorEnter.png", "cursorEnter");
                } else if (labelObject == labelWell) {
                    setCursorIcon(labelObject, "icon/cursor/cursorMagnifier.png", "cursorMagnifier");
                } else {
                    setCursorIcon(labelObject, "icon/cursor/cursorGet.png", "cursorGet");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        panelBackground[screenNum].add(labelObject); //Adding Label to Panel
        panelBackground[screenNum].add(labelBackground[screenNum]); //First comes the object image then background image otherwise the background will overlap the object
        //changeScreen(1, 0, 150, 50, 50, "mainScreen2");
        return labelObject;
    }

    public JLabel enemyObject(int screenNum, int x, int y, int width, int height, String fileName, String menuItem1, String menuItem2, String menuItem3, String menuItem4, String menuItem1Command, String menuItem2Command, String menuItem3Command, String menuItem4Command) {
        //Menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem[] menuItem = new JMenuItem[4]; //Rows in the popup menu
        menuItem[0] = new JMenuItem(menuItem1);
        menuItem[0].addActionListener(gameHub.actionHandler);
        menuItem[0].setActionCommand(menuItem1Command);

        menuItem[1] = new JMenuItem(menuItem2);
        menuItem[1].addActionListener(gameHub.actionHandler);
        menuItem[1].setActionCommand(menuItem2Command);

        menuItem[2] = new JMenuItem(menuItem3);
        menuItem[2].addActionListener(gameHub.actionHandler);
        menuItem[2].setActionCommand(menuItem3Command);

        menuItem[3] = new JMenuItem(menuItem4);
        menuItem[3].addActionListener(gameHub.actionHandler);
        menuItem[3].setActionCommand(menuItem4Command);

        popupMenu.add(menuItem[0]);
        popupMenu.add(menuItem[1]);
        popupMenu.add(menuItem[2]);
        popupMenu.add(menuItem[3]);

        //Objects
        Border border = BorderFactory.createLineBorder(Color.yellow, 2);
        JLabel labelObject = new JLabel();
        labelObject.setBounds(x, y, width, height);
        // FIXME: 02.01.2022 labelObject.setBorder(border);
        //labelObject.setOpaque(true); //Setting visible background of the object
        //labelObject.setBackground(Color.yellow); //Setting opaques color

        ImageIcon imgObject = jarImg(fileName);
        labelObject.setIcon(imgObject);

        //Game objects actions
        labelObject.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (gameHub.fight.count == 0) {
                        gameHub.fight.setEnemy(Enemies.RAT);
                    } else if (gameHub.fight.count == 3) {
                        gameHub.sound.currentSoundEffect = gameHub.sound.chestOpen;
                        gameHub.event.chest();
                        gameHub.player.playerCurrentStats();
                       /* if (gameHub.player.knife) {
                            gameHub.player.playerCurrentStats();
                            textMessage.setText("You went back in tavern");
                        } else {
                            textMessage.setText("Open the chest!");
                        }*/
                    } else if (gameHub.fight.count == 7) {
                        gameHub.sound.currentSoundEffect = gameHub.sound.finalChestOpen;
                        gameHub.event.treasure();
                        gameHub.player.playerCurrentStats();
                    }
                    gameHub.fight.attack();
                }
                /*try {
                    labelObject.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Thread.sleep(100);
                    labelObject.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }*/
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
                if (gameHub.fight.count == 3) {
                    setCursorIcon(labelObject, "icon/cursor/cursorGet.png", "cursorGet");
                } else if (!gameHub.player.sword) {
                    setCursorIcon(labelObject, "icon/cursor/cursorPunch.png", "cursorPunch");
                } else {
                    setCursorIcon(labelObject, "icon/cursor/swordSilver.png", "cursorSword");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        panelBackground[screenNum].add(labelObject); //Adding Label to Panel
        panelBackground[screenNum].add(labelBackground[screenNum]); //First comes the object image then background image otherwise the background will overlap the object
        //changeScreen(1, 0, 150, 50, 50, "mainScreen2");
        return labelObject;
    }

    public JLabel shopObject(int screenNum, int x, int y, int width, int height, String fileName) {
        //Objects
        JLabel labelObject = new JLabel();
        labelObject.setBounds(x, y, width, height);
        //labelObject.setBorder(border);

        ImageIcon imgObject = jarImg(fileName);
        labelObject.setIcon(imgObject);

        //Game objects actions
        labelObject.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (labelObject == labelBeer) {
                    gameHub.event.beer();
                }
                if (labelObject == labelLiquor) {
                    gameHub.event.liquor();
                }
                if (labelObject == labelHolyWater) {
                    gameHub.event.holyWater();
                }
                if (labelObject == labelCheese) {
                    gameHub.event.cheese();
                }
                if (labelObject == labelPork) {
                    gameHub.event.pork();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursorIcon(labelObject, "icon/cursor/cursorGet.png", "cursorGet");
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        panelBackground[screenNum].add(labelObject); //Adding Label to Panel
        panelBackground[screenNum].add(labelBackground[screenNum]); //First comes the object image then background image otherwise the background will overlap the object
        //changeScreen(1, 0, 150, 50, 50, "mainScreen2");
        return labelObject;
    }

    public void changeScreenButton(int screenNum, int x, int y, int width, int height, String command, String text) { //Change screen button
        final int[] i = {0};
        final int[] j = {0};
        JButton arrowButton = new JButton();
        arrowButton.setBounds(x, y, width, height);
        arrowButton.setBackground(Color.yellow);
        arrowButton.setContentAreaFilled(true);
        arrowButton.setFocusPainted(false);
        arrowButton.setBorderPainted(false);
        arrowButton.setToolTipText(text);
        if (x > 200) {
            arrowButton.setIcon(jarImg("icon/arrowRight.png"));
            arrowButton.addActionListener(l -> System.out.println("Right arrow button clicked " + ++i[0] + " times"));
        } else {
            arrowButton.setIcon(jarImg("icon/arrowLeft.png"));
            arrowButton.addActionListener(l -> System.out.println("Left arrow button clicked " + ++j[0] + " times"));
        }
        arrowButton.addActionListener(gameHub.actionHandler);
        arrowButton.setActionCommand(command);

        panelBackground[screenNum].add(arrowButton);
    }

    public JButton buttonIcon(int screenNum, int x, int y, int width, int height, String command, String file, String text, Boolean opaque) {
        JButton buttonIcon = new JButton();
        buttonIcon.setBounds(x, y, width, height);
        buttonIcon.setIcon(jarImg(file));
        buttonIcon.setContentAreaFilled(opaque);
        buttonIcon.setFocusPainted(false); //Border around image
        buttonIcon.setBorderPainted(false); //Border around button
        buttonIcon.setBackground(Color.gray);
        buttonIcon.setToolTipText(text);

        buttonIcon.addActionListener(gameHub.actionHandler);
        buttonIcon.setActionCommand(command);

        panelBackground[screenNum].add(buttonIcon);
        return buttonIcon;
    }

    public void gameScreen() {
        //#SPAWN
        gameBackground(1, "bg/forest.png");
        changeScreenButton(1, 0, 150, 50, 50, "mainScreen2", "Town");
        buttonIcon(1, 0, 0, 35, 35, "teleport", "icon/compass.png", "Teleportation map", true);
        buttonIcon(1, 40, 0, 35, 35, "stats", "icon/stats.png", "Statistics", true);
        buttonType(1, 757, 0, 3, 3, "am");
        gameObject(1, 0, 0, 0, 0, "object/blankTransparent.png", "", "", "", "", "", "");


        //gameObject(1, 0, 50, 300, 300, imgPath("entity/blackSmith.png"));

        //#TOWN 1 SIDE
        gameBackground(2, "bg/townSquare.png");
        buttonIcon(2, 0, 0, 35, 35, "teleport", "icon/compass.png", "Teleportation map", true);
        buttonIcon(2, 40, 0, 35, 35, "stats", "icon/stats.png", "Statistics", true);
        changeScreenButton(2, 710, 150, 50, 50, "mainScreen1", "Forest");
        gameObject(2, 620, 320, 115, 141, ("entity/Knight2.png"), "Talk", "Challenge", "-", "talkKnight", "encounterKnight", "-");
        labelPubEntrance = gameObject(2, 100, 290, 40, 70, ("object/blankTransparent.png"), "Enter", "Knock", "-", "enterPub", "knockPub", "-");
        labelWell = gameObject(2, 480, 230, 100, 170, ("object/blankTransparent.png"), "Search", "-", "-", "searchWell", "-", "-");
        labelTown2 = gameObject(2, 230, 220, 100, 100, ("object/blankTransparent.png"), "Go", "-", "-", "goTown2", "-", "-");

        //#PUB INSIDE
        gameBackground(3, "bg/tavernInside.png");
        buttonIcon(3, 0, 0, 35, 35, "teleport", "icon/compass.png", "Teleportation map", true);
        buttonIcon(3, 40, 0, 35, 35, "stats", "icon/stats.png", "Statistics", true);
        changeScreenButton(3, 710, 150, 50, 50, "mainScreen2", "Town");
        labelDungeonEntrance = gameObject(3, 350, 150, 130, 100, ("object/blankTransparent.png"), "Enter", "-", "-", "enterDungeon", "-", "-");
        labelBeer = shopObject(3, 500, 180, 96, 105, "object/fullBeer.png");
        labelLiquor = shopObject(3, 230, 270, 52, 80, "object/liquor.png");
        labelHolyWater = shopObject(3, 160, 290, 44, 54, "object/holyWaterObject.png");
        labelPork = shopObject(3, -20, 330, 180, 107, "object/porkObject.png");
        labelCheese = shopObject(3, 160, 350, 63, 50, "object/cheeseObject.png");
        gameObject(3, 560, 100, 150, 208, ("entity/innkeeper.png"), "Menu", "Talk", "-", "tavernMenu", "talkBartender", "-");
        gameObject(3, 360, 250, 111, 205, ("entity/dwarf.png"), "Talk", "-", "Arena", "getQuest", "-", "-");

        //#BLACKSMITH
        gameBackground(7, "bg/blacksmith.png");
        buttonIcon(7, 0, 0, 35, 35, "teleport", "icon/compass.png", "Teleportation map", true);
        buttonIcon(7, 40, 0, 35, 35, "stats", "icon/stats.png", "Statistics", true);
        changeScreenButton(7, 710, 150, 50, 50, "goTown2", "Forge");
        buttonKnife = buttonIcon(7, 270, 250, 50, 50, "buyKnife", "icon/knife.png", "Price: 10, Weapon: Gives 1 DMG", false);
        buttonTorso = buttonIcon(7, 375, 305, 50, 50, "buyTorso", "icon/chestArmor.png", "Price: 20, Torso: Gives 20% / 50% of Armor", false);
        buttonOldKnife = buttonIcon(7, 325, 250, 50, 50, "buyOldKnife", "icon/oldKnife.png", "Price: 10, Weapon: Gives 2 DMG", false);
        buttonTorsoBasic = buttonIcon(7, 425, 305, 50, 50, "buyTorsoBasic", "icon/torsoBasic.png", "Price: 10, Torso: Gives 20% / 50% of Armor", false);
        buttonTorsoBetter = buttonIcon(7, 270, 305, 50, 50, "buyTorsoBetter", "icon/torsoBetter.png", "Price: 50, Torso: Gives 20% / 50% of Armor", false);
        buttonHelmet = buttonIcon(7, 325, 305, 50, 50, "buyHelmet", "icon/helmet.png", "Price: 50, Torso: Gives 20% / 50% of Armor", false);
        buttonWarHammer = buttonIcon(7, 375, 250, 50, 50, "buyWarHammer", "icon/warHammer.png", "Price: 150, Weapon: Gives 2 DMG", false);
        buttonShieldBasic = buttonIcon(7, 425, 250, 50, 50, "buyShieldBasic", "icon/shieldBasic.png", "Price: 10, Shield: Gives 20% / 50% of Armor", false);
        shopObject(7, 180, 235, 400, 140, "panels/shopPanel.png");
        gameObject(7, 0, 0, 0, 0, ("object/blankTransparent.png"), "", "", "", "", "", "");

        //#TOWN 2 SIDE
        gameBackground(5, "bg/townSquare2.png");
        buttonIcon(5, 0, 0, 35, 35, "teleport", "icon/compass.png", "Teleportation map", true);
        buttonIcon(5, 40, 0, 35, 35, "stats", "icon/stats.png", "Statistics", true);
        changeScreenButton(5, 710, 150, 50, 50, "mainScreen2", "Town");
        gameObject(5, 430, 230, 320, 200, ("object/blankTransparent.png"), "Enter", "-", "-", "enterForge", "-", "-");

        //#DUNGEON
        gameBackground(4, "bg/Dungeon.png");
        //changeScreenButton(4, 710, 150, 50, 50, "enterPub");
        //setBar(1, "f", 5);
        labelRat = enemyObject(4, 350, 350, 91, 70, ("entity/Rat.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy", "defend1", "specialAttack", "runAway");
        labelWolf = enemyObject(4, 300, 220, 222, 200, ("entity/wolf.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelKnight = enemyObject(4, 320, 56, 200, 388, ("entity/warrior.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelOgre = enemyObject(4, 320, 56, 200, 388, ("entity/ogre.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelWizard = enemyObject(4, 320, 100, 120, 300, ("entity/wizard.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelWarriorOgre = enemyObject(4, 260, 30, 300, 405, ("entity/warriorOgre.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelFrogMonster = enemyObject(4, 260, 120, 341, 310, ("entity/toadWarrior.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelCoffinMonster = enemyObject(4, 260, 23, 357, 430, ("entity/occultists.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelDoubleMonster = enemyObject(4, 230, 10, 388, 404, ("entity/twins.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelGhastliness = enemyObject(4, 300, 30, 300, 405, ("entity/ghastliness.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelSpiderMonster = enemyObject(4, 200, 270, 388, 296, ("entity/spiderWoman.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelWizard2 = enemyObject(4, 340, 45, 216, 400, ("entity/essenceWizard.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelWitch = enemyObject(4, 290, 30, 218, 400, ("entity/witch.png"), "Attack", "Luck", "Special Attack", "Run Away", "fightEnemy2", "defend", "specialAttack", "runAway");
        labelChest = enemyObject(4, 350, 330, 150, 180, ("object/Chest.png"), "Open", "-", "-", "-", "openChest", "-", "-", "-");
        labelChestFinal = enemyObject(4, 350, 300, 194, 150, ("object/finalChest.png"), "Open", "-", "-", "-", "openTreasure", "-", "-", "-");

        //TAVERN SHOP
        gameBackground(8, "bg/map.png");
        changeScreenButton(8, 710, 150, 50, 50, "enterPub", "Pub");
        buttonIcon(8, 0, 0, 35, 35, "teleport", "icon/compass.png", "Teleportation map", true);
        buttonIcon(8, 40, 0, 35, 35, "stats", "icon/stats.png", "Statistics", true);
        buttonIcon(8, 325, 115, 50, 50, "buyBeer", "icon/beer.png", "Price: 1 coin : Adds 1 HP when drank", true);
        buttonIcon(8, 325, 170, 50, 50, "buyLiquor", "icon/liquor.png", "Price: 20 coins : Adds 10 HP when drank", true);
        buttonIcon(8, 325, 225, 50, 50, "buyHolyWater", "icon/holyWater.png", "Price: 100 coins : Adds FULL HP when drank", true);
        buttonIcon(8, 395, 115, 50, 50, "buyPork", "icon/pork.png", "Price: 10 coins : Adds 5 HP when eaten", true);
        buttonIcon(8, 395, 170, 50, 50, "buyCheese", "icon/cheese.png", "Price: 5 coins : Adds 2 HP when eaten", true);
        gameObject(8, 0, 0, 0, 0, ("object/blankTransparent.png"), "", "", "", "", "", "");

        //MAP
        gameBackground(6, "bg/map.png");
        buttonIcon(6, 0, 0, 35, 35, "currentScreen", "icon/compass.png", "Teleportation map", true);
        buttonIcon(6, 40, 0, 35, 35, "stats", "icon/stats.png", "Statistics", true);
        buttonMapItem1 = buttonIcon(6, 365, 110, 50, 50, "mainScreen1", "icon/forest.png", "Forest", true);
        buttonMapItem2 = buttonIcon(6, 365, 165, 50, 50, "mainScreen2", "icon/townEntrance.png", "Town", true);
        buttonMapItem3 = buttonIcon(6, 365, 220, 50, 50, "enterPub", "icon/beer.png", "Tavern", true);
        buttonMapItem4 = buttonIcon(6, 365, 275, 50, 50, "goTown2", "icon/forge.png", "Forge", true);
        gameObject(6, 0, 0, 0, 0, ("object/blankTransparent.png"), "", "", "", "", "", "");

        //STATISTICS
        gameBackground(9, "bg/map.png");
        buttonIcon(9, 0, 0, 35, 35, "currentScreen", "icon/compass.png", "Teleportation map", true);
        buttonStats = buttonIcon(9, 40, 0, 35, 35, "stats", "icon/stats.png", "Statistics", true);
        buttonIncreaseHP = buttonIcon(9, 450, 105, 30, 30, "increaseHP", "icon/hearthPlus.png", "Increase MAX HP by 1", false);
        buttonIncreaseDmg = buttonIcon(9, 450, 137, 30, 30, "increaseDmg", "icon/plus.png", "Increase MAX DMG by 1", false);
        buttonIncreaseArmor = buttonIcon(9, 450, 169, 30, 30, "increaseArmor", "icon/plus.png", "Increase Armor by 2%", false);
        gameObject(9, 0, 0, 0, 0, ("object/blankTransparent.png"), "", "", "", "", "", "");

    }


    //UTILITIES

    public void buttonType(int screenNum, int x, int y, int width, int height, String command) {
        JButton buttonIcon = new JButton();
        buttonIcon.setBounds(x, y, width, height);
        buttonIcon.setContentAreaFilled(true);
        buttonIcon.setFocusPainted(false); //Border around image
        buttonIcon.setBorderPainted(false); //Border around button
        buttonIcon.setBackground(Color.gray);

        buttonIcon.addActionListener(gameHub.actionHandler);
        buttonIcon.setActionCommand(command);

        panelBackground[screenNum].add(buttonIcon);
    }

    public void textInfo() {
        textInfo = new JTextArea();
        textInfo.setText("");
        textInfo.setBounds(120, 655, 760, 120); //Text position relative to frame
        textInfo.setBackground(Color.BLACK); // FIXME: 02.01.2022 Set new color
        textInfo.setForeground(Color.white);
        textInfo.setEditable(false);
        textInfo.setLineWrap(true);
        textInfo.setWrapStyleWord(true);
        textInfo.setFont(setFont(fontRegular, "font/Regular.ttf"));
        window.add(textInfo);
    }

    public void textStats() {
        textStats = new JTextArea();
        textStats.setText("");
        textStats.setBounds(420, 170, 150, 250);
        textStats.setBackground(Color.RED);
        textStats.setOpaque(false);
        textStats.setForeground(Color.BLACK);
        textStats.setEditable(false);
        textStats.setLineWrap(true);
        textStats.setWrapStyleWord(true);
        textStats.setFont(setFont(fontRegular, "font/Ancient.ttf"));
        textStats.setVisible(false);
        window.add(textStats);
    }

    public void validateFont() { //Validating custom font
        try {
            fontRpg = Font.createFont(Font.TRUETYPE_FONT, new File(path("font/rpgFire.ttf"))).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path("font/rpgFire.ttf"))));
        } catch (IOException | FontFormatException e) {
            System.out.println("Font not found, regular font is used");
        }
    }

    //TEXT OF CONSECUTIVE CHARACTERS
    public Timer timer;
    public int i;

    public void consecutiveText(String text) {
        timer = new Timer(30, e -> {
            char[] characters = text.toCharArray();
            int arrayNumber = characters.length;

            String addedCharacter = String.valueOf(characters[i]);
            if (characters.length <= characters[i]) { // FIXME: 05.01.2022 IF characters >= characters[i] -> timer.stop(), to working state
                textInfo.append(addedCharacter);
                i++;
            } else {
                timer.stop();
            }

            if (i >= arrayNumber) {
                i = 0;
                timer.stop();
            }
        });
        timer.start();
        textInfo.setText("");
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

    public ImageIcon jarImg(String path) {
        return new javax.swing.ImageIcon(getClass().getResource("/cz/reindl/game/ui/graphics/" + path));
    }

    public void cursorIconMain() {
        try {
            window.setCursor(
                    Toolkit
                            .getDefaultToolkit()
                            .createCustomCursor(
                                    jarImg(("icon/cursor/cursorHand.png")).getImage(),
                                    new Point(0, 0),
                                    "My cursor"
                            )
            );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setCursorIcon(JLabel label, String path, String name) {
        try {
            label.setCursor(
                    Toolkit
                            .getDefaultToolkit()
                            .createCustomCursor(
                                    jarImg(path).getImage(),
                                    new Point(0, 0),
                                    name
                            )
            );
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void setCursorIconButton(JButton button, String path, String name) {
        try {
            button.setCursor(
                    Toolkit
                            .getDefaultToolkit()
                            .createCustomCursor(
                                    jarImg(path).getImage(),
                                    new Point(0, 0),
                                    name
                            )
            );
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public ImageIcon imgIcon(String imgSrc) {
        return new ImageIcon((imgSrc));
    }

    public void setMoneyCount(int moneyCount) {
        gameHub.player.playerCoins += moneyCount;
        gameHub.ui.moneyCount.setText(String.valueOf(gameHub.player.playerCoins));
    }

    public void setExpCount(int expCount) {
        gameHub.player.playerExp += expCount; // FIXME: 09.01.2022 Paint bar
        //gameHub.ui.xpProgress.setMinimum(0);
        //gameHub.ui.xpProgress.setMaximum(gameHub.player.levelUpExp);
        gameHub.ui.xpProgress.setValue(gameHub.player.playerExp);
        gameHub.ui.xpProgress.setString(gameHub.player.playerExp + " / " + gameHub.player.levelUpExp);
        gameHub.player.playerCurrentStats();
    }

    public void loadingScreen() {
        bar = new JProgressBar();
        labelLoadingText = new JLabel("Loading...", JLabel.CENTER);
        labelLoadingText.setBounds(0, 190, 1024, 768);
        labelLoadingText.setForeground(Color.white);
        labelLoadingText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        labelLoadingText.setVisible(false);
        window.add(labelLoadingText);

        bar.setValue(0); //Percentage of bar
        bar.setBounds(162, 600, 700, 20);
        bar.setStringPainted(true); //Paints the percentage of bar
        bar.setFont(new Font("MV Boli", Font.BOLD, 15));
        bar.setForeground(Color.CYAN);
        bar.setBackground(Color.BLACK);
        bar.setVisible(false);
        window.add(bar);
    }

    public void setLoadingScreen(int currentScreen) {
        bar.setValue(0);
        gameHub.ui.panelBackground[currentScreen].setVisible(false);
        gameHub.ui.panelHeathBar.setVisible(false);
        gameHub.ui.panelInventory.setVisible(false);
        gameHub.ui.labelInventory.setVisible(false);
        gameHub.ui.panelHp.setVisible(false);
        gameHub.ui.textMessage.setVisible(false);
        gameHub.ui.panelXpBar.setVisible(false);

        gameHub.stopMusic(gameHub.sound.currentMusic);
        gameHub.playMusic(gameHub.sound.mainTheme, false);

        int counter = bar.getValue();
        while (counter <= 100) {
            labelLoadingText.setVisible(true);
            bar.setVisible(true);
            bar.setValue(counter);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter += 1;
        }
        bar.setString("Done");

        pressKey();

        labelLoadingText.setVisible(false);
        bar.setVisible(false);
        gameHub.ui.panelInventory.setVisible(true);
        gameHub.ui.panelHp.setVisible(true);
        gameHub.ui.textMessage.setVisible(true);
        gameHub.ui.labelInventory.setVisible(true);
    }

    public void pressKey() {

    }

}
