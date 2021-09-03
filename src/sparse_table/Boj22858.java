package sparse_table;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22858번: 원상 복구 (small)
 *
 * @see https://www.acmicpc.net/problem/22858
 *
 */
public class Boj22858 {

    private static int[][] parent;
    private static int[] D, S;
    private static int N;

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = PARSE.apply(st.nextToken());
        long K = PARSE_LONG.apply(st.nextToken());

        parent = ARR_2D.apply(N);
        S = ARR.apply(N);
        D = ARR.apply(N);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            S[i] = PARSE.apply(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            D[i] = PARSE.apply(st.nextToken()) - 1;
        }

        connection();
        System.out.println(shuffle(K));
    }

    private static String shuffle(long shift) {
        StringBuilder sb = new StringBuilder();
        List<Integer> plist = new ArrayList<>();

        int target = 0;

        for(int p = 20; p >= 0; p--) {
            long jump = 1L << p;
            if(jump > shift) continue;

            shift -= jump;
            target = parent[target][p];
            plist.add(p);
        }

        sb.append(S[target]).append(SPACE);
        for(int i = 1; i < N; i++) {
            target = i;

            for(int p: plist) {
                target = parent[target][p];
            }

            sb.append(S[target]).append(SPACE);
        }

        return sb.toString();
    }

    /**
     *
     * Connection: make level
     *
     */
    private static void connection() {
        for(int i = 0; i < N; i++) {
            parent[D[i]][0] = i;
        }

        for(int p = 1; p < 21; p++) {
            for(int cur = 0; cur < N; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<String, Long> PARSE_LONG = Long::parseLong;

    private static final Function<Integer, int[]> ARR = int[]::new;
    private static final Function<Integer, int[][]> ARR_2D = size -> new int[size][21];

}
