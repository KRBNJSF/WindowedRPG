package cz.reindl.game.event;

import cz.reindl.game.GameHub;

public class Event {

    GameHub hub;
    public int questCount = 0;

    public Event(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void gameOverScreen(int currentScreen) {
        hub.ui.panelBackground[currentScreen].setVisible(false);
        hub.ui.panelHeathBar.setVisible(false);
        hub.ui.panelInventory.setVisible(false);
        hub.ui.panelHp.setVisible(false);
        hub.ui.textMessage.setVisible(false);
        hub.ui.labelTitle.setVisible(true);
        hub.ui.restartButton.setVisible(true);

        hub.stopMusic(hub.sound.currentMusic);
        hub.playMusic(hub.sound.fightWin, false);
    }

    public void gameOverScreenApply() {
        hub.ui.labelTitle.setVisible(false);
        hub.ui.restartButton.setVisible(false);
        hub.player.playerDefaultStats();
    }

    public void spawnScreen() {
        hub.ui.panelHeathBar.setVisible(false);
        hub.ui.panelInventory.setVisible(true);
        hub.ui.panelHp.setVisible(true);
        hub.ui.textMessage.setVisible(true);
        hub.ui.panelBackground[1].setVisible(true);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
        hub.ui.textMessage.setText("Forest");
        hub.ui.consecutiveText("Welcome");

        hub.stopMusic(hub.sound.currentMusic);
        hub.sound.currentMusic = hub.sound.mainTheme;
        hub.playMusic(hub.sound.currentMusic, true);
    }

    public void loadingScreen() {
        hub.ui.panelInventory.setVisible(false);
        hub.ui.panelHp.setVisible(false);
        hub.ui.textMessage.setVisible(false);

        hub.ui.panelBackground[1].setVisible(false);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);

        hub.stopMusic(hub.sound.currentMusic);
    }

    public void sceneTownSquare() {
        hub.ui.panelHeathBar.setVisible(false);
        hub.player.mapItem2 = true;
        hub.ui.panelBackground[1].setVisible(false);
        //hub.ui.setLoadingScreen(1);
        hub.ui.panelBackground[2].setVisible(true);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
        hub.ui.textMessage.setText("Town\n");
        hub.ui.consecutiveText("Hmm?");

        hub.stopMusic(hub.sound.currentMusic);
        hub.sound.currentMusic = hub.sound.townMusic;
        hub.playMusic(hub.sound.currentMusic, true);
        // FIXME: 30.12.2021 hub.playMusic(hub.sound.forge, true);
        hub.player.playerCurrentStats();
    }

    public void scenePubInside() {
        hub.ui.panelHeathBar.setVisible(false);
        hub.player.mapItem3 = true;
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(true);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
        hub.ui.textMessage.setText("Tavern\n");

        hub.stopMusic(hub.sound.currentMusic);
        hub.sound.currentMusic = hub.sound.shopMusic;
        hub.playMusic(hub.sound.currentMusic, true);
        System.out.println(hub.sound.currentMusic);
        hub.player.playerCurrentStats();
    }

    public void beer() {
        if (hub.player.playerHp != hub.player.playerMaxHp - 1) {
            hub.ui.consecutiveText("You recovered 1 hp");
            hub.player.playerHp++;
            hub.player.beer = false;
            hub.player.playerCurrentStats();
            //hub.playMusic(hub.sound.pubGreet, false);
        } else {
            hub.ui.textMessage.setText("You are at full health \n");
        }
    }

    public void liquor() {
        if (hub.player.playerHp != hub.player.playerMaxHp - 1) {
            hub.ui.textMessage.setText("You recovered full hp\n");
            hub.player.playerHp = hub.player.playerMaxHp - 1;
            hub.player.liquor = false;
            hub.player.playerCurrentStats();
            //hub.playMusic(hub.sound.pubGreet, false);
        } else {
            hub.ui.textMessage.setText("You are at full health\n");
        }
    }

    public void chest() {
        if (!hub.player.knife) {
            hub.ui.setMoneyCount(100);
            hub.ui.textMessage.setText("You opened the chest and found a knife!\n (max dmg + 3)");
            hub.ui.labelWeapon.setIcon(hub.ui.jarImg("icon/sword.png"));
            hub.player.knife = true;
            hub.player.playerCurrentStats();
            hub.event.scenePubInside();
        } else {
            hub.ui.textMessage.setText("It's empty\n");
        }
    }

    public void quests() {
        switch (questCount) {
            case 0 -> {
                if (!hub.player.key) {
                    hub.ui.textMessage.setText("You retrieved a skeleton key!");
                    hub.ui.consecutiveText("What's the key for?");
                    hub.ui.labelQuestItem.setIcon(hub.ui.jarImg("icon/skeletonKey.png"));
                    hub.player.key = true;
                    hub.player.playerCurrentStats();
                    questCount++;
                    hub.ui.setMoneyCount(1);
                }
            }
            case 1 -> {
                hub.ui.textMessage.setText("Hmm?");
            }
        }
    }

    public void guard() {
        //hub.ui.textMessage.setText("Hello there");
        if (!hub.player.shield) {
            if (!hub.player.knife) {
                if (hub.player.playerHp >= 1) {
                    hub.ui.textMessage.setText("Don't even try! \n(hp - 1)");
                    hub.player.playerHp--;
                } else if (hub.player.playerHp <= 0) {
                    hub.ui.textMessage.setText("You died\n");
                    hub.event.gameOverScreen(1);
                }
            } else if (hub.player.knife) {
                hub.ui.textMessage.setText("You won and earned shield\n + (Unlocked block animation)");
                hub.player.shield = true;
            }
            hub.player.playerCurrentStats();
        } else {
            hub.ui.textMessage.setText("Nothing happened\n");
        }
    }

    public void pubDoor() {
        if (!hub.player.coin) {
            hub.ui.textMessage.setText("Coin is needed to enter\n");
        } else {
            scenePubInside();
        }
    }

    public void well() {
        if (!hub.player.coin) {
            hub.ui.textMessage.setText("You found a coin\n");
            hub.player.coin = true;
            hub.player.playerCurrentStats();
        } else {
            hub.ui.textMessage.setText("There's nothing\n");
        }
    }

    public void sceneDungeon() {
        if (hub.player.key) {
            hub.ui.panelHeathBar.setVisible(true);
            if (hub.fight.count == 0) {
                hub.fight.firstEncounter();
            }
            hub.stopMusic(hub.sound.currentMusic);
            hub.sound.currentMusic = hub.sound.bossTheme;
            hub.playMusic(hub.sound.currentMusic, true);
            hub.ui.panelBackground[3].setVisible(false);
            hub.ui.panelBackground[4].setVisible(true);
            hub.ui.panelBackground[5].setVisible(false);
            hub.ui.panelBackground[6].setVisible(false);
            hub.ui.panelBackground[8].setVisible(false);
        } else {
            hub.ui.textMessage.setText("Door is locked with a strange-looking lock. It looks almost like a skull.");
        }
    }

    public void sceneTownSquare2() {
        hub.ui.panelHeathBar.setVisible(false);
        hub.player.mapItem4 = true;
        hub.stopMusic(hub.sound.currentMusic);
        hub.sound.currentMusic = hub.sound.forge;
        hub.playMusic(hub.sound.currentMusic, true);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[5].setVisible(true);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[7].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
        hub.ui.textMessage.setText("Town square 2\n");
        hub.player.playerCurrentStats();
    }

    public void chooseLocation() {
        hub.ui.panelHeathBar.setVisible(false);
        //hub.stopMusic(hub.sound.currentMusic);
        hub.ui.textMessage.setText("");
        hub.ui.panelBackground[1].setVisible(false);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[6].setVisible(true);
        hub.ui.panelBackground[7].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
    }

    public void currentScreen(int currentScreen) {
        hub.ui.panelBackground[6].setVisible(false);
        switch (currentScreen) {
            case 1 -> spawnScreen();
            case 2 -> sceneTownSquare();
            case 3 -> scenePubInside();
            default -> spawnScreen();
        }
    }

    public void forgeTown() {
        hub.ui.panelHeathBar.setVisible(false);
        hub.ui.panelBackground[1].setVisible(false);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[7].setVisible(true);
        hub.ui.panelBackground[8].setVisible(false);
    }

    public void tavernMenu() {
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[8].setVisible(true);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
    }

    public void buyBeer() {
        int price = 1;
        if (hub.player.playerCoins >= price && !hub.player.beer) {
            hub.player.beer = true;
            hub.ui.setMoneyCount(-1);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought beer, check the table over there");
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins \nPrice: 1 coin, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You can't buy two beers at once. Drink the old one first");
            }
        }
    }

    public void buyLiquor() {
        int price = 10;
        if (hub.player.playerCoins >= price && !hub.player.liquor) {
            hub.player.liquor = true;
            hub.ui.setMoneyCount(-10);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Liquor, check the table over there");
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: 10 coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You can't buy two liquors at once. Drink the old one first");
            }
        }
    }

    public void am() {
        hub.player.playerHp = 10 - 1;
        hub.player.playerDmg = 100;
        hub.ui.setMoneyCount(10);
        hub.player.playerDef = 2;
        hub.player.playerCurrentStats();
    }
}
