package common;

public class Pair {
    private final int first;
    private final int second;

    public static class Builder {
        // must
        private final int first;
        private final int second;

        // opt

        public Builder(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public Pair build() {
            return new Pair(this);
        }
    }

    private Pair(Builder builder) {
        first = builder.first;
        second = builder.second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
