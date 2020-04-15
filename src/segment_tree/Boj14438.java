package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14438번: 수열과 쿼리 17
 *
 * @see https://www.acmicpc.net/problem/14438/
 *
 */
public class Boj14438 {
    private static int[] tree;
    private static int N, S = 1;

    private static final int INF = 2_000_000_000;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = S; i < S + N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = S - 1; i > 0; i--) {
            int[] son = makeSon(i);
            tree[i] = Math.min(tree[son[0]], tree[son[1]]);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                update(i, i, j, 1, 1, S);
            }
            else {
                sb.append(getMin(i, j, 1, 1, S)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init () {
        while(N >= S) S <<= 1;
        tree = new int[S * 2];

        Arrays.fill(tree, INF);
    }

    private static int[] makeSon (int node) {
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void update (int left, int right, int value, int node, int start, int end) {
        if(right < start || end < left) return;

        if(left <= start && end <= right) {
            tree[node] = value;                                     // update value;
            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        update(left, right, value, son[0], start, mid);
        update(left, right, value, son[1], mid + 1, end);

        tree[node] = Math.min(tree[son[0]], tree[son[1]]);          // update to min with input value
    }

    private static int getMin (int left, int right, int node, int start, int end) {
        if(right < start || end < left) return INF;

        if(left <= start && end <= right) return tree[node];

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        return Math.min(getMin(left, right, son[0], start, mid), getMin(left, right, son[1], mid + 1, end));
    }
}
