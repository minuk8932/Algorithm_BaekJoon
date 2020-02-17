package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 4159번: 알래스카
 *
 * @see https://www.acmicpc.net/problem/4159/
 *
 */
public class Boj4159 {
    private static final String I = "IMPOSSIBLE\n";
    private static final String P = "POSSIBLE\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            int[] recharge = new int[n];
            for(int i = 0; i < n; i++){
                recharge[i] = Integer.parseInt(br.readLine());
            }

            sb.append(reached(n, recharge));
        }

        System.out.print(sb.toString());
    }

    private static String reached(int n, int[] arr){
        Arrays.sort(arr);
        int prev = arr[0];

        for(int i = 1; i < n; i++){
            if(arr[i] - prev > 200) return I;
            prev = arr[i];
        }

        if(1422 - arr[n - 1] > 100) return I;       // Delta Junction round trip possible?
        return P;
    }
}
