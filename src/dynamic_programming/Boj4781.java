package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 4781번: 사탕 가게
 *
 * @see https://www.acmicpc.net/problem/4781/
 *
 */
public class Boj4781 {
    private static int[] calorie;
    private static int[] candies;
    private static int[] payment;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

            if(n == 0 && m == 0.00) break;
            calorie = new int[10_001];
            candies = new int[10_001];
            payment = new int[10_001];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int p = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);     // caution!

                candies[i] = c;
                payment[i] = p;
            }

            sb.append(search(m, n)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int search(int total, int n) {
        for(int i = 0; i <= total; i++){
            for(int j = 0; j < n; j++){
                int diff = i - payment[j];                                      // can buy
                if(diff < 0) continue;

                calorie[i] = Math.max(calorie[diff] + candies[j], calorie[i]);
            }
        }

        int max = 0;

        for(int i = 0; i <= total; i++) {
            max = Math.max(max, calorie[i]);
        }

        return max;
    }
}
