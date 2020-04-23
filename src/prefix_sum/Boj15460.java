package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15460번: My Cow Ate My Homework
 *
 * @see https://www.acmicpc.net/problem/15460/
 *
 */
public class Boj15460 {
    private static double[] min;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] work = new int[N];
        min = new double[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            work[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(search(N, work));
    }

    private static String search(int n, int[] w) {
        Arrays.fill(min, 10_001);

        double[] sum = new double[n];
        sum[n - 1] = min[n - 1] = w[n - 1];

        for(int i = n - 2; i > 0; i--) {
            sum[i] = sum[i + 1] + w[i];                     // score sum
            min[i] = Math.min(w[i], min[i + 1]);            // minimum score
        }

        double max = 0;
        for(int k = 1; k < n - 2; k++) {
            double m = (n - k - 1);
            if(m == 0) continue;

            double avg = (sum[k] - min[k]) / m;             // section calculatetion

            if(avg > max) max = avg;
        }

        StringBuilder sb = new StringBuilder();

        for(int k = 1; k < n - 1; k++) {
            double m = (n - k - 1);
            if(m == 0) continue;

            double avg = (sum[k] - min[k]) / m;
            if(avg == max) sb.append(k).append(NEW_LINE);
        }

        return sb.toString();
    }
}
