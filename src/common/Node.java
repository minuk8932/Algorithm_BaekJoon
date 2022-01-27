package common;

public class Node {
    private int node;
    private int cost;

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public int getNode() {
        return node;
    }

    public int getCost() {
        return cost;
    }
}
