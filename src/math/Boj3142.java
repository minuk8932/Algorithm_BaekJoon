package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 3142번: 즐거운 삶을 위한 노력
 *
 * @see https://www.acmicpc.net/problem/3142/
 *
 */
public class Boj3142 {
    private static final String D = "DA\n";
    private static final String E = "NE\n";

    private static final int INF = 1_000_000;
    private static int[] factor = new int[INF + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        eratosthenes();

        int N = Integer.parseInt(br.readLine());
        boolean[] flag = new boolean[INF + 1];
        int result = 0;

        while(N-- > 0) {
            int x = Integer.parseInt(br.readLine());

            while(x > 1) {
                result += (!flag[factor[x]] ? 1: 0) - (flag[factor[x]] ? 1: 0);     // is pow ?

                flag[factor[x]] ^= true;
                x /= factor[x];
            }

            if(result == 0) sb.append(D);
            else sb.append(E);
        }

        System.out.println(sb.toString());
    }

    private static void eratosthenes() {
        for( int i = 2; i <= INF; ++i ) {                   // make prime factor (smallest)
            if(factor[i] != 0) continue;
            factor[i] = i;

            long pow = (long) i * i;
            if(pow >= INF) continue;

            for(int j = (int) pow; j <= INF; j += i) {
                factor[j] = i;
            }
        }
    }
}
