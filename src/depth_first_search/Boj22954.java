package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22954번: 그래프 트리 분할
 *
 * @see https://www.acmicpc.net/problem/22954
 *
 */
public class Boj22954 {

    private static List<Node>[] nodes;
    private static List<Integer>[] vertexes;
    private static List<Integer>[] edges;

    private static boolean[] visit;
    private static boolean[] isLeaf;

    private static int size;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final String NONE = "-1";

    private static class Node {
        int node;
        int index;

        public Node(int node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = PARSE.apply(st.nextToken());
        int M = PARSE.apply(st.nextToken());

        init(N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = PARSE.apply(st.nextToken()) - 1;
            int node2 = PARSE.apply(st.nextToken()) - 1;

            nodes[node1].add(new Node(node2, i));
            nodes[node2].add(new Node(node1, i));
        }

        for(int i = 0; i < N; i++){
            if (visit[i]) continue;

            recursion(i, size);
            size++;
        }

        System.out.print(prints(N));
    }

    private static String prints(int n) {
        if(size > 2) return NONE;
        if(size < 2) decomposition(n);

        if (vertexes[0].size() == vertexes[1].size()) return NONE;

        StringBuilder sb = new StringBuilder();

        sb.append(vertexes[0].size()).append(SPACE).append(vertexes[1].size()).append(NEW_LINE);

        for(int i = 0; i < 2; i++) {
            Collections.sort(vertexes[i]);
            Collections.sort(edges[i]);

            for(int vertex: vertexes[i]) {
                sb.append(vertex + 1).append(SPACE);
            }

            sb.append(NEW_LINE);

            for(int edge: edges[i]) {
                sb.append(edge + 1).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    /**
     *
     * Decomposition
     *
     * divide for 2 trees
     *
     * @param n
     */
    private static void decomposition(int n) {
        int leaf = 0;
        int start = 0;

        for (int vertex: vertexes[0]){
            if (isLeaf[vertex]) leaf = vertex;
            else start = vertex;
        }

        vertexes[0] = new ArrayList<>();
        edges[0] = new ArrayList<>();

        visit = ARRY.apply(n);
        visit[leaf] = true;

        recursion(start, 0);
        vertexes[1].add(leaf);
    }

    private static void recursion(int current, int index) {
        vertexes[index].add(current);
        visit[current] = true;

        boolean flag = true;

        for (Node next: nodes[current]){
            if (visit[next.node]) continue;
            edges[index].add(next.index);

            flag = false;
            recursion(next.node, index);
        }

        isLeaf[current] = flag;
    }

    private static void init(int n) {
        nodes = new ArrayList[n + 1];
        vertexes = new ArrayList[n + 1];
        edges = new ArrayList[n + 1];

        visit = ARRY.apply(n + 1);
        isLeaf = ARRY.apply(n + 1);

        for(int i = 0; i <= n; i++) {
            nodes[i] = new ArrayList<>();
            vertexes[i] = new ArrayList<>();
            edges[i] = new ArrayList<>();
        }
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, boolean[]> ARRY = boolean[]::new;
}
