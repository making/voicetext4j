package am.ik.voicetext4j.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class VoiceTextFields {
    final ByteArrayOutputStream content = new ByteArrayOutputStream();

    public VoiceTextFields put(String name, String value) {
        if (content.size() > 0) {
            content.write('&');
        }
        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");

            content.write(name.getBytes("UTF-8"));
            content.write('=');
            content.write(value.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public byte[] getBody() {
        return content.toByteArray();
    }
}
