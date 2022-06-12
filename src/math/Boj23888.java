package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23888번: 등차수열과 쿼리
 *
 * @see https://www.acmicpc.net/problem/23888
 *
 */
public class Boj23888 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long d = Long.parseLong(st.nextToken());

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            long from = a + d * (l - 1);
            long to = a + d * (r - 1);
            int n = (r - l + 1);

            sb.append(cmd == 1 ? sum(from, to, n): l == r ? from: gcd(a, d)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    private static long sum(long a, long b, int n) {
        return ((a + b) * n) >> 1L;
    }
}
