package cz.reindl.game.entity;

import cz.reindl.game.GameHub;

public class Player {

    GameHub hub;

    public int playerMaxHp;
    public double playerHp;
    public int playerDmg;
    public int playerDef;
    public int playerMaxDef;
    public int playerCoins;

    public boolean hand;
    public boolean sword;

    public boolean shield;

    public boolean torso;

    public boolean coin;
    public boolean key;

    public boolean beer;
    public boolean liquor;

    public boolean mapItem1;
    public boolean mapItem2;
    public boolean mapItem3;
    public boolean mapItem4;


    public Player(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void playerDefaultStats() {
        playerMaxHp = 10;
        playerHp = 9; // FIXME: 06.01.2022 Set to 5 and fix Hearth label img
        playerDmg = 2;
        playerMaxDef = 50;
        playerDef = 0;
        playerCoins = 0;
        hand = true;
        sword = false;
        shield = false;
        torso = false;
        coin = false;
        key = false;

        beer = false;
        liquor = false;

        mapItem1 = true;
        mapItem2 = false;
        mapItem3 = false;
        mapItem4 = false;

        hub.ui.labelWeapon.setIcon(hub.ui.jarImg("icon/hand.png"));
        hub.ui.labelQuestItem.setIcon(hub.ui.jarImg("icon/coin.png"));
        hub.ui.moneyCount.setText(String.valueOf(playerCoins));
        hub.event.questCount = 0;
        hub.fight.count = 0;

        playerCurrentStats();
    }

    public void playerCurrentStats() {
        //REMOVING HEARTH ICONS
        int i = 0;
        while (i < 10) {
            hub.ui.labelHp[i].setVisible(false);
            i++;
        }

        //Setting visible hearth icons equal to player's HP
        double currentHp = playerHp;
        while (currentHp >= 0) { // FIXME: 07.01.2022 >0 -> 9HP, >=0 -> 10HP but it's actually 9
            hub.ui.labelHp[(int) currentHp].setVisible(true);
            currentHp--;
        }

        //PLAYER ITEMS CHECK
        hub.ui.labelWeapon.setVisible(hand);
        hub.ui.labelShield.setVisible(shield);
        hub.ui.labelQuestItem.setVisible(coin);
        hub.ui.labelChestArmor.setVisible(torso);

        hub.ui.labelBeer.setVisible(beer);
        hub.ui.labelLiquor.setVisible(liquor);

        hub.ui.buttonMapItem1.setVisible(mapItem1);
        hub.ui.buttonMapItem2.setVisible(mapItem2);
        hub.ui.buttonMapItem3.setVisible(mapItem3);
        hub.ui.buttonMapItem4.setVisible(mapItem4);

        if (sword) {
            playerDmg += 2;
        }
        if (torso) {
            playerDef += 20;
        }
        if (shield) {
            playerDef += 10;
        }
    }

}
