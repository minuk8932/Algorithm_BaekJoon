package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 21921번: 블로그
 *
 * @see https://www.acmicpc.net/problem/21921
 *
 */
public class Boj21921 {

    private static final String NONE = "SAD";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = PARSE.apply(st.nextToken());
        int X = PARSE.apply(st.nextToken());

        int[] visiter = ARRAY.apply(N);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            visiter[i] = PARSE.apply(st.nextToken());
        }

        System.out.println(slidingWindow(visiter, X));
    }

    private static String slidingWindow(int[] visit, int x) {
        TreeMap<Integer, Integer> records = new TreeMap<>();
        int sum = 0;

        for(int i = 0; i < x; i++) {
            sum += visit[i];
        }

        records.put(-sum, 1);

        for(int i = x; i < visit.length; i++) {
            sum -= visit[i - x];
            sum += visit[i];

            records.merge(-sum, 1, Integer::sum);
        }

        Map.Entry<Integer, Integer> peek = records.pollFirstEntry();
        if(peek.getKey() == 0) return NONE;

        StringBuilder sb = new StringBuilder();
        sb.append(-peek.getKey()).append(NEW_LINE).append(peek.getValue());

        return sb.toString();
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, int[]> ARRAY = int[]::new;

}
