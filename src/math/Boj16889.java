package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16889번: 중복 없는 님 게임
 *
 * @see https://www.acmicpc.net/problem/16889
 *
 */
public class Boj16889 {
    private static final int SIZE = 60;
    private static int[] grundy = new int[SIZE + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        grundyNumber();                                         // pre-find grundy table with recursion

        int result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            result ^= grundy[Integer.parseInt(st.nextToken())]; // who is the winner ?
        }

        System.out.println(result != 0 ? "koosaga": "cubelover");
    }

    private static void grundyNumber() {
        int cut = 2;
        int val = 0;

        for(int i = 1; i < 61; i++) {
            grundy[i] = (cut - 1);
            val++;

            if(val == cut) {
                cut++;
                val = 0;
            }
        }
    }
}
