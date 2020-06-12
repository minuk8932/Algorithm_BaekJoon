package string_handle.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 1701번: Cubeditor
 *
 * @see https://www.acmicpc.net/problem/1701/
 *
 */
public class Boj1701 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cube = br.readLine().toCharArray();

        System.out.println(editorKMP(cube));
    }

    private static int editorKMP(char[] cube) {
        int max = 0;

        for(int i = 0; i < cube.length; i++) {
            int[] fail = failure(makeSubSet(cube, i, cube.length));         // find same suffix size

            for(int j = 0; j < fail.length; j++) {
                max = Math.max(fail[j], max);
            }
        }

        return max;
    }

    private static int[] failure(char[] word) {
        int[] f = new int[word.length];

        int j = 0;
        for(int i = 1; i < word.length; i++) {
            while(j > 0 && word[i] != word[j]) {                            // find next same thing
                j = f[j - 1];
            }

            if(word[i] == word[j]) f[i] = ++j;                              // continuous sub string
        }

        return f;
    }

    private static char[] makeSubSet(char[] c, int start, int end) {
        char[] arr = new char[end - start];

        int index = 0;
        for(int i = start; i < end; i++) {
            arr[index++] = c[i];
        }

        return arr;
    }
}
