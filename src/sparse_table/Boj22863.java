package sparse_table;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22863번: 원상 복구 (large)
 *
 * @see https://www.acmicpc.net/problem/22863
 *
 */
public class Boj22863 {

    private static int[][] parent;
    private static int[] S;
    private static int N;

    private static final int LIMIT = 51;
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = PARSE.apply(st.nextToken());
        long K = PARSE_LONG.apply(st.nextToken());

        parent = ARR_2D.apply(N + 1);
        S = ARR.apply(N + 1);

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            S[i] = PARSE.apply(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            parent[0][i] = PARSE.apply(st.nextToken());
        }

        connection();
        System.out.println(shuffle(K));
    }

    private static String shuffle(long shift) {
        StringBuilder sb = new StringBuilder();
        int[] answer = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            int target = i;
            
            for(int p = 0; p < LIMIT; p++) {
                long jump = 1L << p;
                if ((shift & jump) == 0) continue;

                target = parent[p][target];
            }

            answer[target] = S[i];
        }

        for(int i = 1; i <= N; i++){
            sb.append(answer[i]).append(SPACE);
        }

        return sb.toString();
    }

    /**
     *
     * Connection: make level
     *
     */
    private static void connection() {
        for(int p = 1; p < LIMIT; p++) {
            for (int cur = 1; cur <= N; cur++) {
                parent[p][cur] = parent[p - 1][parent[p - 1][cur]];
            }
        }
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<String, Long> PARSE_LONG = Long::parseLong;

    private static final Function<Integer, int[]> ARR = int[]::new;
    private static final Function<Integer, int[][]> ARR_2D = size -> new int[LIMIT][size];
}