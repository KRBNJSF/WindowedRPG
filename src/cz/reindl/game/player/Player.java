package cz.reindl.game.player;

import cz.reindl.game.GameHub;

public class Player {

    GameHub hub;

    public int playerMaxHp;
    public int playerHp;

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

        while (playerHp != 0) {
            hub.ui.labelHp[playerHp].setVisible(true);
            playerHp--;
        }

        //PLAYER ITEMS CHECK
        hub.ui.labelHand.setVisible(hand);
        hub.ui.labelShield.setVisible(shield);
        hub.ui.labelCoin.setVisible(coin);
        hub.ui.labelChestArmor.setVisible(torso);
    }

}
