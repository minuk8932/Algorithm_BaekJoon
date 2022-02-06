package common;

public class Time {

    private final int start;
    private final int end;

    public static class Builder {
        private final int start;
        private final int end;

        public Builder(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Time build() {
            return new Time(this);
        }
    }

    private Time(Builder builder) {
        start = builder.start;
        end = builder.end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
