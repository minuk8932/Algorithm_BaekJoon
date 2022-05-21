package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25179번: 배스킨라빈스~N~귀엽고~깜찍하게~
 *
 * @see https://www.acmicpc.net/problem/25179
 *
 */
public class Boj25179 {

    private static final String W = "Can win";
    private static final String L = "Can't win";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        System.out.println(gaming(N, M));
    }

    private static String gaming(long n, long m) {
        if(n == 1) return L;
        if(n <= m) return W;

        return (n - 1) % (m + 1) == 0 ? L: W;
    }
}
