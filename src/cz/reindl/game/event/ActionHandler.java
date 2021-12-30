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
                hub.event.screen1();
            }
            //Object interaction

            case "talkKnight" -> hub.event.guard();
            case "openChest" -> hub.event.chest();
            case "enterPub" -> hub.event.pubDoor();
            case "searchWell" -> hub.event.well();
            case "fightEnemy" -> hub.fight.setEnemy(Enemies.BAT);
            //Location changes

            case "shopHut" -> hub.event.shop();
            case "mainScreen1" -> hub.event.screen1();
            case "mainScreen2" -> hub.event.screen2();
            default -> hub.ui.textMessage.setText("");
        }
    }
}
