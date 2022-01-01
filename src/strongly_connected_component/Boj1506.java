package strongly_connected_component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1506번: 경찰서
 *
 * @see https://www.acmicpc.net/problem/1506
 *
 */
public class Boj1506 {

    private static int min;
    private static int count;

    private static boolean[] visit;
    private static int[] cost;
    private static int[] lowest;
    private static int[] number;

    private static Deque<Integer> stack = new ArrayDeque<>();
    private static List<Integer>[] path;

    private static final int INF = 1_000_000_000;
    private static final char BLOCK = '0';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        cost = new int[N];
        lowest = new int[N];
        number = new int[N];

        visit = new boolean[N];
        path = new ArrayList[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            path[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                boolean way = line.charAt(j) != BLOCK;
                if(!way) continue;

                path[i].add(j);
            }
        }

        for (int i = 0; i < N; i++) {
            if(number[i] != 0) continue;
            recursion(i);
        }

        System.out.println(min);
    }

    /**
     *
     * Recursion: tarjan
     *
     * @param current
     */
    private static void recursion(int current) {
        visit[current] = true;
        stack.push(current);

        number[current] = ++count;
        lowest[current] = count;

        for(int next: path[current]) {
            if(number[next] == 0) {
                recursion(next);
                lowest[current] = Math.min(lowest[current], lowest[next]);

                continue;
            }

            if(!visit[next]) continue;
            lowest[current] = Math.min(lowest[current], number[next]);
        }

        if(lowest[current] != number[current]) return;
        int minCost = INF;

        while(!stack.isEmpty()) {
            int top = stack.pop();

            minCost = Math.min(minCost, cost[top]);

            visit[top] = false;
            if(current == top) break;
        }

        min += minCost;
    }
}
