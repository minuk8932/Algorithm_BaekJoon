package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16882번: 카드 게임
 *
 * @see https://www.acmicpc.net/problem/16882/
 *
 */
public class Boj16882 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[100_001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[Integer.parseInt(st.nextToken())]++;
        }

        System.out.println(winner(arr));
    }

    private static String winner(int[] arr) {
        for(int a: arr) {
            if(a % 2 == 1) return "koosaga";
        }

        return "cubelover";                     // all even data case koosaga can't win
    }
}
