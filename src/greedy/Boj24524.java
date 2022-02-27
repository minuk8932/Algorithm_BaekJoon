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
        String S = br.readLine();
        String T = br.readLine();
        int len = T.length();

        for(int i = 0; i < len; i++) {
            map.put(T.charAt(i), i + 1);
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
    private static int linearSearching(String src) {
        int len = src.length();
        int size = map.size();

        int[] arr = new int[size + 1];
        arr[0] = len;

        for (int i = 0; i < len; i++) {
            if(!map.containsKey(src.charAt(i))) continue;

            int index = map.get(src.charAt(i));
            if (arr[index - 1] == 0) continue;

            arr[index - 1]--;
            arr[index]++;
        }

        return arr[size];
    }
}
