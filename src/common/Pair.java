package common;

public class Pair<T> {
    private final T first;
    private final T second;

    private final T third;

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

        public Builder another(T value) {
            third = value;
            return this;
        }

        public Pair build() {
            return new Pair(this);
        }
    }

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
}
