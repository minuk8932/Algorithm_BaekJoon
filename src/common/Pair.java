package common;

public class Pair<T> {
    private final T first;
    private final T second;

    public static class Builder<T> {
        // must
        private final T first;
        private final T second;

        // opt

        public Builder(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public Pair build() {
            return new Pair(this);
        }
    }

    private Pair(Builder<T> builder) {
        first = builder.first;
        second = builder.second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
}
