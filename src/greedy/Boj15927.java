package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 15927번: 회문은 회문 아니야!
 *
 * @see https://www.acmicpc.net/problem/15927
 *
 */
public class Boj15927 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(isPalindrome(br.readLine().toCharArray()));
    }

    private static int isPalindrome(char[] string) {
        int[] alpha = new int[26];
        int len = string.length / 2;
        int count = 0, max = 0;

        for(int i = 0; i < len; i++) {
            int pre = string[i];
            int post = string[string.length - 1 - i];

            max = Math.max(max, ++alpha[pre - 'A']);
            max = Math.max(max, ++alpha[post - 'A']);

            if(pre != post) break;
            count += 2;
        }

        if(string.length % 2 == 1){                             // odd length
            count++;                                            // add palindrome size
            max = Math.max(max, ++alpha[string[len] - 'A']);    // check middel character
        }

        if(max == string.length) return -1;                     // is composed of one ?

        if(count == string.length) return count - 1;
        return string.length;
    }
}
