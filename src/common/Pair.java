package common;

public class Pair<T> {
    private final T first;
    private final T second;

    private final T third;

    private Pair(Builder<T> builder) {
        first = builder.first;
        second = builder.second;
        third = builder.third;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

    public static class Builder<T> {
        // must
        private final T first;
        private final T second;

        // opt
        private T third;

        public Builder(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public Builder<T> third(T value) {
            third = value;
            return this;
        }

        public Pair<T> build() {
            return new Pair<>(this);
        }
    }
}
