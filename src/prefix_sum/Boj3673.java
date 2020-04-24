package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3673번: 나눌 수 있는 부분 수열
 *
 * @see https://www.acmicpc.net/problem/3673/
 *
 */
public class Boj3673 {
    private static int count;
    private static int[] sums;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] sum = new int[n];
            sums = new int[d];
            count = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int A = Integer.parseInt(st.nextToken()) % d;

                if (i == 0) sum[i] = A;
                else sum[i] = (sum[i - 1] % d + A) % d;        // make modular of sum

                if (sum[i] == 0) count++;
                sums[sum[i]]++;                                    // same values set
            }

            section(d);
            sb.append(count).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void section(int n) {
        for(int i = 0; i < n; i++) {
            count += (long) sums[i] * (sums[i] - 1) / 2;			// prefix sum pairing
        }
    }
}
