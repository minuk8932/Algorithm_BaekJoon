package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 1254번: 팰린드롬 만들기
 *
 * @see https://www.acmicpc.net/problem/1254
 *
 */
public class Boj1254 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        char[] arr = input.toCharArray();

        System.out.println(makePalindrome(input, arr));
    }

    private static int makePalindrome(String in, char[] arr) {
        int len = arr.length;
        if(isPalindrome(in)) return len;

        for(int i = 0; i < len; i++) {
            int count = 0;

            StringBuilder builder = new StringBuilder();
            builder.append(in);
            for(int rev = i; rev >= 0; rev--) {             // make palindrome with a character reversed
                builder.append(arr[rev]);
                count++;
            }

            if (isPalindrome(builder.toString())) return len + count;
        }

        return len * 2 - 1;                                 // most needs
    }

    private static boolean isPalindrome(String str) {
        int size = str.length();

        for(int i = 0; i < size / 2; i++) {
            if(str.charAt(i) != str.charAt(size - 1 - i)) return false;
        }

        return true;
    }
}
