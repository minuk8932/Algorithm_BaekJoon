package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17951번: 흩날리는 시험지 속에서 내 평점이 느껴진거야
 *
 * @see https://www.acmicpc.net/problem/17951/
 *
 */
public class Boj17951 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] score = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(N, K, score));
    }

    private static int binarySearch(int n, int k, int[] s){
        int start = 0, end = 20_000_000;
        int result = 0;

        while(start <= end) {
            int mid = (start + end) / 2;
            int count = 0, sum = 0;

            for(int i = 0; i < n; i++){
                sum += s[i];

                if(sum >= mid){             // make sum with add count
                    count++;
                    sum = 0;
                }
            }

            if(count >= k) {                // if count over than or equal to k ? is candidate
                start = mid + 1;
                result = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return result;
    }
}
