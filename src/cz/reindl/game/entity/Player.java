package cz.reindl.game.entity;

import cz.reindl.game.GameHub;

public class Player {

    GameHub hub;

    public int playerMaxHp;
    public int playerHp;
    public int playerDmg;
    public int playerDef;

    public boolean knife;
    public boolean shield;
    public boolean coin;
    public boolean hand;
    public boolean torso;

    public Player(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void playerDefaultStats() {
        playerMaxHp = 10;
        playerHp = 4;
        playerDmg = 2;
        playerDef = 0;
        hand = true;
        knife = false;
        shield = false;
        coin = false;
        torso = false;

        playerCurrentStats();
    }

    public void playerCurrentStats() {
        //REMOVING HEARTH ICONS
        int i = 1;
        while (i < 10) {
            hub.ui.labelHp[i].setVisible(false);
            i++;
        }

        int currentHp = playerHp;
        while (currentHp >= 0) {
            hub.ui.labelHp[currentHp].setVisible(true);
            currentHp--;
        }

        //PLAYER ITEMS CHECK
        hub.ui.labelWeapon.setVisible(hand);
        hub.ui.labelShield.setVisible(shield);
        hub.ui.labelCoin.setVisible(coin);
        hub.ui.labelChestArmor.setVisible(torso);

        if (knife) {
            playerDmg = 5;
        } else if (torso) {
            playerDef = 1;
        }
    }

}
