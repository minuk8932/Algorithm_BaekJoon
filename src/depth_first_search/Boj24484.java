package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24484번: 알고리즘 수업 - 깊이 우선 탐색 6
 *
 * @see https://www.acmicpc.net/problem/24484
 *
 */
public class Boj24484 {
    private static long count;
    private static long[] depth;
    private static long[] visit;

    private static List<Integer>[] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()) - 1;

        path = new ArrayList[N];
        depth = new long[N];
        visit = new long[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            depth[i] = -1;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            path[u].add(-v);
            path[v].add(-u);
        }

        for(int i = 0; i < N; i++){
            Collections.sort(path[i]);
        }

        depth[R] = 0;
        recursion(R);
        System.out.println(printer());
    }

    private static void recursion(int current) {

        visit[current] = ++count;

        for(int next: path[current]) {
            if (depth[-next] != -1) continue;
            depth[-next] = depth[current] + 1;

            recursion(-next);
        }
    }

    private static long printer() {
        long answer = 0;

        for(int i = 0; i < visit.length; i++) {
            answer += visit[i] * depth[i];
        }

        return answer;
    }
}
