package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 2038번: 골룽 수열
 *
 * @see https://www.acmicpc.net/problem/2038/
 *
 */
public class Boj2038 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(sequence(n));
    }

    private static long sequence(int n) {
        if(n == 1) return n;

        HashMap<Long, Long> G = new HashMap<>();
        G.put(1L, 1L);

        long sum = G.get(1L);               // @ref https://stackoverflow.com/questions/12786087/golombs-sequence

        for (long i = 2; i <= n; i++) {
            G.put(i, 1L + G.get(i - G.get(G.get(i - 1))));
            sum += G.get(i);

            if (sum >= n) return i;
        }

        return -1;
    }
}
