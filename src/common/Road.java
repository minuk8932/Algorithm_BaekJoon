package common;

public class Road<T> {

    private final T start;
    private final T end;
    private final T cost;

    public static class Builder<T> {

        //must
        private final T start;
        private final T end;

        // opt
        private T cost;

        public Builder(T start, T end) {
            this.start = start;
            this.end = end;
        }

        public Builder cost(T value) {
            cost = value;
            return this;
        }

        public Road build() {
            return new Road(this);
        }
    }

    private Road(Builder<T> builder){
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

    public T getCost() {
        return cost;
    }
}
