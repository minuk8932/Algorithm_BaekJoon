package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1407번: 2로 몇번 나누어질까
 *
 * @see https://www.acmicpc.net/problem/1407/
 *
 */
public class Boj1407 {
    private static long[] pows = new long[64];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        init();
        System.out.println(divide(A, B));
    }

    private static void init() {
        long two = 2;

        for(int i = 1; i < pows.length; i++) {
            pows[i] = (long) Math.pow(two, i);
        }
    }

    private static long divide(long a, long b) {
        long result = 0;
        long[] accum = new long[64];

        long odd = (b - a + 1) / 2 + ((a % 2 == 1) && (b % 2 == 1) ? 1: 0);

        for(int i = 1; i < pows.length; i++) {                   // counting contains in range
            if (pows[i] > b) break;
            accum[i] = counting(i, a, b);
        }

        long prev = 0;
        for(int i = pows.length - 1; i > 0; i--) {              // make sum with duplicated
            if (accum[i] < prev) continue;
            result += (accum[i] - prev) * pows[i];
            prev = accum[i];
        }

        return result + odd;
    }

    private static long counting(int idx, long a, long b) {
        long from = a / pows[idx];
        long to = b / pows[idx];

        return to - from + (a % pows[idx] == 0 ? 1: 0);
    }
}
