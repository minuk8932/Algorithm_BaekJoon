package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 16896번: 더곱이일 게임
 *
 * @see https://www.acmicpc.net/problem/16896
 *
 */
public class Boj16896 {
    private static final String C = "cubelover\n", K = "koosaga\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            long N = Long.parseLong(br.readLine());
            sb.append(winner(N));
        }

        System.out.println(sb.toString());
    }

    private static String winner(long n) {
        if(n % 2 == 0) return C;
        n = (n - 1) / 2;

        int index = 0;
        while(n > 0) {
            if(index % 2 == 1 && n % 2 == 1) return C;
            index++;
            n /= 2;
        }

        return K;
    }
}
