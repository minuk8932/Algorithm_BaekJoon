package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23630번: 가장 긴 부분 수열 구하기
 *
 */
public class Boj23630 {

    private static int[][] bin = new int[1_000_000][21];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            bin[i] = integerToBinaryArray(Integer.parseInt(st.nextToken()));
        }

        System.out.println(longestSequence(n));
    }

    private static int longestSequence(int n) {
        int max = 0;

        for(int i = 20; i >= 0; i--) {
            int count = 0;

            for(int j = 0; j < n; j++) {
                count += bin[j][i];
            }

            max = Math.max(count, max);
        }

        return max;
    }

    private static int[] integerToBinaryArray(int number) {
        int[] bit = new int[21];

        int index = 20;
        while(number > 0) {
            bit[index--] = number % 2;
            number /= 2;
        }

        return bit;
    }
}
