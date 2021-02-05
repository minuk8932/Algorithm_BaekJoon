import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj20668 {

    private static ArrayList<Node>[] path;
    private static long[] dist;
    private static final long INF = 1_000_000_000_000_000_000L;
    private static final long TEN = 1_000_000_000L;

    private static class Node implements Comparable<Node> {
        int node;
        long velo;
        long leng;

        public Node(int node, long leng, long velo) {
            this.node = node;
            this.velo = velo;
            this.leng = leng;
        }

        @Override
        public int compareTo(Node n) {
            return this.leng / this.velo < n.leng / this.velo ? -1 : 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        path = new ArrayList[N];
        dist = new long[N];
        for (int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            dist[i] = INF;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            long L = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());

            path[A].add(new Node(B, L * TEN, K));
            path[B].add(new Node(A, L * TEN, K));
        }

        System.out.println(String.format("%.9f", dijkstra(N)));
    }

    private static double dijkstra(int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 1));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (dist[current.node] < current.leng) continue;

            for (Node next : path[current.node]) {
                long[] velo = {current.velo + 1, current.velo, current.velo - 1};

                for (long tail : velo) {
                    if (tail > next.velo || tail < 1) continue;
                    if (dist[next.node] <= dist[current.node] + next.leng / tail) continue;
                    dist[next.node] = dist[current.node] + next.leng / tail;

                    pq.offer(new Node(next.node, dist[next.node], tail));
                }
            }
        }

        return dist[n - 1] / (double) TEN;
    }
}
