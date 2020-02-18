package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 2553번: 마지막 팩토리얼 수
 *
 * @see https://www.acmicpc.net/problem/2553/
 *
 */
public class Boj2553 {
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(factorial(n));
    }

    private static int factorial(int n){
        long f = 1;

        while(n > 0){
            f *= n;
            f %= MOD;                       // remained

            while(f % 10 == 0){             // remove zeroes
                f /= 10;
            }

            n--;
        }

        int result = 0;
        while(result == 0 && f != 0){
            result = (int) (f % 10);
            f /= 10;
        }

        return result;
    }
}
