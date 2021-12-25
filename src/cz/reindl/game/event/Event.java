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

}