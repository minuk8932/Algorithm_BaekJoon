package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21944번: 문제 추천 시스템 Version 2
 *
 * @see https://www.acmicpc.net/problem/21944
 *
 */
public class Boj21944 {

    private static final String A = "add", R1 = "recommend", S = "solved";
    private static final String R2 = "recommend2", R3 = "recommend3";
    private static final String NEW_LINE = "\n";

    private static final int CIPHER = 1_000_000;
    private static final int INF = 1_000_000_000;

    private static TreeSet<Integer>[] problems = new TreeSet[101];
    private static Problem[] level = new Problem[100_001];

    private static class Problem {
        int lev;
        int cat;

        public Problem(int lev, int cat) {
            this.lev = lev;
            this.cat = cat;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < problems.length; i++) {
            problems[i] = new TreeSet<>();
        }

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());

            putting(P, L, G);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            int x = Integer.parseInt(st.nextToken());
            int L, G;

            switch (cmd) {
                case A:
                    L = Integer.parseInt(st.nextToken());
                    G = Integer.parseInt(st.nextToken());

                    putting(x, L, G);
                    break;

                case R1:
                    L = Integer.parseInt(st.nextToken());

                    sb.append(recommender(x, L) % CIPHER).append(NEW_LINE);
                    break;

                case R2:
                    sb.append(recommender2(x) % CIPHER).append(NEW_LINE);
                    break;

                case R3:
                    L = Integer.parseInt(st.nextToken());

                    sb.append(recommender3(x, L) % CIPHER).append(NEW_LINE);
                    break;

                case S:
                    problems[level[x].cat].remove(level[x].lev * CIPHER + x);
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Recommender 2
     *
     * line 110 ~ 111: comparing all categories
     *
     * @param x
     * @return
     */
    private static int recommender2(int x) {
        int result = x == 1 ? -1: INF;

        for(int g = 1; g < problems.length; g++) {
            if(problems[g].isEmpty()) continue;

            result = x == 1 ?
                    Math.max(result, problems[g].last()): Math.min(result, problems[g].first());
        }

        return result;
    }

    /**
     *
     * Recommeder
     *
     * Just G category comparing
     *
     * @param g
     * @param x
     * @return
     */
    private static int recommender(int g, int x) {
        if(x == 1) return problems[g].last();
        return problems[g].first();
    }

    /**
     *
     * Recommender 3
     *
     * line 150 ~ 158: null check, find value by lower/upper bound & distinct with CIPHER
     *
     * @param x
     * @param limit
     * @return
     */
    private static int recommender3(int x, int limit) {
        int result = x == 1 ? INF: -1;
        limit = limit * CIPHER;

        for(int g = 1; g < problems.length; g++) {
            if(problems[g].isEmpty()) continue;
            int target;

            try {
                if (x == 1) target = problems[g].higher(limit);
                else target = problems[g].lower(limit);
            }
            catch (NullPointerException npe) {
                continue;
            }

            result = x == 1 ? Math.min(result, target): Math.max(result, target);
        }

        return result == INF ? -1: result;
    }

    private static void putting(int P, int L, int G) {
        problems[G].add(L * CIPHER + P);
        level[P] = new Problem(L, G);
    }
}
