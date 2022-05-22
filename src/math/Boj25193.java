package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 25193번: 곰곰이의 식단 관리
 *
 * @see https://www.acmicpc.net/problem/25193
 *
 */
public class Boj25193 {

    private static final char CHICKEN_DAY = 'C';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        char[] chickLog = br.readLine().toCharArray();
        System.out.println(chickenDays(chickLog));
    }

    private static int chickenDays(char[] chickLog) {
        int total = chickLog.length;
        int chick = 0;
        for(char cl: chickLog) {
            if(cl != CHICKEN_DAY) continue;
            chick++;
        }

        int div = total - chick + 1;
        return chick / div + (chick % div == 0 ? 0: 1);
    }
}
