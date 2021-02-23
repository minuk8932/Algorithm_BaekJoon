package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20930번: 우주 정거장
 *
 * @see https://www.acmicpc.net/problem/20930
 *
 */
public class Boj20930 {

    private static int[] parent;
    private static final String NEW_LINE = "\n";

    private static class Coordinate implements Comparable<Coordinate>{
        int idx;
        int p1;
        int p2;

        public Coordinate(int idx, int p1, int p2) {
            this.idx = idx;
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public int compareTo(Coordinate c) {
            return this.p1 - c.p1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);
        Coordinate[] x = new Coordinate[N];
        Coordinate[] y = new Coordinate[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            x[i] = new Coordinate(i, Math.min(x1, x2), Math.max(x1, x2));
            y[i] = new Coordinate(i, Math.min(y1, y2), Math.max(y1, y2));
        }

        setting(x);
        setting(y);

        StringBuilder sb = new StringBuilder();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;

            sb.append(find(p1) == find(p2) ? 1: 0).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Follow the problem, the line is represented by rectangle.
     *      For example if input is 'x1 y1 x2 y2'
     *      then, rectangle's left-bottom point is Math.min(x1, x2), Math.min(y1, y2)
     *      and, reectangle's right-top point is Math.max(x1, x2), Math.max(y1, y2).
     *
     *      then just sweep by ith & (i - 1)th index by merge.
     *
     * @param c
     */
    private static void setting(Coordinate[] c) {
        Arrays.sort(c);

        for(int i = 1; i < c.length; i++) {
            if(c[i].p1 > c[i - 1].p2 || c[i].p2 < c[i - 1].p1) continue;
            merge(c[i].idx, c[i - 1].idx);
        }
    }

    private static void init(int n) {
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
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
}
