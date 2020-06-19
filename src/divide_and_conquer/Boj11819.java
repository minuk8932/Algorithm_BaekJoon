package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11819번: The Shortest does not Mean the Simplest
 *
 * @see https://www.acmicpc.net/problem/11819
 *
 */
public class Boj11819 {
    private static BigInteger org;
    private static final BigInteger TWO = new BigInteger("2");
    private static HashMap<BigInteger, BigInteger> visit = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigInteger A = new BigInteger(st.nextToken());
        BigInteger B = new BigInteger(st.nextToken());
        BigInteger C = new BigInteger(st.nextToken());

        org = A;
        System.out.println(recursion(A.mod(C), B, C).mod(C));
    }

    private static BigInteger recursion(BigInteger current, BigInteger exponential, BigInteger mod) {
        if(exponential.equals(BigInteger.ONE)) return org;

        if(visit.containsKey(exponential)) return visit.get(exponential);

        BigInteger half = exponential.divide(TWO);
        BigInteger next = recursion(current, half, mod).mod(mod);
        visit.put(half, next);

        if(exponential.mod(TWO).equals(BigInteger.ZERO)){               // divide & conquer
            return makePow(next, next, mod);
        }
        else {
            BigInteger next1 = recursion(makePow(current, org, mod), half.add(BigInteger.ONE), mod).mod(mod);
            visit.put(half.add(BigInteger.ONE), next1);

            return makePow(next, next1, mod);
        }
    }

    private static BigInteger makePow(BigInteger value1, BigInteger value2, BigInteger c) {
        return ((value1.mod(c)).multiply(value2.mod(c))).mod(c);
    }
}
