package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 *
 * @author exponential-e
 * 백준 17218번: 비밀번호 만들기
 *
 * @see https://www.acmicpc.net/problem/17218/
 *
 */
public class Boj17218 {
    private static int[][] dp = null;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stand = br.readLine();
        String comp = br.readLine();

        System.out.println(lcs(stand.toCharArray(), comp.toCharArray()));
    }

    private static String lcs(char[] src, char[] snk) {
        int lengS = src.length;
        int lengC = snk.length;

        dp = new int[lengC + 1][lengS + 1];

        for (int i = 1; i < lengS + 1; i++) {
            if (snk[0] == src[i - 1]) dp[1][i] = dp[0][i - 1] + 1;
            else dp[1][i] = Math.max(dp[1][i - 1], dp[0][i]);
        }

        for (int i = 2; i < lengC + 1; i++) {
            for (int j = 1; j < lengS + 1; j++) {
                if (snk[i - 1] == src[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = lengC; 0 < i; i--) {
            for (int j = lengS; 0 < j; j--) {
                if (dp[i][j] != Math.max(dp[i - 1][j], dp[i][j - 1])) {
                    stack.push(src[j - 1]);
                    lengS = j - 1;

                    break;
                }

                if (dp[i][j] == dp[i][j - 1] && dp[i][j] != dp[i - 1][j]) continue;
                if (dp[i][j] != dp[i][j - 1] && dp[i][j] == dp[i - 1][j]) break;
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
