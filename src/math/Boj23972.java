package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23972번: 악마의 제안
 *
 * @see https://www.acmicpc.net/problem/23972
 *
 */
public class Boj23972 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long K = Long.parseLong(st.nextToken());
        long N = Long.parseLong(st.nextToken());

        System.out.println(exchange(K, N));
    }

    private static long exchange(long k, long n) {
        if(n == 1) return -1;

        long up = k * n % (n - 1) == 0 ? 0: 1;
        return k * n / (n - 1) + up;
    }
}
