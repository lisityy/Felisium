package cvut.fel.pjv.pimenol1.utils;

import cvut.fel.pjv.pimenol1.main.Felisium;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.logging.Level;

public class MusicPlayer {

    private Clip clip;

    /**
     * Plays the specified audio file.
     *
     * @param filename the filename of the audio file to be played
     */
    public void play(String filename) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(filename));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            Felisium.logger.log(Level.SEVERE, "Error playing music: {}", e.getMessage());
        }
    }

    /**
     * Stops the currently playing audio.
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Changes the intensity of the currently playing audio.
     *
     * @param intensity the intensity value to set
     */
    public void changeIntensity(float intensity) {
        if (clip != null && clip.isOpen()) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minGain = gainControl.getMinimum();
            float maxGain = gainControl.getMaximum();
            float gain = (maxGain - minGain) * intensity + minGain;
            gainControl.setValue(gain);
        }
    }
}