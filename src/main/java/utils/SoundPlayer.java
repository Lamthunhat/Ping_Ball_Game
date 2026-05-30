package utils;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class SoundPlayer {
    private static Clip backgroundClip; // Stores background music Clip for control
    private static boolean isSoundEnabled = true; // Sound status (enabled by default)

    // Play non-looping sound (used for hit.wav, game_over.wav)
    public static void playSound(String path) {
        if (!isSoundEnabled) {
            return; // Do not play if sound is disabled
        }
        try {
            InputStream audioSrc = SoundPlayer.class.getResourceAsStream(path);
            if (audioSrc == null) {
                System.err.println("Không tìm thấy file âm thanh trong classpath: " + path);
                return;
            }
            BufferedInputStream bufferedStream = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Định dạng âm thanh không được hỗ trợ: " + path);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Không thể truy cập thiết bị âm thanh: " + path);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Lỗi khác khi phát âm thanh: " + path);
            e.printStackTrace();
        }
    }

    // Play looping background music
    public static void playBackgroundMusic(String path) {
        if (!isSoundEnabled) {
            return; // Do not play if sound is disabled
        }
        try {
            InputStream audioSrc = SoundPlayer.class.getResourceAsStream(path);
            if (audioSrc == null) {
                System.err.println("Không tìm thấy file âm thanh trong classpath: " + path);
                return;
            }
            BufferedInputStream bufferedStream = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedStream);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); // Infinite loop
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Định dạng âm thanh không được hỗ trợ: " + path);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Không thể truy cập thiết bị âm thanh: " + path);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Lỗi khác khi phát nhạc nền: " + path);
            e.printStackTrace();
        }
    }

    // Stop background music
    public static void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
            System.out.println("Đã dừng nhạc nền.");
        }
    }

    // Toggle sound on/off
    public static void toggleSound() {
        isSoundEnabled = !isSoundEnabled;
        if (!isSoundEnabled) {
            stopBackgroundMusic(); // Stop background music if sound is turned off
        } else {
            playBackgroundMusic("/opensound.wav"); // Play background music if sound is turned on
        }
        System.out.println("Trạng thái âm thanh: " + (isSoundEnabled ? "ON" : "OFF"));
    }

    // Get sound status
    public static boolean isSoundEnabled() {
        return isSoundEnabled;
    }
}