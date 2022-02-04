package common;

public class Node {
    private final int node;

    private final int cost;
    private final double doubleCost;
    private final int another;

    public static class Builder {
        //must
        private final int node;
        // opt
        private int another;
        private int cost;
        private double doubleCost;

        public Builder(int node) {
            this.node = node;
        }

        public Builder cost(int value) {
            cost = value;
            return this;
        }

        public Builder doubleCost(double value) {
            doubleCost = value;
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

    private Node(Node.Builder builder) {
        node = builder.node;
        cost = builder.cost;
        doubleCost =  builder.doubleCost;
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

    public double getDoubleCost() {
        return doubleCost;
    }
}
