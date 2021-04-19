package topology_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9470번: Strahler 순서
 *
 * @see https://www.acmicpc.net/problem/9470
 *
 */
public class Boj9470 {

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static List<Integer>[] river;
    private static int[] indegree;
    private static int[] strahler;
    private static final int CIPHER = 10_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            river = new ArrayList[M];
            indegree = new int[M];
            strahler = new int[M];

            for(int i = 0; i < M; i++) {
                river[i] = new ArrayList<>();
            }

            while(P-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                river[from].add(to);
                indegree[to]++;
            }

            sb.append(K).append(SPACE).append(topologySort(M)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Topology Sort
     *
     * line 74: start nodes
     * line 85 ~ 91: update next node to largest, if max flows are much more than 2 next value: currnet + 1.
     *
     * @param m
     * @return
     */
    private static int topologySort(int m) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if(indegree[i] != 0) continue;
            q.offer(i);
            strahler[i] = CIPHER + 1;
        }

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: river[current]) {
                indegree[next]--;
                int head = strahler[next] / CIPHER;
                int curHead = strahler[current] / CIPHER;

                if(head == curHead){
                    strahler[next] = strahler[next] + 1;
                    if(strahler[next] % CIPHER >= 2) strahler[next] = (head + 1) * CIPHER;
                }
                else if(head < curHead){
                    strahler[next] = curHead * CIPHER + 1;
                }

                if(indegree[next] != 0) continue;
                q.offer(next);
            }
        }

        return strahler[m - 1] / CIPHER;
    }
}
