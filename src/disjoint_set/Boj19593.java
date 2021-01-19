package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19593번: 다도해
 *
 * @see https://www.acmicpc.net/problem/19593
 *
 */
public class Boj19593 {

    private static int[] parent;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            long Seed = Long.parseLong(st.nextToken());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            init(N);
            sb.append(grouping(N, Seed, A, B)).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }

    private static long grouping(int n, long s, long a, long b) {
        long[] E = new long[n * n];
        long N = n;
        long nPow = N * N;

        int M = 0;

        for(int i = 1; i < E.length; i++) {
            if(i == 1) E[i] = s % nPow;
            else E[i] = (E[i - 1] * a + b) % nPow;

            int X = (int) (E[i] / N);
            int Y = (int) (E[i] % N);

            if(parent[find(X)] == -n) return M;
            M++;
            merge(X, Y);                                // make bridge
        }

        return 0;
    }

    private static void init(int n) {
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}
