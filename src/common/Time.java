package common;

public class Time<T, E> {

    private final T start;
    private final T end;
    private final E cost;

    public static class Builder<T, E> {
        //must
        private final T start;
        private final T end;

        //opt
        private E cost;

        public Builder(T start, T end) {
            this.start = start;
            this.end = end;
        }

        public Builder cost(E value) {
            cost = value;
            return this;
        }

        public Time build() {
            return new Time(this);
        }
    }

    private Time(Builder<T, E> builder) {
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
