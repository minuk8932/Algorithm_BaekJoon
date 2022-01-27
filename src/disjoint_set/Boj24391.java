package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24391번: 귀찮은 해강이
 *
 * @see https://www.acmicpc.net/problem/24391
 *
 */
public class Boj24391 {

    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int building1 = Integer.parseInt(st.nextToken()) - 1;
            int building2 = Integer.parseInt(st.nextToken()) - 1;

            merge(building1, building2);
        }

        System.out.println(lecture(br.readLine()));
    }

    private static int lecture(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int current = Integer.parseInt(st.nextToken()) - 1;
        int count = 0;

        while(st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken()) - 1;

            int x = find(current);
            int y = find(next);

            count += x == y ? 0: 1;
            current = next;
        }

        return count;
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

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

    private static void init(int n) {
        parent = new int[n];
        for(int i = 0; i < parent.length; i++){
            parent[i] = -1;
        }
    }
}
