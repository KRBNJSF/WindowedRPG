package res;

import cz.reindl.game.GameHub;
import jaco.mp3.player.MP3Player;

import java.io.File;
import java.util.Timer;

public class Sound {

    GameHub hub;
    Timer timer = new Timer();

    public Sound(GameHub gameHub) {
        this.hub = gameHub;
    }

    MP3Player musicPlayer;
    MP3Player soundEffectPlayer;
    public File currentMusic;
    public File currentSoundEffect;

    //MUSIC
    public File townMusic = new File("src/res/storage/townMusic.mp3");
    public File shopMusic = new File("src/res/storage/shopMusic.mp3");
    public File mainTheme = new File("src/res/storage/mainTheme.mp3");
    public File bossTheme = new File("src/res/storage/bossTheme.mp3");

    //SOUND EFFECTS
    public File fightWin = new File("src/res/storage/fightWin.mp3");
    public File forge = new File("src/res/storage/forge.mp3");
    public File swordSlash = new File("src/res/storage/swordSlash.mp3");
    public File punch = new File("src/res/storage/punch.mp3");
    //CHARACTERS SPEECH

    //TEXT SPEECH
    public File pubGreet = new File("src/res/storage/pubGreet.mp3");

    //TEXT SOUND EFFECT
    public void prepareText() {
        hub.ui.textMessage.setText("");
    }

    //MUSIC SETTINGS
    public void setFile(File name) {
        try {
            musicPlayer = new MP3Player(name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void playSound(File name) {
        musicPlayer.play();
    }

    public void loopSound(Boolean loop) {
        musicPlayer.setRepeat(loop);
    }

    public void stopSound(File name) {
        musicPlayer.stop();
    }

    //SOUND EFFECTS SETTINGS
    public void setSoundEffect(File name) {
        try {
            soundEffectPlayer = new MP3Player(name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void playSoundEffect(File name) {
        soundEffectPlayer.play();
    }

    public void loopSoundEffect(Boolean loop) {
        soundEffectPlayer.setRepeat(loop);
    }

    public void stopSoundEffect(File name) {
        soundEffectPlayer.stop();
    }

}
