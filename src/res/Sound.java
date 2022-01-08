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
    public File moneyEarn = new File("src/res/storage/moneyEarn.mp3");
    public File coinWin = new File("src/res/storage/coinWin.mp3");
    public File moneyEarnMore = new File("src/res/storage/moneyEarnMore.mp3");
    public File healEffect = new File("src/res/storage/healEffect.mp3");
    public File fullHealEffect = new File("src/res/storage/fullHealEffect.mp3");
    public File daggerHit = new File("src/res/storage/daggerHit.mp3");
    public File ratBite = new File("src/res/storage/ratBite.mp3");
    public File wolfAttack = new File("src/res/storage/wolfAttack.mp3");
    public File fireBall = new File("src/res/storage/fireBall.mp3");
    public File chestOpen = new File("src/res/storage/chestOpen.mp3");
    public File finalChestOpen = new File("src/res/storage/finalChestOpen.mp3");
    public File maceHit = new File("src/res/storage/maceHit.mp3");
    public File levelUp = new File("src/res/storage/levelUp.mp3");
    public File loseSound = new File("src/res/storage/loseSound.mp3");
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
