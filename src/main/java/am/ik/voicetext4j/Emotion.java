package am.ik.voicetext4j;

public enum Emotion {
    HAPPINESS,
    ANGER,
    SADNESS;

    public static enum Level {
        NORMAL(1), HIGH(2);

        private final int value;

        Level(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}
