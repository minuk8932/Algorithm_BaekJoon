package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14288번: 내리 갈굼 4
 *
 * @see https://www.acmicpc.net/problem/14288/
 *
 */
public class Boj14288 {
    private static ArrayList<Integer>[] link;
    private static int[][] tree;
    private static int[][] lazy;

    private static int[] start, end;
    private static int N, S = 1;
    private static int count = -1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int val = Integer.parseInt(st.nextToken());
            if(val == -1) continue;

            link[--val].add(i);
        }

        dfs(0);

        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 3){
                flag = !flag;
                continue;
            }

            int i = Integer.parseInt(st.nextToken()) - 1;

            if(cmd == 1){
                int w = Integer.parseInt(st.nextToken());
                if(flag) add(start[i], start[i], w, 1, 0, N - 1, 0);        // back
                else add(start[i], end[i], w, 1, 0, N - 1, 1);              // for
            }
            else {
                sb.append(sum(start[i], end[i], 1, 0, N - 1, 0)             // for + back cases
                + sum(start[i], start[i], 1, 0, N - 1, 1)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S <= N) S <<= 1;

        tree = new int[S * 2][2];           // divide to For, Back
        lazy = new int[S * 2][2];
        start = new int[N];
        end = new int[N];

        link = new ArrayList[N];
        for(int i = 0; i < N; i++){
            link[i] = new ArrayList<>();
        }
    }

    private static void dfs(int current){
        start[current] = ++count;

        for(int next: link[current]){
            dfs(next);
        }

        end[current] = count;
    }

    private static int[] makeSon(int node){
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void propagation(int node, int start, int end, int idx){
        if(lazy[node][idx] == 0) return;

        if(start != end){
            int[] son = makeSon(node);
            lazy[son[0]][idx] += lazy[node][idx];
            lazy[son[1]][idx] += lazy[node][idx];
        }

        tree[node][idx] += lazy[node][idx] * (end - start + 1);
        lazy[node][idx] = 0;
    }

    private static void add(int left, int right, int val, int node, int start, int end, int idx){
        propagation(node, start, end, idx);

        if(right < start || end < left) return;
        if(left <= start && end <= right) {
            lazy[node][idx] += val;
            propagation(node, start, end, idx);

            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        add(left, right, val, son[0], start, mid, idx);
        add(left, right, val, son[1], mid + 1, end, idx);

        tree[node][idx] = tree[son[0]][idx] + tree[son[1]][idx];
    }

    private static int sum(int left, int right, int node, int start, int end, int idx){
        propagation(node, start, end, idx);

        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node][idx];

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        return sum(left, right, son[0], start, mid, idx) + sum(left, right, son[1], mid + 1, end, idx);
    }
}
