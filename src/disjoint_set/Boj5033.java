package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 5033번: Money matters
 *
 * @see https://www.acmicpc.net/problem/5033
 *
 */
public class Boj5033 {

    private static final String P = "POSSIBLE";
    private static final String IMP = "IMPOSSIBLE";

    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n);
        int[] owe = new int[n];
        for(int i = 0; i < n; i++) {
            owe[i] = Integer.parseInt(br.readLine());
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            merge(x, y);
        }

        System.out.println(owed(owe));
    }

    private static String owed(int[] o) {
        Set<Integer> sets = new HashSet<>();
        for(int i = 0; i < o.length; i++) {
            if(parent[i] >= 0) continue;
            sets.add(i);
        }

        for(int s: sets) {
            int sum = 0;

            for(int i = 0; i < o.length; i++) {
                if(find(i) != s) continue;
                sum += o[i];
            }

            if(sum != 0) return IMP;
        }

        return P;
    }

    private static void init(int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}