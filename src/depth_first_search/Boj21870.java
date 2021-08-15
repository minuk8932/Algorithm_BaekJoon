package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 21870번: 시철이가 사랑한 GCD
 *
 * @see https://www.acmicpc.net/problem/21870
 *
 */
public class Boj21870 {

    private static int[] integer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        integer = ARRAY.apply(N);
        for(int i = 0; i < N; i++) {
            integer[i] = PARSE.apply(st.nextToken());
        }

        System.out.println(recursion(0, N - 1));
    }

    private static long recursion(int start, int end) {
        if (start == end) return integer[start];

        int mid = (end - start + 1) >> 1;
        boolean flag = (end - start + 1) % 2 == 0;

        long ans = Math.max(recursion(start + mid, end) + gcds(start, start + mid - 1)
                ,  recursion(start, end - mid - (flag ? 0: 1)) + gcds(end - mid + (flag ? 1: 0), end));

        return ans;
    }

    private static long gcds(int start, int end) {
        long gcd = integer[start];

        for (int i = start; i <= end; i++) {
            gcd = gcd(gcd, integer[i]);
        }

        return gcd;
    }

    private static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, int[]> ARRAY = int[]::new;
}
