package amethyst.logger.media;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class MusicPlayer {

    private static Clip clip;

    public static void play(InputStream stream) {

        stop();

        new Thread(() -> {

            try {

                AudioInputStream audio =
                        AudioSystem.getAudioInputStream(
                                new BufferedInputStream(stream));

                AudioInputStream decoded = AudioSystem.getAudioInputStream(
                        new AudioFormat(
                                AudioFormat.Encoding.PCM_SIGNED,
                                audio.getFormat().getSampleRate(),
                                16,
                                audio.getFormat().getChannels(),
                                audio.getFormat().getChannels() * 2,
                                audio.getFormat().getSampleRate(),
                                false
                        ),
                        audio
                );

                clip = AudioSystem.getClip();
                clip.open(decoded);
                System.out.println("Frames: " + clip.getFrameLength());
                System.out.println("Microseconds: " + clip.getMicrosecondLength());
                clip.start();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }, "ASS Music Player").start();

    }

    public static void stop() {

        if (clip != null) {

            clip.stop();
            clip.close();
            clip = null;

        }

    }

}