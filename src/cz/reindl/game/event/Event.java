package cz.reindl.game.event;

import cz.reindl.game.GameHub;
import res.Sound;

public class Event {

    GameHub hub;

    public Event(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void gameOverScreen(int currentScreen) {
        hub.ui.panelBackground[currentScreen].setVisible(false);
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

    public void screen1() {
        hub.ui.panelBackground[1].setVisible(true);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.textMessage.setText("Forest");

        hub.stopMusic(hub.sound.currentMusic);
        hub.sound.currentMusic = hub.sound.townMusic;
        hub.playMusic(hub.sound.currentMusic, true);
    }

    public void screen2() {
        hub.ui.panelBackground[1].setVisible(false);
        hub.ui.panelBackground[2].setVisible(true);
        hub.ui.panelBackground[3].setVisible(false);
        hub.ui.textMessage.setText("Town");

        hub.stopMusic(hub.sound.currentMusic);
        hub.sound.currentMusic = hub.sound.shopMusic;
        hub.playMusic(hub.sound.currentMusic, true);
        // FIXME: 30.12.2021 hub.playMusic(hub.sound.forge, true);
    }

    public void screen3() {
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.panelBackground[3].setVisible(true);
        hub.ui.textMessage.setText("Pub");

        hub.stopMusic(hub.sound.currentMusic);
        hub.sound.currentMusic = hub.sound.mainTheme;
        hub.playMusic(hub.sound.currentMusic, true);
        System.out.println(hub.sound.currentMusic);
    }

    public void shop() { //Changing the scene
        hub.ui.textMessage.setText("Welcome in pub");
        if (hub.player.playerHp != hub.player.playerMaxHp - 1) {
            hub.ui.textMessage.setText("You recovered 1 hp");
            hub.player.playerHp++;
            hub.player.playerCurrentStats();
            hub.playMusic(hub.sound.pubGreet, false);
        } else {
            hub.ui.textMessage.setText("You are at full health");
        }
    }

    public void chest() {
        if (!hub.player.knife) {
            hub.ui.textMessage.setText("You opened the chest and found a knife!");
            hub.ui.labelWeapon.setIcon(hub.ui.imgIcon("icon/knife.png"));
            hub.player.knife = true;
            hub.player.playerCurrentStats();
        } else {
            hub.ui.textMessage.setText("It's empty");
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
                    hub.ui.textMessage.setText("You died");
                    hub.event.gameOverScreen(1);
                }
            } else if (hub.player.knife) {
                hub.ui.textMessage.setText("You won and earned shield");
                hub.player.shield = true;
            }
            hub.player.playerCurrentStats();
        } else {
            hub.ui.textMessage.setText("Nothing happened");
        }
    }

    public void pubDoor() {
        if (!hub.player.coin) {
            hub.ui.textMessage.setText("Coin is needed to enter");
        } else {
            screen3();
        }
    }

    public void well() {
        if (!hub.player.coin) {
            hub.ui.textMessage.setText("You found a coin");
            hub.player.coin = true;
            hub.player.playerCurrentStats();
        } else {
            hub.ui.textMessage.setText("There's nothing");
        }
    }
}