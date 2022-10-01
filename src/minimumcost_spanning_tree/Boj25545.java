package minimumcost_spanning_tree;

import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25545번: MMST
 *
 * @see https://www.acmicpc.net/problem/25545
 *
 */
public class Boj25545 {

    private static final ArrayList<MstNode> COMPONENTS = new ArrayList<>();
    private static final String O = "YES\n";
    private static final String X = "NO\n";
    private static final String SPACE = " ";
    private static int[] parent;
    private static int N;
    private static int x, y;

    private static TreeSet<Integer> index;
    private static int except = -1;
    private static long[] costs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        costs = new long[M];

        int I = 1;
        int loop = M;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken()) - 1;
            int V = Integer.parseInt(st.nextToken()) - 1;
            long C = Long.parseLong(st.nextToken());

            costs[I - 1] = C;
            COMPONENTS.add(new MstNode(U, V, C, I++));
        }

        init();
        mst();
        System.out.print(M == N - 1 ? X: print());
    }

    private static String print() {
        index = new TreeSet<>();
        index.add(except);

        init();
        merged(x, y);
        mst();

        StringBuilder sb = new StringBuilder();
        sb.append(O);

        for(int key: index) {
            sb.append(key).append(SPACE);
        }

        return sb.toString();
    }

    private static void mst() {
        COMPONENTS.sort(Comparator.comparingLong(MstNode::getCost));

        for(MstNode node: COMPONENTS) {
            if(merged(node.getNode(), node.getAnother())) {
                x = node.getNode();
                y = node.getAnother();
                except = node.getIndex();

                continue;
            }

            if(index == null) continue;
            index.add(node.getIndex());
        }
    }

    private static void init() {
        parent = new int[N];
        Arrays.fill(parent, -1);
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return true;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }
}

class MstNode {
    private final int node;
    private final int another;
    private final long cost;
    private final int index;

    public MstNode(int node, int another, long cost, int index) {
        this.node = node;
        this.another = another;
        this.cost = cost;
        this.index = index;
    }

    public int getNode() {
        return node;
    }

    public int getAnother() {
        return another;
    }

    public long getCost() {
        return cost;
    }

    public int getIndex() {
        return index;
    }
}