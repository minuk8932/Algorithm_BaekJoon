package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20530번: 양분
 *
 * @see https://www.acmicpc.net/problem/20530
 *
 */
public class Boj20530 {

    private static final String NEW_LINE = "\n";

    private static List<Integer>[] path;
    private static List<Integer> cycle = new ArrayList<>();

    private static int[] set;
    private static boolean[] visit;
    private static int src = -2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        path = new ArrayList[N];
        visit = new boolean[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken()) - 1;
            int vertex2 = Integer.parseInt(st.nextToken()) - 1;

            path[vertex1].add(vertex2);
            path[vertex2].add(vertex1);
        }

        cycleSearch(0, -1);
        distribution();

        StringBuilder sb = new StringBuilder();
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            sb.append(set[start] == set[end] ? 1: 2).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void recursion(int current, int number) {
        if(set[current] != -1) return;
        set[current] = number;

        for(int next: path[current]) {
            recursion(next, number);
        }
    }

    /**
     *
     * Distribution
     *
     * by cycle & non-cycle => cost distinct by cases
     *
     */
    private static void distribution() {
        set = new int[visit.length];
        Arrays.fill(set, -1);

        for (int c: cycle) {
            set[c] = c;
        }

        for (int start: cycle) {
            set[start] = -1;
            recursion(start, start);
        }
    }

    /**
     *
     * Cycle search
     *
     * @param current
     * @param before
     */
    private static void cycleSearch(int current, int before) {
        if(visit[current]) {
            src = current;
            cycle.add(src);
            return;
        }

        visit[current] = true;

        for(int next: path[current]) {
            if(next == before) continue;

            cycleSearch(next, current);
            if(src == -1) return;
            if(src == current) {
                src = -1;
                return;
            }

            if(src > -1) {
                cycle.add(current);
                return;
            }
        }
    }
}
