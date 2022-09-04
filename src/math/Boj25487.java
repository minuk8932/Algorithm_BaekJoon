package math;

import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25487번: 단순한 문제 (Large)
 *
 * @see https://www.acmicpc.net/problem/25487
 *
 */
public class Boj25487 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            sb.append(Math.min(a, Math.min(b, c))).append(NEW_LINE);
        }

        System.out.println(sb);
    }
}
