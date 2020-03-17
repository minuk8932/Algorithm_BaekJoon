package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16975: 수열과 쿼리 21
 *
 * @see https://www.acmicpc.net/problem/16975/
 *
 */
public class Boj16975 {
    private static long[] tree;
    private static long[] lazy;

    private static int N, S = 1;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = S; i < N + S; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = S - 1; i > 0; i--){
            int[] son = makeSon(i);
            tree[i] = tree[son[0]] + tree[son[1]];
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken()) - 1;

            if(cmd == 1){
                int j = Integer.parseInt(st.nextToken()) - 1;
                int k = Integer.parseInt(st.nextToken());

                update(i, j, k, 1, 0, S - 1);
            }
            else{
                sb.append(sum(i, i, 1, 0, S - 1)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S <= N) S <<= 1;

        tree = new long[S * 2];
        lazy = new long[S * 2];
    }

    private static int[] makeSon(int node){
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void propagation(int node, int start, int end){
        if(lazy[node] == 0) return;

        if(start != end){
            int[] son = makeSon(node);                      // push lazy

            lazy[son[0]] += lazy[node];
            lazy[son[1]] += lazy[node];
        }

        tree[node] += lazy[node] * (end - start + 1);       // add values
        lazy[node] = 0;
    }

    private static void update(int left, int right, int val, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return;
        if(left <= start && end <= right){
            lazy[node] += val;
            propagation(node, start, end);

            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        update(left, right, val, son[0], start, mid);
        update(left, right, val, son[1], mid + 1, end);

        tree[node] = tree[son[0]] + tree[son[1]];               // update
    }

    private static long sum(int left, int right, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        return sum(left, right, son[0], start, mid) + sum(left, right, son[1], mid + 1, end);       // make section sum
    }
}
