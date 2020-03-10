package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1395번: 스위치
 *
 * @see https://www.acmicpc.net/problem/1395/
 *
 */
public class Boj1395 {
    private static int INF = 1 << 20;
    private static int start = INF / 2;
    private static int[] tree = new int[INF];
    private static boolean[] lazy = new boolean[INF];

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int T = Integer.parseInt(st.nextToken());


            if(cmd == 0){
                click(S, T, 1, 0, start);
            }
            else{
                sb.append(status(S, T, 1, 0, start)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static int[] makeSon(int node){
        return new int[]{node * 2, node * 2 + 1};
    }

    private static void propagation(int node, int s, int e){
        if(!lazy[node]) return;

        if(node < start){
            int[] son = makeSon(node);
            lazy[son[0]] ^= true;           // click
            lazy[son[1]] ^= true;

            int size = (e - s) / 2;
            int sum = 0;

            if(lazy[son[0]]) sum += size - tree[son[0]];    // if left side flipping
            else sum += tree[son[0]];

            if(lazy[son[1]]) sum += size - tree[son[1]];
            else sum += tree[son[1]];

            tree[node] = sum;
        }
        else{
            tree[node] ^= 1;
        }

        lazy[node] = false;
    }

    private static void click(int s, int e, int node, int ns, int ne){
        propagation(node, ns, ne);

        if(e <= ns || ne <= s) return;
        if(s <= ns && ne <= e){
            lazy[node] ^= true;                         // clicked
            propagation(node, ns, ne);
            return;
        }

        int[] son = makeSon(node);
        int mid = (ns + ne) / 2;

        click(s, e, son[0], ns, mid);
        click(s, e, son[1], mid, ne);

        tree[node] = tree[son[0]] + tree[son[1]];       // get on switch
    }

    private static int status(int s, int e, int node, int ns, int ne){
        propagation(node, ns, ne);

        if(e <= ns || ne <= s) return 0;
        if(s <= ns && ne <= e) return tree[node];

        int[] son = makeSon(node);
        int mid = (ns + ne) / 2;

        return status(s, e, son[0], ns, mid) + status(s, e, son[1], mid, ne);
    }
}
