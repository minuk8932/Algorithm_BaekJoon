import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11962 {

    private static final long INF = 1_000_000_000_000L;
    private static final int SIZE = 1 << 21;

    private static int S;

    private static long[] tree = new long[SIZE];
    private static long[] lazy = new long[SIZE];

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
        for(int i = 0; i < N; i++) {
            tree[S + i] = Integer.parseInt(st.nextToken());
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
                    sb.append(sectionSum(A - 1, B, 1, 0, S)).append(NEW_LINE);
                    break;

                case MIN:
                    sb.append(sectionMin(A - 1, B, 1, 0, S)).append(NEW_LINE);
                    break;

                case PUT:
                    int C = Integer.parseInt(st.nextToken());
                    sectionAdd(A - 1, B, C, 1, 0, S);
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    private static long sectionMin(int inStart, int inEnd, int node, int pStart, int pEnd) {
        propagation(node, pStart, pEnd);

        if(inEnd <= pStart || pEnd <= inStart) return INF;
        if(inStart <= pStart && pEnd <= inEnd) return min[node];

        int mid = (pStart + pEnd) >> 1;
        int son = node << 1;
        return Math.min(sectionMin(inStart, inEnd, son, pStart, mid),
            sectionMin(inStart, inEnd, son + 1, mid, pEnd));
    }

    private static long sectionSum(int inStart, int inEnd, int node, int pStart, int pEnd) {
        propagation(node, pStart, pEnd);

        if(inEnd <= pStart || pEnd <= inStart) return 0;
        if(inStart <= pStart && pEnd <= inEnd) return tree[node];

        int mid = (pStart + pEnd) >> 1;
        int son = node << 1;
        return sectionSum(inStart, inEnd, son, pStart, mid)
            + sectionSum(inStart, inEnd, son + 1, mid, pEnd);
    }

    private static void sectionAdd(int inStart, int inEnd, int value, int node, int pStart, int pEnd){
        propagation(node, pStart, pEnd);

        if(inEnd <= pStart || pEnd <= inStart) return;
        if(inStart <= pStart && pEnd <= inEnd){
            lazy[node] += value;
            propagation(node, pStart, pEnd);
            return;
        }

        int son = node << 1;
        int mid = (pStart + pEnd) >> 1;

        sectionAdd(inStart, inEnd, value, son, pStart, mid);
        sectionAdd(inStart, inEnd, value, son + 1, mid, pEnd);

        tree[node] = tree[son] + tree[son + 1];
    }

    private static void propagation(int node, int pStart, int pEnd) {
        if(lazy[node] == 0) return;

        if(node < S) {
            int son = node << 1;
            lazy[son] += lazy[node];
            lazy[son + 1] += lazy[node];
        }

        tree[node] += lazy[node] * (pEnd - pStart);
//        min[node] = Math.min(min[node], tree[node]);
        lazy[node] = 0;
    }

    private static void accumulation() {
        for(int i = S - 1; i > 0; i--) {
            int son = i << 1;
            tree[i] = tree[son] + tree[son + 1];
        }
    }

    private static void init() {
        S = SIZE >> 1;
//        Arrays.fill(min, INF);
    }
}
