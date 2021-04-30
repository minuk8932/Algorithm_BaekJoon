package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 *
 * @author exponential-e
 * 백준 2226번: 이진수
 *
 * @see https://www.acmicpc.net/problem/2226
 *
 */
public class Boj2226 {

    private static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        BigInteger result = BigInteger.ZERO;
        for (int i = 2; i <= N; i++) {
            result = result.multiply(TWO);

            if ((i % 2) == 0) result = result.add(BigInteger.ONE);
            else result = result.subtract(BigInteger.ONE);
        }

        System.out.println(result);
    }
}
