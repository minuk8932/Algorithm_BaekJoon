package dynamic_programming;

import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23815번: 똥게임
 *
 * @see https://www.acmicpc.net/problem/23815
 *
 */
public class Boj23815 {

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTI = '*';
    private static final String LOSE = "ddong game";

    private static int[][][] dp;
    private static Pair<String>[] pairs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pairs = new Pair[N + 1];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pairs[i] = new Pair.Builder(st.nextToken(), st.nextToken()).build();
        }

        init(N);
        System.out.println(recursion(0, 2, N) <= 0 ? LOSE: dp[0][2][N]);
    }

    private static int recursion(int skip, int select, int current) {
        if(current == 0) return 1;
        if(dp[skip][select][current] != -1) return dp[skip][select][current];

        int answer = parser(recursion(skip, 0, current - 1)
            , pairs[current].getFirst());

        answer = Math.max(answer
            , parser(recursion(skip, 1, current - 1), pairs[current].getSecond()));

        if(skip != 1) {
            answer = Math.max(answer, recursion(1, 2, current - 1));
        }

        return dp[skip][select][current] = answer;
    }

    /**
     *
     * Parser
     *
     * line 68: when value is smaller than 1, return 0 immediately
     *
     * @param cache
     * @param formula
     * @return
     */
    private static int parser(int cache, String formula) {
        if(cache <= 0) return 0;

        char prev = formula.charAt(0);
        int value = formula.charAt(1) - '0';

        if(prev == PLUS) return cache + value;
        else if(prev == MINUS) return cache - value;
        else if(prev == MULTI) return cache * value;
        else return cache / value;
    }

    private static void init(int n) {
        dp = new int[2][3][n + 1];

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }
}
