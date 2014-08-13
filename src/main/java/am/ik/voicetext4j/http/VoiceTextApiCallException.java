package am.ik.voicetext4j.http;

public class VoiceTextApiCallException extends RuntimeException {
    public VoiceTextApiCallException(int status, String reason) {
        super(status + " " + reason);
    }
}
