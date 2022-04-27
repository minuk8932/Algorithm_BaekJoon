package dijkstra;

import common.Node;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14284번: 간선 이어가기2
 *
 * @see https://www.acmicpc.net/problem/14284
 *
 */
public class Boj14284 {

    private static final int INF = 1_000_000_000;
    private static List<Node>[] path;
    private static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n);

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            path[a].add(new Node.Builder(b).cost(c).build());
            path[b].add(new Node.Builder(a).cost(c).build());
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(dijkstra(start, end));
    }

    private static void init(int n) {
        path = new ArrayList[n];
        dist = new int[n];

        for(int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
            dist[i] = INF;
        }
    }

    private static int dijkstra(int start, int end) {
        Queue<Node<Integer, Integer>> pq
            = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));

        pq.offer(new Node.Builder(start).cost(0).build());
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node<Integer, Integer> current = pq.poll();

            for(Node<Integer, Integer> next: path[current.getNode()]){
                if(dist[next.getNode()] <= dist[current.getNode()] + next.getCost()) continue;
                dist[next.getNode()] = dist[current.getNode()] + next.getCost();

                pq.offer(new Node.Builder(next.getNode()).cost(dist[next.getNode()]).build());
            }
        }

        return dist[end];
    }
}
