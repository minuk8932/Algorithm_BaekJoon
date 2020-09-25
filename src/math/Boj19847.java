package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19847번: 여우 신탁
 *
 * @see https://www.acmicpc.net/problem/19847
 *
 */
public class Boj19847 {
    private static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        double[] numbers = new double[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            numbers[i] = Double.parseDouble(st.nextToken());
        }

        result = new int[(int) numbers[0]];
        for(int i = 0; i < result.length; i++) {        // counting
            result[i]++;
        }

        System.out.println(oracle(numbers));
    }

    private static double oracle(double[] nums) {
        double total = 0;
        double mod = nums[0];
        int end = (int) nums[0];

        for(int index = 1; index < nums.length; index++) {
            if(mod < nums[index]) continue;
            mod = nums[index];

            for(int i = (int) mod; i < end; i++) {          // data compression
                if(result[i] == 0) continue;
                int value = result[i];
                result[i] = 0;
                result[(int) (i % mod)] += value;
            }

            end = (int) mod;
        }

        for(int i = 0; i < nums[0]; i++) {
            total += result[i] * i;
        }

        return total / nums[0];
    }
}
