package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3980번: 선발 명단
 *
 * @see https://www.acmicpc.net/problem/3980/
 *
 */
public class Boj3980 {
    private static final String NEW_LINE = "\n";
    private static final int OVER = 2047;
    private static int result;

    private static boolean[] pvisit;
    private static int[][] eleven;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (C-- > 0) {
            eleven = new int[11][11];
            pvisit = new boolean[11];
            result = 0;

            for (int i = 0; i < eleven.length; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < eleven.length; j++) {
                    eleven[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < 11; i++) {
                if (eleven[0][i] == 0) continue;
                backTracking(0, i, 0, eleven[0][i]);
            }

            sb.append(result).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void backTracking(int visit, int current, int person, int val) {
        pvisit[person] = true;
        visit |= (1 << current);

        if (person == 10) {
            if(visit == OVER) result = Math.max(result, val);
            return;
        }

        for (int pos = 0; pos < 11; pos++) {
            if (eleven[person + 1][pos] == 0) continue;                                             // value 0

            int next = 1 << pos;
            if ((visit & next) != 0) continue;

            backTracking(visit, pos, person + 1, val + eleven[person + 1][pos]);        // find proper position
            pvisit[person + 1] = false;
        }
    }
}