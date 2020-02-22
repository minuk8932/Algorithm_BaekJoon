package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 2830번: 행성 X3
 *
 * @see https://www.acmicpc.net/problem/2830/
 *
 */
public class Boj2830 {
    private static int[][] binary;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] name = new int[N];
        binary = new int[N][21];
        for (int i = 0; i < N; i++) {
            name[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getValue(N, name));
    }

    private static long getValue(int n, int[] arr){
        long res = 0;

        for(int i = 0; i < n; i++){
            integerToBinary(i, arr[i]);
        }

        long pow = 1;
        for(int i = 20; i >= 0; i--){
            long[] count = new long[2];

            for(int j = 0; j < n; j++){             // find zeroes & ones
                count[binary[j][i]]++;
            }

            long mul = count[0] * count[1];
            res += mul * pow;                       // make result
            pow *= 2;
        }

        return res;
    }

    private static void integerToBinary(int idx, int val){
        int len = 20;

        while(val > 0){                         // make bits
            binary[idx][len--] = val % 2;
            val /= 2;
        }
    }
}
