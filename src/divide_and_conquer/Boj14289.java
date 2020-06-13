package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14289번: 본대 산책 3
 *
 * @see https://www.amicpc.net/problem/14289
 *
 */
public class Boj14289 {
    private static long[][] path;
    private static int n;

    private static final long MOD = 1_000_000_007;
    private static HashMap<Integer, long[][]> visit = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        path = new long[n][n];
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            path[node1][node2] = 1;                 // cost 1
            path[node2][node1] = 1;
        }

        int D = Integer.parseInt(br.readLine());
        path = recursion(D, path);

        System.out.println(path[0][0]);
    }

    private static long[][] martixProduct(long[][] src, long[][] snk) {
        long[][] arr = new long[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                long value = 0;

                for(int k = 0; k < n; k++) {
                    value = modulation(value, src[i][k], snk[k][j]);
                }

                arr[i][j] = value;
            }
        }

        return arr;
    }

    private static long modulation(long v, long s, long t) {
        return ((v % MOD) + ((s % MOD) * (t % MOD)) % MOD) % MOD;
    }

    private static long[][] recursion(int d, long[][] arr) {
        if(d == 1) return arr;
        if(visit.containsKey(d)) return visit.get(d);

        int half = d / 2;
        long[][] even = recursion(half, arr);
        visit.put(half, even);

        if(d % 2 == 0){                         // divide by 2 cases & find path cost
            return martixProduct(even, even);
        }
        else {
            long[][] odd = recursion(half + 1, arr);
            visit.put(half + 1, odd);

            return martixProduct(even, odd);
        }
    }
}
