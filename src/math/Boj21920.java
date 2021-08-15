package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 21920번: 서로소 평균
 *
 * @see https://www.acmicpc.net/problem/21920
 *
 */
public class Boj21920 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());

        int[] A = ARRAY.apply(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = PARSE.apply(st.nextToken());
        }

        int X = PARSE.apply(br.readLine());
        System.out.println(getAvg(A, X));
    }

    private static double getAvg(int[] seq, int x) {
        long sum = 0;
        double count = 0;

        for(int s: seq) {
            int g = gcd(s, x);
            if(g != 1) continue;

            sum += s;
            count++;
        }

        return sum / count;
    }

    private static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, int[]> ARRAY = int[]::new;
}
