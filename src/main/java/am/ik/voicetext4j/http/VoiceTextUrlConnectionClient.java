package am.ik.voicetext4j.http;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VoiceTextUrlConnectionClient {
    static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s
    static final int READ_TIMEOUT_MILLIS = 20 * 1000; // 20s
    static final int CHUNK_SIZE = 4096;
    static final String API_ENDPOINT = System.getProperty("voicetext.endpoint", "https://api.voicetext.jp/v1/tts");

    HttpURLConnection openConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(API_ENDPOINT).openConnection();
        connection.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
        connection.setReadTimeout(READ_TIMEOUT_MILLIS);

        return connection;
    }

    void prepareRequest(HttpURLConnection connection, VoiceTextFields fieldsBuilder) throws IOException {
        connection.setRequestMethod("POST");
        connection.setChunkedStreamingMode(CHUNK_SIZE);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setDoOutput(true);
        connection.getOutputStream().write(fieldsBuilder.getBody());
    }

    AudioInputStream readResponse(HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        String reason = connection.getResponseMessage();
        if (reason == null) reason = ""; // HttpURLConnection treats empty reason as null.
        InputStream stream;

        if (status >= 400) {
            throw new VoiceTextApiCallException(status, reason);
        } else {
            stream = connection.getInputStream();
        }

        try {
            return AudioSystem.getAudioInputStream(new BufferedInputStream(stream));
        } catch (UnsupportedAudioFileException e) {
            throw new VoiceTextIllegalStateException(e);
        }
    }

    public VoiceTextResponse execute(VoiceTextFields fields, String apiKey) {
        try {
            HttpURLConnection connection = openConnection();
            connection.setRequestProperty("Authorization", "Basic " + DatatypeConverter.printBase64Binary((apiKey + ":").getBytes()));
            prepareRequest(connection, fields);
            return new VoiceTextResponse(readResponse(connection));
        } catch (IOException e) {
            throw new VoiceTextIllegalStateException(e);
        }
    }
}
