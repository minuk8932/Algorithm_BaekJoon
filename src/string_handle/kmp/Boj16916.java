package string_handle.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 16916번: 부분 문자열
 *
 * @see https://www.acmicpc.net/problem/16916/
 *
 */
public class Boj16916 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        char[] P = br.readLine().toCharArray();

        System.out.println(KMP(S, P));
    }

    private static int KMP(char[] s, char[] p) {
        int[] fail = failure(p);

        int j = 0;
        for(int i = 0; i < s.length; i++) {
            while (j > 0 && s[i] != p[j]) {
                j = fail[j - 1];
            }

            if (s[i] == p[j]) {
                if (j == p.length - 1) return 1;        // find all
                else j++;                               // miss
            }
        }

        return 0;
    }

    private static int[] failure(char[] p) {
        int[] f = new int[p.length];
        int j = 0;

        for(int i = 1; i < f.length; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = f[j - 1];
            }

            if (p[i] == p[j]) f[i] = ++j;
        }

        return f;
    }
}
