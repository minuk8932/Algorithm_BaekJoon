package string_handle.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author exponential-e
 * 백준 1786번: 찾기
 *
 * @see https://www.acmicpc.net/problem/1786
 *
 */
public class Boj1786 {
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] T = br.readLine().toCharArray();
        char[] P = br.readLine().toCharArray();

        System.out.println(kmp(T, P));
    }

    private static String kmp(char[] t, char[] p) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] fail = failure(p);                        // pre make fail func

        int j = 0;
        for(int i = 0; i < t.length; i++) {
            while (j > 0 && t[i] != p[j]) {             // not matching
                j = fail[j - 1];
            }

            if (t[i] == p[j]) {                         // matching
                if (j == p.length - 1) {
                    result.add(i - j);
                    j = fail[j];
                }
                else {
                    j++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int size = result.size();
        sb.append(size).append(NEW_LINE);

        for(int index: result) {
            sb.append(++index).append(SPACE);
        }

        return sb.toString();
    }

    private static int[] failure(char[] words) {
        int m = words.length;
        int j = 0;

        int[] farr = new int[m];

        for (int i = 1; i < m; i++) {                   // matching prefix & suffix size
            while (j > 0 && words[i] != words[j]) {
                j = farr[j - 1];
            }

            if (words[i] == words[j]) farr[i] = ++j;
        }

        return farr;
    }
}
