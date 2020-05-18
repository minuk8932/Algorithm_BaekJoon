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
 * 백준 14567번: 선수 과목
 *
 * @see https://www.acmicpc.net/problem/14567/
 *
 */
public class Boj14567 {
    private static int[] indegree;
    private static ArrayList<Integer>[] graph;

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        indegree = new int[N];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            graph[node1].add(node2);
            indegree[node2]++;                  // make priority
        }

        System.out.println(topologySort(N));
    }

    private static String topologySort(int n) {
        Queue<Integer> q = new LinkedList<>();
        int[] visit = new int[n];

        for(int i = 0; i < n; i++) {
            if(indegree[i] != 0) continue;
            q.offer(i);
            visit[i] = 1;
        }

        while(!q.isEmpty()){
            int current = q.poll();

            for(int next: graph[current]) {
                indegree[next]--;

                if(visit[next] < visit[current] + 1) visit[next] = visit[current] + 1;
                if(indegree[next] == 0) q.offer(next);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {                // prerequisite
            sb.append(visit[i]).append(SPACE);
        }

        return sb.toString();
    }
}
