package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17088: 등차수열 변환
 *
 * @see https://www.acmicpc.net/problem/17088
 *
 */
public class Boj17088 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(arithmeticalSequence(N, arr));
    }

    private static int arithmeticalSequence(int n, int[] arr) {
        if(n == 1) return 0;

        int min = Integer.MAX_VALUE;

        for(int d1 = -1; d1 <= 1; d1++) {
            for(int d2 = -1; d2 <= 1; d2++) {
                int a1 = arr[0] + d1;
                int a2 = arr[1] + d2;

                int diff = a2 - a1;                 // make equidistant difference cases
                int prev = a2;

                boolean flag = true;
                int count = 0;

                for(int i = 2; i < n; i++) {
                    int make = arr[i] - prev - diff;
                    prev = arr[i] - make;

                    if(make < -1 || make > 1){      // can convert ?
                        flag = false;
                        continue;
                    }

                    if(make != 0) count++;
                }

                if(d1 != 0) count++;
                if(d2 != 0) count++;
                if(flag) min = Math.min(min, count);
            }
        }

        return min == Integer.MAX_VALUE ? -1: min;
    }
}
