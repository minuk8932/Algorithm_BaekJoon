package common;

public class Query<T, E> {
    private final T from;
    private final T to;
    private final E value;
    private final T index;

    public static class Builder<T, E> {
        //must
        private final T from;

        //opt
        private T to;
        private T index;
        private E cost;

        public Builder(T from) {
            this.from = from;
        }

        public Builder to(T value) {
            to = value;
            return this;
        }

        public Builder index(T value) {
            index = value;
            return this;
        }

        public Builder value(E value) {
            cost = value;
            return this;
        }

        public Query build() {
            return new Query(this);
        }
    }

    private Query(Builder<T, E> builder) {
        from = builder.from;
        to = builder.to;
        value = builder.cost;
        index = builder.index;
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

    public T getIndex() {
        return index;
    }
}
