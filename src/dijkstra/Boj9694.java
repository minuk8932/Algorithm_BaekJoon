package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9694번: 무엇을 아느냐가 문제가 아니라, 누구를 아느냐가 문제다.
 *
 * @see https://www.acmicpc.net/problem/9694/
 *
 */
public class Boj9694 {
    private static StringBuilder pathSave;
    private static ArrayList<PersonalConnections>[] intimacy;
    private static ArrayList<Integer> path;
    private static int[] cost;
    private static boolean flag;

    private static final int INF = 100_000_000;
    private static final String NEW_LINE = "\n";
    private static final String CASE = "Case #";
    private static final String COLON = ": ";
    private static final String SPACE = " ";
    private static final String NONE = "-1";

    private static class PersonalConnections implements Comparable<PersonalConnections>{
        int politician;
        int intimate;

        public PersonalConnections(int politician, int intimate) {
            this.politician = politician;
            this.intimate = intimate;
        }

        @Override
        public int compareTo(PersonalConnections pc) {
            return this.intimate < pc.intimate ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 0;

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            intimacy = new ArrayList[M];
            path = new ArrayList<>();
            pathSave = new StringBuilder();
            cost = new int[M];

            for(int i = 0; i < M; i++) {
                intimacy[i] = new ArrayList<>();
                cost[i] = INF;
            }

            while(N-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                intimacy[x].add(new PersonalConnections(y, z));
                intimacy[y].add(new PersonalConnections(x, z));
            }

            int result = dijkstra(0, M - 1);                    // find minimum cost

            if(result == INF){
                sb.append(CASE).append(++t).append(COLON).append(NONE).append(NEW_LINE);
                continue;
            }

            flag = false;

            path.add(M - 1);
            reverse(new PersonalConnections(M - 1, cost[M - 1]));       // find specific path
            sb.append(CASE).append(++t).append(COLON).append(pathSave.toString()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void reverse(PersonalConnections current) {
        if(flag) return;

        if(current.politician == 0) {                   // meet
            int size = path.size();

            for(int i = size - 1; i >= 0; i--) {
                pathSave.append(path.get(i)).append(SPACE);
            }

            flag = true;
            return;
        }

        for(PersonalConnections before: intimacy[current.politician]) {
            if (flag) continue;
            if (cost[before.politician] != cost[current.politician] - before.intimate) continue;

            path.add(before.politician);
            reverse(new PersonalConnections(before.politician, cost[before.politician]));
        }
    }

    private static int dijkstra(int start, int end){
        PriorityQueue<PersonalConnections> pq = new PriorityQueue<>();
        pq.offer(new PersonalConnections(start, 0));
        cost[start] = 0;

        while(!pq.isEmpty()) {
            PersonalConnections current = pq.poll();

            for(PersonalConnections next: intimacy[current.politician]) {
                if(cost[next.politician] <= cost[current.politician] + next.intimate) continue;
                cost[next.politician] = cost[current.politician] + next.intimate;

                pq.offer(next);
            }
        }

        return cost[end];
    }
}
