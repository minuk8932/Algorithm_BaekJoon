package string_handle.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 4354번: 문자열 제곱
 *
 * @see https://www.acmicpc.net/problem/4354/
 *
 */
public class Boj4354 {
    private static final String DOT = ".";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String input = br.readLine();
            if (input.equals(DOT)) break;

            char[] str = input.toCharArray();
            sb.append(judgement(str)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int judgement(char[] arr) {
        if(arr.length == 0) return 0;

        int[] fail = failure(arr);
        int len = arr.length;
        int mod = len % (len - fail[len - 1]);

        return mod != 0 ? 1: len / (len - fail[len - 1]);       // except prime palindrome
    }

    private static int[] failure(char[] arr) {
        int[] f = new int[arr.length];
        int j = 0;

        for(int i = 1; i < arr.length; i++) {
            while(j > 0 && arr[i] != arr[j]) {                  // find, prefix == suffix
                j = f[j - 1];
            }

            if(arr[i] == arr[j]) f[i] = ++j;
        }

        return f;
    }
}
