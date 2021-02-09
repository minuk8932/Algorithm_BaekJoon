package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20313번: 출퇴근
 *
 * @see https://www.acmicpc.net/problme/20313
 *
 */
public class Boj20313 {

    private static long[][] cost;
    private static long[][] dist;
    private static ArrayList<Node>[] path;

    private static final long INF = Long.MAX_VALUE;

    private static class Node implements Comparable<Node>{
        int node;
        int index;
        int count;
        long cost;

        public Node(int node, int index, int count, long cost) {
            this.node = node;
            this.index = index;
            this.count = count;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken()) - 1;
        int B = Integer.parseInt(st.nextToken()) - 1;

        path = new ArrayList[N];
        cost = new long[101][M];
        dist = new long[N][101];
        for (int i = 0; i < path.length; i++) {
            path[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            cost[0][i] = c;
            path[u].add(new Node(v, i, 0, c));
            path[v].add(new Node(u, i, 0, c));
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 1; i <= K; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                cost[i][j] = Long.parseLong(st.nextToken());
            }
        }

        System.out.println(dijkstra(A, B, K));
    }

    /**
     *
     * dijkstra - shortest path
     *
     * line 102 ~ 110: find shortest path using magic (current count ~ total count)
     *
     * @param start
     * @param end
     * @param k
     * @return
     */
    private static long dijkstra(int start, int end, int k) {
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.offer(new Node(start, 0, 0, 0));
        dist[start][0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.cost > dist[current.node][current.count]) continue;

            for(Node next: path[current.node]) {
                for(int nextCount = current.count; nextCount <= k; nextCount++) {
                    if (dist[next.node][nextCount] <= dist[current.node][current.count] + cost[nextCount][next.index]) continue;
                    dist[next.node][nextCount] = dist[current.node][current.count] + cost[nextCount][next.index];

                    pq.offer(new Node(next.node, next.index, nextCount, dist[next.node][nextCount]));
                }
            }
        }

        long answer = INF;
        for(int i = 0; i <= k; i++) {
            answer = Math.min(answer, dist[end][i]);
        }

        return answer;
    }
}
