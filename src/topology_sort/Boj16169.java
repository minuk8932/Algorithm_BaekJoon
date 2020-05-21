package topology_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16169번: 수행 시간
 *
 * @see https://www.acmicpc.net/problem/16169/
 *
 */
public class Boj16169 {
    private static ArrayList<Computer>[] coms;
    private static int[] result, timer;
    private static int[] indegree;

    private static class Computer {
        int node;
        int transfer;

        public Computer(int node, int transfer) {
            this.node = node;
            this.transfer = transfer;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        init(n);

        int[] level = new int[n];
        timer = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            level[i] = Integer.parseInt(st.nextToken()) - 1;
            timer[i] = Integer.parseInt(st.nextToken());

            result[i] = timer[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (level[i] + 1 == level[j]) {
                    coms[i].add(new Computer(j, (int) Math.pow(i - j, 2)));    // make relation
                    indegree[j]++;
                }
            }
        }

        System.out.println(runtime(n));
    }

    private static void init (int n) {
        indegree = new int[n];
        coms = new ArrayList[n];
        result = new int[n];

        for(int i = 0; i < n; i++) {
            coms[i] = new ArrayList<>();
        }
    }

    private static int runtime(int n) {
        Queue<Integer> q = new LinkedList<>();
        int max = 0;

        for(int i = 0; i < n; i++) {
            if (indegree[i] != 0) continue;
            q.offer(i);
        }

        while(!q.isEmpty()) {
            int current = q.poll();
            int size = coms[current].size();

            for(int i = 0; i < size; i++) {
                Computer next = coms[current].get(i);
                indegree[next.node]--;

                if(result[next.node] < result[current] + next.transfer + timer[next.node]){ // update for max value
                    result[next.node] = result[current] + next.transfer + timer[next.node];
                    max = Math.max(result[next.node], max);
                }

                if(indegree[next.node] == 0) q.offer(next.node);
            }
        }

        return max;
    }
}
