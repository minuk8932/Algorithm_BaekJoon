package greedy;

import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25556번: 포스택
 *
 * @see https://www.acmicpc.net/problem/25556
 *
 */
public class Boj25556 {

    private static ArrayDeque<Integer>[] stack = new ArrayDeque[4];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        for(int i = 0; i < 4; i++) {
            stack[i] = new ArrayDeque<>();
        }

        System.out.println(sorting(br.readLine()));
    }

    private static String sorting(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int[] peek = new int[4];
        Arrays.fill(peek, 1);

        while(st.hasMoreTokens()) {
            boolean flag = false;
            int current = Integer.parseInt(st.nextToken());

            for(int i = 0; i < stack.length; i++) {
                if(!stack[i].isEmpty() && stack[i].peek() > current) continue;
                stack[i].push(current);

                flag = true;
                break;
            }

            if(!flag) return "NO";
        }

        return "YES";
    }
}
