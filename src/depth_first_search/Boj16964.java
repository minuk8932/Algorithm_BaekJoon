package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16964번: DFS 스페셜 저지
 *
 * @see https://www.acmicpc.net/problem/16964/
 *
 */
public class Boj16964 {
    private static ArrayList<Integer>[] tree;
    private static int[] visit;
    private static int[] seq;

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        visit = new int[N];
        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
            visit[i] = -2;
        }

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        seq = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        visit[0] = -1;
        dfs(0);

        System.out.println(makeSPJ());
    }

    private static int makeSPJ() {
        if(seq[0] != 0) return 0;

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            while(!stack.isEmpty()) {
                int parent = stack.peek();

                if (parent == visit[seq[i]]) break;         // contains parent ?
                if (stack.size() == 1) return 0;            // !contains

                stack.pop();
            }

            stack.push(seq[i]);
        }

        return 1;
    }

    private static void dfs(int current) {
        for(int next: tree[current]) {
            if(visit[next] != -2) continue;
            visit[next] = current;

            dfs(next);
        }
    }
}
