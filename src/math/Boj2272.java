package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2272번: 램프
 *
 * @see https://www.acmicpc.net/problem/2272
 *
 */
public class Boj2272 {

    private static int[] lamp;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        lamp = new int[N];
        for(int i = 0; i < N; i++) {
            lamp[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(onOff(N, M));
    }

    /**
     *
     * on-off
     *
     * line 50: lamp[i] rotating (1 << m) then lamp[i] = lamp[i] ^ lamp[(i + (1 << m)) % N]
     *
     * @param n
     * @param m
     * @return
     */
    private static String onOff(int n, int m) {
        int[] status = new int[n];

        for(int time = 1; time <= m; time <<= 1) {
            if ((time & m) == 0) continue;

            for (int light = 0; light < n; light++) {
                status[light] = lamp[light] ^ lamp[(time + light) % n];
            }

            for (int i = 0; i < n; i++) {
                lamp[i] = status[i];
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int l: lamp) {
            sb.append(l).append(NEW_LINE);
        }

        return sb.toString();
    }
}
