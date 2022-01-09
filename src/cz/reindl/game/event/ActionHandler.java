package cz.reindl.game.event;

import cz.reindl.game.GameHub;
import cz.reindl.game.entity.Enemies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {

    GameHub hub;

    public ActionHandler(GameHub gameHub) {
        this.hub = gameHub;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) { //User's clicked button action
            case "-" -> hub.ui.textMessage.setText("no action yet");
            case "restart" -> {
                hub.event.gameOverScreenApply();
                hub.event.spawnScreen();
            }
            case "am" -> hub.event.am();
            //Object interaction

            case "talkKnight" -> hub.event.guard();
            case "openChest" -> hub.event.chest();
            case "openTreasure" -> hub.event.treasure();
            case "enterPub" -> hub.event.pubDoor();
            //case "knockPub" -> hub.event;
            case "searchWell" -> hub.event.well();
            case "drinkBeer" -> hub.event.beer();
            case "drinkLiquor" -> hub.event.liquor();
            case "getQuest" -> hub.event.quests();
            case "tavernMenu" -> hub.event.tavernMenu();

            case "buyBeer" -> hub.event.buyBeer();
            case "buyLiquor" -> hub.event.buyLiquor();
            case "buyCheese" -> hub.event.buyCheese();
            case "buyPork" -> hub.event.buyPork();
            case "buyHolyWater" -> hub.event.buyHolyWater();

            case "buyKnife" -> hub.event.buyKnife();
            case "buyTorso" -> hub.event.buyTorso();
            case "buyOldKnife" -> hub.event.buyOldKnife();
            case "buyTorsoBasic" -> hub.event.buyTorsoBasic();
            case "buyTorsoBetter" -> hub.event.buyTorsoBetter();
            case "buyHelmet" -> hub.event.buyHelmet();
            case "buyWarHammer" -> hub.event.buyWarHammer();
            case "buyShieldBasic" -> hub.event.buyShieldBasic();

            case "stats" -> hub.event.stats();
            case "increaseDmg" -> hub.event.dmg();
            case "increaseHP" -> hub.event.hp();
            case "increaseArmor" -> hub.event.armor();
            //FIGHT SCENE

            case "runAway" -> {
                hub.event.scenePubInside();
                hub.fight.restartEnemyHp();
            }
            case "fightEnemy" -> {
                hub.fight.setEnemy(Enemies.RAT);
                hub.fight.attack();
            }
            case "fightEnemy2" -> {
                hub.fight.attack();
            }
            case "defend1" -> {
                hub.fight.setEnemy(Enemies.RAT);
                hub.fight.defend();
            }
            case "defend" -> {
                hub.fight.defend();
            }
            case "specialAttack" -> {
                hub.fight.specialAttack();
            }
            case "fightEnemy4" -> {
                hub.fight.setEnemy(Enemies.MIRROR); // FIXME: 03.01.2022 Delete
                hub.fight.attack();
            }
            //Location changes

            case "mainScreen1" -> hub.event.spawnScreen();
            case "mainScreen2" -> hub.event.sceneTownSquare();
            case "enterDungeon" -> hub.event.sceneDungeon();
            case "goTown2" -> hub.event.sceneTownSquare2();
            case "enterForge" -> hub.event.forgeTown();
            case "teleport" -> hub.event.chooseLocation();
            case "currentScreen" -> hub.event.currentScreen(1); // FIXME: 02.01.2022 current screen number

            default -> hub.ui.textMessage.setText("");
        }
    }
}
