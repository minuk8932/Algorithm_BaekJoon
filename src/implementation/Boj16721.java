package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16721번: Structure of Balanced Networks
 *
 * @see https://www.acmicpc.net/problem/16721/
 *
 */
public class Boj16721 {
    private static String[] closed = new String[5_000];

    private static final String NEW_LINE = "\n";
    private static final String MINUS = "-";
    private static final String PLUS = "+";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                String target = st.nextToken();
                if(i != 0) continue;

                closed[j] = target;
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int min = Math.min(B, C);
            int max = Math.max(B, C);

            if(min == 0) sb.append(closed[min + max]).append(NEW_LINE);                         // 1th row data
            else sb.append(!closed[min].equals(closed[max]) ? MINUS: PLUS).append(NEW_LINE);    // considering with 1th row data
        }

        System.out.println(sb.toString());
    }
}
