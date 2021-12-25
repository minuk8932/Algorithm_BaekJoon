package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23835번: 어떤 우유의 배달목록 (Easy)
 *
 * @see https://www.acmicpc.net/problem/23835
 *
 */
public class Boj23835 {

    private static final String NEW_LINE = "\n";

    private static List<Integer>[] rooms;
    private static long[] milk;
    private static int[] passed;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        rooms = new ArrayList[N];
        milk = new long[N];
        for(int i = 0; i < N; i++) {
            rooms[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            rooms[a].add(b);
            rooms[b].add(a);
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        passed = new int[N];

        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;

                bfs(u, v);
                recursion(v, u);
            }
            else {
                int x = Integer.parseInt(st.nextToken()) - 1;
                sb.append(milk[x]).append(NEW_LINE);
            }
        }

        System.out.print(sb.toString());
    }

    /**
     *
     * DFS: Tracking reverse track
     *
     * @param current
     * @param target
     */
    private static void recursion(int current, int target) {
        if(current == target) return;
        milk[current] += passed[current];

        for(int next: rooms[current]) {
            if(passed[next] + 1 != passed[current]) continue;
            recursion(next, target);
        }
    }

    /**
     *
     * BFS: Flood fill until end
     *
     * @param start
     * @param end
     */
    private static void bfs(int start, int end) {
        Arrays.fill(passed, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        passed[start] = 0;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: rooms[current]) {
                if(passed[next] != -1) continue;
                passed[next] = passed[current] + 1;

                if(next == end) return;
                q.offer(next);
            }
        }
    }
}
