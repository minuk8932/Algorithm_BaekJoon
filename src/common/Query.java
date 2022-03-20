package common;

public class Query<T, E, R> {
    private final T cmd;
    private final E i;
    private final E j;

    private final R k;

    public static class Builder<T, E, R> {
        //must
        private final T cmd;
        private final E i;
        private final E j;

        // opt
        private R k;

        public Builder(T cmd, E i, E j) {
            this.cmd = cmd;
            this.i = i;
            this.j = j;
        }

        public Builder k(R value) {
            k = value;
            return this;
        }

        public Query build() {
            return new Query(this);
        }
    }

    private Query(Builder<T, E, R> builder) {
        cmd = builder.cmd;
        i = builder.i;
        j = builder.j;
        k = builder.k;
    }

    public T getCmd() {
        return cmd;
    }

    public E getI() {
        return i;
    }

    public E getJ() {
        return j;
    }

    public R getK() {
        return k;
    }
}
