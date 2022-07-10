package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24913번: 개표
 *
 * @see https://www.acmicpc.net/problem/24913
 *
 */
public class Boj24913 {

    private static final String O = "YES\n";
    private static final String X = "NO\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        long[] candidate = new long[N + 1];
        long max = 0;
        long total = 0;

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            long x = Long.parseLong(st.nextToken());

            if(cmd == 1) {
                int p = Integer.parseInt(st.nextToken()) - 1;

                candidate[p] += x;
                if(p != N){
                    total += x;
                    max = Math.max(max, candidate[p]);
                }
            }
            else {
                long y = Long.parseLong(st.nextToken());
                long jh = candidate[N] + x;
                sb.append(jh > max && y + total <= N * (jh - 1) ? O: X);
            }
        }

        System.out.println(sb);
    }
}
