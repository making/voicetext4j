package am.ik.voicetext4j;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NormalVoiceContextTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testBuild() throws Exception {
        byte[] param = new NormalVoiceContext("hello", "show")
                .build().getBody();
        assertThat(param, is("text=hello&speaker=show&pitch=100&speed=100&volume=100".getBytes()));
    }


    @Test
    public void testJapanese() throws Exception {
        byte[] param = new NormalVoiceContext("こんにちは", "show")
                .build().getBody();
        assertThat(param, is("text=%E3%81%93%E3%82%93%E3%81%AB%E3%81%A1%E3%81%AF&speaker=show&pitch=100&speed=100&volume=100".getBytes()));
    }

    @Test
    public void testBuild_setAll() throws Exception {
        byte[] param = new NormalVoiceContext("hello", "show")
                .pitch(120)
                .speed(200)
                .volume(150)
                .build().getBody();
        assertThat(param, is("text=hello&speaker=show&pitch=120&speed=200&volume=150".getBytes()));
    }

    @Test
    public void testBuild_setAllMax() throws Exception {
        byte[] param = new NormalVoiceContext("hello", "show")
                .pitch(200)
                .speed(400)
                .volume(200)
                .build().getBody();
        assertThat(param, is("text=hello&speaker=show&pitch=200&speed=400&volume=200".getBytes()));
    }

    @Test
    public void testBuild_setAllMin() throws Exception {
        byte[] param = new NormalVoiceContext("hello", "show")
                .pitch(50)
                .speed(50)
                .volume(50)
                .build().getBody();
        assertThat(param, is("text=hello&speaker=show&pitch=50&speed=50&volume=50".getBytes()));
    }

    @Test
    public void testBuild_setLargePitch() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        new NormalVoiceContext("hello", "show")
                .pitch(201)
                .speed(200)
                .volume(150)
                .build().getBody();
    }


    @Test
    public void testBuild_setSmallPitch() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        new NormalVoiceContext("hello", "show")
                .pitch(49)
                .speed(200)
                .volume(150)
                .build().getBody();
    }

    @Test
    public void testBuild_setLargeSpeed() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        new NormalVoiceContext("hello", "show")
                .pitch(200)
                .speed(401)
                .volume(150)
                .build().getBody();
    }


    @Test
    public void testBuild_setSmallSpeed() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        new NormalVoiceContext("hello", "show")
                .pitch(200)
                .speed(49)
                .volume(150)
                .build().getBody();
    }


    @Test
    public void testBuild_setLargeVolume() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        new NormalVoiceContext("hello", "show")
                .pitch(200)
                .speed(400)
                .volume(201)
                .build().getBody();
    }


    @Test
    public void testBuild_setSmallVolume() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        new NormalVoiceContext("hello", "show")
                .pitch(200)
                .speed(400)
                .volume(49)
                .build().getBody();
    }
}