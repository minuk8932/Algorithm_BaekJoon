package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14268번: 내리 갈굼 2
 *
 * @see https://www.acmicpc.net/problem/14268/
 *
 */
public class Boj14268 {
    private static ArrayList<Integer>[] link;
    private static long[] lazy;
    private static long[] tree;

    private static int[] s, e;
    private static int N, S, count;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int node = Integer.parseInt(st.nextToken());
            if(node == -1) continue;

            link[node].add(i);
        }

        dfs(1);                         // making tree

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                int w = Integer.parseInt(st.nextToken());
                add(s[i], e[i], w, 1, 1, N);
            }
            else{
                sb.append(sum(s[i], s[i], 1, 1, N)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(int n){
        S = 1;
        while(S <= N) S <<= 1;

        tree = new long[S * 2];
        lazy = new long[S * 2];
        link = new ArrayList[n + 1];
        s = new int[n + 1];
        e = new int[n + 1];

        for(int i = 0; i <= n; i++){
            link[i] = new ArrayList<>();
        }
    }

    private static void dfs(int current){
        s[current] = ++count;

        for(int next: link[current]){
            dfs(next);
        }

        e[current] = count;
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

        tree[node] += lazy[node] * (e - s + 1);     // add terminal
        lazy[node] = 0;
    }

    private static void add(int s, int e, int k, int node, int ns, int ne){
        propagation(node, ns, ne);

        if(e < ns || ne < s) return;
        if(s <= ns && ne <= e){                     // is on range
            lazy[node] += k;

            propagation(node, ns, ne);
            return;
        }

        int mid = (ns + ne) / 2;
        int[] son = makeSon(node);

        add(s, e, k, son[0], ns, mid);
        add(s, e, k, son[1], mid + 1, ne);

        tree[node] = tree[son[0]] + tree[son[1]];   // update parent with updated cause of lazy
    }

    private static long sum(int s, int e, int node, int ns, int ne){
        propagation(node, ns, ne);

        if(e < ns || ne < s) return 0;
        if(s <= ns && ne <= e) return tree[node];               // ns == ne

        int mid = (ns + ne) / 2;
        int[] son = makeSon(node);

        return sum(s, e, son[0], ns, mid) + sum(s, e, son[1], mid + 1, ne);
    }
}
