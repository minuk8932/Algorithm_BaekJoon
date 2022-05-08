package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5546번: 파스타
 *
 * @see https://www.acmicpc.net/problem/5546
 *
 */
public class Boj5546 {

    private static final int MOD = 10_000;
    private static final Map<Integer, Integer> FIX_DAY = new HashMap<>();

    private static int[][][] dp;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            FIX_DAY.put(A, B);
        }

        dp = new int[N + 1][4][4];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(recursion(0, 0, 0));
    }

    private static int recursion(int current, int type, int continuous) {
        if(continuous >= 3) return 0;
        if(current == N) return 1;

        if(dp[current][type][continuous] != -1) return dp[current][type][continuous];
        int answer = 0;

        int next = current + 1;
        if(FIX_DAY.containsKey(next)) {
            answer = recursion(next, FIX_DAY.get(next),
                type == FIX_DAY.get(next) ? continuous + 1: 1) % MOD;

            return dp[current][type][continuous] = answer;
        }

        for(int nextType = 1; nextType < 4; nextType++) {
            if (type != nextType) {
                answer = ((answer % MOD) + (recursion(next, nextType, 1) % MOD)) % MOD;
            }
            else {
                answer = ((answer % MOD)
                    + (recursion(next, nextType, continuous + 1) % MOD)) % MOD;
            }
        }

        return dp[current][type][continuous] = answer;
    }
}
