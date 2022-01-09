package cz.reindl.game.event;

import cz.reindl.game.GameHub;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
        hub.ui.panelInventory.setVisible(false);
        hub.ui.labelInventory.setVisible(false);
        hub.ui.panelHp.setVisible(false);
        hub.ui.textMessage.setVisible(false);
        hub.ui.labelTitle.setVisible(true);
        hub.ui.restartButton.setVisible(true);
        hub.ui.textStats.setVisible(false);
        hub.ui.panelXpBar.setVisible(false);

        hub.stopMusic(hub.sound.currentMusic);
        hub.playMusic(hub.sound.fightLose, false);
        hub.playSoundEffect(hub.sound.loseSound, false);
    }

    public void gameOverScreenApply() {
        hub.ui.labelTitle.setVisible(false);
        hub.ui.restartButton.setVisible(false);
        hub.ui.labelInventory.setVisible(true);
        hub.ui.panelXpBar.setVisible(true);
        hub.player.playerDefaultStats();
    }

    public void spawnScreen() {
        hub.ui.panelHeathBar.setVisible(false);
        hub.ui.panelInventory.setVisible(true);
        hub.ui.panelHp.setVisible(true);
        hub.ui.textMessage.setVisible(true);
        hub.ui.textStats.setVisible(false);
        hub.ui.panelBackground[1].setVisible(true);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
        hub.ui.panelBackground[9].setVisible(false);
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
        hub.ui.textStats.setVisible(false);

        hub.ui.panelBackground[1].setVisible(false);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[9].setVisible(false);

        hub.stopMusic(hub.sound.currentMusic);
    }

    public void sceneTownSquare() {
        hub.ui.panelHeathBar.setVisible(false);
        hub.ui.textStats.setVisible(false);
        hub.player.mapItem2 = true;
        hub.ui.panelBackground[1].setVisible(false);
        //hub.ui.setLoadingScreen(1);
        hub.ui.panelBackground[2].setVisible(true);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[7].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
        hub.ui.panelBackground[9].setVisible(false);
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
        hub.ui.textStats.setVisible(false);
        hub.player.mapItem3 = true;
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(true);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
        hub.ui.panelBackground[9].setVisible(false);
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
            hub.playSoundEffect(hub.sound.healEffect, false);
        } else {
            hub.ui.textMessage.setText("You are at full health \n");
        }
    }

    public void liquor() {
        if (hub.player.playerHp != hub.player.playerMaxHp - 1) {
            hub.playSoundEffect(hub.sound.fullHealEffect, false);
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
        if (!hub.player.sword) {
            hub.sound.currentSoundEffect = hub.sound.chestOpen;
            hub.playSoundEffect(hub.sound.currentSoundEffect, false);
            hub.ui.setMoneyCount(100);
            hub.ui.textMessage.setText("You opened the chest and found a sword!\n (max dmg + 2)");
            hub.ui.labelWeapon.setIcon(hub.ui.jarImg("icon/sword.png"));
            hub.ui.labelWeapon.setName("\nSword +3 DMG");
            hub.player.sword = true;
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
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.textMessage.setText("You retrieved a skeleton key!");
                    hub.ui.consecutiveText("What's the key for?");
                    hub.ui.labelQuestItem.setIcon(hub.ui.jarImg("icon/skeletonKey.png"));
                    hub.player.key = true;
                    hub.player.playerCurrentStats();
                    questCount++;
                    hub.ui.setMoneyCount(1);
                    hub.ui.setExpCount(125);
                }
            }
            case 1 -> {
                hub.ui.textMessage.setText("Hmm?");
            }
        }
    }

    public void guard() {
        //hub.ui.textMessage.setText("Hello there");
        if (!hub.player.shieldKnight) {
            if (!hub.player.sword) {
                if (hub.player.playerHp >= 1) {
                    hub.ui.textMessage.setText("Don't even try! \n(hp - 1)");
                    hub.player.playerHp--;
                } else if (hub.player.playerHp <= 0) {
                    hub.ui.textMessage.setText("You died\n");
                    hub.event.gameOverScreen(2);
                }
            } else if (hub.player.sword) {
                hub.ui.textMessage.setText("You won and earned shield\n + (Unlocked block animation)");
                hub.player.shieldKnight = true;
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
            hub.ui.panelBackground[9].setVisible(false);
            hub.ui.textStats.setVisible(false);
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
        hub.ui.panelBackground[9].setVisible(false);
        hub.ui.textStats.setVisible(false);
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
        hub.ui.panelBackground[9].setVisible(false);
        hub.ui.textStats.setVisible(false);
    }

    public void stats() {
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        hub.player.playerCurrentStats();
        hub.ui.panelBackground[1].setVisible(false);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[7].setVisible(false);
        hub.ui.panelBackground[8].setVisible(false);
        hub.ui.panelBackground[9].setVisible(true);
        hub.ui.textStats.setVisible(true);
        hub.ui.buttonStats.setVisible(false);

        hub.ui.textStats.setText("");
        hub.ui.textStats.append("HP: " + df.format(hub.player.playerHp) + " - " + hub.player.playerMaxHp + "\n"); // FIXME: 09.01.2022 format
        hub.ui.textStats.append("DMG: " + hub.player.playerMinDmg + " - " + hub.player.playerMaxDmg + "\n");
        hub.ui.textStats.append("Armor: " + hub.player.playerDef + "%\n");
        hub.ui.textStats.append("Coins: " + hub.player.playerCoins + "\n");
        hub.ui.textStats.append("Weapon: " + hub.ui.labelWeapon.getName());
        hub.ui.textStats.append("Level: " + hub.player.playerLevel + "\n");
        hub.ui.textStats.append("Level points: " + hub.player.points + "\n");
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
        hub.ui.panelBackground[9].setVisible(false);
        hub.ui.textStats.setVisible(false);
    }

    public void tavernMenu() {
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[4].setVisible(false);
        hub.ui.panelBackground[8].setVisible(true);
        hub.ui.panelBackground[6].setVisible(false);
        hub.ui.panelBackground[5].setVisible(false);
        hub.ui.panelBackground[9].setVisible(false);
        hub.ui.textStats.setVisible(false);
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
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
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
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You can't buy two liquors at once. Drink the old one first");
            }
        }
    }

    public void buyKnife() {
        int price = 10;
        if (hub.player.playerCoins >= price && !hub.player.knife) {
            hub.ui.labelWeapon.setIcon(hub.ui.jarImg("icon/knife.png"));
            hub.ui.labelWeapon.setName("\nKnife +1 min,max DMG");
            hub.player.knife = true;
            hub.ui.setMoneyCount(-10);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Knife (Max DMG = 4)");
            hub.ui.buttonKnife.setVisible(false);
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You already bought this item!");
            }
        }
    }

    public void buyTorso() {
        int price = 20;
        if (hub.player.playerCoins >= price && !hub.player.torso) {
            hub.ui.labelChestArmor.setIcon(hub.ui.jarImg("icon/chestArmor.png"));
            hub.player.torso = true;
            hub.ui.setMoneyCount(-20);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought ChestPlate : (Armor +20% -> " + hub.player.playerDef + "% / " + hub.player.playerMaxDef + "%)");
            hub.ui.buttonTorso.setVisible(false);
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You already bought this item!");
            }
        }
    }

    public void am() {
        hub.player.playerHp = 10 - 1;
        hub.player.playerMaxDmg = 100;
        hub.ui.setMoneyCount(10);
        hub.player.playerDef = 50;
        hub.player.playerCurrentStats();
    }

    public void dmg() {
        if (hub.player.points >= 1) {
            hub.player.points--;
            hub.player.playerMaxDmg++;
            hub.player.playerCurrentStats();
            System.out.println(hub.player.playerMaxDmg);
            stats();
        } else {
            hub.player.buttonDmg = false;
            hub.ui.textMessage.setText("Not enough points");
            hub.player.playerCurrentStats();
        }
    }

    public void hp() {
        if (hub.player.points >= 1 && hub.player.playerMaxHp < 59) {
            hub.player.points--;
            hub.player.playerMaxHp++;
            hub.player.playerCurrentStats();
            System.out.println(hub.player.playerMaxHp);
            stats();
        } else {
            hub.player.buttonHp = false;
            hub.ui.textMessage.setText("Not enough points or HP limit (60)");
            hub.player.playerCurrentStats();
        }
    }

    public void armor() {
        if (hub.player.points >= 1) {
            hub.player.points--;
            hub.player.playerDef += 2;
            hub.player.playerCurrentStats();
            System.out.println(hub.player.playerDef);
            stats();
        } else {
            hub.player.buttonArmor = false;
            hub.ui.textMessage.setText("Not enough points");
            hub.player.playerCurrentStats();
        }
    }

    public void buyOldKnife() {
        int price = 10;
        if (hub.player.playerCoins >= price && !hub.player.oldKnife) {
            hub.ui.labelWeapon.setIcon(hub.ui.jarImg("icon/oldKnife.png"));
            hub.ui.labelWeapon.setName("\nold knife +1 DMG");
            hub.player.oldKnife = true;
            hub.ui.setMoneyCount(-10);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Knife (Max DMG = 4)");
            hub.ui.buttonOldKnife.setVisible(false);
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You already bought this item!");
            }
        }
    }

    public void buyTorsoBasic() {
        int price = 10;
        if (hub.player.playerCoins >= price && !hub.player.torsoBasic) {
            hub.ui.labelChestArmor.setIcon(hub.ui.jarImg("icon/torsoBasic.png"));
            hub.ui.labelChestArmor.setName("\nTorso +1 DMG");
            hub.player.torsoBasic = true;
            hub.ui.setMoneyCount(-10);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Knife (Max DMG = 4)");
            hub.ui.buttonTorsoBasic.setVisible(false);
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You already bought this item!");
            }
        }
    }

    public void buyTorsoBetter() {
        int price = 10;
        if (hub.player.playerCoins >= price && !hub.player.torsoBetter) {
            hub.ui.labelChestArmor.setIcon(hub.ui.jarImg("icon/torsoBetter.png"));
            hub.ui.labelChestArmor.setName("\nTorso +1 DMG");
            hub.player.torsoBetter = true;
            hub.ui.setMoneyCount(-10);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Knife (Max DMG = 4)");
            hub.ui.buttonTorsoBetter.setVisible(false);
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You already bought this item!");
            }
        }
    }

    public void buyHelmet() {
        int price = 10;
        if (hub.player.playerCoins >= price && !hub.player.helmet) {
            hub.ui.labelChestArmor.setIcon(hub.ui.jarImg("icon/helmet.png"));
            hub.ui.labelChestArmor.setName("\nHelmet +1 DMG");
            hub.player.helmet = true;
            hub.ui.setMoneyCount(-10);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Knife (Max DMG = 4)");
            hub.ui.buttonHelmet.setVisible(false);
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You already bought this item!");
            }
        }
    }

    public void buyWarHammer() {
        int price = 10;
        if (hub.player.playerCoins >= price && !hub.player.warHammer) {
            hub.ui.labelWeapon.setIcon(hub.ui.jarImg("icon/warhammer.png"));
            hub.ui.labelWeapon.setName("\nWarhammer +1 DMG");
            hub.player.warHammer = true;
            hub.ui.setMoneyCount(-10);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Knife (Max DMG = 4)");
            hub.ui.buttonWarHammer.setVisible(false);
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You already bought this item!");
            }
        }
    }

    public void buyShieldBasic() {
        int price = 10;
        if (hub.player.playerCoins >= price && !hub.player.shieldBasic) {
            hub.ui.labelShield.setIcon(hub.ui.jarImg("icon/shieldBasic.png"));
            hub.ui.labelShield.setName("\nShield +1 DMG");
            hub.player.shieldBasic = true;
            hub.ui.setMoneyCount(-10);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Knife (Max DMG = 4)");
            hub.ui.buttonShieldBasic.setVisible(false);
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You already bought this item!");
            }
        }
    }

    public void buyCheese() {
        int price = 2;
        if (hub.player.playerCoins >= price && !hub.player.cheese) {
            hub.player.cheese = true;
            hub.ui.setMoneyCount(-2);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Cheese, check the table over there");
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You can't buy two cheeses at once. Drink the old one first");
            }
        }
    }

    public void buyPork() {
        int price = 5;
        if (hub.player.playerCoins >= price && !hub.player.pork) {
            hub.player.pork = true;
            hub.ui.setMoneyCount(-5);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Pork, check the table over there");
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You can't buy two porks at once. Drink the old one first");
            }
        }
    }

    public void buyHolyWater() {
        int price = 100;
        if (hub.player.playerCoins >= price && !hub.player.holyWater) {
            hub.player.pork = true;
            hub.ui.setMoneyCount(-100);
            hub.player.playerCurrentStats();
            hub.ui.textMessage.setText("You bought Holy water, check the table over there");
        } else {
            if (price >= hub.player.playerCoins) {
                hub.ui.textMessage.setText("You don't have enough coins\nPrice: " + price + " coins, " + (price - hub.player.playerCoins) + " remaining");
            } else {
                hub.ui.textMessage.setText("You can't buy two Holy waters at once. Drink the old one first");
            }
        }
    }

}
