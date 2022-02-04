import common.Node;
import common.RealCoordinate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj22813 {

    private static Queue<Node> pq = new PriorityQueue<>();
    private static int[] parent;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            RealCoordinate[] coordinates = new RealCoordinate[N];
            parent = new int[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                double z = Double.parseDouble(st.nextToken());
                double r = Double.parseDouble(st.nextToken());

                coordinates[i] = new RealCoordinate.Builder(x, y)
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

    private static String mst() {

        return "";
    }

    private static void linkage(RealCoordinate[] coordinates) {
        for(int i = 0; i < coordinates.length; i++) {
            for(int j = i + 1; j < coordinates.length; j++) {
                double cost = euclideanAdjacent(coordinates[i], coordinates[j]);

                pq.offer(new Node.Builder(i)
                        .doubleCost(cost)
                        .another(j)
                        .build());
            }
        }
    }

    private static double euclideanAdjacent(RealCoordinate c1, RealCoordinate c2) {
        double xdiff = c2.getX() - c1.getX();
        double ydiff = c2.getY() - c1.getY();

        double total = (xdiff * xdiff + ydiff * ydiff) - (c1.getR() + c2.getR());
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