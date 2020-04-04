package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13344번: Chess Tournament
 *
 * @see https://www.acmicpc.net/problem/13344/
 *
 */
public class Boj13344 {
    private static ArrayList<Integer>[] tree;
    private static ArrayList<Integer>[] path;
    private static int[] parent;
    private static int[] visit;

    private static final char LEFT = '>';
    private static final char RIGHT = '<';

    private static final String C = "consistent";
    private static final String I = "inconsistent";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            char mark = st.nextToken().charAt(0);
            int node2 = Integer.parseInt(st.nextToken());

            if (mark == LEFT) tree[node1].add(node2);
            else if (mark == RIGHT) tree[node2].add(node1);
            else merge(find(node1), find(node2));
        }

        System.out.println(judgement(N) ? C : I);
    }

    private static void init(int n) {
        tree = new ArrayList[n];
        path = new ArrayList[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            path[i] = new ArrayList<>();
            parent[i] = -1;
        }
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        if (x == y) return;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    private static boolean judgement(int n) {
        visit = new int[n];

        for(int i = 0; i < n; i++){             // make list by set
            for(int j : tree[i]){
                int x = find(i);
                int y = find(j);

                path[x].add(y);
            }
        }

        for(int i = 0; i < n; i++){             // cycle detection
            if(iscycle(i)) return false;
        }

        return true;
    }

    private static boolean iscycle(int node){
        if(visit[node] != 0){
            if(visit[node] == -1) return true;
            return false;
        }

        visit[node] = -1;
        for(int next: path[node]){
            if(iscycle(next)) return true;
        }

        visit[node] = 1;
        return false;
    }
}
