package am.ik.voicetext4j;

public enum Speaker implements Speakable<EmotionalVoiceContext> {
    HARUKA, HIKARI, TAKERU;

    @Override
    @SuppressWarnings("unchecked")
    public EmotionalVoiceContext ready(String text) {
        return new EmotionalVoiceContext(text, this.name().toLowerCase());
    }
}
