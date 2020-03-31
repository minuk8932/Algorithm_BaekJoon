package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13306번: 트리
 *
 * @see https://www.acmicpc.net/problem/13306/
 *
 */
public class Boj13306 {
    private static ArrayList<Integer>[] tree;
    private static int[] parent;

    private static final String NEW_LINE = "\n";
    private static final String Y = "YES", N = "NO";

    private static class Query {
        int cmd;
        int a;
        int b;

        public Query(int cmd, int a) {
            this.cmd = cmd;
            this.a = a;
        }

        public Query(int cmd, int a, int b) {
            this.cmd = cmd;
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        init(n);

        int loop = n - 1;
        for(int i = 1; i <= loop; i++) {
            int node = Integer.parseInt(br.readLine()) - 1;

            tree[i].add(node);
        }

        ArrayDeque<Query> stack = new ArrayDeque<>();
        loop = n - 1 + q;

        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()) - 1;

            if(cmd == 0) {                                      // make reversed query
                stack.push(new Query(cmd, a));
            }
            else{
                int b = Integer.parseInt(st.nextToken()) - 1;
                stack.push(new Query(cmd, a, b));
            }
        }

        ArrayDeque<Boolean> answer = new ArrayDeque<>();

        while (!stack.isEmpty()){                               // answer
            Query current = stack.pop();

            if(current.cmd == 0) merge(current.a, tree[current.a].get(0));
            else answer.push(find(current.a) == find(current.b));
        }

        StringBuilder sb = new StringBuilder();
        while(!answer.isEmpty()) sb.append(answer.pop() ? Y: N).append(NEW_LINE);

        System.out.println(sb.toString());
    }

    private static void init(int n){
        parent = new int[n];
        tree = new ArrayList[n];

        for(int i = 0; i < n; i++){
            tree[i] = new ArrayList<>();
            parent[i] = -1;
        }
    }

    private static int find(int x){
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]){
            parent[x] += parent[y];
            parent[y] = x;
        }
        else{
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}
