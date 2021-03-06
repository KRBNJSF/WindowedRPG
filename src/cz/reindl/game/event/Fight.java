package cz.reindl.game.event;

import cz.reindl.game.GameHub;
import cz.reindl.game.entity.Enemies;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class Fight {

    GameHub hub;
    Enemies enemies;
    Random random = new Random();
    public DecimalFormat df = new DecimalFormat("#.###");
    public double playerDefPercent = 1;
    private int enemyDef = 1;
    public double defChance;
    public int count = 0;

    public Fight(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void setEnemy(Enemies enemy) {
        enemies = enemy;
    }

    public void attack() {
        df.setRoundingMode(RoundingMode.CEILING);
        checkArmor();
        if (defChance == 1) {
            hub.player.playerCurrentStats();
            if (hub.player.sword) {
                hub.playSoundEffect(hub.sound.swordSlash, false);
            } else if (hub.player.hand) {
                hub.playSoundEffect(hub.sound.punch, false);
            }
            hub.ui.healthBarProgress.setMaximum(enemies.entityMaxHp);
            int playerDmg = (random.nextInt(hub.player.playerCurrentMaxDmg) + hub.player.playerMinDmg) * enemyDef * 3;
            hub.ui.textMessage.setText("");
            enemies.setEntityHp(enemies.getEntityHp() - playerDmg);
            hub.ui.textMessage.append("You hit " + enemies.getEntityName() + " and gave him " + playerDmg + " dmg \n" + enemies.getEntityName() + ": " + enemies.getEntityHp() + " HP left \n");
            hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
            hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
        } else {
            hub.player.playerCurrentStats();
            if (hub.player.sword) {
                if (count > 3) {
                    hub.playSoundEffect(hub.sound.swordSlash, false);
                } else {
                    hub.sound.currentSoundEffect = hub.sound.chestOpen;
                }
            } else if (hub.player.knife) {
                hub.playSoundEffect(hub.sound.daggerHit, false);
            } else if (hub.player.hand) {
                hub.playSoundEffect(hub.sound.punch, false);
            }
            hub.ui.healthBarProgress.setMaximum(enemies.entityMaxHp);
            int playerDmg = (random.nextInt(hub.player.playerCurrentMaxDmg) + hub.player.playerMinDmg) * enemyDef;
            hub.ui.textMessage.setText("");
            enemies.setEntityHp(enemies.getEntityHp() - playerDmg);
            hub.ui.textMessage.append("You hit " + enemies.getEntityName() + " and gave him " + playerDmg + " dmg \n" + enemies.getEntityName() + ": " + enemies.getEntityHp() + " HP left \n");
            hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
            hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
        }
        if (enemies.getEntityHp() <= 0) {
            hub.player.playerCurrentStats();
            count++;
            //TEST BAR hub.barCheck.button.setVisible(false);

            switch (count) {
                case 1 -> {
                    hub.sound.currentSoundEffect = hub.sound.wolfAttack;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelWolf.getX() + 105, hub.ui.labelWolf.getY() + 15, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setExpCount(60);
                    hub.ui.setMoneyCount(5);
                    hub.fight.setEnemy(Enemies.WOLF);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    //hub.ui.healthBarProgress.setMaximum(enemies.entityMaxHp);
                    hub.ui.labelRat.setVisible(false);
                    hub.ui.labelWolf.setVisible(true);
                    winScreen();
                }
                case 2 -> {
                    hub.sound.currentSoundEffect = hub.sound.swordSlash;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelKnight.getX() + 105, hub.ui.labelKnight.getY() + 15, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setExpCount(80);
                    hub.ui.setMoneyCount(5);
                    hub.fight.setEnemy(Enemies.KNIGHT);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.labelWolf.setVisible(false);
                    hub.ui.labelKnight.setVisible(true);
                    winScreen();
                }
                case 3 -> {
                    hub.sound.currentSoundEffect = hub.sound.chestOpen;
                    hub.ui.textMessage.setText("You won chest! \n" +
                            "Look inside to find out what you got");
                    hub.playSoundEffect(hub.sound.moneyEarnMore, false);
                    hub.ui.labelKnight.setVisible(false);
                    hub.ui.labelChest.setVisible(true);
                    hub.ui.panelHeathBar.setVisible(false);
                }
                case 4 -> {
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelOgre.getX() + 105, hub.ui.labelOgre.getY() + 15, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setExpCount(100);
                    hub.ui.setMoneyCount(20);
                    hub.fight.setEnemy(Enemies.OGRE);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.labelChest.setVisible(false);
                    hub.ui.labelOgre.setVisible(true);
                    winScreen();
                }
                case 5 -> {
                    hub.sound.currentSoundEffect = hub.sound.fireBall;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelWizard.getX() + 105, hub.ui.labelWizard.getY() + 15, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setExpCount(100);
                    hub.ui.setMoneyCount(20);
                    hub.fight.setEnemy(Enemies.WIZARD);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.labelOgre.setVisible(false);
                    hub.ui.labelWizard.setVisible(true);
                    winScreen();
                }
                case 6 -> {
                    hub.sound.currentSoundEffect = hub.sound.maceHit;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelWarriorOgre.getX() + 180, hub.ui.labelWarriorOgre.getY() + 50, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setMoneyCount(100);
                    hub.fight.setEnemy(Enemies.WARRIOROGRE);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.setExpCount(250);
                    hub.ui.labelWizard.setVisible(false);
                    hub.ui.labelWarriorOgre.setVisible(true);
                    winScreen();
                }
                case 7 -> {
                    hub.sound.currentSoundEffect = hub.sound.chestOpen;
                    hub.ui.textMessage.setText("You won chest! \n" +
                            "Look inside to find out what you got");
                    hub.playSoundEffect(hub.sound.moneyEarnMore, false);
                    hub.ui.labelWarriorOgre.setVisible(false);
                    hub.ui.panelHeathBar.setVisible(false);
                    hub.ui.labelChestFinal.setVisible(true);
                }
                case 8 -> {
                    hub.sound.currentSoundEffect = hub.sound.maceHit;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelCoffinMonster.getX() + 180, hub.ui.labelCoffinMonster.getY() + 50, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setMoneyCount(100);
                    hub.fight.setEnemy(Enemies.OCCULTISTS);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.setExpCount(20);
                    hub.ui.labelChestFinal.setVisible(false);
                    hub.ui.labelCoffinMonster.setVisible(true);
                    winScreen();
                }
                case 9 -> {
                    hub.sound.currentSoundEffect = hub.sound.maceHit;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelGhastliness.getX() + 180, hub.ui.labelGhastliness.getY() + 50, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setMoneyCount(100);
                    hub.fight.setEnemy(Enemies.GHASTLINESS);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.setExpCount(20);
                    hub.ui.labelCoffinMonster.setVisible(false);
                    hub.ui.labelGhastliness.setVisible(true);
                    winScreen();
                }
                case 10 -> {
                    hub.sound.currentSoundEffect = hub.sound.maceHit;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelDoubleMonster.getX() + 180, hub.ui.labelDoubleMonster.getY() + 50, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setMoneyCount(100);
                    hub.fight.setEnemy(Enemies.TWINS);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.setExpCount(20);
                    hub.ui.labelGhastliness.setVisible(false);
                    hub.ui.labelDoubleMonster.setVisible(true);
                    winScreen();
                }
                case 11 -> {
                    hub.sound.currentSoundEffect = hub.sound.maceHit;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelWizard2.getX() + 180, hub.ui.labelWizard2.getY() + 50, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setMoneyCount(100);
                    hub.fight.setEnemy(Enemies.ESSENCEWIZARD);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.setExpCount(20);
                    hub.ui.labelDoubleMonster.setVisible(false);
                    hub.ui.labelWizard2.setVisible(true);
                    winScreen();
                }
                case 12 -> {
                    hub.sound.currentSoundEffect = hub.sound.maceHit;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelWitch.getX() + 180, hub.ui.labelWitch.getY() + 50, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setMoneyCount(100);
                    hub.fight.setEnemy(Enemies.WITCH);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.setExpCount(20);
                    hub.ui.labelWizard2.setVisible(false);
                    hub.ui.labelWitch.setVisible(true);
                    winScreen();
                }
                case 13 -> {
                    hub.sound.currentSoundEffect = hub.sound.maceHit;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelFrogMonster.getX() + 180, hub.ui.labelFrogMonster.getY() + 50, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setMoneyCount(100);
                    hub.fight.setEnemy(Enemies.TOADWARRIOR);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.setExpCount(20);
                    hub.ui.labelWitch.setVisible(false);
                    hub.ui.labelFrogMonster.setVisible(true);
                    winScreen();
                }
                case 14 -> {
                    hub.sound.currentSoundEffect = hub.sound.maceHit;
                    hub.ui.panelHeathBar.setBounds(hub.ui.labelSpiderMonster.getX() + 180, hub.ui.labelSpiderMonster.getY() + 50, 200, 50);
                    hub.playSoundEffect(hub.sound.moneyEarn, false);
                    hub.ui.setMoneyCount(100);
                    hub.fight.setEnemy(Enemies.SPIDER);
                    hub.ui.healthBarProgress.setValue(enemies.getEntityHp());
                    hub.ui.healthBarProgress.setString(enemies.getEntityHp() + "/" + enemies.entityMaxHp + "HP");
                    hub.ui.setExpCount(20);
                    hub.ui.labelFrogMonster.setVisible(false);
                    hub.ui.labelSpiderMonster.setVisible(true);
                    winScreen();
                }
            }
        } else {
            hub.playSoundEffect(hub.sound.currentSoundEffect, false);
            double enemyDmg = (random.nextInt(enemies.getEntityMaxDmg()) + enemies.entityMinDmg) * playerDefPercent;
            hub.player.playerHp -= enemyDmg;
            hub.player.playerCurrentStats();
            hub.ui.textMessage.append(enemies.getEntityName() + " gave you " + df.format(enemyDmg) + " dmg \n " + "You: " + df.format(hub.player.playerHp) + " HP left");
            if (hub.player.playerHp <= 0) {
                hub.player.playerHp = 1;
                hub.player.playerCurrentStats();
                hub.event.gameOverScreen(4);
                hub.event.gameOverScreen(3);
                hub.event.gameOverScreen(2);
                hub.event.gameOverScreen(6);
                restartEnemies();
            }
        }

    }

    public void winScreen() {
        hub.ui.textMessage.setText("You won");
        hub.stopMusic(hub.sound.currentMusic);
        hub.playSoundEffect(hub.sound.fightWin, false);
        hub.event.scenePubInside();
    }

    public void restartEnemies() {
        Enemies.RAT.setEntityHp(Enemies.RAT.entityMaxHp);
        Enemies.WOLF.setEntityHp(Enemies.WOLF.entityMaxHp);
        Enemies.KNIGHT.setEntityHp(Enemies.KNIGHT.entityMaxHp);
        Enemies.OGRE.setEntityHp(Enemies.OGRE.entityMaxHp);
        Enemies.WIZARD.setEntityHp(Enemies.WIZARD.entityMaxHp);
        Enemies.WARRIOROGRE.setEntityHp(Enemies.WARRIOROGRE.entityMaxHp);
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
        hub.sound.currentSoundEffect = hub.sound.ratBite;
        hub.ui.labelRat.setVisible(true);
        hub.ui.labelChest.setVisible(false);
        hub.ui.labelWolf.setVisible(false);
        hub.ui.labelKnight.setVisible(false);
        hub.ui.labelOgre.setVisible(false);
        hub.ui.labelWizard.setVisible(false);
        hub.ui.labelWizard2.setVisible(false);
        hub.ui.labelSpiderMonster.setVisible(false);
        hub.ui.labelDoubleMonster.setVisible(false);
        hub.ui.labelWitch.setVisible(false);
        hub.ui.labelCoffinMonster.setVisible(false);
        hub.ui.labelFrogMonster.setVisible(false);
        hub.ui.labelGhastliness.setVisible(false);
        hub.ui.labelChestFinal.setVisible(false);
        hub.ui.labelChestFinal.setVisible(false);
        hub.ui.labelWarriorOgre.setVisible(false);

        hub.ui.panelHeathBar.setBounds(hub.ui.labelRat.getX() + 50, hub.ui.labelRat.getY() - 20, 200, 30);
        hub.ui.healthBarProgress.setMaximum(Enemies.RAT.entityMaxHp);
        hub.ui.healthBarProgress.setValue(Enemies.RAT.entityMaxHp);
        hub.ui.healthBarProgress.setString(Enemies.RAT.entityHp + "/" + Enemies.RAT.entityMaxHp + "HP");
    }

    public void checkArmor() {
        System.out.println(hub.player.playerMinDmg + " - " + hub.player.playerMaxDmg + " - " + hub.player.playerCurrentMaxDmg);
        if (hub.player.playerDef >= hub.player.playerMaxDef) {
            playerDefPercent = 0.5;
            System.out.println("0.5, Maximum armor value reached " + playerDefPercent);
        } else if (hub.player.playerDef > 1 && hub.player.playerDef <= 10) {
            playerDefPercent = 0.9;
            System.out.println("0.9 - " + playerDefPercent);
        } else if (hub.player.playerDef > 10 && hub.player.playerDef < 20) {
            playerDefPercent = 0.8;
            System.out.println("0.8 - " + playerDefPercent);
        } else if (hub.player.playerDef >= 20 && hub.player.playerDef < 30) {
            playerDefPercent = 0.7;
            System.out.println("0.7 - " + playerDefPercent);
        } else if (hub.player.playerDef >= 30 && hub.player.playerDef < 40) {
            playerDefPercent = 0.6;
            System.out.println("0.6 - " + playerDefPercent);
        } else {
            playerDefPercent = 1;
            System.out.println("1 " + playerDefPercent);
        }
    }

    public void defend() {
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        defChance = random.nextDouble(1);
        if (defChance >= 0.6) {
            if (defChance == 1) {
                attack();
            }
            if (hub.player.playerHp < hub.player.playerMaxHp - 1) {
                hub.playSoundEffect(hub.sound.healEffect, false);
                hub.ui.textMessage.setText("");
                hub.ui.textMessage.append("You gained 1HP");
                hub.player.playerHp += 1;
                hub.player.playerCurrentStats();
            } else {
                hub.playSoundEffect(hub.sound.coinWin, false);
                hub.ui.textMessage.setText("");
                hub.ui.textMessage.append("You earned 10 coins");
                hub.ui.setMoneyCount(10);
            }
        } else {
            hub.ui.textMessage.setText("");
            double enemyDmg = (random.nextInt(enemies.getEntityMaxDmg()) + 1) * playerDefPercent;
            System.out.println(enemies.getEntityName() + " " + df.format(enemyDmg));
            hub.player.playerHp -= enemyDmg;
            hub.player.playerCurrentStats();
            hub.ui.textMessage.append(enemies.getEntityName() + " gave you " + df.format(enemyDmg) + " dmg \n " + "You: " + df.format(hub.player.playerHp) + " HP left");
            if (hub.player.playerHp <= 0) {
                hub.player.playerHp = 1;
                hub.player.playerCurrentStats();
                hub.event.gameOverScreen(4);
                hub.event.gameOverScreen(3);
                hub.event.gameOverScreen(2);
                hub.event.gameOverScreen(6);
                restartEnemies();
            }
        }
    }

    public void specialAttack() {
        hub.ui.textMessage.setText("coming soon");
    }
}
