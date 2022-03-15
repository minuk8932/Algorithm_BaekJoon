package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12844번: XOR
 *
 * @see https://www.acmicpc.net/problem/12844/
 *
 */
public class Boj12844 {
    private static final int INF = 1 << 21;
    private static final String NEW_LINE = "\n";
    private static int start = INF / 2;
    private static int[] tree = new int[INF];
    private static int[] lazy = new int[INF];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = start; i < start + n; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }

        init();

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());

        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int from = Math.min(a, b);
            int to = Math.max(a, b) + 1;

            if(cmd == 1){
                int c = Integer.parseInt(st.nextToken());
                xor(from, to, c, 1, 0, start);
            }
            else{
                sb.append(sectionXor(from, to, 1, 0, start)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        for(int i = start - 1; i > 0; i--) {
            int[] son = makeSon(i);
            tree[i] = tree[son[0]] ^ tree[son[1]];
        }
    }

    public static int[] makeSon(int node){
        return new int[]{node * 2, node * 2 + 1};
    }

    public static void propagation(int node, int s, int e){
        if(lazy[node] == 0) return;

        if(node < start){
            int[] son = makeSon(node);                  // put off lazy to son
            lazy[son[0]] ^= lazy[node];
            lazy[son[1]] ^= lazy[node];
        }

        int val = (e - s) % 2 == 0 ? 0: lazy[node];     // a ^ a == 0
        tree[node] ^= val;
        lazy[node] = 0;
    }

    public static void xor(int s, int e, int k, int node, int ns, int ne){
        propagation(node, ns, ne);

        if(e <= ns || ne <= s) return;              // is terminal node
        if(s <= ns && ne <= e){                     // fully included
            lazy[node] ^= k;
            propagation(node, ns, ne);
            return;
        }

        int mid = (ns + ne) / 2;
        int[] son = makeSon(node);

        xor(s, e, k, son[0], ns, mid);              // find next section
        xor(s, e, k, son[1], mid, ne);

        tree[node] = tree[son[0]] ^ tree[son[1]];   // update terminal
    }

    public static int sectionXor(int s, int e, int node, int ns, int ne){
        propagation(node, ns, ne);

        if(e <= ns || ne <= s) return 0;            // out of range (ns, ne)
        if(s <= ns && ne <= e) return tree[node];

        int mid = (ns + ne) / 2;
        int[] son = makeSon(node);

        return sectionXor(s, e, son[0], ns, mid) ^ sectionXor(s, e, son[1], mid, ne);
    }
}
