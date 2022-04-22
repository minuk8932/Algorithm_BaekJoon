package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1354번: 무한 수열 2
 *
 * @see https://www.acmicpc.net/problem/1354
 *
 */
public class Boj1354 {

    private static HashMap<Long, Long> dp = new HashMap<>();
    private static long P, Q, X, Y;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        dp.put(0L, 1L);
        System.out.println(recursion(N));
    }

    private static long recursion(long n) {
        if(n <= 0) return 1;
        if(dp.containsKey(n)) return dp.get(n);

        dp.put(n, recursion(n / P - X) + recursion(n / Q - Y));
        return dp.get(n);
    }
}
