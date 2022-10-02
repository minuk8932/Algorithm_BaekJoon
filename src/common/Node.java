package common;

public class Node<T, E> {
    private final T node;

    private final E cost;
    private final E weight;
    private final T another;

    private Node(Builder<T, E> builder) {
        node = builder.node;
        cost = builder.cost;
        weight = builder.weight;
        another = builder.another;
    }

    public T getAnother() {
        return another;
    }

    public T getNode() {
        return node;
    }

    public E getCost() { return cost; }

    public E getWeight() { return weight; }

    public static class Builder<T, E> {
        //must
        private final T node;
        // opt
        private T another;
        private E cost;
        private E weight;

        public Builder(T node) {
            this.node = node;
        }

        public Builder<T, E> cost(E value) {
            cost = value;
            return this;
        }

        public Builder<T, E> weight(E value) {
            weight = value;
            return this;
        }

        public Builder<T, E> another(T value) {
            another = value;
            return this;
        }

        public Node<T, E> build() {
            return new Node<>(this);
        }
    }

}
