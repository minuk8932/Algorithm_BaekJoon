package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author exponential-e
 * 백준 24524번: 아름다운 문자열
 *
 * @see https://www.acmicpc.net/problem/24524
 *
 */
public class Boj24524 {

    private static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        char[] T = br.readLine().toCharArray();

        for(int i = 1; i <= T.length; i++) {
            map.put(T[i - 1], i);
        }

        System.out.println(linearSearching(S));
    }

    /**
     *
     * Linear search
     *
     * line 52 ~ 57: move 1, check string subsequence
     *
     * @param src
     * @return
     */
    private static int linearSearching(char[] src) {
        int size = map.size();

        int[] chart = new int[size + 1];
        chart[0] = src.length;

        for (int i = 0; i < src.length; i++) {
            if(!map.containsKey(src[i])) continue;

            int index = map.get(src[i]);
            if (chart[index - 1] == 0) continue;

            chart[index]++;
            chart[index - 1]--;
        }

        return chart[size];
    }
}
