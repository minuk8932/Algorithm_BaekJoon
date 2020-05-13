package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2891번: 카약과 강풍
 *
 * @see https://www.acmicpc.net/problem/2891/
 *
 */
public class Boj2891 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        boolean[] broke = new boolean[N];
        st = new StringTokenizer(br.readLine());
        while (S-- > 0) {
            broke[Integer.parseInt(st.nextToken()) - 1] = true;
        }

        boolean[] spare = new boolean[N];
        st = new StringTokenizer(br.readLine());
        while (R-- > 0) {
            int team = Integer.parseInt(st.nextToken()) - 1;

            if(!broke[team]) spare[team] = true;
            else broke[team] = false;
        }

        System.out.println(count(N, broke, spare));
    }

    private static int count(int n, boolean[] b, boolean[] s) {
        if(b[0]) {
            if(s[1]) {
                b[0] = false;
                s[1] = false;
            }
        }

        if(b[n - 1]) {
            if(s[n - 2]) {
                b[n - 1] = false;
                s[n - 2] = false;
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (!b[i]) continue;

            if (s[i + 1]) {
                b[i] = false;
                s[i + 1] = false;
            } else if (s[i - 1]) {
                b[i] = false;
                s[i - 1] = false;
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (b[i]) result++;
        }

        return result;
    }
}
