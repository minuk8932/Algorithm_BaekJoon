package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9098번: The Suspects
 *
 * @see https://acmicpc.net/problem/9098
 *
 */
public class Boj9098 {

    private static final String NEW_LINE = "\n";
    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;
            init(n);

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                int one = Integer.parseInt(st.nextToken());

                while(k-- > 1) {
                    int other = Integer.parseInt(st.nextToken());
                    merged(one, other);
                }
            }

            sb.append(-parent[find(0)]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static void init(int n) {
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }
}
