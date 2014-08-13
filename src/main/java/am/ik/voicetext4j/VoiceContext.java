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
        this.pitch = pitch;
        return (T) this;
    }

    public T speed(int speed) {
        this.speed = speed;
        return (T) this;
    }

    public T volume(int volume) {
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

    public void say(String apiKey) throws InterruptedException {
        new VoiceTextUrlConnectionClient()
                .execute(build(), apiKey)
                .say();
    }

    public void say() throws InterruptedException {
        say(System.getProperty("voicetext.apikey"));
    }
}
