package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20159번: 동작 그만, 밑장 빼기냐?
 *
 * @see https://www.acmicpc.net/problem/20159
 *
 */
public class Boj20159 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cards = new int[N];

        for(int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(flip(N, cards));
    }

    private static int flip(int n, int[] cards) {
        int[][] arr = prefixSum(cards);
        int max = 0;
        int size = n / 2 - 1;

        for(int i = 1; i <= n; i++) {           // find sum by prefix sum
            int div = i / 2;

            if(i % 2 == 0) max = Math.max(arr[1][div] + (arr[0][size] - arr[0][div - 1]), max);
            else max = Math.max(arr[1][div] + (arr[0][size] - arr[0][div]) + cards[n - 1], max);
        }

        return max;
    }

    private static int[][] prefixSum(int[] c) {
        int[][] arr = new int[2][c.length / 2 + 1];

        for (int i = 0; i < c.length; i++) {
            int div = i / 2;

            if(i % 2 != 0) arr[0][div + 1] = arr[0][div] + c[i];
            else arr[1][div + 1] = arr[1][div] + c[i];
        }

        return arr;
    }
}
