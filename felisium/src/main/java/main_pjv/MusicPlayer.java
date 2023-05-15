package main_pjv;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicPlayer {

    private Clip clip;

    public void play(String filename) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(filename));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

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
