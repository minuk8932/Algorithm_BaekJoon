package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 24447번: 알고리즘 수업 - 너비 우선 탐색 4
 *
 * @see https://www.acmicpc.net/problem/24447
 *
 */
public class Boj24447 {

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

            graph[u].add(v);
            graph[v].add(u);
        }

        for(int i = 0; i < graph.length; i++) {
            Collections.sort(graph[i]);
        }

        System.out.println(bfs(R));
    }

    private static long bfs(int start) {
        long[][] visit = new long[graph.length][2];

        for(int i = 0; i < visit.length; i++) {
            visit[i][0] = -1;
        }

        int count = 1;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visit[start][0] = 0;
        visit[start][1] = count++;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: graph[current]) {
                if(visit[next][0] != -1) continue;
                visit[next][0] = visit[current][0] + 1;
                visit[next][1] = count++;

                q.offer(next);
            }
        }

        long answer = 0;
        for(long[] vArr: visit) {
            answer += vArr[0] * vArr[1];
        }

        return answer;
    }
}
