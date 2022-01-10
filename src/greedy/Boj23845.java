package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23845번: 마트료시카
 *
 * @see https://www.acmicpc.net/problem/23845
 *
 */
public class Boj23845 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] matryoshka = new long[200_001];
        int[] cost = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            matryoshka[cost[i]]++;
        }

        Arrays.sort(cost);
        System.out.println(maximumProfit(matryoshka, cost));
    }

    /**
     *
     * Maximum profit
     *
     * line 52 ~ 63: find longest sequence & handling accumulated
     *
     * @param mat
     * @param cost
     * @return
     */
    private static long maximumProfit(long[] mat, int[] cost) {
        long answer = 0;
        long count;
        long max;

        for(int i = 0; i < cost.length; i++){
            if(mat[cost[i]] == 0) continue;
            mat[cost[i]]--;
            max = cost[i];
            count = 1;

            int value = cost[i] + 1;
            while(true) {
                if(mat[value] == 0) {
                    answer += max * count;
                    break;
                }

                max = value;
                mat[value]--;
                count++;
                value++;
            }
        }

        return answer;
    }
}
