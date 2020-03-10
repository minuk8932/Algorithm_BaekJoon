package segment_tree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 10999번: 구간 합 구하기2
 *
 *	@see https://www.acmicpc.net/problem/10999/
 *
 */
public class Boj10999 {
    private static final int INF = 1 << 21;
    private static final String NEW_LINE = "\n";

    private static class Tree{
        int start;
        long[] tree;
        long[] lazy;

        public Tree(){
            start = INF / 2;
            tree = new long[INF];
            lazy = new long[INF];
        }

        private void init(){
            for(int i = start - 1; i > 0; i--){             // segment tree initialization
                int[] son = makeSon(i);
                tree[i] = tree[son[0]] + tree[son[1]];
            }
        }

        private void propagation(int node, int s, int e){
            if(lazy[node] == 0) return;                      // not thing to put off

            if(node < start){                                // little than start it means segment nodes (not real)
                int[] son = makeSon(node);
                lazy[son[0]] += lazy[node];                  // spread lazy to left, right sons
                lazy[son[1]] += lazy[node];
            }

            tree[node] += lazy[node] * (e - s);              // making total lazy & update tree values (hold total lazy)
            lazy[node] = 0;
        }

        private void add(int s, int e, int k, int node, int ns, int ne){
            propagation(node, ns, ne);

            if(e <= ns || ne <= s) return;
            if(s <= ns && ne <= e){                     // node section fully included
                lazy[node] += k;
                propagation(node, ns, ne);
                return;
            }

            int[] son = makeSon(node);
            int mid = (ns + ne) / 2;                    // if not, divide half and tracking
            add(s, e, k, son[0], ns, mid);
            add(s, e, k, son[1], mid, ne);

            tree[node] = tree[son[0]] + tree[son[1]];   // update node by son's value
        }

        private long sectionSum(int s, int e, int node, int ns, int ne){
            propagation(node, ns, ne);

            if(e <= ns || ne <= s) return 0;
            if(s <= ns && ne <= e) return tree[node];                     // node section fully included

            int[] son = makeSon(node);
            int mid = (ns + ne) / 2;

            return sectionSum(s, e, son[0], ns, mid) + sectionSum(s, e, son[1], mid, ne);   // if not divide & conquer, half by half
        }

        private int[] makeSon(int node){
            return new int[]{node * 2, node * 2 + 1};
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Tree trees = new Tree();
        for(int i = trees.start; i < N + trees.start; i++){
            trees.tree[i] = Integer.parseInt(br.readLine());
        }

        trees.init();

        int loop = M + K;
        StringBuilder sb = new StringBuilder();

        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                int d = Integer.parseInt(st.nextToken());
                trees.add(b, c, d, 1, 0, trees.start);
            }
            else{
                sb.append(trees.sectionSum(b, c, 1, 0, trees.start)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }
}
