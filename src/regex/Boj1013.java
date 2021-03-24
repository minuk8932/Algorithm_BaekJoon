package regex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 *
 * @author exponential-e
 * 백준 1013번: Contact
 *
 * @see https://www.acmicpc.net/problem/1013
 *
 */
public class Boj1013 {
    private static final String Y = "YES\n";
    private static final String S = "NO\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(N-- > 0 ){
            sb.append(Pattern.matches("(100+1+|01)+", br.readLine()) ? Y: S);
        }

        System.out.println(sb.toString());
    }
}
