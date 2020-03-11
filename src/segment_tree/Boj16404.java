package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16404번: 주식회사 승범이네
 *
 * @see https://www.acmicpc.net/problem/16404/
 *
 */
public class Boj16404 {
    private static ArrayList<Integer>[] link;
    private static int[] tree;
    private static int[] lazy;
    private static int[] s, e;

    private static int N, S = 1;
    private static int count = 0;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int node = Integer.parseInt(st.nextToken());
            if(node == -1) continue;
            link[node].add(i);
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                int w = Integer.parseInt(st.nextToken());
                add(s[i], e[i] - 1, w, 1, 0, N);
            }
            else {
                sb.append(sum(s[i], s[i], 1, 0, N)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S <= N) S <<= 1;

        tree = new int[S * 2];
        lazy = new int[S * 2];
        link = new ArrayList[N + 1];
        s = new int[N + 1];
        e = new int[N + 1];

        for(int i = 0; i <= N; i++){
            link[i]= new ArrayList();
        }
    }

    private static void dfs(int current){
        s[current] = count++;

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

        if(s != e){                                 // lazy push
            int[] son = makeSon(node);
            lazy[son[0]] += lazy[node];
            lazy[son[1]] += lazy[node];
        }

        tree[node] += lazy[node] * (e - s + 1);     // update parent with lazy
        lazy[node] = 0;
    }

    private static void add(int s, int e, int k, int node, int ns, int ne){
        propagation(node, ns, ne);

        if(e < ns || ne < s) return;
        if(s <= ns && ne <= e) {
            lazy[node] += k;                        // in range lazy saved
            propagation(node, ns, ne);
            return;
        }

        int mid = (ns + ne) / 2;
        int[] son = makeSon(node);

        add(s, e, k, son[0], ns, mid);              // find matched range
        add(s, e, k, son[1], mid + 1, ne);

        tree[node] = tree[son[0]] + tree[son[1]];   // update with son's
    }

    private static int sum(int s, int e, int node, int ns, int ne){
        propagation(node, ns, ne);

        if(e < ns || ne < s) return 0;
        if(s <= ns && ne <= e) return tree[node];           // ns == ne

        int mid = (ns + ne) / 2;
        int[] son = makeSon(node);

        return sum(s, e, son[0], ns, mid) + sum(s, e, son[1], mid + 1, ne);
    }
}
