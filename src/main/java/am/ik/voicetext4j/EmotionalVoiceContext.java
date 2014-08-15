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

    public EmotionalVoiceContext(String speaker) {
        super(speaker);
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

    public EmotionContext very() {
        return new EmotionContext(this, Emotion.Level.HIGH);
    }

    public EmotionalVoiceContext angry() {
        if (this.emotion != null) {
            throw new IllegalArgumentException("'emotion' is already set.");
        }
        return new EmotionContext(this).angry();
    }

    public EmotionalVoiceContext happy() {
        if (this.emotion != null) {
            throw new IllegalArgumentException("'emotion' is already set.");
        }
        return new EmotionContext(this).happy();
    }

    public EmotionalVoiceContext sad() {
        if (this.emotion != null) {
            throw new IllegalArgumentException("'emotion' is already set.");
        }
        return new EmotionContext(this).sad();
    }

    public class EmotionContext {
        final EmotionalVoiceContext voiceContext;

        public EmotionContext(EmotionalVoiceContext voiceContext, Emotion.Level emotionLevel) {
            this.voiceContext = voiceContext;
            voiceContext.emotionLevel = emotionLevel;
        }

        public EmotionContext(EmotionalVoiceContext voiceContext) {
            this(voiceContext, Emotion.Level.NORMAL);
        }

        public EmotionalVoiceContext angry() {
            voiceContext.emotion = Emotion.ANGER;
            return voiceContext;
        }

        public EmotionalVoiceContext happy() {
            voiceContext.emotion = Emotion.HAPPINESS;
            return voiceContext;
        }

        public EmotionalVoiceContext sad() {
            voiceContext.emotion = Emotion.SADNESS;
            return voiceContext;
        }
    }
}
