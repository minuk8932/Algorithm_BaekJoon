package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2820번: 자동차 공장
 *
 * @see https://www.acmicpc.net/problem/2820
 *
 */
public class Boj2820 {
    private static ArrayList<Integer>[] link;
    private static long[] tree;
    private static long[] lazy;
    private static int[] start, end;

    private static int N, S = 1;
    private static int count = -1;

    private static final char PAY = 'p';
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        long[] cost = new long[N];
        cost[0] = Long.parseLong(br.readLine());

        for(int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long pay = Long.parseLong(st.nextToken());
            int node = Integer.parseInt(st.nextToken()) - 1;

            link[node].add(i);
            cost[i] = pay;
        }

        dfs(0);
        for(int i = 0; i < N; i++){             // cost positioning in tree
            update(start[i], start[i], cost[i], 1, 0, N - 1);
        }

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken()) - 1;

            if(cmd == PAY){
                long x = Long.parseLong(st.nextToken());
                update(start[a] + 1, end[a], x, 1, 0, N - 1);
            }
            else{
                sb.append(sum(start[a], start[a], 1, 0, N - 1)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S <= N) S <<= 1;

        tree = new long[S * 2];
        lazy = new long[S * 2];
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

    private static void propagation(int node, int s, int e){
        if(lazy[node] == 0) return;

        if(s != e){
            int[] son = makeSon(node);

            lazy[son[0]] += lazy[node];             // push lazy
            lazy[son[1]] += lazy[node];
        }

        tree[node] += lazy[node] * (e - s + 1);
        lazy[node] = 0;
    }

    private static void update(int left, int right, long val, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return;
        if(left <= start && end <= right) {         // in range
            lazy[node] += val;
            propagation(node, start, end);

            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        update(left, right, val, son[0], start, mid);
        update(left, right, val, son[1], mid + 1, end);

        tree[node] = tree[son[0]] + tree[son[1]];   // total update
    }

    private static long sum(int left, int right, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];        // sum

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        return sum(left, right, son[0], start, mid) + sum(left, right, son[1], mid + 1, end);
    }
}
