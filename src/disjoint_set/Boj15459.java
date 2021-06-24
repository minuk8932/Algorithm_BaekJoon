package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15459번: Haybale Feast
 *
 * @see https://www.acmicpc.net/problem/1549
 *
 */
public class Boj15459 {

    private static int N;
    private static long M;
    private static long[] parent;
    private static Plate[] plates;

    private static class Plate implements Comparable<Plate>{
        int index;
        long spicy;

        public Plate(int index, long spicy) {
            this.index = index;
            this.spicy = spicy;
        }

        @Override
        public int compareTo(Plate p) {
            return this.spicy < p.spicy ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        plates = new Plate[N];
        parent = new long[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            parent[i] = -Long.parseLong(st.nextToken());
            plates[i] = new Plate(i, Long.parseLong(st.nextToken()));
        }

        Arrays.sort(plates);
        System.out.println(findList());
    }

    /**
     *
     * Find list
     *
     * line 72 ~ 74: search section & merge by set
     *
     * @return
     */
    private static long findList() {
        boolean[] check = new boolean[N];
        long result = 0;

        for(int i = 0; i < N; i++) {
            int idx = plates[i].index;
            long spicy = plates[i].spicy;
            check[idx] = true;

            if(idx > 0 && check[idx - 1]) merged(idx,idx - 1);
            if(idx < N - 1 && check[idx + 1]) merged(idx, idx + 1);
            if(-parent[find(idx)] < M) continue;

            result = spicy;
            break;
        }

        return result;
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return (int) (parent[x] = find((int) parent[x]));
    }

    private static void merged(int x, int y) {
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
