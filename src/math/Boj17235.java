package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 17235번: Good pizza, Great Pizza
 *
 * @see https://www.acmicpc.net/problem/17235/
 *
 */
public class Boj17235 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        BigDecimal[] positive = new BigDecimal[N];
        BigDecimal[] negative = new BigDecimal[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            positive[i] = new BigDecimal((y - x) + "");             // y = x + c        -> rotate 45
            negative[i] = new BigDecimal((y + x) + "");             // y = x - c
        }

        Arrays.sort(positive);
        Arrays.sort(negative);
        System.out.println(makePizza(N, positive, negative));
    }

    private static BigDecimal makePizza (int size, BigDecimal[] p, BigDecimal[] n) {
        BigDecimal c1result = p[size - 1].subtract(p[0]);
        BigDecimal c2result = n[size - 1].subtract(n[0]);

        BigDecimal line;
        if(c1result.compareTo(c2result) < 0) line = c2result;
        else line = c1result;

        return line.multiply(line).divide(new BigDecimal("2"));
    }
}
