package am.ik.voicetext4j.http;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class VoiceTextResponse {
    final AudioInputStream audioInputStream;

    public VoiceTextResponse(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }

    public AudioInputStream audioInputStream() {
        return audioInputStream;
    }

    public Clip clip() {
        AudioFormat format = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        try {
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
            return clip;
        } catch (LineUnavailableException | IOException e) {
            throw new VoiceTextIllegalStateException(e);
        }
    }

    public void play() throws InterruptedException {
        Clip clip = clip();
        clip.start();
        final CountDownLatch latch = new CountDownLatch(1);
        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    latch.countDown();
                }
            }
        });
        latch.await();
        clip.close();
    }
}
