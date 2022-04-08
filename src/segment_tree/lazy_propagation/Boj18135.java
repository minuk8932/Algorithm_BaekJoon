package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18135번: 겨울 나기
 *
 * @see https://www.acmicpc.net/problem/18135
 *
 */
public class Boj18135 {

    private static long[] tree;
    private static long[] lazy;
    private static Map<Integer, Integer> mapper = new HashMap<>();

    private static int S = 1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(M);

        for(int i = S; i < M + S; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            for(int x = a; x <= b; x++) {
                mapper.put(x, i - S + 1);
            }

            tree[i] = c;
        }

        for(int i = S - 1; i > 0; i--) {
            int[] son = getSon(i);
            tree[i] = tree[son[0]] + tree[son[1]];
        }

        StringBuilder sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(cmd == 0) break;

            int s = mapper.get(x);
            int e = mapper.get(y);

            if(cmd == 1) {
                long answer;
                if(x <= y) {
                    answer = summary(s, e, 1, 1, S - 1);
                }
                else {
                    if(s == e) e--;

                    answer = summary(s, M, 1, 1, S - 1)
                        + summary(1, e, 1, 1, S - 1);
                }

                sb.append(answer).append(NEW_LINE);
            }
            else {
                long z = Long.parseLong(st.nextToken());

                if(x <= y) {
                    update(s, e, z, 1, 1, S - 1);
                }
                else {
                    if(s == e) e--;

                    update(s, M, z, 1, 1, S - 1);
                    update(1, e, z, 1, 1, S - 1);
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static long summary(int left, int right, int node, int currentStart, int currentEnd) {
        propagation(node, currentStart, currentEnd);

        if(left > currentEnd || right < currentStart) return 0;
        if(left <= currentStart && currentEnd <= right) return tree[node];

        int mid = (currentStart + currentEnd) >> 1;
        int[] son = getSon(node);

        return summary(left, right, son[0], currentStart, mid) +
            summary(left, right, son[1], mid + 1, currentEnd);
    }

    private static void update(int left, int right, long value
        , int node, int currentStart, int currentEnd) {

        propagation(node, currentStart, currentEnd);

        if(left > currentEnd || right < currentStart) return;
        if(left <= currentStart && currentEnd <= right) {
            lazy[node] += value;
            propagation(node, currentStart, currentEnd);

            return;
        }

        int mid = (currentStart + currentEnd) >> 1;
        int[] son = getSon(node);

        update(left, right, value, son[0], currentStart, mid);
        update(left, right, value, son[1], mid + 1, currentEnd);

        tree[node] = tree[son[0]] + tree[son[1]];
    }

    private static void propagation(int node, int currentStart, int currentEnd) {
        if(lazy[node] == 0) return;

        if(node < S) {
            int[] son = getSon(node);
            lazy[son[0]] += lazy[node];
            lazy[son[1]] += lazy[node];
        }

        tree[node] += lazy[node] * (currentEnd - currentStart + 1);
        lazy[node] = 0;
    }

    private static int[] getSon(int node) {
        int shift = node << 1;
        return new int[] {shift, ++shift};
    }

    private static void init(int n) {
        while(S <= n) {
            S <<= 1;
        }

        int shift = S << 1;
        tree = new long[shift];
        lazy = new long[shift];
    }
}
