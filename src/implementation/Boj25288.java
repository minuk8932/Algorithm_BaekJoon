package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 25288번: 영어 시험
 *
 * @see https://www.acmicpc.net/problem/25288
 *
 */
public class Boj25288 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(palindrome(N, br.readLine()));
    }

    private static String palindrome(int n, String word) {
        StringBuilder sb = new StringBuilder();
        while(n-- > 0) {
            sb.append(word);
        }

        return sb.toString();
    }
}
