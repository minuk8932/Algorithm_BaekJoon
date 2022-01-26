package sliding_window;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1593번: 문자 해독
 *
 * @see https://www.acmicpc.net/problem/1593
 *
 */
public class Boj1593 {

    private static int[][] src;
    private static int[][] snk;

    private static char[] W;
    private static char[] S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int g = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        W = br.readLine().toCharArray();
        S = br.readLine().toCharArray();

        src = stringToIntArray(W);
        snk = new int[2][26];

        System.out.println(slidingWindow(s, g));
    }

    /**
     *
     * Sliding window
     *
     * line 52 ~ 54: move window, add alpha counts
     * line 59 ~ 60: move window, remove alpha counts
     *
     * @param s
     * @param g
     * @return
     */
    private static int slidingWindow(int s, int g) {
        int count = 0;
        int len = 0;

        for(int i = 0; i < s; i++) {
            if(S[i] >= 'a') snk[1][S[i] - 'a']++;
            else snk[0][S[i] - 'A']++;
            len++;

            if(len != g) continue;
            if(isEqual()) count++;

            if(S[i - g + 1] >= 'a') snk[1][S[i - g + 1] - 'a']--;
            else snk[0][S[i - g + 1] - 'A']--;
            len--;
        }

        return count;
    }

    /**
     *
     * Window check
     *
     * @return
     */
    private static boolean isEqual() {
        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 2; j++) {
                if (src[j][i] != snk[j][i]) return false;
            }
        }

        return true;
    }

    private static int[][] stringToIntArray(char[] inputs) {
        int[][] alpha = new int[2][26];

        for(char input: inputs) {
            if(input >= 'a') alpha[1][input - 'a']++;
            else alpha[0][input - 'A']++;
        }

        return alpha;
    }
}
