package heavy_light_decomposition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11438번: LCA2
 *
 * @see https://www.acmicpc.net/problem/11438
 *
 */
public class Boj11438 {
    private static int[] size, parent;
    private static ArrayList<Integer>[] connection;

    private static ArrayList<Integer>[] chain;              // included in chain's number sequence (parent -> son), start node is i
    private static int[] depth;                             // chain depth that is each nodes included
    private static int[] chainNum, chainIdx;                // chain number / save the nodes order in each chain

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            connection[node1].add(node2);
            connection[node2].add(node1);
        }

        makeSubTreeSize(1, 0);
        HLD(1, 0, 1, 0);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            sb.append(LCA(node1, node2)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(int n) {
        size = new int[n + 1];
        parent = new int[n + 1];
        connection = new ArrayList[n + 1];

        chain = new ArrayList[n + 1];
        chainNum = new int[n + 1];
        chainIdx = new int[n + 1];
        depth = new int[n + 1];

        for(int i = 0; i <= n; i++) {
            connection[i] = new ArrayList<>();
            chain[i] = new ArrayList<>();
        }
    }

    /**
     *
     * @param current
     * @param p
     * @return: current node's subtree size
     */
    private static int makeSubTreeSize(int current, int p) {
        parent[current] = p;
        size[current] = 1;

        for(int next: connection[current]) {
            if(next == p) continue;

            size[current] += makeSubTreeSize(next, current);
        }

        return size[current];
    }

    /**
     *
     * @param current: current node number
     * @param parent: parent node number
     * @param currentChain: current chain number
     * @param deep: current chain depth
     */
    private static void HLD(int current, int parent, int currentChain, int deep) {
        depth[current] = deep;

        chainNum[current] = currentChain;                       // add node int the chain
        chainIdx[current] = chain[currentChain].size();         // save sequence of node in this chain
        chain[currentChain].add(current);

        int heavy = -1;
        for(int next: connection[current]) {                    // find most heavy
            if(next == parent) continue;
            if(heavy != -1 && size[next] <= size[heavy]) continue;

            heavy = next;
        }

        if(heavy != -1) HLD(heavy, current, currentChain, deep);    // at first decompositioning by most heavy subtree

        for(int next: connection[current]) {                        // numbering others with most heavy
            if(next == parent || next == heavy) continue;

            HLD(next, current, next, deep + 1);
        }
    }

    private static int LCA(int x, int y) {
        while (chainNum[x] != chainNum[y]) {                        // push up, until two nodes' included same chain
            if (depth[x] > depth[y]) x = parent[chainNum[x]];
            else y = parent[chainNum[y]];
        }
        
        return chainIdx[x] > chainIdx[y] ? y : x;
    }
}
