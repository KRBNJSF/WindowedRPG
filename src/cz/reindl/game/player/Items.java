package cz.reindl.game.player;

import cz.reindl.game.GameHub;

import javax.swing.*;

public class Items {

    GameHub hub;

    public Items(GameHub gameHub) {
        this.hub = gameHub;
    }

    public JLabel labelSword, labelDagger, labelKnife; //Weapon
    public JLabel labelShield, labelShieldEx;          //Shield
    public JLabel labelCoin;                           //Quest item

    public JLabel[] labelItems;

}
