package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14878번: 부분 수열 XOR합
 *
 * @see https://www.acmicpc.net/problem/14878
 *
 */
public class Boj14878 {

    private static final int SIZE = 1 << 16;
    private static long[] count= new long[SIZE];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        count[0]++;
        int xor = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            xor ^= Integer.parseInt(st.nextToken());
            count[xor]++;
        }

        xorConvolution();
        int appear = 0;
        long max = 0;

        for(int i = 1; i < SIZE; i++) {
            if (count[i] > max) max = count[appear = i];
        }

        System.out.println(appear + " " + (max >> 1L));
    }

    /**
     *
     * XOR convolution
     *
     */
    private static void xorConvolution() {
        multiplySetter();
        for(int i = 0; i < SIZE; i++) {
            count[i] *= count[i];
        }

        multiplySetter();
        for(int i = 0; i < SIZE; i++) {
            count[i] /= SIZE;
        }
    }

    private static void multiplySetter() {
        for (int two = 2; two <= SIZE; two <<= 1) {
            int len = two >> 1;

            for (int i = 0; i < SIZE; i += two) {
                for(int j = 0; j < len; j++) {
                    long x = count[i + j];
                    long y = count[i + j + len];

                    count[i + j] = x + y;
                    count[i + j + len] = x - y;
                }
            }
        }
    }
}
