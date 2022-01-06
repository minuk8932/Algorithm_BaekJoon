package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24023번: 아기 홍윤
 *
 * @see https://www.acmicpc.net/problem/24023
 *
 */
public class Boj24023 {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] value = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(sweeping(N, K, value));
    }

    /**
     *
     * Sweeping
     *
     * line 45: check is sub set ?
     *
     * @param n
     * @param src
     * @param val
     * @return
     */
    private static String sweeping(int n, int src, int[] val) {
        int start = -1;
        int snk = 0;

        for(int i = 0; i < n; i++) {
            if(val[i] > src || (src & val[i]) != val[i]) {
                start = -1;
                snk = 0;
                continue;
            }

            if(start == -1) start = (i + 1);
            snk |= val[i];

            if(snk != src) continue;
            return start + " " + (i + 1);
        }

        return snk == src ? start + " " + (n - 1): "-1";
    }
}
