package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13199번: 치킨 먹고 싶다.
 *
 * @see https://www.acmicpc.net/problem/13199
 *
 */
public class Boj13199 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken()) / P;
            int F = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int coupon = M * C;                                     // coupons

            int more = -coupon / F;
            if ((coupon -= F) >= 0) more += coupon / (F - C) + 1;   // get coupon by coupon chicks

            sb.append(more).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}
