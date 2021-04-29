package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 14607번: 피자 (Large)
 *
 * @see https://www.acmicpc.net/problem/14607
 *
 */
public class Boj14607 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.println((N * (N - 1)) >> 1L);
    }
}

