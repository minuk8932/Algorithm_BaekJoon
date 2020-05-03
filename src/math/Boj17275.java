package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17275번: 부족 전쟁
 *
 * @see https://www.acmicpc.net/problem/17275/
 *
 */
public class Boj17275 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] alliance = new int[N];

            while(M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                alliance[a]++;                                  // make colored
                alliance[b]++;
            }

            sb.append(calculate(N, alliance)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long calculate(int n, int[] alli) {
        long total = getTriangles(n);                           // total tribes number
        long except = 0;

        for(long colored: alli) {
            if(colored == n - 1 || colored == 0) continue;      // triple alliance or triple hostile
            except += (colored * (n - 1 - colored));
        }

        return total - except / 2;                              // except duplicated
    }

    private static long getTriangles(long n) {
        return n * (n - 1) * (n - 2) / 6;
    }
}
