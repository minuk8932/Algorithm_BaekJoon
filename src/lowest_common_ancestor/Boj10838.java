package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10838번: 트리
 *
 * @see https://www.acmicpc.net/problem/10838
 *
 */
public class Boj10838 {

    private static int[] parent;
    private static int[] color;
    private static int[] lev;

    private static Set<Integer> type;

    private static final int MAX = 1_000;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        init(N);

        StringBuilder sb = new StringBuilder();
        int seq = 1;
        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int lca = cmd == 2 ? 0: LCA(a, b, seq);

            switch (cmd) {
                case 1:
                    int c = Integer.parseInt(st.nextToken());

                    coloring(c, a, lca);
                    coloring(c, b, lca);

                    break;

                case 2:
                    parent[a] = b;
                    break;

                case 3:     // count
                    type = new HashSet<>();

                    searchingColor(a, lca);
                    searchingColor(b, lca);

                    sb.append(type.size()).append(NEW_LINE);
                    break;
            }

            seq++;
        }

        System.out.println(sb.toString());
    }

    private static void coloring(int paint, int target, int lca) {
        while (target != lca) {
            color[target] = paint;
            target = parent[target];
        }
    }

    /**
     *
     * LCA
     *
     * line 95: Following condition
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    private static int LCA(int x, int y, int z) {
        if (x == y) return x;

        int count = 0;
        while (x >= 0 && count < MAX) {
            lev[x] = z;
            x = parent[x];

            count++;
        }

        while (y >= 0) {
            if (lev[y] == z) return y;
            y = parent[y];
        }

        return 0;
    }

    private static void searchingColor(int target, int lca) {
        while (target != lca) {
            type.add(color[target]);
            target = parent[target];
        }
    }

    private static void init(int n) {
        parent = new int[n + 1];
        color = new int[n + 1];
        lev = new int[n + 1];
    }
}
