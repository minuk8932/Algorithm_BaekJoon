package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5043번: 정말 좋은 압축
 *
 * @see https://www.acmicpc.net/problem/5043
 *
 */
public class Boj5043 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        System.out.println(compression(N, d));
    }

    private static String compression (long n, int loop) {
        long result = 1;
        long expo2 = 2;

        while(loop-- > 0) {
            result += expo2;
            expo2 <<= 1L;
        }

        return n <= result ? "yes": "no";
    }
}
