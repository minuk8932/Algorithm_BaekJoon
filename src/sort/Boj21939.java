package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21939번: 문제 추천 시스템 Version1
 *
 * @see https://www.acmicpc.net/problem/21939
 *
 */
public class Boj21939 {

    private static final String A = "add", R = "recommend", S = "solved";
    private static final String NEW_LINE = "\n";

    private static final int CIPHER = 1_000_000;

    private static TreeSet<Integer> problems;
    private static int[] level = new int[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        problems = new TreeSet<>();

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            putting(P, L);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            int x = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case A:
                    int L = Integer.parseInt(st.nextToken());
                    putting(x, L);
                    break;

                case R:
                    sb.append(recommender(x) % CIPHER).append(NEW_LINE);
                    break;

                case S:
                    problems.remove(level[x] * CIPHER + x);
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    private static int recommender(int x) {
        if(x == 1) return problems.last();
        return problems.first();
    }

    private static void putting(int P, int L) {
        problems.add(L * CIPHER + P);
        level[P] = L;
    }
}
