package cz.reindl.game.event;

import cz.reindl.game.GameHub;

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
            case "talkKnight" -> hub.ui.textMessage.setText("Hello there");
            case "shopHut" -> hub.event.shop();
            case "openChest" -> hub.ui.textMessage.setText("You opened the chest and found a sword!");
            default -> hub.ui.textMessage.setText("");
        }
    }
}
