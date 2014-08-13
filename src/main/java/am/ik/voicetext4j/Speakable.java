package am.ik.voicetext4j;

public interface Speakable<T> {
    <T extends VoiceContext> T ready(String text);
}
