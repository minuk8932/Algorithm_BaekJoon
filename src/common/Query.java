package common;

public class Query<T, E> {
    private final T from;
    private final T to;
    private final E value;

    public static class Builder<T, E> {
        //must
        private final T from;

        //opt
        private T to;
        private E value;

        public Builder(T from) {
            this.from = from;
        }

        public Builder to(T value) {
            to = value;
            return this;
        }

        public Builder value(E value) {
            value = value;
            return this;
        }

        public Query build() {
            return new Query(this);
        }
    }

    private Query(Builder<T, E> builder) {
        from = builder.from;
        to = builder.to;
        value = builder.value;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }

    public E getValue() {
        return value;
    }
}
