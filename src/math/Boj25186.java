package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25186번: INFP 두람
 *
 * @see https://www.acmicpc.net/problem/25186
 *
 */
public class Boj25186 {

    private static final String H = "Happy";
    private static final String UN = "Unhappy";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(takePhoto(N, br.readLine()));
    }

    private static String takePhoto(int n, String input) {
        StringTokenizer st = new StringTokenizer(input);
        long sum = 0;
        long max = 0;

        for(int i = 0; i < n; i++) {
            long clothes = Long.parseLong(st.nextToken());
            sum += clothes;
            max = Math.max(clothes, max);
        }

        if(n == 1 && sum == 1) return H;

        sum -= max;
        return max - sum >= 1 ? UN: H;
    }
}
