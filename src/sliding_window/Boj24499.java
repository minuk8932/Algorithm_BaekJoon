package sliding_window;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24499번: blobyum
 *
 * @see https://www.acimcpc.net/problem/24499
 *
 */
public class Boj24499 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] pie = new int[N + K - 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            pie[i] = Integer.parseInt(st.nextToken());
            if(i >= K - 1) continue;

            pie[i + N] = pie[i];
        }

        System.out.println(slidingWindow(K, pie));
    }

    private static int slidingWindow(int window, int[] pie) {
        int sum = 0;
        for(int i = 0; i < window; i++) {
            sum += pie[i];
        }

        int max = sum;
        int prev = 0;
        for(int i = window; i < pie.length; i++) {
            sum += pie[i];
            sum -= pie[prev++];

            max = Math.max(max, sum);
        }

        return max;
    }
}
