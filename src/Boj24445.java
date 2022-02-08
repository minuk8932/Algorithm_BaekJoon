import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 24445번: 알고리즘 수업 - 너비 우선 탐색 2
 *
 * @see https://www.acmicpc.net/problem/24445
 *
 */
public class Boj24445 {

    private static final String NEW_LINE = "\n";
    private static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()) - 1;

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            graph[u].add(-v);
            graph[v].add(-u);
        }

        for(int i = 0; i < N; i++) {
            Collections.sort(graph[i]);
        }

        System.out.println(bfs(R));
    }

    private static String bfs(int start) {
        int[] visit = new int[graph.length];
        int count = 1;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visit[start] = count++;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: graph[current]) {
                if(visit[-next] != 0) continue;
                visit[-next] = count++;

                q.offer(-next);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int v: visit) {
            sb.append(v).append(NEW_LINE);
        }

        return sb.toString();
    }
}
