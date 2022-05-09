package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1509번: 팰린드롬 분할
 *
 * @see https://www.acmicpc.net/problem/1509
 *
 */
public class Boj1509 {

    private static final String EMPTY = " ";
    private static long[][] dp;
    private static char[] words;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        words = (EMPTY + br.readLine()).toCharArray();

        dp = new long[words.length][words.length];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1L);
        }

        System.out.println(getPartition());
    }

    private static long getPartition() {
        long[] partitions = new long[words.length];

        for(int to = 1; to < partitions.length; to++){
            partitions[to] = partitions[to - 1] + 1;

            for(int from = 1; from < to; from++){
                if(recursion(from, to) == 0) continue;
                partitions[to] = Math.min(partitions[to], partitions[from - 1] + 1);
            }
        }

        return partitions[words.length - 1];
    }

    private static long recursion(int start, int end) {
        if(start >= end) return 1;

        if(dp[start][end] != -1) return dp[start][end];
        long answer = 0;

        if(words[start] == words[end]) {
            answer = recursion(start + 1, end - 1);
        }

        return dp[start][end] = answer;
    }
}
