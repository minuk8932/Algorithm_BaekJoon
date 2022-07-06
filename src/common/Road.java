package common;

public class Road<T, E> {

    private final T start;
    private final T end;
    private final E cost;

    private Road(Builder<T, E> builder){
        start = builder.start;
        end = builder.end;
        cost = builder.cost;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    public E getCost() {
        return cost;
    }

    public static class Builder<T, E> {

        //must
        private final T start;
        private final T end;

        // opt
        private E cost;

        public Builder(T start, T end) {
            this.start = start;
            this.end = end;
        }

        public Builder<T, E> cost(E value) {
            cost = value;
            return this;
        }

        public Road<T, E> build() {
            return new Road<>(this);
        }
    }
}
