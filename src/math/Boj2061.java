package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2061번: 좋은 암호
 *
 * @see https://www.acmicpc.net/problem/2061/
 *
 */
public class Boj2061 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigInteger K = new BigInteger(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        System.out.println(judgement(K, L));
    }

    private static String judgement(BigInteger k, int l) {
        for(int i = 2; i < l; i++) {
            if(k.mod(new BigInteger(i + "")).equals(BigInteger.ZERO)) return "BAD " + i;
        }

        return "GOOD";
    }
}
