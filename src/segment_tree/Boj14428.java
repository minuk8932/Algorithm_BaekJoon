package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14428번: 수열과 쿼리 16
 *
 * @see https://www.acmicpc.net/problem/14428/
 *
 */
public class Boj14428 {
    private static Tree[] tree;
    private static int N, S = 1;

    private static final int INF = 2_000_000_000;
    private static final String NEW_LINE = "\n";

    private static class Tree {
        int value;
        int index;

        public Tree(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = S; i < S + N; i++) {
            tree[i] = new Tree(i - S, Integer.parseInt(st.nextToken()));
        }

        for(int i = S - 1; i > 0; i--) {
            int[] son = makeSon(i);
            if(tree[son[0]].value < tree[son[1]].value) tree[i] = tree[son[0]];
            else if(tree[son[0]].value > tree[son[1]].value) tree[i] = tree[son[1]];
            else tree[i] = new Tree(Math.min(tree[son[1]].index, tree[son[0]].index), tree[son[0]].value);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                update(i, i, j, 1, 0, S - 1);
            }
            else {
                sb.append(getMin(i, j - 1, 1, 0, S - 1).index + 1).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init () {
        while(N >= S) S <<= 1;
        tree = new Tree[S * 2];

        for(int i = 0; i < tree.length; i++) {
            tree[i] = new Tree(INF, INF);
        }
    }

    private static int[] makeSon (int node) {
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void update (int left, int right, int value, int node, int start, int end) {
        if(right < start || end < left) return;

        if(left <= start && end <= right) {
            tree[node] = new Tree(left, value);             // caution!!
            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        update(left, right, value, son[0], start, mid);
        update(left, right, value, son[1], mid + 1, end);

        if(tree[son[0]].value < tree[son[1]].value) tree[node] = tree[son[0]];          // make min
        else if(tree[son[0]].value > tree[son[1]].value) tree[node] = tree[son[1]];
        else tree[node] = new Tree(Math.min(tree[son[1]].index, tree[son[0]].index), tree[son[1]].value);
    }

    private static Tree getMin (int left, int right, int node, int start, int end) {
        if(right < start || end < left) return new Tree(INF, INF);
        if(left <= start && end <= right) return tree[node];

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        Tree[] trees = {getMin(left, right, son[0], start, mid), getMin(left, right, son[1], mid + 1, end)};

        if(trees[0].value > trees[1].value) return trees[1];                              // get min
        else if(trees[1].value > trees[0].value) return trees[0];
        else return new Tree(Math.min(trees[0].index, trees[1].index), trees[0].value);
    }
}
