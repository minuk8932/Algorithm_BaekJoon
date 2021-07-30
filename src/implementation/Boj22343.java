package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 22343번: 괄호의 값 비교
 *
 * @see https://www.acmicpc.net/problem/22343
 *
 */
public class Boj22343 {

    private static final String L = ">\n";
    private static final String R = "<\n";
    private static final String E = "=\n";

    private static final char OPEN = '(';
    private static final int SIZE = 3_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int[] left = calculation(br.readLine().toCharArray());
            int[] right = calculation(br.readLine().toCharArray());

            boolean flag = false;

            for(int i = left.length - 1; i >= 0; i--) {
                if(left[i] == right[i]) continue;

                if(left[i] > right[i]) sb.append(L);
                else sb.append(R);

                flag = true;
                break;
            }

            if(!flag) sb.append(E);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Calculation
     *
     * line 72: add value in closed position
     * lin 82 ~ 83: each index means bracket's depth
     *      , more deeper more bigger and same index just adding.
     *
     * @param brackets
     * @return
     */
    private static int[] calculation(char[] brackets) {
        int[] values = new int[SIZE];
        boolean opened = false;

        int valid = 0;

        for(char c: brackets) {
            if(c == OPEN) {
                valid++;
                opened = true;
            }
            else {
                if(opened) values[valid]++;

                valid--;
                opened = false;
            }
        }

        for(int i = 0; i < values.length; i++) {
            if(values[i] < 2) continue;

            values[i + 1] += values[i] >> 1;
            values[i] %= 2;
        }

        return values;
    }
}
