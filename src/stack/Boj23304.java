package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 *
 * @author exponential-e
 * 백준 23304번: 아카라카
 *
 * @see https://www.acmicpc.net/problem/23304
 *
 */
public class Boj23304 {

    private static final String AK = "AKARAKA";
    private static final String NON_AK = "IPSELENTI";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        System.out.println(process(S) ? AK: NON_AK);
    }

    private static boolean process(String s){
        int len = s.length();

        if (len == 1) return true;
        if (!isPalindrome(s.toCharArray())) return false;

        return process(s.substring(0, len >> 1));
    }

    private static boolean isPalindrome(char[] words){
        if (words.length == 1) return true;

        ArrayDeque<Character> stack = new ArrayDeque<>();

        int half = words.length >> 1;
        for (int i = 0; i < half; i++){
            stack.push(words[i]);
        }

        int left = half + words.length % 2;
        for (int i = left; i < words.length; i++){
            if (stack.pop() != words[i]) return false;
        }
        return true;
    }
}
