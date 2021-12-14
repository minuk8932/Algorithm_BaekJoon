package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiFunction;

/**
 *
 * @author exponential-e
 * 백준 23827번: 수열 (Easy)
 *
 * @see https://www.acmicpc.net/problem/23827
 *
 */
public class Boj23827 {

    private static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];
        long total = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            total += arr[i];
        }

        System.out.println(getSummary(total, arr));
    }

    private static long getSummary(long T, long[] arr) {
        long result = 0L;

        for(int i = 0; i < arr.length; i++){
            T -= arr[i];
            result = MOD_SUM.apply(result, MOD_MUL.apply(T, arr[i]));
        }

        return result;
    }

    private static final BiFunction<Long, Long, Long> MOD_SUM = (x, y) ->
            ((x % MOD) + (y % MOD)) % MOD;

    private static final BiFunction<Long, Long, Long> MOD_MUL = (x, y) ->
            ((x % MOD) * (y % MOD)) % MOD;
}
