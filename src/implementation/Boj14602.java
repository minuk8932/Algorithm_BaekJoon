package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14602번: 소금과 후추 (small)
 *
 * @see https://www.acmicpc.net/problem/14602/
 *
 */
public class Boj14602 {
    private static ArrayList<Integer> candidate;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        st.nextToken();
        int W = Integer.parseInt(st.nextToken());

        int[][] spagetti = new int[M + 1][N + 1];
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                spagetti[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(removeSaltPepper(M, N, W, spagetti));
    }

    private static String removeSaltPepper(int n, int m, int w, int[][] s) {
        candidate = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int[] len = {n - w + 1, m - w + 1};
        int index = w * w / 2;

        for(int i = 1; i <= len[0]; i++) {
            for(int j = 1; j <= len[1]; j++) {
                candidate = new ArrayList<>();

                for(int x = 0; x < w; x++) {                    // make sub square
                    for(int y = 0; y < w; y++) {
                        candidate.add(s[i + x][j + y]);
                    }
                }

                Collections.sort(candidate);
                sb.append(candidate.get(index)).append(SPACE);  // find median
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}
