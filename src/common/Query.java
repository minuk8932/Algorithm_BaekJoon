package common;

public class Query<T, E> {
    private final T from;
    private final T to;
    private final T index;
    private final T sequence;
    private final E cost;

    public static class Builder<T, E> {
        //must
        private final T from;

        //opt
        private T to;
        private T index;
        private T sequence;
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

        public Builder sequence(T value) {
            sequence = value;
            return this;
        }

        public Builder cost(E value) {
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
        index = builder.index;
        sequence = builder.sequence;
        cost = builder.cost;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }

    public E getCost() {
        return cost;
    }

    public T getSequence() {
        return sequence;
    }

    public T getIndex() {
        return index;
    }
}
