package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2086번: 피보나치 수의 합
 *
 * @see https://www.acmicpc.net/problem/2086/
 *
 */
public class Boj2086 {
    private static final BigInteger MOD = new BigInteger(1_000_000_000 + "");

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigInteger a = new BigInteger(st.nextToken());
        BigInteger b = new BigInteger(st.nextToken());

        BigInteger aSum = (fibonacci(a).add(fibonacci(a.subtract(BigInteger.ONE))));                    // prevent overflow
        BigInteger bSum = (fibonacci(b.add(BigInteger.ONE)).add(fibonacci(b)));

        System.out.println(a == b ? bSum.subtract(BigInteger.ONE): (bSum.subtract(aSum)).mod(MOD));     // make sum
    }

    private static BigInteger fibonacci(BigInteger n) {
        BigInteger[][] X = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
        BigInteger[][] E = {{BigInteger.ONE, BigInteger.ZERO}, {BigInteger.ZERO, BigInteger.ONE}};

        while (!n.equals(BigInteger.ZERO)) {
            if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) E = cartesian(E, X);
            X = cartesian(X, X);
            n = n.divide(BigInteger.valueOf(2));
        }

        return E[0][1].mod(MOD);
    }

    private static BigInteger[][] cartesian(BigInteger[][] A, BigInteger[][] B) {       // make fibonacci with matrix
        BigInteger[][] result = new BigInteger[2][2];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                result[i][j] = BigInteger.ZERO;
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] = (result[i][j].mod(MOD).add(A[i][k].mod(MOD).multiply(B[k][j].mod(MOD)))).mod(MOD);
                }
            }
        }

        return result;
    }
}
