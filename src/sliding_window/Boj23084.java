package sliding_window;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 *
 * @author exponential-e
 * 백준 23084번: IUPC와 비밀번호
 *
 * @see https://www.acmicpc.net/problem/23084
 *
 */
public class Boj23084 {

    private static final String CANDIDATE = "YES\n";
    private static final String NOT_CANDIDATE = "NO\n";

    private static final char ASCII_EXTRACTOR = 'a';

    private static Map<Character, Integer> source;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] src = br.readLine().toCharArray();

        source = new HashMap<>();
        container(src);

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            char[] snk = br.readLine().toCharArray();
            sb.append(isCandidate(src, snk) ? CANDIDATE: NOT_CANDIDATE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * is Candidate
     *
     * line 67 ~ 72: Sliding window
     *
     * @param S
     * @param T
     * @return
     */
    private static boolean isCandidate(char[] S, char[] T) {
        if(S.length > T.length) return false;
        if(S.length == T.length) return manhattanDistance(S, T);

        Map<Character, Integer> target = new HashMap<>();
        for(int i = 0; i < S.length; i++) {
            target.merge(T[i], 1, Integer::sum);
        }

        if(checkDifference(source, target)) return true;

        int index = 0;
        for(int i = S.length; i < T.length; i++) {
            target.merge(T[index++], -1, Integer::sum);
            target.merge(T[i], 1, Integer::sum);

            if(checkDifference(source, target)) return true;
        }

        return false;
    }

    /**
     *
     * Check difference
     *
     * line 87: 계속 틀린 이유, 당연히 각각의 맵을 비교하며 차잇값을 뽑아야 하는데, S만 기준으로 사용함.
     *
     * @param S
     * @param T
     * @return
     */
    private static boolean checkDifference(Map<Character, Integer> S, Map<Character, Integer> T) {
        int difference = 0;
        int multiply = 1;

        for(int i = 0; i < 26; i++) {
            char alpha = (char) (i + ASCII_EXTRACTOR);

            int diff = Math.abs(IS_CONTAINS.apply(S, alpha) - IS_CONTAINS.apply(T, alpha));
            if(diff == 0) continue;

            difference += diff;
            multiply *= diff;
        }

        return (difference == 2 && multiply == 1) || difference == 0;
    }

    private static void container(char[] arr) {
        for(char alpha: arr) {
            source.merge(alpha, 1, Integer::sum);
        }
    }

    private static boolean manhattanDistance(char[] S, char[] T) {
        int[][] count = new int[2][26];
        count[0] = alphaCounting(S);
        count[1] = alphaCounting(T);

        int difference = 0;
        int multiply = 1;

        for(int i = 0; i < 26; i++) {
            int diff = Math.abs(count[0][i] - count[1][i]);
            if(diff == 0) continue;

            difference += diff;
            multiply *= 1;
        }

        return difference == 2 && multiply == 1;
    }

    private static int[] alphaCounting(char[] arr) {
        int[] alpha = new int[26];

        for(char a: arr) {
            alpha[a - ASCII_EXTRACTOR]++;
        }

        return alpha;
    }

    private static final BiFunction<Map<Character, Integer>, Character, Integer> IS_CONTAINS = (map, alpha) ->
            map.containsKey(alpha) ? map.get(alpha): 0;

}
