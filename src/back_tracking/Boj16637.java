package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 16637번: 괄호 추가하기
 *
 * @see https://www.acmicpc.net/problem/16637/
 *
 */
public class Boj16637 {
    private static int[][] values;
    private static char[] formula;

    private static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        formula = new char[N];
        values = new int[2][N];
        String input = br.readLine();
        for(int i = 0; i < N; i++) {
            formula[i] = input.charAt(i);

            if(i % 2 == 0) values[0][i] = formula[i] - '0';                 // save values
        }

        parenthesis(N);
        System.out.println(result);
    }

    private static void parenthesis(int n) {
        if(n == 1){
            result = values[0][0];
            return;
        }

        for(int i = 0; i < n - 2; i++) {
            values[1][i] = calculation(i + 1, values[0][i], 0);   // pre calculation each part
        }

        backTracking(n, values[0][0], 0);
        backTracking(n, values[1][0], 2);
    }

    private static void backTracking(int n, int current, int index) {
        if (index == n - 1) {
            result = Math.max(result, current);
            return;
        }

        int[] remain = {index + 2, index + 4};
        int subCal;

        if (remain[0] < n){                                                 // cal considering remained
            subCal = calculation(remain[0] - 1, current, 0);
            backTracking(n, subCal, remain[0]);
        }

        if (remain[1] < n){
            subCal = calculation(remain[0] - 1, current, 1);
            backTracking(n, subCal, remain[1]);
        }
    }

    private static int calculation(int op, int target, int index) {
        int cal = 0;

        switch (formula[op]) {
            case '+':
                cal = target + values[index][op + 1];
                break;
            case '-':
                cal = target - values[index][op + 1];
                break;
            case '*':
                cal = target * values[index][op + 1];
                break;
        }

        return cal;
    }
}
