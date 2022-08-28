package disjoint_set;

import common.Node;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25482번: 분필 도둑
 *
 * @see https://www.acmicpc.net/problem/25482
 *
 */
public class Boj25482 {

    private static int[] parent;
    private static long[] chalk;

    private static ArrayList<ArrayList<Integer>> path = new ArrayList<>();

    private static ArrayList<Node<Integer, Long>> classInfo = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        chalk = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            chalk[i] = Long.parseLong(st.nextToken());
            path.add(i, new ArrayList<>());

            classInfo.add(new Node.Builder<Integer, Long>(i).cost(-chalk[i]).build());
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            path.get(u).add(v);
            path.get(v).add(u);
        }

        classInfo.sort(Comparator.comparingLong(Node::getCost));
        System.out.println(stealing());
    }

    private static long stealing() {
        boolean[] approval = new boolean[classInfo.size()];
        long max = 0;

        for(Node<Integer, Long> node: classInfo) {
            approval[node.getNode()] = true;

            for(int next: path.get(node.getNode())) {
                if(!approval[next]) continue;
                merge(node.getNode(), next);
            }

            max = Math.max(node.getCost() * parent[find(node.getNode())], max);
        }

        return max;
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if(parent[x] > parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    private static void init(int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);
    }
}
