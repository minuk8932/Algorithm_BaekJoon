package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5024번: dirty driving
 *
 * @see https://www.acmicpc.net/problem/5024
 *
 */
public class Boj5024 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long p = Long.parseLong(st.nextToken());

        long[] distance = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            distance[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(breaking(n, p, distance));
    }

    private static long breaking(int n, long p, long[] d) {
        Arrays.sort(d);
        long max = 0;

        for (int i = 0; i < n; i++) {
            long c = p * (i + 1) - d[i] + d[0];
            if (c > max) max = c;
        }

        return max;
    }
}
