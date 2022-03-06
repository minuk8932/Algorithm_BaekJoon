package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author exponential-e
 * 백준 20291번: 파일 정리
 *
 * @see https://www.acmicpc.net/problem/20291
 *
 */
public class Boj20291 {

    private static final String DOT = ".";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> extension = new TreeMap<>();
        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), DOT);
            st.nextToken();
            String tail = st.nextToken();

            extension.merge(tail, 1, Integer::sum);
        }

        System.out.println(printer(extension));
    }

    private static String printer(Map<String, Integer> extension) {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, Integer> entry: extension.entrySet()) {
            sb.append(entry.getKey()).append(SPACE).append(entry.getValue()).append(NEW_LINE);
        }

        return sb.toString();
    }
}
