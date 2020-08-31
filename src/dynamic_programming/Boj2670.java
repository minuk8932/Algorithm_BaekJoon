package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author
 * 백준 2670번: 연속부분최대곱
 *
 * @see https://www.acmicpc.net/problem/2670
 *
 */
public class Boj2670 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        double[] data = new double[N];
        for(int i = 0; i < N; i++) {
            data[i] = Double.parseDouble(br.readLine());
        }

        System.out.println(String.format("%.3f", result(N, data)));
    }

    private static double result(int n, double[] d) {
        double result = 0;

        for(int i = 0; i < n; i++) {
            double value = 1;

            for(int j = i; j < n; j++) {
                value *= d[j];
                result = Math.max(result, value);
            }
        }

        return result;
    }
}
