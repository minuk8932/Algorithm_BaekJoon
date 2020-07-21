package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 *
 * @author exponential-e
 * 백준 17413번: 단어 뒤집기 2
 *
 * @see https://www.acmicpc.net/problem/17413
 *
 */
public class Boj17413 {
    private static final char OPEN = '<', CLOSE = '>';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(reverse(br.readLine().toCharArray()));
    }

    private static String reverse(char[] s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        boolean flag = false;

        for(char c: s) {
            if(c == OPEN) flag = true;

            if(flag || c == ' ') {
                while(!stack.isEmpty()) { sb.append(stack.pop()); }     // print
                sb.append(c);

                if(c == CLOSE) flag = false;
            }
            else {
                stack.push(c);                  // reverse save
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
