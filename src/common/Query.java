package common;

public class Query<T, E> {
    private final T i;
    private final T j;

    private final E k;

    public static class Builder<T, E> {
        //must
        private final T i;
        private final T j;

        // opt
        private E k;

        public Builder(T i, T j) {
            this.i = i;
            this.j = j;
        }

        public Builder k(E value) {
            k = value;
            return this;
        }

        public Query build() {
            return new Query(this);
        }
    }

    private Query(Builder<T, E> builder) {
        i = builder.i;
        j = builder.j;
        k = builder.k;
    }

    public T getI() {
        return i;
    }

    public T getJ() {
        return j;
    }

    public E getK() {
        return k;
    }
}
