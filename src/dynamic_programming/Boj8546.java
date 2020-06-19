package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 8546번: Szyfr
 *
 * @see https://www.acmicpc.net/problem/8546/
 *
 */
public class Boj8546 {
    private static int[] fibo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        fibo = new int[m];
        System.out.println(pisanoPeriod(n, m));
    }

    private static String pisanoPeriod(int n, int m) {
        fibo = new int[m + 1];
        fibo[0] = 0;
        fibo[1] = 1;

        int len = Math.min(m, 13);
        for(int i = 2; i <= len; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 10;
        }

        for (int i = 14; i <= m; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 10;;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = n; i <= m; i++) {
            sb.append(fibo[i]);
        }

        return sb.toString();
    }
}
