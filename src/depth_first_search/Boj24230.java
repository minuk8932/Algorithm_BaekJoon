package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24230번: 트리 색칠하기
 *
 * @see https://www.acmicpc.net/problem/24230
 *
 */
public class Boj24230 {

    private static int[] colors;

    private static List<Integer>[] tree;
    private static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        colors = new int[N];
        tree = new ArrayList[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int leaf1 = Integer.parseInt(st.nextToken()) - 1;
            int leaf2 = Integer.parseInt(st.nextToken()) - 1;

            tree[leaf1].add(leaf2);
            tree[leaf2].add(leaf1);
        }

        dfs(0, -1);
        System.out.println(count);
    }

    private static void dfs(int current, int prev) {
        if(colors[current] != 0) {
            if(prev == -1) count++;
            else count += colors[prev] == colors[current] ? 0: 1;
        }

        for(int next: tree[current]) {
            if (next == prev) continue;

            dfs(next, current);
        }
    }
}
