package sliding_window;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13422번: 도둑
 *
 * @see https://www.acmicpc.net/problem/13422
 *
 */
public class Boj13422 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] homes = new int[N << 1];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                homes[i] = Integer.parseInt(st.nextToken());
                homes[i + N] = homes[i];
            }

            sb.append(slidingWindow(homes, N, M, K)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int slidingWindow(int[] homes, int n, int window, int limit) {
        int count = 0;

        int sum = 0;
        for(int i = 0; i < window; i++) {
            sum += homes[i];
        }

        if(limit > sum) count++;
        int prev = 0;

        for(int i = window; i < n + window - 1; i++) {
            sum -= homes[prev++];
            sum += homes[i];

            if(limit <= sum) continue;
            count++;
        }

        return n == window ? count / n: count;
    }
}
