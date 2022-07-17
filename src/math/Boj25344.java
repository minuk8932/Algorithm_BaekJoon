package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25344번: 행성 정렬
 *
 * @see https://www.acmicpc.net/problem/25344
 *
 */
public class Boj25344 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] align = new long[N - 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < align.length; i++) {
            align[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(findRotation(align));
    }

    private static long findRotation(long[] align) {
        if(align.length == 1) return align[0];

        long answer = align[0];
        for(int i = 1; i < align.length; i++) {
            long GCD = gcd(answer, align[i]);
            answer *= align[i];
            answer /= GCD;
        }

        return answer;
    }

    private static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
