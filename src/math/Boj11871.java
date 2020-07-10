package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11871번: 님 게임 홀 짝
 *
 * @see https://www.acmicpc.net/problem/11871/
 *
 */
public class Boj11871 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;

        for(int i = 0; i < N; i++) {
            int stone = Integer.parseInt(st.nextToken());
            result ^= (stone % 2 == 0 ? stone / 2 - 1: ++stone / 2);
        }

        System.out.println(result != 0 ? "koosaga": "cubelover");
    }
}
