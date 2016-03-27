# VoiceText4J
Java Client Library for [VoiceText Web API](https://cloud.voicetext.jp/webapi)

[![Build Status](https://travis-ci.org/making/voicetext4j.svg?branch=master)](https://travis-ci.org/making/voicetext4j)

You can use VoiceText4J with the following setting in pom.xml:

    <dependency>
      <groupId>am.ik.voicetext</groupId>
      <artifactId>voicetext4j</artifactId>
      <version>0.12.0</version>
    </dependency>

## Usage

    import org.junit.Test;
    import am.ik.voicetext4j.*;
    
    public class SpeakerTest {
    
        @Test
        public void testSay() throws Exception {
            System.setProperty("voicetext.apikey", "API_KEY");
            
            EmotionalSpeaker.HARUKA.ready()
                    .pitch(105)
                    .speed(105)
                    .very().happy()
                    .speak("おはようございます");
                    
            // you can use speak(text, "API_KEY") instead of using System.setProperty("voicetext.apikey", "API_KEY")
        }
    }


## Quick Start using Groovy and Grape

`test.groovy`

    @Grab("am.ik.voicetext:voicetext4j:0.12.0")
    import am.ik.voicetext4j.*;
    
    System.setProperty("voicetext.apikey", "API_KEY");
    
    Speaker.SHOW.ready().speak("こんにちは");
    EmotionalSpeaker.HARUKA.ready().speak("こんにちは");
    EmotionalSpeaker.HIKARI.ready().speak("こんにちは");
    EmotionalSpeaker.TAKERU.ready().speak("こんにちは");
    EmotionalSpeaker.SANTA.ready().speak("メリークリスマス"); // new speaker from 0.11.0
    EmotionalSpeaker.BEAR.ready().speak("こんにちは"); // new speaker from 0.12.0

run

    $ groovy test.groovy
    
### Change emotion

`EmotionalSpeaker`s can be changed their emotion like the following:

    EmotionalSpeaker.HARUKA.ready().speak("こんにちは");
    EmotionalSpeaker.HARUKA.ready().angry().speak("こんにちは");
    EmotionalSpeaker.HARUKA.ready().very().angry().speak("こんにちは");
    EmotionalSpeaker.HARUKA.ready().happy().speak("こんにちは");
    EmotionalSpeaker.HARUKA.ready().very().happy().speak("こんにちは");
    EmotionalSpeaker.HARUKA.ready().sad().speak("こんにちは");
    EmotionalSpeaker.HARUKA.ready().very().sad().speak("こんにちは");

## License

Licensed under the Apache License, Version 2.0.
