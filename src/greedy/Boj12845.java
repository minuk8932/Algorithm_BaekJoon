package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12845번: 모두의 마블
 *
 * @see https://www.acmicpc.net/problem/12845
 *
 */
public class Boj12845 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] L = new int[n];
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            L[i] = Integer.parseInt(st.nextToken());
            max = Math.max(L[i], max);
        }

        System.out.println(marble(L, max));
    }

    private static int marble(int[] level, int max) {
        int gold = -max;

        for(int lev: level) {
            gold += max + lev;
        }

        return gold - max;
    }
}
