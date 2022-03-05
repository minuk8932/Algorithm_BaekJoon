package common;

public class Node<T, E> {
    private final T node;

    private final E cost;
    private final T another;

    public static class Builder<T, E> {
        //must
        private final T node;
        // opt
        private T another;
        private E cost;

        public Builder(T node) {
            this.node = node;
        }

        public Builder cost(E value) {
            cost = value;
            return this;
        }

        public Builder another(T value) {
            another = value;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    }

    private Node(Builder<T, E> builder) {
        node = builder.node;
        cost = builder.cost;
        another = builder.another;
    }

    public T getAnother() {
        return another;
    }

    public T getNode() {
        return node;
    }

    public E getCost() { return cost; }

}
