package cz.reindl.game.event;

import cz.reindl.game.GameHub;
import cz.reindl.game.entity.Enemies;

import java.util.Random;

public class Fight {

    GameHub hub;
    Enemies enemies;
    Random random = new Random();
    private int playerDef = 1;
    private int enemyDef = 1;
    public int count = 0;

    public Fight(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void setEnemy(Enemies enemy) {
        enemies = enemy;
    }

    public void attack() {
        hub.player.playerCurrentStats();
        int playerDmg = (random.nextInt(hub.player.playerDmg) + 1) * enemyDef;
        hub.ui.textMessage.setText("");
        enemies.setEntityHp(enemies.getEntityHp() - playerDmg);
        hub.ui.textMessage.append("You hit " + enemies.getEntityName() + " and gave him " + playerDmg + " dmg \n" + enemies.getEntityName() + ": " + enemies.getEntityHp() + " HP left \n");

        if (enemies.getEntityHp() <= 0) {
            hub.player.playerCurrentStats();
            count++;
            winScreen();
            hub.ui.textMessage.setText("You won");

            switch (count) {
                case 1 -> {
                    hub.fight.setEnemy(Enemies.WOLF);
                    hub.ui.labelRat.setVisible(false);
                    hub.ui.labelWolf.setVisible(true);
                }
                case 2 -> {
                    hub.fight.setEnemy(Enemies.KNIGHT);
                    hub.ui.labelWolf.setVisible(false);
                    hub.ui.labelKnight.setVisible(true);
                }
                case 3 -> {
                    hub.ui.textMessage.setText("You won chest! \n" +
                            "Look inside to find out what you got");
                    hub.ui.labelKnight.setVisible(false);
                    hub.ui.labelChest.setVisible(true);
                }
                case 4 -> {
                    hub.fight.setEnemy(Enemies.OGRE);
                    hub.ui.labelChest.setVisible(false);
                    hub.ui.labelOgre.setVisible(true);
                }
                case 5 -> {
                    hub.fight.setEnemy(Enemies.WIZARD);
                    hub.ui.labelOgre.setVisible(false);
                    hub.ui.labelWizard.setVisible(true);
                }
                case 6 -> {
                    hub.ui.labelWizard.setVisible(false);
                    //hub.ui.label.setVisible(true);
                }
            }
        } else {
            int enemyDmg = (random.nextInt(enemies.getEntityDmg()) + 1) * playerDef;
            hub.player.playerHp -= enemyDmg;
            hub.player.playerCurrentStats();
            hub.ui.textMessage.append(enemies.getEntityName() + " gave you " + enemyDmg + " dmg \n " + "You: " + hub.player.playerHp + " HP left");
            if (hub.player.playerHp <= 0) {
                hub.player.playerHp = 1;
                hub.player.playerCurrentStats();
                hub.event.gameOverScreen(4);
                hub.event.gameOverScreen(3);
                hub.event.gameOverScreen(6);
                restartEnemies();
            }
        }

    }

    public void winScreen() {
        hub.ui.textMessage.setText("You won");
        hub.stopMusic(hub.sound.currentMusic);
        hub.event.scenePubInside();
    }

    public void restartEnemies() {
        Enemies.RAT.setEntityHp(5);
        Enemies.WOLF.setEntityHp(5);
        Enemies.KNIGHT.setEntityHp(5);
        Enemies.OGRE.setEntityHp(5);
        Enemies.WIZARD.setEntityHp(5);
        Enemies.MIRROR.setEntityHp(5);
    }

    public void restartEnemyHp() {
        System.out.println(enemies.getEntityName());
        enemies.setEntityHp(5);
        System.out.println(enemies.getEntityHp());
    }

    public void firstEncounter() {
        hub.ui.labelRat.setVisible(true);
        hub.ui.labelChest.setVisible(false);
        hub.ui.labelWolf.setVisible(false);
        hub.ui.labelKnight.setVisible(false);
        hub.ui.labelOgre.setVisible(false);
        hub.ui.labelWizard.setVisible(false);
    }

}
