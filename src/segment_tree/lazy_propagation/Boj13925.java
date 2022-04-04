package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13925번: 수열과 쿼리 13
 *
 * @see https://www.acmicpc.net/problem/13925
 *
 */
public class Boj13925 {

    private static final long MOD = 1_000_000_007L;
    private static final String NEW_LINE = "\n";

    private static long[] tree;
    private static long[][] lazy;

    private static int S = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = S; i < S + N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = S - 1; i > 0; i--) {
            int[] son = getSon(i);
            tree[i] = tree[son[0]] % MOD + tree[son[1]] % MOD;
            tree[i] %= MOD;
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            long v = cmd == 4 ? 0: Long.parseLong(st.nextToken());

            if(cmd == 1) {
                update(x, y, 1, v, 1, 1, S - 1);
            }
            else if(cmd == 2){
                update(x, y, 1, 0, v, 1, S - 1);
            }
            else if(cmd == 3) {
                update(x, y, 1, v, 0, 1, S - 1);
            }
            else {
                sb.append(sum(x, y, 1, 1, S - 1) % MOD).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static long sum(int left, int right, int node, int currentStart, int currentEnd) {

        propagation(node, currentStart, currentEnd);

        if(currentStart > right || currentEnd < left) return 0L;
        if(left <= currentStart && currentEnd <= right) return tree[node] % MOD;

        int mid = (currentStart + currentEnd) >> 1;
        int[] son = getSon(node);

        return (sum(left, right, son[0], currentStart, mid) % MOD)
            + (sum(left, right, son[1], mid + 1, currentEnd) % MOD) % MOD;
    }

    /**
     *
     * Propagation
     *
     * line 101 ~ 104: manage add, multiply lazy / f(x) = a * x + b
     *
     * @param node
     * @param currentStart
     * @param currentEnd
     */
    private static void propagation(int node, int currentStart, int currentEnd) {
        if(lazy[0][node] == 1 && lazy[1][node] == 0) return;

        if(node < S) {
            int[] son = getSon(node);

            for(int i = son[0]; i <= son[1]; i++){
                long a = lazy[0][i];
                long b = lazy[1][i];

                lazy[0][i] = lazy[0][node] * a;
                lazy[0][i] %= MOD;
                lazy[1][i] = lazy[0][node] * b + lazy[1][node];
                lazy[1][i] %= MOD;
            }
        }

        tree[node] = (tree[node] * lazy[0][node]) % MOD
            + ((currentEnd - currentStart + 1) * lazy[1][node]) % MOD;
        tree[node] %= MOD;

        lazy[0][node] = 1;
        lazy[1][node] = 0;
    }

    private static void update(int left, int right, int node, long sum, long multiply
        , int currentStart, int currentEnd) {

        propagation(node, currentStart, currentEnd);
        if(currentStart > right || currentEnd < left) return;

        if(left <= currentStart && currentEnd <= right) {

            lazy[0][node] *= multiply;
            lazy[0][node] %= MOD;

            lazy[1][node] *= multiply;
            lazy[1][node] %= MOD;

            lazy[1][node] += sum;
            lazy[1][node] %= MOD;

            propagation(node, currentStart, currentEnd);

            return;
        }

        int mid = (currentStart + currentEnd) >> 1;
        int[] son = getSon(node);

        update(left, right, son[0], sum, multiply, currentStart, mid);
        update(left, right, son[1], sum, multiply, mid + 1, currentEnd);

        tree[node] = (tree[son[0]] % MOD) + (tree[son[1]] % MOD);
        tree[node] %= MOD;
    }

    private static int[] getSon(int node) {
        int shift = node << 1;
        return new int[] {shift, ++shift};
    }

    private static void init(int n) {
        while(S <= n) {
            S <<= 1;
        }

        int shift = S << 1;
        tree = new long[shift];
        lazy = new long[2][shift];

        Arrays.fill(lazy[0], 1);
    }

}
