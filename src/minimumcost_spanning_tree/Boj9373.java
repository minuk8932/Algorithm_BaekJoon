package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9373번: 복도 뚫기
 *
 * @see https://www.acmicpc.net/problem/9373/
 *
 */
public class Boj9373 {
    private static int[] parent;
    private static PriorityQueue<Node> pq;

    private static final String NEW_LINE = "\n";

    private static class Circle{
        int x;
        int y;
        int r;

        public Circle(int x, int y, int r){
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    private static class Node implements Comparable<Node>{
        int node1;
        int node2;
        double dist;

        public Node(int node1, int node2, double dist){
            this.node1 = node1;
            this.node2 = node2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node n) {
            return this.dist < n.dist ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int w = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            Circle[] c = new Circle[n];
            pq = new PriorityQueue<>();
            init(n);

            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                c[i] = new Circle(x, y, r);
                double d1 = c[i].x - c[i].r;
                double d2 = w - (c[i].x + c[i].r);

                pq.offer(new Node(i, n, d1 < 0 ? 0: d1));                  // link with left mid
                pq.offer(new Node(i, n + 1, d2 < 0 ? 0: d2));       // link with mid right

                for(int j = 0; j < i; j++){
                    double d = distance(c[i], c[j]);
                    pq.offer(new Node(i, j, d));                           // origin link
                }
            }

            pq.offer(new Node(n, n + 1, w));                        // max size
            sb.append(String.format("%.6f", kruskal(n))).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(int n){
        parent = new int[n + 2];

        for(int i = 0; i < parent.length; i++){
            parent[i] = -1;
        }
    }

    private static int find(int x){
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]){
            parent[x] += parent[y];
            parent[y] = x;
        }
        else{
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static double distance(Circle c1, Circle c2){
        double xdiff = c1.x - c2.x;
        double ydiff = c1.y - c2.y;

        double d = Math.sqrt(xdiff * xdiff + ydiff * ydiff) - c1.r - c2.r;
        return d < 0 ? 0: d;
    }

    private static double kruskal(int n) {
        double res = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(merged(current.node1, current.node2) || find(n) != find(n + 1)) continue;        // until aisle is covered

            if(current.dist == 0) res = 0;              // then means linked 0 to w, so last distance is the largest can pass
            else res = current.dist / 2;
            break;
        }

        return res;
    }
}
