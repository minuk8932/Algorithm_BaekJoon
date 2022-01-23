package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 4889번: 안정적인 문자열
 *
 * @see https://www.acmicpc.net/problem/4889
 *
 */
public class Boj4889 {
    private static final char TERMINATE = '-';
    private static final String NEW_LINE = "\n";
    private static final String DOT = ". ";

    private static final char OPEN = '{';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int sequence = 1;

        while(true) {
            char[] parenthesis = br.readLine().toCharArray();
            if(parenthesis[0] == TERMINATE) break;

            sb.append(sequence++).append(DOT).append(validator(parenthesis)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int validator(char[] pars) {
        int count = 0;
        int size = 0;

        for(char par: pars) {
            if(par == OPEN) {
                size++;
                continue;
            }

            if(size > 0) {
                size--;
                continue;
            }

            count += 1;
            size++;
        }

        return count + (size >> 1);
    }
}
