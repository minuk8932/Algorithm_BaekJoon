package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 16233번: 수학 문제
 *
 * @see https://www.acmicpc.net/problem/16233
 *
 */
public class Boj16233 {
    private static final String SPACE = " ";

    private static final int SIZE = 100_000;
    private static int[] prefix = new int[SIZE + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        init();

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(findX(N)).append(SPACE);
        }

        System.out.println(sb.toString());
    }

    private static void init() {
        for(int i = 1; i < prefix.length; i++) {
            int loop = i;

            while(loop > 0) {                                   // pre making each number sum
                prefix[i] += loop % 10;
                loop /= 10;
            }
        }
    }

    private static int findX(int n) {
        int x = n;

        for(int i = 1; i < 101; i++) {
            int sum = prefix[x / SIZE] + prefix[x % SIZE] + n;  // big number(x) each sum with n

            if (sum == x) break;
            if (sum < x++) return -1;
        }

        return x;
    }
}
