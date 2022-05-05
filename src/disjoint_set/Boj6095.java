package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 6095번: Surround the islands
 *
 * @see https://www.acmicpc.net/problem/6095
 *
 */
public class Boj6095 {

    private static final int INF = 1_000_000_000;

    private static Set<Integer> sets = new HashSet<>();
    private static int[][] graph;
    private static int[] parent;

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        init(N);

        int loop = N;
        while (loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V1 = Integer.parseInt(st.nextToken()) - 1;
            int V2 = Integer.parseInt(st.nextToken()) - 1;

            merged(V1, V2);
        }

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sets.add(find(i));

            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(shortestCostProcess(N));
    }

    private static int shortestCostProcess(int n) {
        int[][] cost = new int[n][n];
        for (int i = 0; i < n ; i++) {
            Arrays.fill(cost[i], INF);
        }

        for (int i = 0; i < n ; i++){
            int x = find(i);

            for (int j = 0; j < n ; j ++){
                int y = find(j);
                cost[x][y] = Math.min(cost[x][y], graph[i][j]);
            }
        }


        int size = sets.size();
        int[] pos = new int[size];
        int index = 0;
        for(int i = 0; i < n; i++) {
            if (find(i) != i) continue;
            pos[index++] = i;
        }

        int answer = INF;
        for (int i = 0; i < size; i++){
            int sum = 0;

            for (int j = 0; j < size; j++){
                if (i == j) continue;
                sum += cost[pos[i]][pos[j]];
            }

            answer = Math.min(answer, sum);
        }

        return answer << 1;
    }

    private static boolean merged(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]){
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static int find(int x){
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void init(int n) {
        graph = new int[n][n];
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }
}