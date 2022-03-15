package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15782번: Calculate! 2
 *
 * @see https://www.acmicpc.net/problem/15782/
 *
 */
public class Boj15782 {
    private static ArrayList<Integer>[] link;
    private static long[] tree;
    private static long[] lazy;
    private static int[] s, e;

    private static int N, S = 1;
    private static int count = -1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        int loop = N - 1;
        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            link[from].add(to);
            link[to].add(from);
        }

        dfs(0);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int value = Integer.parseInt(st.nextToken());
            update(s[i], s[i], value, 1, 0, N - 1);
        }

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;

            if(cmd == 1){
                sb.append(xor(s[x], e[x], 1, 0, N - 1)).append(NEW_LINE);
            }
            else{
                long y = Long.parseLong(st.nextToken());
                update(s[x], e[x], y, 1, 0, N - 1);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S <= N) S <<= 1;

        tree = new long[S * 2];
        lazy = new long[S * 2];
        s = new int[N];
        e = new int[N];

        link = new ArrayList[N];

        for(int i = 0; i < N; i++){
            link[i] = new ArrayList<>();
            s[i] = -1;
        }
    }

    private static void dfs(int current){
        s[current] = ++count;

        for(int next: link[current]){
            if(s[next] != -1) continue;
            dfs(next);
        }

        e[current] = count;
    }

    private static int[] makeSon(int node){
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void propagation(int node, int start, int end){
        if (lazy[node] == 0) return;

        if(start != end){
            int[] son = makeSon(node);

            lazy[son[0]] ^= lazy[node];         // push lazy
            lazy[son[1]] ^= lazy[node];
        }

        if((end - start) % 2 == 0) tree[node] ^= lazy[node];        // need parenthesis!!
        lazy[node] = 0;
    }

    private static void update(int left, int right, long val, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return;
        if(left <= start && end <= right){
            lazy[node] ^= val;
            propagation(node, start, end);

            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        update(left, right, val, son[0], start, mid);
        update(left, right, val, son[1], mid + 1, end);

        tree[node] = tree[son[0]] ^ tree[son[1]];               // update
    }

    private static long xor(int left, int right, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        return xor(left, right, son[0], start, mid) ^ xor(left, right, son[1], mid + 1, end);
    }
}
