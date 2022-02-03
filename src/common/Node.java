package common;

public class Node {
    private final int node;
    private final int cost;

    private final int another;

    public static class Builder {
        //must
        private final int node;
        private final int cost;

        // opt
        private int another;

        public Builder(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public Builder another(int value) {
            another = value;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    }

    private Node(Node.Builder builder) {
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

    public int getCost() {
        return cost;
    }
}
