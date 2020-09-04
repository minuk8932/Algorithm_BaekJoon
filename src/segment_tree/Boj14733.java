package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14733번: 행사장 대여 (Large)
 *
 * @see https://www.acmicpc.net/problem/14733
 *
 */
public class Boj14733 {
    private static int N, S = 1 << 20;
    private static long[] tree = new long[S * 2];
    private static long[] area = new long[S * 2];

    private static final int EXTEND = 50_000;

    private static class Festival implements Comparable<Festival> {
        int x;
        int y;
        int yEnd;
        int add;

        public Festival(int x, int y, int yEnd, int add) {
            this.x = x;
            this.y = y;
            this.yEnd = yEnd;
            this.add = add;
        }

        @Override
        public int compareTo(Festival f) {
            return this.x < f.x ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Festival[] f = new Festival[N * 2];

        for(int i = 0; i < f.length; i += 2) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + EXTEND;
            int y1 = Integer.parseInt(st.nextToken()) + EXTEND;
            int x2 = Integer.parseInt(st.nextToken()) + EXTEND;
            int y2 = Integer.parseInt(st.nextToken()) + EXTEND;

            f[i] = new Festival(x1, y1, y2, 1);                     // row, col decomposition
            f[i + 1] = new Festival(x2, y1, y2, -1);
        }

        long result = 0;
        int x = f[0].x;

        Arrays.sort(f);

        for (Festival fs : f) {
            result += tree[1] * (fs.x - x);                             // get area
            x = fs.x;

            update(1, S, 1, fs.y + 1, fs.yEnd, fs.add); // add area
        }

        System.out.println(result);
    }

    private static void update(int left, int right, int index, int start, int end, int value) {
        if (right < start || end < left) return;

        if (start <= left && right <= end) {
            area[index] += value;
            propagation(left, right, index);
            return;
        }

        int mid = (left + right) / 2;
        int[] son = makeSon(index);

        update(left, mid, son[0], start, end, value);
        update(mid + 1, right, son[1], start, end, value);

        propagation(left, right, index);
    }

    private static void propagation(int s, int e, int node) {
        if(area[node] > 0) {
            tree[node] = e - s + 1;
            return;
        }

        if(s != e){
            int[] son = makeSon(node);

            tree[node] = tree[son[0]];
            tree[node] += tree[son[1]];

            return;
        }

        tree[node] = 0;
    }

    private static int[] makeSon(int node){
        int son = node * 2;
        return new int[]{son, ++son};
    }
}
