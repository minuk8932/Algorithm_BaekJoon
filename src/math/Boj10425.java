package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 10425번: 피보나치 인버스
 *
 * @see https://www.acmicpc.net/problem/10425/
 *
 */
public class Boj10425 {
    private static final BigInteger INF = BigInteger.TEN.pow(21000);
    private static final String ONE = "1";
    private static final String NEW_LINE = "\n";

    private static HashMap<BigInteger, Integer> fiboMap = new HashMap<>();
    private static BigInteger[] fibo = new BigInteger[100_500];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        fibonacci();
        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            String target = br.readLine();

            if(target.equals(ONE)) sb.append(2);
            else sb.append(fiboMap.get(new BigInteger(target)) + 1);

            sb.append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void fibonacci() {
        fibo[0] = BigInteger.ONE;
        fibo[1] = BigInteger.ONE;

        fiboMap.put(BigInteger.ONE, 0);
        fiboMap.put(BigInteger.ONE, 1);

        for(int i = 2; i < fibo.length; i++){
            fibo[i] = fibo[i - 1].add(fibo[i - 2]);
            fiboMap.put(fibo[i], i);                    // make map with BigInteger
            if(fibo[i].compareTo(INF) == 1) break;
        }
    }
}
