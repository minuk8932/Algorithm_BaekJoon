package fenwick_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 7578번: 공장
 *
 * @see https://www.acmicpc.net/problem/7578/
 *
 */
public class Boj7578 {
    private static long[] tree;
    private static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new long[N + 1];

        int[] A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] B = new int[1_000_001];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {                      // idx: A value
            B[Integer.parseInt(st.nextToken())] = i;
        }

        System.out.println(fenwickTree(A, B));
    }

    private static long fenwickTree(int[] a, int[] b) {
        long result = 0;

        for (int i = 1; i <= N; i++) {
            result = result + sum(N) - sum(b[a[i]]);        // count crossed
            update(b[a[i]]);                                // fix
        }

        return result;
    }

    private static void update(int idx) {
        while (idx <= N) {
            tree[idx]++;
            idx += (idx & -idx);
        }
    }

    private static long sum(int idx) {
        long sum = 0;

        while (idx > 0) {
            sum = sum + tree[idx];
            idx -= (idx & -idx);
        }

        return sum;
    }
}
