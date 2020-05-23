package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1246번: 온라인 판매
 *
 * @see https://www.acmicpc.net/problem/1246/
 *
 */
public class Boj1246 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] eggs = new int[M];
        for(int i = 0; i < M; i++) {
            eggs[i] = -Integer.parseInt(br.readLine());
        }

        Arrays.sort(eggs);
        System.out.println(sell(N, eggs));
    }

    private static String sell(int n, int[] eggs) {
        int result = 0;
        int cost = 0;

        for(int i = 0; i < Math.min(eggs.length, n); i++) {
            int value = -(i + 1) * eggs[i];

            if(value < result) continue;

            result = value;
            cost = -eggs[i];
        }

        return cost + " " + result;
    }
}
