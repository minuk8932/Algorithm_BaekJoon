package string_handle.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 1305번: 광고
 *
 * @see https://www.acmicpc.net/problem/1305
 *
 */
public class Boj1305 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] sign = br.readLine().toCharArray();

        int[] fail = failure(sign);
        System.out.println(N - fail[N - 1]);            // necessary containing size
    }

    private static int[] failure(char[] str) {
        int[] f = new int[str.length];

        int j = 0;
        for(int i = 1; i < str.length; i++) {           // find can ignore size
            while(j > 0 && str[i] != str[j]) {
                j = f[j - 1];
            }

            if(str[i] == str[j]) f[i] = ++j;
        }

        return f;
    }
}
