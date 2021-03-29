package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21278번: 호석이 두 마리 치킨
 *
 * @see https://www.acmicpc.net/problem/21278
 *
 */
public class Boj21278 {

    private static ArrayList<Integer>[] graph;
    private static final int INF = 1_000_000_000;
    private static int[] visit;

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
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        System.out.println(minCost(N));
    }

    /**
     *
     * Min cost
     *
     * line 55 ~ 68: find all cases (100C2)
     *
     * @param n
     * @return
     */
    private static String minCost(int n) {
        int min = INF;
        int[] pair = new int[2];

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                visit = new int[n];
                Arrays.fill(visit, INF);
                bfs(new int[] {i, j});

                int cost = shortCost();
                if(min <= cost) continue;
                min = cost;

                pair[0] = i + 1;
                pair[1] = j + 1;
            }
        }

        return pair[0] + " " + pair[1] + " " + min;
    }

    /**
     *
     * Breadth First Search
     *
     * make all shortest cost
     *
     * @param start
     */
    private static void bfs(int[] start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start[0]);
        q.offer(start[1]);

        visit[start[0]] = visit[start[1]] = 0;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: graph[current]) {
                if(visit[next] <= visit[current] + 1) continue;
                visit[next] = visit[current] + 1;

                q.offer(next);
            }
        }
    }

    private static int shortCost() {
        int cost = 0;

        for(int v: visit) {
            cost += v * 2;
        }

        return cost;
    }
}
