package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17831번: 대기업 승범이네
 *
 * @see https://www.acmicpc.net/problem/17831/
 *
 */
public class Boj17831 {
    private static int[][] dp;
    private static int[] synergy;
    private static ArrayList<Integer>[] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[2][N + 1];
        path = new ArrayList[N + 1];
        synergy = new int[N + 1];
        for(int i = 0; i < N + 1; i++) {
            path[i] = new ArrayList<>();
            dp[0][i] = dp[1][i] = -1;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 2; i <= N; i++) {
            path[Integer.parseInt(st.nextToken())].add(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            synergy[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(recursion(0, 1));
    }

    private static int recursion(int hasMentor, int current) {
        if (dp[hasMentor][current] != -1) return dp[hasMentor][current];

        int result = 0;
        for (int next : path[current]) {
            result += recursion(0, next);           // has already mentor
        }

        if(hasMentor == 0) {
            int tmp = 0;

            for (int next : path[current]) {                    // no mentor
                tmp = Math.max(tmp,
                        result - recursion(0, next) + recursion(1, next) + synergy[current] * synergy[next]);
            }

            result = Math.max(result, tmp);
        }

        return dp[hasMentor][current] = result;
    }
}
