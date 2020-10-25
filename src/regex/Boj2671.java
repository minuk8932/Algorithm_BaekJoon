package regex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 *
 * @author exponential-e
 * 백준 2671번: 잠수함 식별
 *
 * @see https://www.acmicpc.net/problem/2671
 *
 */
public class Boj2671 {
    private static final String N = "NOISE";
    private static final String S = "SUBMARINE";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sound = br.readLine();

        System.out.println(Pattern.matches("(100+1+|01)+", sound) ? S: N);
    }
}
