package res;

import cz.reindl.game.GameHub;
import jaco.mp3.player.MP3Player;

import java.io.File;

public class Sound {
    GameHub hub;

    public Sound(GameHub gameHub) {
        this.hub = gameHub;
    }

    MP3Player player;
    public File currentMusic;
    public File currentSoundEffect;

    //MUSIC
    public File townMusic = new File("src/res/storage/townMusic.mp3");
    public File shopMusic = new File("src/res/storage/shopMusic.mp3");
    public File mainTheme = new File("src/res/storage/mainTheme.mp3");

    //SOUND EFFECTS
    public File fightWin = new File("src/res/storage/fightWin.mp3");
    public File forge = new File("src/res/storage/forge.mp3");
    //CHARACTERS SPEECH

    //TEXT SPEECH
    public File pubGreet = new File("src/res/storage/pubGreet.mp3");

    //MUSIC SETTINGS
    public void setFile(File name) {
        try {
            player = new MP3Player(name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void playSound(File name) {
        player.play();
    }

    public void loopSound(Boolean loop) {
        player.setRepeat(loop);
    }

    public void stopSound(File name) {
        player.stop();
    }

}
