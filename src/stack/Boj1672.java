package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 *
 * @author exponential-e
 * 백준 1672번: DNA 해독
 *
 * @see https://www.acmicpc.net/problem/1672/
 *
 */
public class Boj1672 {
    private static final HashMap<Character, Integer> INDEX = new HashMap<>();
    private static final char[][] ORDER = {{'A', 'C', 'A', 'G'},
            {'C', 'G', 'T', 'A'},
            {'A', 'T', 'C', 'G'},
            {'G', 'A', 'G', 'T'}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init();

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for(char c: br.readLine().toCharArray()) {
            stack.push(c);
        }

        System.out.println(result(N, stack));
    }

    private static char result(int n, ArrayDeque<Character> stack) {
        int loop = n - 1;

        while(loop-- > 0) {
            char peek = stack.pop();
            char second = stack.pop();

            stack.push(ORDER[INDEX.get(peek)][INDEX.get(second)]);
        }

        return stack.pop();
    }

    private static void init() {
        INDEX.put('A', 0);
        INDEX.put('G', 1);
        INDEX.put('C', 2);
        INDEX.put('T', 3);
    }
}
