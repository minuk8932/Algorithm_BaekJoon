package hashing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author exponential-e
 * 백준 5614번: 問題 3
 *
 * @see https://www.acmicpc.net/problem/5614
 *
 */
public class Boj5614 {

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new TreeMap<>((str1, str2) -> {
            if(str1.length() == str2.length()) return str1.compareTo(str2);
            return str1.length() - str2.length();
        });

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map.merge(st.nextToken(), Integer.parseInt(st.nextToken()), Integer::sum);
        }

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            sb.append(entry.getKey()).append(SPACE).append(entry.getValue()).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }
}
