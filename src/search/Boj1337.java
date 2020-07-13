package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1337번: 올바른 배열
 *
 * @see https://www.acmicpc.net/problem/1337/
 *
 */
public class Boj1337 {
    public static void main(String[] args ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] seq = new int[N];
        for(int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(correct(seq));
    }

    private static int correct(int[] seq) {
        Arrays.sort(seq);
        int count = 0;

        for(int current = 0; current < seq.length; current++){
            int inter = 1;

            for(int i = 1; i < 5; i++) {
                int next = current + i;                    // i ~ i + 5
                if (next >= seq.length) break;

                if (seq[next] < seq[current] + 5) inter++;  // count
            }

            count = Math.max(count, inter);
        }

        return 5 - count;
    }
}
