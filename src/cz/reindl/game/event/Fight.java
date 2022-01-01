package cz.reindl.game.event;

import cz.reindl.game.GameHub;
import cz.reindl.game.entity.Enemies;

import java.util.Random;

public class Fight {

    GameHub hub;
    Enemies enemies;
    Random random = new Random();

    public Fight(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void setEnemy(Enemies enemy) {
        enemies = enemy;
        attack();
    }

    public void attack() {
        int playerDmg = random.nextInt(hub.player.playerDmg);
        hub.ui.textMessage.setText("");
        hub.ui.textMessage.append("You hit " + enemies.getEntityName() + " and gave him " + playerDmg + " dmg ; " + enemies.getEntityHp() + " HP left \n");
        enemies.setEntityHp(enemies.getEntityHp() - playerDmg);

        if (enemies.getEntityHp() <= 0) {
            winScreen(Enemies.WOLF);
            hub.ui.textMessage.setText("You won");
        } else {
            int enemyDmg = random.nextInt(enemies.getEntityDmg());
            hub.player.playerHp -= enemyDmg;
            hub.player.playerCurrentStats();
            hub.ui.textMessage.append(enemies.getEntityName() + " gave you " + enemyDmg + " dmg ; " + hub.player.playerHp + " HP left");
            if (hub.player.playerHp <= 0) {
                hub.player.playerHp = 0;
                hub.player.playerCurrentStats();
                hub.event.gameOverScreen(4);
            }
        }
    }

    public void winScreen(Enemies enemy) {
        hub.ui.textMessage.setText("You won");
        hub.stopMusic(hub.sound.currentMusic);
        hub.event.scenePubInside();
        setEnemy(enemy);
    }
}
