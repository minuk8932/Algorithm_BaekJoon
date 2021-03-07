package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20952번: 게임 개발자 승희
 *
 * @see https://www.acmicpc.net/problem/20952
 *
 */
public class Boj20952 {

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final int MOD = 1_000_000_007;

    private static boolean[] removed = new boolean[7];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            removed[A[i] % 7] = true;
        }

        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(let369(A, B, setSize()));
    }

    private static int setSize() {
        int count = 0;

        for (int i = 0; i < removed.length; i++) {
            if (!removed[i]) continue;
            count++;
        }

        return count;
    }

    /**
     *
     * Let's 369
     *
     * line 73: modular check
     * line 76 ~ 79: except, if all removed
     *
     * @param a
     * @param b
     * @param count
     * @return
     */
    private static String let369(int[] a, int[] b, int count){
        long sum = 0;

        for (int i = 0; i < b.length; i++) {
            sum += b[i];
            int idx = (int) (7 - (sum % 7)) % 7;
            if(!removed[idx]) continue;

            if (count == 1) {
                sum -= b[i];
                continue;
            }

            removed[idx] = false;
            count--;
        }

        List<Long> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (!removed[a[i] % 7]) continue;
            list.add(modulation(a[i], sum));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append(NEW_LINE);

        for(long v: list) {
            sb.append(v).append(SPACE);
        }

        return sb.toString();
    }

    private static long modulation(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }
}

