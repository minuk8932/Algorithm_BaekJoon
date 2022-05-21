package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 25178번: 두라무리 휴지
 *
 * @see https://www.acmicpc.net/problem/25178
 *
 */
public class Boj25178 {

    private static final String O = "YES";
    private static final String X = "NO";

    private static int shift = 0;
    private static int[] idx = new int[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        init();

        char[] src = br.readLine().toCharArray();
        char[] snk = br.readLine().toCharArray();

        System.out.println(isDuramuri(src, snk));
    }

    private static String isDuramuri(char[] S, char[] T) {
        if(!countingEquals(charCounting(S), charCounting(T))) return X;
        if(S[0] != T[0] || S[S.length - 1] != T[T.length - 1]) return X;

        while(idx[0] < S.length && idx[1] < T.length) {
            int sInt = shifter(S[idx[0]]);
            int tInt = shifter(T[idx[1]]);

            sInt = searching(sInt, S, 0);
            tInt = searching(tInt, T, 1);

            if(sInt != tInt) return X;
            idx[0]++;
            idx[1]++;
        }

        return idx[0] == idx[1] ? O: X;
    }

    private static int searching(int value, char[] words, int index) {
        while((shift & value) != 0) {
            idx[index]++;
            if(idx[index] >= words.length) break;

            value = shifter(words[idx[index]]);
        }

        return value;
    }

    private static boolean countingEquals(int[] alpha1, int[] alpha2) {
        for(int i = 0; i < alpha1.length; i++) {
            if(alpha1[i] != alpha2[i]) return false;
        }

        return true;
    }

    private static int[] charCounting(char[] words) {
        int[] alpha = new int[26];

        for(char c: words) {
            alpha[c - 'a']++;
        }

        return alpha;
    }

    private static void init() {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        for(char v: vowels) {
            shift |= shifter(v);
        }
    }

    private static int shifter(char c) {
        return 1 << (c - 'a');
    }
}
