package cz.reindl.game.entity;

import cz.reindl.game.GameHub;

public class Player {

    GameHub hub;

    public int playerMaxHp;
    public int playerHp;
    public int playerDmg;
    public int playerDef;
    public int playerCoins;

    public boolean hand;
    public boolean knife;

    public boolean shield;

    public boolean torso;

    public boolean coin;
    public boolean key;

    public boolean beer;
    public boolean liquor;


    public Player(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void playerDefaultStats() {
        playerMaxHp = 10;
        playerHp = 5;
        playerDmg = 2;
        playerDef = 0;
        playerCoins = 0;
        hand = true;
        knife = false;
        shield = false;
        torso = false;
        coin = false;
        key = false;

        beer = false;
        liquor = false;

        hub.ui.labelWeapon.setIcon(hub.ui.jarImg("icon/hand.png"));
        hub.ui.labelQuestItem.setIcon(hub.ui.jarImg("icon/coin.png"));
        hub.ui.moneyCount.setText(String.valueOf(playerCoins));
        hub.event.questCount = 0;

        playerCurrentStats();
    }

    public void playerCurrentStats() {
        //REMOVING HEARTH ICONS
        int i = 1;
        while (i < 10) {
            hub.ui.labelHp[i].setVisible(false);
            i++;
        }

        //Setting visible hearth icons equal to player's HP
        int currentHp = playerHp;
        while (currentHp >= 0) {
            hub.ui.labelHp[currentHp].setVisible(true);
            currentHp--;
        }

        //PLAYER ITEMS CHECK
        hub.ui.labelWeapon.setVisible(hand);
        hub.ui.labelShield.setVisible(shield);
        hub.ui.labelQuestItem.setVisible(coin);
        hub.ui.labelChestArmor.setVisible(torso);
        hub.ui.labelBeer.setVisible(beer);
        hub.ui.labelLiquor.setVisible(liquor);

        if (knife) {
            playerDmg += 2;
        }
        if (torso) {
            playerDef += 1;
        }
        if (shield) {
            playerDef += 2;
        }
    }

}
