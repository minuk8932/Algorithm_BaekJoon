package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2213번: 트리의 최대 독립 집합
 *
 * @see https://www.acmicpc.net/problem/2213
 *
 */
public class Boj2213 {

    private static PriorityQueue pq = new PriorityQueue();
    private static ArrayList<Integer>[] edges;
    private static int[] vertex;
    private static int[][] dp;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        vertex = new int[N + 1];
        edges = new ArrayList[N + 1];
        dp = new int[N + 1][3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i <= N; i++) {
            if(i != 0) vertex[i] = Integer.parseInt(st.nextToken());
            edges[i] = new ArrayList<>();
            dp[i][0] = dp[i][1] = dp[i][2] = -1;
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            edges[node1].add(node2);
            edges[node2].add(node1);
        }

        StringBuilder sb = new StringBuilder();
        int[] result = {recursion(0, 1, 0), recursion(0, 1, 1)};

        sb.append(Math.max(result[0], result[1])).append(NEW_LINE);
        if(result[1] >= result[0]) vertexTrace(0, 1, 1);
        else vertexTrace(0, 1, 0);

        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append(SPACE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Vertex trace
     *
     * line 84 ~ 90: compare current visit or not, and check before state
     *
     * @param prev
     * @param current
     * @param select
     */
    private static void vertexTrace(int prev, int current, int select) {
        if(select == 1) pq.offer(current);

        for(int next: edges[current]) {
            if(prev == next) continue;

            int left = recursion(current,  next, 0);
            int right = recursion(current,  next, 1);


            if (left > right) {
                vertexTrace(current, next, 0);
            }
            else {
                if(select == 1) vertexTrace(current, next, 0);
                else vertexTrace(current, next, 1);
            }
        }
    }

    /**
     *
     * Recursion
     *
     * line 113 ~ 114: add all cases, visit or not
     *
     * @param prev
     * @param current
     * @param select
     * @return
     */
    private static int recursion(int prev, int current, int select) {
        int result = dp[current][select];
        if(result != -1) return result;
        result = select == 1 ? vertex[current]: 0;

        for(int next: edges[current]) {
            if(prev == next) continue;

            if(select == 0) result += Math.max(recursion(current, next, 0), recursion(current, next, 1));
            else result += recursion(current, next, 0);
        }

        return dp[current][select] = result;
    }
}
