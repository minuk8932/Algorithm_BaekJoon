package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 9411번: 실수 계산
 *
 * @see https://www.acmicpc.net/problem/9411
 *
 */
public class Boj9411 {

    private static final String TERMINATE = "0";
    private static final String NEW_LINE = "\n";

    private static final char NEGATIVE = '-';
    private static final char DIVIDER = '.';
    private static final char EMPTY = ' ';

    private static boolean[] positive;
    private static char[][] value;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            ArrayList<String> inputs = new ArrayList<>();

            while(true) {
                String input = br.readLine();
                if(input.equals(TERMINATE)) break;

                inputs.add(input);
            }

            valueSpecify(inputs);
            sb.append(formula()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Add positive & negative each other, exception dot
     *
     * @param flag
     * @return
     */
    private static int[] adder(boolean flag) {
        int[] result = new int[61];

        for(int i = 0; i < value.length; i++) {
            if(positive[i] != flag) continue;
            int carry = 0;

            for(int j = 60; j > 30; j--) {
                int v = value[i][j] == EMPTY ? 0: value[i][j] - '0';
                int sum = result[j] + v + carry;

                result[j] = sum % 10;
                carry = sum / 10;
            }

            for(int j = 29; j >= 0; j--) {
                int v = value[i][j] == EMPTY ? 0: value[i][j] - '0';
                int sum = result[j] + v + carry;

                result[j] = sum % 10;
                carry = sum / 10;
            }
        }

        return result;
    }

    private static String formula() {
        int[][] result = new int[2][61];

        result[0] = adder(true);
        result[1] = adder(false);

        boolean flag = true;
        for(int i = 0; i < 61; i++) {
            if (result[0][i] > result[1][i]) break;
            if (result[1][i] > result[0][i]){
                flag = false;
                break;
            }
        }

        int[] answer = new int[61];
        int carry = 0;
        for(int i = 60; i >= 0; i--) {
            int sub = flag ? result[0][i] - result[1][i] + carry: result[1][i] - result[0][i] + carry;

            if (sub < 0){
                carry = -1;
                sub += 10;
            }
            else {
                carry = 0;
            }

            answer[i] = sub;
        }

        StringBuilder number = new StringBuilder();
        if(!flag) number.append(NEGATIVE);

        int start = 0, end = 60;
        for(int i = 0; i < 30; i++) {
            start = i;
            if(answer[i] != 0) break;
        }

        for(int i = 60; i > 30; i--) {
            end = i;
            if(answer[i] != 0) break;
        }

        if(end == 31 && answer[end] == 0) end = 29;
        for(int i = start; i <= end; i++) {
            if(i == 30) number.append(DIVIDER);
            else number.append(answer[i]);
        }

        return number.toString();
    }

    /**
     *
     * String to char array
     *
     * line 164: dot
     * line 167 ~ 177: input numbers before dot & after dot each other
     *
     * @param inputs
     */
    private static void valueSpecify(ArrayList<String> inputs){
        int size = inputs.size();
        int idx = 0;
        positive = new boolean[size];
        value = new char[size][61];

        for(int i = 0; i < size; i++) {
            Arrays.fill(value[i], EMPTY);
        }

        for(String in: inputs) {
            if(in.charAt(0) != NEGATIVE) positive[idx] = true;
            int len = in.length();

            int dot = 0;
            for(char c: in.toCharArray()) {
                if(c == DIVIDER) break;
                dot++;
            }

            value[idx][30] = DIVIDER;

            int d = 1;
            for(int i = dot - 1; positive[idx] ? i >= 0: i > 0; i--) {
                value[idx][30 - d] = in.charAt(i);
                d++;
            }

            d = 1;
            for(int i = dot + 1; i < len; i++) {
                value[idx][30 + d] = in.charAt(i);
                d++;
            }

            idx++;
        }
    }
}
