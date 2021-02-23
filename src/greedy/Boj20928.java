package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20928번: 걷는 건 귀찮아
 *
 * @see https://www.acmicpc.net/problem/20928
 *
 */
public class Boj20928 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] p = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] x = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(rickshaw(N, M, p, x));
    }

    /**
     *
     * Rickshaw process
     *
     * line 59 ~ 70: find next destination & save minimum transfer at the stop
     *
     * @param n
     * @param m
     * @param p
     * @param x
     * @return
     */
    private static int rickshaw(int n, int m, int[] p, int[] x) {
        int[] transfer = new int[n + 2];
        Arrays.fill(transfer, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        transfer[0] = 0;

        int next = 1;
        while (!q.isEmpty()) {
            int current = q.poll();

            while(next < n) {
                if (p[next] > p[current] + x[current]) break;
                if (transfer[next] <= transfer[current] + 1) continue;
                transfer[next] = transfer[current] + 1;

                q.offer(next);
                next++;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            if (p[i] + x[i] < m)  continue;
            result = Math.min(result, transfer[i]);
        }

        return result == Integer.MAX_VALUE ? -1: result;
    }
}
