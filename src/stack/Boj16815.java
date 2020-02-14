package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 *
 * @author exponential-e
 * 백준 16815번: Star in parentheses
 *
 * @see https://www.acmicpc.net/problem/16815/
 *
 */
public class Boj16815 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] paren = br.readLine().toCharArray();

        System.out.println(isBalanced(paren));
    }

    private static int isBalanced(char[] p){
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int idx = 0;

        while(idx < p.length){
            if(p[idx] == '*') return stack.size();          // get size

            if(p[idx] == '(') stack.push(p[idx]);
            else stack.pop();

            idx++;
        }

        return 0;
    }
}
