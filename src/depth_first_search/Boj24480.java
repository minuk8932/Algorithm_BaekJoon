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
 * 백준 22480번: 알고리즘 수업 - 깊이 우선 탐색2
 *
 * @see https://www.acmicpc.net/problem/24480
 *
 */
public class Boj24480 {
    private static int[] visit;
    private static int count;
    private static List<Integer>[] path;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()) - 1;

        path = new ArrayList[N];
        visit = new int[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
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

        recursion(R);
        System.out.println(printer());
    }

    private static void recursion(int current) {
        visit[current] = ++count;

        for(int next: path[current]) {
            if (visit[-next] != 0) continue;

            recursion(-next);
        }
    }

    private static String printer() {
        StringBuilder sb = new StringBuilder();

        for(int v: visit) {
            sb.append(v).append(NEW_LINE);
        }

        return sb.toString();
    }
}
