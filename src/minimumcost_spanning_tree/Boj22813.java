package minimumcost_spanning_tree;

import common.Coordinate;
import common.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22813번: Building a Space Station
 *
 * @see https://www.acmicpc.net/problem/22813
 *
 */
public class Boj22813 {

    private static Queue<Node<Integer, Double>> pq;
    private static int[] parent;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            pq = new PriorityQueue<>(Comparator.comparingDouble(Node<Integer, Double>::getCost));
            Coordinate<Double, Double>[] coordinates = new Coordinate[N];
            parent = new int[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                double z = Double.parseDouble(st.nextToken());
                double r = Double.parseDouble(st.nextToken());

                coordinates[i] = new Coordinate.Builder(x, y)
                        .z(z)
                        .r(r)
                        .build();

                parent[i] = -1;
            }

            linkage(coordinates);
            sb.append(mst()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Minimum cost Spanning Tree
     *
     * @return
     */
    private static String mst() {
        double total = 0;

        while(!pq.isEmpty()) {
            Node<Integer, Double> current = pq.poll();

            if(merged(current.getNode(), current.getAnother())) continue;
            total += current.getCost();
        }

        return String.format("%.3f", total);
    }

    private static void linkage(Coordinate<Double, Double>[] coordinates) {
        for(int i = 0; i < coordinates.length; i++) {
            for(int j = i + 1; j < coordinates.length; j++) {
                double cost = euclideanAdjacent(coordinates[i], coordinates[j]);

                pq.offer(new Node.Builder<Integer, Double>(i)
                        .cost(cost)
                        .another(j)
                        .build());
            }
        }
    }

    /**
     *
     * Euclidean Adjacent: make cost, node to node
     *
     * @param c1
     * @param c2
     * @return
     */
    private static double euclideanAdjacent(Coordinate<Double, Double> c1, Coordinate<Double, Double> c2) {
        double xdiff = c2.getX() - c1.getX();
        double ydiff = c2.getY() - c1.getY();
        double zdiff = c2.getZ() - c1.getZ();

        double total = Math.sqrt(xdiff * xdiff + ydiff * ydiff + zdiff * zdiff)
                - (c1.getR() + c2.getR());
        return total < 0 ? 0: total;
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }
}