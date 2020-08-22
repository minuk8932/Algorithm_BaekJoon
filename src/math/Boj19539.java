package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19539번: 사과나무
 *
 * @see https://www.acmicpc.net/problem/19539
 *
 */
public class Boj19539 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        int sum = 0;

        for(int i = 0; i < N; i++) {
            int water = Integer.parseInt(st.nextToken());
            sum += water;
            count -= water >> 1;                            // 1, 2 used same amount ?
        }

        System.out.println(sum % 3 == 0 ? count + (sum / 3) > 0 ? "NO": "YES": "NO");
    }
}
