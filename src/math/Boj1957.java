package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1957번: 앉았다 일어나기
 *
 * @see https://www.acmicpc.net/problem/1957
 *
 */
public class Boj1957 {

    private static int[] student;

    private static final char UP = 'u';
    private static final char DOWN = 'd';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        student = new int[N];
        String input = br.readLine();
        for(int i = 0; i < N; i++) {
            student[i] = input.charAt(i) == 'd' ? 0: 1;
        }

        System.out.println(onOff(N, M));
    }

    /**
     *
     * on-off
     *
     * line 50: student[i] rotating (1 << m) then student[i] = student[i] ^ student[(i + (1 << m)) % N]
     *
     * @param n
     * @param m
     * @return
     */
    private static String onOff(int n, int m) {
        int[] status = new int[n];

        for(int time = 1; time <= m; time <<= 1) {
            if ((time & m) == 0) continue;

            for (int seats = 0; seats < n; seats++) {
                status[seats] = student[seats] ^ student[(time + seats) % n];
            }

            for (int i = 0; i < n; i++) {
                student[i] = status[i];
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int std: student) {
            sb.append(std == 0 ? DOWN: UP);
        }

        return sb.toString();
    }
}
