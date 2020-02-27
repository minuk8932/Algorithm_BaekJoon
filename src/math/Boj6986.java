package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 6986번: 절사 평균
 *
 * @see https://www.acmicpc.net/problem/6986/
 *
 */
public class Boj6986 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        double[] score = new double[N];
        for(int i = 0; i < N; i++){
            score[i] = Double.parseDouble(br.readLine());
        }

        Arrays.sort(score);
        System.out.println(truncate(N, K, score) + NEW_LINE + correction(N, K, score));
    }

    private static String truncate(int n, int k, double[] arr){
        double result = 0;

        for(int i = k; i < n - k; i++){
            result += arr[i];
        }

        return String.format("%.2f", result / (n - k * 2));
    }

    private static String correction(int n, int k, double[] arr){
        double result = 0;

        for(int i = k; i < n - k; i++){
            result += arr[i];
        }

        result += k * (arr[k] + arr[n - 1 - k]);

        return String.format("%.2f", result / n);
    }
}
