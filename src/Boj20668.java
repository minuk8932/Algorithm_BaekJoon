import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj20668 {

    private static ArrayList<Node>[] path;
    private static long[][] dist;
    private static final long INF = 1_000_000_000_000L;

    private static class Node implements Comparable<Node> {
        int node;
        int leng;
        long head;
        long tail;
        int velocity;

        public Node(int node, int leng, long head, long tail, int velocity) {
            this.node = node;
            this.leng = leng;
            this.head = head;
            this.tail = tail;
            this.velocity = velocity;
        }

        @Override
        public int compareTo(Node n) {
            return this.head * n.tail < n.head * this.tail ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        path = new ArrayList[N];
        dist = new long[N][2];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            dist[i][0] = INF;
            dist[i][1] = 1;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int L = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            path[A].add(new Node(B, L, L, 1, K));
            path[B].add(new Node(A, L, L, 1, K));
        }

        System.out.println(dijkstra(N));
    }

    private static String dijkstra(int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0, 1, 1));
        dist[0][0] = 0;
        dist[0][1] = 1;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(dist[current.node][0] * current.velocity < dist[current.node][1] * current.leng) continue;

            for(Node next: path[current.node]) {
                int[] velo = {current.velocity + 1, current.velocity, current.velocity - 1};

                for(int v: velo) {
                    if(v > next.velocity || v < 1) continue;

                    if (shorter(next, current, next.head, v)) continue;
                    long gcd = gcd(v, dist[current.node][1]);
                    long lcm = dist[current.node][1] * v / gcd;
                    dist[next.node][1] = lcm;

                    long right = dist[next.node][1] / dist[current.node][1];
                    long left = dist[next.node][1] / (long) v;
                    dist[next.node][0] = next.head * left + dist[current.node][0] * right;

                    long nGcd = gcd(dist[next.node][0], dist[next.node][1]);
                    dist[next.node][0] /= nGcd;
                    dist[next.node][1] /= nGcd;

                    pq.offer(new Node(next.node, next.leng, dist[next.node][0], dist[next.node][1], v));
                }
            }
        }

        DecimalFormat decimal = new DecimalFormat(".000000000");
        return decimal.format(new BigDecimal(dist[n - 1][0] + "").divide(new BigDecimal(dist[n - 1][1] + "")));
    }

    private static boolean shorter(Node next, Node current, long head, long tail) {
        long gcd1 = gcd(tail, dist[current.node][1]);
        long lcm1 = tail * dist[current.node][1] / gcd1;
        long left1 = head * (lcm1 / tail);
        long right1 = dist[current.node][0] * (lcm1 / dist[current.node][1]);

        long gcd2 = gcd(lcm1, dist[next.node][1]);
        long lcm2 = lcm1 * dist[next.node][1] / gcd2;
        long left = dist[next.node][0] * (lcm2 / dist[next.node][1]);
        long mid = left1 * (lcm2 / lcm1);
        long right = right1 * (lcm2 / lcm1);

        return left <= mid + right;
    }

    private static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
