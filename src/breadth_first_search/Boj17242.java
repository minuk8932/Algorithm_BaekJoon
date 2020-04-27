package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 17242번: Kaka & Bebe
 *
 * @see https://www.acmicpc.net/problem/17242/
 *
 */
public class Boj17242 {
    private static ArrayList<Node>[] graph;
    private static final int INF = 10_000_000;
    private static final int LIMIT = 1_000;

    private static class Node {
        int node;
        int kaka;
        int bebe;

        public Node(int node, int kaka, int bebe) {
            this.node = node;
            this.kaka = kaka;
            this.bebe = bebe;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c, d));
            graph[b].add(new Node(a, c, d));
        }

        System.out.println(bfs(N));
    }

    private static int bfs(int n) {
        int[][] cost = new int[n][LIMIT + 1];
        boolean[][] visit = new boolean[n][LIMIT + 1];

        for(int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        cost[0][0] = 0;

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(Node next: graph[current.node]) {
                int nextKaka = current.kaka + next.kaka;
                int nextBebe = current.bebe + next.bebe;

                if(nextKaka > LIMIT || nextBebe > LIMIT) continue;
                if(visit[next.node][nextKaka]) continue;
                visit[next.node][nextKaka] = true;
                cost[next.node][nextKaka] = nextBebe;               // considering kaka & bebe both

                q.offer(new Node(next.node, nextKaka, nextBebe));
            }
        }

        int result = INF;
        for(int kaka = 0; kaka <= LIMIT; ++kaka)
            if(cost[n - 1][kaka] < INF) result = Math.min(kaka * cost[n - 1][kaka], result);

        return result == INF ? -1: result;
    }
}
