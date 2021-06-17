package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21941번: 문자열 제거
 *
 * @see https://www.acmicpc.net/problem/21941
 *
 */
public class Boj21941 {

    private static int[] dp;
    private static int N;

    private static char[] S;
    private static List<Score> scores = new ArrayList<>();

    private static class Score {
        char[] key;
        int value;

        public Score(char[] key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = input.length();
        S = makeCharArray(input);

        int M = Integer.parseInt(br.readLine());
        dp = new int[N];

        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char[] a = makeCharArray(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if(a.length > x) continue;
            scores.add(new Score(a, x));
        }

        Arrays.fill(dp, -1);
        System.out.println(recursion(0));
    }

    /**
     *
     * Recursion
     *
     * line 71: check string contains
     * line 73: DP
     *
     * @param current
     * @return
     */
    private static int recursion(int current) {
        if(current >= N) return 0;
        if(dp[current] != -1) return dp[current];

        int result = recursion(current + 1) + 1;
        for(Score s: scores) {
            int jump = current + s.key.length - 1;
            if(jump >= N) continue;
            if(!isContains(s.key, current)) continue;

            result = Math.max(recursion(current + s.key.length) + s.value, result);
        }

        return dp[current] = result;
    }

    private static boolean isContains(char[] target, int adder) {
        for(int i = 0; i < target.length; i++) {
            if(target[i] != S[i + adder]) return false;
        }

        return true;
    }

    private static char[] makeCharArray(String str) {
        char[] arr = new char[str.length()];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = str.charAt(i);
        }

        return arr;
    }
}
