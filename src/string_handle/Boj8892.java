package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 8892번: 팰린드롬
 *
 * @see https://www.acmicpc.net/problem/8892/
 *
 */
public class Boj8892 {
    private static final String NEW_LINE = "\n";
    private static final String ZERO = "0";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int k = Integer.parseInt(br.readLine());

            char[][] word = new char[k][];
            for(int i = 0; i < k; i++) {
                word[i] = br.readLine().toCharArray();
            }

            sb.append(palindrome(k, word)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static String palindrome(int k, char[][] w) {
        for(int i = 0; i < k; i++) {
            for(int j = i + 1; j < k; j++) {
                String isPalin = rebuild(i, j, w);
                if(!isPalin.equals(ZERO)) return isPalin;

                isPalin = rebuild(j, i, w);
                if(!isPalin.equals(ZERO)) return isPalin;
            }
        }

        return ZERO;
    }

    private static String rebuild (int x, int y, char[][] w) {
        StringBuilder rebuild = new StringBuilder();

        for(int i = 0; i < w[x].length; i++) {
            rebuild.append(w[x][i]);
        }

        for(int i = 0; i < w[y].length; i++) {
            rebuild.append(w[y][i]);
        }

        String build = rebuild.toString();
        int len = build.length();

        for(int i = 0; i < len / 2; i++) {
            if(build.charAt(i) != build.charAt(len - 1 - i)) return ZERO;
        }

        return build;
    }
}
