package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author exponential-e
 * 백준 9009번: 피보나치
 *
 * @see https://www.acmicpc.net/problem/9009
 *
 */
public class Boj9009 {

    private static int[] dp = new int[46];

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        Arrays.fill(dp, -1);
        fibonacci(45);

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int n = Integer.parseInt(br.readLine());
            List<Integer> fibo = getComponents(n);

            for(int f: fibo) {
                sb.append(f).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static List<Integer> getComponents(int value) {
        List<Integer> list = new ArrayList<>();

        for(int i = dp.length - 1; i > 0; i--) {
            if(value < dp[i]) continue;
            value -= dp[i];
            list.add(dp[i]);
        }

        Collections.sort(list);
        return list;
    }

    private static int fibonacci(int current) {
        if(current == 1 || current == 2) return dp[current] = 1;
        if(dp[current] != -1) return dp[current];

        return dp[current] = fibonacci(current - 1) + fibonacci(current - 2);
    }
}
