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
        if (hub.player.knife) {
            hub.playSoundEffect(hub.sound.swordSlash, false);
        } else if (hub.player.hand) {
            hub.playSoundEffect(hub.sound.punch, false);
        }
        hub.ui.healthBarProgress.setMaximum(enemies.entityMaxHp);
        int playerDmg = (random.nextInt(hub.player.playerDmg) + 1) * enemyDef;
        hub.ui.textMessage.setText("");
        enemies.setEntityHp(enemies.getEntityHp() - playerDmg);
        hub.ui.textMessage.append("You hit " + enemies.getEntityName() + " and gave him " + playerDmg + " dmg \n" + enemies.getEntityName() + ": " + enemies.getEntityHp() + " HP left \n");
        hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
        hub.ui.healthBarProgress.setValue(enemies.getEntityHp());

        if (enemies.getEntityHp() <= 0) {
            hub.player.playerCurrentStats();
            count++;
            //TEST BAR hub.barCheck.button.setVisible(false);

            switch (count) {
                case 1 -> {
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelWolf.getX(), hub.ui.labelWolf.getY(), 200, 50);
                    hub.ui.setMoneyCount(10);
                    hub.fight.setEnemy(Enemies.WOLF);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    //hub.ui.healthBarProgress.setMaximum(enemies.entityMaxHp);
                    hub.ui.labelRat.setVisible(false);
                    hub.ui.labelWolf.setVisible(true);
                    winScreen();
                }
                case 2 -> {
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelKnight.getX(), hub.ui.labelKnight.getY(), 200, 50);
                    hub.ui.setMoneyCount(20);
                    hub.fight.setEnemy(Enemies.KNIGHT);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.labelWolf.setVisible(false);
                    hub.ui.labelKnight.setVisible(true);
                    winScreen();
                }
                case 3 -> {
                    hub.ui.textMessage.setText("You won chest! \n" +
                            "Look inside to find out what you got");
                    hub.ui.labelKnight.setVisible(false);
                    hub.ui.labelChest.setVisible(true);
                    hub.ui.panelHeathBar.setVisible(false);
                }
                case 4 -> {
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelOgre.getX(), hub.ui.labelOgre.getY(), 200, 50);
                    hub.ui.setMoneyCount(50);
                    hub.fight.setEnemy(Enemies.OGRE);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.labelChest.setVisible(false);
                    hub.ui.labelOgre.setVisible(true);
                    winScreen();
                }
                case 5 -> {
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelWizard.getX(), hub.ui.labelWizard.getY(), 200, 50);
                    hub.ui.setMoneyCount(50);
                    hub.fight.setEnemy(Enemies.WIZARD);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.labelOgre.setVisible(false);
                    hub.ui.labelWizard.setVisible(true);
                    winScreen();
                }
                case 6 -> {
                    hub.ui.setMoneyCount(100);
                    hub.ui.labelWizard.setVisible(false);
                    //hub.ui.label.setVisible(true);
                    winScreen();
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
        Enemies.RAT.setEntityHp(Enemies.RAT.entityMaxHp);
        Enemies.WOLF.setEntityHp(Enemies.WOLF.entityMaxHp);
        Enemies.KNIGHT.setEntityHp(Enemies.KNIGHT.entityMaxHp);
        Enemies.OGRE.setEntityHp(Enemies.OGRE.entityMaxHp);
        Enemies.WIZARD.setEntityHp(Enemies.WIZARD.entityMaxHp);
        Enemies.MIRROR.setEntityHp(Enemies.MIRROR.entityMaxHp);
    }

    public void restartEnemyHp() {
        System.out.println(enemies.getEntityName());
        enemies.setEntityHp(enemies.entityMaxHp);
        hub.ui.healthBarProgress.setValue(enemies.entityMaxHp);
        hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp);
        System.out.println(enemies.getEntityHp());
    }

    public void firstEncounter() {
        hub.ui.labelRat.setVisible(true);
        hub.ui.labelChest.setVisible(false);
        hub.ui.labelWolf.setVisible(false);
        hub.ui.labelKnight.setVisible(false);
        hub.ui.labelOgre.setVisible(false);
        hub.ui.labelWizard.setVisible(false);

        hub.ui.panelHeathBar.setBounds(hub.ui.labelRat.getX() + 50, hub.ui.labelRat.getY() - 20, 200, 30);
        hub.ui.healthBarProgress.setMaximum(Enemies.RAT.entityMaxHp);
        hub.ui.healthBarProgress.setValue(Enemies.RAT.entityMaxHp);
        hub.ui.healthBarProgress.setString(Enemies.RAT.entityHp + "/" + Enemies.RAT.entityMaxHp + "HP");
    }

}
