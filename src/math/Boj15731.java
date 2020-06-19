package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 15731번: Python 문법
 *
 * @see https://www.acmicpc.net/problem/15731/
 *
 */
public class Boj15731 {
    private static final int MOD = 1_000_000_007;
    private static final char FOR_STATEMENT = 'f';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] python = br.readLine().toCharArray();

        System.out.println(indentation(python));
    }

    private static int indentation(char[] py) {
        int indent = 0;

        int[] result = new int[py.length];
        result[0] = 1;

        for(char p: py) {
            if(p == FOR_STATEMENT){                                             // count for statement
                indent++;
                continue;
            }

            for(int i = 1; i <= indent; i++) {                                  // make indentation case
                result[i] = ((result[i] % MOD) + (result[i - 1] % MOD)) % MOD;
            }
        }

        return result[indent];
    }
}
