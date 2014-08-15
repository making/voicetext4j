# VoiceText4J
Java Client Library for [VoiceText Web API](https://cloud.voicetext.jp/webapi)

You can use VoiceText4J with the following setting in pom.xml:

    <dependency>
      <groupId>am.ik.voicetext</groupId>
      <artifactId>voicetext4j</artifactId>
      <version>0.9.0</version>
    </dependency>

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
                    .speak();
                    
            // you can use speak("API_KEY") instead of System.setProperty("voicetext.apikey", "API_KEY")
        }
    }


## Quick Start using Groovy and Grape

`test.groovy`

    @Grab("am.ik.voicetext:voicetext4j:0.9.0")
    import am.ik.voicetext4j.*;
    
    System.setProperty("voicetext.apikey", "API_KEY");
    
    NormalSpeaker.SHOW.ready("こんにちは").speak();
    Speaker.HARUKA.ready("こんにちは").speak();
    Speaker.HIKARI.ready("こんにちは").speak();
    Speaker.TAKERU.ready("こんにちは").speak();

run

    $ groovy test.groovy


## License

Licensed under the Apache License, Version 2.0.
