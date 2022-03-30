package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 *
 * @author exponential-e
 * 백준 1914번: 하노이 탑
 *
 * @see https://www.acmicpc.net/problem/1914
 *
 */
public class Boj1914 {
    private static StringBuilder sb = new StringBuilder();

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        System.out.print(hanoiTower());
    }

    private static String hanoiTower() {
        if(N <= 20) {
            sb.append((1 << N) - 1).append(NEW_LINE);
            dfs(N, 1, 2, 3);

            return sb.toString();
        }

        BigInteger answer = new BigInteger("2");
        return answer.pow(N).subtract(BigInteger.ONE).toString();
    }

    private static void dfs(int n, int current, int via, int next) {
        if(n == 0) return;

        dfs(n - 1, current, next, via);
        sb.append(current).append(SPACE).append(next).append(NEW_LINE);
        dfs(n - 1, via, current, next);
    }
}