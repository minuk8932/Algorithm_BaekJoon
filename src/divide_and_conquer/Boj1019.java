package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 1019번: 책 페이지
 *
 * @see https://www.acmicpc.net/problem/1019
 *
 */
public class Boj1019 {
    private static int[] number = new int[10];

    private static final int TEN = 10;
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        recursion(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int n: number) {
            sb.append(n).append(SPACE);
        }

        System.out.println(sb.toString());
    }

    private static void recursion(int from, int to, int cipher) {
        from = compress(from, to, cipher, 1);                        // find on range
        if (from > to) return;
        to = compress(to, from, cipher, -1);

        int count = to / TEN - from / TEN + 1;
        for (int i = 0; i < number.length; i++) {
            number[i] += count * cipher;
        }

        recursion(from / TEN, to / TEN, cipher * TEN);      // next cipher
    }

    private static void checker(int target, int cipher) {
        while (target > 0) {
            number[target % 10] += cipher;
            target /= 10;
        }
    }

    private static int compress(int src, int snk, int cipher, int adder) {
        int limit = (TEN + adder) % 11;

        while (src % TEN != limit && src * adder <= snk * adder) {
            checker(src, cipher);
            src += adder;
        }

        return src;
    }
}
