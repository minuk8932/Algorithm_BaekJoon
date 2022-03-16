package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11962번: Counting haybales
 *
 * @see https://www.amicpc.net/problem/11962
 *
 */
public class Boj11962 {

    private static final long INF = 1_000_000_000_000L;

    private static int SIZE;
    private static int S;

    private static long[] tree;
    private static long[] lazy;
    private static long[] min;

    private static final String SUM = "S";
    private static final String MIN = "M";
    private static final String PUT = "P";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init(N);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int index = S + i;
            tree[index] = Integer.parseInt(st.nextToken());
            min[index] = tree[index];
        }

        accumulation();
        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case SUM:
                    sb.append(sectionSum(A, B, 1, 1, S)).append(NEW_LINE);
                    break;

                case MIN:
                    sb.append(sectionMin(A, B, 1, 1, S)).append(NEW_LINE);
                    break;

                case PUT:
                    long C = Long.parseLong(st.nextToken());
                    sectionUpdate(A, B, C, 1, 1, S);
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    private static long sectionMin(int start, int end, int node, int currentStart, int currentEnd) {
        propagation(node, currentStart, currentEnd);

        if(end < currentStart || currentEnd < start) return INF;
        if(start <= currentStart && currentEnd <= end) return min[node];

        int mid = (currentStart + currentEnd) >> 1;
        int son = node << 1;
        return Math.min(sectionMin(start, end, son, currentStart, mid),
            sectionMin(start, end, son + 1, mid + 1, currentEnd));
    }

    private static long sectionSum(int start, int end, int node, int currentStart, int currentEnd) {
        propagation(node, currentStart, currentEnd);

        if(end < currentStart || currentEnd < start) return 0L;
        if(start <= currentStart && currentEnd <= end) return tree[node];

        int mid = (currentStart + currentEnd) >> 1;
        int son = node << 1;
        return sectionSum(start, end, son, currentStart, mid)
            + sectionSum(start, end, son + 1, mid + 1, currentEnd);
    }

    private static void sectionUpdate(int start, int end, long value, int node
        , int currentStart, int currentEnd){
        propagation(node, currentStart, currentEnd);

        if(end < currentStart || currentEnd < start) return;
        if(start <= currentStart && currentEnd <= end){
            lazy[node] += value;
            propagation(node, currentStart, currentEnd);
            return;
        }

        int son = node << 1;
        int mid = (currentStart + currentEnd) >> 1;

        sectionUpdate(start, end, value, son, currentStart, mid);
        sectionUpdate(start, end, value, son + 1, mid + 1, currentEnd);

        tree[node] = tree[son] + tree[son + 1];
        min[node] = Math.min(min[son], min[son + 1]);
    }

    private static void propagation(int node, int currentStart, int currentEnd) {
        if(lazy[node] == 0) return;

        if(node < S) {
            int son = node << 1;
            lazy[son] += lazy[node];
            lazy[son + 1] += lazy[node];
        }

        tree[node] += lazy[node] * (currentEnd - currentStart + 1);
        min[node] += lazy[node];
        lazy[node] = 0;
    }

    private static void accumulation() {
        for(int i = S - 1; i > 0; i--) {
            int son = i << 1;
            tree[i] = tree[son] + tree[son + 1];
            min[i] = Math.min(min[son], min[son + 1]);
        }
    }

    private static void init(int n) {
        SIZE = 1;
        while(SIZE <= n) {
            SIZE <<= 1;
        }

        S = SIZE;
        SIZE <<= 1;

        tree = new long[SIZE];
        min = new long[SIZE];
        lazy = new long[SIZE];
    }
}
