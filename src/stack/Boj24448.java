package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author exponential-e
 * 백준 24448번: 図書館 2 (Library 2)
 *
 * @see https://www.acimcpc.net/problem/24448
 *
 */
public class Boj24448 {

    private static final String POP = "READ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();

        while(Q-- > 0) {
            String in = br.readLine();

            if(!in.equals(POP)) stack.push(in);
            else sb.append(stack.pop()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}
