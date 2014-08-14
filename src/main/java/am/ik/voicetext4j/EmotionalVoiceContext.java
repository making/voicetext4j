package am.ik.voicetext4j;


import am.ik.voicetext4j.http.VoiceTextFields;

public class EmotionalVoiceContext extends VoiceContext<EmotionalVoiceContext> {
    Emotion emotion;
    Emotion.Level emotionLevel = Emotion.Level.NORMAL;

    public EmotionalVoiceContext(String text, String speaker) {
        super(text, speaker);
    }

    public EmotionalVoiceContext emotion(Emotion emotion, Emotion.Level level) {
        this.emotion = emotion;
        this.emotionLevel = level;
        return this;
    }

    public EmotionalVoiceContext emotion(Emotion emotion) {
        this.emotion = emotion;
        return this;
    }

    @Override
    protected VoiceTextFields build() {
        VoiceTextFields fields = super.build();
        if (emotion != null) {
            fields.put("emotion", emotion.name().toLowerCase())
                    .put("emotion_level", String.valueOf(emotionLevel.value()));
        }
        return fields;

    }
}
