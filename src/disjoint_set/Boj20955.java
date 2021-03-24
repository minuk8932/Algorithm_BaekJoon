package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20955번: 민서의 응급 수술
 *
 * @see https://www.acmicpc.net/problem/20955
 *
 */
public class Boj20955 {

    private static int result;

    private static class DisjointSet {
        int[] parent;

        public void init(int size) {
            parent = new int[size];

            for(int i = 0; i < parent.length; i++) {
                parent[i] = -1;
            }
        }

        public int find(int x) {
            if(parent[x] < 0) return x;
            return parent[x] = find(parent[x]);
        }

        public boolean merged(int x, int y) {
            x = find(x);
            y = find(y);

            if(x == y) return true;

            if (parent[x] < parent[y]) {
                parent[x] += parent[y];
                parent[y] = x;
            }
            else {
                parent[y] += parent[x];
                parent[x] = y;
            }

            return false;
        }
    }

    /**
     *
     * Main
     *
     * line 75: Cut synapse to make tree
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        DisjointSet disjointSet = new DisjointSet();
        disjointSet.init(N);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            if(disjointSet.merged(x, y)) result++;
        }

        treeMaker(disjointSet);
        System.out.println(result);
    }

    /**
     *
     * Tree maker
     *
     * line 94 ~ 97: Find seperated set & counting
     *
     * @param set
     */
    private static void treeMaker(DisjointSet set) {
        int n = set.parent.length;
        if(-set.parent[set.find(0)] == n) return;

        for(int i = 0; i < n; i++) {
            if(set.parent[i] >= 0) continue;
            result++;
        }

        result--;
    }
}
