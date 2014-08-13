# VoiceText4J
Java Client Library for [VoiceText Web API](https://cloud.voicetext.jp/webapi)

## Usage

    import org.junit.Test;
    import am.ik.voicetext4j.*;
    
    public class SpeakerTest {
    
        @Test
        public void testSay() throws Exception {
            System.setProperty("voicetext.apikey", "API_KEY");
            
            Speaker.HARUKA.ready("おはようございます")
                    .pitch(105)
                    .speed(105)
                    .emotion(Emotion.HAPPINESS, Emotion.Level.HIGH)
                    .say();
                    
            // you can use say("API_KEY") instead of System.setProperty
        }
    }


## License

Licensed under the Apache License, Version 2.0.