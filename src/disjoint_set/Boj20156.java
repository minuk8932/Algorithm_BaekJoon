package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20156번: 기왕 이렇게 된 거 암기왕이 되어라
 *
 * @see https://www.acmicpc.net/problem/20156
 *
 */
public class Boj20156 {
    private static int[] parent;
    private static int[] mento;
    private static int[] count;

    private static Set<Integer> cutter = new HashSet<>();

    private static final String GROUP = "Same Same;\n";
    private static final String DIFF = "No;\n";

    private static class OfflineQuery implements Comparable<OfflineQuery>{
        int idx;
        int a;
        int b;
        int c;

        public OfflineQuery(int idx, int a, int b, int c) {
            this.idx = idx;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(OfflineQuery oq) {
            return oq.a - this.a;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N];
        init();

        st = new StringTokenizer(br.readLine());
        mento = new int[N];
        for(int i = 0; i < N; i++) {
            mento[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        List<Integer> round = new ArrayList<>();
        count = new int[100_001];

        while(M-- > 0) {
            int menti = Integer.parseInt(br.readLine()) - 1;
            count[menti]++;
            round.add(menti);
            cutter.add(menti);          // round query save
        }

        PriorityQueue<OfflineQuery> query = new PriorityQueue<>();
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            query.add(new OfflineQuery(i, Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }

        System.out.println(linkedIn(round, query));
    }

    private static String linkedIn(List<Integer> round, PriorityQueue<OfflineQuery> query) {
        boolean[] result = new boolean[query.size()];
        int size = round.size();

        for(int menti = 0; menti < parent.length; menti++) {
            if(cutter.contains(menti) || mento[menti] < 0) continue;
            merge(mento[menti], menti);             // not cut
        }

        int idx = size - 1;

        while(!query.isEmpty()) {
            OfflineQuery oq = query.poll();

            while(oq.a < size){                     // matching current round
                int menti = round.get(idx--);

                if(count[menti] == 1){
                    int mentor = mento[menti];
                    if(mentor >= 0) merge(menti, mentor);
                }

                size--;
                count[menti]--;
            }

            result[oq.idx] = find(oq.c) == find(oq.b);
        }

        StringBuilder sb = new StringBuilder();
        for(boolean r: result) {
            sb.append(r ? GROUP: DIFF);
        }

        return sb.toString();
    }

    private static void init() {
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        else return parent[x] = find(parent[x]);
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
