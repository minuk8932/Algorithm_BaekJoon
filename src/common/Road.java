package common;

public class Road<T, E> {

    private final T start;
    private final T end;
    private final E cost;

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

        public Builder cost(E value) {
            cost = value;
            return this;
        }

        public Road build() {
            return new Road(this);
        }
    }

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
}
