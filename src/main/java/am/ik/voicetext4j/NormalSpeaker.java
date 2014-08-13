package am.ik.voicetext4j;


public enum NormalSpeaker implements Speakable<NormalVoiceContext> {
    SHOW;

    @Override
    @SuppressWarnings("unchecked")
    public NormalVoiceContext ready(String text) {
        return new NormalVoiceContext(text, this.name().toLowerCase());
    }
}
