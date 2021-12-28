package cz.reindl.game;

import cz.reindl.game.event.ActionHandler;
import cz.reindl.game.event.Event;
import cz.reindl.game.player.Player;
import cz.reindl.game.ui.UserInterface;

public class GameHub {

    public ActionHandler actionHandler = new ActionHandler(this);
    public UserInterface ui = new UserInterface(this);
    public Player player = new Player(this);
    public Event event = new Event(this);

    public GameHub() {
        player.playerDefaultStats();
        event.screen1(); //First executed method
    }

}
