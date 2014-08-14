/*
 * Copyright (C) 2014 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package am.ik.voicetext4j;


import am.ik.voicetext4j.http.VoiceTextFields;

public class EmotionalVoiceContext extends VoiceContext<EmotionalVoiceContext> {
    Emotion emotion;
    Emotion.Level emotionLevel = Emotion.Level.NORMAL;

    public EmotionalVoiceContext(String text, String speaker) {
        super(text, speaker);
    }

    public EmotionalVoiceContext emotion(Emotion emotion, Emotion.Level level) {
        this.emotion = emotion;
        this.emotionLevel = level;
        return this;
    }

    public EmotionalVoiceContext emotion(Emotion emotion) {
        this.emotion = emotion;
        return this;
    }

    @Override
    protected VoiceTextFields build() {
        VoiceTextFields fields = super.build();
        if (emotion != null) {
            fields.put("emotion", emotion.name().toLowerCase())
                    .put("emotion_level", String.valueOf(emotionLevel.value()));
        }
        return fields;

    }
}
