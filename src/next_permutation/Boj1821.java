package next_permutation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1821번: 수들의 합
 *
 * @see https://www.acmicpc.net/problem/1821/
 *
 */
public class Boj1821 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int F = Integer.parseInt(st.nextToken());

        int[] permutation = new int[N];
        for(int i = 0; i < N; i++) {
            permutation[i] = i + 1;
        }

        while (sequenceSum(N, permutation) != F) nextPermutation(permutation);      // do before find F
        System.out.println(print(permutation));
    }

    private static String print(int[] perm) {
        StringBuilder sb = new StringBuilder();

        for(int p: perm) {
            sb.append(p).append(SPACE);
        }

        return sb.toString();
    }

    public static void nextPermutation(int[] p) {
        int idx = p.length - 1;
        while (idx > 0 && p[idx - 1] >= p[idx]) idx--;

        if(idx == 0) return;

        int post = p.length - 1;
        while (p[post] <= p[idx - 1]) post--;

        int prev = p[idx - 1];
        p[idx - 1] = p[post];
        p[post] = prev;

        post = p.length - 1;

        while (idx < post) {
            prev = p[idx];
            p[idx++] = p[post];
            p[post--] = prev;
        }
    }

    private static int sequenceSum (int n, int[] perm) {
        int[] sum = new int [n];

        for(int i = 0; i<n; i++){
            sum[i] = perm[i];
        }

        int loop = n;

        while (loop > 1) {                          // pascal triangle calculation
            for (int i = 0; i < loop - 1; i++) {
                sum[i] = sum[i] + sum[i + 1];
            }

            loop--;
        }

        return sum[0];
    }
}
