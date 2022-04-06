package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3002번: 아날로그 다이얼
 *
 * @see https://www.acmicpc.net/problem/3002
 *
 */
public class Boj3002 {

    private static final String NEW_LINE = "\n";
    private static final int DIALOG_MAX = 10;

    private static int[][] tree;
    private static int[] lazy;
    private static int[] accumulate = new int[DIALOG_MAX];

    private static int S = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        String input = br.readLine();
        for(int i = S; i < N + S; i++) {
            int dialog = input.charAt(i - S) - '0';
            tree[dialog][i] = 1;
        }

        for(int i = S - 1; i > 0; i--) {
            int[] son = getSon(i);

            for(int value = 0; value < DIALOG_MAX; value++) {
                tree[value][i] = tree[value][son[0]] + tree[value][son[1]];
            }
        }

        StringBuilder sb = new StringBuilder();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(sum(s, e, 1, 1, S - 1)).append(NEW_LINE);
            update(s, e, 1, 1, S - 1);
        }

        System.out.println(sb.toString());
    }

    private static void propagation(int node) {
        if(lazy[node] == 0) return;

        if(node < S) {
            int[] son = getSon(node);
            lazy[son[0]] += lazy[node];
            lazy[son[1]] += lazy[node];
        }

        for (int value = 0; value < DIALOG_MAX; value++) {
            accumulate[(value + lazy[node]) % DIALOG_MAX] = tree[value][node];
        }

        for (int value = 0; value < DIALOG_MAX; value++) {
            tree[value][node] = accumulate[value];
        }

        lazy[node] = 0;
    }

    private static int sum(int left, int right, int node, int currentStart, int currentEnd) {
        propagation(node);

        if(left > currentEnd || right < currentStart) return 0;
        if(left <= currentStart && currentEnd <= right){
            int answer = 0;

            for (int value = 1; value < DIALOG_MAX; value++) {
                answer += tree[value][node] * value;
            }

            return answer;
        }

        int mid = (currentStart + currentEnd) >> 1;
        int[] son = getSon(node);

        return sum(left, right, son[0], currentStart, mid)
            + sum(left, right, son[1], mid + 1, currentEnd);
    }

    private static void update(int left, int right, int node, int currentStart, int currentEnd) {

        propagation(node);

        if(left > currentEnd || right < currentStart) return;
        if(left <= currentStart && currentEnd <= right){
            lazy[node]++;
            propagation(node);

            return;
        }

        int mid = (currentStart + currentEnd) >> 1;
        int[] son = getSon(node);

        update(left, right, son[0], currentStart, mid);
        update(left, right, son[1], mid + 1, currentEnd);

        for (int value = 0; value < DIALOG_MAX; value++) {
            tree[value][node] = tree[value][son[0]] + tree[value][son[1]];
        }
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
        tree = new int[DIALOG_MAX][shift];
        lazy = new int[shift];
    }
}
