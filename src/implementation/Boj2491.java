package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2491번: 수열
 *
 * @see https://www.acmicpc.net/problem/2491
 *
 */
public class Boj2491 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N];

        for(int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // IS, DS check
        System.out.println(Math.max(search(1, N, 1, seq), search(N - 2, -1, -1, seq)));
    }

    private static int search(int start, int end, int add, int[] arr) {
        if(arr.length <= 2) return arr.length;

        int count = 1;
        int max = 0;

        int prev = arr[start - add];

        for(int i = start; i * add < end * add; i += add){
            if(arr[i] >= prev) count++;
            else{
                max = Math.max(max, count);
                count = 1;
            }

            prev = arr[i];
        }

        return Math.max(count, max);
    }
}
