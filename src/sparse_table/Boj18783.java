package sparse_table;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18783번: Swapity Swapity Swap
 *
 * @see https://www.acmicpc.net/problem/18783
 *
 */
public class Boj18783 {
    private static int[][] parent;
    private static int[] cows;
    private static int[] rotate;

    private static final String NEW_LINE = "\n";

    private static class Range {
        int from;
        int to;

        public Range(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N][31];
        cows = new int[N];
        rotate = new int[N];

        Range[] r = new Range[M];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r[i] = new Range(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        findOne(r);
        connection(N);

        System.out.println(rotating(N, K - 1));
    }

    private static String rotating(int n, int k) {
        StringBuilder sb = new StringBuilder();

        for(int i = 30; i >= 0; i--) {
            int jump = 1 << i;
            if(k < jump) continue;

            k -= jump;
            for(int j = 0; j < n; j++) {                    // rotating
                cows[j] = parent[cows[j]][i];
            }
        }

        for(int c: cows) {
            sb.append(parent[c][0] + 1).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void findOne(Range[] arr) {
        for(int i = 0; i < cows.length; i++) {
            cows[i] = i;
            rotate[i] = i;
        }

        for(Range r: arr) {
            int ran = r.to - r.from;
            int mod = ran % 2;
            int loop = ran / 2 + mod;

            int index = 0;
            for(int i = r.from; i < r.from + loop; i++) {       // 1 time rotating result
                swap(i, r.from + ran - index++);
            }
        }
    }

    private static void swap(int src, int snk) {
        int tmp = rotate[src];
        rotate[src] = rotate[snk];
        rotate[snk] = tmp;
    }

    private static void connection(int n) {
        for(int i = 0; i < n; i++) {
            parent[i][0] = rotate[i];
        }

        for(int p = 1; p < 31; p++) {                   // make n times rotating relations
            for (int cur = 0; cur < n; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }
}
