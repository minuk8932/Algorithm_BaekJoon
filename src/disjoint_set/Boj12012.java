package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12012번: Closing the Farm
 *
 * @see https://www.acmicpc.net/problem/12012
 *
 */
public class Boj12012 {

    private static final String FULLY = "YES\n";
    private static final String DISTRIBUTED = "NO\n";

    private static ArrayList<ArrayList<Integer>> farms = new ArrayList<>();
    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        Arrays.fill(parent, -1);

        for(int i = 0; i < N; i++) {
            farms.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            farms.get(node1).add(node2);
            farms.get(node2).add(node1);
        }

        ArrayDeque<Integer> query = new ArrayDeque<>();
        while(N-- > 0) {
            query.push(Integer.parseInt(br.readLine()) - 1);
        }

        System.out.print(travel(query));
    }

    private static String travel(ArrayDeque<Integer> query) {
        long count = 0;
        boolean[] visit = new boolean[query.size()];

        ArrayDeque<String> stack = new ArrayDeque<>();
        while(!query.isEmpty()) {
            int current = query.pop();
            count++;

            visit[current] = true;

            for(int adj: farms.get(current)) {
                if(!visit[adj]) continue;
                merge(current, adj);
            }

            stack.push(count == -parent[find(current)] ? FULLY: DISTRIBUTED);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}
