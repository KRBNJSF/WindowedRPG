package cz.reindl.game;

import cz.reindl.game.entity.Enemies;
import cz.reindl.game.event.ActionHandler;
import cz.reindl.game.event.Event;
import cz.reindl.game.entity.Player;
import cz.reindl.game.event.Fight;
import cz.reindl.game.ui.UserInterface;
import cz.reindl.game.utils.Utilities;
import res.Sound;

import java.io.File;

public class GameHub {

    public ActionHandler actionHandler = new ActionHandler(this);
    public UserInterface ui = new UserInterface(this);
    public Player player = new Player(this);
    public Event event = new Event(this);
    public Sound sound = new Sound(this);
    public Fight fight = new Fight(this);
    public Utilities utilities = new Utilities(this);

    public GameHub() {
        sound.currentMusic = sound.townMusic;
        playMusic(sound.currentMusic, true);

        player.playerDefaultStats();
        event.screen1(); //First executed scene method
    }

    public void playMusic(File file, Boolean loop) {
        sound.setFile(file);
        sound.playSound(file);
        sound.loopSound(loop);
    }

    public void stopMusic(File file) {
        sound.stopSound(file);
    }

}
