package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 5875번: 오타
 *
 * @see https://www.acmicpc.net/problem/5875/
 *
 */
public class Boj5875 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] parenthesis = br.readLine().toCharArray();

        System.out.println(checker(parenthesis));
    }

    private static int checker(char[] paren) {
        if(paren.length % 2 == 1) return 0;

        int pair = 0;
        for(int i = 0; i < paren.length; i++) {
            pair += paren[i] == '(' ? 1: -1;
        }

        if(Math.abs(pair) != 2) return 0;       // any pairs exist

        int count = 0;                          // validator
        int open = 0, close = 0;

        for (char c: paren) {
            if(c == '(') {
                count++;
                open++;
            }
            else {
                count--;
                close++;
            }

            if(count == -1) return close;       // need close
            if(count <= 1) open = 0;
        }

        return open;
    }
}
