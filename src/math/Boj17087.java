package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17087번: 숨바꼭질 6
 *
 * @see https://www.acmicpc.net/problem/17087
 *
 */
public class Boj17087 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] p = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            p[i] = Math.abs(Integer.parseInt(st.nextToken()) - S);
        }

        System.out.println(hideAndSeek(p));
    }

    private static int hideAndSeek(int[] person) {
        int first = person[0];
        if(person.length == 1) return first;

        int gcd = first;
        for (int i = 1; i < person.length; i++) {
            gcd = GCD(gcd, person[i]);
        }

        return gcd;
    }

    private static int GCD(int a, int b) {
        if(b == 0) return a;
        return GCD(b, a % b);
    }
}
