package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18437번: 회사 문화 5
 *
 * @see https://www.acmicpc.net/problem/18437
 *
 */
public class Boj18437 {
    private static ArrayList<Integer>[] link;
    private static int[] tree;
    private static int[] lazy;

    private static int[] start, end;
    private static int N, S = 1;
    private static int count = 0;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int val = Integer.parseInt(st.nextToken());
            link[val].add(i);
            tree[i + S] = 1;
        }

        for (int i = S - 1; i >= 1; i--) {
            int[] son = makeSon(i);
            tree[i] = tree[son[0]] + tree[son[1]];
        }

        recursion(0);
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());

            if(cmd == 3) {
                sb.append(sum(start[i] + 1, end[i], 1, 0, S - 1)).append(NEW_LINE);
            }

            if(start[i] == end[i]) continue;

            if(cmd == 1) update(start[i] + 1, end[i], 1, 1, 0, S - 1);
            if(cmd == 2) update(start[i] + 1, end[i], -1, 1, 0, S - 1);
        }

        System.out.print(sb.toString());
    }

    private static void init(){
        while(S <= N) {
            S <<= 1;
        }

        int shift = S << 1;
        tree = new int[shift + 1];
        lazy = new int[shift + 1];
        start = new int[N + 1];
        end = new int[N + 1];

        link = new ArrayList[N + 1];
        for(int i = 0; i < link.length; i++){
            link[i] = new ArrayList<>();
        }
    }

    /**
     *
     * Euler Tour Technique
     *
     * @param current
     */
    private static void recursion(int current){
        start[current] = count++;

        for(int next: link[current]){
            recursion(next);
        }

        end[current] = count - 1;
    }

    private static int[] makeSon(int node){
        int son = node << 1;
        return new int[]{son, ++son};
    }

    private static void propagation(int node, int start, int end){
        if(lazy[node] == 0) return;

        if(node < S){
            int[] son = makeSon(node);
            lazy[son[0]] = lazy[node];
            lazy[son[1]] = lazy[node];
        }

        tree[node] = lazy[node] == 1 ? (end - start + 1) : 0;
        lazy[node] = 0;
    }

    private static void update(int left, int right, int val, int node, int start, int end){
        propagation(node, start, end);

        if (right < start || end < left) return;
        if (left <= start && end <= right) {
            lazy[node] = val;
            propagation(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        int[] son = makeSon(node);

        update(left, right, val, son[0], start, mid);
        update(left, right, val, son[1], mid + 1, end);

        tree[node] = tree[son[0]] + tree[son[1]];
    }

    private static int sum(int left, int right, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];

        int[] son = makeSon(node);
        int mid = (start + end) >> 1;

        return sum(left, right, son[0], start, mid) + sum(left, right, son[1], mid + 1, end);
    }
}
