package cz.reindl.game.event;

import cz.reindl.game.GameHub;

public class Event {

    GameHub hub;

    public Event(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void shop() { //Changing the scene
        hub.ui.textMessage.setText("Welcome in pub");
    }

    public void screen1() {
        hub.ui.panelBackground[1].setVisible(true);
        hub.ui.panelBackground[2].setVisible(false);
        hub.ui.textMessage.setText("Forest");
    }

    public void screen2() {
        hub.ui.panelBackground[1].setVisible(false);
        hub.ui.panelBackground[2].setVisible(true);
        hub.ui.textMessage.setText("Pub");
    }

}