package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1758번: 알바생 강호
 *
 * @see https://www.acmicpc.net/problem/1758/
 *
 */
public class Boj1758 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] tip = new int[N];
        for(int i = 0; i < N; i++){
            tip[i] = -Integer.parseInt(br.readLine());
        }

        System.out.println(getTip(N, tip));
    }

    private static long getTip(int n, int[] arr){
        long result = 0;

        Arrays.sort(arr);

        for(int i = 0; i < n; i++){
            long val = -arr[i] - i;         // tips
            if(val < 0) break;

            result += val;
        }

        return result;
    }
}
