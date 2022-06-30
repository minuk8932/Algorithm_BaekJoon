package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14217번: 그래프 탐색
 *
 * @see https://www.acmicpc.net/problem/14217
 *
 */
public class Boj14217 {

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static ArrayList<ArrayList<Integer>> path = new ArrayList<>();
    private static boolean[][] blocked;
    private static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            path.add(i, new ArrayList<>());
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            path.get(u).add(v);
            path.get(v).add(u);
        }

        int q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        blocked = new boolean[n][n];

        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;

            if(a == 1) {
                path.get(i).add(j);
                path.get(j).add(i);
                blocked[i][j] = blocked[j][i] = false;
            }
            else {
                blocked[i][j] = blocked[j][i] = true;
            }

            StringBuilder pathTracking = new StringBuilder();
            pathTracking.append(0).append(SPACE);
            for(int s = 1; s < n; s++) {
                visit = new int[n];
                pathTracking.append(bfs(s)).append(SPACE);
            }

            sb.append(pathTracking).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        visit[start] = 1;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: path.get(current)) {
                if(blocked[current][next]) continue;
                if(visit[next] != 0) continue;
                visit[next] = visit[current] + 1;

                if(next == 0) return visit[next] - 1;
                q.offer(next);
            }
        }

        return -1;
    }
}
