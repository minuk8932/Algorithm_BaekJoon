package hashing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1501번: 영어 읽기
 *
 * @see https://www.acmicpc.net/problem/1501
 *
 */
public class Boj1501 {
    private static HashMap<String, Integer> words = new HashMap<>();
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            String key = hashing(br.readLine());
            words.merge(key, 1, Integer::sum);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int count = 1;

            while(st.hasMoreTokens()) {                     // extract words in sentence
                String target = hashing(st.nextToken());
                count *= words.containsKey(target) ? words.get(target): 0;
            }

            sb.append(count).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }

    private static String hashing(String input) {
        int len = input.length();
        if(len <= 2) return input;                          // word length limitation

        char[] mid = new char[len - 2];
        for(int i = 1; i < len - 1; i++) {
            mid[i - 1] = input.charAt(i);
        }

        Arrays.sort(mid);

        StringBuilder sb = new StringBuilder();
        sb.append(input.charAt(0));

        for(char c: mid) {
            sb.append(c);
        }

        sb.append(input.charAt(len - 1));
        return sb.toString();
    }
}
