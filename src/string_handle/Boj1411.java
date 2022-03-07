package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author exponential-e
 * 백준 1411번: 비슷한 단어
 *
 * @see https://www.acmicpc.net/problem/1411
 *
 */
public class Boj1411 {

    private static String[] input;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        input = new String[N];
        for(int i = 0; i < N; i++) {
            input[i] = br.readLine();
        }

        System.out.println(comparing());
    }

    private static int comparing() {
        int answer = 0;

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                answer += changeOneToAnother(input[i], input[j]);
            }
        }

        return answer;
    }

    private static int changeOneToAnother(String in1, String in2) {
        Map<Character, Character> mapper = new HashMap<>();
        Map<Character, Character> reversed = new HashMap<>();

        int len = in1.length();

        for(int i = 0; i < len; i++) {
            char word1 = in1.charAt(i);
            char word2 = in2.charAt(i);

            if(mapper.containsKey(word1) && word2 != mapper.get(word1)) return 0;
            if(reversed.containsKey(word2) && word1 != reversed.get(word2)) return 0;

            mapper.put(word1, word2);
            reversed.put(word2, word1);
        }

        return 1;
    }
}
