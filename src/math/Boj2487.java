package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2487번: 섞기 수열
 *
 * @see https://www.acmicpc.net/problem/2487
 *
 */
public class Boj2487 {
    private static int[] elem;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N + 1];
        elem = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(shake(N, seq));
    }

    private static long shake(int n, int[] seq) {
        long result = 1;

        for(int i = 1; i <= n; i++) {
            if(elem[i] != 0) continue;

            int depth = recursion(seq, i, 0);           // find cycle
            result = result * depth / gcd(result, depth);     // lcm
        }

        return result;
    }

    private static int recursion(int[] s, int current, int count){
        if(elem[current] != 0) return count;
        elem[current] = 1;

        return recursion(s, s[current], count + 1);
    }

    private static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
