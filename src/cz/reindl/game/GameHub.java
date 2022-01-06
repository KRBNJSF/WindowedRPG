package cz.reindl.game;

import cz.reindl.game.event.ActionHandler;
import cz.reindl.game.event.Event;
import cz.reindl.game.entity.Player;
import cz.reindl.game.event.Fight;
import cz.reindl.game.ui.UserInterface;
import cz.reindl.game.utils.ProgressBarCheck;
import cz.reindl.game.utils.Utilities;
import res.Sound;

import javax.swing.plaf.ProgressBarUI;
import java.io.File;

public class GameHub {

    public ActionHandler actionHandler = new ActionHandler(this);
    public UserInterface ui = new UserInterface(this);
    public Player player = new Player(this);
    public Event event = new Event(this);
    public Sound sound = new Sound(this);
    public Fight fight = new Fight(this);
    public Utilities utils = new Utilities(this);
    public ProgressBarCheck barCheck = new ProgressBarCheck(this);

    public GameHub() {
        sound.currentMusic = sound.townMusic;
        playMusic(sound.currentMusic, true);

        //event.loadingScreen();
        //ui.setLoadingScreen(1);

        //barCheck.progressBarCheck();
        event.spawnScreen(); //First executed scene method
        player.playerDefaultStats();
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
