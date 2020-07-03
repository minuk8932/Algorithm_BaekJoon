package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11868번: 님 게임 2
 *
 * @see https://www.acmicpc.net/problem/11868/
 *
 */
public class Boj11868 {
    private static final String K = "koosaga";
    private static final String C = "cubelover";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            result ^= Integer.parseInt(st.nextToken());
        }

        System.out.println(result > 0 ? K: C);          // disturbance make zero or starter win
    }
}
