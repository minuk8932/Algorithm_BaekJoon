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
    private static final int SIZE = 1 << 21;

    private static int S;

    private static long[] tree = new long[SIZE];
    private static long[] lazy = new long[SIZE];
    private static long[] min = new long[SIZE];

    private static final String SUM = "S";
    private static final String MIN = "M";
    private static final String PUT = "P";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            sectionUpdate(i, i, Integer.parseInt(st.nextToken()), 1, 0, S - 1);
        }

        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case SUM:
                    sb.append(sectionSum(A, B, 1, 0, S - 1)).append(NEW_LINE);
                    break;

                case MIN:
                    sb.append(sectionMin(A, B, 1, 0, S - 1)).append(NEW_LINE);
                    break;

                case PUT:
                    long C = Long.parseLong(st.nextToken());
                    sectionUpdate(A, B, C, 1, 0, S - 1);
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    private static long sectionMin(int inStart, int inEnd, int node, int pStart, int pEnd) {
        propagation(node, pStart, pEnd);

        if(inEnd < pStart || pEnd < inStart) return INF;
        if(inStart <= pStart && pEnd <= inEnd) return min[node];

        int mid = (pStart + pEnd) >> 1;
        int son = node << 1;
        return Math.min(sectionMin(inStart, inEnd, son, pStart, mid),
            sectionMin(inStart, inEnd, son + 1, mid + 1, pEnd));
    }

    private static long sectionSum(int inStart, int inEnd, int node, int pStart, int pEnd) {
        propagation(node, pStart, pEnd);

        if(inEnd < pStart || pEnd < inStart) return 0L;
        if(inStart <= pStart && pEnd <= inEnd) return tree[node];

        int mid = (pStart + pEnd) >> 1;
        int son = node << 1;
        return sectionSum(inStart, inEnd, son, pStart, mid)
            + sectionSum(inStart, inEnd, son + 1, mid + 1, pEnd);
    }

    private static void sectionUpdate(int inStart, int inEnd, long value, int node, int pStart, int pEnd){
        propagation(node, pStart, pEnd);

        if(inEnd < pStart || pEnd < inStart) return;
        if(inStart <= pStart && pEnd <= inEnd){
            if (pStart != pEnd) {
                lazy[node * 2] += value;
                lazy[node * 2 + 1] += value;
            }

            min[node] += value;
            tree[node] += value * (pEnd - pStart + 1);

            return;
        }

        int son = node << 1;
        int mid = (pStart + pEnd) >> 1;

        sectionUpdate(inStart, inEnd, value, son, pStart, mid);
        sectionUpdate(inStart, inEnd, value, son + 1, mid + 1, pEnd);

        tree[node] = tree[son] + tree[son + 1];
        min[node] = Math.min(min[son], min[son + 1]);
    }

    private static void propagation(int node, int pStart, int pEnd) {
        if(lazy[node] == 0) return;

        if(node < S) {
            int son = node << 1;
            lazy[son] += lazy[node];
            lazy[son + 1] += lazy[node];
        }

        tree[node] += lazy[node] * (pEnd - pStart + 1);
        min[node] += lazy[node];
        lazy[node] = 0;
    }

    private static void init() {
        S = SIZE >> 1;
    }
}
