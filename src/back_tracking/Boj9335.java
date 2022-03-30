package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9335번: 소셜 광고
 *
 * @see https://www.acmicpc.net/problem/9335
 *
 */
public class Boj9335 {

    private static final String NEW_LINE = "\n";

    private static int[][] mutualFriends;
    private static int[] count;

    private static int min;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            min = n;
            N = n;

            count = new int[n + 1];
            count[0] = 1;

            mutualFriends = new int[n][];
            mutualFriends[0] = new int[1];
            for(int i = 0; i < n; i++) {
                inputProcess(i, br.readLine());
            }

            recursion(0, 0);
            sb.append(min).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Recursion
     *
     * combination nCr
     *
     * @param n
     * @param r
     */
    private static void recursion(int n, int r) {
        if(n == N || r >= min) return;

        boolean flag = true;
        recursion(n + 1, r);

        count[n + 1]++;
        for(int i = 0; i < mutualFriends[n].length; i++) {
            count[mutualFriends[n][i]]++;
        }

        for(int c: count) {
            if(c > 0) continue;
            flag = false;
            break;
        }

        if (flag) min = Math.min(min, r + 1);
        else recursion(n + 1, r + 1);

        count[n + 1]--;
        for(int i = 0; i < mutualFriends[n].length; i++) {
            count[mutualFriends[n][i]]--;
        }
    }

    private static void inputProcess(int target, String input) {
        StringTokenizer st = new StringTokenizer(input);
        int size = Integer.parseInt(st.nextToken());
        mutualFriends[target] = new int[size];

        for(int p = 0; p < size; p++) {
            int friend = Integer.parseInt(st.nextToken());
            mutualFriends[target][p] = friend;
        }
    }
}
