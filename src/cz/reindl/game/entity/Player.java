package cz.reindl.game.entity;

import cz.reindl.game.GameHub;

public class Player {

    GameHub hub;

    public int playerMaxHp;
    public double playerHp;
    public int playerMaxDmg;
    public int playerMinDmg;
    public int playerCurrentMaxDmg;
    public int playerDef;
    public int playerMaxDef;
    public int playerCoins;
    public int playerExp;
    public int levelUpExp;
    public int playerLevel;
    public int points;

    public boolean hand;
    public boolean knife;
    public boolean isKnife;
    public boolean oldKnife;
    public boolean isOldKnife;
    public boolean sword;
    public boolean isSword;
    public boolean warHammer;
    public boolean isWarHammer;
    public boolean shardSword;
    public boolean isShardSword;

    public boolean shieldKnight;
    public boolean shieldBasic;
    public boolean shieldSkull;

    public boolean torso;
    public boolean torsoBasic;
    public boolean torsoBetter;
    public boolean helmet;

    public boolean coin;
    public boolean key;

    public boolean beer;
    public boolean liquor;
    public boolean cheese;
    public boolean pork;
    public boolean holyWater;

    public boolean mapItem1;
    public boolean mapItem2;
    public boolean mapItem3;
    public boolean mapItem4;

    public boolean buttonDmg;
    public boolean buttonHp;
    public boolean buttonArmor;


    public Player(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void playerDefaultStats() {
        playerMaxHp = 10;
        playerHp = 5; // FIXME: 06.01.2022 Set to 5 and fix Hearth label img
        playerMinDmg = 1;
        playerMaxDmg = 0;
        playerCurrentMaxDmg = 2;
        playerMaxDef = 50;
        playerDef = 0;
        playerCoins = 0;
        playerExp = 0;
        levelUpExp = 100;
        playerLevel = 1;
        points = 0;

        hand = true;
        knife = false;
        isKnife = false;
        oldKnife = false;
        sword = false;
        shardSword = false;
        warHammer = false;

        shieldKnight = false;
        shieldBasic = false;
        shieldSkull = false;

        torso = false;
        torsoBasic = false;
        torsoBetter = false;
        helmet = false;


        coin = false;
        key = false;

        beer = false;
        liquor = false;
        cheese = false;
        pork = false;
        holyWater = false;

        mapItem1 = true;
        mapItem2 = false;
        mapItem3 = false;
        mapItem4 = false;

        buttonDmg = false;
        buttonHp = false;
        buttonArmor = false;

        hub.ui.labelWeapon.setIcon(hub.ui.jarImg("icon/hand.png"));
        hub.ui.labelWeapon.setName("\nHand ");
        hub.ui.labelQuestItem.setIcon(hub.ui.jarImg("icon/coin.png"));
        hub.ui.moneyCount.setText(String.valueOf(playerCoins));
        hub.ui.xpProgress.setMinimum(0);
        hub.ui.xpProgress.setMaximum(100);
        hub.ui.xpProgress.setString(0 + " / " + 100);
        hub.event.questCount = 0;
        hub.fight.count = 0;

        playerCurrentStats();
    }

    public void playerCurrentStats() {
        //REMOVING HEARTH ICONS
        int i = 0;
        while (i < 60) {
            hub.ui.labelHp[i].setVisible(false);
            i++;
        }

        //Setting visible hearth icons equal to player's HP
        double currentHp = playerHp;
        while (currentHp > 0) { // FIXME: 07.01.2022 >0 -> 9HP, >=0 -> 10HP but it's actually 9
            hub.ui.labelHp[(int) currentHp].setVisible(true);
            currentHp--;
        }

        //PLAYER ITEMS CHECK
        hub.ui.labelWeapon.setVisible(hand);
        hub.ui.labelShield.setVisible(shieldKnight);
        hub.ui.labelShield.setVisible(shieldBasic);
        hub.ui.labelShield.setVisible(shieldSkull);
        hub.ui.labelQuestItem.setVisible(coin);
        hub.ui.labelChestArmor.setVisible(torso);

        hub.ui.labelBeer.setVisible(beer);
        hub.ui.labelLiquor.setVisible(liquor);
        hub.ui.labelCheese.setVisible(cheese);
        hub.ui.labelPork.setVisible(pork);
        hub.ui.labelHolyWater.setVisible(holyWater);

        hub.ui.buttonMapItem1.setVisible(mapItem1);
        hub.ui.buttonMapItem2.setVisible(mapItem2);
        hub.ui.buttonMapItem3.setVisible(mapItem3);
        hub.ui.buttonMapItem4.setVisible(mapItem4);

        hub.ui.buttonIncreaseDmg.setVisible(buttonDmg);
        hub.ui.buttonIncreaseHP.setVisible(buttonHp);
        hub.ui.buttonIncreaseArmor.setVisible(buttonArmor);

        hub.ui.buttonKnife.setVisible(!knife);
        hub.ui.buttonTorso.setVisible(!torso);
        hub.ui.buttonTorso.setVisible(!oldKnife);
        hub.ui.buttonTorso.setVisible(!shieldBasic);
        hub.ui.buttonTorso.setVisible(!torsoBasic);
        hub.ui.buttonTorso.setVisible(!torsoBetter);
        hub.ui.buttonTorso.setVisible(!helmet);
        hub.ui.buttonTorso.setVisible(!warHammer);


        //WEAPONS
        if (isSword) {
            playerMinDmg = 4;
            playerMaxDmg = 5;
            playerCurrentMaxDmg += playerMaxDmg;
            isSword = false;
        }
        if (isWarHammer) {
            playerMinDmg = 2;
            playerMaxDmg = 5;
            playerCurrentMaxDmg += playerMaxDmg;
            isWarHammer = false;
        }
        if (isKnife) {
            playerMinDmg = 2;
            playerMaxDmg = 3;
            playerCurrentMaxDmg += playerMaxDmg;
            isKnife = false;
        }
        if (isOldKnife) {
            playerMinDmg = 1;
            playerMaxDmg = 4;
            playerCurrentMaxDmg += playerMaxDmg;
            isOldKnife = false;
        }
        if (isShardSword) {
            playerMinDmg = 6;
            playerMaxDmg = 10;
            playerCurrentMaxDmg += playerMaxDmg;
            isShardSword = false;
        }

        //SHIELD // FIXME: 09.01.2022 Deduct players armor from older item
        if (shieldKnight) {
            playerDef += 10;
        }
        if (shieldSkull) {
            playerDef += 15;
        }
        if (shieldBasic) {
            playerDef += 5;
        }

        //ARMOUR
        if (helmet) {
            playerDef += 3;
        }
        if (torso) {
            playerDef += 10;
        }
        if (torsoBasic) {
            playerDef += 5;
        }
        if (torsoBetter) {
            playerDef += 20;
        }


        //LEVEL CHECK
        if (playerExp >= levelUpExp) {
            setLevelUp();
        }
        if (points <= 0) {
            hub.ui.buttonIncreaseDmg.setVisible(false);
            hub.ui.buttonIncreaseHP.setVisible(false);
            hub.ui.buttonIncreaseArmor.setVisible(false);
        }
    }

    public void setLevelUp() {
        buttonDmg = true;
        buttonHp = true;
        buttonArmor = true;
        hub.ui.textMessage.setText("Level up!\n(1 upgrade point)");
        points++;
        hub.ui.setMoneyCount(5);
        hub.playSoundEffect(hub.sound.levelUp, false);
        hub.ui.setExpCount(-levelUpExp);
        playerLevel++;
        levelUpExp += 25;
        hub.ui.xpProgress.setString(playerExp + " / " + levelUpExp);
    }

}
