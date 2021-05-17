package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 21734번: SMUPC의 등장
 *
 * @see https://www.acmicpc.net/problem/21734
 *
 */
public class Boj21734 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(char word: br.readLine().toCharArray()) {
            int size = 0;
            int ASCII = word;

            while(ASCII > 0) {
                size += ASCII % 10;
                ASCII /= 10;
            }

            while(size-- > 0) {
                sb.append(word);
            }

            sb.append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}
