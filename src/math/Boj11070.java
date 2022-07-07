package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11070번: 피타고라스 기댓값
 *
 * @see https://www.acmicpc.net/problem/11070
 *
 */
public class Boj11070 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long[] win = new long[n];
            long[] lose = new long[n];

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int t1 = Integer.parseInt(st.nextToken()) - 1;
                int t2 = Integer.parseInt(st.nextToken()) - 1;
                long w1 = Long.parseLong(st.nextToken());
                long w2 = Long.parseLong(st.nextToken());

                win[t1] += w1;
                lose[t1] += w2;
                win[t2] += w2;
                lose[t2] += w1;
            }

            ArrayList<Long> expected = getExpect(win, lose);

            sb.append(Collections.max(expected)).append(NEW_LINE)
                .append(Collections.min(expected)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static ArrayList<Long> getExpect(long[] win, long[] lose) {
        ArrayList<Long> expect = new ArrayList<>();

        for(int i = 0; i < win.length; i++) {
            long winPow = win[i] * win[i];
            long losePow = lose[i] * lose[i];

            if(winPow + losePow == 0) expect.add(0L);
            else expect.add(winPow * 1_000 / (winPow + losePow));
        }

        return expect;
    }
}
