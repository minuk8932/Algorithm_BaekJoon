package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2230번: 수 고르기
 *
 * @see https://www.acmicpc.net/problem/2230
 *
 */
public class Boj2230 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(twoPointer(M, arr));
    }

    private static int twoPointer(int limit, int[] arr) {
        int min = 2_000_000_001;
        Arrays.sort(arr);

        int start = 0, end = 0;

        while(end < arr.length && start <= end) {
            int diff = Math.abs(arr[end] - arr[start]);

            if(diff < limit) {
                end++;
                continue;
            }

            min = Math.min(min, diff);
            start++;
        }

        return min;
    }
}
