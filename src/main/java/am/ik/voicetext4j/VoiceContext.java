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

package am.ik.voicetext4j;


import am.ik.voicetext4j.http.VoiceTextFields;
import am.ik.voicetext4j.http.VoiceTextUrlConnectionClient;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public abstract class VoiceContext<T extends VoiceContext> implements Serializable {
    final String text;
    final String speaker;
    int pitch = 100;
    int speed = 100;
    int volume = 100;

    public VoiceContext(String text, String speaker) {
        this.text = text;
        this.speaker = speaker;
    }

    public T pitch(int pitch) {
        if (pitch < 50 || pitch > 200) {
            throw new IllegalArgumentException("'pitch' must be between 50 and 200.");
        }
        this.pitch = pitch;
        return (T) this;
    }

    public T speed(int speed) {
        if (speed < 50 || speed > 400) {
            throw new IllegalArgumentException("'speed' must be between 50 and 400.");
        }
        this.speed = speed;
        return (T) this;
    }

    public T volume(int volume) {
        if (volume < 50 || volume > 200) {
            throw new IllegalArgumentException("'volume' must be between 50 and 200.");
        }
        this.volume = volume;
        return (T) this;
    }

    protected VoiceTextFields build() {
        return new VoiceTextFields()
                .put("text", text)
                .put("speaker", speaker)
                .put("pitch", String.valueOf(pitch))
                .put("speed", String.valueOf(speed))
                .put("volume", String.valueOf(volume));
    }

    public void speak(String apiKey) throws InterruptedException {
        new VoiceTextUrlConnectionClient()
                .execute(build(), apiKey)
                .play();
    }

    public void speak() throws InterruptedException {
        speak(System.getProperty("voicetext.apikey"));
    }
}
