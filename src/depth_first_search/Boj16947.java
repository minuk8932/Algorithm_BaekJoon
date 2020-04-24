package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 16947번: 서울 지하철 2호선
 *
 * @see https://www.acmicpc.net/problem/16947/
 *
 */
public class Boj16947 {
    private static int[] distance;
    private static ArrayList<Integer>[] graph;
    private static ArrayList<Integer> cycle = new ArrayList<>();

    private static int[] visit;

    private static final int REVISIT = -1;
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        init(N);

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        visit = new int[distance.length];
        dfs(0, -1);

        for (int i = 0; i < graph.length; i++) {
            if (visit[i] == REVISIT) cycle.add(i);
        }

        bfs();
        System.out.println(print());
    }

    private static void init(int n) {
        graph = new ArrayList[n];
        distance = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static String print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < distance.length; i++) {
            sb.append(distance[i] - 1).append(SPACE);
        }

        return sb.toString();
    }

    private static int dfs(int current, int prev) {
        if (visit[current] == 1) return current;
        visit[current] = 1;

        for (int next : graph[current]) {
            if (next == prev) continue;                     // bidrection safe
            int cycle = dfs(next, current);

            if(cycle < 0) {
                if(cycle == REVISIT) return cycle;          // revisit
                else continue;
            }
            else {
                visit[current] = REVISIT;

                if (current == cycle) return visit[current];
                else return cycle;
            }
        }

        return -2;
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();

        for (int start : cycle) {
            q.offer(start);
            distance[start] = 1;
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph[current]) {
                if (distance[next] != 0) continue;
                distance[next] = distance[current] + 1;

                q.offer(next);
            }
        }
    }
}
