package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14671번: 영정이의 청소
 *
 * @see https://www.acmicpc.net/problem/14671/
 *
 */
public class Boj14671 {
    private static boolean[][] tile = new boolean[2][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            tile[(x + y) % 2][y % 2] = true;            // is covered
        }

        System.out.println(tile[0][0] & tile[0][1] & tile[1][0] & tile[1][1] ? "YES" : "NO");
    }
}
