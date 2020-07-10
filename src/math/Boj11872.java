package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11872번: 님 게임 나누기
 *
 * @see https://www.acmicpc.net/problem/11872/
 *
 */
public class Boj11872 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;

        for(int i = 0; i < N; i++){
            int stone = Integer.parseInt(st.nextToken());
            result ^= stone + (stone % 4 == 0 ? -1: stone % 4 == 3 ? 1: 0);
        }

        System.out.println(result != 0 ? "koosaga": "cubelover");
    }
}
