package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13705번: Ax+Bsin(x)=C
 *
 * @see https://www.acmicpc.net/problem/13705/
 *
 */
public class Boj13705 {
    private static final BigDecimal INF = new BigDecimal("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
    private static final long INFL = 1_000_000_000_000_000L;
    private static final BigDecimal ONE = new BigDecimal("1");
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final int CIPHER = 30;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(String.format("%.6f", A == 1 && B == 1 ?
                triAnother(A, B, C): triangularFunction(new BigDecimal(A), new BigDecimal(B), new BigDecimal(C))));
    }

    private static BigDecimal triangularFunction(BigDecimal a, BigDecimal b, BigDecimal c){
        BigDecimal left = (c.subtract(b)).divide(a, CIPHER, RoundingMode.HALF_UP);
        BigDecimal right = (c.add(b)).divide(a, CIPHER, RoundingMode.HALF_UP);
        BigDecimal result = new BigDecimal("0");

        for(; isRange(left, right); left = left.add(ONE)){              // find value with binary search
            BigInteger start = new BigInteger("0");
            BigInteger end = INF.toBigInteger();

            while(isRange(start, end)){
                BigDecimal val = (new BigDecimal(start).add(new BigDecimal(end))).divide(TWO, CIPHER, RoundingMode.HALF_UP);
                BigInteger mid = (start.add(end)).divide(TWO.toBigInteger());

                BigDecimal x = (val.divide(INF, CIPHER, RoundingMode.HALF_UP)).add(left);
                BigDecimal temp = makeValue(a, b, x);

                if(isRange(temp, c)) {
                    start = mid.add(ONE.toBigInteger());
                    result = x;
                }
                else {
                    end = mid.subtract(ONE.toBigInteger());
                }
            }
        }

        return result;
    }

    private static BigDecimal makeValue(BigDecimal a, BigDecimal b, BigDecimal x){
        return a.multiply(x).add(b.multiply(sin(x)));
    }

    private static boolean isRange(BigDecimal a, BigDecimal b){ return a.compareTo(b) == 0 || a.compareTo(b) < 0; }

    private static boolean isRange(BigInteger a, BigInteger b){ return a.compareTo(b) == 0 || a.compareTo(b) < 0; }

    private static BigDecimal sin(BigDecimal pi){                    // get sin(x) with Taylor series
        BigDecimal sum = pi;
        BigDecimal result = pi;
        BigDecimal fact = new BigDecimal("1");
        BigDecimal pi2 = pi.multiply(pi).multiply(fact.negate());

        for(int i = 1; i < 60; i++) {
            BigDecimal idx = new BigDecimal(i);
            fact = fact.multiply(TWO.multiply(idx).multiply((TWO.multiply(idx).add(ONE))));
            result = result.multiply(pi2);
            sum = sum.add(result.divide(fact, CIPHER, RoundingMode.HALF_UP));
        }

        return sum;
    }

    private static double triAnother(double a, double b, double c){
        double left = (c - b) / a;
        double right = (c + b) / a;
        double result = 0;

        for(; left <= right; left++){
            long start = 0, end = INFL;

            while(start <= end){
                long mid = (start + end) / 2;
                double x = mid / (double) INFL + left;
                double temp = makeValue(a, b, x);

                if(temp <= c){
                    start = mid + 1;
                    result = x;
                }
                else {
                    end = mid - 1;
                }
            }
        }

        return result;
    }

    private static double makeValue(double a, double b, double x){
        return a * x + b * Math.sin(x);
    }
}
