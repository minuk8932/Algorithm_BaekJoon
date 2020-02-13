package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 17609번: 회문
 *
 * @see https://www.acmicpc.net/problem/17609/
 *
 */
public class Boj17609 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            char[] word = br.readLine().toCharArray();
            sb.append(isPalindrome(word)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int isPalindrome(char[] c){
        boolean flag = false;

        int prev = 0, post = c.length - 1;

        while(prev <= post){
            if(c[prev++] == c[post--]) continue;

            flag = true;

            if(restart(c, prev - 1, post) || restart(c, prev, post + 1)) break;  // almost palindrome ?
            return 2;           // not palindrome
        }

        return flag ? 1: 0;
    }

    private static boolean restart(char[] c, int from, int to){
        while(from <= to){
            if(c[from++] != c[to--]) return false;
        }

        return true;
    }
}
