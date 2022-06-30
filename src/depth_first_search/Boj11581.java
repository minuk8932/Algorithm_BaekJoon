package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11581번: 구호물자
 *
 * @see https://www.acmicpc.net/problem/11581
 *
 */
public class Boj11581 {

    private static ArrayList<ArrayList<Integer>> path = new ArrayList<>();
    private static int[] visit;

    private static boolean isCycle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            path.add(i, new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) {
            int M = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            while(M-- > 0) {
                int j = Integer.parseInt(st.nextToken()) - 1;
                path.get(i).add(j);
            }
        }

        visit = new int[N];
        recursion(0);

        System.out.println(!isCycle ? "NO CYCLE": "CYCLE");
    }

    private static void recursion(int current) {
        if (visit[current] == 1) {
            isCycle = true;
            return;
        }

        visit[current] = 1;

        for (int next : path.get(current)) {
            if (visit[next] != 2) recursion(next);
        }

        visit[current] = 2;
    }
}
