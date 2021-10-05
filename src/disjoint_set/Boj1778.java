package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1778번: Ubiquitous Religions
 *
 * @see https://www.acmicpc.net/problem/1778
 *
 */
public class Boj1778 {

    private static int[] parent;

    private static final String CASE = "Case ";
    private static final String COLON = ": ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test = 1;

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            parent = new int[n];
            Arrays.fill(parent, -1);

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;

                merge(x, y);
            }

            sb.append(CASE).append(test++).append(COLON).append(groups()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int groups() {
        int count = 0;

        for(int p: parent) {
            if(p >= 0) continue;
            count++;
        }

        return count;
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge (int x, int y) {
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
