/*
 * Copyright (C) 2014 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package am.ik.voicetext4j.http;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
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

    public CompletableFuture<Void> play() {
        Clip clip = clip();
        CompletableFuture<Void> future = new CompletableFuture<Void>()
                .whenComplete((x, e) -> clip.close());
        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                future.complete(null);
            }
        });
        clip.start();
        return future;
    }
}
