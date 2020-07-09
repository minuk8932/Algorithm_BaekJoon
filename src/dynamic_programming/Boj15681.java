package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15681번: 트리와 쿼리
 *
 * @see https://www.acmicpc.net/problem/15681/
 *
 */
public class Boj15681 {
    private static ArrayList<Integer>[] path;
    private static int[] size;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init(N + 1);
        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            path[U].add(V);
            path[V].add(U);
        }

        StringBuilder sb = new StringBuilder();
        recursion(R, 0);

        while(Q-- > 0) {
            int U = Integer.parseInt(br.readLine());
            sb.append(size[U]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int recursion(int current, int parent) {
        if(size[current] != 0) return size[current];
        int result = 1;
        size[current] = 1;

        for(int next: path[current]) {
            if (next == parent) continue;

            result += recursion(next, current);     // count subtree size
        }

        return size[current] = result;
    }

    private static void init(int n) {
        size = new int[n];
        path = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
    }
}
