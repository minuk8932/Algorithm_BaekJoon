package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 7585번: Brackets
 *
 * @see https://www.acmicpc.net/problem/7585
 *
 */
public class Boj7585 {
    private static ArrayDeque<Integer> stack;

    private static final HashMap<Character, Integer> brackets = new HashMap<>();

    private static final String AC = "Legal\n";
    private static final String WA = "Illegal\n";

    private static final String TERMINATOR = "#";
    private static final char[] OPENED = {'(', '{', '['};
    private static final char[] CLOSED = {')', '}', ']'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        init();

        while(true) {
            String input = br.readLine();
            if(input.equals(TERMINATOR)) break;

            stack = new ArrayDeque<>();
            sb.append(validation(input.toCharArray()) ? AC: WA);
        }

        System.out.println(sb.toString());
    }

    private static boolean validation(char[] chars) {
        for(char c: chars) {
            if(!isBrackets(c)) continue;                    // any other characters not need

            if(isOpened(c)) {
                stack.push(brackets.get(c));                // open push
            }
            else {
                if(stack.isEmpty()) return false;           // close pop when matched stack peek data
                if(CLOSED[stack.peek()] != c) return false;

                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    private static void init() {
        for(int i = 0; i < 3; i++) {
            brackets.put(OPENED[i], i);
        }
    }

    private static boolean isBrackets(char b) {
        return isOpened(b) || isClosed(b);
    }

    private static boolean isOpened(char b) {
        return b == OPENED[0] || b == OPENED[1] || b == OPENED[2];
    }

    private static boolean isClosed(char b) {
        return b == CLOSED[0] || b == CLOSED[1] || b == CLOSED[2];
    }
}
