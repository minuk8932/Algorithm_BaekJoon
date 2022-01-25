package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2428반: 표절
 *
 * @see https://www.acmicpc.net/problem/2428
 *
 */
public class Boj2428 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] files = new int[N];
        for(int i = 0; i < N; i++) {
            files[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(files);
        System.out.println(inspection(files));
    }

    private static long inspection(int[] files) {
        long answer = 0;

        for(int i = 0; i < files.length; i++) {
            int left = i;
            int right = files.length - 1;

            long target = 0;

            while(left <= right) {
                int mid = (left + right) >> 1;

                if (files[i] * 10 >= files[mid] * 9) {
                    left = mid + 1;
                    target = mid;
                }
                else{
                    right = mid - 1;
                }
            }

            answer += target - i;
        }

        return answer;
    }
}