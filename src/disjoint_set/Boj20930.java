package disjoint_set;

import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
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

    private static final String NEW_LINE = "\n";
    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);
        Pair<Integer>[] x = new Pair[N];
        Pair<Integer>[] y = new Pair[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            x[i] = new Pair.Builder(Math.min(x1, x2)
                , Math.max(x1, x2)).third(i)
                .build();

            y[i] = new Pair.Builder(Math.min(y1, y2)
                , Math.max(y1, y2)).third(i)
                .build();
        }

        sortAndSweeping(x);
        sortAndSweeping(y);

        StringBuilder sb = new StringBuilder();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;

            sb.append(find(p1) == find(p2) ? 1: 0).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    /**
     *
     * Sort and sweeping
     *
     * line 81 ~ 82: sweeping
     *
     * @param coors
     */
    private static void sortAndSweeping(Pair<Integer>[] coors) {
        Arrays.sort(coors, Comparator.comparingInt(Pair::getFirst));

        Pair<Integer> target = coors[0];
        for(int i = 1; i < coors.length; i++) {
            if(target.getSecond() >= coors[i].getFirst()) {
                merge(target.getThird(), coors[i].getThird());
            }

            if(target.getSecond() >= coors[i].getSecond()) continue;
            target = coors[i];
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
