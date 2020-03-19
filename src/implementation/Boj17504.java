package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17504번: 제리와 톰2
 *
 * @see https://www.acmicpc.net/problem/17504/
 *
 */
public class Boj17504 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] cheese = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            cheese[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(stole(N, cheese));
    }

    private static String stole(int n, long[] arr){
        long P = arr[n - 1], Q = 0;
        long adder = 1;

        for(int i = n - 2; i >= 0; i--){
            Q = arr[i] * P + adder;

            adder = P;
            P = Q;
        }

        Q = adder;

        StringBuilder sb = new StringBuilder();
        return sb.append(P - Q).append(" ").append(P).toString();
    }
}
