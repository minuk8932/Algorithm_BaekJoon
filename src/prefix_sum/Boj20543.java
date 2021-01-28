package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20543번: 폭탄 던지는 태영이
 *
 * @see https://www.acmicpc.net/problem/20543
 *
 */
public class Boj20543 {

    private static long[][] result;
    private static long[][] prefix;
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        result = new long[N + M][N + M];
        prefix = new long[N + M][N + M];

        long[][] map = new long[N + M][N + M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Long.parseLong(st.nextToken());
            }
        }

        System.out.println(boom(N, M, map, M / 2));
    }

    /**
     *
     * Find arr[i][j] by negative value & prefix sum
     *
     * line 56 ~ 57: calculate arr[i][j]
     * arr
     *      [i][j]      ....    [i][j + m]
     *      ...         ....    ...
     *      [i + m][j]  ....    [i + m][j + m]
     *
     *      And, map[i + inc][j + inc + 1] - map[i + inc][j + inc] means
     *      A                   B
     *      [i][j]              [i][j + m]
     *      ...         -       ...
     *      [i + m][j]          [i + m][j + m]
     *
     *      that is, duplicated area is removed & pair of side data subtraction
     *      so, if get A, B by O(1) we can find result. => using prefix sum
     *
     * @param n
     * @param m
     * @param map
     * @param inc
     * @return
     */
    private static String boom(int n, int m, long[][] map, int inc) {
        for(int i = n - inc - 1; i >= 0; i--) {
            for(int j = n - inc - 1; j >= 0; j--) {
                result[i][j] = map[i + inc][j + inc + 1] - map[i + inc][j + inc]
                        + (prefix[i][j + m] - prefix[i + m][j + m]) - (prefix[i + 1][j] - prefix[i + m][j]);

                prefix[i][j] = result[i][j] + prefix[i + 1][j];
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(result[i][j]).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}
