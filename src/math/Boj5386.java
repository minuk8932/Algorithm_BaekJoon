package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5386번: 금화 게임
 *
 * @see https://www.acmicpc.net/problem/5386/
 *
 */
public class Boj5386 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if(K % 2 == 1) sb.append(S % 2).append(NEW_LINE);
            else {
                S %= (K + 1);
                sb.append(S == K || S == 0 ? S: S % 2).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }
}
