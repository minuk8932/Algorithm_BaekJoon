package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 24552번: 올바른 괄호
 *
 * @see https://www.acmicpc.net/problem/24552
 *
 */
public class Boj24552 {

    private static final char OPEN = '(';
    private static final char CLOSE = ')';
    private static final char SPACE = ' ';

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        N = input.length();

        int[] prefixSum = new int[N + 1];
        for(int i = 1; i < prefixSum.length; i++) {
            int adder = (input.charAt(i - 1) == OPEN ? 1: -1);
            prefixSum[i] = prefixSum[i - 1] + adder;
        }

        int last = prefixSum[N];
        int[] components = last == 1 ?
            new int[]{0, input.length() - 1, 1} : new int[]{input.length() - 1, 0, -1};

        char[] fix = getFixed(input.toCharArray(), components);
        System.out.println(caseWork(last > 0 ? CLOSE: OPEN, fix
            , input.charAt(0) == CLOSE || input.charAt(N - 1) == OPEN));
    }

    private static char[] getFixed(char[] input, int[] components) {
        char[] result = new char[input.length];
        Arrays.fill(result, SPACE);

        int index = -1;
        int value = 0;

        for(int i = components[0]; LOOP.test(components, i); i += components[2]) {
            value += (input[i] == OPEN ? 1: -1);
            if(value != 0) continue;

            index = i;
        }

        if(index == -1) return input;
        for(int i = index + components[2]; LOOP.test(components, i); i += components[2]) {
            result[i] = input[i];
        }

        return result;
    }

    private static final BiPredicate<int[], Integer> LOOP = (components, index) ->
        index * components[2] <= components[1] * components[2];

    private static int caseWork(char target, char[] parenthesis, boolean wrongParenthesis) {
        if(wrongParenthesis) return 1;
        int count = 0;

        for(char par: parenthesis) {
            if(par == SPACE) continue;
            if(par == target) continue;
            count++;
        }

        return count;
    }
}
