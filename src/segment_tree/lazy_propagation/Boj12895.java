package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12895번: 화려한 마을
 *
 * @see https://www.acmicpc.net/problem/12895
 *
 */
public class Boj12895 {

    private static int[] tree;
    private static int[] lazy;

    private static int start = 1 << 20;

    private static final String NEW_LINE = "\n";
    private static final String COLORING = "C";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init(N);
        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int x = Math.min(a, b);
            int y = Math.max(a, b);

            if(cmd.equals(COLORING)) {
                int z = Integer.parseInt(st.nextToken()) - 1;
                updateSection(x, y, 1, 1 << z, 1, start);
            }
            else {
                int color = shiftSection(x, y, 1, 1, start);
                sb.append(colorCounting(color)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static int colorCounting(int color) {
        int count = 0;

        while(color > 0){
            count += (color & 1);
            color >>= 1;
        }

        return count;
    }

    private static int shiftSection(int left, int right, int node, int currentStart, int currentEnd) {
        propagation(node, currentStart, currentEnd);

        if(left > currentEnd || currentStart > right) return 0;
        if(left <= currentStart && currentEnd <= right) return tree[node];

        int mid = (currentEnd + currentStart) >> 1;
        int[] son = makeSon(node);

        return shiftSection(left, right, son[0], currentStart, mid) |
            shiftSection(left, right, son[1], mid + 1, currentEnd);
    }

    private static void updateSection(int left, int right, int node, int value
        , int currentStart, int currentEnd) {

        propagation(node, currentStart, currentEnd);
        if(left > currentEnd || currentStart > right) return;
        if(left <= currentStart && currentEnd <= right) {
            lazy[node] = value;
            propagation(node, currentStart, currentEnd);
            return;
        }

        int mid = (currentEnd + currentStart) >> 1;
        int[] son = makeSon(node);

        updateSection(left, right, son[0], value, currentStart, mid);
        updateSection(left, right, son[1], value, mid + 1, currentEnd);
        tree[node] = tree[son[0]] | tree[son[1]];
    }

    private static void propagation(int node, int currentStart, int currentEnd) {
        if (lazy[node] == 0) return;

        if(currentStart != currentEnd) {
            int[] son = makeSon(node);
            lazy[son[0]] = lazy[node];
            lazy[son[1]] = lazy[node];
        }

        tree[node] = lazy[node];
        lazy[node] = 0;
    }

    private static int[] makeSon(int node) {
        int son = node << 1;
        return new int[] {son, ++son};
    }

    private static void init(int n) {
        int size = start << 1;

        tree = new int[size];
        lazy = new int[size];

        for(int i = 0; i < n; i++) {
            int index = i + start;
            tree[index] = 1;
        }

        for(int i = start - 1; i > 1; i--) {
            tree[i] = 1;
        }
    }
}
