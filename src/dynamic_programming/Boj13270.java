package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 13270번: 피보나치 치킨
 *
 * @see https://www.acmicpc.net/problem/13270
 *
 */
public class Boj13270 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println((N + 1) / 2 + " " + (N * 2 / 3));
    }
}
