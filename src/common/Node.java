package common;

public class Node<T> {
    private final int node;

    private T cost;
    private final int another;

    public static class Builder<T> {
        //must
        private final int node;
        // opt
        private int another;
        private T cost;

        public Builder(int node) {
            this.node = node;
        }

        public Builder cost(T value) {
            cost = value;
            return this;
        }

        public Builder another(int value) {
            another = value;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    }

    private Node(Builder<T> builder) {
        node = builder.node;
        cost = builder.cost;
        another = builder.another;
    }

    public int getAnother() {
        return another;
    }

    public int getNode() {
        return node;
    }

    public T getCost() { return cost; }

}
