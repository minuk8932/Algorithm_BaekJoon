package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1557번: 제곱 ㄴㄴ
 *
 * @see https://www.acmicpc.net/problem/1557/
 *
 */
public class Boj1557 {
    private static int[] mobius = new int[1_000_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        eratosthenesSieve();
        System.out.println(binarySearch(N));
    }

    private static long binarySearch(int n) {
        long start = 1;
        long end = 1_000_000_000_000L;

        long result = 0;

        while(start <= end) {
            long mid = (start + end) / 2;
            long count = mobiusCounting(mid);

            if (count >= n){                            // find kth sqr free
                end = mid - 1;
                result = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }

    private static long mobiusCounting(long m) {
        long count = 0;
        int loop = (int) (Math.sqrt(m)) + 1;

        for(int i = 1; i < loop; i++) {                 // counting
            long div = i;
            div *= i;
            count += mobius[i] * (m / div);
        }

        return count;
    }

    private static void setting() {
        for(int i = 2; i < mobius.length; i++) {
            if(mobius[i] == i) mobius[i] = 1;
            else if(mobius[i] == -i) mobius[i] = -1;
            else if(mobius[i] < 0) mobius[i] = 1;
            else if(mobius[i] > 0) mobius[i] = -1;
        }
    }

    private static void eratosthenesSieve() {
        int loop = 1_001;
        Arrays.fill(mobius, 1);

        for (int i = 2; i < loop; i++) {                // make mobius func by eratosthenes technique
            if (mobius[i] != 1) continue;

            for (int j = i; j <= 1000000; j += i){
                mobius[j] *= -i;
            }

            for (int j = i * i; j <= 1000000; j += i * i) {
                mobius[j] = 0;
            }
        }

        setting();
    }
}
