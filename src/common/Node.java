package common;

public class Node {
    private int node;
    private int another;
    private int cost;

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public Node(int node, int another, int cost) {
        this.node = node;
        this.another = another;
        this.cost = cost;
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
