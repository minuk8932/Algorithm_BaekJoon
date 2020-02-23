package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13702번: 이상한 술집
 *
 * @see https://www.acmicpc.net/problem/13702/
 *
 */
public class Boj13702 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] pot = new int[N];
        long sum = 0;
        for(int i = 0; i < N; i++){
            pot[i] = Integer.parseInt(br.readLine());
            sum += pot[i];
        }

        System.out.println(make(N, K, pot, sum));
    }

    private static long make(int n, int k, int[] p, long sum){
        long result = 0;

        long start = 0, end = sum / k;

        while(start <= end) {
            long mid = (start + end) / 2;

            long count = 0;
            for (int i = 0; i < n; i++) {
                count += p[i] / mid;
            }

            if(count >= k){                         // give more alchol
                start = mid + 1;
                result = Math.max(result, mid);
            }
            else{
                end = mid - 1;
            }
        }

        return result;
    }
}
