package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1222번: 홍준 프로그래밍 대회
 *
 * @see https://www.acmicpc.net/problem/1222/
 *
 */
public class Boj1222 {
    private static final int INF = 2_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] student = new int[INF];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            student[Integer.parseInt(st.nextToken())]++;
        }

        System.out.println(contest(student));
    }

    private static long contest(int[] arr){
        long result = 0;

        for(int i = 1; i < INF; i++){
            long count = 0;

            for(int j = i; j < INF; j += i){                // find prime
                count += arr[j];
            }

            if(count <= 1) continue;
            result = Math.max(result, count * i);
        }

        return result;
    }
}
